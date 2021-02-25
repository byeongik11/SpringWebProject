package com.webProject.mvc01;


import org.springframework.ui.Model;

public interface ICmmntyCUDSvc {
	//게시글 등록 실행
	String cmmntyInsert(Model model, CmmntyDto cmmntyDto);
	//게시글 수정 화면 가져오기
	String getUpdate(Model model, CmmntyDto cmmntyDto);
	//게시글 수정 실행
	String cmmntyUpdate(Model model, CmmntyDto cmmntyDto);
	//게시글 삭제 실행
	String cmmntyDelete(Model model, CmmntyDto cmmntyDto);
}
