package com.example.androidbasedemo.applist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class TestActivity extends Activity{
	private SharedPreferences mySharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mySharedPreferences= getSharedPreferences("login_infor", 
				 Context.MODE_PRIVATE);
		String name =mySharedPreferences.getString("password_login", ""); 
		//System.out.println(name+"------------");
		if(!name.equals(""))
		{
			startActivity(new Intent(TestActivity.this, VerificationActivity.class));
			finish();
		}else
		{
			startActivity(new Intent(TestActivity.this, LoginActivity.class));
			finish();
		}
		
	}

}
