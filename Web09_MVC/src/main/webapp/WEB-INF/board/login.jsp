<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	    <form action="login.do" method="post">
	        <div>
	            <label for="userid">아이디:</label>
	            <input type="text" id="userid" name="userid" required>
	        </div>
	        <div>
	            <label for="password">비밀번호:</label>
	            <input type="password" id="password" name="password" required>
	        </div>
	        
	        <div>
	            <button type="submit">로그인</button>
	        </div>
	        
	    </form>
</body>
</html>