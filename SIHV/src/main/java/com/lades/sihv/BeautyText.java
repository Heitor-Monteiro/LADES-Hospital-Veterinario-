/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv;

/**
 *
 * @author waves
 */
public class BeautyText {

    public static String right(String value, int length) {
        return value.substring(value.length() - length);
    }

    public static String Captalizador(String text) {
        String backUp = text;
        try {
            text = text.toLowerCase();
            text = text.replaceAll(right(text, text.length() - 1), "").toUpperCase() + right(text, text.length() - 1);
            char[] charText = text.toCharArray();
            for (int x = 0; x < charText.length; x++) {
                try {
                    int index = text.indexOf(" ");
                    charText[index] = '§';
                    text = new String(charText);
                    char letra = Character.toUpperCase(charText[index + 1]);
                    charText[index + 1] = letra;
                } catch (Exception ex) {
                }
            }
            return new String(charText).replaceAll("§", " ");
        } catch (Exception ex) {
            return backUp;
        }
    }
    
    public static String fistNLast(String Str){;
      String[] name = Str.split(" ");
      if(name.length>1)
        return name[0]+" "+name[name.length-1];
      else
          return Str;
   }
}
