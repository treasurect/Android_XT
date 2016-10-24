package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.utils;

/**
 * Created by treasure on 2016.10.19.
 */

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 常用的加密算法
 */
public final class CryptoUtil {

    private CryptoUtil(){}

    ///////////////////////////////////////////////////////////////////////////
    // DES
    ///////////////////////////////////////////////////////////////////////////

    /**
     * DES 加密算法
     * @param data 原始数据
     * @param key 密码，必须是8个字节
     * @return byte[] 经过加密之后的内容
     */
    public static byte[] desEncrypt(byte[] data, byte[] key){
        byte[] ret = null;
        if (data != null && key != null) {
            if(data.length > 0 && key.length == 8){
                // 1. 使用 Cipher 引擎 来初始化 加密，并且设置密码
                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    // 1.1 DESKeySpec 用于描述DES的密码
                    DESKeySpec spec = new DESKeySpec(key);
                    // 1.2 使用 SecretKeyFactory 生成 Key对象
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey sk = factory.generateSecret(spec);

                    // 1.3 初始化 Cipher 为加密操作，并且指定密钥
                    cipher.init(Cipher.ENCRYPT_MODE, sk);

                    // 2. 加密数据
                    ret = cipher.doFinal(data);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    /**
     * DES 解密算法
     * @param data 原始数据
     * @param key 密码，必须是8个字节
     * @return byte[] 经过解密之后的内容
     */
    public static byte[] desDecrypt(byte[] data, byte[] key){
        byte[] ret = null;
        if (data != null && key != null) {
            if(data.length > 0 && key.length == 8){
                // 1. 使用 Cipher 引擎 来初始化 解密，并且设置密码
                try {
                    Cipher cipher = Cipher.getInstance("DES");

                    // 1.1 DESKeySpec 用于描述DES的密码
                    DESKeySpec spec = new DESKeySpec(key);
                    // 1.2 使用 SecretKeyFactory 生成 Key对象
                    SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
                    SecretKey sk = factory.generateSecret(spec);

                    // 1.3 初始化 Cipher 为解密操作，并且指定密钥
                    cipher.init(Cipher.DECRYPT_MODE, sk);

                    // 2. 解密数据
                    ret = cipher.doFinal(data);

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    ///////////////////////////////////////////////////////////////////////////
    // AES 方式1
    ///////////////////////////////////////////////////////////////////////////

    public static byte[] aesEncryptSimple(byte[] data, byte[] key){
        byte[] ret = null;
        if (data != null && key != null) {
            if(data.length > 0 && key.length == 16){
                // AES 128bit = 16bytes
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                    ret = cipher.doFinal(data);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
    public static byte[] aesDecryptSimple(byte[] data, byte[] key){
        byte[] ret = null;
        if (data != null && key != null) {
            if(data.length > 0 && key.length == 16){
                // AES 128bit = 16bytes
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                    cipher.init(Cipher.DECRYPT_MODE, keySpec);
                    ret = cipher.doFinal(data);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    ///////////////////////////////////////////////////////////////////////////
    // AES 方式2 使用两套密码
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 使用两套密码的加密，强度更高
     * @param data 数据
     * @param key 第一个密码
     * @param ivData 第二个密码
     * @return byte[]
     */
    public static byte[] aesEncryptWithIv(byte[] data, byte[] key, byte[] ivData){
        byte[] ret = null;
        if (data != null && key != null && ivData != null) {
            if(data.length > 0 && key.length == 16 && ivData.length == 16){
                // 使用两套密码的，算法需要写成 AES/算法模式/填充模式
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    // 准备第一套密码
                    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                    // 准备第二套密码
                    IvParameterSpec iv = new IvParameterSpec(ivData);
                    // 设置两套密码的初始化
                    cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
                    cipher.update(data);
                    ret = cipher.doFinal();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static byte[] aesDecryptWithIv(byte[] data, byte[] key, byte[] ivData){
        byte[] ret = null;
        if (data != null && key != null && ivData != null) {
            if(data.length > 0 && key.length == 16 && ivData.length == 16){
                // 使用两套密码的，算法需要写成 AES/算法模式/填充模式
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    // 准备第一套密码
                    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
                    // 准备第二套密码
                    IvParameterSpec iv = new IvParameterSpec(ivData);
                    // 设置两套密码的初始化
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
                    cipher.update(data);
                    ret = cipher.doFinal();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    ///////////////////////////////////////////////////////////////////////////
    // RSA
    ///////////////////////////////////////////////////////////////////////////

    // 1. 生成密钥对 公钥和私钥

    /**
     *
     * @param bits 必须在 1024，2048
     * @return
     */
    public static KeyPair generateRsaKey(int bits){
        KeyPair ret = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            kpg.initialize(bits);

            ret = kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * RSA加密，使用公钥加密，那么必须使用私钥解密
     *         使用私钥加密，那么必须使用公钥解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] rsaEncrypt(byte[] data, Key key){
        byte[] ret = null;
        if (data != null && data.length > 0 && key != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static byte[] rsaDecrypt(byte[] data, Key key){
        byte[] ret = null;
        if (data != null && data.length > 0 && key != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}
