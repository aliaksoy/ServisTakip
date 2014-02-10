package com.varma.samples.speeddemo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.varma.samples.interfaces.Constants;
import com.varma.samples.interfaces.GPSCallback;
import com.varma.samples.managers.GPSManager;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

public class MainActivity extends Activity implements GPSCallback{
        private GPSManager gpsManager = null;
        private double speed = 0.0;
        private int measurement_index = Constants.INDEX_KM;
        private AbsoluteSizeSpan sizeSpanLarge = null;
        private AbsoluteSizeSpan sizeSpanSmall = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        gpsManager = new GPSManager();
        
        gpsManager.startListening(getApplicationContext());
        gpsManager.setGPSCallback(this);
        
        ((TextView)findViewById(R.id.info_message)).setText(getString(R.string.info));
        
       
    }

        @Override
        public void onGPSUpdate(Location location) 
        {
                location.getLatitude();
                location.getLongitude();
                speed = location.getSpeed();
                
                String speedString = "" + roundDecimal(convertSpeed(speed),2);
                String unitString = measurementUnitString(measurement_index);
                
                setSpeedText(R.id.info_message,speedString + " " + unitString);
        }

        @Override
        protected void onDestroy() {
                gpsManager.stopListening();
                gpsManager.setGPSCallback(null);
                
                gpsManager = null;
                
                super.onDestroy();
        }
                
     

        private double convertSpeed(double speed){
                return ((speed * Constants.HOUR_MULTIPLIER) * Constants.UNIT_MULTIPLIERS[measurement_index]); 
        }
        
        private String measurementUnitString(int unitIndex){
                String string = "";
                
                switch(unitIndex)
                {
                        case Constants.INDEX_KM:                string = "km/h";        break;
//                        case Constants.INDEX_MILES:     string = "mi/h";        break;
                }
                
                return string;
        }
        
        private double roundDecimal(double value, final int decimalPlace)
        {
                BigDecimal bd = new BigDecimal(value);
                
                bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
                value = bd.doubleValue();
                
                return value;
        }
        
        private void setSpeedText(int textid,String text)
        {
                Spannable span = new SpannableString(text);
                int firstPos = text.indexOf(32);
                
                span.setSpan(sizeSpanLarge, 0, firstPos,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                span.setSpan(sizeSpanSmall, firstPos + 1, text.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                
                TextView tv = ((TextView)findViewById(textid));
                
                tv.setText(span);
        }
        

}