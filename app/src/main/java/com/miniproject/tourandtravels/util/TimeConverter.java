package com.miniproject.tourandtravels.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class TimeConverter {
    public static SimpleDateFormat formatter = new SimpleDateFormat("EEE dd MMM yyyy", Locale.ENGLISH);
    public static SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public static SimpleDateFormat dmy = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public static SimpleDateFormat week = new SimpleDateFormat("EEE", Locale.ENGLISH);
    public static SimpleDateFormat day = new SimpleDateFormat("dd", Locale.ENGLISH);
    public static SimpleDateFormat month = new SimpleDateFormat("MMM", Locale.ENGLISH);


}
