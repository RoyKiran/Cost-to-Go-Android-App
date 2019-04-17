package com.bakmaps.cost_to_go.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakmaps.cost_to_go.R;

/**
 * Created by WORK with FUN on 01-02-2016.
 */
public class ListRoutes extends Fragment {

    int type;
    public ListRoutes(){

    }

    public ListRoutes(int position){
        type = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_list_item, container, false);

        return v;
    }

}
