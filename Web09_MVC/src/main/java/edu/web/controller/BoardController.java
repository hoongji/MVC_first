package edu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.web.domain.BoardVO;
import edu.web.persistence.BoardDAO;
import edu.web.persistence.BoardDAOImple;
import edu.web.util.PageCriteria;
import edu.web.util.PageMaker;


@WebServlet("*.do") // do -> 서버사이드로 가라 , .do : 클라이언트에서 서버사이드로 가라 , *.do : ~.do 로 선언된 HTTP 호출에 대해 반응
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String BOARD_URL = "WEB-INF/board/";
    private static final String MAIN = "index";
    private static final String LIST = "list";
    private static final String REGISTER = "register";
    private static final String DETAIL = "detail";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String EXTENSION = ".jsp";
    private static final String SERVER_EXTENSION = ".do";
    private static BoardDAO dao; // DB 연결
    
    public BoardController() {
        dao = BoardDAOImple.getInstance();
        System.out.println("BoardController()");
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// .do 로 끝나는 애들이 옴
		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		
		System.out.println("호출 경로 : " + requestURI);
		System.out.println("호출 방식 : " + requestMethod);
		
		if(requestURI.contains(LIST + SERVER_EXTENSION)) { // list.do 포함되면
			System.out.println("list 호출 확인"); 
			list(request,response);
		}else if(requestURI.contains(REGISTER + SERVER_EXTENSION)) { // register.do 호출하면 
			System.out.println("register 호출 확인"); 
			if(requestMethod.equals("GET")) { // GET 방식(페이지 불러오기)
				registerGET(request, response); 
			}else if(requestMethod.equals("POST")) { // POST 방식(DB에 저장)
				registerPOST(request, response);
			}
			
		}else if(requestURI.contains(DETAIL + SERVER_EXTENSION)) { // detail.do 호출하면 
			System.out.println("detail 호출 확인");
			detail(request, response);
		}else if(requestURI.contains(UPDATE + SERVER_EXTENSION)) {
			System.out.println("update 호출 확인");
			if(requestMethod.equals("GET")) {
				updateGET(request,response);
			}else if(requestMethod.equals("POST")) {
				updatePOST(request,response);
			}
		}else if(requestURI.contains(DELETE + EXTENSION)){
			System.out.println("delete 호출 확인");
			if(requestMethod.equals("POST")) {
				deletePOST(request,response);
			}
		}
	}//end service()

	
	

	

	// TODO : 전체 게시판 내용(list)을 DB에서 가져오고, 그 데이터를 list.jsp 페이지에 전송
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("list()");
		
		//List<BoardVO> list = dao.select(); // VO 객체 생성
		String page = request.getParameter("page");
		
		PageCriteria criteria = new PageCriteria();
		
		if(page != null) {
			criteria.setPage(Integer.parseInt(page));
		}
		
		List<BoardVO> list = dao.select(criteria);
		
		// list.jsp 페이지로 포워딩
	    String path = BOARD_URL + LIST + EXTENSION;
	    
	   // System.out.println(path);
	    RequestDispatcher dispatcher 
	    	= request.getRequestDispatcher(path);
	   
		
	 // 데이터를 list.jsp 페이지에 전송한다 
	    request.setAttribute("boardList", list); // 서버에서 JSP 페이지로 데이터를 전달
	    
	    
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCriteria(criteria);
	    int totalCount = dao.getTotalCount();
	    pageMaker.setTotalCount(totalCount);
	    pageMaker.setPageData();
	    System.out.println("전체 게시글 수 : " + pageMaker.getTotalCount());
	    System.out.println("현재 선책된 페이지 : " + criteria.getPage());
	    System.out.println("한 페이지 당 게시글 수 : "
	    		+ criteria.getNumsPerPage());
	    System.out.println("페이지 링크 번호 개수 : "
	    		+ pageMaker.getNumsOfPageLinks());
	    System.out.println("시작 페이지 링크 번호 : " 
	    		+ pageMaker.getStartPageNo());
	    System.out.println("끝 페이지 링크 번호 : "
	    		+ pageMaker.getEndPageNo());
	    
	    request.setAttribute("pageMaker", pageMaker);
	    dispatcher.forward(request, response);
	   
		
	}//end list()
		
	
	// TODO : register.jsp 페이지 호출
	private void registerGET(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("registerGET()");
		String path = BOARD_URL + REGISTER + EXTENSION;
		RequestDispatcher dispatcher 
			= request.getRequestDispatcher(path); // url이 사용자에게 안뜸 -> jsp를 불러올때 우회적으로 가도록 한 것
		dispatcher.forward(request, response);
		
	}//end registerGET()
	
	
	// TODO : register.jsp form에서 전송된 데이터를 DB 테이블에 등록 
	// TODO : index.jsp로 이동 ->이동은 dispatcher 쓰면 안댐 -> redirect? location?
	private void registerPOST(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("registerPOST()");
		//  register.jsp form에서 전송된 데이터 가져오기 request.getParameter()
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String memberId = request.getParameter("memberId");
		
		// 데이터를 db 테이블에 등록
		BoardVO vo = new BoardVO(0, boardTitle, boardContent, memberId, null);
		System.out.println(vo);
		
		int result = dao.insert(vo);
		
		if (result > 0) {
		    System.out.println("데이터 등록 성공");
		} else {
		    System.out.println("데이터 등록 실패");
		}
		
		// index.jsp로 이동 =>  dispatcher 쓰면 안댐
		String path = MAIN + EXTENSION;
		System.out.println(path);
		response.sendRedirect(path); // 경로가 안보여서 
	}//end registerPOST()

	
	// TODO : DB 테이블에서 상세 조회 데이터를 가져와서, detail.jsp 페이지로 전송 및 페이지 출력
	private void detail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("detail()");
		// DB 테이블에서 상세 조회 데이터

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		BoardVO result = dao.select(boardId); // DB에서 상세 조회 데이터 가져오기
		System.out.println(result);
	    
	    // detail.jsp에 출력한다 ->  포워딩
	    String path = BOARD_URL + DETAIL + EXTENSION;
	    RequestDispatcher dispatcher 
		= request.getRequestDispatcher(path); 
	    request.setAttribute("board", result);
	    dispatcher.forward(request, response);
	    
	}//end detail()
	

	// TODO : DB 테이블에서 상세 조회한 게시글 데이터를 전송하고, update.jsp 페이지를 호출
	private void updateGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("updateGET()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));

		BoardVO result = dao.select(boardId);
		System.out.println(result);
		 
	    
	   // update.jsp 페이지를 호출 = 포워딩 (전송 & 호출)
		String path = BOARD_URL + UPDATE + EXTENSION;
	    RequestDispatcher dispatcher 
		= request.getRequestDispatcher(path); 
	    dispatcher.forward(request, response);
		
	}//end updateGET()
	
	
	// TODO : update.jsp에서 전송된 수정할 데이터를 DB로 전송하여 테이블 수정 수행
	// TODO : 수정이 완료되면, detail.jsp로 이동(이동할 때 어떤 값을 전송해야 할걸?) -> forwarding 쓰면 안댐..get방식에서 쓰는 방식
	private void updatePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("updatePOST()");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");

		BoardVO vo = new BoardVO(boardId, boardTitle, boardContent, null, null);
		
		int result = dao.update(vo);
		
		
		if(result == 1){
			String path = DETAIL + EXTENSION ;
			response.sendRedirect(path + "?boardId=" + boardId);
		}
		
	}//end updatePOST()
	
	
	// TODO : 게시글 번호를 전송받아서, DB 테이블에서 데이터 삭제
	// TODO : 삭제가 완료되면, index.jsp로 이동
	private void deletePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("deletePOST()");
		// 게시글 번호를 전송받아서, DB 테이블에서 데이터 삭제

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		int result = dao.delete(boardId);
		
		if(result == 1){
			response.sendRedirect(MAIN + EXTENSION);
		}
		
	}//end deletePOST()


	
}
