package com.sam.core.config;

import com.sam.core.helpers.PropertiesHelper;

public class Configuration {
    public static String URL = PropertiesHelper.getValue("URL");
    public static String BROWSER = PropertiesHelper.getValue("BROWSER");
    public static String EMAIL = PropertiesHelper.getValue("EMAIL");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));
    public static String SCREENSHOTS = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static String RECORDVIDEOS = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
}
