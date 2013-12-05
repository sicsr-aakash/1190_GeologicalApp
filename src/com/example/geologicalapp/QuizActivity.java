package com.example.geologicalapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private TextView myText = null;
//	private ImageView img = null;
	private RadioButton myRadio1 = null;
	private RadioButton myRadio2 = null;
	private RadioButton myRadio3 = null;
//	private RadioButton myRadio4 = null;
	private RadioGroup rg = null;
	private Button nextButton = null;
	private Button finishButton = null;
	private ArrayList<Questions> questions = null;
	private int nextQuestion = 0;
	private LinearLayout lView = null;
	private int score = 0;
	LinearLayout.LayoutParams layoutParamParent;
	LinearLayout.LayoutParams layoutParamQuestion;
	LinearLayout.LayoutParams layoutParam1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_quiz);
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
		
		
		addQuestions();
		Collections.shuffle(questions);
		
		nextQuestion = 0;
		onClickNext();

		
	}
	

	
	public void addQuestions() {
		InputStream in1;
		InputStream in2;
		InputStream in3;
		InputStream in4;
//		InputStream in5;
		BufferedReader reader1;
		BufferedReader reader2;
		BufferedReader reader3;
		BufferedReader reader4;
//		BufferedReader reader5;
		String questionline= "";
		String correctAns= "";
		String incorrectAns1= "";
		String incorrectAns2= "";
//		String incorrectAns3= "";
		int qid = 0;
		
		questions = new ArrayList<Questions>();
		
		try {
			in1 = this.getAssets().open("questions.txt");
			in2 = this.getAssets().open("correct.txt");
			in3 = this.getAssets().open("incorrect1.txt");
			in4 = this.getAssets().open("incorrect2.txt");
//			in5 = this.getAssets().open("incorrect3.txt");
			
			reader1 = new BufferedReader(new InputStreamReader(in1));
			reader2 = new BufferedReader(new InputStreamReader(in2));
			reader3 = new BufferedReader(new InputStreamReader(in3));
			reader4 = new BufferedReader(new InputStreamReader(in4));
//			reader5 = new BufferedReader(new InputStreamReader(in5));
			
			questionline = reader1.readLine();
			correctAns = reader2.readLine();
			incorrectAns1 = reader3.readLine();
			incorrectAns2 = reader4.readLine();
//			incorrectAns3 = reader5.readLine();
			
			while(questionline!=null) {
				Questions q =  new Questions();
				ArrayList<String> allanswers= new ArrayList<String>();
				q = new Questions();
				allanswers = new ArrayList<String>();
				
				q.setQId(qid);
				qid++;
//				q.setImages(R.drawable.quant);
				q.setQuestions(questionline);
				q.setAnswers(correctAns);
				allanswers.add(correctAns);
				allanswers.add(incorrectAns1);
				allanswers.add(incorrectAns2);
//				allanswers.add(incorrectAns3);
				Collections.shuffle(allanswers);
				q.setAllAanswers(allanswers);
				questions.add(q);
				questionline = reader1.readLine();
				correctAns = reader2.readLine();
				incorrectAns1 = reader3.readLine();
				incorrectAns2 = reader4.readLine();
//				incorrectAns3 = reader5.readLine();
			}
	    }
	    catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void onClickNext() {
		if(nextQuestion < 14) {
			
			lView = new LinearLayout(this);
			layoutParam1= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 60);
		    layoutParam1.setMargins(80,40,80,70);
			lView.setOrientation(LinearLayout.VERTICAL);
			lView.setBackgroundColor(Color.parseColor("#EDC9AF"));
			nextButton = new Button(this);
			nextButton.setText("Next");
			nextButton.setBackgroundResource(R.drawable.black);
			//nextButton.setLayoutParams(layoutParam1);
			
			 layoutParamQuestion= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
			
		    layoutParamQuestion.setMargins(15,25,40,20);
			String question = questions.get(nextQuestion).questions;
			
			myText = new TextView(this);
			//myText.setLayoutParams(layoutParam1);
			myText.setText((nextQuestion+1)+"."+question);
			
			myRadio1 = new RadioButton(this);
			myRadio2 = new RadioButton(this);
			myRadio3 = new RadioButton(this);
//			myRadio4 = new RadioButton(this);
			
			
			myRadio1.setText(questions.get(nextQuestion).getAllAnswers().get(0));
			myRadio2.setText(questions.get(nextQuestion).getAllAnswers().get(1));
			myRadio3.setText(questions.get(nextQuestion).getAllAnswers().get(2));
//			myRadio4.setText(questions.get(nextQuestion).getAllAnswers().get(3));
			
			rg = new RadioGroup(this);
			rg.addView(myRadio1);
			rg.addView(myRadio2);
			rg.addView(myRadio3);
//			rg.addView(myRadio4);
			
			nextButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					checkMarkedAnswer();
					nextQuestion++;
					onClickNext();
				}
			});
           layoutParamParent= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			
		    layoutParamParent.setMargins(10,10,10,10);
			lView.addView(myText,layoutParamQuestion);
