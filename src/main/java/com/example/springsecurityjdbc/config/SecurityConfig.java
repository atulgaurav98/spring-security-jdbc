package com.example.springsecurityjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * For internal db, spring security is sensible enough and it knows that it has to create a datasource for
 * it so for h2 in this case spring security will create datasource for h2.
 *
 * Also, if we give clean db to spring security it is sensible enough to create a User details schema all by
 * itself.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                //for using datasource of your choice create your own datasource and pass it below.
                //One approach could be by adding the db specific url,username and password to the application.properties/yml file.
                .dataSource(dataSource)
        //If we don't want to use the default schema which spring security gives for storing user details and authorities
        //We can just add on the queries to help spring fetch the needed records like username,password,enabled and authorities username,authority
//                .usersByUsernameQuery("select username,password,enabled " +
//                        "from users " +
//                        "where username = ?")
//                .authoritiesByUsernameQuery("select username,authority " +
//                        "from authorities " +
//                        "where username = ?")
                /**
                 * The below initialisation is not a production ready approach
                 * current written code is good for production.
                 * .withDefaultSchema()
                .withUser(
                        User.withUsername("user")
                                .password("pass")
                                .roles("USER")
                )
                .withUser(
                        User.withUsername("admin")
                                .password("pass")
                                .roles("ADMIN")
                )*/
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") //admin api accessile by ADMIN role only
                .antMatchers("/user").hasAnyRole("USER","ADMIN") //user api can be accessed by both roles            .antMatchers("/").permitAll()
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
