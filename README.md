명세서

1. 주제 : 게시판 로그인 + 댓글

2. 기능 : 
<게시판>
- 게시글을 등록할 수 있다.
- 게시글을 게시글 아이디에 맞게 조회할 수 있다.
+ 페이징 처리
- 게시글을 전체 조회할 수 있다. 
- 게시글을 수정할 수 있다.
- 게시글을 삭제할 수 있다.

<댓글> - 비동기
- 댓글을 등록할 수 있다.
- 댓글을 전체 조회할 수 있다. 
- 댓글을 수정할 수 있다. 
- 댓글을 삭제할 수 있다. 

<로그인>
- 로그인을 수행할 수 있다 
- 로그아웃을 수행할 수 있다 
- 로그인된 사용자만 글을 작성할 수 있다.

3. DB 테이블 : 
//사용자한테 보여주는 테이블 위주로 짜기

<BOARD>

게시글 번호(BOARD_ID : NUMBER)-PK

게시글 제목(BOARD_TITLE : VARCHAR2(100))

게시글 내용(BOARD_CONTENT : VARCHAR2(500))

게시글 작성자(MEMBER_ID : VARCHAR2(20))

게시글 작성일(BOARD_DATE_CREATED : TIMESTAMP)


<REPLY>
  
댓글 번호(REPLY_ID : NUMBER)

게시글 번호(BOARD_ID : NUMBER)-PK

댓글 작성자(MEMBER_ID : VARCHAR2(20))

댓글 내용(REPLY_CONTENT : VARCHAR2(100))

댓글 작성일(REPLY_DATE_CREATED : TIMESTAMP)

