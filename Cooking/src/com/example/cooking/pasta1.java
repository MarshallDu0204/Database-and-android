package com.example.cooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class pasta1 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pasta1);
		ListView listView = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.dishlist,new String[]{"id","text"},new int[]{R.id.text1,R.id.text2});
		listView.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				
					Intent intent = new Intent(pasta1.this,Movie.class);
					int number = (int)id;
					intent.putExtra("number", number);
					intent.putExtra("set", "true");
					pasta1.this.startActivity(intent);
					
			}
		};
		listView.setOnItemClickListener(itemClickListener);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(pasta1.this,Pizza.class);
				pasta1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private List<Map<String,String>> getData(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("id", "1");
		a.put("text", "Heat a frying pan over medium-high heat. Add half the bacon and cook for 2 mins each side or until crisp. ");
		list.add(a);
		
		Map<String, String> b = new HashMap<String, String>();
		b.put("id", "2");
		b.put("text", " Transfer to a plate lined with a paper towel. Repeat with remaining bacon. Coarsely chop and set aside. ");
		list.add(b);
		
		Map<String, String> c = new HashMap<String, String>();
		c.put("id", "3");
		c.put("text", "Cook spaghetti following packet directions, until al dente. Drain well. ");
		list.add(c);
		
		return list;
	}
}
