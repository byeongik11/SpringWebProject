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

/**
 * 국내 / 해외 게시판
 * Controller
 * 조회 / 등록 / 수정 / 삭제
 * last : 09/04 10:00 (상혁)
 */

@Controller
public class BoardGuideCtrl {

	@Autowired
	IBoardSvc boardSvc;
	
	@Autowired
	IBoardRSvc boardRSvc;
	
	//국내
	
	//국내 목록 보기
	@RequestMapping("domesticBoard.do")	
	public String getDomesticBoard(Model model
								, SearchDto serDto
								, PageInfoDto pageDto) {
		/* 목록 조회 */
		String viewName = boardRSvc.sltDomesticPage(model, serDto, pageDto);
		
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
	
	//국내 글 보기
		@RequestMapping("domesticContent.do")	
public String DomesticContent(Model model, @RequestParam("value") int textNo, HttpSession session) {
			
			/* 로그인 정보 가져오기 */
			
			System.out.println(session.getAttribute( "MEM_NUM"  ));
			System.out.println(session.getAttribute( "MEM_NAME" ));
			
			int mem_num = (int) session.getAttribute( "MEM_NUM" );
			String mem_name = (String) session.getAttribute( "MEM_NAME" );
			
			System.out.println(mem_num);
			
			if(mem_num <= 0) {
				model.addAttribute("MSG", "로그인이 필요합니다!");
				return "login";
			}
			
			System.out.println("등록된 유저정보 : "	+ mem_name);
			System.out.println("게시물 번호 : " 	+ textNo);
			
			/* 글 조회 실행 */
			String viewName = boardRSvc.sltDomesticContent(model, textNo);
			
			return viewName;
		}
	
	//국내 글 쓰기 페이지 호출
	@RequestMapping("domesticWrite")		
	public String DomesticWritePage(Model model,HttpSession session) {
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
		
		return "domesticWrite";
	}
	
	//국내 글 등록
	@RequestMapping("domesticInsert.do")
	public String DomesticInsert(HttpSession session,
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
		String viewName = boardSvc.boardInsert(model, dataHm);
		
		return viewName;
	}
	
	//국내 글 수정 페이지 호출
	@RequestMapping(value="updateDomestic")
	public String getDomesticUpdate(@RequestParam("value") int textNo, Model model) {
		
		String viewName = boardSvc.getUpdate(model, textNo);
		
		return viewName;
		
	}
		
		
	
	//해외
	
	//해외 목록 보기
	@RequestMapping("abroadBoard.do")	
	public String getAbroadBoard(Model model
							   , SearchDto serDto
							   , PageInfoDto pageDto) {
		
		/* 목록 조회 */
		String viewName = boardRSvc.sltAbroadPage(model, serDto, pageDto);
		
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
	
	//해외 글 보기
	@RequestMapping("abroadContent.do")	
	public String abroadContent(Model model, @RequestParam("value") int textNo, HttpSession session) {
		
		/* 로그인 정보 가져오기 */
		
		System.out.println(session.getAttribute( "MEM_NUM"  ));
		System.out.println(session.getAttribute( "MEM_NAME" ));
		int mem_num = (int) session.getAttribute( "MEM_NUM" );
		String mem_name = (String) session.getAttribute( "MEM_NAME" );
		System.out.println(mem_num);
		
		if(mem_num <= 0) {
			model.addAttribute("MSG", "로그인이 필요합니다!");
			return "login";
		}
		
		System.out.println("등록된 유저정보 : "	+ mem_name);
		System.out.println("게시물 번호 : " 	+ textNo);
		
		/* 글 조회 실행 */
		String viewName = boardRSvc.sltAbroadContent(model, textNo);
		
		return viewName;
	}
	
	//해외 글 쓰기 페이지 호출
	@RequestMapping("abroadWrite")		
	public String abroadWritePage(Model model, HttpSession session) {
		
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
		
		return "abroadWrite";
	}
	
	//해외 글 등록
	@RequestMapping("abroadInsert.do")
	public String abroadInsert(HttpSession session,
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
		
		String viewName = boardSvc.boardInsert(model, dataHm);
		
		return viewName;
	}
	
	//해외 글 수정 페이지 호출
	@RequestMapping(value="updateAbroad")
	public String getUpdateView(@RequestParam("value") int textNo, Model model) {
		
		String viewName = boardSvc.getUpdate(model, textNo);
		
		return viewName;
		
	}
	
	//글 수정
	@RequestMapping(value="guideUpdate.do")
	public String guideUpdate(HttpSession session
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
		
		String viewName = boardSvc.boardUpdate(model, gubun, dtoBoard);
		
		redirectAttributes.addAttribute("value", dtoBoard.getTextNo());
		
		return viewName;
	}
	
	
}
