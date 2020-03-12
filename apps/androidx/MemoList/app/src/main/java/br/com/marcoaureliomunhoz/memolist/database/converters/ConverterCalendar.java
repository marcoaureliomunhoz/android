package br.com.marcoaureliomunhoz.memolist.database.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverterCalendar {

    @TypeConverter
    public Long toLong(Calendar value) {
        return value.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long value) {
        Calendar calendar = Calendar.getInstance();
        if (value != null) calendar.setTimeInMillis(value);
        return calendar;
    }

}
