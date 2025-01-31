package ru.job4j.todo.util;

import lombok.AllArgsConstructor;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@AllArgsConstructor
public class TimeZonesUtil {

    private static final List<TimeZone> ZONES = new ArrayList<>();

    private static final List<String> ZONES_COLLECTED = new ArrayList<>();

    public static List<String> collectTimeZones() {
        for (String timeId : ZoneId.getAvailableZoneIds()) {
            ZONES.add(TimeZone.getTimeZone(timeId));
        }
        ZONES_COLLECTED.add("Default : по умолчанию");
        for (TimeZone zone : ZONES) {
            ZONES_COLLECTED.add(zone.getID() + " : " + zone.getDisplayName());
        }
        return ZONES_COLLECTED;
    }
}


