package com.webProject.mvc01;

public interface ICommentCUDSvc {

	String cmtInsert(CommentDto cmtDto);
	
	String cmtUpdate();
	
	String cmtDelete();
	
}
