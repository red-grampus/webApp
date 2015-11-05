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
 * Servlet implementation class Login1
 */

public class Login1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login1() {
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
		
		out.println("<p>本来はここでログインするためのフォームが表示されます");
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
			/*セッションが開始されずにここへ来た場合は無条件でエラー表示*/
			out.println("<p>不正なアクセスです</p>");
		}else{
			/*今回は無条件で認証を許可する*/
			out.println("<p>認証が行われました</p>");
			
			/*認証済みにセット*/
			session.setAttribute("login", "OK");
			
			/*本来のアクセス先へのリンクを設定*/
			String target = (String)session.getAttribute("target");
			out.println("<a href=\"" + target + "\">ページを表示する</a>");
		}
		
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
