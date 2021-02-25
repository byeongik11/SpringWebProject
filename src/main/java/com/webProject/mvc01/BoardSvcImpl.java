package com.webProject.mvc01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service("boardSvc")
public class BoardSvcImpl implements IBoardSvc {

	@Autowired
	IBoardCUDDao cudDao;
	@Autowired
	IDetailCUDDao dtCudDao;
	
	@Override
	@Transactional
	public String boardInsert(Model model, HashMap<String, Object> dataHm) {
		String viewName = "";
		
		//데이터 꺼내기
		int gubun 	 		  = (int) 	 	   dataHm.get("gubun");
		int mem_num 		  = (int) 	   	   dataHm.get("mem_num");
		BoardInfoDto dtoBoard = (BoardInfoDto) dataHm.get("boardData");
		
		//카테고리 별 뷰 페이지 설정
		if(gubun == 1) {
			viewName = "redirect:/domesticBoard.do";
		}
		else if(gubun == 2) {
			viewName = "redirect:/abroadBoard.do";
		}
		
		//글번호
		int maxNum = cudDao.sltMaxNum();
		int newNum = maxNum + 1;
		
		//작성자 = mem_num
		
		//작성일
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddhhmm");
	    String date = dateFmt.format(cal.getTime());

	    //게시판 테이블 데이터 셋
		dtoBoard.setTextNo(newNum);		//BOD_NO		(글번호)
		dtoBoard.setMbrCode(mem_num);	//MEM_NUM		(작성자)
	    dtoBoard.setDate(date);			//BOD_DATE		(작성일)
		dtoBoard.setCtgryNo(gubun);		//CTGR_NO		(카테고리 번호)
		dtoBoard.setCnt(0);				//BOD_CNT 		(조회수 	default:0)
		dtoBoard.setDelChk(0);			//BOD_DELCHK	(삭제여부	default:0)
		
		
		BoardInfoDto resBDto  = cudDao.sltOne(newNum);	// 등록하기 전 단건 조회
		
		if(resBDto != null) {
			model.addAttribute("MSG", "데이터가 존재 합니다.");
			return viewName;
		}
		
		int res  = cudDao.insertBoard(dtoBoard);		// 게시글 등록
		
		if(res <= 0) {
			System.out.println("등록 불가!");
			model.addAttribute("MSG", "데이터 등록에 실패하였습니다.");
			
			return viewName;		//실패시 목록으로
		}
		else {
			System.out.println("게시판 테이블에 등록 성공!");
		}
		
		//세부정보 셋
		String srtDate  = dtoBoard.getStartDate();
	    String nSrtDate = srtDate.replaceAll("-", "");
		String endDate  = dtoBoard.getEndDate();
	    String nEndDate = endDate.replaceAll("-", "");
	    
		dtoBoard.setStartDate(nSrtDate);
		dtoBoard.setEndDate(nEndDate);
		
		dtoBoard.setMax("-");
		dtoBoard.setTrans("-");
		dtoBoard.setEtc("-");
		
		int resD = dtCudDao.insertDetail(dtoBoard);		// 세부정보 등록
		
		if(resD <= 0) {
			System.out.println("등록 불가!");
			model.addAttribute("MSG", "데이터 등록에 실패하였습니다.");
			
			return viewName;
		}
		else {
			System.out.println("세부정보 등록 성공!");
		}
		model.addAttribute("MSG", "글을 등록하였습니다.");
		
		return viewName;
	}

