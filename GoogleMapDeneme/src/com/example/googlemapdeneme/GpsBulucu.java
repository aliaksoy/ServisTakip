package com.example.googlemapdeneme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GpsBulucu extends Activity implements LocationListener {
	private TextView enlemDegeri;
	private TextView boylamDegeri;
	private LocationManager locationManager;
	private String provider;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps);
		enlemDegeri = (TextView) findViewById(R.id.enlemIcerik);
		boylamDegeri = (TextView) findViewById(R.id.boylamIcerik);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){  
	          gpsErisilemiyorUyarisi();  
	    } 
		
		provider = LocationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			enlemDegeri.setText(String.valueOf(location.getLatitude()));
			boylamDegeri.setText(String.valueOf(location.getLongitude()));

			
		} else {
			enlemDegeri.setText("Konum bilginize erisilemiyor");
			boylamDegeri.setText("Konum bilginize erisilemiyor");

		}
		
		
		Button haritadaGoster = (Button) findViewById(R.id.btnHaritadaGoster);
		haritadaGoster.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNesnesi = new Intent(GpsBulucu.this,GoogleMapGosterici.class); 
				intentNesnesi.putExtra("enlem", enlemDegeri.getText().toString());
				intentNesnesi.putExtra("boylam", boylamDegeri.getText().toString());
				startActivity(intentNesnesi);
				
			}
		});
		
		
		Button mesafeHesapla = (Button) findViewById(R.id.btnHesapla);
		mesafeHesapla.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNesne = new Intent(GpsBulucu.this,MesafeBulucu.class); 
				startActivity(intentNesne);
				
			}
		});
		
		
		
		Button cizgi = (Button) findViewById(R.id.btnCizgi);
		cizgi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				Intent intentNesne = new Intent(GpsBulucu.this,CizgiCekme.class); 
				startActivity(intentNesne);
//				Intent i = new Intent(getBaseContext(),CizgiCekme.class);
//                getApplicationContext().startActivity(i);
				
//				Intent intent = new Intent();
//				intent.setClass(GpsBulucu.this, CizgiCekme.class);
//				startActivity(intent);
				
//				Intent i = new Intent(GpsBulucu.this,CizgiCekme.class); 
//				startActivity(i);
				
				
//				Uri uri = Uri.parse("https://maps.google.com/?q=pizza+hut&ll=-33.867701,151.208471&z=12");
//			     Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
//			     intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//			     intent.setClass(GpsBulucu.this, CizgiCekme.class);
//			     startActivity(intent);
				
//                Intent intent = new Intent();
//                intent.setClass(GpsBulucu.this, CizgiCekme.class);
//                startActivity(intent);
		
			}
		});
		
		
		

		
	}
	
	private void gpsErisilemiyorUyarisi(){  
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		builder.setMessage("GPS kapaalı, acmak ister misiniz?")  
		     .setCancelable(false)  
		     .setPositiveButton("GPS Aktiflestir",  
		          new DialogInterface.OnClickListener(){  
		          public void onClick(DialogInterface dialog, int id){  
		               gpsSecenekleriGoster();  
		          }  
		     });  
		     builder.setNegativeButton("Hayir aktiflestirme",  
		          new DialogInterface.OnClickListener(){  
		          public void onClick(DialogInterface dialog, int id){  
		               dialog.cancel();  
		          }  
		     });  
		AlertDialog alert = builder.create();  
		alert.show();  
		}  

	//Ayarladaki gps acma yerine gitme olayi.
	private void gpsSecenekleriGoster(){  
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
        startActivity(gpsOptionsIntent);  
	} 

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);// 400 milisaniyede bir 
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	//Konum degisikliginde calicak method
	@Override
	public void onLocationChanged(Location location) {
		enlemDegeri.setText(String.valueOf(location.getLatitude()));
		boylamDegeri.setText(String.valueOf(location.getLongitude()));

	}

	//GPS sinyalinin cekmedigi durmlarda
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	//Kullanicinin GPS i acma durumu
	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "GPS ACIK : " + provider,Toast.LENGTH_SHORT).show();

	}

	//Kullanicinin GPS is kapatma durumu
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "GPS KAPALI : " + provider,Toast.LENGTH_SHORT).show();
		enlemDegeri.setText("");
		boylamDegeri.setText("");

	}
}
