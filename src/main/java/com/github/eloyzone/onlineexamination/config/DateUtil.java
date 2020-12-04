package com.github.eloyzone.onlineexamination.config;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 10/17/20, 5:32 PM.
 */
public class DateUtil
{
    /**
     * There is a good method within LocalDate for today's date, I use this method just for testing purposes.
     *
     * @return
     */
    public static LocalDate getTodayDate()
    {
        return LocalDate.now();
//        return LocalDate.of(2019, Month.OCTOBER, 12);
    }
}
