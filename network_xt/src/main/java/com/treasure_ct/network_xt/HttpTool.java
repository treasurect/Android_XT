package com.treasure_ct.network_xt;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 16/9/6
 * Email: vhly@163.com
 */
public final class HttpTool {

    private HttpTool(){}

    public static byte[] doGet(String url){
        byte[] ret = null;

        if (url != null) {

            HttpURLConnection conn = null;

            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();

                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Accept-Encoding", "gzip");

                conn.connect();

                int code = conn.getResponseCode();

                if(code == HttpURLConnection.HTTP_OK){

                    InputStream stream = conn.getInputStream();

                    // Accept-Encoding 代表能够接受服务器传过来的压缩内容
                    String encoding = conn.getContentEncoding();
                    if("gzip".equals(encoding)){
                        // 解压缩
                        stream = new GZIPInputStream(stream);
                    }
                    ret = StreamUtil.readStream(stream);
                    StreamUtil.close(stream);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(conn);
            }

        }

        return ret;
    }

}
