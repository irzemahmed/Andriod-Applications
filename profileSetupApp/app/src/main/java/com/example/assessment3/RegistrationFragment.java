package com.example.assessment3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assessment3.databinding.FragmentRegistrationBinding;


public class RegistrationFragment extends Fragment {

    private String selectedGender;

    public RegistrationFragment() {
        // Required empty public constructor
    }
    FragmentRegistrationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Registration");
        if (selectedGender == null){
            binding.textViewSelectedGender.setText("N/A");
        } else {
            binding.textViewSelectedGender.setText(selectedGender);
        }

        binding.buttonSetGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.sendToSetGender();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextName.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter a name !!", Toast.LENGTH_SHORT).show();
                } else if (selectedGender == null){
                    Toast.makeText(getActivity(), "Please select a gender !!", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.goToProfile(new Profile(name, selectedGender));
                }
            }
        });


    }

    public String getGender() {
        return selectedGender;
    }

    public void setSelectedGender(String gender) {
        this.selectedGender = gender;

    }

    RegistrationListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (RegistrationListener) context;
    }

    interface RegistrationListener {

        void sendToSetGender();
        void goToProfile(Profile profile);

    }



}