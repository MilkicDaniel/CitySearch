package com.m.dan.mobileassignmentrnd.asysncTasks;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m.dan.mobileassignmentrnd.model.City;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoadCitiesTask extends AsyncTask<Void, Void, ArrayList<City>> {

    public interface TaskListener {
        void onComplete(ArrayList<City> cities);
    }

    private Reader reader;
    private TaskListener taskListener;

    public LoadCitiesTask(Context context, TaskListener taskListener){

        this.taskListener = taskListener;

        try {
            reader = new InputStreamReader(context.getAssets().open("cities.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ArrayList<City> doInBackground(Void... voids) {
        return new Gson().fromJson(reader, new TypeToken<ArrayList<City>>() {}.getType());
    }

    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);
        taskListener.onComplete(cities);
    }
}
