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

import com.example.inclass05.databinding.FragmentAppCatagoriesBinding;

import java.util.ArrayList;

public class AppCatagoriesFragment extends Fragment {


    public AppCatagoriesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentAppCatagoriesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAppCatagoriesBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }
    ArrayList<String> categories;
    ArrayAdapter<String> adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("App Categories");
        categories = DataServices.getAppCategories();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, categories );
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String category = categories.get(i);
                mListener.sendSelectedCategory(category);

            }
        });
    }

    AppCategoriesFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppCategoriesFragmentListener) context;
    }

    interface AppCategoriesFragmentListener{
        void sendSelectedCategory(String category);
    }

}