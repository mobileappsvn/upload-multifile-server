package com.robert.ws.authenticate.jsonwebtoken.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public final class AuthenticationFilter implements Filter {
	private FilterConfig filterConfig = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String password = ((HttpServletRequest) request).getParameter("password");

		if (password.equals("opensesame")) {
			chain.doFilter(request, response);
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>");
			out.println("Incorrect Password");
			out.println("</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("<H1>Incorrect Password</H1>");
			out.println("Sorry, that password was incorrect.");
			out.println("</BODY>");
			out.println("</HTML>");
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		
		System.out.println("init(FilterConfig):" + this.filterConfig.toString());
	}
}
