package com.aandd.simbaproject.activity;

import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.utility.FileUtil;

import java.io.File;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OldRecordActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FileUtil.getFilesName());
		setListAdapter(adapter);	
	    android.app.ActionBar actionBar = getActionBar();
	    actionBar.show();
	    actionBar.setDisplayShowTitleEnabled(false);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    Play.mediaPlay(FileUtil.getPath() + "/" + item);
	}
	
}
