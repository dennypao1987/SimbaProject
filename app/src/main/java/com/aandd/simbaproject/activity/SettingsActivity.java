package com.aandd.simbaproject.activity;


import com.aandd.simbaproject.utility.FileName;
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
	
public String[] values;
	
	public String[] getValues() {
		return values;
	}

	public void setValues() {
		String[] New= new String[]{"Formato","Percorso","About"};
		values=New;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setValues();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getValues());
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
	
	//crea il dialog x la scelta del path (OK)
	private void displayPathDialog() {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    String path[] = { FileName.getAudioRecorderFolder() };
		    builder.setTitle(getString(R.string.choose_path)).setSingleChoiceItems(path, currentPath, new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		             currentPath = which;
		             FileName.setCurrentFormat(which);
		             dialog.dismiss();
		         }
		     }).show();
		}

	//crea il dialog x la scelta del formato (OK)
	private void displayFormatDialog() {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    String formats[] = { "MPEG 4", "3GPP" };
		    builder.setTitle(getString(R.string.choose_format_title)).setSingleChoiceItems(formats, currentFormat, new DialogInterface.OnClickListener() {
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		             currentFormat = which;
		             FileName.setCurrentFormat(which);
		             dialog.dismiss();
		         }
		     }).show();
		}
	
	@Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
//	    String item = (String) getListAdapter().getItem(position);
	    switch(position){
	    case 0:displayFormatDialog();break;  //"Formato"
	    case 1:displayPathDialog();break;  //"Percorso"
	    case 2:callActivity(AboutActivity.class);break;  //"About"
	    }
	}
}