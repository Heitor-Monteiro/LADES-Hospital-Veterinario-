/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import java.security.MessageDigest;
import java.math.BigInteger;

/**
 *
 * @author waves
 */
public class Security {

    public String getSHA1(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(senha.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            return hash.toString(16);
        } catch (Exception ex) {
            System.out.println("FUDEU");
            return "Numvaidanao";
        }
    }

    public String getMD5(String plainText) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plainText.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception ex) {
            System.out.println("FUDEU");
            return "Numvaidanao";
        }
    }
    
    public String encrypter(String plainText){
        return getMD5(getSHA1(plainText));
    }
}
