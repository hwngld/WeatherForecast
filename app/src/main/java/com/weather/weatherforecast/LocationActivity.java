package com.weather.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.weather.weatherforecast.adapter.AdapterLocation;
import com.weather.weatherforecast.datalocal.DataManager;
import com.weather.weatherforecast.model.ItemLocation;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {
    private RecyclerView LocationList;
    private AdapterLocation adapterLocation;
    private List<ItemLocation> listAllLocation;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        listAllLocation = new ArrayList<>();
        listAllLocation = DataManager.getListAllLocation();

        adapterLocation = new AdapterLocation(this, new AdapterLocation.IAdapter() {
            @Override
            public void clickChanged(ItemLocation itemLocation) {
                if(!itemLocation.isSelected()){
                    MainActivity.locationList.add(itemLocation);
                    for(int i = 0; i < MainActivity.locationList.size(); i++){
                        MainActivity.locationList.get(i).setSelected(false);
                        if(MainActivity.locationList.get(i).getId().equals(itemLocation.getId())){
                            MainActivity.locationList.get(i).setSelected(true);
                        }
                    }
                    itemLocation.setSelected(true);
                    DataManager.setListLocation(MainActivity.locationList);
                    DataManager.setListAllLocation(listAllLocation);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }

            }
        });
        LocationList = findViewById(R.id.listLocation);
        LocationList.setLayoutManager(new LinearLayoutManager(this));
        LocationList.setAdapter(adapterLocation);
        adapterLocation.setData(listAllLocation);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = findViewById(R.id.searchLocation);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapterLocation.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterLocation.getFilter().filter(newText);
                return false;
            }
        });

    }
}