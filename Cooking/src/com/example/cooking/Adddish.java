package com.example.cooking;

import org.w3c.dom.Text;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adddish extends Activity{
	EditText editText11,editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText12;
	EditText text1,text2,text3,text4,text5,text6,text7,text8;
	String[] i = new String[8];
	String[] q = new String[8];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddish);
		editText12 = (EditText) findViewById(R.id.editText12);
		editText11 = (EditText) findViewById(R.id.editText11);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		editText5 = (EditText) findViewById(R.id.editText5);
		editText6 = (EditText) findViewById(R.id.editText6);
		editText7 = (EditText) findViewById(R.id.editText7);
		editText8 = (EditText) findViewById(R.id.editText8);
		text1 = (EditText) findViewById(R.id.Text1);
		text2 = (EditText) findViewById(R.id.Text2);
		text3 = (EditText) findViewById(R.id.Text3);
		text4 = (EditText) findViewById(R.id.Text4);
		text5 = (EditText) findViewById(R.id.Text5);
		text6 = (EditText) findViewById(R.id.Text6);
		text7 = (EditText) findViewById(R.id.Text7);
		text8 = (EditText) findViewById(R.id.Text8);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Adddish.this,List.class);
				Adddish.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Adddish.this,Addstep.class);
				String name =editText11.getText().toString().trim();
				
				i[0] = editText1.getText().toString().trim();
				i[1] = editText2.getText().toString().trim();
				i[2] = editText3.getText().toString().trim();
				i[3] = editText4.getText().toString().trim();
				i[4] = editText5.getText().toString().trim();
				i[5] = editText6.getText().toString().trim();
				i[6] = editText7.getText().toString().trim();
				i[7] = editText8.getText().toString().trim();
				
				q[0] = text1.getText().toString().trim();
				q[1] = text2.getText().toString().trim();
				q[2] = text3.getText().toString().trim();
				q[3] = text4.getText().toString().trim();
				q[4] = text5.getText().toString().trim();
				q[5] = text6.getText().toString().trim();
				q[6] = text7.getText().toString().trim();
				q[7] = text8.getText().toString().trim();
				ContentValues contentValues = new ContentValues();
				
				int a = 0;
				for(int k=0;k<8;k++)
				{
					if(i[k].equals(""))
					{
						a=k;
						//Toast.makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();
					}
				}
				
				DBHelper dbHelper = new DBHelper(getApplicationContext());
				for(int j=0;j<8;j++)
				{
					contentValues.put("ingredients", i[j]);
					contentValues.put("quantity", q[j]);
					contentValues.put("name", name);
					dbHelper.insert(contentValues,1);
				}
				String time = editText12.getText().toString().trim();
				ContentValues contentValues1 = new ContentValues();
				contentValues1.put("name", name);
				contentValues1.put("time", time);
				dbHelper.insert(contentValues1,3);
				dbHelper.close();
				intent.putExtra("name", name);
				Adddish.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
}
