package com.example.googlemapdeneme;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

 
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class GoogleMapGosterici extends FragmentActivity {
 
private GoogleMap googleHarita;
 
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.google_map);
 
    String gelenEnlem = getIntent().getExtras().getString("enlem");
    

    
    String gelenBoylam = getIntent().getExtras().getString("boylam");
    
    
    if (googleHarita == null) {
        googleHarita = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritafragment))
                .getMap();
        if (googleHarita != null) {
            

        	double enlem=Double.valueOf(gelenEnlem);
        	double boylam=Double.valueOf(gelenBoylam);

			
//        	LatLng kordinat = new LatLng(41.06102533179591,28.952445157771024);
        	// intent de gelen GpsBulucu.java daki enlem boylami ver.
        	LatLng kordinat = new LatLng(enlem,boylam);
            googleHarita.addMarker(new MarkerOptions().position(kordinat).title("Su an Bulundugun Yer"));
            googleHarita.moveCamera(CameraUpdateFactory.newLatLngZoom(kordinat, 13));         
        }
    }
    

   
 
}
}