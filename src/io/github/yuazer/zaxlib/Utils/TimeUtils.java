package io.github.yuazer.zaxlib.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    private static TimeUtils utils;

    public static TimeUtils getUtils() {
        return utils;
    }

    public static String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        switch (weekday) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
                break;
            default:
                week = "错误";
                break;
        }
//        if (weekday == 1) {
//            week = "周日";
//        } else if (weekday == 2) {
//            week = "周一";
//        } else if (weekday == 3) {
//            week = "周二";
//        } else if (weekday == 4) {
//            week = "周三";
//        } else if (weekday == 5) {
//            week = "周四";
//        } else if (weekday == 6) {
//            week = "周五";
//        } else if (weekday == 7) {
//            week = "周六";
//        }
        return week;
    }

    public static void copyFile(String oldPath, String newPath) throws Exception {
        int bytesum = 0;
        int byteread = 0;
        FileInputStream inPutStream = null;
        FileOutputStream outPutStream = null;
        try {
            // oldPath的文件copy到新的路径下，如果在新路径下有同名文件，则覆盖源文件
            inPutStream = new FileInputStream(oldPath);
            outPutStream = new FileOutputStream(newPath);
            byte[] buffer = new byte[4096];
            while ((byteread = inPutStream.read(buffer)) != -1) {
                bytesum += byteread;
                outPutStream.write(buffer, 0, byteread);
            }
        } finally {
            // inPutStream关闭
            if (inPutStream != null) {
                inPutStream.close();
                inPutStream = null;
            }
            // outPutStream关闭
            if (outPutStream != null) {
                outPutStream.close();
                outPutStream = null;
            }
        }
    }
}
