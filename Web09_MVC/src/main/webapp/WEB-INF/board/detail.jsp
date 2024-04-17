<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardTitle }</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" > 
</script>
</head>
<body>
	<h2>글 보기</h2>
	<div>
		<p>글 번호 : ${board.boardId }</p>
	</div>
	<div>
		<p>제목 : </p>
		<p>${board.boardTitle }</p>
	</div>
	<div>
		<p>작성자 : ${board.memberId }</p>
		<p>작성일 : ${board.boardDateCreated }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${board.boardContent }</textarea>
	</div>
	
	<a href="index.jsp"><input type="button" value="글 목록"></a>
	<a href="update.do?boardId=${board.boardId }">
	<input type="button" value="글 수정"></a>
	<form action="delete.do" method="POST">
		<input type="hidden" id="boardId" name="boardId" value="${board.boardId }">
		<input type="submit" value="글 삭제">
	</form>

	<div style="text-align: center;">
		<input type="text" id="memberId">
		<input type="text" id="replyContent">
		<button id="btnAdd">작성</button>
	</div>
	<hr>
	<div style="text-align: center;">
		<div id="replies"></div>
	</div>

	<div>
		<br><br><br><br><br><br><br><br><br><br><br><br>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			getAllReplies(); // 함수 호출 코드 추가
			
			$('#btnAdd').click(function(){
				let boardId = $('#boardId').val(); // 게시판 번호 데이터
				let memberId = $('#memberId').val(); // 작성자 데이터 
				let replyContent = $('#replyContent').val(); // 댓글 내용
				let obj = {
						'boardId' : boardId,
						'memberId' : memberId,
						'replyContent' : replyContent
				};
				console.log(obj);
				
				// $.ajax로 송수신 
				$.ajax({
					type : 'POST',
					url : 'replies/add',
					data : {'obj' : JSON.stringify(obj)}, //JSON으로 변환
					success : function(result) {
						console.log(result);
						if(result == 1){
							alert('댓글 입력 성공!');
							getAllReplies();
						}
					}
				});//end ajax
			});// end btnAdd.click()
			
			// 게시글 댓글 전체 가져오기 
			function getAllReplies() {
				// 댓글을 가져오기 위해 boardId 필요
				let boardId = $('#boardId').val();
				
				// url에 boardId 전송
				let url = 'replies/all?boardId=' + boardId;
				
				// 가져올 데이터가 JSON이므로
				// getJSON으로 파싱하는게 편함
				$.getJSON(
					url,
					function(data){
						// data : 서버에서 전송받은 list 데이터가 저장되어 있음,
						// getJSON() 에서 json데이터는
						// javascript object로 자동 parsing 됨
						console.log(data); // data의 타입 = obj
						
						let list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수 
						
						// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수 
						$(data).each(function(){
							// this : 컬렉션의 각 인덱스 데이터를 의미
							console.log(this); 
							// 데이터 화면 출력 코드 
							
							// string을 date 타입으로 변경
							var replyDateCreated = new Date(this.replyDateCreated); // Data Date Data Date...
							
							list += '<div class="reply_item">'
								+ '<pre>'
								+ '<input type="hidden" id="replyId" value="'+ this.replyId +'">'
								+ this.memberId
								+ '&nbsp;&nbsp;' // 공백
								+ '<input type="text" id="replyContent" value="'+ this.replyContent +'">'
								+ '&nbsp;&nbsp;' // 공백
								+ replyDateCreated
								+ '&nbsp;&nbsp;' // 공백
								+ '<button class="btn_update">수정</button>'
								+ '<button class="btn_delete">삭제</button>'
								+ '</pre>'
								+ '</div>';
						
						});//end each()
						
						$('#replies').html(list);
					}
				); //end getJSON
			}//end getAllReplies()
			
			// 수정 버튼을 클릭하면 선택된 댓글 수정
			$('#replies').on('click', '.reply_item .btn_update', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장 
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근 
				var replyId = $(this).prevAll('#replyId').val();
				var replyContent = $(this).prevAll('#replyContent').val();
				console.log("선택된 댓글 번호 : " + replyId + ", 댓글 내용 : " + replyContent);
				
				//ajax로 데이터 전송하여
				//댓글 수정 기능 수행하고
				//수행 결과를 리턴하는 코드
				//ajax 요청 
				$.ajax({
					type : 'POST',
					url : 'replies/update',
					data: {
						'replyId' : replyId,
						'replyContent' : replyContent
					},
					success : function(result) {
						console.log(result);
						if(result == 'success'){
							alert('댓글 수정 성공!');
							getAllReplies();
						}
					
					}
				}); // end ajax()
			});// end replies.on()
			
			
			$('#replies').on('click', '.reply_item .btn_delete', function(){
				console.log(this);
				
				// 선택된 댓글의 replyId, replyContent 값을 저장 
				// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근 
				var replyId = $(this).prevAll('#replyId').val();
				
				console.log("선택된 댓글 번호 : " + replyId );
				
				//ajax로 데이터 전송하여
				//댓글 삭제 기능 수행하고
				//수행 결과를 리턴하는 코드
				//ajax 요청 
				$.ajax({
					type : 'POST',
					url : 'replies/delete',
					data: {
						'replyId' : replyId,
						
					},
					success : function(result) {
						console.log(result);
						if(result == 'success'){
							alert('댓글 삭제 성공!');
							getAllReplies();
						}
					}
				}); // end ajax()
			});// end replies
			
			
			
		});//end document()
	</script>

</body>
</html>









