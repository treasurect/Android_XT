package com.treasure_ct.post_network_xt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by treasure on 2016.09.18.
 */
public final class HttpUtil {
    public HttpUtil() {
    }
    public static byte[] doPostJson(String url,String jsonString){
        byte[] ret = null;
        try {
            ret = doPost(url,jsonString.getBytes("UTF-8"),"application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static byte[] doPost(String url,byte[] data,String contentType){
        byte[] ret = null;
        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(url);
            connection = ((HttpURLConnection) url1.openConnection());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Encoding","gzip");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(600000);
            if (data != null && data.length >0){
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type",contentType);
                OutputStream os = connection.getOutputStream();
                os.write(data);
                os.close();
            }
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                String encoding = connection.getContentEncoding();
                if ("gzip".equals(encoding)){
                    is = new GZIPInputStream(is);
                }
                byte[] buf = new byte[1024];
                int length;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((length = is.read(buf))!= -1){
                    bos.write(buf,0,length);
                }
                buf = null;
                ret = bos.toByteArray();
                bos.close();
                is.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return ret;
    }
}
