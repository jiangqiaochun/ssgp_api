package com.jiang.ssgp.config;

import com.jiang.ssgp.security.MyAuthenticationProvider;
import com.jiang.ssgp.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jqc
 * @create 2019-03-18 18:57
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyAuthenticationProvider myAuthenticationProvider;
    private final UserDetailsServiceImpl myUserDetailsService;

    public WebSecurityConfig(MyAuthenticationProvider myAuthenticationProvider, UserDetailsServiceImpl myUserDetailsService) {
        this.myAuthenticationProvider = myAuthenticationProvider;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .cors().and()
                .httpBasic().disable();
        http
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/upload/students").permitAll()
                .antMatchers("/upload/teachers").permitAll()
                .anyRequest().authenticated()
                .and().rememberMe()
                //设置记住我的时间为7天
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                // 其他 url 需要身份认证
                .and().userDetailsService(myUserDetailsService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }
}