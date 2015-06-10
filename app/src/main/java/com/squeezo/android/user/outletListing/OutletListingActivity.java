package com.squeezo.android.user.outletListing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squeezo.android.user.R;

import java.util.ArrayList;
import java.util.List;


public class OutletListingActivity extends AppCompatActivity {

    public static List<Outlet> getData() {

        List<Outlet> data = new ArrayList<>();

        String offers[] = {"buy 1 get 1", "20% off", "chicken wings free", "pint free"};
        String names[] = {"R&J's Lounge", "Toons", "Sky Garage", "HRC"};
        String locations[] = {"Bibwewadi", "Camp", "Aundh", "KP"};

        for (int i = 0; i < offers.length; i++) {
            Outlet current = new Outlet();
            current.offers = offers;
            current.name = names[i];
            current.location = locations[i];
            data.add(current);
        }

        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet_listing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewOutletListing);
        OutletListingAdapter adapter = new OutletListingAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

}
