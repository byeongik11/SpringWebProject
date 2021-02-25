package com.webProject.mvc01;

import java.util.ArrayList;

public interface ICommentDao {

	CommentDto sltOneCmt(int ctgryNo, int textNo, int cmtNo);	//단건 조회
	
	ArrayList<CommentDto> sltCmtAll(int ctgryNo, int textNo);	//다건 조회
	
	int insertCmt(CommentDto dtoCmt);							//댓글 등록
	
	int updateCmt(CommentDto dtoCmt);							//댓글 수정
	
	int deleteCmt(int ctgryNo, int textNo, int cmtNo);			//댓글 삭제
	
}
