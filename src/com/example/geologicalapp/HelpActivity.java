package com.example.geologicalapp;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class HelpActivity extends Activity {
	AutoCompleteTextView textView;
	ImageButton img;
	TextView t1;
	ImageView imgView;
	String igneous_rocks[]={"andesite","basalt","diorite","dunite","felsite","obsidian","pumice","scoria",
							"porphyry","granite","syenite","tonalite","gabbro","peridotite","pyroxenite","pegmatite"};
	
	String metamorphic_rocks[]={
			"soapstone","slate","argillite","phylite","mylonite","schist","gneiss",
			"migmatite","amphibolite","serpentinite","hornfels","eclogite","marbles","quartzite"
	
								};
	String sedimentary_rocks[]={"sandstone","arkose","wacke","conglomerate","breccia","siltstone","chert","shale",
			"coal","limestone","dolostone","coquina","rocksalt","rockgypsum"
								};
	
	int explanation_igneous[]={R.raw.igneous_type_andesite1,R.raw.igneous_type_basalt2,R.raw.igneous_type_diorite3,
			R.raw.igneous_type_dunite4,R.raw.igneous_type_felsite5,R.raw.igneous_type_obsidian6,
			R.raw.igneous_type_pumice7,R.raw.igneous_type_scoria8txt,R.raw.igneous_type_porphyry9,
			R.raw.igneous_type_granite10,R.raw.igneous_type_syenite11,R.raw.igneous_type_tonalite12,
			R.raw.igneous_type_gabbro13,R.raw.igneous_type_peridotite14,R.raw.igneous_type_pyroxenite15,
			R.raw.igneous_type_pegmatite16};
	
	
	int explanation_metamorphic[]={
			R.raw.metamorphic_type_soapstone1,R.raw.metamorphic_type_slate2,R.raw.metamorphic_type_argillite3,
			R.raw.metamorphic_type_phylite4,R.raw.metamorphic_type_mylonite5,R.raw.metamorphic_type_schist6,
			R.raw.metamorphic_type_gneiss7,R.raw.metamorphic_type_migmatite8,R.raw.metamorphic_type_amphibolite9,
			R.raw.metamorphic_type_serpentinite10,R.raw.metamorphic_type_hornfels11,R.raw.metamorphic_type_eclogite12,
			R.raw.metamorphic_type_marble13,R.raw.metamorphic_type_quartzite14
			};
	
int explanation_sedimentary[]={
			R.raw.sedimentary_type_sandstone1,R.raw.sedimenatry_type_arkose2,R.raw.sedimentary_type_wacke3,
			
			R.raw.sedimentary_type_conglomerate4,R.raw.sedimentary_type_breccia5,R.raw.sedimentary_type_siltstone6,
			R.raw.sedimentary_type_chert7,R.raw.sedimentary_type_shale8,R.raw.sedimentary_type_coal9,
			R.raw.sedimentary_type_limestone10,R.raw.sedimentary_type_dolostone11,R.raw.sedimentary_type_coquina12,
			R.raw.sedimentary_type_rocksalt13,R.raw.sedimentary_type_rockgypsum14
			
	};
int image_igneous[]={R.drawable.igneous_type_img1,R.drawable.igneous_type_img2,R.drawable.igneous_type_img3,
		R.drawable.igneous_type_img4,R.drawable.igneous_type_img5,R.drawable.igneous_type_img6,
		R.drawable.igneous_type_img7,R.drawable.igneous_type_img8,R.drawable.igneous_type_img9,
		R.drawable.igneous_type_img10,R.drawable.igneous_type_img11,R.drawable.igneous_type_img12,
		R.drawable.igneous_type_img13,R.drawable.igneous_type_img14,R.drawable.igneous_type_img15,
		R.drawable.igneous_type_img16};

int image_sedimentary[]={R.drawable.sedimentary_type_img1,R.drawable.sedimentary_type_img2,R.drawable.sedimentary_type_img3,
		R.drawable.sedimentary_type_img4,R.drawable.sedimentary_type_img5,R.drawable.sedimentary_type_img6,
		R.drawable.sedimentary_type_img7,R.drawable.sedimentary_type_img8,R.drawable.sedimentary_type_img9,
		R.drawable.sedimentary_type_img10,R.drawable.sedimentary_type_img11,R.drawable.sedimentary_type_img12,
		R.drawable.sedimentary_type_img13,R.drawable.sedimentary_type_img14
		};
int image_metamorphic[]={R.drawable.metamorphic_type_img1,R.drawable.metamorphic_type_img2,R.drawable.metamorphic_type_img3,
		R.drawable.metamorphic_type_img4,R.drawable.metamorphic_type_img5,R.drawable.metamorphic_type_img6,
		R.drawable.metamorphic_type_img7,R.drawable.metamorphic_type_img8,R.drawable.metamorphic_type_img9,
		R.drawable.metamorphic_type_img10,R.drawable.metamorphic_type_img11,R.drawable.metamorphic_type_img12,
		R.drawable.metamorphic_type_img13,R.drawable.metamorphic_type_img14};

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
		
		search();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.app_search:
	        	search();
	            return true;
	        case R.id.app_help:
		          help(); 
	            return true;
	        case R.id.search_home:
	        	home();
	            return true;
	        case R.id.search_back :
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
	public void search()
	{
		setContentView(R.layout.activity_help);
		 textView = (AutoCompleteTextView) findViewById(R.id.txt_search);
		// Get the string array
		String[] rocks = getResources().getStringArray(R.array.Rocks);
		// Create the adapter and set it to the AutoCompleteTextView 
		ArrayAdapter<String> adapter = 
		        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rocks);
		textView.setAdapter(adapter);
		
		img=(ImageButton)findViewById(R.id.img_search);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				imgView=(ImageView)findViewById(R.id.imgSearchResult);
				t1=(TextView)findViewById(R.id.txtSearchResult);
				String result=textView.getText().toString();
				breakWhenFound:
					for(int i=0;i<16;i++)
				{
					if(result.equals(igneous_rocks[i]))
					{
						LoadRawData rawData=new LoadRawData (explanation_igneous[i]);
						imgView.setBackgroundResource(image_igneous[i]);
						t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
						
						break breakWhenFound;
						
					}
					if(i<14)
						{
					if(result.equals(metamorphic_rocks[i]))
					{
						LoadRawData rawData=new LoadRawData (explanation_metamorphic[i]);
						imgView.setBackgroundResource(image_metamorphic[i]);
						t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
						
						break breakWhenFound;
						
					}
					if(result.equals(sedimentary_rocks[i]))
					{
						LoadRawData rawData=new LoadRawData (explanation_sedimentary[i]);
						imgView.setBackgroundResource(image_sedimentary[i]);
						t1.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
						
						break breakWhenFound;
						
					}
						}
					
				}
				
				
			}
		});
		
		
	}
	public void help()
	{
		 LinearLayout linearLayout = new LinearLayout(this);
	      
	     linearLayout.setOrientation(LinearLayout.VERTICAL);
	     LayoutParams param=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	     LinearLayout.LayoutParams layoutParam1= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	     layoutParam1.setMargins(20,20,20,20);
	    
		TextView txtHelp=new TextView(this);
		LoadRawData rawData=new LoadRawData(R.raw.help);
		rawData.getRawData(getApplicationContext());
		
		txtHelp.setText(Html.fromHtml(rawData.getRawData(getApplicationContext()).toString()));
    	
	       
    	txtHelp.setLayoutParams(param);
    	linearLayout.addView(txtHelp, layoutParam1);
    	
    	 ScrollView scrollView=new ScrollView(this);
         
         scrollView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         scrollView.addView(linearLayout);
         setContentView(scrollView);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	

}
