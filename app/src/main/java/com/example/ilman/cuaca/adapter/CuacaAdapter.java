package com.example.ilman.cuaca.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ilman.cuaca.DetailFragment;
import com.example.ilman.cuaca.MainActivity;
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
                String status = cuacalist.getStatus();

                FragmentManager fm= ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fm.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("time",time);
                bundle.putString("status",status);
                DetailFragment fragment= new DetailFragment();
                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.framelayout_mainactivity,fragment).addToBackStack(null);
                fragmentTransaction.commit();
//                //For Pass Parameter to other fragment
////                Bundle bundle = new Bundle();
////                bundle.putString("idOrder", String.valueOf(id));
//                DetailFragment fragment = new DetailFragment();
////                fragment.setArguments(bundle);
//
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCuacaList.size();
    }
}
