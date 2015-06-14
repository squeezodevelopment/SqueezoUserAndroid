package com.squeezo.android.user.notificationsSettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.squeezo.android.user.R;
import com.squeezo.android.user.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class NotificationSettingsActivity extends AppCompatActivity {

    public static List<NotificationSettings> getData() {

        List<NotificationSettings> data = new ArrayList<>();
        String[] name = {"name1", "name2", "name3", "name4"};
        String[] location = {"loc1", "loc2", "loc3", "loc4"};
        String[] follow = {"true", "false", "true", "false"};
        String[] notification = {"true", "false", "true", "false"};

        for (int i = 0; i < name.length; i++) {
            NotificationSettings current = new NotificationSettings();
            current.name = name[i];
            current.location = location[i];
            current.following = Boolean.parseBoolean(follow[i]);
            current.notification = Boolean.parseBoolean(notification[i]);
            data.add(current);
        }

        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotificationSettings);
        NotificationSettingsAdapter adapter = new NotificationSettingsAdapter(getApplicationContext(), getData());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
