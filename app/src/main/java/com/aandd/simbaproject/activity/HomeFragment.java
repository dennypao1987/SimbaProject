package com.aandd.simbaproject.activity;

import com.aandd.simbaproject.db.DBManager;
import com.aandd.simbaproject.db.DBStrings;
import com.aandd.simbaproject.media.Play;
import com.aandd.simbaproject.media.Recorder;
import com.aandd.simbaproject.utility.FileName;
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
import android.database.Cursor;


public class HomeFragment extends Fragment implements OnClickListener{
	
	private static final String STRING_BUTTON_START = "Spara il rutto";
	private static final String STRING_BUTTON_STOP = "STOP";
	private int currentString = 0;
	private String btnStr[] = { STRING_BUTTON_START, STRING_BUTTON_STOP };
	
	private DBManager db=null;	

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
            	if(currentString==0){
//                Toast.makeText(this.getActivity(), R.string.start_recording, Toast.LENGTH_SHORT).show();
                ((Button) v.findViewById(R.id.btnStart)).setText(btnStr[1]);
                
                ((ImageButton) getView().findViewById(R.id.imgbtnPlay)).setVisibility(View.GONE);
                
                currentString=1;
                FileName.setFullFileName();   
                re.startRecording();
               }else{
            	Toast.makeText(this.getActivity(), R.string.stop_recording, Toast.LENGTH_SHORT).show();
            	
            	((Button) v.findViewById(R.id.btnStart)).setText(btnStr[0]);
            	((ImageButton) getView().findViewById(R.id.imgbtnPlay)).setVisibility(View.VISIBLE);
            	currentString=0;
                re.stopRecording();
//                String amp = String.valueOf(re.getAmpiezza());
//                addDB();
//                Fetch();
                }
                break;
            }
            case R.id.imgbtnPlay: 
				Play.mediaPlay(FileName.getFullFileName());
				Toast.makeText(this.getActivity(), "play", Toast.LENGTH_LONG).show();
					break;        
        }
	}

//	public void addDB(){
//		db = new DBManager(this); 
//        db.save("Antonio", "23", Date.getDate(),FileName.getFullFileName() );
//	}
//	private void Fetch(){
//		Cursor crs = db.query();
//        crs.moveToFirst();
//        String stringa = crs.getString(crs.getColumnIndex(DBStrings.FIELD_NAME));
//        Toast.makeText(getActivity().getApplicationContext(), "Ciao " + stringa + ", oggi � il " + crs.getString(2) , Toast.LENGTH_LONG).show();
//	}	
}
