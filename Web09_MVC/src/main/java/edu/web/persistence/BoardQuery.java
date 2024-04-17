package edu.web.persistence;

public interface BoardQuery {
	public static final String TABLE_NAME = "BOARD";
	public static final String COL_BOARD_ID = "BOARD_ID";
	public static final String COL_BOARD_TITLE = "BOARD_TITLE";
	public static final String COL_BOARD_CONTENT = "BOARD_CONTENT";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_BOARD_DATE_CREATED = "BOARD_DATE_CREATED";
	
	
	// INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE);
		public static final String SQL_INSERT = 
				"INSERT INTO " + TABLE_NAME + " VALUES " + "(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		
	//  정렬시 중첩 FOR문을 쓴다. 그래서 PK로 정렬한다
		// SELECT * FROM BOARD ORDER BY BOARD_ID DESC; 내림차순 정렬
		public static final String SQL_SELECT =	"SELECT * FROM " + TABLE_NAME 
					+ " ORDER BY " + COL_BOARD_ID + " DESC";
		
   
		// 누른 게시글 조회  -> SELECT * FROM BOARD WHERE BOARD_ID = ?; 
	 	public static final String SQL_SELECT_BY_BOARD_ID = 
	 			"SELECT * FROM " + TABLE_NAME + " WHERE " + COL_BOARD_ID + " = ?";
	 	
	 	//UPDATE BOARD
	 	//SET BOARD_TITLE = ?, 
	 	//BOARD_CONTENT = ?, 
	 	//BOARD_DATE_CREATED = SYSDATE 
	 	//WHERE BOARD_ID = ?;
	 	public static final String SQL_UPDATE = "update " + TABLE_NAME
	 			+ " set " + 
	 			COL_BOARD_TITLE + " = ?, " +
	 			COL_BOARD_CONTENT + " = ?, " +
	 			COL_BOARD_DATE_CREATED + " = ? " +
	 			" where " + COL_BOARD_ID + " = ?";
	 	
	 	
	 // DELETE FROM BOARD WHERE BOARD_ID = ?;
	 	public static final String SQL_DELETE = "delete " + TABLE_NAME 
	 			+ " where " + COL_BOARD_ID + " = ?";
	 	
	// 게시글 페이징 처리
	// 	SELECT * FROM(
	// 		    SELECT ROW_NUMBER() OVER (ORDER BY BOARD_ID DESC) AS RN, BOARD.*
	// 		    FROM BOARD
	// 		)WHERE RN BETWEEN 2 AND 5;
	 	public static final String SQL_SELECT_PAGESCOPE = 
	 			"SELECT * FROM("
	 			+ " SELECT ROW_NUMBER() OVER (ORDER BY BOARD_ID DESC) AS RN, BOARD.*"
	 			+ "	FROM BOARD"
	 			+ ")WHERE RN BETWEEN ? AND ?";
	
	 // SELECT COUNT(BOARD_ID) TOTAL_CNT FROM BOARD;
	 public static final String SQL_TOTAL_CNT = 
	 		"SELECT COUNT(BOARD_ID) TOTAL_CNT FROM BOARD";
	 	
	 	
	 	
	 	
}
