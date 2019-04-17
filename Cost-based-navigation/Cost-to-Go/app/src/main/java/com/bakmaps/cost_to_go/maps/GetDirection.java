package com.bakmaps.cost_to_go.maps;

import com.bakmaps.cost_to_go.Model.RouteModel;
import com.bakmaps.cost_to_go.R;
import com.bakmaps.cost_to_go.ui.Google_Map_Cost;
import com.google.android.gms.location.places.Place;


public class GetDirection {

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/";
    private static final String TYPE = "";
    private static final String OUT_JSON = "json";
    private static final String API_KEY = "AIzaSyD3ZeO6Ykg_Dj8yanrYVsejY-IDWFE3we8";
    private static String parameter;

    Place source , destination;

    GetDirection(){
        source = Google_Map_Cost.route.getSource();
        destination = Google_Map_Cost.route.getDestination();
    }

    public void setPath(){
        /* url: https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|
        Victoria+BC&key=YOUR_API_KEY
    */
        //parameter = "?origin="+source.getAddress()+"&destination="+destination.getAddress()+"&key="+API_KEY;
        parameter = "";
    }

}
