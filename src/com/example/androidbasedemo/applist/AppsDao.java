package com.example.androidbasedemo.applist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class AppsDao {

	private AppsSQLiteOpenHelper helper;
	private Context mContext;
	
	public AppsDao(Context context) {
		super();
		// TODO Auto-generated constructor stub
		mContext =context;
		helper =new AppsSQLiteOpenHelper(context);
	}
	
	public boolean DeleteDatabase(Context context)
	{
		return helper.deleteDatabase(context);
	}
	
	
	/*
	 * 删除favorite 通过 cmp
	 */
	public int deleteFavoratesByCmp(String component) {
		int n =-1;
		String AUTHORITY = "com.android.launcher3.settings"; 
		 Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY  + "/favorites?notify=true");
		// db.execSQL("delete from person where name=?", new Object[] { name });
			 //n= mContext.getContentResolver().delete(CONTENT_URI, "iconPackage=?", new String[] { pkgname });
		    n= mContext.getContentResolver().delete(CONTENT_URI, "intent like ?", new String[] { "%" + component + "%"});
		 
		//System.out.println("delete delete  favorite  ===="+n);
		return n;
	}	

	
	public long add(String pkgname,String pkglabel,String pkgcmp,int status,Drawable drawable) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("pkgname", pkgname);
		values.put("pkglabel", pkglabel);
		values.put("status", status);
		values.put("cmp", pkgcmp);
		values.put("avatar", bitmabToBytes(drawable));
		long id = db.insert("appsInfors", null, values);
		db.close();
		//System.out.println(id+"---------");
		return id;
	}
	
	/**
	 * 查看选中项
	 * 
	 */
	
	public ArrayList<AppInfo> findSelected(int status) {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
		 byte[] imgData=null;
		 Cursor cursor = db.rawQuery("select * from appsInfors where status=?",
					new String[] { status+"" });
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("numberid"));
			String name = cursor.getString(cursor.getColumnIndex("pkgname"));
			String label = cursor.getString(cursor.getColumnIndex("pkglabel"));
			String cmp = cursor.getString(cursor.getColumnIndex("cmp"));
			//int status = cursor.getInt(cursor.getColumnIndex("status"));
			   imgData=cursor.getBlob(cursor.getColumnIndex("avatar"));
			   AppInfo  a = new AppInfo(id,name, label,cmp, new BitmapDrawable(Bytes2Bimap(imgData)), status);
			   apps.add(a);
		}
		cursor.close();
		db.close();
		return apps;

	}
	
	/**
	 * 修改信息
	 * 
	 */
	
	/*public int update(String pkgname,int status,Drawable drawable) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("status", status);
		//values.put("avatar", bitmabToBytes(drawable));
		int number = db.update("appsInfors", values, "pkgname=?",
				new String[] { pkgname });
		db.close();
		return number;
	}*/
	
	
	/**
	 * 修改信息
	 * 
	 */
	
	public int updateByCmp(String component,int status/*,Drawable drawable*/) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("status", status);
		//values.put("avatar", bitmabToBytes(drawable));
		int number = db.update("appsInfors", values, "cmp=?",
				new String[] { component });
		db.close();
		return number;
	}
	
	
	/**
	 * 修改label名称显示
	 * 
	 */
	
	/*public int updateLabel(String pkgname,String label,Drawable drawable) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("pkglabel", label);
		//values.put("avatar", bitmabToBytes(drawable));
		int number = db.update("appsInfors", values, "pkgname=?",
				new String[] { pkgname });
		db.close();
		return number;
	}*/
	
	/**
	 * 修改label名称显示 通过cmp
	 * 
	 */
	
	public int updateLabelByCmp(String component,String label/*,Drawable drawable*/) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("pkglabel", label);
		//values.put("avatar", bitmabToBytes(drawable));
		int number = db.update("appsInfors", values, "cmp=?",
				new String[] { component });
		db.close();
		return number;
	}
	
	/**
	 * 查询
	 * @param name
	 * @return
	 */
	public boolean findByName(String pkgname) {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		Cursor cursor = db.query("appsInfors", null, "pkgname=?",
				new String[] { pkgname }, null, null, null);
		boolean result = cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}
	
	/*
	 * 删除
	 */
	public int delete(String pkgname) {
		SQLiteDatabase db = this.helper.getWritableDatabase();
		// db.execSQL("delete from person where name=?", new Object[] { name });
		int number = db.delete("appsInfors", "pkgname=?", new String[] { pkgname });
		db.close();
		return number;
	}
	
	public ArrayList<AppInfo> findAll() {
		SQLiteDatabase db = this.helper.getReadableDatabase();
		ArrayList<AppInfo> apps = new ArrayList<AppInfo>();
		 byte[] imgData=null;
		 Cursor cursor = db.query(true, "appsInfors", null, null, null, null, null, null, null, null);
		//Cursor cursor = db.query("appsInfors", null, null, null, "pkgname", null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("numberid"));
			String name = cursor.getString(cursor.getColumnIndex("pkgname"));
			String label = cursor.getString(cursor.getColumnIndex("pkglabel"));
			String cmp = cursor.getString(cursor.getColumnIndex("cmp"));
			int status = cursor.getInt(cursor.getColumnIndex("status"));
			   imgData=cursor.getBlob(cursor.getColumnIndex("avatar"));
			   AppInfo  a = new AppInfo(id,name,label,cmp, new BitmapDrawable(Bytes2Bimap(imgData)), status);
			   apps.add(a);
		}
		cursor.close();
		db.close();
		return apps;

	}
	
	
	 private Bitmap Bytes2Bimap(byte[] b){
         if(b.length!=0){

                 return BitmapFactory.decodeByteArray(b, 0, b.length);
         }
         else {
                 return null;
         }

}


	
	
	public static Bitmap drawableToBitmap(Drawable drawable) {

       int w = drawable.getIntrinsicWidth();
       int h = drawable.getIntrinsicHeight();
      // System.out.println("Drawable转Bitmap");
       Bitmap.Config config =
               drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                       : Bitmap.Config.RGB_565;
       Bitmap bitmap = Bitmap.createBitmap(w, h, config);
       //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
       Canvas canvas = new Canvas(bitmap);
       drawable.setBounds(0, 0, w, h);
       drawable.draw(canvas);

       return bitmap;
   }
	
	//图片转为二进制数据
	public byte[] bitmabToBytes(Drawable drawable){
	       //将图片转化为位图
	       Bitmap bitmap = drawableToBitmap(drawable);
	       int size = bitmap.getWidth() * bitmap.getHeight() * 4;
	       //创建一个字节数组输出流,流的大小为size
	       ByteArrayOutputStream baos= new ByteArrayOutputStream(size);
	       try {
	           //设置位图的压缩格式，质量为100%，并放入字节数组输出流中
	           bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	           //将字节数组输出流转化为字节数组byte[]
	           byte[] imagedata = baos.toByteArray();
	           return imagedata;
	       }catch (Exception e){
	       }finally {
	           try {
	               bitmap.recycle();
	               baos.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	       }
	       return new byte[0];
	   }

}
