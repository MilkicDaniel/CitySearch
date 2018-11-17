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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.m.dan.mobileassignmentrnd.MainActivity;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.model.City;
import com.m.dan.mobileassignmentrnd.viewModel.CitiesViewModel;
import com.m.dan.mobileassignmentrnd.adapters.CitiesRecyclerAdapter;
import java.util.ArrayList;

public class CitiesDisplayFragment extends Fragment {

    public final static String TAG = "CitiesDisplayFragment";
    private CitiesRecyclerAdapter adapter;
    private EditText searchView;
    private FrameLayout loadingContainer;
    private TextView resultsText;
    private AppBarLayout appBar;
    private CitiesViewModel model;

    public CitiesDisplayFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        model = ViewModelProviders.of(((MainActivity) getContext())).get(CitiesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_cities_display, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.cities_display_recycler_view);
        loadingContainer = root.findViewById(R.id.cities_display_loading_container);
        resultsText = root.findViewById(R.id.cities_display_results_text);

        searchView = getActivity().findViewById(R.id.toolbar_search);
        appBar = getActivity().findViewById(R.id.appbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CitiesRecyclerAdapter(getContext(), model.getSearchResults().getValue());
        recyclerView.setAdapter(adapter);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                model.search(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        model.getSearchResults().observe(this, new Observer<ArrayList<City>>() {
            @Override
            public void onChanged(@Nullable ArrayList<City> cities) {
                adapter.notifyDataSetChanged();

                if(cities.size() == 0) {

                    if(loadingContainer.getVisibility() == View.GONE)
                        resultsText.setVisibility(View.VISIBLE);

                }
                else {

                    if(loadingContainer.getVisibility() == View.VISIBLE) {
                        loadingContainer.setVisibility(View.GONE);
                        searchView.setEnabled(true);
                    }

                    resultsText.setVisibility(View.GONE);

                }

            }
        });

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        searchView.clearFocus();
    }

    @Override
    public void onResume() {
        super.onResume();
        appBar.setExpanded(true, false);
    }
}
