package com.bakmaps.cost_to_go.maps;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bakmaps.cost_to_go.Model.RouteModel;
import com.bakmaps.cost_to_go.ui.Google_Map_Cost;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class PlaceAutocomplete implements PlaceSelectionListener {

    private static final String TAG = PlaceAutocomplete.class.getSimpleName();

    int SOURCE_POINT = 0;
    int DESTINATION_POINT = 1;
    int check;

    public PlaceAutocomplete(int check) {
        this.check = check;
    }

    @Override
    public void onPlaceSelected(Place place) {
        // TODO: Get info about the selected place.
        Log.wtf(TAG, "Place: " + place.getName());

        if (check == SOURCE_POINT)
            Google_Map_Cost.route.setSource(place);

        else if (check == DESTINATION_POINT)
            Google_Map_Cost.route.setDestination(place);
    }

    @Override
    public void onError(Status status) {
        // TODO: Handle the error.
        Log.wtf(TAG, "An error occurred: " + status);
    }

}
