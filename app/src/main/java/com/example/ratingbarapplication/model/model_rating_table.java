package com.example.ratingbarapplication.model;

public class model_rating_table {

    private int MobileId;
    private String MobileName;
    private String MobileType;
    private String Quantity;
    private String Rating;
    private String Date;
    private String Status;

    public  model_rating_table(){

    }

    public model_rating_table(String mobileName, String mobileType, String quantity, String rating, String date)
    {

        MobileName=mobileName;
        MobileType=mobileType;
        Quantity=quantity;
        Rating=rating;
        Date=date;
    }

    public model_rating_table(int mobileId, String mobileName, String mobileType, String quantity, String rating, String date, String status)
    {
        MobileId=mobileId;
        MobileName=mobileName;
        MobileType=mobileType;
        Quantity=quantity;
        Rating=rating;
        Date=date;
        Status=status;
    }

    public String getMobileType() {
        return MobileType;
    }

    public void setMobileType(String mobileType) {
        MobileType = mobileType;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getMobileId() {
        return MobileId;
    }

    public void setMobileId(int mobileId) {
        MobileId = mobileId;
    }

    public String getMobileName() {
        return MobileName;
    }

    public void setMobileName(String mobileName) {
        MobileName = mobileName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
