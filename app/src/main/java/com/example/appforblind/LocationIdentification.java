package com.example.appforblind;

import android.location.LocationProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.LocationServices.*;

public class LocationIdentification {

    LocationProvider location;
    private FusedLocationProviderClient fusedLocationClient;
    public void getLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }

}
