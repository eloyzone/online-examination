package com.github.eloyzone.onlineexamination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.http.HttpServletRequestWrapper;

@SpringBootApplication
public class WebApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebApplication.class, args);
    }

    /**
     * Since browsers currently only support GET and POST, a common technique - used by the
     * Prototype library, for instance - is to use a normal POST with an additional hidden form
     * field ({@code _method}) to pass the "real" HTTP method along. This filter reads that parameter
     * and changes the {@link HttpServletRequestWrapper#getMethod()} return value accordingly.
     * Only {@code "PUT"}, {@code "DELETE"} and {@code "PATCH"} HTTP methods are allowed.
     *
     * @return
     */
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter()
    {
        return new HiddenHttpMethodFilter();
    }
}
