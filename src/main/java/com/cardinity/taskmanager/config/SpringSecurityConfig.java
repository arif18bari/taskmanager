package com.cardinity.taskmanager.config;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.cardinity.taskmanager.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/common/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                "/configuration/security","/index.html", "/swagger-ui.html", "/webjars/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().antMatcher("/**") // this will apply to the entire
                // web server
                .authorizeRequests().antMatchers("/",
                "/v2/api-docs",           // swagger
                "/webjars/**",            // swagger-ui webjars
                "**/swagger-resources/**",  // swagger-ui resources
                "/configuration/**",      // swagger configuration
                "/swagger-ui.html",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js")
                .permitAll().anyRequest().authenticated().and().httpBasic()
                .authenticationEntryPoint(entryPoint()).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Exception handling configuration

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) ->
                {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    try {
                        response.getWriter().write(new JSONObject()
                                .put("timestamp", LocalDateTime.now())
                                .put("message", "Access denied")
                                .toString());
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                });
    }

 

    @Bean
    public AuthenticationEntryPoint entryPoint() {
        return new BasicAuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                JSONObject jsonObject = new JSONObject();
                try {
                    response.addHeader("WWW-Authenticate", "Basic Realm - " + getRealmName());
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter()
                            .println(jsonObject.put("exception", "HTTP Status 401 - " + authException.getMessage()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterPropertiesSet() {
                setRealmName("cardinity");
                super.afterPropertiesSet();
            }
        };
    }


    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.getWriter().write("Access Denied... Forbidden");
    }
}
