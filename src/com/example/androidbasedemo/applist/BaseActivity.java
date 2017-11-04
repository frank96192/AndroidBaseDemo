package com.example.androidbasedemo.applist;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbasedemo.R;

public class BaseActivity extends Activity {

	private ActionBar actionBar;
	private View mCustomView;
	private TextView mtextView;
	private ImageView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		setCustomPopupMenu(true);
		// actionBar=getActionBar();
		// actionBar.show();
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * super.onCreateOptionsMenu(menu); // MenuItem add=menu.add(0,0,0,"add");
	 * MenuItem del=menu.add(0,0,0,"del"); MenuItem save=menu.add(0,0,0,"save");
	 * //ActionBar add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	 * del.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	 * save.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); return true;
	 * MenuInflater inflater = getMenuInflater();
	 * inflater.inflate(R.menu.activity_main, menu); return true; }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();

		// update menu color size ....
		/*
		 * getLayoutInflater().setFactory(new Factory() {
		 * 
		 * @Override public View onCreateView(String name, Context context,
		 * AttributeSet attrs) { System.out.println(name); if
		 * (name.equalsIgnoreCase
		 * ("com.android.internal.view.menu.IconMenuItemView") ||
		 * name.equalsIgnoreCase
		 * ("com.android.internal.view.menu.ActionMenuItemView")) { try {
		 * LayoutInflater f = getLayoutInflater(); final View view =
		 * f.createView(name, null, attrs); System.out.println((view instanceof
		 * TextView)); if(view instanceof TextView){
		 * ((TextView)view).setTextColor(Color.WHITE); } return view; } catch
		 * (InflateException e) { e.printStackTrace(); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); } } return null; }
		 * 
		 * 
		 * 
		 * });
		 */

		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	/*	case R.id.item1:
			// Toast.makeText(getApplicationContext(), "23w",
			// Toast.LENGTH_LONG).show();
			break;
*/
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private View getCustomizeView() {
		if (actionBar != null) {
			return actionBar.getCustomView();
		}
		return null;
	}

	public String getTilte() {
		return getString(R.string.logi_title);

	}

	private void setCustomPopupMenu(boolean show) {
		if (show) {
			actionBar = getActionBar();
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			actionBar.setCustomView(R.layout.popup_mode);
			mCustomView = this.getCustomizeView();
			if (null == mCustomView)
				return;
			back = (ImageView) mCustomView.findViewById(R.id.back);
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

			mtextView = (TextView) findViewById(R.id.action_title);
			// mtextView.setText(getResources().getString(R.string.albums));
			mtextView.setText(getTilte());
			mtextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
		}
	}

}