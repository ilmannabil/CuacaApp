package com.example.ilman.cuaca;


import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilman.cuaca.adapter.CuacaAdapter;
import com.example.ilman.cuaca.model.Cuaca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuacaFragment extends Fragment {

    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvDayDate)
    TextView tvDayDate;
    @BindView(R.id.tvMaxTemp)
    TextView tvMaxTemp;
    @BindView(R.id.tvMinTemp)
    TextView tvMinTemp;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @BindView(R.id.imgCuaca)
    ImageView imgCuaca;

    ArrayList<Cuaca> mCuacaList= new ArrayList<>();
    CuacaAdapter adapterCuaca;

    public CuacaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cuaca, container, false);
        ButterKnife.bind(this,view);

        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycleList);

        adapterCuaca = new CuacaAdapter(mCuacaList);
        recyclerView.setAdapter(adapterCuaca);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);


        String API_URL= "http://api.openweathermap.org/data/2.5/forecast/?lat=-6.1877386&lon=106.7400824&units=metric&APPID=9ed2257682b1d9a2eb66c15047e1bfdd";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(JSONObject response) {
                Log.d("onResponse Volley", String.valueOf(response));
                try {
                    JSONObject cityObject= response.getJSONObject("city");
                    String cityName= cityObject.getString("name");
                    String countryName= cityObject.getString("country");

                    JSONArray arrayList= response.getJSONArray("list");
                    JSONObject firstObject= arrayList.getJSONObject(0);
                    String dt_txt = firstObject.getString("dt_txt");
                    JSONObject mainObject = firstObject.getJSONObject("main");
                    Double tempMax= mainObject.getDouble("temp_max");
                    Double tempMin= mainObject.getDouble("temp_min");

                    JSONArray weatherArray= firstObject.getJSONArray("weather");
                    JSONObject weatherFirstObject = weatherArray.getJSONObject(0);
                    String weather= weatherFirstObject.getString("main");

                    SimpleDateFormat formatDefault= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh.mm");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");

                    String time= dt_txt;
                    String date = dt_txt;

                    try {
                        Date timesformat = formatDefault.parse(time);
                        time= timeFormat.format(timesformat);

                        Date datesformat = formatDefault.parse(date);
                        date= dateFormat.format(datesformat);

                        tvTime.setText(time);
                        tvDayDate.setText(date);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    for (int x=1; x<10;x++){
                        JSONObject objectList= arrayList.getJSONObject(x);
                        JSONObject mainObject2= objectList.getJSONObject("main");
                        Double tempMaxList= mainObject2.getDouble("temp_max");
                        Double tempMinList= mainObject2.getDouble("temp_min");

                        JSONArray cuacaArray= objectList.getJSONArray("weather");
                        JSONObject objectListCuaca= cuacaArray.getJSONObject(0);
                        String statusList= objectListCuaca.getString("main");

                        String waktu= objectList.getString("dt_txt");

                        Cuaca cuacaModel = new Cuaca(statusList,tempMaxList,tempMinList,waktu);
                        mCuacaList.add(cuacaModel);
                        adapterCuaca.notifyDataSetChanged();

                    }


                    tvMaxTemp.setText(String.valueOf(tempMax));
                    tvMinTemp.setText(String.valueOf(tempMin));
                    tvStatus.setText(weather);
                    tvCity.setText(cityName+" "+countryName);

                    if (weather.equals("Rain")){
                        imgCuaca.setImageResource(R.drawable.rain_large);
                    }else if( weather.equals("Clouds")){
                        imgCuaca.setImageResource(R.drawable.coldly_large);
                    }else{
                        imgCuaca.setImageResource(R.drawable.sun_large);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse Volley", String.valueOf(error));
            }
        });

        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

//        String asd= "asd";
//        Log.d("TEST", String.valueOf(asd));
////        tvTime.setText(asd);


        return view;

    }

}
