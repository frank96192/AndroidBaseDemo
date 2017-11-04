package com.example.androidbasedemo.picture;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidbasedemo.R;

public class GetPictureActivity extends Activity {
	private ImageView imageView;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_layout);
		
		imageView =(ImageView) findViewById(R.id.imageView1);
	}

	
	public void get1(View v)
	{
		dialog=ProgressDialog.show(this, null, "���ڼ�����...");
		new Thread()
		{
			public void run()
			{
				try {
					//1���õ�·��
					String path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507271177830&di=538b3bb08f794042ec37710ae1ad2068&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fimages%2F20170823%2F3bb023c69efc45ffb84973e449217e17.jpeg";
					//2.����url
					URL url =new URL(path);
					//3.������
					HttpURLConnection con=	(HttpURLConnection) url.openConnection();
					//4.���ò���
					con.setReadTimeout(2000);
					con.setConnectTimeout(2500);
					//5.���ӷ�����
					con.connect();
					//6�õ���Ӧ��
					int responseCode = con.getResponseCode();
					if(responseCode ==200)
					{
						//�õ���Ӧ�ı�
						InputStream is = con.getInputStream();
						final Bitmap decodeStream = BitmapFactory.decodeStream(is);
						is.close();
						//���̸߳���ui
						runOnUiThread( new Runnable() {
							public void run() {
								imageView.setImageBitmap(decodeStream);
								dialog.dismiss();
							}
						});
						
					}
					//7.�Ͽ�����
					con.disconnect();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dialog.dismiss();
				}
			}
		}.start();
	}
	
	//����2
	public void get2(View v)
	{
		dialog =ProgressDialog.show(this, null, "���ڼ��ء�������");
		new Thread()
		{
			public void run()		
			{
				try {
					//��ȡͼƬ��ַ
					String path="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507271177829&di=5d8d8a8b81dc7b1845770699bd37fe93&imgtype=0&src=http%3A%2F%2Fbaiducdn.pig66.com%2Fuploadfile%2F2017%2F0410%2F20170410113106581.jpg";
					//����url
					URL url =new URL(path);
					//������
					 HttpURLConnection con = (HttpURLConnection) url.openConnection();
					 //���ò���
					 con.setReadTimeout(2000);
					 con.setConnectTimeout(3000);
					 //�õ�����
					 con.connect();
					 int responseCode = con.getResponseCode();
					 if(responseCode ==200)
					 {
						 
						//7.�õ���Ӧ�ı�
							InputStream is = con.getInputStream();
							//����ByteArrayOutputStream ת�ɶ������ļ�
							final byte[] byteArray = Tools2(is);
							
							runOnUiThread(new Runnable() {
								public void run() {
									Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
									
									imageView.setImageBitmap(image);
									dialog.dismiss();
								}
							});
					 }
					//7.�Ͽ�����
						con.disconnect();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dialog.dismiss();
				}
			}

			
		}.start();
	}
	
	public void get3(View v)
	{
		dialog =ProgressDialog.show(this, null, "�����ڼ���...");
		new Thread()
		{
			public void run() 
			{
				try {
					//1.�õ�path·��
					String path ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1507271177826&di=4aea10421cd203c1a670da043b9d5af1&imgtype=0&src=http%3A%2F%2Fimg2.utuku.china.com%2F640x0%2Fent%2F20170908%2Fabb4d446-fdcb-4073-807f-d4089177395a.jpg";
					//2.����httpclient
					HttpClient client = new DefaultHttpClient();
					//3���ò���
					HttpParams params = client.getParams();
					HttpConnectionParams.setSoTimeout(params, 2000);
					HttpConnectionParams.setConnectionTimeout(params, 2000);
					//4.����httpGet ����
					HttpGet httpGet =new HttpGet(path);
					//5.ִ��httpGet �õ���Ӧ����
					HttpResponse response = client.execute(httpGet);
					int statusCode = response.getStatusLine().getStatusCode();
					if(statusCode ==200)
					{
						HttpEntity entity = response.getEntity();
						final byte[] byteArray = EntityUtils.toByteArray(entity);
						runOnUiThread(new Runnable() {
							public void run() {
								Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
								imageView.setImageBitmap(decodeByteArray);
								dialog.dismiss();
							}
						});
					}
					//�Ͽ�����
					client.getConnectionManager().shutdown();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dialog.dismiss();
				}
			}
		}.start();
		
	}
	
	private byte[] Tools2(InputStream is) throws IOException {
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		int len =-1;
		byte b [] =new byte[1024];
		while((len=is.read(b))!=-1)
		{
			baos.write(b, 0, len);
		}
		
		final byte[] byteArray = baos.toByteArray();
		
		baos.close();
		is.close();
		return byteArray;
	}
	
	
}
