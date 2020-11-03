package com.example.ratingbarapplication.adapter;


import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ratingbarapplication.R;
import com.example.ratingbarapplication.database.AddRatingTable;
import com.example.ratingbarapplication.model.itemModel;
import com.example.ratingbarapplication.model.model_rating_table;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.ArrayList;

public class ExpandableListAdapter extends BaseExpandableListAdapter {


    //---------------------------------------------- bind data  -----------------------------------------------------

    Context context;
    String title[];
    HashMap<String, ArrayList<model_rating_table>> list_item;
    //ArrayList<model_rating_table> list_item1;
    public ExpandableListAdapter(Context context, String[] title, HashMap<String, ArrayList<model_rating_table>> List_Item) {
        this.context = context;
        this.title = title;
        this.list_item = List_Item;
    }


    @Override
    public  int getGroupCount() {
        return list_item.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return title[groupPosition].length();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return title[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.title_list, parent, false);
        }


        //----------------------------------------------  control find set Title text with ExpandablelistView -----------------------------------------------------
        TextView text;
        ImageView image;
        text = (TextView) convertView.findViewById(R.id.text);
        image = (ImageView) convertView.findViewById(R.id.image);
        text.setText(title[groupPosition]);

        if(isExpanded) {
            image.setImageResource(R.drawable.arrow_up_black);

        }else {
            image.setImageResource(R.drawable.arrow_down_black);
        }

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list, parent, false);
        }

        //----------------------------------------------  control find set child text and other details with ExpandablelistView -----------------------------------------------------

        TextView name;
        TextView Quantity;
        RatingBar ratingBar;
        TextView Date;
        ImageView image;

        name = (TextView) convertView.findViewById(R.id.Mobile_name);
        Quantity = (TextView) convertView.findViewById(R.id.Quantity);
        ratingBar = (RatingBar) convertView.findViewById(R.id.rating_set);
        Date = (TextView) convertView.findViewById(R.id.Date);
        image = (ImageView) convertView.findViewById(R.id.image_sub_view);


        name.setText(list_item.get(title[groupPosition]).get(childPosition).getMobileName());
        Quantity.setText("Quantity:"+list_item.get(title[groupPosition]).get(childPosition).getQuantity());
        ratingBar.setRating(Float.parseFloat(list_item.get(title[groupPosition]).get(childPosition).getRating()));
        Date.setText(list_item.get(title[groupPosition]).get(childPosition).getDate());
        if (title[groupPosition].equals("Samsung")) {
            image.setImageResource(R.drawable.samsung);
        }else {
            image.setImageResource(R.drawable.apple);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}