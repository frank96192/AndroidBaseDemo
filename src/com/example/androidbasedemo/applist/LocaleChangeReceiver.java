package com.example.androidbasedemo.applist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocaleChangeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED))
		{
			Toast.makeText(context, "”Ô—‘∏ƒ±‰¡À---------", Toast.LENGTH_SHORT).show();
			new Utils(context).updateBylanguageChagedAllapp();
		}
	}

}
