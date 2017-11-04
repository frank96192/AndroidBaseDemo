package com.example.androidbasedemo.applist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidbasedemo.R;

public class LoginActivity extends BaseActivity implements OnClickListener {
	private EditText editText;
	private Button cancelBt,nextBt;
	private SharedPreferences mySharedPreferences;
	private  SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		
		editText =(EditText) findViewById(R.id.password_et);
		cancelBt =(Button) findViewById(R.id.cancel_bt);
		nextBt =(Button) findViewById(R.id.next_bt);
		nextBt.setEnabled(false);
		nextBt.setOnClickListener(this);
		cancelBt.setOnClickListener(this);
		
		 mySharedPreferences= getSharedPreferences("login_infor", 
				 Context.MODE_PRIVATE); 
		 editor = mySharedPreferences.edit(); 
		
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			//	System.out.println("onTextChanged===="+s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				//System.out.println("beforeTextChanged===="+s);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			//	System.out.println("afterTextChanged===="+s);
				if(s.toString().length()>3)
				{
					nextBt.setEnabled(true);
				}else
				{
					nextBt.setEnabled(false);
				}
			}
		});
	}
	
	public  String getTilte()
	 {
		return getString(R.string.logi_title);
		 
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.main, menu);
		// menu.getItem(0).setVisible(false);
		menu.findItem(R.id.item1).setVisible(false);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cancel_bt:
			finish();
			break;
		case R.id.next_bt:
			editor.putString("password_login", editText.getText().toString());
			editor.putInt("password_length",editText.getText().toString().length()); 
			editor.commit(); 
			
			startActivity(new Intent(LoginActivity.this, ConfirmActivity.class));
			finish();
			break;

		default:
			break;
		}
	}

}

