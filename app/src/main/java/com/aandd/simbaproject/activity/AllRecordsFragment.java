package com.aandd.simbaproject.activity;

import java.io.File;

import com.aandd.simbaproject.utility.FileUtil;
import com.example.simbaproject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AllRecordsFragment extends Fragment{
	
	public String[] values;
	
	public String[] getValues() {
		return values;
	}

	public AllRecordsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getValues());
//		setListAdapter(adapter);	
//	    android.app.ActionBar actionBar = getActionBar();
//	    actionBar.show();
//	    actionBar.setDisplayShowTitleEnabled(false);       
        
        return rootView;
    }

//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//	    String item = (String) getListAdapter().getItem(position);
//	    Play.mediaPlay(getPath() + "/" + item);
//	}

}
