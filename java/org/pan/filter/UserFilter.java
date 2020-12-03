package org.pan.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Object obj=request.getSession().getAttribute("user");
        if(obj==null) response.sendRedirect(request.getContextPath()+"/login.html");
        else{
            int userId=(int)obj;
            if(userId!=0) filterChain.doFilter(request,response);
            else response.sendRedirect(request.getContextPath()+"/login.html");
        }



    }

    @Override
    public void destroy() {

    }
}
