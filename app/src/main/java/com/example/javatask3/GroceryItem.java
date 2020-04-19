package com.example.javatask3;

import android.os.Parcel;
import android.os.Parcelable;

public class GroceryItem implements Parcelable {
    private String item;
    private int price;

    public GroceryItem(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public GroceryItem(Parcel source) {
        item = source.readString();
        price = source.readInt();
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(item);
        dest.writeInt(price);
    }

    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel source) {
            return new GroceryItem(source);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };
}
