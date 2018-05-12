package com.reka.rumahsakit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reka.rumahsakit.R;
import com.reka.rumahsakit.model.Dokter;
import com.reka.rumahsakit.model.Pasien;

import java.util.List;

/**
 * Created by reka on 3/19/18.
 */

public class AdapterDokter extends RecyclerView.Adapter<AdapterDokter.ViewHolder> {

    private Context context;
    private List<Dokter> dokters;

    public AdapterDokter(Context context, List<Dokter> dokters) {
        this.context = context;
        this.dokters = dokters;
    }

    @Override
    public AdapterDokter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterDokter.ViewHolder holder, int position) {
    Dokter dokter = dokters.get(position);
    holder.nama.setText(dokter.getNama());
    holder.jeniskelamin.setText(dokter.getJeniskelamin());
    holder.jadwaldinas.setText(dokter.getJadwaldinas());
    holder.spesialis.setText(dokter.getSpesialis());


    }

    @Override
    public int getItemCount() {
        return dokters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,jeniskelamin,jadwaldinas,spesialis;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tx_nama);
            jeniskelamin = (TextView) itemView.findViewById(R.id.tx_jl);
            jadwaldinas = (TextView) itemView.findViewById(R.id.tx_jd);
            spesialis = (TextView) itemView.findViewById(R.id.tx_sp);



        }
    }
}
