package com.example.androidbasedemo.applist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppsSQLiteOpenHelper extends SQLiteOpenHelper {

	public AppsSQLiteOpenHelper(Context context) {
		super(context, "apps.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//String sql = "create table appsInfors(numberid integer primary key autoincrement,pkgname varchar(20),pkglabel varchar(20),status integer,avatar BLOB)";
		String sql = "create table appsInfors(numberid integer primary key autoincrement,pkgname varchar(20),pkglabel varchar(20),cmp varchar(40),status integer,avatar BLOB)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	/** 
	* É¾³ýÊý¾Ý¿â 
	* @param context 
	* @return 
	*/ 
	public boolean deleteDatabase(Context context) { 
	return context.deleteDatabase("apps.db"); 
	} 

}
