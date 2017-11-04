package com.example.androidbasedemo.applist;

import com.example.androidbasedemo.R;

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
import android.widget.Toast;

public class VerificationActivity extends BaseActivity implements OnClickListener {
	private EditText editText;
	private Button cancelBt,okBt;
	private SharedPreferences mySharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verification_layout);
		mySharedPreferences= getSharedPreferences("login_infor", 
				 Context.MODE_PRIVATE);
		
		editText =(EditText) findViewById(R.id.password_et_verify);
		cancelBt =(Button) findViewById(R.id.cancel_bt);
		okBt =(Button) findViewById(R.id.ok_bt);
		okBt.setEnabled(false);
		cancelBt.setOnClickListener(this);
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//System.out.println("onTextChanged===="+s);
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
				String name =mySharedPreferences.getString("password_login", ""); 
				int length =mySharedPreferences.getInt("password_length", 0);
				//System.out.println("afterTextChanged===="+s);
				if(s.toString().length()==length)
				{
					
					
					//Toast.makeText(ConfirmActivity.this, "ok"+name, Toast.LENGTH_SHORT).show();
					if(s.toString().length()==length&&!s.toString().equals(name))
					{
						okBt.setEnabled(false);
						Toast.makeText(VerificationActivity.this, "error", Toast.LENGTH_SHORT).show();
						editText.setText("");
					//	return;
					}else
					{
						okBt.setEnabled(true);
						startActivity(new Intent(VerificationActivity.this, GetlistMainActivity.class));
						finish();
					}
				}else
				{
					okBt.setEnabled(false);
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
			
			   Intent home=new Intent(Intent.ACTION_MAIN);
				home.addCategory(Intent.CATEGORY_HOME);
				startActivity(home);
			 
			break;

		default:
			break;
		}
	}

}