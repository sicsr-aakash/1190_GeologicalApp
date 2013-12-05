package com.example.geologicalapp;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class RockTypeActivity extends Activity {
	ImageView view1,view2,view3;
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
		setContentView(R.layout.activity_rock_type);
		view1=(ImageView)findViewById(R.id.btn_igneous);
		view2=(ImageView)findViewById(R.id.btn_metamorphic);
		view3=(ImageView)findViewById(R.id.btn_sedimentary);
		view1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),IgneousTypeActivity.class);
				startActivity(intent);
			}
		});
        view2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),MetamorphicTypeActivity.class);
				startActivity(intent);
			}
		});
        view3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),SedimentaryTypeActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_back_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        
	        case R.id.app_home :
	        	home();
	        	 return true;
	        case R.id.app_back :
	        	back();
	        	 return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
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

}
