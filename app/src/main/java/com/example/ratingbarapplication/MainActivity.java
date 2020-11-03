package com.example.ratingbarapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.ratingbarapplication.adapter.ExpandableListAdapter;
import com.example.ratingbarapplication.database.AddRatingTable;
import com.example.ratingbarapplication.model.itemModel;
import com.example.ratingbarapplication.model.model_rating_table;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    String title[] = {"Apple", "Samsung"};
    ArrayList<model_rating_table> arrayList, arrayList1;
    HashMap<String, ArrayList<model_rating_table>> hashMap;
   int count=0;
    @SuppressLint({"NewApi", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.arrow_down_menu);
        toolbar.setOverflowIcon(drawable);

        //----------------------------------------------  control find ExpandablelistView and list-----------------------------------------------------

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();


        //----------------------------------------------  Check exiting data avilable or not in Sqlite database-----------------------------------------------------

        AddRatingTable addRatingTable = new AddRatingTable(getApplicationContext());
        JSONArray Rating_jsonArray = addRatingTable.GetRatingRecord();
       if(Rating_jsonArray==null){
           saveDataInSqlite();
       }else {
           setDataToListView();
       }
        //----------------------------------------------   bind data to adpter-----------------------------------------------------

         expandableListAdapter = new ExpandableListAdapter(getApplicationContext(), title,hashMap);
        expandableListView.setAdapter(expandableListAdapter);

        //child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), hashMap.get(title[groupPosition]).get(childPosition).getMobileName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
    //----------------------------------------------  set the data to bind adpter-----------------------------------------------------

    private void setDataToListView() {

        AddRatingTable addRatingTable = new AddRatingTable(getApplicationContext());
       JSONArray rating_jsonArray = addRatingTable.GetRatingRecord();
        for (int b = 0; b < rating_jsonArray.length(); b++) {
            JSONObject Order_Obj = null;
            try {
                Order_Obj = rating_jsonArray.getJSONObject(b);
                String mobileName = Order_Obj.getString("MobileName");
                String mobileType = Order_Obj.getString("MobileType");
                String quantity = Order_Obj.getString("Quantity");
                String rating = Order_Obj.getString("Rating");
                String date = Order_Obj.getString("Date");
                if(mobileType.equals("Apple")){
                    model_rating_table item = new model_rating_table(mobileName, mobileType, quantity, rating, date);
                    arrayList.add(item);
                    hashMap.put(title[0], arrayList);
                }else {
                    model_rating_table item = new model_rating_table(mobileName, mobileType, quantity, rating, date);
                    arrayList1.add(item);
                    //title and child data
                    hashMap.put(title[1], arrayList1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //----------------------------------------------  Insert data in Sqlite database one time-----------------------------------------------------

    private void saveDataInSqlite() {

        String ratingDetails= " [" +
                "{\"MobileName\":\"iphone 6\",\"Quantity\":\"15.0\",\"Rating\":\"3.5\",\"date\":\"2017-12-05\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"Iphone 6 s\",\"Quantity\":\"20.0\",\"Rating\":\"4.5\",\"date\":\"2016-01-02\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"iphone 7\",\"Quantity\":\"50.0\",\"Rating\":\"4.5\",\"date\":\"2018-05-22\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"Iphone 8\",\"Quantity\":\"105.0\",\"Rating\":\"4\",\"date\":\"2019-08-15\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"Iphone X\",\"Quantity\":\"55.0\",\"Rating\":\"3.5\",\"date\":\"2018-12-02\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"Iphone XR\",\"Quantity\":\"25.0\",\"Rating\":\"2.5\",\"date\":\"2015-11-05\",\"MobileType\":\"Apple\"},\n" +
                "{\"MobileName\":\"S7\",\"Quantity\":\"40.0\",\"Rating\":\"1.5\",\"date\":\"2017-06-07\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"S8\",\"Quantity\":\"30.0\",\"Rating\":\"2\",\"date\":\"2020-03-05\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"S9\",\"Quantity\":\"10.0\",\"Rating\":\"3.5\",\"date\":\"2016-01-08\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"S10\",\"Quantity\":\"15.0\",\"Rating\":\"3\",\"date\":\"2017-10-25\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"Note 7\",\"Quantity\":\"80.0\",\"Rating\":\"3.5\",\"date\":\"2019-07-14\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"Note 8\",\"Quantity\":\"100.0\",\"Rating\":\"5\",\"date\":\"2017-03-18\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"Note 9\",\"Quantity\":\"75.0\",\"Rating\":\"4.5\",\"date\":\"2015-07-04\",\"MobileType\":\"Samsung\"},\n" +
                "{\"MobileName\":\"Note 10\",\"Quantity\":\"37.0\",\"Rating\":\"2.5\",\"date\":\"2018-08-17\",\"MobileType\":\"Samsung\"}]";


        try {

            JSONArray jsonArray = new JSONArray(ratingDetails);

            for (int b = 0; b < jsonArray.length(); b++) {
                JSONObject Order_Obj = jsonArray.getJSONObject(b);
                String MobileName = Order_Obj.getString("MobileName");
                String MobileType = Order_Obj.getString("MobileType");
                String Quantity = Order_Obj.getString("Quantity");
                String Rating = Order_Obj.getString("Rating");
                String date = Order_Obj.getString("date");

                AddRatingTable addRatingTable = new AddRatingTable(getApplicationContext());
                model_rating_table Model_rating_table = new model_rating_table();
                Model_rating_table.setMobileName(MobileName);
                Model_rating_table.setMobileType(MobileType);
                Model_rating_table.setQuantity(Quantity);
                Model_rating_table.setRating(Rating);
                Model_rating_table.setDate(date);
                Model_rating_table.setStatus("0");
                int Result = addRatingTable.SaveRating(Model_rating_table);
                if (Result != -1) {
                    Log.d("Success", "data Save successfully");
                    count++;
                    if(count==12){
                        setDataToListView();
                    }
                } else {
                    Log.d("Failure", "Error while save Data");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SortBy) {
            return true;
        }
        //----------------------------------------------  sort the data in listview (date,wise)-----------------------------------------------------

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Date) {
            Collections.sort(arrayList, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {
                    return lhs.getDate().compareTo(rhs.getDate());
                }
            });

            Collections.sort(arrayList1, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {
                    return lhs.getDate().compareTo(rhs.getDate());
                }
            });

            //ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getApplicationContext(), title,hashMap);
           // expandableListView.setAdapter(expandableListAdapter);
            expandableListAdapter.notifyDataSetChanged();

            return true;
        }
        //----------------------------------------------  sort the data in listview (Quantity,wise)-----------------------------------------------------
        if (id == R.id.action_Quantity) {
            Collections.sort(arrayList, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {
                    // return lhs.getRating().compareTo(rhs.getRating());
                    double qty2= Double.parseDouble(lhs.getQuantity());
                    double qty1= Double.parseDouble(rhs.getQuantity());
                    return Double.compare(qty1,qty2);
                }
            });
            Collections.sort(arrayList1, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {
                   // return lhs.getRating().compareTo(rhs.getRating());
                    double qty2= Double.parseDouble(lhs.getQuantity());
                  double qty1= Double.parseDouble(rhs.getQuantity());
                   return Double.compare(qty1,qty2);
                }
            });

            //ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getApplicationContext(), title,hashMap);
          //  expandableListView.setAdapter(expandableListAdapter);
            expandableListAdapter.notifyDataSetChanged();
            return true;
        }
        //----------------------------------------------  sort the data in listview (Rating,wise)-----------------------------------------------------
        if (id == R.id.action_Populate) {
            Collections.sort(arrayList, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {

                    double rt2= Double.parseDouble(lhs.getRating());
                    double rt1= Double.parseDouble(rhs.getRating());
                    return Double.compare(rt1,rt2);
                }
            });
            Collections.sort(arrayList1, new Comparator<model_rating_table>() {
                @Override
                public int compare(model_rating_table lhs, model_rating_table rhs) {

                    double rt2= Double.parseDouble(lhs.getRating());
                    double rt1= Double.parseDouble(rhs.getRating());
                    return Double.compare(rt1,rt2);
                }
            });
            //ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(getApplicationContext(), title,hashMap);
           // expandableListView.setAdapter(expandableListAdapter);
            expandableListAdapter.notifyDataSetChanged();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}