package com.andrologiciels.andropreferencechange;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;


public class PreferenceChangeActivity extends Activity {
    //-- Global Var
	private	TextView printname;
	private String TxtName="";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//-- Main Screen
		setContentView(R.layout.main);
		printname  = (TextView) findViewById(R.id.printname);
	    //-- Get preferences
		loadPref();
	}

	//-- Menu Init
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {  
	  	  MenuInflater inflater = getMenuInflater();  
	  	  inflater.inflate(R.menu.menu, menu);
	  	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	  			ActionBar actionBar = getActionBar();
	  			actionBar.setDisplayHomeAsUpEnabled(true);
	  		}
	  		return true;
	  	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) { 
	        case android.R.id.home: //--Home
	            finish();
  	            return true;
			case R.id.settings:
			{
	  	  	   	Intent intent = new Intent(this, PreferenceSettingActivity.class);
	    	    startActivityForResult(intent, 0); 
      	        return true;
			}

			default:
			{
				return false;
			}
		}
	}
    /////////////
	private void loadPref(){
  		SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
  		TxtName  = mySharedPreferences.getString("name", "Empty");
		//-- Update printname
		printname.setText(TxtName);
	}
	//////////
	//-- Intent Result (update txt field with pref)
    @Override     
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	loadPref();
    
    }
}