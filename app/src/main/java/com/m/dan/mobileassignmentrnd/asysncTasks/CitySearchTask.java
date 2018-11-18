package com.m.dan.mobileassignmentrnd.asysncTasks;

import android.os.AsyncTask;
import com.m.dan.mobileassignmentrnd.model.City;
import java.util.ArrayList;

public class CitySearchTask extends AsyncTask<Void, Void, ArrayList<City>> {


    public interface TaskListener {
        void onComplete(ArrayList<City> cities);
    }

    private ArrayList<City> cities;
    private String searchTerm;
    private TaskListener taskListener;

    public CitySearchTask(ArrayList<City> cities, String searchTerm, TaskListener taskListener) {

        this.cities = cities;
        this.searchTerm = searchTerm;
        this.taskListener = taskListener;

    }

    @Override
    protected ArrayList<City> doInBackground(Void... voids) {
        return Search();
    }


    public ArrayList<City> Search() {

        ArrayList<City> tmp = new ArrayList<>();

        if(cities.size() <= 0)
            return tmp;

        int low = 0;
        int high = cities.size()-1;
        int startPosition = -1;

        //binary search that finds the first result
        while (low <= high) {

            if (isCancelled())
                break;

            int mid = (low + high) >>> 1;

            String tmpCityName = cities.get(mid).getName().toLowerCase();

            if (compare(tmpCityName, searchTerm) < 0)
                low = mid + 1;
            else if (compare(tmpCityName, searchTerm) > 0)
                high = mid - 1;
            else if (low != mid)
                high = mid;
            else {
                startPosition = mid;
                break;
            }

        }

        if (startPosition == -1)
            return tmp;

        //searching for all other matches
        for (int i = startPosition; i < cities.size(); i++) {
            if (isCancelled())
                break;
            if (cities.get(i) == null)
                break;
            if (cities.get(i).getName().length() < searchTerm.length())
                break;
            else if (!cities.get(i).getName().substring(0, searchTerm.length()).equalsIgnoreCase(searchTerm))
                break;
            else {
                tmp.add(cities.get(i));
            }
        }

        return tmp;
    }


    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);
        taskListener.onComplete(cities);
    }

    private int compare(String cityName, String searchTerm) {

        if (cityName.length() < searchTerm.length()) {
            return cityName.compareTo(searchTerm.substring(0, cityName.length()));
        } else {
            return cityName.substring(0, searchTerm.length()).compareTo(searchTerm);
        }
    }
}
