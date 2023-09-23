package org.hngxfreelunch.LiquidApplicationApi.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getExpirationDate(Integer expiration){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expiration);
        return new Date(calendar.getTime().getTime());
    }
}
