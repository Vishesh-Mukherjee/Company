package com.gdgu.company.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDate {
    public static Date stringToDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(strDate);
        } catch(Exception e) {
            return null;
        }
    }
}
