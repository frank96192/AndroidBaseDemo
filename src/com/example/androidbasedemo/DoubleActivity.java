package com.example.androidbasedemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class DoubleActivity extends Activity {
	private static final String TAG = "DoubleActivity";
	private TextView textView;
	private  boolean exit =false;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==1) {
				exit = false;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		textView =(TextView) findViewById(R.id.textView1);
		textView.setText("按两次退出");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onKeyDown==="+event.getKeyCode()+"");
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onKeyUp==="+event.getKeyCode()+"");
		if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
		{
			if(!exit)
			{
				exit =true;
				Toast.makeText(this, "再按一次就退出应用", 0).show();
				handler.sendEmptyMessageDelayed(1, 2000);
				return true;
			}
		}
		return super.onKeyUp(keyCode, event);
	}
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "dispatchKeyEvent==="+event.getKeyCode()+"");
		return super.dispatchKeyEvent(event);
	}

}
