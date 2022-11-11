package com.valleon.rewardyourteacherapi.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeConverter {
    public static String localDateTimeConverter(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM , yyyy hh:mm a", Locale.US);
        return formatter.format(localDateTime);
    }
}
