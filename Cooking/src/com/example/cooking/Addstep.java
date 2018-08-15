package com.example.cooking;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addstep extends Activity{
	Uri[] Images = new Uri[7];
	String[] x = new String[7];
	String[] x0 = new String[7];
	String[] a = new String[7];
	String name = null;
	EditText edittext1,edittext2,edittext3,edittext4,edittext5,edittext6,edittext7,edittext8,edittext9,edittext10,edittext11,edittext12,edittext13,edittext14;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstep);
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		edittext1 = (EditText) findViewById(R.id.editText1);
		edittext2 = (EditText) findViewById(R.id.editText2);
		edittext3 = (EditText) findViewById(R.id.editText3);
		edittext4 = (EditText) findViewById(R.id.editText4);
		edittext5 = (EditText) findViewById(R.id.editText5);
		edittext6 = (EditText) findViewById(R.id.editText6);
		edittext7 = (EditText) findViewById(R.id.editText7);
		edittext8 = (EditText) findViewById(R.id.editText8);
		edittext9 = (EditText) findViewById(R.id.editText9);
		edittext10 = (EditText) findViewById(R.id.editText10);
		edittext11= (EditText) findViewById(R.id.editText11);
		edittext12 = (EditText) findViewById(R.id.editText12);
		edittext13 = (EditText) findViewById(R.id.editText13);
		edittext14 = (EditText) findViewById(R.id.editText14);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Addstep.this,Adddish.class);
				Addstep.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(0);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(1);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(2);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(3);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button7 = (Button) findViewById(R.id.button7);
		button7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(4);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button8 = (Button) findViewById(R.id.button8);
		button8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(5);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button9 = (Button) findViewById(R.id.button9);
		button9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pickImage(6);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Addstep.this,List.class);
				x[0] = edittext1.getText().toString().trim();
				x[1] = edittext2.getText().toString().trim();
				x[2] = edittext3.getText().toString().trim();
				x[3] = edittext4.getText().toString().trim();
				x[4] = edittext5.getText().toString().trim();
				x[5] = edittext6.getText().toString().trim();
				x[6] = edittext7.getText().toString().trim();
				x0[0] = edittext8.getText().toString().trim();
				x0[1] = edittext9.getText().toString().trim();
				x0[2] = edittext10.getText().toString().trim();
				x0[3] = edittext11.getText().toString().trim();
				x0[4] = edittext12.getText().toString().trim();
				x0[5] = edittext13.getText().toString().trim();
				x0[6] = edittext14.getText().toString().trim();
				int x1 = 0;
				for(int i=0;i<7;i++)
				{
					try{
					a[i] = Images[i].toString();
					}
					catch (Exception e) {
						// TODO: handle exception
					}
					
						//a[i]="empty";
					
					if(x[i].equals(""))
					{
						x1=i;
					}
					
				}
				ContentValues contentValues = new ContentValues();
				DBHelper dbHelper = new DBHelper(getApplicationContext());
				for(int k=0;k<x1;k++)
				{
					contentValues.put("step", x[k]);
					contentValues.put("uri", a[k]);
					contentValues.put("time", x0[k]);
					contentValues.put("name", name);
					dbHelper.insert(contentValues,2);
				}
				dbHelper.close();
				Addstep.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void pickImage(int number){
		Intent intent  = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setData(MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(intent, number);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		 super.onActivityResult(requestCode, resultCode, data);
		 if(resultCode==RESULT_CANCELED){
			 return;
		 }
		try{
			Images[requestCode] = data.getData();
			Toast.makeText(Addstep.this, "success", Toast.LENGTH_SHORT).show();
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
