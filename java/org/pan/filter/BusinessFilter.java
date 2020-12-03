package org.pan.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BusinessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Object obj=request.getSession().getAttribute("business");
        if(obj==null) response.sendRedirect(request.getContextPath()+"/bussLogin.html");
        else{
            int businessId=(int)obj;
            if(businessId!=0) filterChain.doFilter(request,response);
            else response.sendRedirect(request.getContextPath()+"/bussLogin.html");
        }

    }

    @Override
    public void destroy() {

    }
}
