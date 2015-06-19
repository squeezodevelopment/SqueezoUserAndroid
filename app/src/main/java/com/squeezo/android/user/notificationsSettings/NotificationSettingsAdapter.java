package com.squeezo.android.user.notificationsSettings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squeezo.android.user.R;

import java.util.Collections;
import java.util.List;

public class NotificationSettingsAdapter extends RecyclerView.Adapter<NotificationSettingsAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<NotificationSettings> data = Collections.emptyList();

    public NotificationSettingsAdapter(Context context, List<NotificationSettings> data) {
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.row_notification_setting, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        NotificationSettings current = data.get(i);

//        String imagePath = current.pictureAddress;
        holder.name.setText(current.name);

        if (current.following)
            holder.following.setText("unfollow");
        else
            holder.following.setText("follow");

        holder.notification.setChecked(current.notification);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView name;
        Button following;
        Switch notification;

        public MyViewHolder(View itemView) {
            super(itemView);

            picture = (ImageView) itemView.findViewById(R.id.imageViewPictureRow);
            name = (TextView) itemView.findViewById(R.id.textViewNameRow);
            following = (Button) itemView.findViewById(R.id.buttonFollowRow);
            notification = (Switch) itemView.findViewById(R.id.switchRowNotification);

        }
    }

}
