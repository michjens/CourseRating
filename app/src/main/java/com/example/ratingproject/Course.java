package com.example.ratingproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable{
    private String courseName;
    private String teacherEmail;

    public Course(String courseName, String teacherEmail){
        this.courseName = courseName;
        this.teacherEmail = teacherEmail;
    }

    public String getCourseName() {
        return courseName;
    }



    public String getTeacherEmail() {
        return teacherEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(teacherEmail);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Course createFromParcel(Parcel in){
            return new Course(in);
        }
        public Course[] newArray(int size){
            return new Course[size];
        }
    };

    public Course(Parcel in){
        this.courseName = in.readString();
        this.teacherEmail = in.readString();

    }
}
