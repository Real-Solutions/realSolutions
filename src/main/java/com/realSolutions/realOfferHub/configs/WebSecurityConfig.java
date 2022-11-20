package com.realSolutions.realOfferHub.configs;

import com.realSolutions.realOfferHub.models.SiteUser;
import com.realSolutions.realOfferHub.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    SiteUserDetailsServiceImpl siteUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public  void configureGlobal (AuthenticationManagerBuilder auth) throws  Exception{
        auth.inMemoryAuthentication();


    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(siteUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/", "/signup", "/login", "/css/*.css", "/images/**", "/fragments/**").permitAll()
                .anyRequest().authenticated()
                .and()


                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")

                .and()
                .logout()
                .logoutSuccessUrl("/");

    }
}
