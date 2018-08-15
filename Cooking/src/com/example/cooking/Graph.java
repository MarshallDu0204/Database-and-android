package com.example.cooking;

import java.util.Calendar;

import android.R.string;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Graph extends Activity{
	Vibrator vibrator;
	String uri,name,time;
	Uri aUri = null;
	int i=0;
	TextView textView2;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph);
		Intent intent = getIntent();
		uri = intent.getStringExtra("uri");
		name = intent.getStringExtra("name");
		time = intent.getStringExtra("time");
		try{
			i = Integer.parseInt(time);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		textView2 = (TextView) findViewById(R.id.textView2);
		try{
			textView2.setText(time);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		try{
		aUri = Uri.parse(uri);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		imageView = (ImageView) findViewById(R.id.imageView1);
		try{
		imageView.setImageURI(aUri);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Graph.this,Step1.class);
				intent.putExtra("name", name);
				Graph.this.startActivity(intent);
			
				// TODO Auto-generated method stub
				
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				startRemind(hour, minute+i);
				vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				long[] pattern = {i*1000*60,4000};
				vibrator.vibrate(pattern, -1);
				Toast.makeText(Graph.this, "success", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopRemind(0);
				vibrator.cancel();
				Toast.makeText(Graph.this, "Cancel", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
void startRemind(int hour,int minute){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		long systemTime = System.currentTimeMillis();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    long selectTime = calendar.getTimeInMillis();
	    if(systemTime > selectTime) {
	         calendar.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    Intent intent = new Intent(Graph.this, receiver1.class);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(Graph.this, 0, intent,0);
	    AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	
	 private void stopRemind(int i){
		 
	     Intent intent = new Intent(Graph.this, receiver1.class);
	     PendingIntent pendingIntent = PendingIntent.getBroadcast(Graph.this, 0, intent, i);
	     AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	     alarmManager.cancel(pendingIntent);
	 }
}
