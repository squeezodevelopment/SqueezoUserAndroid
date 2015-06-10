package com.squeezo.android.user.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squeezo.android.user.R;

import java.util.Collections;
import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Deals> data = Collections.emptyList();

    public DealsAdapter(Context context, List<Deals> data) {
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.deals_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Deals current = data.get(position);

        holder.description.setText(current.description);
        holder.condition.setText(current.condition);
        holder.expiry.setText(current.expiry);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView description, condition, expiry;

        public MyViewHolder(View itemView) {
            super(itemView);

            picture = (ImageView) itemView.findViewById(R.id.imageViewDeals);
            description = (TextView) itemView.findViewById(R.id.textViewDescriptionDeals);
            condition = (TextView) itemView.findViewById(R.id.textViewConditionDeals);
            expiry = (TextView) itemView.findViewById(R.id.textViewExpiryDeals);

        }
    }
}
