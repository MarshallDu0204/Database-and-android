package com.example.cooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.UrlQuerySanitizer.ValueSanitizer;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	public static String name = "course.db";
	private static int version = 1;
	
	private SQLiteDatabase database;

	public DBHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.database=db;
		String sql = "create table dish(ingredients varchar(100) ,quantity varchar(30),name varchar(30),primary key (ingredients, name))";
		String sql1 = "create table step(step varchar(150) primary key,uri varchar(50),time varchar(30),name varchar(30))";
		String sql2 = "create table name(name varchar(30) primary key,time varchar(20))";
		database.execSQL(sql);
		database.execSQL(sql1);
		database.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void insert(ContentValues values,int number){
		SQLiteDatabase database = getWritableDatabase();
		if(number==1)
		{
			database.insert("dish", null, values);
		}
		else if(number==2){
			database.insert("step", null, values);
		}
		else {
			database.insert("name", null, values);
		}
		database.close();
	}
	
	public void delete(String name){
		if(database==null)
		{
			database=getWritableDatabase();
			database.delete("dish", "name=?", new String[]{name});
			database.delete("step","name=?", new String[]{name});
			database.delete("name", "name=?", new String[]{name});
		}
	}
	
	public Cursor query(int number,String name) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if(number==1)
		{
			cursor = database.query("dish", null, "name=?", new String[]{name}, null, null, null);
		}
		else if(number==2){
			cursor = database.query("step", null, "name=?", new String[]{name}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query1() {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		cursor = database.query("name", null, null,null, null, null, null);
		return cursor;
	}
	
	public void close(){
		if(database!=null)
		{
			database.close();
		}
	}

}
