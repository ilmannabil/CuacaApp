package com.example.ilman.cuaca.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilman.cuaca.R;
import com.example.ilman.cuaca.holder.CuacaHolder;
import com.example.ilman.cuaca.model.Cuaca;

import java.util.ArrayList;

/**
 * Created by ilman on 10/02/2018.
 */

public class CuacaAdapter extends RecyclerView.Adapter<CuacaHolder>{

    private ArrayList<Cuaca> mCuacaList;

    public CuacaAdapter(ArrayList<Cuaca> mCuacaList) {
        this.mCuacaList = mCuacaList;
    }

    @Override
    public CuacaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather,parent,false);
        return new CuacaHolder(card);
    }

    @Override
    public void onBindViewHolder(CuacaHolder holder, int position) {
        Cuaca cuacalist= mCuacaList.get(position);
        holder.updateUI(cuacalist);
    }

    @Override
    public int getItemCount() {
        return mCuacaList.size();
    }
}
