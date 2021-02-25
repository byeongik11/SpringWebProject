package com.webProject.mvc01;

import java.util.ArrayList;

public interface ICmmntyRDao {

	//게시글 전체 조회
	ArrayList<CmmntyDto> sltAll(int ctgr_no);
	//게시글 보기
	CmmntyDto sltPost(int bod_no);
	//조회수
	void updateCnt(CmmntyDto cmmntyDto);
	//댓글 sltAll
/*	ArrayList<CommentDto> sltCmt(int bod_no);*/
	
	
	//	상혁 작업
	//페이지 목록 조회
	int sltPageMax(PageInfoDto page);								//게시글 갯수 조회
	
	ArrayList<BoardInfoDto> sltPageIndex(PageInfoDto page);			//해당 페이지 목록 가져오기	
	
	//전체 검색 조회
	int sltAllMax(PageInfoDto page);								//조건 조회 : 전체검색 갯수 조회
	
	ArrayList<BoardInfoDto> sltAllIndex(PageInfoDto page);			//조건 조회 : 전체검색
	
	//제목 검색 조회
	int sltTitleMax(PageInfoDto page);								//조건 조회 : 제목검색 갯수 조회
	
	ArrayList<BoardInfoDto> sltTitleIndex(PageInfoDto page);		//조건 조회 : 제목으로 검색
	
	//작성자 검색 조회
	int sltWriterMax(PageInfoDto page);								//조건 조회 : 작성자검색 갯수 조회
	
	ArrayList<BoardInfoDto> sltWriterIndex(PageInfoDto page);		//조건 조회 : 작성자로 검색
	
}
