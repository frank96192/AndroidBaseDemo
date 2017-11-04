package com.example.androidbasedemo.applist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.androidbasedemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ApplicationInfoAdapter extends BaseAdapter{
	private List<AppInfo> mlListAppInfos =null;
	private Context mcontext;
	private String[] type;
	private static HashMap<Integer,Boolean> isSelected;//

	public ApplicationInfoAdapter(List<AppInfo> list, Context context) {
		//super();
		this.mlListAppInfos = list;
		this.mcontext = context;
		type = mcontext.getResources().getStringArray(R.array.types);
	//    map =	new HashMap<String,Boolean>();
		isSelected = new HashMap<Integer, Boolean>();
		//initData();
		initData2();
	}
	private void initData()
	{
		for(int i=0;i<mlListAppInfos.size();i++)
		{
			isSelected.put(i, false);
		}
	}
	
	private void initData2()
	{
		ArrayList<AppInfo> findAll = new AppsDao(mcontext).findAll();
		for(int i=0;i<findAll.size();i++)
		{
			isSelected.put(i, findAll.get(i).getStatus()==1?true:false);
		}
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlListAppInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlListAppInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView==null)
		{
			holder =new ViewHolder();
			convertView =View.inflate(mcontext, R.layout.list_app_item, null);
	        holder =new ViewHolder();
	        holder.checkBox =(CheckBox) convertView.findViewById(R.id.cb_item);
	        holder.appId =(TextView) convertView.findViewById(R.id.data_id);
	        holder.tvAppLabel =(TextView) convertView.findViewById(R.id.tvAppLabel);
	        holder.tvPkgName =(TextView) convertView.findViewById(R.id.tvPkgName);
	        holder.appIcon =(ImageView) convertView.findViewById(R.id.imgApp);
	        holder.tvcomponent =(TextView) convertView.findViewById(R.id.tvcmp);
			convertView.setTag(holder);
		}else
		{
			holder =(ViewHolder) convertView.getTag();
		}
		AppInfo appInfo =mlListAppInfos.get(position);
		holder.checkBox.setChecked(getIsSelected().get(position)?true:false);  
		//	holder.checkBox.setChecked(appInfo.getStatus()==1||getIsSelected().get(position)?true:false);  
			//holder.checkBox.setChecked(appInfo.getStatus()==1?true:false);  
		//holder.checkBox.setChecked(appInfo.getStatus()==1?true:false);  
		holder.appId.setText(appInfo.getNumberId()+"");
		holder.tvPkgName.setText(appInfo.getPkgname());
		holder.tvAppLabel.setText(appInfo.getPkglabel());
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		holder.tvcomponent.setText(appInfo.getComponent());
		return convertView;
	}
	
	public static HashMap<Integer,Boolean> getIsSelected() {  
        return isSelected;  
    }  
	
	public static void setIsSelected(HashMap<Integer,Boolean> isSelected) {  
        ApplicationInfoAdapter.isSelected = isSelected;  
    }  
	
	class ViewHolder 
	{
		CheckBox checkBox;
		ImageView appIcon;
		TextView tvAppLabel;
		TextView tvPkgName;
		TextView appId;
		TextView tvcomponent;
		
	}

}
