package com.aandd.simbaproject.utility;

import java.io.File;
import android.os.Environment;

public class FileName {

	private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
	private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
	private static String AUDIO_RECORDER_FOLDER = "SimbaProject";
	
	private static int currentFormat = 0;
	private static int currentPath = 0;
	private static String FullFileName;
	private static String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP };

	public static void setFullFileName() {
		String filepath = Environment.getExternalStorageDirectory().getPath();
	    File file = new File(filepath, AUDIO_RECORDER_FOLDER);
	    Date.setDate();
	    if (!file.exists()) {
	        file.mkdirs();
	    }
	    FullFileName = file.getAbsolutePath() + "/" + Date.getDate() + file_exts[currentFormat];
	}	

	public static String getFullFileName() {
		return FullFileName;
	}

	public static String getAudioRecorderFolder() {
		return AUDIO_RECORDER_FOLDER;
	}
	
	public static void setAudioRecorderFolder(String AUDIO_RECORDER_FOLDER) {
		FileName.AUDIO_RECORDER_FOLDER = AUDIO_RECORDER_FOLDER;
	}
	
	public static void setCurrentFormat(int currentFormat) {
		FileName.currentFormat = currentFormat;
	}
}
