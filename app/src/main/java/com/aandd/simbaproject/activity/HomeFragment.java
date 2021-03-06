package com.aandd.simbaproject.activity;

//import com.aandd.simbaproject.db.DBManager;
import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.media.Recorder;
import com.aandd.simbaproject.utility.FileUtil;
import com.example.simbaproject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class HomeFragment extends Fragment implements OnClickListener{
	
	private static final String STRING_BUTTON_START = "Spara il rutto";
	private static final String STRING_BUTTON_STOP = "STOP";
	private int currentString = 0;		// step 0: non in registrazione;
										// step 1: in registrazione;
	private String btnStr[] = { STRING_BUTTON_START, STRING_BUTTON_STOP };
	
//	private DBManager db=null;

	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
		Button button1 = (Button) rootView.findViewById(R.id.btnStart);
		button1.setOnClickListener(this);
		ImageButton button2 = (ImageButton) rootView.findViewById(R.id.imgbtnPlay);
		button2.setOnClickListener(this);
        
        return rootView;
    }

	public void onClick(View v) {
		Recorder re = new Recorder();
		switch (v.getId()) {
			case R.id.btnStart: {
				if (currentString == 0) {
//                Toast.makeText(this.getActivity(), R.string.start_recording, Toast.LENGTH_SHORT).show();
					((Button) v.findViewById(R.id.btnStart)).setText(btnStr[1]);
					((ImageButton) getView().findViewById(R.id.imgbtnPlay)).setVisibility(View.GONE);
					currentString = 1;
					FileUtil.setFullFileName();
					re.startRecording();
				} else {
					Toast.makeText(this.getActivity(), R.string.stop_recording, Toast.LENGTH_SHORT).show();
					((Button) v.findViewById(R.id.btnStart)).setText(btnStr[0]);
					((ImageButton) getView().findViewById(R.id.imgbtnPlay)).setVisibility(View.VISIBLE);
					currentString = 0;
					re.stopRecording();
					Toast.makeText(this.getActivity(),Double.toString(re.getAmpiezza()), Toast.LENGTH_LONG).show();
//                addDB();
//                Fetch();
				}
				break;
			}
			case R.id.imgbtnPlay:
				Play.mediaPlay(FileUtil.getFullFileName());
				Toast.makeText(this.getActivity(), "play", Toast.LENGTH_LONG).show();
				break;
		}
		refreshDrawer();
	}

	public void refreshDrawer(){
		MainActivity main = (MainActivity) getActivity();
		main.refreshDraver();
	}

//	public void addDB(){
//		db = new DBManager(this); 
//        db.save("Antonio", "23", Date.getDate(),FileUtil.getFullFileName() );
//	}
//	private void Fetch(){
//		Cursor crs = db.query();
//        crs.moveToFirst();
//        String stringa = crs.getString(crs.getColumnIndex(DBStrings.FIELD_NAME));
//        Toast.makeText(getActivity().getApplicationContext(), "Ciao " + stringa + ", oggi è il " + crs.getString(2) , Toast.LENGTH_LONG).show();
//	}	
}
