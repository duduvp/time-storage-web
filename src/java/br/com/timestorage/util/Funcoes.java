/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.timestorage.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 * @author mateus
 */
public class Funcoes {

    public static Date StringToDate(String data) throws ParseException {
        if (data != null || !data.equals("")) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            return (Date) fmt.parse(data);
        } else {
            return null;
        }
    }

    public static String FormartDate(Date data, String format) throws ParseException {
        if (data != null) {
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            return fmt.format(data);
        } else {
            return null;
        }
    }

    public static Date getPegaDataAtual() {
        Calendar calendar = new GregorianCalendar();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.getTime();
    }

    public static String converterSHA(String senha) throws NoSuchAlgorithmException {
        if (senha != null) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senha.getBytes());

            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } else {
            return null;
        }
    }
}
