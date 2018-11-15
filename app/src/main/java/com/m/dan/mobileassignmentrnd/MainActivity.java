package com.m.dan.mobileassignmentrnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.m.dan.mobileassignmentrnd.asysncTasks.LoadCitiesTask;
import com.m.dan.mobileassignmentrnd.model.City;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadCitiesTask(this, new LoadCitiesTask.TaskListener() {
            @Override
            public void onComplete(ArrayList<City> cities) {
                Log.d("LOG",cities.get(0).toString());
            }
        }).execute();
        

    }
}
