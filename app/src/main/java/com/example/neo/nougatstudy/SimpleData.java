package com.example.neo.nougatstudy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neo on 2017-07-02.
 */

public class SimpleData implements Parcelable{

    int number;
    String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    public SimpleData(Parcel in) {
        number = in.readInt();
        message = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);
        }

        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(message);
    }
}
