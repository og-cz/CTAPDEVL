package com.example.itemtwo;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    public String name, price, description;
    public int imageResId;

    public Product(String name, String price, String description, int imageResId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readString();
        description = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) { return new Product(in); }
        @Override
        public Product[] newArray(int size) { return new Product[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(description);
        parcel.writeInt(imageResId);
    }
}
