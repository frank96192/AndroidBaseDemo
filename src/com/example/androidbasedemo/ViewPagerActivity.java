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
	//ͼƬ��ԴID
	private int ids[] ={R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,
			R.drawable.p5,R.drawable.p6,R.drawable.p7};
	//ͼƬ���⼯��
	private String imagetitle[]={
			"��������1",
            "��������2",
            "��������3",
            "��������4",
            "��������5",
            "��������6",
            "��������7",
	};
	//ͼƬ����
	private List<ImageView> imageViewslist;
	/**
     * ��һ�θ�����ʾ�±�λ��
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
			//��ӵ�������
			imageViewslist.add(imageView);
			
			 //��ӵ�
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
		//�����м�ֵ
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
         * �Ƚ�view��object�Ƿ�ͬһ��ʵ��
         * @param view ҳ��
         * @param object  �������instantiateItem���صĽ��
         * @return
         */
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view ==object;
		}

		 /**
         * �൱��getView����
         * @param container ViewPager����
         * @param position ��ǰʵ����ҳ���λ��
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
         * �ͷ���Դ
         * @param container viewpager
         * @param position Ҫ�ͷŵ�λ��
         * @param object Ҫ�ͷŵ�ҳ��
         */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}
		
		
		
	}

}
