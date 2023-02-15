package com.example.inclass05;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inclass05.databinding.FragmentAppDetailsBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1_APP = "ARG_PARAM1_APP";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private DataServices.App mParam1;
    private String mParam2;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AppDetailsFragment newInstance(DataServices.App app) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1_APP, app);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (DataServices.App) getArguments().getSerializable(ARG_PARAM1_APP);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FragmentAppDetailsBinding binding;
    ArrayAdapter<String> adapter;
    ArrayList<String> appArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppDetailsBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewName.setText(mParam1.getName());
        binding.textViewartist.setText((mParam1.getArtistName()));
        binding.textViewRelease.setText(mParam1.getReleaseDate());
        binding.textViewgenres.setText("Genres");


        appArrayList = mParam1.getGenres();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, appArrayList);
        binding.listView.setAdapter(adapter);
    }
}