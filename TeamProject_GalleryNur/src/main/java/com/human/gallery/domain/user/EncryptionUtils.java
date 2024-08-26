package com.human.gallery.domain.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class EncryptionUtils {

    public static String getEncrypt(String source, String salt) throws NoSuchAlgorithmException {
        return getEncrypt(source, salt.getBytes());
    }

    public static String getEncrypt(String source, byte[] salt) throws NoSuchAlgorithmException {

        String result = ""; // 초기값 선언

        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];

        System.arraycopy(a, 0, bytes,0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);

        byte[] byteData = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
        }
        result = sb.toString();

        return result;
    }
    public static String getSalt() {
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x", salt[i]));
        }
        return sb.toString();
    }
}
