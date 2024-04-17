<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardTitle }</title>
</head>
<body>
   <h2>글 수정 페이지</h2>
   <form action="update.do" method="POST">
      <div>
         <p>글 번호 : ${board.boardId }</p>
         <input type="hidden" name="boardId" value="${board.boardId }">
      </div>
      <div>
         <p>제목 : </p>
         <input type="text" name="boardTitle" value="${board.boardTitle }">
      </div>
      <div>
         <p>작성자 : ${board.memberId }</p>
         <p>작성일 : ${board.boardDateCreated }</p>
      </div>
      <div>
         <textarea rows="20" cols="120" name="boardContent">${board.boardContent }</textarea>
      </div>
      <div>
         <input type="submit" value="등록">
      </div>
   </form>   

</body>
</html>









