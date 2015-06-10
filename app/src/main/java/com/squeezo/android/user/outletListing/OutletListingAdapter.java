package com.squeezo.android.user.outletListing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squeezo.android.user.R;

import java.util.Collections;
import java.util.List;

public class OutletListingAdapter extends RecyclerView.Adapter<OutletListingAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Outlet> data = Collections.emptyList();
    private Context context;

    public OutletListingAdapter(Context context, List<Outlet> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.outlet_listing_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Outlet current = data.get(position);

        holder.name.setText(current.name);
        holder.location.setText(current.location);
        //add for imageViews
        String[] offers = current.offers;

        for (String offer : offers) {
            final TextView rowTextView = new TextView(context);
            rowTextView.setText(offer);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rowTextView.setLayoutParams(layoutParams);
            rowTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            holder.linearLayout.addView(rowTextView);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button name;
        Button location;
        ImageView pic1;
        ImageView pic2;
        ImageView pic3;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (Button) itemView.findViewById(R.id.buttonNameOutletListing);
            location = (Button) itemView.findViewById(R.id.buttonLocationOutletListing);
            pic1 = (ImageView) itemView.findViewById(R.id.imageViewOneOutletListing);
            pic2 = (ImageView) itemView.findViewById(R.id.imageViewTwoOutletListing);
            pic3 = (ImageView) itemView.findViewById(R.id.imageViewThreeOutletListing);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutOffersOutletListing);

        }
    }

}
