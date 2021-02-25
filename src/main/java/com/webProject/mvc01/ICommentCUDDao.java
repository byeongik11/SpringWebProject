package com.webProject.mvc01;

public interface ICommentCUDDao {
	
	/*int cmtNoMax();*/
	
	int cmtInsert(CommentDto cmtDto);							//댓글 등록
	
	int cmtUpdate(CommentDto cmtDto);							//댓글 수정
	
	int cmtDelete(int ctgryNo, int textNo, int cmtNo);			//댓글 삭제
	
}
