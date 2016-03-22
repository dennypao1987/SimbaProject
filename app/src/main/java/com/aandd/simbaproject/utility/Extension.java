package com.aandd.simbaproject.utility;

public class Extension {

    private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
    private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";

    private static final String MPEG4 = "MPEG 4";
    private static final String THREEGPP = "3GPP";

    private static String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP };

    private static String formats[] = { MPEG4, THREEGPP };

    public static String[] getFile_exts() {
        return file_exts;
    }

    public static String[] getFormats() {
        return formats;
    }
}
