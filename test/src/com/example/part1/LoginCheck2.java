package com.example.part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginCheck2
 */
@WebServlet("/LoginCheck2")
public class LoginCheck2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected Connection conn = null;
    
    public LoginCheck2() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException{
    	String url = "jdbc:mysql://localhost/sampledb";
    	String user = "root";
    	String password = "meiji";
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		conn = DriverManager.getConnection(url,user,password);
    	}catch(ClassNotFoundException e){
    		log("ClassNotFoundException:" + e.getMessage());
    	}catch(SQLException e){
    		log("SQLException:" + e.getMessage());
    	}catch(Exception e){
    		log("Exception:" + e.getMessage());
    	}
    }
    
    public void destory(){
    	try{
    		if(conn != null){
    			conn.close();
    		}
    	}catch(SQLException e){
    		log("SQLException:" + e.getMessage());
    	}
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
  

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
		
		boolean check = authUser(user,pass);
		if(check){
			/*認証済みにセット*/
			session.setAttribute("login", "OK");
			
			/*本来のアクセス先へ飛ばす*/
			String target = (String)session.getAttribute("target");
			response.sendRedirect(target);
		}else{
			/*認証に失敗したら、ログイン画面に戻す*/
			session.setAttribute("status", "Not Auth");
			response.sendRedirect("./Login2");
		}
		
	}
	
	protected boolean authUser(String user,String pass){
		if(user == null || user.length() == 0 || pass == null || pass.length() == 0){
			return false;
		}
		
		try{
			String sql = "SELECT * FROM user_table WHERE user = ? && pass = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			log("SQLException:" + e.getMessage());
			return false;
		}
	}

}
