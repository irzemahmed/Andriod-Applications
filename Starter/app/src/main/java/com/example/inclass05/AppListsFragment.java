package com.example.inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.inclass05.databinding.FragmentAppListsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppListsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppListsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1_CATEGORY = "ARG_PARAM1_CATEGORY";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mCategory;
    private String mParam2;

    public AppListsFragment() {
        // Required empty public constructor
    }
    public static AppListsFragment newInstance(String category) {
        AppListsFragment fragment = new AppListsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1_CATEGORY, category);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM1_CATEGORY);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FragmentAppListsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppListsBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    ArrayList<DataServices.App> appList;
    userAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(mCategory);
        ListView listView;
        listView = binding.listView;
        appList = DataServices.getAppsByCategory(mCategory);
        adapter = new userAdapter(getActivity(), appList);

        listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               DataServices.App app = appList.get(i);

                mListener.sendSelectedAppList(app);
            }
        });




    }
    AppListsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (AppListsFragmentListener) context;
    }

    interface AppListsFragmentListener {
        void sendSelectedAppList(DataServices.App app);
    }

    class userAdapter extends ArrayAdapter<DataServices.App> {
        public userAdapter(@NonNull Context context, @NonNull List<DataServices.App> objects) {
            super(context, R.layout.app_list_view, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.app_list_view, parent, false);

            }
            DataServices.App app = appList.get(position);
            TextView textViewAppNAME = convertView.findViewById(R.id.textViewAppNAME);
            TextView textViewArtistName = convertView.findViewById(R.id.textViewArtistName);
            TextView textViewdate = convertView.findViewById(R.id.textViewdate);

            textViewAppNAME.setText(app.getName());
            textViewArtistName.setText(app.getArtistName());
            textViewdate.setText(app.getReleaseDate());


            return convertView;
        }
    }

}