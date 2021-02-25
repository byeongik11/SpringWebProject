package com.webProject.mvc01;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//일반 게시판 조회, 글 등록, 수정, 삭제
@Controller
public class BoardCommonCtrl {

	@Autowired
	private ICmmntyRSvc cmmntySvc;
	
	@Autowired
	private ICmmntyCUDSvc cmmntyCUDSvc;
	
	@Autowired
	private ICommentRSvc cmtRSvc;
	
	@Autowired
	private ICommentCUDSvc cmtCUDSvc;
	
	
	//게시판
	@RequestMapping("communityBoard.do")		//커뮤니티 화면 및 등록 게시글 조회 결과 뿌리기
	public String getCommunity( Model     model
							  , SearchDto serDto
							  , PageInfoDto pageDto
							  , CmmntyDto cmmntyDto) {
		
		String viewName = cmmntySvc.sltAll(model, serDto, pageDto, cmmntyDto);
		
		return viewName;
	}
	
	public String boardSltChk(Model model) {						//조건검색(제목, 작성자, )
		return null;
	}
	
	@RequestMapping(value="cmmntyWrite.do")
	public String boardRgt() {										//커뮤니티 등록 화면 띄우기(viewName)
		
		return "communityInsert";	
	}
	
	@RequestMapping(value="cmmntySlt.do", method=RequestMethod.GET)	//커뮤니티 게시글 등록 결과 뿌리기
	public String boardResult(@RequestParam("value") int bod_no, Model model, CmmntyDto cmmntyDto) {
		
		System.out.println("================="+ bod_no );
		
		String viewName = cmmntySvc.sltPost(model, bod_no);
		
		return viewName;
	}
	
	@RequestMapping(value="cmmntyInsert.do")	//커뮤니티 게시글 등록 실행 및 실행결과 뿌리기
	public String boardRgtInsert(HttpSession session
								, Model model, CmmntyDto cmmntyDto) {	
		int mem_num = (int) session.getAttribute("MEM_NUM");
		String mem_name = (String) session.getAttribute( "MEM_NAME" );
		
		cmmntyDto.setCtgr_no(4);		//게시판구분
		cmmntyDto.setMem_num(mem_num);	//회원코드
		cmmntyDto.setMem_name(mem_name);//회원이름

		/*System.out.println(cmmntyDto.toString());*/
		
		String viewName = cmmntyCUDSvc.cmmntyInsert(model, cmmntyDto);
		
		return viewName;
	}
	
	@RequestMapping(value="updatePost")	//커뮤니티 게시글 수정 페이지 호출
	public String getUpdateView(@RequestParam("value") int bod_no, Model model, CmmntyDto cmmntyDto) {
		
		cmmntyDto.setBod_no(bod_no);
		
		String viewName = cmmntyCUDSvc.getUpdate(model, cmmntyDto);
		
		return viewName;	
	}
	
	@RequestMapping(value="updatePost.do")
	public String cmmntyUpdate(@RequestParam("value") int bod_no, Model model, CmmntyDto cmmntyDto)	{
		
		cmmntyDto.setBod_no(bod_no);

		System.out.println("update : " + cmmntyDto.toString());
		
		String viewName = cmmntyCUDSvc.cmmntyUpdate(model, cmmntyDto);
		
		return viewName;
	}
	
	
	@RequestMapping(value="deletePost.do")	//커뮤니티 게시글 삭제 실행 및 실행결과 뿌리기
	public String cmmntyDelete(@RequestParam("value") int bod_no, Model model, CmmntyDto cmmntyDto) {
		
		cmmntyDto.setBod_no(bod_no);
		
		String viewName = cmmntyCUDSvc.cmmntyDelete(model, cmmntyDto);
		
		return viewName;
	}
	
	@RequestMapping(value="cmtInsert.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String cmtInsert(@ModelAttribute("cmtDto") CommentDto cmtDto, HttpSession session) {
		
		int mem_num = (int) session.getAttribute("MEM_NUM");
		
		cmtDto.setMem_num(mem_num);
		
		System.out.println("=============cmtDto : " + cmtDto.toString());
		
		String data = cmtCUDSvc.cmtInsert(cmtDto);
		
		return data;
	}
	
	@RequestMapping(value="reCmtInsert.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String reCmtInsert(@RequestParam(value="bod_no") String bod_no,
							  @RequestParam(value="cmt_no") String cmt_no,
							  @RequestParam(value="cmt_text") String cmt_text,
							  HttpSession session) {
		
		int mem_num = (int) session.getAttribute("MEM_NUM");
		
		System.out.println(cmt_text);
		System.out.println(cmt_no);
		System.out.println(bod_no);
		
		CommentDto cmtDto = new CommentDto();
		cmtDto.setBod_no(Integer.parseInt(bod_no));		//게시글번호
		/*cmtDto.setCmt_no(Integer.parseInt(cmt_no));*/		//댓글 번호
		cmtDto.setMem_num(mem_num);						//댓글 작성자 번호
		cmtDto.setPrt_cmtno(Integer.parseInt(cmt_no));	//부모댓글(=댓글번호)
		cmtDto.setCmt_text(cmt_text);
		
		String data = cmtCUDSvc.cmtInsert(cmtDto);
		
		System.out.println("============reDto : " + cmtDto.toString());
		
		return data;
		
	}
	
	@RequestMapping(value="cmtSlt.do", produces = "application/text; charset=utf8")
	@ResponseBody
	public String cmtSlt(@ModelAttribute("cmtDto") CommentDto cmtDto) {
		
		ArrayList<HashMap<String, Object>> hmlist = new ArrayList<HashMap<String, Object>>();
		
		hmlist = cmtRSvc.cmtSltAll(cmtDto);
		
		JSONArray json = new JSONArray(hmlist);
		
		return json.toString();
		
	}
	
}

