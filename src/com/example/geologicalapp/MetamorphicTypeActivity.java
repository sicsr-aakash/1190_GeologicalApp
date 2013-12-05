package com.example.geologicalapp;

import java.lang.reflect.Field;

import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MetamorphicTypeActivity extends Activity {
	TextView t1;
	ImageView imgView;
	LoadRawData rawData;
	int image[]={R.drawable.metamorphic_type_img1,R.drawable.metamorphic_type_img2,R.drawable.metamorphic_type_img3,
			R.drawable.metamorphic_type_img4,R.drawable.metamorphic_type_img5,R.drawable.metamorphic_type_img6,
			R.drawable.metamorphic_type_img7,R.drawable.metamorphic_type_img8,R.drawable.metamorphic_type_img9,
			R.drawable.metamorphic_type_img10,R.drawable.metamorphic_type_img11,R.drawable.metamorphic_type_img12,
			R.drawable.metamorphic_type_img13,R.drawable.metamorphic_type_img14};
	int explanation[]={R.raw.metamorphic_type_soapstone1,R.raw.metamorphic_type_slate2,R.raw.metamorphic_type_argillite3,
			R.raw.metamorphic_type_phylite4,R.raw.metamorphic_type_mylonite5,R.raw.metamorphic_type_schist6,
			R.raw.metamorphic_type_gneiss7,R.raw.metamorphic_type_migmatite8,R.raw.metamorphic_type_amphibolite9,
			R.raw.metamorphic_type_serpentinite10,R.raw.metamorphic_type_hornfels11,R.raw.metamorphic_type_eclogite12,
			R.raw.metamorphic_type_marble13,R.raw.metamorphic_type_quartzite14
			};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
	        ViewConfiguration config = ViewConfiguration.get(this);
	        Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	        if(menuKeyField != null) {
	            menuKeyField.setAccessible(true);
	            menuKeyField.setBoolean(config, false);
	        }
	    } catch (Exception ex) {
	        // Ignore
	    }
		
		about_metamorphic();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_igneous_type, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.about:
	            about_metamorphic();
	            return true;
	        case R.id.list:
		           list();
	            return true;
	        case R.id.call:
	        	 call_metamorphic();
	            return true;
	        case R.id.Email_Us :
	        	email_metamorphic();
	        	 return true;
	        case R.id.web_link :
	        	web_link();
	        	 return true;
	        case R.id.igneous_home :
	        	metamorphic_home();
	        	 return true;
	        case R.id.igneous_back :
	        	metamorphic_back();
	        	 return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public void about_metamorphic()
	{
		setContentView(R.layout.activity_metamorphic_type);
		t1=(TextView)findViewById(R.id.txtMetamorphic);
		LoadRawData rawData=new LoadRawData(R.raw.about_metamorphic);
		t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
	}
	public void list()
	{
		 // Creating a new LinearLayout
	       LinearLayout linearLayout = new LinearLayout(this);
	      
	       linearLayout.setOrientation(LinearLayout.VERTICAL);
	       LinearLayout.LayoutParams layoutParam2= new LinearLayout.LayoutParams(380,200);
	       layoutParam2.setMargins(50,20,50,20);
	     
	       
	       
	       LayoutParams param=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	       LinearLayout.LayoutParams layoutParam1= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	       layoutParam1.setMargins(20,20,20,20);
	        
	       for(int i=1;i<=14;i++)
	        {
	        	imgView=new ImageView(this);
	        	imgView.setBackgroundResource(image[i-1]);
	        	imgView.setLayoutParams(layoutParam2);
	        	
	        	linearLayout.addView(imgView);
	        
	        	rawData =new LoadRawData(explanation[i-1]);
	        	t1=new TextView(this);
	        	
	        	t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
	        	
	       
	        	t1.setLayoutParams(param);
	        	linearLayout.addView(t1, layoutParam1);
	        	
	        }
	       
	       ScrollView scrollView=new ScrollView(this);
	      
	       scrollView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	       scrollView.addView(linearLayout);
	       setContentView(scrollView); 	
	       
	       
	        
        
        
	}
	public void metamorphic_back()
	{
		finish();
	}
	public void metamorphic_home()
	{
		Intent i=new Intent(getApplicationContext(),HomePageActivity.class);
		startActivity(i);
	}
	public void web_link()
	{
		Uri webpage = Uri.parse("http://geology.com/rocks");
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
		startActivity(webIntent);
	}
	public void call_metamorphic()
	
	{
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
			.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
		try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:8055320381"));
	        startActivity(callIntent);
	    } 
		catch (ActivityNotFoundException e) {
	        Log.e("Unable To call !!! Set Permission in Manifest", "Call failed", e);
	    }
	}
	public void email_metamorphic()
	{
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		// The intent does not have a URI, so declare the "text/plain" MIME type
		emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"bhavesh.kawad01@gmail.com"}); // recipients
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
		try {
		    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	private class PhoneCallListener extends PhoneStateListener {
		 
		private boolean isPhoneCalling = false;
 
		String LOG_TAG = "LOGGING 123";
 
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
 
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}
 
			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");
 
				isPhoneCalling = true;
			}
 
			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended, 
				// need detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");
 
				if (isPhoneCalling) {
 
					Log.i(LOG_TAG, "restart app");
 
					// restart activity
					Intent i = new Intent(getApplicationContext(),MetamorphicTypeActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
 
					isPhoneCalling = false;
				}
 
			}
		}
	}
 

}
