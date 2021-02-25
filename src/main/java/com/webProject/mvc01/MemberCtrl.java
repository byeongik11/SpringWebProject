package com.webProject.mvc01;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//회원가입, 로그인, ID/PW찾기, 마이페이지 조회
@Controller
public class MemberCtrl {
	
	@Autowired
	private IMemberSvc mbrSvc;
	
	@RequestMapping("myPage")
	public String myPage() {
		return "myPage";
	}
	
	//마이페이지 호출 및 정보조회결과 뿌리기
	@RequestMapping(value="myPage.do")
	public String mbrMyPage(HttpSession session, Model model) {		//-----
		
		int mem_num = (int) session.getAttribute("MEM_NUM");
		
		String result = mbrSvc.mbrSltOne(model, mem_num);
		
		return result;
	}
	
	//회원등록(가입)화면 띄우기(viewName)
	@RequestMapping(value="mbrJoin")									
	public String mbrRgt() {
		return "mbrJoin";
	}
	
	//로그인 화면 띄우기
	@RequestMapping(value="login")									
	public String getLogin() {	
		return "login";
	}
	
	//로그인 실행
	@RequestMapping(value="login.do")									
	public String mbrLogin(Model model, HttpSession session, MbrDto mbrDto) {

		String viewName = mbrSvc.login(model, session, mbrDto);
		
		return viewName;
	}
	
	//ID 중복조회 실행 
	@RequestMapping(value="idChk.do", produces = "application/text;chartset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String idChk(@ModelAttribute("mem_id") String mem_id) {
		
		String result = mbrSvc.mbrIdChk(mem_id);
		
		return result;
	}
	
	//회원등록(가입) 실행(id단건조회 포함되야함)
	@RequestMapping(value="joinInsert.do")
	public String mbrRgtInsert(Model model, MbrDto mbrDto) { 	
		
		String viewName = mbrSvc.mbrInsert(model, mbrDto);
		
		return viewName;
	}
	
	//마이페이지에서 회원 수정실행 및 수정결과 뿌리기
	@RequestMapping(value="joinUpdate.do")
	public String mbrRgtUpdate(HttpSession session, Model model, MbrDto mbrDto) {
		
		int mem_num = (int) session.getAttribute("MEM_NUM");
		
		String viewName = mbrSvc.mbrUpdate(model, mem_num, mbrDto);
		
		System.out.println(mbrDto.getMem_id());
		
		return viewName;
	}
	
	//마이페이지에서 회원 탈퇴실행 및 실행결과 뿌리기
	@RequestMapping(value="joinDelete.do")
	public String mbrRgtDelete(HttpSession session, Model model, MbrDto mbrDto) { 	
		
		int mem_num = (int) session.getAttribute("MEM_NUM");
		
		String viewName = mbrSvc.mbrDelete(model, mem_num, mbrDto);
		
		return viewName;
	}
	
	//ID&PW 화면 띄우기(viewName)
	@RequestMapping(value="findIDPW")
	public String findIdPw() {			
		
		return "findIDPW";
	}
	
	//ID찾기 실행 및 결과 뿌리기
	@RequestMapping(value="findId.do")
	public String findID(Model model, MbrDto mbrDto) {	
		
		String viewName = mbrSvc.searchID(model, mbrDto);
		
		return viewName;
	}
	
	//PW찾기 실행 및 결과 뿌리기
	@RequestMapping(value="findPw.do")
	public String findPW(Model model, MbrDto mbrDto) {
		
		String viewName = mbrSvc.searchPW(model, mbrDto);
		
		return viewName;
	}
	
}
