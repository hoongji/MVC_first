package edu.web.persistence;

public interface ReplyQuery {
	public static final String TABLE_NAME = "REPLY";
	public static final String COL_REPLY_ID = "REPLY_ID";
	public static final String COL_BOARD_ID = "BOARD_ID";
	public static final String COL_MEMBER_ID = "MEMBER_ID";
	public static final String COL_REPLY_CONTENT = "REPLY_CONTENT";
	public static final String COL_REPLY_DATE_CREATED = "REPLY_DATE_CREATED";
	
	// INSERT INTO REPLY VALUES
	// (REPLY_SEQ.NEXTVAL, ?, ?, ?, SYSDATE);
	public static final String SQL_INSERT =
			"INSERT INTO " + TABLE_NAME + " VALUES " +
					"(REPLY_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
	
	// SELECT * FROM REPLY WHERE BOARD_ID = ?
	// ORDER BY REPLY_ID DESC;
	public static final String SQL_SELECT_BY_BOARD_ID = 
			"SELECT * FROM " + TABLE_NAME + " WHERE " + COL_BOARD_ID
			+ " = ? ORDER BY " + COL_REPLY_ID + " DESC";
	
	// UPDATE_REPLY SET
	// REPLY_CONTENT = ?, REPLY_DATE_CREATED = SYSDATE
	// WHERE REPLY_ID = ?;
	public static final String SQL_UPDATE = 
			"UPDATE " + TABLE_NAME + " SET " + 
			COL_REPLY_CONTENT + " = ?, " + COL_REPLY_DATE_CREATED + " = SYSDATE "
			+ " WHERE " + COL_REPLY_ID + " = ?";
	
	// DELETE REPLY WHERE REPLY_ID = ?;
	public static final String SQL_DELETE = 
			"DELETE " + TABLE_NAME + " WHERE "
			+ COL_REPLY_ID + " = ?";
	
}








