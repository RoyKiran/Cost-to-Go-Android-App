package com.bakmaps.cost_to_go.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bakmaps.cost_to_go.R;
/**
 * Created by WORK with FUN on 23-12-2015.
 */
public class M_Indicator extends Fragment {

    public M_Indicator() {
        // Required empty public constructor
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.m_indicator_activity,null);
        }

}
