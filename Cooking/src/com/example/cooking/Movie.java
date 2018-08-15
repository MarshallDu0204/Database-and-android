package com.example.cooking;

import java.io.File;
import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils.SimpleStringSplitter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Movie extends Activity{
	Vibrator vibrator;
	int i1=2;
	int i = 0;
	String x = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie);
		Intent intent = getIntent();
		i = intent.getIntExtra("number", 0);
		x = intent.getStringExtra("set");
		VideoView videoView = (VideoView) findViewById(R.id.videoView1);
		videoView.setMediaController(new MediaController(this));
		TextView textView = (TextView) findViewById(R.id.textView2);
		textView.setText("2");
		String[] a = {"http://pic.ibaotu.com/00/33/61/47D888piCwkr.mp4","http://pic.ibaotu.com/00/34/20/26A888piCJMy.mp4","http://pic.ibaotu.com/00/36/24/40F888piCAHp.mp4", "http://pic.ibaotu.com/00/33/79/4888piCY888piCcIM.mp4"};
		//String src = "android.resource://" + getPackageName() + "/"+com.example.cooking.R.raw.a1; 
		String src="";
		if(x.equals("false"))
		{
			src = a[i];
		}
		else{
			if(i==0)
			{
				src = "android.resource://" + getPackageName() + "/"+com.example.cooking.R.raw.a1; 
			}
			else if(i==1){
				src = "android.resource://" + getPackageName() + "/"+com.example.cooking.R.raw.a2; 
			}
			else{
				src = "android.resource://" + getPackageName() + "/"+com.example.cooking.R.raw.a3; 
			}
			
		}
		
		Uri uri = Uri.parse(src);
		videoView.setVideoURI(uri);
		videoView.start();
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				startRemind(hour, minute+i1,x,i);
				vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				long[] pattern = {i1*60*1000,1000};
				vibrator.vibrate(pattern, -1);
				Toast.makeText(Movie.this, "success", Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Movie.this,pizza1.class);
				Movie.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopRemind(0);
				vibrator.cancel();
				Toast.makeText(Movie.this,"cancel" , Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				
			}
		});
	}
	
void startRemind(int hour,int minute,String x,int i){
		
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
	    Intent intent = new Intent(Movie.this, receiver.class);
	    intent.putExtra("set", x);
	    intent.putExtra("number", i);
	    PendingIntent pendingIntent = PendingIntent.getBroadcast(Movie.this, 0, intent,0);
	    AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	
	 private void stopRemind(int i){
		 
	     Intent intent = new Intent(Movie.this, receiver.class);
	     PendingIntent pendingIntent = PendingIntent.getBroadcast(Movie.this, 0, intent, i);
	     AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	     alarmManager.cancel(pendingIntent);
	 }
}
