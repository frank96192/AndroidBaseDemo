package com.example.androidbasedemo.applist;

import android.graphics.drawable.Drawable;

public class AppInfo {
	
	
	public AppInfo(int numberId, String pkgname, 
			String pkglabel, String component,Drawable appIcon, int status) {
		//super();
		this.numberId = numberId;
		this.pkgname = pkgname;
		this.appIcon = appIcon;
		this.pkglabel = pkglabel;
		this.component = component;
		this.status = status;
	}

	private int numberId;//���
	private String pkgname;//����
	private Drawable appIcon; // ͼ��
	private String pkglabel;//Ӧ������
	private String component;//����+����
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	private int status;//�Ƿ�ѡ�� 1.ѡ�� ��0Ϊ��ѡ��
	
	
	public AppInfo() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getNumberId() {
		return numberId;
	}
	public void setNumberId(int numberId) {
		this.numberId = numberId;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public Drawable getAppIcon() {
		return appIcon;
	}
	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPkglabel() {
		return pkglabel;
	}
	public void setPkglabel(String pkglabel) {
		this.pkglabel = pkglabel;
	}

	@Override
	public String toString() {
		return "AppInfo [numberId=" + numberId + ", pkgname=" + pkgname
				+ ", appIcon=" + appIcon + ", pkglabel=" + pkglabel
				+ ", component=" + component + ", status=" + status + "]";
	}

	
	
	

}
