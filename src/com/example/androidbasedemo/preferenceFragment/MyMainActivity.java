package com.example.androidbasedemo.preferenceFragment;



import com.example.androidbasedemo.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MyMainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferencefragment);
		//º”‘ÿprefrenceFragment
		FragmentManager manager =getFragmentManager();
		FragmentTransaction beginTransaction = manager.beginTransaction();
		MyPrefFragment myPrefFragment = new MyPrefFragment();
		beginTransaction.add(R.id.prefFragment, myPrefFragment);
		beginTransaction.commit();
	}
}
