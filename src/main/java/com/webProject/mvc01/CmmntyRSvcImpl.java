package com.webProject.mvc01;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service("cmmntySvc")
public class CmmntyRSvcImpl implements ICmmntyRSvc {

	@Autowired
	private ICmmntyRDao cmmntyDao;

	@Override
	public String sltAll(Model model, SearchDto serDto , PageInfoDto pageDto, CmmntyDto cmmntyDto) {

		pageDto.setGubun(4);		//구분자 커뮤니티로 세팅 (4번)
		
		System.out.println("===================" + pageDto.getGubun());
		
		
		String viewName = sltPage(model, serDto, pageDto);	//쿼리 실행
		
		model.addAttribute("serDto",serDto);
		
		return viewName;
	}

	@Override
	@Transactional
	public String sltPost(Model model, int bod_no) {
		
		CmmntyDto cmmntyDtoDB = cmmntyDao.sltPost(bod_no);
		
		String bod_delchk = String.valueOf(cmmntyDtoDB.getBod_delchk());
		
		if( cmmntyDtoDB == null || "9".equals(bod_delchk))
		{
			return "error";	//데이터 없다.
		}
		
		// 조회수 카운트
		int bod_cnt = cmmntyDtoDB.getBod_cnt() + 1;
		
		cmmntyDtoDB.setBod_cnt(bod_cnt);
		
		cmmntyDao.updateCnt(cmmntyDtoDB);
		
		model.addAttribute("CMMNTYDTO", cmmntyDtoDB);
		
		return "cmmntyRead";
	}

	public String sltPage(Model model, SearchDto serDto, PageInfoDto pageDto) {
		System.out.println("=====================================");
		System.out.println("=====================================");
		System.out.println("=====================================");
		System.out.println("=====================================");
		System.out.println("=====================================");
		
		ArrayList<BoardInfoDto> list;
		
		int pageMax = cmmntyDao.sltPageMax(pageDto);			//글 갯수 
		
		if(serDto == null) {		//페이지 부를때
			System.out.println("svc = 검색어 없음");
			pageDto.setTotal(pageMax);		//뷰에서 페이징하기 위해 지정
			list = cmmntyDao.sltPageIndex(pageDto);	// 전체조회
			
		}
		else if("all".equals(serDto.getSearchWhat())) {		//전체 검색일때
			System.out.println("svc = all");
			
			pageDto.setTitleText(serDto.getSearchTxt());		//제목 검색어
			pageDto.setWriterText(serDto.getSearchTxt());		//작성자 검색어

			
			pageDto.setTotal(cmmntyDao.sltAllMax(pageDto));		//뷰에서 페이징하기 위해 지정
			list = cmmntyDao.sltAllIndex(pageDto);	// 조회
			System.out.println("페이지 index : " + pageDto.getIndex());
			
			System.out.println("페이지 구분자 : " + pageDto.getGubun());
			System.out.println("페이지 StartData : " + pageDto.getStartData());
			System.out.println("페이지 ListCnt : " + pageDto.getListCnt());
			System.out.println("페이지 TitleText : " + pageDto.getTitleText());
			System.out.println("페이지 WriterText : " + pageDto.getWriterText());
			System.out.println("페이지 Start : " + pageDto.getStart());
			
			
		}
		else if("subject".equals(serDto.getSearchWhat())) { //제목 검색일때
			System.out.println("svc = subject");
			
			pageDto.setTitleText(serDto.getSearchTxt());		//제목 검색어

			pageDto.setTotal(cmmntyDao.sltTitleMax(pageDto));	//뷰에서 페이징하기 위해 지정
			list = cmmntyDao.sltTitleIndex(pageDto);	// 조회
			
		}
		else if("writer".equals(serDto.getSearchWhat())) { 	//작성자 검색일때
			System.out.println("svc = writer");
			
			pageDto.setWriterText(serDto.getSearchTxt());		//작성자 검색어

			pageDto.setTotal(cmmntyDao.sltWriterMax(pageDto));	//뷰에서 페이징하기 위해 지정
			list = cmmntyDao.sltWriterIndex(pageDto);	// 조회
			
		}
		else {				//처음 페이지 호출시
			System.out.println("svc = 페이지 처음 호출");

			pageDto.setTotal(pageMax);		//뷰에서 페이징하기 위해 지정
			list = cmmntyDao.sltPageIndex(pageDto);	// 전체조회
		}

		
		if( list == null )
		{
			return "error";	//게시글이 없다.
		}
		
		
		model.addAttribute("LIST", list);
        model.addAttribute("pageDto", pageDto);
        
        System.out.println("리스트와 페이지 넣기");
        
        return "communityBoard";
	}
	
}
