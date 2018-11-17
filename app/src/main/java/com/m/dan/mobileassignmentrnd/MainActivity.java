package com.m.dan.mobileassignmentrnd;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.m.dan.mobileassignmentrnd.fragments.CitiesDisplayFragment;
import com.m.dan.mobileassignmentrnd.viewModel.CitiesViewModel;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportFragmentManager().findFragmentByTag(CitiesDisplayFragment.TAG) == null){

            CitiesViewModel model = ViewModelProviders.of((MainActivity) this).get(CitiesViewModel.class);
            model.loadCitiesFromJson(this);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, new CitiesDisplayFragment(), CitiesDisplayFragment.TAG)
                    .commit();

        }

    }
}
