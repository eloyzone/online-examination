package com.github.eloyzone.onlineexamination.config;

import org.hibernate.dialect.MySQL57Dialect;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 3/24/20, 9:16 AM.
 */
public class MySQL57Utf8Dialect extends MySQL57Dialect
{
    @Override
    public String getTableTypeString()
    {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
    }
}

