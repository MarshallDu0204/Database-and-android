package com.example.cooking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.apache.http.entity.InputStreamEntity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class List extends Activity{
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		
		Cursor cursor = dbHelper.query1();
		while(cursor.moveToNext())
		{
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			addList(name, time);
		}
		
		
		//cursor.close();
		ListView listView1 = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter1 = new SimpleAdapter(this, getData(),R.layout.dish, new String[]{"text","time","img"}, new int[]{R.id.text1,R.id.text2,R.id.img});
		listView1.setAdapter(simpleAdapter1);
		
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(position==0)
				{
					Intent intent = new Intent(List.this,Pizza.class);
					List.this.startActivity(intent);
				}
				else if(position==1)
				{
					Intent intent = new Intent(List.this,Pasta.class);
					List.this.startActivity(intent);
				}
				
				// TODO Auto-generated method stub
				
			}
		};
		listView1.setOnItemClickListener(itemClickListener);
		
		ListView listView2 = (ListView) findViewById(R.id.listView2);
		SimpleAdapter simpleAdapter2 = new SimpleAdapter(this,list1,R.layout.list1,new String[]{"name","time"},new int[]{R.id.textView1,R.id.textView2});
		listView2.setAdapter(simpleAdapter2);
		
		AdapterView.OnItemClickListener itemClickListener2 = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(List.this,Dish1.class);
				int i = (int) id;
				intent.putExtra("id", i);
				List.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		};
		listView2.setOnItemClickListener(itemClickListener2);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(List.this,MainActivity.class);
				List.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		Button button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(List.this,Adddish.class);
				List.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	private java.util.List<Map<String, Object>> getData() {
		java.util.List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("text", "Pizza");
		aMap.put("time", "40min");
		aMap.put("img", R.drawable.list_1);
		list.add(aMap);
		
		Map<String,Object> bMap = new HashMap<String, Object>();
		bMap.put("text", "pasta");
		bMap.put("time", "20min");
		bMap.put("img", R.drawable.list_2);
		list.add(bMap);
		
		return list;
		
	}
	
	private void addList(String a,Object b){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("name", a);
		aMap.put("time", b);
		list1.add(aMap);
	}
}
