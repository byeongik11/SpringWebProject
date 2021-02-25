package com.webProject.mvc01;

import java.util.ArrayList;

public interface IBoardRDao {
	//Common
	ArrayList<BoardInfoDto> sltAll();									//전체조회
	
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
	
	//지역명 검색 조회
	int sltAreaMax(PageInfoDto page);								//조건 조회 : 지역명검색 갯수 조회
	
	ArrayList<BoardInfoDto> sltAreaIndex(PageInfoDto page);		//조건 조회 : 지역명으로 검색
		
	BoardInfoDto boardData(int ctgryNo, int textNo);	
}
