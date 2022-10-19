package edu.java.web.client;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/html"})
public class GzipFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("\n Init filter!\n");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        PrintWriter out = servletResponse.getWriter();
//        out.println("============ \n Filter intercepted! \n ===========");
//        out.flush();
//
//        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String encoding = httpRequest.getHeader("Accept-Encoding");
        if(encoding != null && encoding.contains("gzip")){
            httpResponse.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
            filterChain.doFilter(servletRequest, gzipResponse);
            gzipResponse.close();
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        System.out.println("\n Destroy filter!\n");
    }
}
