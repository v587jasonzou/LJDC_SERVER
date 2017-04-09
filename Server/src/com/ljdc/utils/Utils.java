package com.ljdc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/23 0023
 * Time:上午 2:22
 * Desc:略
 */
public class Utils {

    /**
     * 向浏览器返回字符串数据
     *
     * @param response
     * @param stream
     */
    public static void printToBrowser(HttpServletResponse response, String stream) {
        response.setCharacterEncoding("utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.flush();
        pw.close();
    }

    public static SimpleDateFormat getDateFormater() {
        SimpleDateFormat sdf;//小写的mm表示的是分钟
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf;
    }

    public static Gson getGsonForDate() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //java.util.Date的时间格式
                .create();
        return gson;
    }

    public static String getStringFromFile(File file) {

        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

}
