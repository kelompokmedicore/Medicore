package com.example.medicore.object;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    private String nama, email, password, gender;
    private int id, umur;

    public User(int id, String nama, String email, String password, String gender, int umur) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.id = id;
        this.umur = umur;
    }

    protected User(Parcel in) {
        nama = in.readString();
        email = in.readString();
        password = in.readString();
        gender = in.readString();
        id = in.readInt();
        umur = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(gender);
        parcel.writeInt(id);
        parcel.writeInt(umur);
    }
}
