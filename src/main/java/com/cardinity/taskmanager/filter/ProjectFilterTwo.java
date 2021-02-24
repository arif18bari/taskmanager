package com.cardinity.taskmanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ProjectFilterTwo implements Filter {

    private static Logger log = LoggerFactory.getLogger(ProjectFilterTwo.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("ProjectFilterTwo - doFilter");
        chain.doFilter(request, response);
    }

}
