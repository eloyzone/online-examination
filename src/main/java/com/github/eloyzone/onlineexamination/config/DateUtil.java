package com.github.eloyzone.onlineexamination.config;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/17/20, 5:32 PM.
 */
public class DateUtil
{
    private static LocalDate localDate;

    public static LocalDate getTodayDate()
    {
        if (localDate == null)
        {
//            localDate = LocalDate.now();
            localDate = LocalDate.of(2019, Month.OCTOBER, 11);

        }
        return localDate;
    }
}
