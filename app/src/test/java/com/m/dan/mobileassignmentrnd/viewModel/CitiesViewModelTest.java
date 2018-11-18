package com.m.dan.mobileassignmentrnd.viewModel;

import com.m.dan.mobileassignmentrnd.asysncTasks.CitySearchTask;
import com.m.dan.mobileassignmentrnd.model.City;
import com.m.dan.mobileassignmentrnd.model.Coordinate;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CitiesViewModelTest  {

    @Test
    public void testSearch() {

        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(0, "US", "aberystwyth", new Coordinate(0,0)));
        cities.add(new City(0, "US", "abu al matamir", new Coordinate(0,0)));
        cities.add(new City(0, "US", "abovyan", new Coordinate(0,0)));
        cities.add(new City(0, "US", "bangor", new Coordinate(0,0)));

        CitySearchTask test = new CitySearchTask(cities, "a", null);
        assertEquals(3,  test.Search().size());
        test = new CitySearchTask(cities, "ab", null);
        assertEquals(3,  test.Search().size());
        test = new CitySearchTask(cities, "b", null);
        assertEquals(1,  test.Search().size());
        test = new CitySearchTask(cities, "abc", null);
        assertEquals(0,  test.Search().size());
        test = new CitySearchTask(cities, "", null);
        assertEquals(4,  test.Search().size());

    }

}