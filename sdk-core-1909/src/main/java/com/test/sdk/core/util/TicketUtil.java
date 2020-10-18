package com.test.sdk.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 徒有琴
 */
public class TicketUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String encode(String ticket) {
        Date time = new Date();
        String now = FORMAT.format(time);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < now.length(); i++) {
            builder.append(ticket.charAt(i));
            builder.append(now.charAt(i));
        }
        builder.append(ticket.substring(14));
        return builder.toString();
    }

    public static Date getTime(String ticket) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 28; i += 2) {
            builder.append(ticket.charAt(i + 1));
        }
        String time = builder.toString();
        try {
            return FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String ticket) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 28; i += 2) {
            builder.append(ticket.charAt(i));
        }
        builder.append(ticket.substring(28));
        return builder.toString();
    }

}
