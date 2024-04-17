package edu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BOARD_URL = "WEB-INF/board/";
    private static final String LOGIN = "login";
    private static final String REGISTER = "register";
    private static final String EXTENSION = ".jsp";
    private static final String SERVER_EXTENSION = ".do";
	
    public LoginController() {
        
    }

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// .do로 끝나는 애들이 옴
		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		
		System.out.println("호출 경로 : " + requestURI);
		System.out.println("호출 방식 : " + requestMethod);
		
		if(requestURI.contains(LOGIN + SERVER_EXTENSION)) {
			System.out.println("login 호출 확인");
			if(requestMethod.equals("GET")) {
				loginGET(request,response);
			}else if(requestMethod.equals("POST")) {
				loginPOST(request, response);
			}
		}
	}

	// login.jsp 페이지가 호출되어야 한다
	private void loginGET(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("loginGET");
		String path = BOARD_URL + LOGIN + EXTENSION;
		RequestDispatcher dispatcher
			= request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}//end loginGET()

	
	private void loginPOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("loginPOST");
		javax.servlet.http.HttpSession session = request.getSession(); // 세션 가져오기
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		System.out.println("userid = " + userid + ", password = " + password);
		
		PrintWriter out = response.getWriter();
		if(userid.equals("")) { // 입력한 적이 있는 아이디&비밀번호라면 => 이 부분을 모르겟음; 
			session.setAttribute("userid", userid);
			session.setMaxInactiveInterval(60);
			
			out.print("<script>alert('로그인 성공');</script>");
			out.print("<script>location.href='register.jsp';</script>");
		}else {
			out.print("<script>alert('다시 입력');</script>");
			out.print("<script>location.href='login.jsp';</script>");
		}
		
	}//end loginPOST()




	

}
