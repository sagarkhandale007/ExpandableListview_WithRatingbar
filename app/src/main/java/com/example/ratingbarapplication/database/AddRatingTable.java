package com.example.ratingbarapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.ratingbarapplication.model.model_rating_table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddRatingTable {

    //----------------------------------------------  Create table and insert data -----------------------------------------------------

    private Context mContext;
    public final static String TableName = "Rating_Table";

    public final static String MobileId = "MobileId";
    public final static String MobileName = "MobileName";
    public final static String MobileType = "MobileType";
    public final static String Quantity = "Quantity";
    public final static String Rating = "Rating";
    public final static String Date = "Date";
    public final static String Status = "Status";

    private DataAccess objDA;

    public AddRatingTable(Context context) {
        this.mContext = context;
    }


    public static final String CreateRatingTable= "create table "
            + TableName + " (" + MobileId + " INTEGER PRIMARY KEY NOT NULL, "
            + MobileName + " TEXT NOT NULL,"
            + MobileType + " TEXT NOT NULL,"
            + Quantity + " TEXT NOT NULL,"
            + Rating + " TEXT NOT NULL,"
            + Date + " TEXT NOT NULL,"
            + Status + " TEXT NULL)";


    public  int SaveRating(model_rating_table Model_rating_table) {
        DataAccess objDA = new DataAccess(mContext);
        ContentValues contentValues = new ContentValues();
        contentValues.put(MobileName, Model_rating_table.getMobileName());
        contentValues.put(MobileType, Model_rating_table.getMobileType());
        contentValues.put(Quantity, Model_rating_table.getQuantity());
        contentValues.put(Rating, Model_rating_table.getRating());
        contentValues.put(Date, Model_rating_table.getDate());
        contentValues.put(Status, Model_rating_table.getStatus());
        return objDA.insert(contentValues, TableName);
    }


    public JSONArray GetRatingRecord() {
        JSONObject jsonObject ;
        JSONArray jsonArray=new JSONArray();
        Cursor cursor = null;
        try {
            if (objDA == null) {
                objDA = new DataAccess(mContext);
            }
            String sql = "select * from " + TableName + " where " + Status + "=0";
            // String sql = "select * from " + TableName;
            cursor = objDA.GetRecords(sql);
            if (cursor.moveToFirst()) {
                do {
                    jsonObject = new JSONObject();
                    jsonObject.put(MobileId,cursor.getString( cursor.getColumnIndex("MobileId") ));
                    jsonObject.put(MobileName,cursor.getString( cursor.getColumnIndex("MobileName") ));
                    jsonObject.put(MobileType,cursor.getString( cursor.getColumnIndex("MobileType") ));

                    jsonObject.put(Quantity,cursor.getString( cursor.getColumnIndex("Quantity") ));
                    jsonObject.put(Rating,cursor.getString( cursor.getColumnIndex("Rating") ));
                    jsonObject.put(Date,cursor.getString( cursor.getColumnIndex("Date") ));
                    jsonArray.put(jsonObject);
                } while (cursor.moveToNext());
                return jsonArray;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // return null;

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return null;
    }

}
