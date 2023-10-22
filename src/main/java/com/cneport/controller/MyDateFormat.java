package com.cneport.controller;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zpw
 * @date 2023-10-22 21:51
 */
public class MyDateFormat extends DateFormat {
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return toAppendTo.append(sdf.format(date));
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        return null;
    }
}
