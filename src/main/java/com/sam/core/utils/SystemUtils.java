package com.sam.core.utils;

import java.io.File;

public class SystemUtils {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}
