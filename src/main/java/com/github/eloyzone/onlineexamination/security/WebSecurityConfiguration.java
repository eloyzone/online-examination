package com.github.eloyzone.onlineexamination.security;

import com.github.eloyzone.onlineexamination.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Eloy 'Elyas' Hadizadeh Tasbiti
 * Created in 3/16/20, 12:57 PM.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/anonymous*").anonymous()
//                .antMatchers("/login*", "/register*").permitAll()
//                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                .antMatchers("/index*").permitAll()
//                .antMatchers("/books*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/", true);


        http.csrf().disable().authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*", "/register*").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/index*").permitAll()
                .antMatchers("/books/**", "/exam/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/", true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        // it throws an exception
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("eloy").password(passwordEncoder().encode("myjava123")).roles("USER");

        // it throws an exception
//        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource);

        // it does not throw any exception
         authenticationManagerBuilder.userDetailsService(userDetailsService)
                 .passwordEncoder(passwordEncoder());
    }







    /*


CREATE TABLE users(
 username VARCHAR(50) NOT NULL,
 password VARCHAR(100) NOT NULL,
 enabled TINYINT NOT NULL DEFAULT 1,
 PRIMARY KEY (username)
);



CREATE TABLE authorities(
 username VARCHAR(50) NOT NULL,
 authority VARCHAR(50) NOT NULL,
 FOREIGN KEY (username) REFERENCES users(username)
);


CREATE UNIQUE INDEX ix_auth_username
 on authorities (username,authority);


INSERT INTO users (username, password, enabled)
 values( 'eloy',
   '$2a$10$sGYWQC/41EUWzWcIUaLj7ON5BojsIGQs1rNAljUivQdxU4l6W29P2',
   1
 );

INSERT INTO authorities (username, authority)
  values ('eloy','ROLE_USER');


select * from users;
select * from authorities;



 ALTER TABLE users ADD nickname VARCHAR(50) after enabled;

 update users set nickname ='eloy.zone' where username='eloy';

     */

}
