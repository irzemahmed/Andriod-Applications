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

import com.example.assessment3.databinding.FragmentSetGenderBinding;


public class SetGenderFragment extends Fragment {

    public SetGenderFragment() {
        // Required empty public constructor
    }

    FragmentSetGenderBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSetGenderBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Set Gender");




        binding.buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedGender = "Female";
                if (binding.radioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale){
                    selectedGender = "Male";
                }
                mListener.sendSelectedGender(selectedGender);

            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cencelSetGender();
            }
        });

    }

    SetGenderListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SetGenderListener) context;
    }

    interface SetGenderListener {
        void sendSelectedGender(String gender);
        void cencelSetGender();
    }
}