	@Override
	@Transactional
	public String boardGroupInsert(Model model, HashMap<String, Object> dataHm) {
		String viewName = "redirect:/groupBoard.do";
		
		//데이터 꺼내기
		int gubun 	 		  = (int) 	 	   dataHm.get("gubun");
		int mem_num 		  = (int) 	   	   dataHm.get("mem_num");
		BoardInfoDto dtoBoard = (BoardInfoDto) dataHm.get("boardData");
		
		//글번호
		int maxNum = cudDao.sltMaxNum();
		int newNum = maxNum + 1;
		
		//작성자 = mem_num
		
		//작성일
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddhhmm");
	    String date = dateFmt.format(cal.getTime());

	    //게시판 테이블 데이터 셋
		dtoBoard.setTextNo(newNum);		//BOD_NO		(글번호)
		dtoBoard.setMbrCode(mem_num);	//MEM_NUM		(작성자)
	    dtoBoard.setDate(date);			//BOD_DATE		(작성일)
		dtoBoard.setCtgryNo(gubun);		//CTGR_NO		(카테고리 번호)
		dtoBoard.setCnt(0);				//BOD_CNT 		(조회수 	default:0)
		dtoBoard.setDelChk(0);			//BOD_DELCHK	(삭제여부	default:0)
		
		
		BoardInfoDto resBDto  = cudDao.sltOne(newNum);	// 등록하기 전 단건 조회
		
		if(resBDto != null) {
			model.addAttribute("MSG", "데이터가 존재 합니다.");
			return viewName;
		}
		
		int res  = cudDao.insertBoard(dtoBoard);		// 게시글 등록
		
		if(res <= 0) {
			System.out.println("등록 불가!");
			model.addAttribute("MSG", "데이터 등록에 실패하였습니다.");
			
			return viewName;		//실패시 목록으로
		}
		else {
			System.out.println("게시판 테이블에 등록 성공!");
		}
		
		//세부정보 셋
		String srtDate  = dtoBoard.getStartDate();
	    String nSrtDate = srtDate.replaceAll("-", "");
		String endDate  = dtoBoard.getEndDate();
	    String nEndDate = endDate.replaceAll("-", "");
	    
		dtoBoard.setStartDate(nSrtDate);
		dtoBoard.setEndDate(nEndDate);
		
		dtoBoard.setMyBest("-");
		dtoBoard.setMyWorst("-");

		System.out.println(dtoBoard.getMax()+dtoBoard.getTrans()+dtoBoard.getEtc());
		
		
		int resD = dtCudDao.insertDetail(dtoBoard);		// 세부정보 등록
		
		if(resD <= 0) {
			System.out.println("등록 불가!");
			model.addAttribute("MSG", "데이터 등록에 실패하였습니다.");
			
			return viewName;
		}
		else {
			System.out.println("세부정보 등록 성공!");
		}
		model.addAttribute("MSG", "글을 등록하였습니다.");
		
		return viewName;
	}
	
	@Override
	public String getUpdate(Model model, int textNo) {
		
		BoardInfoDto boardDtoDB = cudDao.sltOne(textNo);
		
		if( boardDtoDB == null )
		{
			return "error";	//해당 글 찾을 수 없음
		}
		
		BoardInfoDto detailDtoDB = dtCudDao.sltOneDetail(textNo);
		
		if( detailDtoDB == null )
		{
			return "error";
		}
		
		//날짜 포맷 변경
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		
	    System.out.println(detailDtoDB.getStartDate() + " ~ " + detailDtoDB.getEndDate());
	    
		Date sDate = null;
		Date eDate = null;
		try
		{
			sDate = dateFmt.parse(detailDtoDB.getStartDate());
			eDate = dateFmt.parse(detailDtoDB.getEndDate());
			
		} catch(ParseException e)
		{
			return "error";
		}
		
		SimpleDateFormat dateNew = new SimpleDateFormat("yyyy-MM-dd");
	    String nSDate = dateNew.format(sDate);
	    String nEDate = dateNew.format(eDate);
		
		model.addAttribute("SDATE", nSDate);
		model.addAttribute("EDATE", nEDate);
		
		model.addAttribute("boardDto", boardDtoDB);
		model.addAttribute("detailDto", detailDtoDB);
		
		if(boardDtoDB.getCtgryNo() == 1)
			return "domesticUpdate";
		else if(boardDtoDB.getCtgryNo() == 2)
			return "abroadUpdate";
		else if(boardDtoDB.getCtgryNo() == 3)
			return "groupUpdate";
		else
			return "main";
	}
	
