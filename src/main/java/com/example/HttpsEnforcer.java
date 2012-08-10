package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpsEnforcer extends HandlerInterceptorAdapter {

    public static final String X_FORWARDED_PROTO = "x-forwarded-proto";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getHeader(X_FORWARDED_PROTO) != null) {
            
            if (request.getHeader(X_FORWARDED_PROTO).indexOf("https") != 0) {
                
                System.out.println("getRequestURI: " + request.getRequestURI());
                System.out.println("getRemoteHost: " + request.getRemoteHost());
                System.out.println("getServerName: " + request.getServerName());
                
                response.sendRedirect("https://" + request.getRequestURI());
                return false;
            }
            
        }
        
        return true;
    }
    
}