package com.example.androidbasedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ViewPagerActivity extends Activity {
	private ViewPager viewPager;
	private TextView textView;
	private LinearLayout layout_point;
	//图片资源ID
	private int ids[] ={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,
			R.drawable.p5,R.drawable.p6,R.drawable.p7};
	//图片标题集合
	private String imagetitle[]={
			"西川结衣1",
            "西川结衣2",
            "西川结衣3",
            "西川结衣4",
            "西川结衣5",
            "西川结衣6",
            "西川结衣7",
	};
	//图片集合
	private List<ImageView> imageViewslist;
	/**
     * 上一次高亮显示下表位置
     */
    private int preposition =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpage_layout);
		viewPager =(ViewPager) findViewById(R.id.viewpage);
		textView =(TextView) findViewById(R.id.tv_title);
		layout_point =(LinearLayout) findViewById(R.id.ll_point_group);
		
		imageViewslist =new ArrayList<ImageView>();
		for(int i=0;i<ids.length;i++)
		{
			ImageView imageView =new ImageView(this);
			imageView.setBackgroundResource(ids[i]);
			//添加到集合中
			imageViewslist.add(imageView);
			
			 //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams layoutParams =new LayoutParams(8, 8);
            if(i==0)
            {
            	point.setEnabled(true);
            }else
            {
            	point.setEnabled(false);
            	layoutParams.leftMargin=10;
            }
            point.setLayoutParams(layoutParams);
            layout_point.addView(point);
            
		}
		textView.setText(imagetitle[preposition]);
		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		//设置中间值
		int currentItem=Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViewslist.size();
		viewPager.setCurrentItem(currentItem);
		
	}
	
	class MyOnPageChangeListener implements OnPageChangeListener
	{

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
          /* textView.setText(imagetitle[position]);
			
			layout_point.getChildAt(preposition).setEnabled(false);
			layout_point.getChildAt(position).setEnabled(true);
			
			preposition =position;*/
			
			int tempposition =position%imageViewslist.size();
			 textView.setText(imagetitle[tempposition]);
			 layout_point.getChildAt(preposition).setEnabled(false);
			layout_point.getChildAt(tempposition).setEnabled(true);
				
			preposition =tempposition;
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class MyPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;//imageViewslist.size();
		}

		/**
         * 比较view和object是否同一个实例
         * @param view 页面
         * @param object  这个方法instantiateItem返回的结果
         * @return
         */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view ==object;
		}

		 /**
         * 相当于getView方法
         * @param container ViewPager自身
         * @param position 当前实例化页面的位置
         * @return
         */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			//return super.instantiateItem(container, position);
			ImageView imageView = imageViewslist.get(position%imageViewslist.size());
			container.addView(imageView);
			return imageView;
		}

		/**
         * 释放资源
         * @param container viewpager
         * @param position 要释放的位置
         * @param object 要释放的页面
         */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
		
		
		
	}

}
