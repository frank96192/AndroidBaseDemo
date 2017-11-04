package com.example.androidbasedemo.applist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidbasedemo.R;

public class GetlistMainActivity extends BaseActivity implements
		OnItemClickListener {
	private ListView listview = null;
	private List<AppInfo> mlistAppInfo = new ArrayList<AppInfo>();
	private ApplicationInfoAdapter adapter = null;

	private List<AppInfo> apps_save;

	public String getTilte() {
		return getString(R.string.hidd_title);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		listview = (ListView) findViewById(R.id.list_all);
		SharedPreferences setting = getSharedPreferences("ass", 0);
		Boolean user_first = setting.getBoolean("FIRST", true);
		if (user_first) {//
			setting.edit().putBoolean("FIRST", false).commit();
			getAllappInfor();
		} else {
			// updateAllappInfor();
		}
		new Utils(this).updateBylanguageChagedAllapp();
		mlistAppInfo = new AppsDao(this).findAll();
		ArrayList<AppInfo> findSelected = new AppsDao(this).findSelected(1);
		for (AppInfo appInfo : findSelected) {
			//System.out.println("xxxx===" + appInfo.getPkglabel());
		}

		startService(new Intent(this, TestService.class));
		adapter = new ApplicationInfoAdapter(mlistAppInfo, this);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(this);

		apps_save = new ArrayList<AppInfo>();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		AppInfo appInfo = mlistAppInfo.get(position);
		Toast.makeText(this, appInfo.getPkgname(), 0).show();
		CheckBox cb = (CheckBox) view.findViewById(R.id.cb_item);
		cb.setChecked(!cb.isChecked());
		ApplicationInfoAdapter.getIsSelected().put(position, cb.isChecked()); 
		// System.out.println("点击==="+cb.isChecked()+"--vvvvv----"+appInfo.getNumberId());

		// apps_save = new ArrayList<AppInfo>();
		AppInfo ai = new AppInfo(appInfo.getNumberId(), appInfo.getPkgname(),
				appInfo.getPkglabel(),appInfo.getComponent(), appInfo.getAppIcon(),
				cb.isChecked() == true ? 1 : 0);
		apps_save.add(ai);
		// Log.d("cxd", apps_save.size()+"");
		/*
		 * if(cb.isChecked()) { //new AppsDao(this).update(appInfo.getPkgname(),
		 * 1, appInfo.getAppIcon()); apps_save = new ArrayList<AppInfo>();
		 * AppInfo ai =new AppInfo(appInfo.getNumberId(), appInfo.getPkgname(),
		 * appInfo.getAppIcon(), 1); apps_save.add(ai); }else { //new
		 * AppsDao(this).update(appInfo.getPkgname(), 0, appInfo.getAppIcon());
		 * apps_save2 = new ArrayList<AppInfo>(); AppInfo ai =new
		 * AppInfo(appInfo.getNumberId(), appInfo.getPkgname(),
		 * appInfo.getAppIcon(), 0); apps_save2.add(ai); }
		 */
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// new AppsDao(this).DeleteDatabase(this);
		if (apps_save != null) {
			apps_save = null;
		}

	}

	// 保存需要保存的数据
	/*
	 * public void saveCheck(String ID,boolean flag) { //保存shuju
	 * SharedPreferences mySharedPreferences=
	 * getSharedPreferences("test2",MODE_PRIVATE); Editor mEditor
	 * =mySharedPreferences.edit(); mEditor.putBoolean(ID, flag);
	 * mEditor.commit(); }
	 */
	
	private void getAllAppInfor()
	{
		
		
		
		
		
		 Intent AppIntent = new Intent(Intent.ACTION_MAIN, null);

         AppIntent.addCategory(Intent.CATEGORY_LAUNCHER);
         
         List<ResolveInfo> resolveInfos = this
                 .getPackageManager().queryIntentActivities(AppIntent, 0);
         ArrayList<AppInfo> findSelected = new AppsDao(this).findSelected(1);
 		for (AppInfo appInfo : findSelected) {
 			//System.out.println("xxxx===" + appInfo.getPkgname());
 			
 			 for (int i = 0; i < resolveInfos.size(); i++) {
 	             ResolveInfo resolveInfo = resolveInfos.get(i);
 	             String ClassName = resolveInfo.activityInfo.name;
 	             String packageName = resolveInfo.activityInfo.packageName;
 	             
 	             
 	             if(appInfo.getPkgname().equals(packageName)&&appInfo.getStatus()==1)
 	             {
 	            	 System.out.println("隐藏的==="+packageName);
 	             }
 	             //com.android.contacts.activities.PeopleActivity
 	             //com.android.contacts/.activities.PeopleActivity
 	             
 	             //
 	             //com.android.contacts/.activities.PeopleActivity
 	             
 	             Log.e("-------ClassName", ClassName);
 	             Log.e("-----packname", packageName);
 	         // String ss=   packageName+"/"+"."+ClassName.substring(packageName.length()+1, ClassName.length());
 	        //  Log.e("-----ss=======", resolveInfo.activityInfo.parentActivityName.toString());
 	             }    
 		}
         
        
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			saveAndExit();
			getAllAppInfor();
			finish();
			// Toast.makeText(getApplicationContext(), "22222222",
			// Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void saveAndExit()
	{
		if(apps_save!=null)
		{
			for(AppInfo aio:apps_save)
			{
				Log.d("cxd", aio.getPkglabel());
				new AppsDao(this).updateByCmp(aio.getComponent(),  aio.getStatus());//(aio.getPkgname(), aio.getStatus()/*, aio.getAppIcon()*/);
			}
		//	apps_save=null;
		}
		
	}
	
	// 获得所有启动Activity的信息，类似于Launch界面  	
		private void getAllappInfor()
		{
			PackageManager pm = getPackageManager();
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
		        		System.out.println(ss+"--------");
		        		 int app_id =info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM;
		        		 int tempId =info.activityInfo.applicationInfo.uid;
		        		 System.out.println(tempId+"xxxxxxxxxxx");
		        		 
		        		 //获取cmp------------------
		        		 
		        		 String ClassName = info.activityInfo.name;
		                 String packageName = info.activityInfo.packageName;
		                 String cmpName=   packageName+"/"+"."+ClassName.substring(packageName.length()+1, ClassName.length());
		                 
		        		 
		        		 //// 创建一个AppInfo对象，并赋值  
		        		// AppInfo appInfo =new AppInfo();
		        	//	 appInfo.setAppLabel(appLabel);
		        		 //appInfo.setPkgname(pkgName);
		        		 //	        		 
		        		// mlistAppInfo.add(appInfo);
		        		 
		        		 AppsDao appsDao =new AppsDao(this);
		        		 appsDao.add(pkgName,appLabel,cmpName, 0,icon);
		        		 
		        	}
		       // }
		}

}
