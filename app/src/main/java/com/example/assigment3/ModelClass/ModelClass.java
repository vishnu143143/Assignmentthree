package com.example.assigment3.ModelClass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class ModelClass implements Parcelable {


    private String mname;
    private int mclass;
    private int mroll;


    protected ModelClass(Parcel in) {
        mname = in.readString();
        mclass = in.readInt();
        mroll = in.readInt();
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    public String getMname() {
        return mname;
    }

    public int getMclass() {
        return mclass;
    }

    public int getMroll() {
        return mroll;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setMclass(int mclass) {
        this.mclass = mclass;
    }

    public void setMroll(int mroll) {
        this.mroll = mroll;
    }

    public ModelClass(String mname, int mclass, int mroll) {
        this.mname = mname;
        this.mclass = mclass;
        this.mroll = mroll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mname);
        parcel.writeInt(mclass);
        parcel.writeInt(mroll);
    }

    public static Comparator<ModelClass> StuNameComparator = new Comparator<ModelClass>() {

        public int compare(ModelClass m1, ModelClass m2) {
            String Stu1 = m1.getMname();
            String Stu2 = m2.getMname();

            //ascending order
            return Stu1.compareTo(Stu2);

        }
    };

    public static Comparator<ModelClass> StuRollno = new Comparator<ModelClass>() {

        public int compare(ModelClass m1, ModelClass m2) {

            int rollno1 = m1.getMroll();
            int rollno2 = m2.getMroll();

            /*For ascending order*/
            return rollno1 - rollno2;

        }};}
