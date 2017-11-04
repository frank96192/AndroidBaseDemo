package com.example.androidbasedemo.applist;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class TestService extends Service {

	MyInstalledReceiver installedReceiver;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if(installedReceiver==null)
		{
			installedReceiver = new MyInstalledReceiver();
			 
			 IntentFilter filter = new IntentFilter();

			  filter.addAction("android.intent.action.PACKAGE_ADDED");
			  filter.addAction("android.intent.action.PACKAGE_REMOVED");
			 // filter.addAction(Intent.ACTION_LOCALE_CHANGED);
			  filter.addDataScheme("package");

			  this.registerReceiver(installedReceiver, filter);	
		}
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if(installedReceiver != null) {
			   this.unregisterReceiver(installedReceiver);
			  }
		startService(new Intent(this, TestService.class));
		super.onDestroy();
	}
}
