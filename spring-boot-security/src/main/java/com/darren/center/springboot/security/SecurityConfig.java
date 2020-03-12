package com.darren.center.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * springboot 2.0.6 + spring security
 * https://blog.csdn.net/wangxueqing52/article/details/82628407
 * @EnableGlobalMethodSecurity 开启方法权限控制
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService()).passwordEncoder(md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所用用户允许访问页面
                .antMatchers("/favicon.ico","/css/**","/common/**","/js/**","/images/**","/captcha.jpg","/login","/userLogin","/login-error").permitAll()
                //任何尚未匹配的URL只需要验证用户即可访问
                .anyRequest().authenticated()
                .and()
                //登陆成功跳转页面和登陆失败跳转页面
                .formLogin().loginPage("/login").successForwardUrl("/index").failureForwardUrl("login?error=1")
                .and()
                //权限拒接页面
                .exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder(){
        return new Md5PasswordEncoder();
    }

    @Bean
    public CustomUserDetailService customUserDetailService(){
        return new CustomUserDetailService();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
