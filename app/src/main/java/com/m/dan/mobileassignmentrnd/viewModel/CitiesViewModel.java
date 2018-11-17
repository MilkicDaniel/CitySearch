package com.m.dan.mobileassignmentrnd.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.Bundle;
import com.m.dan.mobileassignmentrnd.MainActivity;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.asysncTasks.CitySearchTask;
import com.m.dan.mobileassignmentrnd.asysncTasks.LoadCitiesTask;
import com.m.dan.mobileassignmentrnd.fragments.MapFragment;
import com.m.dan.mobileassignmentrnd.model.City;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CitiesViewModel extends ViewModel {

    private ArrayList<City> citiesList = new ArrayList<>();
    private ArrayList<City> tmpSearchResults = new ArrayList<>();
    private MutableLiveData<ArrayList<City>> searchResults = new MutableLiveData<>();
    private CitySearchTask citySearchTask;
    private String oldSearchResult = "";

    public CitiesViewModel() {
        searchResults.setValue(new ArrayList<City>());
    }

    public void loadCitiesFromJson(Context context){

        new LoadCitiesTask(context, new LoadCitiesTask.TaskListener() {
            @Override
            public void onComplete(ArrayList<City> newCities) {
                Collections.sort(newCities, cityComparator);
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


    public void search(String tmpSearchTerm){

        final String searchTerm = tmpSearchTerm.toLowerCase();

        // stop the current search
        if(citySearchTask != null)
            citySearchTask.cancel(true);

        if(searchTerm.length() < 1) {
            searchResults.getValue().clear();
            searchResults.setValue(searchResults.getValue());
            searchResults.getValue().addAll(citiesList);
            searchResults.setValue(searchResults.getValue());
            return;
        }

        ArrayList<City> tmpList = citiesList;

        //user is being more specific, search the current results only
        if (oldSearchResult.length() > 0 &&
                searchTerm.length() > oldSearchResult.length() &&
                searchTerm.substring(0, oldSearchResult.length()).equalsIgnoreCase(oldSearchResult)) {
            tmpList = tmpSearchResults;
        }

        citySearchTask = new CitySearchTask(tmpList, searchTerm, new CitySearchTask.TaskListener() {
            @Override
            public void onComplete(ArrayList<City> cities) {
                searchResults.getValue().clear();
                searchResults.setValue(searchResults.getValue());
                searchResults.getValue().addAll(cities);
                searchResults.setValue(searchResults.getValue());
                tmpSearchResults = cities;
                oldSearchResult = searchTerm;
            }
        });

        citySearchTask.execute();
    }


    private static Comparator<City> cityComparator = new Comparator<City>() {
        @Override
        public int compare(City cityA, City cityB) {
            return cityA.getName().compareToIgnoreCase(cityB.getName());
        }
    };


}

