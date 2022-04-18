/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 */
public class TextManager {
        
    public static String getTextTo(String text, String end) {
        String temp = "";
        int i = text.indexOf(end);
        if (i > 0) {
            temp = text.substring(0, i);
        }
        return temp;
    }

    public static String getTextFrom(String text, String begin) {
        String temp = "";
        int i = text.indexOf(begin);
        if (i >= 0) {
            temp = text.substring(i + begin.length());
        }
        return temp;
    } 

    public static String getTextBetween(String text, String begin, String end) {
        String temp = "";
        int i = text.indexOf(begin);
        int j = text.indexOf(end);
        int tailleBegin = begin.length();
        if (i + tailleBegin >= j) {
            j = text.indexOf(end, i + tailleBegin);
        }
        if (i >= 0 && j >= 0) {
            temp = text.substring(i + tailleBegin, j);
        }
        return temp;
    } 

}
