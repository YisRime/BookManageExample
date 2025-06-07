package com.example.bookmanage.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
    
    public static String getCurrentTimeStr() {
        return LocalDateTime.now().format(formatter);
    }
    
    public static String formatTime(LocalDateTime time) {
        return time.format(formatter);
    }
}