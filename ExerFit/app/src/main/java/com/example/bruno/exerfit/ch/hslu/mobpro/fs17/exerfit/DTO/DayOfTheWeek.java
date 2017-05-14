package com.example.bruno.exerfit.ch.hslu.mobpro.fs17.exerfit.DTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruno on 14/05/2017.
 */

public enum DayOfTheWeek{

    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private final int dayOfTheWeek;

    private static Map<Integer, DayOfTheWeek> map = new HashMap<Integer, DayOfTheWeek>();

    static {
        for (DayOfTheWeek dayOfTheWeekEnum : DayOfTheWeek.values()) {
            map.put(dayOfTheWeekEnum.dayOfTheWeek, dayOfTheWeekEnum);
        }
    }


    DayOfTheWeek(final int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public static DayOfTheWeek valueOfInt(int dayOfTheWeekNo) {
        return map.get(dayOfTheWeekNo);
    }


    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public static DayOfTheWeek valueOfString(String dayOfTheWeek){
        switch(dayOfTheWeek){
            case "Monday" : return MONDAY;
            case "Tuesday": return TUESDAY;
            case "Wednesday" : return WEDNESDAY;
            case "Thursday" : return THURSDAY;
            case "Friday" : return FRIDAY;
            case "Saturday" : return SATURDAY;
            case "Sunday" : return SUNDAY;
            default : return null;
        }
    }

}
