package com.machnet.shared.utils;

public class StringUtils {

    public static boolean isEmpty(String data) {
        return data == null || data.isEmpty() || data.isBlank();
    }
}
