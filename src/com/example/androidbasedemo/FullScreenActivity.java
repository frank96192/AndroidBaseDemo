package com.example.androidbasedemo;

import android.app.Activity;
import android.os.Bundle;

public class FullScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);//�����ޱ���
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ȫ��
		setContentView(R.layout.fullscreen_layout);
	}
}
