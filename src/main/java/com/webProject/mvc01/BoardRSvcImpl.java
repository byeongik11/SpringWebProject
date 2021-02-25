package com.webProject.mvc01;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("boardRSvc")
public class BoardRSvcImpl implements IBoardRSvc{
	
	@Autowired
	IBoardRDao rDao;
	
	@Autowired
	IMemberDao mDao;
	
	@Autowired
	IBoardCUDDao boardDao;

	@Autowired
	IDetailCUDDao detailDao;
	
	@Autowired
	IMemberDao mbrDao;
	
	@Autowired
	IGroupDao groupDao;
	

	@Override
	public String sltAll(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//국내
	
		@Override
		public String sltDomesticPage(Model model, SearchDto serDto, PageInfoDto pageDto) {
			System.out.println("서비스 진입");
			
			pageDto.setGubun(1);		//구분자 국내로 세팅 (1번)
			
			sltPage(model, serDto, pageDto);	//공통 쿼리 실행
			
			model.addAttribute("serDto",serDto);
			
			return "domesticBoard"; 
		}
		
		//국내글보기
		@Override
		public String sltDomesticContent(Model model, int textNo) { 
			
			System.out.println(textNo);

			//게시판 테이블에서 데이터 찾기
			BoardInfoDto boardDto = boardDao.sltOne(textNo);
			
			if(boardDto == null) {
				model.addAttribute("MSG", "해당 글을 찾을 수 없습니다.");
				return "domesticBoard";
			}
			
			//상세 정보 찾기
			BoardInfoDto detailDto = detailDao.sltOneDetail(textNo);
			
			if(detailDto == null) {
				model.addAttribute("MSG", "페이지 내용을 찾을 수 없습니다.");
				return "domesticBoard";
			}
			
			//조회수 +1
			boardDao.updateCnt(boardDto.getTextNo());
			
			//모델에 넣기
			model.addAttribute("boardDto", boardDto);
			model.addAttribute("detailDto", detailDto);
			
			System.out.println(textNo);
			
			return "domesticContent";
		}
		
		//해외
		
		//목록
		@Override
		public String sltAbroadPage(Model model, SearchDto serDto , PageInfoDto pageDto) {
			System.out.println("서비스 진입");
			
			pageDto.setGubun(2);		//구분자 해외로 세팅 (2번)
			
			sltPage(model, serDto, pageDto);	//공통 쿼리 실행
			
			model.addAttribute("serDto",serDto);
			
			return "abroadBoard"; 
		}
		
		//해외글 보기(0904 작업함)
		@Override
		public String sltAbroadContent(Model model, int textNo) { 
			System.out.println(textNo);
			
			//게시판 테이블에서 데이터 찾기
			BoardInfoDto boardDto = boardDao.sltOne(textNo);
			
			if(boardDto == null) {
				model.addAttribute("MSG", "해당 글을 찾을 수 없습니다.");
				return "abroadBoard";
			}
			
			//상세 정보 찾기
			BoardInfoDto detailDto = detailDao.sltOneDetail(textNo);
			
			if(detailDto == null) {
				model.addAttribute("MSG", "페이지 내용을 찾을 수 없습니다.");
				return "abroadBoard";
			}
			
			//조회수 +1
			boardDao.updateCnt(boardDto.getTextNo());
			
			//모델에 넣기
			model.addAttribute("boardDto", boardDto);
			model.addAttribute("detailDto", detailDto);
			
			System.out.println(textNo);
			
			return "abroadContent";
		}

		
		//그룹
		
		//목록
		@Override
		public String sltGroupPage(Model model, SearchDto serDto , PageInfoDto pageDto) {
			System.out.println("서비스 진입");
			
			pageDto.setGubun(3);		//구분자 그룹으로 세팅 (3번)
			
			sltPage(model, serDto, pageDto);	//공통 쿼리 실행
			
			model.addAttribute("serDto",serDto);
			
			return "groupBoard"; 
		}
		
		//그룹글 보기
		@Override
		public String sltGroupContent(Model model, int textNo, int mbrCode) { 
			
			System.out.println("글번호" + textNo);
			
			//게시판 테이블에서 데이터 찾기
			BoardInfoDto boardDto = boardDao.sltOne(textNo);
			
			if(boardDto == null) {
				model.addAttribute("MSG", "해당 글을 찾을 수 없습니다.");
				return "groupBoard";
			}
			
			//상세 정보 찾기
			BoardInfoDto detailDto = detailDao.sltOneDetail(textNo);
			
			if(detailDto == null) {
				model.addAttribute("MSG", "페이지 내용을 찾을 수 없습니다.");
				return "groupBoard";
			}
			
			//조회수 +1
			boardDao.updateCnt(boardDto.getTextNo());
			
			/* 작성자 정보 찾아오기 */
			MbrDto mbrDto = mbrDao.sltGroupWriter(mbrCode);
			
			//모델에 넣기
			model.addAttribute("boardDto", boardDto);
			model.addAttribute("detailDto", detailDto);
			model.addAttribute("mbrDto", mbrDto);
			model.addAttribute("mbrDto", mbrDto);
			System.out.println("글번호 , 작성자 : " + textNo + "|" + mbrDto.toString());
			
			return "groupContent";
		}
				
		//공통
		
		public void sltPage(Model model, SearchDto serDto, PageInfoDto pageDto) {
			System.out.println("=====================================");
			System.out.println("=====================================");
			System.out.println("=====================================");
			System.out.println("=====================================");
			System.out.println("=====================================");
		
			
			ArrayList<BoardInfoDto> list;
			
			int pageMax = rDao.sltPageMax(pageDto);			//글 갯수 
			
			if(serDto == null) {		//페이지 부를때
				System.out.println("svc = 검색어 음슴");
				pageDto.setTotal(pageMax);		//뷰에서 페이징하기 위해 지정
				list = rDao.sltPageIndex(pageDto);	// 전체조회
				
			}
			else if("all".equals(serDto.getSearchWhat())) {		//전체 검색일때
				System.out.println("svc = all");
				
				pageDto.setTitleText(serDto.getSearchTxt());		//제목 검색어
				pageDto.setWriterText(serDto.getSearchTxt());		//작성자 검색어

				
				pageDto.setTotal(rDao.sltAllMax(pageDto));		//뷰에서 페이징하기 위해 지정
				list = rDao.sltAllIndex(pageDto);	// 조회
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

				pageDto.setTotal(rDao.sltTitleMax(pageDto));	//뷰에서 페이징하기 위해 지정
				list = rDao.sltTitleIndex(pageDto);	// 조회
				
			}
			else if("writer".equals(serDto.getSearchWhat())) { 	//작성자 검색일때
				System.out.println("svc = writer");
				
				pageDto.setWriterText(serDto.getSearchTxt());		//작성자 검색어

				pageDto.setTotal(rDao.sltWriterMax(pageDto));	//뷰에서 페이징하기 위해 지정
				list = rDao.sltWriterIndex(pageDto);	// 조회
				
			}
			else if("area".equals(serDto.getSearchWhat())) { 	//지역명 검색일때
				System.out.println("svc = area");
				
				pageDto.setAreaText(serDto.getSearchTxt());		//지역명 검색어

				pageDto.setTotal(rDao.sltAreaMax(pageDto));	//뷰에서 페이징하기 위해 지정
				list = rDao.sltAreaIndex(pageDto);	// 조회
				
			}
			else {				//검색어 없을때
				System.out.println("svc = 페이지 처음 호출");

				pageDto.setTotal(pageMax);		//뷰에서 페이징하기 위해 지정
				list = rDao.sltPageIndex(pageDto);	// 전체조회
			}
			
			model.addAttribute("list", list);
	        model.addAttribute("pageDto", pageDto);
	        
	        System.out.println("리스트와 페이지 넣기");
	        
		}

}
