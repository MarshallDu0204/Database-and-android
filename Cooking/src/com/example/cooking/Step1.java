package com.example.cooking;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Step1 extends Activity{
	String[] x = new String[7];
	String[] x0 = new String[7];
	java.util.List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
	String name=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.step1);
		Intent intent = getIntent();
		name=intent.getStringExtra("name");
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		Cursor cursor = dbHelper.query(2, name);
		int i=0;
		while(cursor.moveToNext()){
			String step = cursor.getString(cursor.getColumnIndex("step"));
			String uri = cursor.getString(cursor.getColumnIndex("uri"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			addList(i+1, step);
			x[i]=uri;
			x0[i] = time;
			i++;
		}
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list1,R.layout.list1,new String[]{"number","step"},new int[]{R.id.textView1,R.id.textView2});
		listView.setAdapter(simpleAdapter);
		AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(Step1.this,Graph.class);
				int i1=(int) id;
				intent.putExtra("uri", x[i1]);
				intent.putExtra("name", name);
				intent.putExtra("time", x0[i1]);
				Step1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		};
		listView.setOnItemClickListener(itemClickListener);
		
		Button button =(Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Step1.this,Dish1.class);
				intent.putExtra("name", name);
				// TODO Auto-generated method stub
				Step1.this.startActivity(intent);
			}
		});
	}
	private void addList(int a,String b){
		Map<String,Object> aMap = new HashMap<String, Object>();
		aMap.put("number", a);
		aMap.put("step", b);
		list1.add(aMap);
	}
}
