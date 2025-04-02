package com.inspire12.likelionbackend.module.core.threadlocal.filter;

import ch.qos.logback.core.util.StringUtil;
import com.inspire12.likelionbackend.module.core.threadlocal.context.UserContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO
        try {
            String username = request.getHeader("X-USER-NAME");
            UserContextHolder.setUser(username);
            if (StringUtil.isNullOrEmpty(username)) {
                log.warn("X-USER-NAME header is empty");
                throw new ServletException();
            }
            filterChain.doFilter(request, response);
        } finally {
            UserContextHolder.clear();
        }
    }
}