package com.optimagrowth.license.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Artyom Zheltyshev on 01.03.2025
 */
@Component
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContext context = UserContextHolder.getContext();

        context.setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        context.setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        context.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        context.setOrganizationId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));

        chain.doFilter(request, response);
    }

}
