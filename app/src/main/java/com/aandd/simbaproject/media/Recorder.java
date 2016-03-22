package com.aandd.simbaproject.media;

import java.io.IOException;

import com.aandd.simbaproject.activity.MainActivity;
import com.aandd.simbaproject.utility.FileUtil;

import android.media.MediaRecorder;
import android.util.Log;
import android.widget.Toast;

public class Recorder {

	private static MediaRecorder recorder = null;
	private static int currentFormat = 0;
	private static int output_formats[] = { MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP };
	
	private int ampiezza;

	private MainActivity ma;

	public void setAmpiezza(int ampiezza){
		this.ampiezza = ampiezza;
	}

	public int getAmpiezza(){
		return ampiezza;
	}
	
	//parte registrazione e salva file (OK)
	public void startRecording() {
	    recorder = new MediaRecorder();
	    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	    recorder.setOutputFormat(output_formats[currentFormat]);
	    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	    recorder.setOutputFile(FileUtil.getFullFileName());
	    recorder.setOnErrorListener(errorListener);
	    recorder.setOnInfoListener(infoListener);
	    try {
	        recorder.prepare();
	        recorder.start();
	    } catch (IllegalStateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	//	stop registrazione (OK)
	public void stopRecording() {
		try{
	    if (null != recorder) {
	        recorder.stop();
	        recorder.reset();
	        recorder.release();
//			Toast.makeText(ma.getApplicationContext(), new Integer(ampiezza).toString(), Toast.LENGTH_SHORT).show();
	        recorder = null;
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			Toast.makeText(ma.getApplicationContext(), "Non è stato possibile effettuare la registrazione", Toast.LENGTH_SHORT).show();
		}
	}

    //TODO
	// registra la max ampiezza (NN FUNGE)
	public double getAmplitude() {
		double AmpiezzaDiRiferimento = 0.6;
		double Ampiezza = recorder.getMaxAmplitude()/2700.0;
		double decibel = 20 * Math.log10(Ampiezza / AmpiezzaDiRiferimento);
		if (decibel < 0) { decibel = 0; }
		return Ampiezza;
		}

	private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
	    @Override
	    public void onError(MediaRecorder mr, int what, int extra) {
	        Toast.makeText(ma.getApplicationContext(), "Error: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
	    }
	};

	private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
	    @Override
	    public void onInfo(MediaRecorder mr, int what, int extra) {
	        Toast.makeText(ma.getApplicationContext(), "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
	    }
	};

}
