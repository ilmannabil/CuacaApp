package com.example.ilman.cuaca;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.tvStatusDetail)
    TextView tvStatusDetail;

    @BindView(R.id.tvTimeDetail) TextView tvTimeDetail;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);

        String time= getArguments().getString("time");
        String status= getArguments().getString("status");

        tvStatusDetail.setText(status);
        tvTimeDetail.setText(time);

        return view;

    }

}
