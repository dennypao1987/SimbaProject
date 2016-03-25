package com.aandd.simbaproject.activity;

import com.aandd.simbaproject.connect.SelectAll;
import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.utility.FileUtil;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.id.home;

public class AllRecordActivity extends ListActivity{

	String result;
	String[] recordList = {"ciao","blu"};

	public void setRecordList(String[] recordList) { this.recordList = recordList; }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String user = "admin";
		String password = "admin";
 		new SelectAll(this, result).execute(user, password);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				onLongItemClick(position);
				return true;
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
		Play.mediaPlay(FileUtil.getPath() + "/" + item);
	}

	//eliminazione file e refresh del fragment
	public void onLongItemClick(int position){
		final int pos = position;
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setMessage("Eliminare la registrazione?");
		alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				FileUtil.deleteFile((String) getListAdapter().getItem(pos));
				finish();
				startActivity(getIntent());
			}
		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//
			}
		});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
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
