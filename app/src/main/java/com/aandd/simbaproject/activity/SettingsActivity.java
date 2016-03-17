package com.aandd.simbaproject.activity;


import com.aandd.simbaproject.utility.Extension;
import com.aandd.simbaproject.utility.FileUtil;
import com.example.simbaproject.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SettingsActivity extends ListActivity {

	private static int currentFormat = 0;
	private static int currentPath = 0;
	private String[] items = new String[]{"Formato","Percorso","About"};
	
	private String[] getItems() {
		return items;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getItems());
		setListAdapter(adapter);
	    android.app.ActionBar actionBar = getActionBar();
	    actionBar.setTitle(R.string.action_settings);
	    actionBar.show();
	    actionBar.setDisplayShowTitleEnabled(true);
	}
	
	//apre activities (OK)
	private void callActivity(Class activity) {
		Intent intent = new Intent(getApplicationContext(),	activity);
		startActivity(intent);	
		}
	
	//crea il dialog per la scelta del path (OK)
	private void displayPathDialog() {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    String path[] = { FileUtil.getAudioRecorderFolder() };
		    builder.setTitle(getString(R.string.choose_path)).setSingleChoiceItems(path, currentPath, new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		             currentPath = which;
		             FileUtil.setCurrentFormat(which);
		             dialog.dismiss();
		         }
		     }).show();
		}

	//crea il dialog per la scelta del formato (OK)
	private void displayFormatDialog() {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle(getString(R.string.choose_format_title)).setSingleChoiceItems(Extension.getFormats(), currentFormat, new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		             currentFormat = which;
		             FileUtil.setCurrentFormat(which);
		             dialog.dismiss();
		         }
		     }).show();
		}
	
	@Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
	    switch(position){
	    case 0:displayFormatDialog();break;  				//"Formato"
	    case 1:displayPathDialog();break; 					//"Percorso"
	    case 2:callActivity(AboutActivity.class);break;  	//"About"
	    }
	}
}