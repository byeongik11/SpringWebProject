package com.webProject.mvc01;

import org.springframework.ui.Model;

public interface IBoardRSvc {
	
	//국내
	String sltDomesticPage(Model model, SearchDto serDto, PageInfoDto pageDto);
	
	String sltDomesticContent(Model model,int textNo);									//클릭한 게시글 결과 뿌리기
	
	//해외
	String sltAbroadPage(Model model, SearchDto serDto , PageInfoDto pageDto);
	
	String sltAbroadContent(Model model,int textNo);	
	
	//그룹
	String sltGroupPage(Model model, SearchDto serDto , PageInfoDto pageDto);
	
	String sltGroupContent(Model model,int textNo, int mbrCode);									//클릭한 게시글 결과 뿌리기
		
	String sltAll(Model model);		//전체조회
	
	

	
}
