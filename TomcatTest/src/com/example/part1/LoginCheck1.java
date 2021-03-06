package com.example.part1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheck1
 */

public class LoginCheck1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=Shift_JIS");
		PrintWriter out = response.getWriter();
		
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession(true);
		
		boolean check = authUser(user, pass);
		if(check){
			/*認証済みにセット*/
			session.setAttribute("login", "OK");
			
			/*本来のリンク先へ飛ばす*/
			String target = (String)session.getAttribute("target");
			response.sendRedirect(target);
		}else{
			/*認証失敗したら、ログイン画面に戻す*/
			session.setAttribute("status", "Not Auth");
			response.sendRedirect("./Login2");
		}
	}
	
	protected boolean authUser(String user, String pass){
		if(user == null || user.length() == 0 || pass == null || pass.length() == 0){
			return false;
		}
		
		return true;
	}

}
