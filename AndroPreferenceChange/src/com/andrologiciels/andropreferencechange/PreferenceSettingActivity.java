package com.andrologiciels.andropreferencechange;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class PreferenceSettingActivity extends PreferenceActivity implements
OnSharedPreferenceChangeListener {
@SuppressWarnings("deprecation")
@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    PreferenceManager.setDefaultValues(this, R.xml.prefs, false);
    addPreferencesFromResource(R.xml.prefs);
 
    SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
 
    Preference name = (Preference) findPreference("name");
    name.setSummary(sp.getString("name", ""));
 
    Preference password = (Preference) findPreference("password");
    password.setSummary(sp.getString("password", "").replaceAll(".","*"));
           
    CheckBoxPreference autostart = (CheckBoxPreference) findPreference("autostart");
    if(autostart.isChecked())
      autostart.setSummary("Auto Login On");
    else
      autostart.setSummary("Auto Login Off");
  }
 
  @SuppressWarnings("deprecation")
@Override
  protected void onPause() {
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    finish();
    super.onPause();
  }
 
  @SuppressWarnings("deprecation")
protected void onResume() {
    super.onResume();
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }
 
  //-- Manage the change event
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    @SuppressWarnings("deprecation")
	Preference pref = findPreference(key);
    //-- Manage EditTextField
    if (pref instanceof EditTextPreference) {
      EditTextPreference etp = (EditTextPreference) pref;
      if(pref.getKey().equals("password")){
        pref.setSummary(etp.getText().replaceAll(".","*"));
      }else{
         pref.setSummary(etp.getText());
      }
    }
    else 
    	if(pref instanceof CheckBoxPreference){
    	    if(((CheckBoxPreference)pref).isChecked())
    	    	{if(pref.getKey().equals("autostart"))
    	    	   pref.setSummary("Auto Login On");}
    	    else
    	    	{if(pref.getKey().equals("autostart"))
    	          pref.setSummary("Auto Login Off");}
    	    }
  }
}