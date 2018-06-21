package com.example.huntertsai.fusedlocationapi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_REQUEST_CODE = 1111;
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView latitude_tv;
    private TextView longitude_tv;
    private String mlatitude;
    private String mlongitude;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude_tv = findViewById(R.id.latitude_tv);
        longitude_tv = findViewById(R.id.longitude_tv);

        mlatitude = "Latitude: ";
        mlongitude = "Longitude: ";

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!checkPermission()) {
            requestPermission();
        }else{
            getLastLocation();
        }
    }

    private boolean checkPermission() {
       int permissionState = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
       return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSION_REQUEST_CODE);
    }

    private void requestPermission() {
        Boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale){
            //show snackbar
            Log.i("MainActivity", "Display snackbar");
            showSnackBar(R.string.permission_rationale, android.R.string.ok, new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startLocationPermissionRequest();
                }
            });
        }else{
            Log.i("MainActivity", "Get Request permission");
            startLocationPermissionRequest();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
//        mFusedLocationClient.getLastLocation()
//                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//
//                        if (task.isSuccessful() && task.getResult() != null) {
//                            // Logic to handle location object
//                            mLastLocation = task.getResult();
//
//                            latitude_tv.setText(String.format(Locale.ENGLISH, "%s: %f", mlatitude, mLastLocation.getLatitude()));
//
//                            longitude_tv.setText(String.format(Locale.ENGLISH, "%s: %f", mlongitude, mLastLocation.getLongitude()));
//
//                        }else{
//                            Log.w("MainActivity", "getLastLocation: e", task.getException());
//                            showSnackBar(getString(R.string.no_location_detected));
//                        }
//                    }
//                });

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            latitude_tv.setText(String.format(Locale.ENGLISH, "%s: %f", mlatitude, location.getLatitude()));
                            longitude_tv.setText(String.format(Locale.ENGLISH, "%s: %f", mlongitude, location.getLongitude()));
                        }else{
                            Log.w("MainActivity", "getLastLocation: e");
                            showSnackBar(getString(R.string.no_location_detected));
                        }
                    }
                });
    }



    private void showSnackBar(final String text){
        View container = findViewById(R.id.main_activity_container);
        if (container != null){
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    private void showSnackBar(final int mainTextStringId, final int actionStringId, View.OnClickListener listener){
        Snackbar.make(findViewById(android.R.id.content), getString(mainTextStringId), Snackbar.LENGTH_INDEFINITE).
        setAction(getString(actionStringId), listener).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.i("MainActivity", "onREqusetPermissionResult");
        if (requestCode == REQUEST_PERMISSION_REQUEST_CODE){
            if (grantResults.length <= 0){
                Log.i("MainActivity", "user interaction was canceled");
            }else if (grantResults[0] == PackageManager. PERMISSION_GRANTED){
                getLastLocation();
            }
        } else{
            showSnackBar(R.string.permission_denied_explanation, R.string.settings, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                    Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                    intent.setData(uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }
    }

}
