package com.example.part1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MysqlTest
 */
@WebServlet("/MysqlTest")
public class MysqlTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MysqlTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/sampledb?user=root&password=meiji");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT userid,status FROM userinfo");
			
			response.setContentType("test/plain");
			while(rs.next()){
				response.getWriter().write("userid=" + rs.getString("userid") + ",");
				response.getWriter().write("status=" + rs.getString("status") + "\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(SQLException e){e.printStackTrace();}}
			if(stmt!=null){try{stmt.close();}catch(SQLException e){e.printStackTrace();}}
			if(conn!=null){try{conn.close();}catch(SQLException e){e.printStackTrace();}}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
