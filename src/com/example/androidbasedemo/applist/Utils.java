package com.example.androidbasedemo.applist;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class Utils {
	private Context mContext;
	public Utils(Context context)
	{
		//super();
		mContext =context;
	}
	
	public void updateAllappInfor() {
		// TODO Auto-generated method stub
		PackageManager pm = mContext.getPackageManager();
		 Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);  
	        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	        // 通过查询，获得所有ResolveInfo对象.  
	        List<ResolveInfo> resolveInfos = pm  
	                .queryIntentActivities(mainIntent, PackageManager.GET_UNINSTALLED_PACKAGES);  //GET_UNINSTALLED_PACKAGES
	     // 调用系统排序 ， 根据name排序  
	        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
	        Collections.sort(resolveInfos,new ResolveInfo.DisplayNameComparator(pm));  
	        int SIZE=resolveInfos.size();
	        //if(mlistAppInfo!=null)
	        //{
	        	for(ResolveInfo info :resolveInfos)
	        	{
	        		// 获得应用程序的Label
	        		 String appLabel = (String) info.loadLabel(pm); //
	        		// 获得应用程序的包名  
	        		 String pkgName = info.activityInfo.packageName; 
	        		 // 获得应用程序图标
	        		 Drawable icon = info.loadIcon(pm);
	        		String ss= icon.toString();
	        		 //appInfo.flags & ApplicationInfo.FLAG_SYSTEM
	        		 //系统app id
	        		//System.out.println(ss+"--------");
	        		 int app_id =info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM;
	        		 int tempId =info.activityInfo.applicationInfo.uid;
	        		// System.out.println(tempId+"xxxxxxxxxxx");
	        		 //// 创建一个AppInfo对象，并赋值  
	        		 AppInfo appInfo =new AppInfo();
	        	//	 appInfo.setAppLabel(appLabel);
	        		 //appInfo.setPkgname(pkgName);
	        		 //	        		 
	        		// mlistAppInfo.add(appInfo);
	        		 
	        		//获取cmp------------------
	        		 
	        		 String ClassName = info.activityInfo.name;
	                 String packageName = info.activityInfo.packageName;
	                 String cmpName=   packageName+"/"+"."+ClassName.substring(packageName.length()+1, ClassName.length());
	        		 
	        		 
	        		 
	        		 AppsDao appsDao =new AppsDao(mContext);
	        		// appsDao.findAll();
	        		 //appsDao.add(pkgName, 0,icon);
	        		String str= android.provider.Settings.System.getString(mContext.getContentResolver(), "temp");
	        		//System.out.println(str.equals("")+"----------");
	        		
	        		if(str!=null&&info.activityInfo.packageName.equals(str))
	        		{
	        			if(!str.equals(new AppsDao(mContext).findByName(str)))
	        			{
	        				 appsDao.add(str,appLabel,cmpName, 0, icon);
	        			}
	        			
	        			//android.provider.Settings.System.putString(mContext.getContentResolver(), "temp","");
	        		}
	        		
	        		 
	        	//	 appsDao.update(pkgName, 0, icon);
	        		 
	        	}
	       // }
	}
	
	//当语言改变时 更新：
	public void updateBylanguageChagedAllapp()
	{
		// TODO Auto-generated method stub
		PackageManager pm = mContext.getPackageManager();
		 Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);  
	        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	        // 通过查询，获得所有ResolveInfo对象.  
	        List<ResolveInfo> resolveInfos = pm  
	                .queryIntentActivities(mainIntent, PackageManager.GET_UNINSTALLED_PACKAGES);  //GET_UNINSTALLED_PACKAGES
	     // 调用系统排序 ， 根据name排序  
	        // 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
	        Collections.sort(resolveInfos,new ResolveInfo.DisplayNameComparator(pm));  
	        int SIZE=resolveInfos.size();
	        //if(mlistAppInfo!=null)
	        //{
	        	for(ResolveInfo info :resolveInfos)
	        	{
	        		// 获得应用程序的Label
	        		 String appLabel = (String) info.loadLabel(pm); //
	        		// 获得应用程序的包名  
	        		 String pkgName = info.activityInfo.packageName; 
	        		 // 获得应用程序图标
	        		 Drawable icon = info.loadIcon(pm);
	        		String ss= icon.toString();
	        		
	        		 //获取cmp------------------
	        		 
	        		 String ClassName = info.activityInfo.name;
	                 String packageName = info.activityInfo.packageName;
	                 String cmpName=   packageName+"/"+"."+ClassName.substring(packageName.length()+1, ClassName.length());
	        		
	        		 //appInfo.flags & ApplicationInfo.FLAG_SYSTEM
	        		 //系统app id
	        		//System.out.println(ss+"--------");
	        		 int app_id =info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM;
	        		 int tempId =info.activityInfo.applicationInfo.uid;
	        		// System.out.println(tempId+"xxxxxxxxxxx");
	        		 //// 创建一个AppInfo对象，并赋值  
	        		 AppInfo appInfo =new AppInfo();
	        	
	        		 
	        		 AppsDao appsDao =new AppsDao(mContext);
	        		
	        	//	 if(pkgName.equals(new AppsDao(mContext).findByName(pkgName)))
	        		// {
	        			 appsDao.updateLabelByCmp(cmpName, appLabel);
	        			// System.out.println("语言改变了后:"+appLabel);
	        		// }
	        		
	        		 
	        	//	 appsDao.update(pkgName, 0, icon);
	        		 
	        	}
	       // }
	}

}