	@Override
	@Transactional
	public String boardUpdate(Model model, int gubun, BoardInfoDto dtoBoard) {
		
		String viewName = "";
		
		if(gubun == 1)		//카테고리 구분별로 포워딩 페이지 지정
			viewName = "redirect:/domesticBoard.do";
		else if(gubun == 2)
			viewName = "redirect:/abroadBoard.do";
		System.out.println("글번호 : " + dtoBoard.getTextNo());
		//게시판 테이블 - 업데이트
		BoardInfoDto resBDto  = cudDao.sltOne(dtoBoard.getTextNo());	// 등록하기 전 단건 조회
		
		if(resBDto == null) {
			model.addAttribute("MSG", "글이 존재하지 않습니다.");
			return viewName;
		}
		
		if(gubun == 1)		//카테고리 구분별로 포워딩 페이지 지정
			viewName = "redirect:/domesticContent.do";
		else if(gubun == 2)
			viewName = "redirect:/abroadContent.do";
		
		int ret = cudDao.updateBoard(dtoBoard);
		
		if(ret > 0) {
			model.addAttribute("MSG", "변경 완료");
		}
		else {
			model.addAttribute("MSG", "변경 실패");
			return "error";
		}

		//세부정보 셋
		String srtDate  = dtoBoard.getStartDate();
	    String nSrtDate = srtDate.replaceAll("-", "");
		String endDate  = dtoBoard.getEndDate();
	    String nEndDate = endDate.replaceAll("-", "");
	    
		dtoBoard.setStartDate(nSrtDate);
		dtoBoard.setEndDate(nEndDate);
		
		dtoBoard.setMax("-");
		dtoBoard.setTrans("-");
		dtoBoard.setEtc("-");
		
		int dRet = dtCudDao.updateDetail(dtoBoard);
		
		if(dRet > 0) {
			model.addAttribute("MSG", "변경 완료");
		}
		else {
			model.addAttribute("MSG", "변경 실패");
			return "error";
		}
		
		model.addAttribute("MSG", "글을 수정 하였습니다.");
		return viewName;
	}
	
	@Override
	@Transactional
	public String boardGroupUpdate(Model model, int gubun, BoardInfoDto dtoBoard) {
		
		String viewName = "redirect:/groupBoard.do";
		
		//게시판 테이블 - 업데이트
		BoardInfoDto resBDto  = cudDao.sltOne(dtoBoard.getTextNo());	// 등록하기 전 단건 조회
		
		if(resBDto == null) {
			model.addAttribute("MSG", "글이 존재하지 않습니다.");
			return viewName;
		}
		
		
		viewName = "redirect:/groupContent.do";
		
		int ret = cudDao.updateBoard(dtoBoard);
		
		if(ret > 0) {
			model.addAttribute("MSG", "변경 완료");
		}
		else {
			model.addAttribute("MSG", "변경 실패");
			return "error";
		}

		//세부정보 셋
				String srtDate  = dtoBoard.getStartDate();
			    String nSrtDate = srtDate.replaceAll("-", "");
				String endDate  = dtoBoard.getEndDate();
			    String nEndDate = endDate.replaceAll("-", "");
			    
				dtoBoard.setStartDate(nSrtDate);
				dtoBoard.setEndDate(nEndDate);
				
				dtoBoard.setMyBest("-");
				dtoBoard.setMyWorst("-");

				System.out.println(dtoBoard.getMax()+dtoBoard.getTrans()+dtoBoard.getEtc());
				
		
		int dRet = dtCudDao.updateDetail(dtoBoard);
		
		if(dRet > 0) {
			model.addAttribute("MSG", "변경 완료");
		}
		else {
			model.addAttribute("MSG", "변경 실패");
			return "error";
		}
		
		model.addAttribute("MSG", "글을 수정 하였습니다.");
		return viewName;
	}


}
