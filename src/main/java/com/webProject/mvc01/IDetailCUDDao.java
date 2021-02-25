package com.webProject.mvc01;

public interface IDetailCUDDao {
	
	BoardInfoDto sltOneDetail(int textNo);
	
	//가이드
	int insertDetail(BoardInfoDto dtoBoard);				//게시글 등록
		
	//그룹
	int insertGroupDetail(BoardInfoDto dtoBoard);				//게시글 등록
	
	int updateDetail(BoardInfoDto dtoBoard);				//게시글 변경
	
	int deleteDetail(int ctgryNo, int textNo);		//게시글 삭제

}
