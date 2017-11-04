package com.example.androidbasedemo.preferenceFragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.example.androidbasedemo.R;

public class MyPrefFragment extends PreferenceFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.xml);
		getPreferenceManager().setSharedPreferencesName("infor_mypref");//自定义文件名
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// TODO Auto-generated method stub
		if(preference.getKey().equals("yesno_save_individual_info"))
		{
			CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("yesno_save_individual_info");
			EditTextPreference editTextPreference =	(EditTextPreference) findPreference("individual_name");
			editTextPreference.setEnabled(checkBoxPreference.isChecked());
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}

}
