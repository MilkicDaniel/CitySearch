package com.m.dan.mobileassignmentrnd.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.m.dan.mobileassignmentrnd.MainActivity;
import com.m.dan.mobileassignmentrnd.R;
import com.m.dan.mobileassignmentrnd.model.City;
import com.m.dan.mobileassignmentrnd.viewModel.CitiesViewModel;
import java.util.ArrayList;

public class CitiesRecyclerAdapter extends RecyclerView.Adapter<CitiesRecyclerAdapter.ViewHolder> {

    private ArrayList<City> cities;
    private Context context;
    private CitiesViewModel model;

    public CitiesRecyclerAdapter(Context context, ArrayList<City> cities) {
        this.cities = cities;
        this.context = context;
        model = ViewModelProviders.of(((MainActivity) context)).get(CitiesViewModel.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final City city = cities.get(position);

        viewHolder.title.setText(context.getString(R.string.city_title, city.getName(), city.getCountry()));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.openMapForCity(context, city);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CardView cardView;

        private ViewHolder(View rootView) {
            super(rootView);

            title = rootView.findViewById(R.id.city_item_title);
            cardView = rootView.findViewById(R.id.city_card_view);

        }
    }


}
