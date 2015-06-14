package com.squeezo.android.user.main;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squeezo.android.user.R;
import com.squeezo.android.user.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SqueezoDailyFragment extends Fragment {


    private List<Deals> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_squeezo_daily, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDeals);
        DealsAdapter adapter = new DealsAdapter(getActivity(), getData());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inflate the layout for this fragment
        return view;
    }


    public List<Deals> getData() {

        List<Deals> data = new ArrayList<>();

        String[] description = {"desc1", "desc2", "desc3", "desc4"};
        String[] condition = {"con1", "con2", "con3", "con4"};
        String[] expiry = {"exp1", "exp2", "exp3", "exp4"};

        for (int i = 0; i < expiry.length; i++) {
            Deals current = new Deals();
            current.description = description[i];
            current.condition = condition[i];
            current.expiry = expiry[i];
            data.add(current);
        }

        return data;
    }
}
