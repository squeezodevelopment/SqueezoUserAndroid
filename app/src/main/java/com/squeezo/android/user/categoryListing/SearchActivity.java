package com.squeezo.android.user.categoryListing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.squeezo.android.user.R;
import com.squeezo.android.user.outletListing.OutletListingActivity;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ArrayList<CategorySearch> categoryList = new ArrayList<>();
    private ArrayList<String> categoryIDs = new ArrayList<>();
    private CategoryAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listView = (ListView) findViewById(R.id.listViewCategorySearch);
        adapter = new CategoryAdapter(this, categoryList); //change to 20 for 20 items
        listView.setAdapter(adapter);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        final EditText txtLocation = (EditText) findViewById(R.id.editTextLocationSearch);
        final EditText txtSearch = (EditText) findViewById(R.id.editTextSearchSearch);
        Button btnSearch = (Button) findViewById(R.id.buttonSearchSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*try {

                    if(!(txtLocation.getText().toString().length() == 0) && !(txtSearch.getText().toString().length() == 0))
                        Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Enter all details", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                //start dialog
                //get data
                //stop dialog
                fetchData();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //position starts from 0
//                final String categoryId = categoryIDs.get(position);

                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SearchActivity.this, OutletListingActivity.class);
                startActivity(intent);

                /*NetworkDetection nd = new NetworkDetection(new AsyncResponse() {
                    @Override
                    public void processFinish(Boolean output) {
                        if(output) {

                            //put category id and proceed to listing through intent

                        } else {
                            Toast.makeText(getApplication(), "Internet Not Connected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                nd.execute();*/

            }
        });

    }

    private void fetchData() {

        for (int i = 0; i < 10; i++)
            categoryList.add(new CategorySearch());

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
