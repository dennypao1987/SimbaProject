package com.aandd.simbaproject.activity;

import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.utility.FileUtil;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.id.home;

public class OldRecordActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FileUtil.getFilesName());
		setListAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
	    Play.mediaPlay(FileUtil.getPath() + "/" + item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}
