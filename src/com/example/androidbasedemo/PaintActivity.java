package com.example.androidbasedemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PaintActivity extends Activity {
	private ImageView imageView;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paint_layout);
		
		imageView =(ImageView) findViewById(R.id.imageView1);
		paint =new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		
		bitmap =Bitmap.createBitmap(600, 900, Config.ARGB_8888);
		canvas =new Canvas(bitmap);
		canvas.drawColor(Color.GRAY);//画布背景
		
		imageView.setOnTouchListener(new OnTouchListener() {
			//开始的位置
			int startX;
			int startY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX =(int) event.getX();
					startY =(int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					int newX=(int) event.getX();
					int newY=(int) event.getY();
					canvas.drawLine(startX, startY, newX, newY, paint);
					startX =(int) event.getX();
					startY =(int) event.getY();
					
					imageView.setImageBitmap(bitmap);
					break;
				case MotionEvent.ACTION_UP:
					break;

				default:
					break;
				}
				return true;
			}
		});
	}
	
	public void save(View v)
	{
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			try {
				String path=Environment.getExternalStorageDirectory().getPath();
				File f =new File(path, System.currentTimeMillis()+".jpg");
				OutputStream fos = new FileOutputStream(f);
				bitmap.compress(CompressFormat.JPEG, 100, fos);
				fos.close();
				Toast.makeText(this, "保存图片成功", Toast.LENGTH_SHORT).show();
				//发一个广播 重新挂载sd卡
				Intent intent =new Intent();
				intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
				intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
				sendBroadcast(intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
