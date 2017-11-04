package com.example.androidbasedemo;

import java.util.ArrayList;
import java.util.List;

import com.example.androidbasedemo.applist.TestActivity;
import com.example.androidbasedemo.picture.GetPictureActivity;
import com.example.androidbasedemo.preferenceFragment.MyMainActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView listView;
	private List<String> list = new ArrayList<String>(); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initlist();
		
		listView =(ListView) findViewById(R.id.listview);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	    listView.setOnItemClickListener(this);
	}

	private void initlist() {
		// TODO Auto-generated method stub
		list.add("全屏");
		list.add("对话框acivity");
		list.add("按两次退出activity");
		list.add("获取网络图片");
		list.add("画板涂鸦");
		list.add("app信息");
		list.add("广告滚动效果");
		list.add("PreferenceFragment");
		list.add("RecyclerView");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			startActivity(new Intent(this, FullScreenActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, DialogActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, DoubleActivity.class));
			break;
		case 3:
			startActivity(new Intent(this, GetPictureActivity.class));
			break;
		case 4:
			startActivity(new Intent(this, PaintActivity.class));
			break;
		case 5:
			startActivity(new Intent(this, TestActivity.class));
			break;
		case 6:
			startActivity(new Intent(this, ViewPagerActivity.class));
			break;
		case 7:
			startActivity(new Intent(this, MyMainActivity.class));
			break;
		

		default:
			break;
		}
		//Toast.makeText(this, list.get(position).toString(), Toast.LENGTH_SHORT).show();
	}

}
