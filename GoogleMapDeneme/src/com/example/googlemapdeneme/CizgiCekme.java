package com.example.googlemapdeneme;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

 
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;



public class CizgiCekme extends FragmentActivity {
 
private GoogleMap googleHarita;
 
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.google_map);
   
    
    if (googleHarita == null) {
        googleHarita = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritafragment))
                .getMap();
        if (googleHarita != null) {

		
        	Polyline line = googleHarita.addPolyline(new PolylineOptions()
            .add(new LatLng(41.061071, 28.949268), new LatLng(41.060845, 28.949569), new LatLng(41.060715, 28.949826), new LatLng(41.060553, 28.950084))
            .width(5)
            .color(Color.RED));
   
     
        }
    }
}
}