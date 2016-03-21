package com.aandd.simbaproject.media;

import com.aandd.simbaproject.activity.MainActivity;
import com.example.simbaproject.R;

import android.media.MediaPlayer;
import android.widget.Toast;

public class Play {

//	parte la riproduzione dell'ultima registrazione (NN FUNGE)
	public static void mediaPlay(String fileName) {
		
			MainActivity ma = new MainActivity();
			
		try {
			MediaPlayer mPlayer= new MediaPlayer();
			mPlayer.setDataSource(fileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (Exception e) {
			Toast.makeText(ma.getApplicationContext(), R.string.play_fail, Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
