package com.bakmaps.cost_to_go.ui;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.bakmaps.cost_to_go.Model.RouteModel;
import com.bakmaps.cost_to_go.R;
import com.bakmaps.cost_to_go.maps.GPSTracker;
import com.bakmaps.cost_to_go.maps.PlaceAutocomplete;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Google_Map_Cost extends Fragment implements LocationListener {

    GoogleMap googleMap;
    Context context;
    AutoCompleteTextView fromText, toText;
    FragmentActivity currentActivity;
    LatLng latLng;
    MapFragment supportMapFragment;
    public static RouteModel route = new RouteModel();

    public Google_Map_Cost() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.right_map_slider, container, false);
        context = container.getContext();
        currentActivity = (FragmentActivity) context;

        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            // currentActivity.finish();
            // show dialog saying no googleplayservices
        }

        // Set Map to current location
        supportMapFragment = (MapFragment) currentActivity.getFragmentManager().findFragmentById(R.id.map_fragment);
        googleMap = supportMapFragment.getMap();
        googleMap.setMyLocationEnabled(true);

        gpsCheck(parentView);
        handleLeftSlide(parentView);
        View up_slidingLayout = parentView.findViewById(R.id.downslide);
     //   up_slidingLayout.setVisibility(View.GONE);      // Initially UpSlider not visible
        handleUpSlide(up_slidingLayout);

        return parentView;
    }

    void gpsCheck(View v) {
        // Check if GPS is on/off
        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            // gps enabled
            onLocationChanged(gps.getLocation());
           /* // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();*/
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
            if (gps.canGetLocation()) {   // gps enabled
                onLocationChanged(gps.getLocation());
            } else {
                LocationManager locationManager = (LocationManager) currentActivity.getSystemService(context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String bestProvider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(bestProvider);
                if (location != null) {
                    onLocationChanged(location);
                }
            }
        }
    }

    void handleLeftSlide(View v) {
        PlaceAutocompleteFragment source_autocompleteFragment = (PlaceAutocompleteFragment)
                currentActivity.getFragmentManager().findFragmentById(R.id.source_autocomplete);

        source_autocompleteFragment.setOnPlaceSelectedListener(new PlaceAutocomplete(0));

        PlaceAutocompleteFragment dest_autocompleteFragment = (PlaceAutocompleteFragment)
                currentActivity.getFragmentManager().findFragmentById(R.id.source_autocomplete);

        dest_autocompleteFragment.setOnPlaceSelectedListener(new PlaceAutocomplete(1));

        if (route.getSource() != null && route.getDestination() != null) {

        }


    }

    void handleUpSlide(View v) {

        FragmentTransaction mFragmentTransaction = currentActivity.getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.slidecontent,new TabFragment(1)).commit();
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, currentActivity, 0).show();
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }


}


