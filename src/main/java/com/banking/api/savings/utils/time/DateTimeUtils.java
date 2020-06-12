package com.banking.api.savings.utils.time;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateTimeUtils {

    private static final String zoneID = "Europe/Bucharest";
    private static final LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(zoneID));


    public static boolean isWeekDay(){
        switch (localDateTime.getDayOfWeek()){
            case SATURDAY:
                return false;
            case SUNDAY:
                return false;
            default:
                return true;
        }
    }

    public static boolean isWorkingHours(){
        LocalTime startingHour = LocalTime.of(8,0);
        LocalTime closingHour = LocalTime.of(18,0);
        LocalTime currentHour = LocalTime.of(localDateTime.getHour(),0);

        return isTimeInRange(currentHour,startingHour,closingHour);
    }

    public static boolean isTimeInRange(LocalTime checkDate, LocalTime firstDate, LocalTime secondDate){
        return !(checkDate.isBefore(firstDate) || firstDate.isAfter(secondDate));
    }
}
