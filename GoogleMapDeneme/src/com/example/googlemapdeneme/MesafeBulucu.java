package com.example.googlemapdeneme;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MesafeBulucu extends Activity  {

	private EditText lati1,long1,lati2,long2;
	private TextView sonuc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mesafe);
		
		lati1 = (EditText) findViewById(R.id.edtLatitute1);
		long1 = (EditText) findViewById(R.id.edtLong1);

		lati2 = (EditText) findViewById(R.id.edtLatitute2);
		long2 = (EditText) findViewById(R.id.edtLong2);
	
	    sonuc = (TextView) findViewById(R.id.txtSonuc);
		
		Button mesafeHesapla = (Button) findViewById(R.id.btnMesafeHesapla);
		mesafeHesapla.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double lat1,lon1,lat2,lon2;
				String a="";
				//gelen degerleri double a çevirme olayi
				lat1=Double.parseDouble(lati1.getText().toString());
				lon1=Double.parseDouble(long1.getText().toString());
				lat2=Double.parseDouble(lati2.getText().toString());
				lon2=Double.parseDouble(long2.getText().toString());
				
				a=getDistance(lat1,lon1,lat2,lon2);
				
				
//				a=getDistance(Double.valueOf(lati1.toString()), Double.valueOf(long1.toString()), Double.valueOf(lati2.toString()), Double.valueOf(long2.toString()));
			
//				a=getDistance(41.055667,28.948925,41.095815,28.993128);
			
				sonuc.setText("ARADAKI MESAFE= "+a);
				
			}
		});
		
		
		

		
	}
	public String getDistance(double lat1, double lon1, double lat2, double lon2) {
	    String result_in_kms = "";
	    String url = "http://maps.google.com/maps/api/directions/xml?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
	    String tag[] = {"text"};
	    HttpResponse response = null;
	    try {
	        HttpClient httpClient = new DefaultHttpClient();
	        HttpContext localContext = new BasicHttpContext();
	        HttpPost httpPost = new HttpPost(url);
	        response = httpClient.execute(httpPost, localContext);
	        InputStream is = response.getEntity().getContent();
	        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document doc = builder.parse(is);
	        if (doc != null) {
	            NodeList nl;
	            ArrayList args = new ArrayList();
	            for (String s : tag) {
	                nl = doc.getElementsByTagName(s);
	                if (nl.getLength() > 0) {
	                    Node node = nl.item(nl.getLength() - 1);
	                    args.add(node.getTextContent());
	                } else {
	                    args.add(" - ");
	                }
	            }
	            result_in_kms = String.format("%s", args.get(0));//sadece ilk parametre de gelen km bilgisi çek demek
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result_in_kms;
	}
	
}
