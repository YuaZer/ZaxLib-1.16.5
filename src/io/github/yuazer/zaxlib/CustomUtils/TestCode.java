package io.github.yuazer.zaxlib.CustomUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCode {
    public static void main(String[] args) {
        System.out.println(formatDate(1786982400741L));
    }
    public static String formatDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
}

