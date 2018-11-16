package com.m.dan.mobileassignmentrnd.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import com.m.dan.mobileassignmentrnd.MainActivity;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.asysncTasks.LoadCitiesTask;
import com.m.dan.mobileassignmentrnd.fragments.MapFragment;
import com.m.dan.mobileassignmentrnd.model.City;
import java.util.ArrayList;

public class CitiesViewModel extends ViewModel {

    private ArrayList<City> citiesList = new ArrayList<>();
    private MutableLiveData<ArrayList<City>> searchResults = new MutableLiveData<>();


    public CitiesViewModel() {
        searchResults.setValue(new ArrayList<City>());
    }

    public void loadCitiesFromJson(Context context){

        new LoadCitiesTask(context, new LoadCitiesTask.TaskListener() {
            @Override
            public void onComplete(ArrayList<City> newCities) {
                citiesList = newCities;
                searchResults.getValue().addAll(newCities);
                searchResults.setValue(searchResults.getValue());
            }
        }).execute();
    }


    public LiveData<ArrayList<City>> getSearchResults(){
        return searchResults;
    }


    public void openMapForCity(Context context, City city) {

        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MapFragment.BUNDLE_CITY, city);
        mapFragment.setArguments(bundle);

        ((MainActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, mapFragment, MapFragment.TAG)
                .addToBackStack(null)
                .commit();

    }

}
