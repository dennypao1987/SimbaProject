package com.aandd.simbaproject.activity;

import java.util.ArrayList;

import com.aandd.simbaproject.db.DBManager;
import com.aandd.simbaproject.drawer.adapter.NavDrawerListAdapter;
import com.aandd.simbaproject.drawer.model.NavDrawerItem;

import com.aandd.simbaproject.utility.FileUtil;
import com.example.simbaproject.R;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity{
	
	private DBManager db=null;
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		createDrawer();

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	public void refreshDraver(){
		ArrayList<NavDrawerItem> items = navDrawerItems;
		NavDrawerItem itemHome = items.get(0);
		NavDrawerItem itemRec = items.get(1);
		itemRec.setCount(((Integer) FileUtil.getNumberOfFiles()).toString());
		NavDrawerItem itemAbout = items.get(2);
		navDrawerItems.clear();
		items.add(0, itemHome);
		items.add(1, itemRec);
		items.add(2, itemAbout);
		navDrawerItems = items;
		adapter = new NavDrawerListAdapter(getApplicationContext(),	navDrawerItems);
		mDrawerList.setAdapter(adapter);
	}

	public void createDrawer(){
		mTitle = mDrawerTitle = getTitle();
		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		if(navDrawerItems == null)
			navDrawerItems = new ArrayList<NavDrawerItem>();
		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// all records
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1),true, ((Integer) FileUtil.getNumberOfFiles()).toString()));
		// about
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		// Recycle the typed array
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),	navDrawerItems);
		mDrawerList.setAdapter(adapter);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
//	public void addDB(){
//		db = new DBManager(this); 
//        db.save("Antonio", "23", Date.getDate(),FileUtil.getFullFileName() );
//	}
	
//	private void Fetch(){
//		Cursor crs = db.query();
//        crs.moveToFirst();
//        String stringa = crs.getString(crs.getColumnIndex(DBStrings.FIELD_NAME));
//        Toast.makeText(getApplicationContext(), "Ciao " + stringa + ", oggi è il " + crs.getString(2) , Toast.LENGTH_LONG).show();
//	}	
//	eventi associati al click start registrazione
	   
	//apre activities (OK)
	private void callActivity(Class activity) {
		Intent intent = new Intent(getApplicationContext(),	activity);
		startActivity(intent);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
				}
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_oldrecord: callActivity(OldRecordActivity.class);return true;
        case R.id.action_settings: callActivity(SettingsActivity.class);return true;
		case R.id.action_allrecord: callActivity(AllRecordActivity.class);return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}
	
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:	fragment = new HomeFragment();		break;
		case 1:	fragment = new AllRecordsFragment();break;
		case 2:	fragment = new AboutFragment();		break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//			update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
//			 error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}