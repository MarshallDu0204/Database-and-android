package com.example.cooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Dish1 extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	String name = null;
	int i1;
	DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dish1);
		Intent intent = getIntent();
		i1 = intent.getIntExtra("id",0);
		dbHelper = new DBHelper(getApplicationContext());
		Cursor cursor = dbHelper.query1();
		int i=0;
		//cursor.moveToFirst();
		while(cursor.moveToNext()){
			if(i==i1)
			{
				name = cursor.getString(cursor.getColumnIndex("name"));
			}
			i++;
		}
		Cursor cursor1 = dbHelper.query(1,name);
		while(cursor1.moveToNext())
		{
			String ingredients = cursor1.getString(cursor1.getColumnIndex("ingredients"));
			String quantity = cursor1.getString(cursor1.getColumnIndex("quantity"));
			addList(ingredients, quantity);
		}
		ListView listView1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.list1,new String[]{"ingredients","quantity"},new int[]{R.id.textView1,R.id.textView2});
		listView1.setAdapter(simpleAdapter);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Dish1.this,List.class);
				Dish1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Dish1.this,Step1.class);
				intent.putExtra("name", name);
				Dish1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Dish1.this,List.class);
				dbHelper.delete(name);
				dbHelper.close();
				Dish1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper dbHelper = new DBHelper(getApplicationContext());
				Cursor cursor2 = dbHelper.query(2, name);
				String b = "look at my new dish: "+name;
				int i=0;
				String a = null;
				while(cursor2.moveToNext())
				{
					a = cursor2.getString(cursor2.getColumnIndex("uri"));
					if(i==0)
					{
						break;
					}
				}
				Uri uri = null;
				try{
					uri = Uri.parse(a);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				share(b, uri);
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void addList(String a,String b){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("ingredients", a);
		aMap.put("quantity", b);
		list1.add(aMap);
	}
	
	private void share(String a,Uri uri){
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		if(uri!=null)
		{
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/*");
		}
		else {
			shareIntent.setType("text/plain");
			
		}
		shareIntent.putExtra(Intent.EXTRA_TEXT, a);
		startActivity(Intent.createChooser(shareIntent, "share"));
	}
}
