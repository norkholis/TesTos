package com.example.norkholis.testtos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.norkholis.testtos.model.Datum;

import java.util.List;

/**
 * Created by norkholis on 04/02/18.
 */

public class KendaraanAdapter extends RecyclerView.Adapter<KendaraanAdapter.CustomViewHolder>{
    private List<Datum> kendaraans;

    public KendaraanAdapter(List<Datum> kendaraans) {
        this.kendaraans = kendaraans;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kendaraan_list, parent, false);

        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Datum kendaraan = kendaraans.get(position);
        holder.merek_kendaraan.setText(kendaraan.getMerkKendaraan());
        holder.nopol.setText(kendaraan.getNopol());
        holder.tipe_mobil.setText(kendaraan.getJenisKendaraan());
    }

    @Override
    public int getItemCount() {
        return kendaraans.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView merek_kendaraan, nopol, tipe_mobil;

        public CustomViewHolder(View view) {
            super(view);
            merek_kendaraan = (TextView)view.findViewById(R.id.merek_kendaraan);
            nopol = (TextView)view.findViewById(R.id.nopol);
            tipe_mobil = (TextView)view.findViewById(R.id.tipe_mobil);

        }
    }
}
