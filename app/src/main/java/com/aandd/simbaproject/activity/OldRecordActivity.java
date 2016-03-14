package com.aandd.simbaproject.activity;

import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.utility.FileName;

import java.io.File;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OldRecordActivity extends ListActivity{
	
	public String[] values;
	
	public String[] getValues() {
		return values;
	}

	public void setValues() {
		String path = getPath();
		File f = new File(path);        
		File file[] = f.listFiles();
		String[] File = new String[file.length];
		for (int i=0; i < file.length; i++)
		{
			File[i] = file[i].getName();
		}
		values = File;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setValues();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getValues());
		setListAdapter(adapter);	
	    android.app.ActionBar actionBar = getActionBar();
	    actionBar.show();
	    actionBar.setDisplayShowTitleEnabled(false);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    Play.mediaPlay(getPath() + "/" + item);
	}

	public String getPath(){
		String filepath = Environment.getExternalStorageDirectory().getPath();
	    File file = new File(filepath, FileName.getAudioRecorderFolder());
	    if (!file.exists()) {
	        file.mkdirs();
	    }	
	    return file.getAbsolutePath();
	}
	
}
