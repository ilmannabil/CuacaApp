package com.example.ilman.cuaca.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilman.cuaca.R;
import com.example.ilman.cuaca.model.Cuaca;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ilman on 10/02/2018.
 */

public class CuacaHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.maxTempList)
    TextView maxTempList;

    @BindView(R.id.minTempList)
    TextView minTempList;

    @BindView(R.id.statusList)
    TextView statusList;

    @BindView(R.id.timeList)
    TextView timeList;

    @BindView(R.id.iconMiniList)
    ImageView iconMiniList;


    public CuacaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void updateUI(Cuaca cuaca){
        maxTempList.setText(String.valueOf(cuaca.getTempMax()));
        minTempList.setText(String.valueOf(cuaca.getTempMin()));
        statusList.setText(cuaca.getStatus());
        timeList.setText(cuaca.getTime());

        if (cuaca.getStatus().equals("Rain")){
            iconMiniList.setImageResource(R.drawable.rain_mini);
        }else if(cuaca.getStatus().equals("Clouds")){
            iconMiniList.setImageResource(R.drawable.cold_mini);
        }else{
            iconMiniList.setImageResource(R.drawable.sun_mini);
        }

    }
}
