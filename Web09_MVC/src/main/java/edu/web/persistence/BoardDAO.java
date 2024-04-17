package edu.web.persistence;

import java.util.List;

import edu.web.domain.BoardVO;
import edu.web.util.PageCriteria;

public interface BoardDAO {
	// 게시글 등록
	int insert(BoardVO vo);
	
	//게시글 전체 조회
	List<BoardVO> select();
	
	// 게시글 상세 조회
	BoardVO select(int boardId);
	
	//게시글 수정
	int update(BoardVO vo);
	
	//게시글 삭제
	int delete(int boardId);
	
	//페이징 처리 
	List<BoardVO> select(PageCriteria criteria);
	
	int getTotalCount();
	
}
