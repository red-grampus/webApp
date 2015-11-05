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
 * Servlet implementation class Login2
 */
@WebServlet("/Login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=Shift_JIS");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ログインページ</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<h1>ログイン画面</h1>");
		
		HttpSession session = request.getSession(true);
		String target = (String)session.getAttribute("target");
		System.out.println(target);
		
		/*認証失敗から呼び出されたのかどうか*/
		Object status = session.getAttribute("status");
		
		if(status != null){
			out.println("<p>認証に失敗しました</p>");
			out.println("<p>再度ユーザ名とパスワードを入力して下さい</p>");
			
			session.setAttribute("status", null);
		}
		
		out.println("<form method=\"POST\" action=\"./LoginCheck2\" name=\"loginform\">");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>ユーザ名</td>");
		out.println("<td><input type=\"text\" name=\"user\" size=\"32\"></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>パスワード</td>");
		out.println("<td><input type=\"password\" name=\"pass\" size=\"32\"></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=\"submit\" value=\"login\"></td>");
		out.println("<td><input type=\"reset\" value=\"reset\"></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
