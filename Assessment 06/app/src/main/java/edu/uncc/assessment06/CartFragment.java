package edu.uncc.assessment06;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.assessment06.databinding.CartRowItemBinding;
import edu.uncc.assessment06.databinding.FragmentCartBinding;


public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Cart");
        adapter = new ProductsAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);




        getProducts();
        adapter.notifyDataSetChanged();

    }


    ArrayList<Product> mProducts = new ArrayList<>();
    ListenerRegistration listenerRegistration;

    private void getProducts() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listenerRegistration = db.collection("cart")
                .whereEqualTo("owner_id", FirebaseAuth.getInstance().getCurrentUser().getUid())//SHOWS ONLY OWNERS GRADES
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            mProducts.clear(); //CLEAR THE ARRAYLIST BEFORE USE
                            for (QueryDocumentSnapshot document : value) {//ITERATE THROUGH THE DATABASE VALUES
                                Product product = document.toObject(Product.class);// GET THE PRODUCT BY TARGETING THE DOCUMENT WITH PRODUCT.CLASS
                                mProducts.add(product);//ADD IT INTO THE ARRAYLIST mProducts
                            }
                            //TOTAL THE PRICES AND DISPLAY
                            double cartTotal = 0.0;
                            for(Product product : mProducts){
                                cartTotal += Double.valueOf(product.getPrice());
                            }

                            binding.textViewTotal.setText("$ " + String.format("%.2f", cartTotal));

                            adapter.notifyDataSetChanged();//NOTIFY THE ADAPTER TO REFRESH IT TO SHOW NEW VALUES

                        }
                    }
                });

    }


    ProductsAdapter adapter;


    class ProductsAdapter extends RecyclerView.Adapter<CartFragment.ProductsAdapter.ProductViewHolder> {

        @NonNull
        @Override
        public CartFragment.ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CartFragment.ProductsAdapter.ProductViewHolder(CartRowItemBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CartFragment.ProductsAdapter.ProductViewHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.setupUI(product);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }

        class ProductViewHolder extends RecyclerView.ViewHolder {
            CartRowItemBinding mBinding;
            Product mProduct;

            public ProductViewHolder(CartRowItemBinding rowItemBinding) {
                super(rowItemBinding.getRoot());
                mBinding = rowItemBinding;
            }

            void setupUI(Product product) {
                this.mProduct = product;

                mBinding.textViewProductName.setText(product.getName());
                mBinding.textViewProductPrice.setText("$" + product.getPrice());
                Picasso.get().load(product.getImg_url()).into(mBinding.imageViewProductIcon);

                mBinding.imageViewDeleteFromCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseFirestore.getInstance().collection("cart")
                                .document(mProduct.getDocId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mProducts.remove(mProduct);
                                        Toast.makeText(getActivity(), "Product Removed !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });


            }
        }
    }


}