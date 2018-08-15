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

public class pizza1 extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pizza1);
		ListView listView = (ListView) findViewById(R.id.listView1);
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.dishlist,new String[]{"id","text"},new int[]{R.id.text1,R.id.text2});
		listView.setAdapter(simpleAdapter);
		
		AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				
					Intent intent = new Intent(pizza1.this,Movie.class);
					int number = (int)id;
					intent.putExtra("number", number);
					intent.putExtra("set", "false");
					pizza1.this.startActivity(intent);
					
			}
		};
		listView.setOnItemClickListener(itemClickListener);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(pizza1.this,Pizza.class);
				pizza1.this.startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private List<Map<String, String>> getData(){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Map<String, String> a = new HashMap<String, String>();
		a.put("id", "1");
		a.put("text", "In the large bowl of a heavy duty electric mixer (such as a Kitchen Aid), add the warm water. Sprinkle on the yeast and let sit for 5 minutes until the yeast is dissolved. Stir to dissolve completely if needed at the end of 5 minutes.");
		list.add(a);
		
		Map<String, String> b = new HashMap<String, String>();
		b.put("id", "2");
		b.put("text", "Attach a mixing paddle to the mixer. Mix in the olive oil, flour, salt and sugar on low speed for about a minute. Remove the mixing paddle and replace with a dough hook. Knead using the mixer and dough hook, on low to medium speed, until the dough is smooth and elastic, about 10 minutes");
		list.add(b);
		
		Map<String, String> c = new HashMap<String, String>();
		c.put("id", "3");
		c.put("text", " Place ball of dough in a bowl that has been coated lightly with olive oil. Turn the dough around in the bowl so that it gets coated with the oil. Cover with plastic wrap. Let sit in a warm place (75-85°„F) until it doubles in size, about 1 to 1 1/2 hours (or several hours longer, a longer rise will improve the flavor)");
		list.add(c);
		
		Map<String, String> d = new HashMap<String, String>();
		d.put("id", "4");
		d.put("text", " Lightly sprinkle your pizza peel (or flat baking sheet) with corn meal. Transfer one prepared flattened dough to the pizza peel.");
		list.add(d);
		return list;
	}
}
