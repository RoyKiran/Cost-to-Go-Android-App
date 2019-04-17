package com.bakmaps.cost_to_go.Model;

import com.google.android.gms.location.places.Place;

public class RouteModel {

    private Place source, destination;

    public Place getSource() {
        return source;
    }

    public void setSource(Place source) {
        this.source = source;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;}
}
