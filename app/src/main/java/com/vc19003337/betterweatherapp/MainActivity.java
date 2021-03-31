package com.vc19003337.betterweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;
import com.vc19003337.betterweatherapp.Common.Common;
import com.vc19003337.betterweatherapp.Permissions.BetterPermissions;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.os.Looper;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    private String TAG = "Location";
    private static final int REQUEST_CODE = 1;
    TabLayout tabLayout;
    Toolbar toolBar;
    ViewPager viewPager;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.better_tabs);
        toolBar = findViewById(R.id.better_toolbar);
        viewPager = findViewById(R.id.better_pager);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Setting up Permissions......
        String[] permsToRequest = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (!BetterPermissions.hasPermissions(this, permsToRequest))
        {
            ActivityCompat.requestPermissions(this, permsToRequest, REQUEST_CODE);
        }

        buildLocationRequest();
        buildLocationCallBack();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,
                Looper.myLooper());
    }

    private void buildLocationRequest()
    {
        locationRequest = LocationRequest.create();
        //locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10.0f);
    }

    private void buildLocationCallBack()
    {
        locationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                super.onLocationResult(locationResult);

                Common.currentLocation = locationResult.getLastLocation();
                Log.i(TAG, "our location is: " + Common.currentLocation);
            }
        };
    }
}