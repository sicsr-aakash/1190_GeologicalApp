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
import android.widget.TextView;
import android.widget.Toast;

public class IntroductionActivity extends Activity {
	TextView t1;
	TextView txtRock;
	TextView txtImg1,txtImg2,txtImg3;
	
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
		
		
		about();
	    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.introduction_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.introduction_about:
	        	about();
	            return true;
	        case R.id.introduction_rock_cycle:
		         rock_cycle();
	            return true;
	        case R.id.intoduction_call:
	        		call();
	            return true;
	        case R.id.introduction_Email_Us :
	        		email();
	        	 return true;
	        case R.id.intoduction_web_link :
	        	web_link();
	        	 return true;
	        case R.id.introduction_home :
	        	home();
	        	 return true;
	        case R.id.introduction_back :
	        	back();
	        	 return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	public void rock_cycle()
	{
		setContentView(R.layout.rock_cycle_introduction);
		txtRock=(TextView)findViewById(R.id.rock_cycle);
		txtImg1=(TextView)findViewById(R.id.rock_cycle_stage_desc);
		txtImg2=(TextView)findViewById(R.id.rock_cycle_igneous_structure);
		txtImg3=(TextView)findViewById(R.id.rock_cycle_diagram);
		LoadRawData rawData=new LoadRawData(R.raw.rock_cycle_introduction);
		txtRock.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
		txtImg1.setText((Html.fromHtml("<b><u>Rock Cycle Stages</u></b>")));
		txtImg2.setText((Html.fromHtml("<b><u>Igneous Structure</u></b>")));
		txtImg3.setText((Html.fromHtml("<b><u>Rock Cycle Diagram</u></b>")));
		
		
	}
	public void about()
	{
		setContentView(R.layout.activity_introduction);
		t1=(TextView)(findViewById(R.id.textView1));
		
		LoadRawData rawData=new LoadRawData(R.raw.whatisgeology);
		t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
		
	}
	public void back()
	{
		finish();
	}
	public void home()
	{
		Intent i=new Intent(getApplicationContext(),HomePageActivity.class);
		startActivity(i);
	}
	public void web_link()
	{
		Uri webpage = Uri.parse("http://geology.com");
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
		startActivity(webIntent);
	}
	public void email()
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
	public void call()
	
	{
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
			.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
		try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:8055320384"));
	        startActivity(callIntent);
	    } 
		catch (ActivityNotFoundException e) {
	        Log.e("Unable To call !!! Set Permission in Manifest", "Call failed", e);
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
					Intent i = new Intent(getApplicationContext(),IntroductionActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
 
					isPhoneCalling = false;
				}
 
			}
		}
	}
 
}
