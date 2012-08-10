package com.jamesward;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpsEnforcer implements Filter {

    private FilterConfig filterConfig;
    
    public static final String X_FORWARDED_PROTO = "x-forwarded-proto";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        System.out.println("doFilter");

        if (request.getHeader(X_FORWARDED_PROTO) != null) {

            if (request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {

                System.out.println("getRequestURI: " + request.getRequestURI());
                System.out.println("getRemoteHost: " + request.getRemoteHost());
                System.out.println("getServerName: " + request.getServerName());

                response.sendRedirect("https://" + request.getRequestURI());
            }

        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // nothing
    }
}