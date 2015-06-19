package com.squeezo.android.user.categoryListing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squeezo.android.user.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<CategorySearch> categoryItems;

    public CategoryAdapter(Activity activity, List<CategorySearch> categoryItems) {
        this.activity = activity;
        this.categoryItems = categoryItems;
    }

    @Override
    public int getCount() {
        return categoryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_category_listing, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewCategorySearch);

        CategorySearch category = categoryItems.get(position);

        //load image with ion
        //title.setText(category.getCategoryImagePath());

        return convertView;
    }
}
