package com.webProject.mvc01;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//동행자 게시판 조회, 글 등록, 수정, 삭제
@Controller
public class BoardGroupCtrl {

	@Autowired
	IBoardSvc boardSvc;
	
	@Autowired
	IBoardRSvc boardRSvc;
	
	@Autowired
	IGroupSvc groupSvc;
	
	@Autowired
	IGroupRSvc groupRSvc;
	
	//그룹
	
	//그룹 목록 보기
	@RequestMapping("groupBoard.do")	
	public String getGroupBoard(Model model
							  , SearchDto serDto
							  , PageInfoDto pageDto) {
		
		/* 목록 조회 */
		String viewName = boardRSvc.sltGroupPage(model, serDto, pageDto);
		
		/* 콘솔 확인용 */
		System.out.println("조회 기준 : " + serDto.getSearchType());
		System.out.println("조회 내용 : " + serDto.getSearchText());
		System.out.println("조회 내용 : " + serDto.getSearchWhat());
		System.out.println("조회 내용 : " + serDto.getSearchTxt());
		System.out.println("tText : " + pageDto.getTitleText());
		System.out.println("tText : " + pageDto.getWriterText());
		System.out.println("Start : " + pageDto.getStart());
		System.out.println("Last : "  +  pageDto.getLast());
		System.out.println("Total: "  +  pageDto.getTotal());
		System.out.println("Index: "  +  pageDto.getIndex());
		System.out.println("pageLastNum: "  +  pageDto.getPageLastNum());
		System.out.println("Last: "  +  pageDto.getLast());
		
		return viewName;
	}
	
	//그룹 글 보기
	@RequestMapping("groupContent.do")	
	public String GroupContent(	 Model model
	                           , @RequestParam("value") int textNo
	                           , @RequestParam("mCode") int mbrCode
	                           , HttpSession session) {
		System.out.println("글번호 : " + textNo);
		System.out.println("작성자 번호 : " + mbrCode);
		
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "로그인이 필요합니다!");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		String viewName = boardRSvc.sltGroupContent(model, textNo, mbrCode);		// 조회 실행
		
		//그룹 참가 여부
		GroupDto groupDto = new GroupDto();
		groupDto.setBod_no(textNo);
		groupDto.setMem_num(mem_num);
		
		boolean join = groupRSvc.sltJoinGroup(groupDto);		//참가 정보 조회
		
		model.addAttribute("JOIN", join);		//모델에 참가 여부 넣기
		
		//현재 그룹 참가 인원 조회
		int gCnt = groupRSvc.sltJoinTotal(textNo);				// 참가 인원 수 조회
		
		model.addAttribute("JOINCNT", gCnt);	//모델에 참가 인원수 넣기
		
		return viewName;
	}
	
	//그룹 글 쓰기 페이지 호출
	@RequestMapping("groupWrite")		
	public String GroupWritePage(Model model,HttpSession session) {
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "로그인이 필요합니다!");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		return "groupWrite";
	}
	
	//그룹 글 등록
	@RequestMapping("groupInsert.do")
	public String GroupInsert(HttpSession session,
			 @ModelAttribute("gubun") int gubun , Model model , BoardInfoDto dtoBoard) {
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "세션이 만료되었습니다. 다시 로그인 해주세요.");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		/* 데이터 한 곳에 담기 */
		HashMap<String, Object> dataHm = new HashMap<String, Object>();
		dataHm.put("gubun", gubun);
		dataHm.put("mem_num", mem_num);
		dataHm.put("boardData", dtoBoard);
		
		/* 등록 실행 */
		String viewName = boardSvc.boardGroupInsert(model, dataHm);
		
		
		return viewName;
	}
	
	//그룹 글 수정 페이지 호출
	@RequestMapping(value="updateGroup")
	public String getGroupUpdate(@RequestParam("value") int textNo, Model model) {
		
		String viewName = boardSvc.getUpdate(model, textNo);
		
		return viewName;
		
	}
	
	//글 수정
	@RequestMapping(value="groupUpdate.do")
	public String groupUpdate(HttpSession session
	                         , @ModelAttribute("gubun") int gubun
	                         , Model model 
	                         , RedirectAttributes redirectAttributes
	                         , BoardInfoDto dtoBoard) {
		
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "세션이 만료되었습니다. 다시 로그인 해주세요.");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		//수정 실행
		
		String viewName = boardSvc.boardGroupUpdate(model, gubun, dtoBoard);
		
		redirectAttributes.addAttribute("value", dtoBoard.getTextNo());
		redirectAttributes.addAttribute("mCode", mem_num);
		
		return viewName;
	}
	
	//참가
	//그룹 참가 신청
	@RequestMapping("groupJoinInsert.do")
	public String insertJoin(Model model
		                   , RedirectAttributes redirectAttributes
	                       , HttpSession session
	                       , @RequestParam("value") int textNo
	                       , @RequestParam("mCode") int mbrCode) {
		
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "세션이 만료되었습니다. 다시 로그인 해주세요.");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setBod_no(textNo);
		groupDto.setMem_num(mem_num);
		
		String viewName = groupSvc.insertJoin(model, groupDto);
		
		redirectAttributes.addAttribute("value", textNo);
		redirectAttributes.addAttribute("mCode", mbrCode);
		
		return viewName;
	}
	
	//그룹 참가 취소
	@RequestMapping("groupJoinDelete.do")
	public String deleteJoin(Model model
	                       , RedirectAttributes redirectAttributes
	                       , HttpSession session
	                       , @RequestParam("value") int textNo
	                       , @RequestParam("mCode") int mbrCode) {
		
		/* 로그인 정보 가져오기 */
		int    mem_num  = 0;
		String mem_name = null;
		
		mem_num  = (int)	session.getAttribute( "MEM_NUM"  );
		mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "세션이 만료되었습니다. 다시 로그인 해주세요.");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : " + mem_name);
		
		GroupDto groupDto = new GroupDto();
		groupDto.setBod_no(textNo);
		groupDto.setMem_num(mem_num);
		
		String viewName = groupSvc.deleteJoin(model, groupDto);
		
		redirectAttributes.addAttribute("value", textNo);
		redirectAttributes.addAttribute("mCode", mbrCode);
		
		return viewName;
	}
}