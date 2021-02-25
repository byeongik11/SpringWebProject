package com.webProject.mvc01;

import java.util.ArrayList;

public interface ICommentRDao {
	
	// 댓글 PK, 최대값 찾기
	int cmtNoMax();
	
	int cmtLvl(int prt_cmtno);
	
	CommentDto sltCmtNo(int cmt_no);

	ArrayList<CommentDto> sltCmtAll(CommentDto cmtDto);	//다건 조회
	
}
