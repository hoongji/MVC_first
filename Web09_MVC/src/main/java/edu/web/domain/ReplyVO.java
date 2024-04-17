package edu.web.domain;

import java.util.Date;

public class ReplyVO {
	private int replyId;
	private int boardId;
	private String memberId;
	private String replyContent;
	private Date replyDateCreated;
	
	public ReplyVO() {
	}

	public ReplyVO(int replyId, int boardId, String memberId, String replyContent, Date replyDateCreated) {
		super();
		this.replyId = replyId;
		this.boardId = boardId;
		this.memberId = memberId;
		this.replyContent = replyContent;
		this.replyDateCreated = replyDateCreated;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDateCreated() {
		return replyDateCreated;
	}

	public void setReplyDateCreated(Date replyDateCreated) {
		this.replyDateCreated = replyDateCreated;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyId=" + replyId + ", boardId=" + boardId + ", memberId=" + memberId + ", replyContent="
				+ replyContent + ", replyDateCreated=" + replyDateCreated + "]";
	}
	
	
	
	
	
}
