package com.example.tejasyevalkar.listview.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tejas yevalkar on 16/12/2017.
 */

public class FruitPOJO implements Parcelable{

    private String id;
    private String mFName;
    private int mFPkgDate;
    private int mFExpDate;
    private int mFPrice;


    public FruitPOJO(String mFName, int mFPkgDate, int mFExpDate, int mFPrice) {
        this.mFName = mFName;
        this.mFPkgDate = mFPkgDate;
        this.mFExpDate = mFExpDate;
        this.mFPrice = mFPrice;
    }
   public FruitPOJO(String id, String mFName, int mFPkgDate, int mFExpDate, int mFPrice) {
        this.id = id;
        this.mFName = mFName;
        this.mFPkgDate = mFPkgDate;
        this.mFExpDate = mFExpDate;
        this.mFPrice = mFPrice;
    }

    private FruitPOJO(Parcel in) {
        id = in.readString();
        mFName = in.readString();
        mFPkgDate = in.readInt();
        mFExpDate = in.readInt();
        mFPrice = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( id );
        dest.writeString( mFName );
        dest.writeInt( mFPkgDate );
        dest.writeInt( mFExpDate );
        dest.writeInt( mFPrice );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FruitPOJO> CREATOR = new Creator<FruitPOJO>() {
        @Override
        public FruitPOJO createFromParcel(Parcel in) {
            return new FruitPOJO( in );
        }

        @Override
        public FruitPOJO[] newArray(int size) {
            return new FruitPOJO[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getmFName() {
        return mFName;
    }

    public int getmFPkgDate() {
        return mFPkgDate;
    }

    public int getmFExpDate() {
        return mFExpDate;
    }

    public int getmFPrice() {
        return mFPrice;
    }



}
