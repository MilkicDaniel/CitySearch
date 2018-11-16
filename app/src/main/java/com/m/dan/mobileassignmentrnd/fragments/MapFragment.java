package com.m.dan.mobileassignmentrnd.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.model.City;


public class MapFragment extends Fragment implements OnMapReadyCallback{


    public static final String BUNDLE_CITY = "BUNDLE_CITY";
    public static final String TAG = "MapFragment";
    private City city;
    private SearchView searchView;

    public MapFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        AppBarLayout appBar = getActivity().findViewById(R.id.appbar);
        appBar.setExpanded(false, false);
        searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setEnabled(false);


        Bundle bundle = getArguments();

        if(bundle != null && bundle.getParcelable(BUNDLE_CITY) != null) {
            city = bundle.getParcelable(BUNDLE_CITY);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment_map_view);
            mapFragment.getMapAsync(this);
        }

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

            LatLng cityLatLng = new LatLng(city.getCoordinate().getLatitude(), city.getCoordinate().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(cityLatLng).title(city.getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cityLatLng));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchView.setEnabled(true);
    }
}
