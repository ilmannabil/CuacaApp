package com.example.ilman.cuaca.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ilman.cuaca.R;
import com.example.ilman.cuaca.holder.CuacaHolder;
import com.example.ilman.cuaca.model.Cuaca;

import java.util.ArrayList;

/**
 * Created by ilman on 10/02/2018.
 */

public class CuacaAdapter extends RecyclerView.Adapter<CuacaHolder>{

    private ArrayList<Cuaca> mCuacaList;

    Context context;

    public CuacaAdapter(ArrayList<Cuaca> mCuacaList,Context context) {
        this.mCuacaList = mCuacaList;
        this.context= context;
    }

    @Override
    public CuacaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather,parent,false);
        return new CuacaHolder(card);
    }

    @Override
    public void onBindViewHolder(CuacaHolder holder, int position) {
        final Cuaca cuacalist= mCuacaList.get(position);
        holder.updateUI(cuacalist);

        holder.itemView.findViewById(R.id.cvWeather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = cuacalist.getTime();
                Log.d("TESTT",time);
            }
        });

//        holder.itemView.findViewById(R.id.cvWeather).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String status = cuacalist.getStatus();
//                Toast.makeText(context,"status = "+status,Toast.LENGTH_SHORT);
//                Log.d("TEST",status);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mCuacaList.size();
    }
}