//			lView.addView(img);
			lView.addView(rg);
			lView.addView(nextButton,layoutParam1);
			setContentView(lView,layoutParamParent);
		}
	/*	else if(nextQuestion==14){
			lView = new LinearLayout(this);
			lView.setOrientation(LinearLayout.VERTICAL);
			
			lView.setBackgroundColor(Color.parseColor("#EDC9AF"));
			finishButton = new Button(this);
			finishButton.setText("Finish Quiz");
			finishButton.setBackgroundResource(R.drawable.black);
			
			String question = questions.get(nextQuestion).questions;
			
			myText = new TextView(this);
			myText.setText((nextQuestion+1)+"."+question);
			
			
			myRadio1 = new RadioButton(this);
			myRadio2 = new RadioButton(this);
			myRadio3 = new RadioButton(this);
			
			
			myRadio1.setText(questions.get(nextQuestion).getAllAnswers().get(0));
			myRadio2.setText(questions.get(nextQuestion).getAllAnswers().get(1));
			myRadio3.setText(questions.get(nextQuestion).getAllAnswers().get(2));

			
			rg = new RadioGroup(this);
			rg.addView(myRadio1);
			rg.addView(myRadio2);
			rg.addView(myRadio3);

			
			finishButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					checkMarkedAnswer();
					nextQuestion++;
					onClickNext();
				}
			});
			
			lView.addView(myText,layoutParamParent);
//			lView.addView(img);
			lView.addView(rg);
			lView.addView(finishButton,layoutParam1);
			setContentView(lView);
		}*/
		else {
			//lView = new LinearLayout(this);
			//lView.setOrientation(LinearLayout.VERTICAL);
			Toast toast = Toast.makeText(getApplicationContext()," Your Final Score is  :  " + score , Toast.LENGTH_LONG);
			
			
			toast.show();
			
			//myText = new TextView(this);
			//myText.setText(" Your Final Score is  :  " + score);
			//lView.addView(myText);
			setContentView(R.layout.activity_quiz);
			final TextView txtAgain=(TextView)findViewById(R.id.btn_try_again);
			final TextView txtBack=(TextView)findViewById(R.id.btn_quiz_back);
			txtAgain.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					addQuestions();
					Collections.shuffle(questions);
					score=0;
					nextQuestion = 0;
					onClickNext();
					
				}
			});
			txtBack.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			
			
		}
	}
	
	
	public void checkMarkedAnswer() {
		int rid = rg.getCheckedRadioButtonId();
		RadioButton rbtn = (RadioButton) rg.findViewById(rid);
		if(nextQuestion<questions.size()&& rbtn!=null ) {
			
			
			if(rbtn.getText() == questions.get(nextQuestion).answers) {
				Toast.makeText(getApplicationContext(), "Correct",Toast.LENGTH_SHORT).show();	
				score++;
			}
			else {
				Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_quit_quiz, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        
	        case R.id.app_home:
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
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Quiz");
		builder.setMessage("Are You Really Want To Quit ?");
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	               nextQuestion = 15;
	               onClickNext();
	               
	           }
	       });
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	           @Override
			public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	           }
	       });

		AlertDialog al = builder.create();
		al.show();
		
	}
	
	public void home()
	{
		Intent i=new Intent(getApplicationContext(),HomePageActivity.class);
		startActivity(i);
	}

}
