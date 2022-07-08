package com.example.medicore.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicore.R;
import com.example.medicore.UpdateObatActivity;
import com.example.medicore.database.DBHelper;
import com.example.medicore.object.Obat;

import java.util.ArrayList;

public class ObatAdapter extends BaseAdapter {

    Context context;
    DBHelper db;
    ArrayList<Obat> obatList;

    public ObatAdapter(Context context, ArrayList<Obat> obatList, DBHelper db) {
        this.context = context;
        this.obatList = obatList;
        this.db = db;
    }

    @Override
    public int getCount() {
        return this.obatList.size();
    }

    @Override
    public Object getItem(int position) {
        return obatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_obat, null);
        
        TextView tvNama = (TextView) view.findViewById(R.id.obat_tv_nama);
        TextView tvDeskripsi = (TextView) view.findViewById(R.id.obat_tv_deskripsi);
        TextView tvJenis = (TextView) view.findViewById(R.id.obat_tv_jenis);
        TextView tvQty = (TextView) view.findViewById(R.id.obat_tv_qty);

        Button btnUpdate = (Button)view.findViewById(R.id.post_btn_update);
        Button btnDelete = (Button)view.findViewById(R.id.post_btn_delete);

        Obat obat = obatList.get(position);
        tvNama.setText(obat.getNama());
        tvDeskripsi.setText(obat.getDeskripsi());
        tvJenis.setText(obat.getJenis());
        tvQty.setText(Integer.toString(obat.getQty()));

        btnUpdate.setOnClickListener(view1 -> {
            Intent i = new Intent(context.getApplicationContext(), UpdateObatActivity.class);
            i.putExtra("Obat", obat);
            context.startActivity(i);
        });

        btnDelete.setOnClickListener(view1 -> {
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to delete?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.deleteData(obat.getId());
                            obatList.remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
        });

        return view;
    }
}
