package org.pan.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Object obj=request.getSession().getAttribute("admin");
        if(obj==null) response.sendRedirect(request.getContextPath()+"/admin.do");
        else{
            String msg=(String)obj;
            if(msg.equals("true")) filterChain.doFilter(request,response);
            else response.sendRedirect(request.getContextPath()+"/admin.do");
        }
    }

    @Override
    public void destroy() {

    }
}
