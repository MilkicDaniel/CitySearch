package com.m.dan.mobileassignmentrnd.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.m.dan.mobileassignmentrnd.MainActivity;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.model.City;
import com.m.dan.mobileassignmentrnd.viewModel.CitiesViewModel;
import com.m.dan.mobileassignmentrnd.adapters.CitiesRecyclerAdapter;
import java.util.ArrayList;

public class CitiesDisplayFragment extends Fragment {

    public final static String TAG = "CitiesDisplayFragment";
    private CitiesRecyclerAdapter adapter;
    private SearchView searchView;
    private AppBarLayout appBar;

    public CitiesDisplayFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CitiesViewModel model = ViewModelProviders.of(((MainActivity) getContext())).get(CitiesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_cities_display, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.cities_display_recycler_view);
        searchView = getActivity().findViewById(R.id.toolbar_search);
        appBar = getActivity().findViewById(R.id.appbar);


        adapter = new CitiesRecyclerAdapter(getContext(), model.getSearchResults().getValue());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        model.getSearchResults().observe(this, new Observer<ArrayList<City>>() {
            @Override
            public void onChanged(@Nullable ArrayList<City> cities) {
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        searchView.clearFocus();
        searchView.setIconified(true);
        searchView.onActionViewCollapsed();
    }

    @Override
    public void onResume() {
        super.onResume();
        searchView.setIconified(false);
        searchView.clearFocus();
        appBar.setExpanded(true, false);
    }
}
