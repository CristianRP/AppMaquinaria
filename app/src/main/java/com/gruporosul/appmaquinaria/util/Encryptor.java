package com.gruporosul.appmaquinaria.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Cristian Ramírez on 26/06/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */

public class Encryptor {
    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(Base64.encodeToString(crypted, Base64.DEFAULT));
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            Base64.decode("123", Base64.DEFAULT);
            output = cipher.doFinal(Base64.decode(input, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(output);
    }

}