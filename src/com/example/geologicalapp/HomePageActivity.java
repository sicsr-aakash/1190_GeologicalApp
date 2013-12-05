package com.example.geologicalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends Activity {
	Button introduction,rockType,quiz,quit,help;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		introduction=(Button)findViewById(R.id.btn_introduction);
		rockType=(Button)findViewById(R.id.btn_typesOfRocks);
		quit=(Button)findViewById(R.id.btn_quit);
		help=(Button)findViewById(R.id.btn_help);
		quiz=(Button)findViewById(R.id.btn_testYourSkills);
	
		introduction.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),IntroductionActivity.class);
				startActivity(intent);
			}
		});
		
		rockType.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),RockTypeActivity.class);
				startActivity(intent);
			}
		});
		
		
		
        quiz.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),QuizActivity.class);
				startActivity(intent);
	       }
       });
        quit.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();          
	            moveTaskToBack(true);
	       }
       });
       help.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),HelpActivity.class);
				startActivity(intent);
				
	       }
       });
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}

}
