package com.briup.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(value= {"/confirm.jsp","/confirmSuc.jsp","/orders.jsp","/register.jsp","/shopCart.jsp","/userinfo.jsp","/viewBook.jsp"})
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response =(HttpServletResponse) resp;
		HttpSession session = request.getSession();
		Object flag = session.getAttribute("flag");
		if (flag==null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
