package com.example.engineoilchangeapp;

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

import com.example.engineoilchangeapp.databinding.FragmentListBinding;
import com.example.engineoilchangeapp.databinding.ListRowItemBinding;
import com.example.engineoilchangeapp.model.OilChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    ArrayList<OilChange> oilChanges = new ArrayList<>();

    public ListFragment() {
    }

        FragmentListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getActivity().setTitle("Engine Oil Changes");

        adapter = new OilChangeAdapter();
        binding.recyclerViewOilChanges.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewOilChanges.setHasFixedSize(true);
        binding.recyclerViewOilChanges.setAdapter(adapter);

        getOilChanges();
    }

    ListenerRegistration listenerRegistration;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    void getOilChanges(){
        listenerRegistration = db.collection("oilchanges").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                } else{
                    oilChanges.clear();

                    for (QueryDocumentSnapshot doc : value){
                        OilChange oilChange = doc.toObject(OilChange.class);
                        oilChanges.add(oilChange);
                    }

                    adapter.notifyDataSetChanged();

                }
            }
        });
    }

    OilChangeAdapter adapter;
    class OilChangeAdapter extends RecyclerView.Adapter<OilChangeAdapter.OilChangeViewHolder>{

        @NonNull
        @Override
        public OilChangeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new OilChangeViewHolder(ListRowItemBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OilChangeViewHolder holder, int position) {
            OilChange oilChange = oilChanges.get(position);
            //SETUP UI
            holder.setupUI(oilChange);
        }

        @Override
        public int getItemCount() {
            return oilChanges.size();
        }

        class OilChangeViewHolder extends RecyclerView.ViewHolder{

            OilChange mOilChange;
            ListRowItemBinding mBinding;

            public OilChangeViewHolder(ListRowItemBinding mBinding) {
                super(mBinding.getRoot());
                this.mBinding = mBinding;
                mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //CLICKING HERE CAN SEND TO DETAILS (ANOTHER FRAGMENT)
                    }
                });
            }

            void setupUI (OilChange oilChange) {
                this.mOilChange = oilChange;
                mBinding.textViewDate.setText(mOilChange.getDate());
                mBinding.textViewMileage.setText(mOilChange.getMileage());
                mBinding.textViewNextDue.setText("TBA");

            }
        }
    }
}