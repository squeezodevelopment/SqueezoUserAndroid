package com.squeezo.android.user.navigationDrawer;

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

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DrawerData> data = Collections.emptyList();
    private Context context;

    public DrawerAdapter(Context context, List<DrawerData> data) {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(layoutInflater.inflate(R.layout.row_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DrawerData current = data.get(position);

        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.imageViewDrawerItem);
            title = (TextView) itemView.findViewById(R.id.textViewDrawerItem);

        }
    }

}
