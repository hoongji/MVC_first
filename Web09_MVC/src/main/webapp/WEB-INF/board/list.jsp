<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
   border-style : solid;
   border-width : 1px;
   text-align : center;
}

ul {
   list-style-type : none;
}

li {
   display : inline-block;
}
</style>
<meta charset="UTF-8">
<title>게시판 메인 페이지</title>
</head>
<body>
   <h1>게시판 메인</h1>
   
   <hr>
   <table>
      <thead>
         <tr>
            <th style="width : 60px">번호</th>
            <th style="width : 700px">제목</th>
            <th style="width : 120px">작성자</th>
            <th style="width : 100px">작성일</th>
         </tr>
      </thead>
      <tbody>
         <c:forEach  items="${boardList}" var="board">
            <tr>
               <td>${board.boardId }</td>
               <td><a href="detail.do?boardId=${board.boardId}">${board.boardTitle}</a></td>
               <td>${board.memberId }</td>
               <td>${board.boardDateCreated }</td>
            </tr>
         </c:forEach>
      </tbody>
   </table>

	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list.do?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
	
		<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }"
		var="num">
			<li><a href="list.do?page=${num }">${num }</a></li>
		</c:forEach>
		
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list.do?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>
	
	<!-- 글 작성 누르면 로그인 페이지로 이동 -->
	<div>
    	<button onclick="location.href='login.do'">글 작성</button>
	</div>
	
	
</body>
</html>







