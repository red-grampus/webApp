package com.example.part1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AuthFilter1
 */
@WebServlet("/AuthFilter1")
public class AuthFilter1 implements Filter {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthFilter1() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		try{
			String target = ((HttpServletRequest)request).getRequestURI();
		
			HttpSession session = ((HttpServletRequest)request).getSession(false);
		
			if(session == null){
				/*まだ認証されていない*/
				session = ((HttpServletRequest)request).getSession(true);
				session.setAttribute("target", target);
			
				((HttpServletResponse)response).sendRedirect("/Login");
			}else{
				Object loginCheck = session.getAttribute("login");
				if(loginCheck == null){
					/*まだ認証されていない*/
					session.setAttribute("target", target);
					((HttpServletResponse)response).sendRedirect("/Login");
				}
			}
		
			chain.doFilter(request, response);
		}catch(ServletException se){
		}catch(IOException e){
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
