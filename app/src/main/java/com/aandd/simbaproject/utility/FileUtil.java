package com.aandd.simbaproject.utility;

import java.io.File;
import android.os.Environment;

public class FileUtil {

	private static String AUDIO_RECORDER_FOLDER = "SimbaProject";
	private static File path;				//path assoluto della cartella dell'app
	private static int currentFormat = 0;
	private static String fullFileName;		//nome completo del file audio compreso di path assoluto

	private FileUtil(){
		setPath(AUDIO_RECORDER_FOLDER);
	}

	//TODO
	public static void setAudioRecorderFolder(String AUDIO_RECORDER_FOLDER) {
		FileUtil.AUDIO_RECORDER_FOLDER = AUDIO_RECORDER_FOLDER;
	}

	//recupara il nome della cartella dell'app
	public static String getAudioRecorderFolder() {
		return AUDIO_RECORDER_FOLDER;
	}

	//setta il path assoluto della cartella dell'applicazione
	private static void setPath(String audioRecorderFolder) {
		String devicePath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(devicePath, audioRecorderFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileUtil.path = file;
	}

	//recupera il path assoluto della cartella dell'app
	public static File getPath() {
		if (!path.exists()) {
			path.mkdirs();
		}
		return path;
	}

	public static void setCurrentFormat(int currentFormat) {
		FileUtil.currentFormat = currentFormat;
	}

	public static String getCurrentFormat() {
		return Extension.getFormats()[currentFormat];
	}

	//setta il nome completo del file audio compreso di path assoluto
	public static String setFullFileName() {
		Date.setDate();
		if (!path.exists()) {
			path.mkdirs();
		}
		fullFileName = path.getAbsolutePath() + "/" + Date.getDate() + Extension.getFile_exts()[currentFormat];
		return fullFileName;
	}

	//recupera il nome completo del file audio compreso di path assoluto
	public static String getFullFileName() {
		return fullFileName;
	}

	//recupera i nomi dei file contenuti nella cartella dell'app
	public static String[] getFilesName() {
		File filesList[] = path.listFiles();
		String[] filesName = new String[filesList.length];
		for (int i=0; i < filesList.length; i++)
		{
			filesName[i] = filesList[i].getName();
		}
		return filesName;
	}

	//recupera il numero di registrazioni presenti nel path dell'app
	public static int getNumberOfFiles() {
		setPath(AUDIO_RECORDER_FOLDER);
		return path.listFiles().length;
	}


}
