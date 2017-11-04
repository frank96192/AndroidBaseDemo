package com.example.androidbasedemo.applist;

import java.util.HashMap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyInstalledReceiver extends BroadcastReceiver {
	 private HashMap<String, String> map;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) { // install
			String packageName = intent.getDataString();
			String temp=	packageName.substring(packageName.lastIndexOf(":")+1,packageName.length());
			Log.i("homer", "安装了:--------"+packageName);
			map =new HashMap<String, String>();
			Toast.makeText(context, "-------"+temp, 0).show();
			map.put("install", temp);
			 for (String key : map.keySet())
			 {
				 System.out.println("key= "+ key + " and value= " + map.get(key));
			 }
			android.provider.Settings.System.putString(context.getContentResolver(), "temp", temp);
			 AppsDao appsDao =new AppsDao(context);
			 if(temp!=null)
			 {
				 //appsDao.delete(temp);
				 new Utils(context).updateAllappInfor();
			 }
		}
		else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) { // uninstall
			String packageName = intent.getDataString();
			String temp=	packageName.substring(packageName.lastIndexOf(":")+1,packageName.length());
			Log.i("homer", "卸载了:-------------"+temp);
			System.out.println(temp);
			 AppsDao appsDao =new AppsDao(context);
			 if(temp!=null)
			 {
				 appsDao.delete(temp);
			 }
		}
	}

}
