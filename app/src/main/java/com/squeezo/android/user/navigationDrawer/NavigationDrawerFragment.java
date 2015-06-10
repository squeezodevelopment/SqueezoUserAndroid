package com.squeezo.android.user.navigationDrawer;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squeezo.android.user.R;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle drawerToggle;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static List<DrawerData> getData() {

        List<DrawerData> data = new ArrayList<>();

        int id = R.mipmap.ic_launcher;
        String titles[] = {"Offers Feed", "Notifications", "Nearby", "Item 4"};

        for (String title : titles) {
            DrawerData current = new DrawerData();
            current.iconId = id;
            current.title = title;
            data.add(current);
        }

        return data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerViewDrawerList);
        DrawerAdapter adapter = new DrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

}
