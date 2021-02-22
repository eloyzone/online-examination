package com.github.eloyzone.onlineexamination.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * @author Elyas 'Eloy' Hadizadeh Tasbiti
 * Created in 4/3/20, 5:41 PM.
 */
@Configuration
//public class ApplicationConfigure extends WebMvcConfigurerAdapter
public class ApplicationConfigure implements WebMvcConfigurer
{
    @Bean
    public LocaleResolver localeResolver()
    {
//        Locale farsiLocale = new Locale("fa", "IR");
        Locale englishLocale = new Locale("en");       // English language
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(englishLocale); // it is not set on the cookie!! It is a default locale
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;

        // also we've added a LocaleChangeInterceptor which then user can change the value of cookie via url.
        // For instance, the user can browse url?=locale=fa or url?locale=en and etc.
    }

    /**
     * Declaring the location i18n messages
     *
     * @return
     */
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/Messages");
//        messageSource.setBasenames("i18n/Messages", "i18n/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @Override
    public Validator getValidator()
    {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
