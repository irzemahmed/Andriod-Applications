package com.example.assessment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.WelcomeListener, RegistrationFragment.RegistrationListener, SetGenderFragment.SetGenderListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new WelcomeFragment())
                .commit();
    }

    @Override
    public void goToRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegistrationFragment(), "reg-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendToSetGender() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SetGenderFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSelectedGender(String gender) {
        RegistrationFragment registrationFragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("reg-fragment");
        if (registrationFragment != null){
            registrationFragment.setSelectedGender(gender);
        }

        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cencelSetGender() {
        getSupportFragmentManager().popBackStack();
    }
}