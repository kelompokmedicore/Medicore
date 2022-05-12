package com.example.mad_utsnomor3.object;

import android.os.Parcel;
import android.os.Parcelable;

public class Obat implements Parcelable{

    private String nama, deskripsi, jenis;
    private int id, qty;

    public Obat(int id, String nama, String deskripsi, String jenis, int qty) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.jenis = jenis;
        this.qty = qty;
    }

    protected Obat(Parcel in) {
        nama = in.readString();
        deskripsi = in.readString();
        jenis = in.readString();
        id = in.readInt();
        qty = in.readInt();
    }

    public static final Creator<Obat> CREATOR = new Creator<Obat>() {
        @Override
        public Obat createFromParcel(Parcel in) {
            return new Obat(in);
        }

        @Override
        public Obat[] newArray(int size) {
            return new Obat[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(deskripsi);
        parcel.writeString(jenis);
        parcel.writeInt(id);
        parcel.writeInt(qty);
    }
}
