package com.treasure_ct.network_xt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 16/9/6
 * Email: vhly@163.com
 */
public final class EncryptUtil {

    private EncryptUtil(){}

    public static String mapUrl(String url){
        String ret = null;

        if(url != null){
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] data = digest.digest(url.getBytes());
                // byte[] -> String  HEX

                StringBuilder sb = new StringBuilder();
                for (byte b : data) {
                    // byte -> 2个 十六进制表示
                    sb.append(String.format("%2x", b));
                }

                ret = sb.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

}
