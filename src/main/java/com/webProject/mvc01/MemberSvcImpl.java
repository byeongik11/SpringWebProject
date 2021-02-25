package com.webProject.mvc01;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service("mbrSvc")
public class MemberSvcImpl implements IMemberSvc{

	@Autowired
	private IMemberDao mbrDao;
	
	@Override	//id중복 조회
	public String mbrIdChk(String mem_id) {
		
		MbrDto mbrDtoDB = mbrDao.mbrIdChk(mem_id);
		
		boolean result = false;
		
		if(mbrDtoDB == null)
		{
			result = true;
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result);
		
		String jsonOut = jsonObj.toString();
		System.out.println("POST====" + jsonOut);
		
		/*return "redirect:/mbrjoin.jsp";*/
		return jsonOut;
		
	}

	@Override	//회원가입
	@Transactional
	public String mbrInsert(Model model, MbrDto mbrDto) {
		
		int max = mbrDao.mbrNumMax();
			
		String birth  = mbrDto.getMem_birth();
		String birth2 = birth.replaceAll("-", "");
		
		mbrDto.setMem_birth(birth2);
		mbrDto.setMem_num(max + 1);
		mbrDto.setMem_lvl("2");
		
		System.out.println(mbrDto.toString());
		
		int cnt = mbrDao.mbrInsert(mbrDto);
		
		if( cnt > 0 )
		{
			return "forward:/main.jsp";
		}
		
		return "error";
	}

	@Override	//회원정보 수정
	@Transactional
	public String mbrUpdate(Model model, int mem_num, MbrDto mbrDto) {
		
		MbrDto mbrDtoDB = mbrDao.mbrSltOne(mem_num);

		if( mbrDtoDB == null )
		{
			return "error";
		}
		
		mbrDto.setMem_num(mem_num);
		
		mbrDao.mbrUpdate(mbrDto);
		
		return "redirect:myPage.do";
	}

	@Override	//회원탈퇴
	@Transactional
	public String mbrDelete(Model model, int mem_num, MbrDto mbrDto) {
		
		MbrDto mbrDtoDB = mbrDao.mbrSltOne(mem_num);
		
		if( mbrDtoDB == null )
		{
			return "error";
		}
		
		mbrDto.setMem_num(mem_num);
		mbrDto.setMem_lvl("9");
		
		mbrDao.mbrDelete(mbrDto);

		return "redirect:mainlogout";
	}

	@Override	//ID찾기
	public String searchID(Model model, MbrDto mbrDto) {
		
		MbrDto mbrDtoDB = mbrDao.findId(mbrDto);
		
		/*String mem_lvl  = mbrDtoDB.getMem_lvl();*/
		
		if( mbrDtoDB == null )
		{
			model.addAttribute("MSG", "일치하는 정보가 없습니다.<br>확인 후 다시 입력해 주십시오.");
			return "findIDPW";	//일치하는 정보 없다 메세지 띄우기
		}
		
		String mem_lvl  = mbrDtoDB.getMem_lvl();
		
		if( "9".equals(mem_lvl) )
		{
			model.addAttribute("MSG", "일치하는 정보가 없습니다.<br>확인 후 다시 입력해 주십시오.");
			return "findIDPW";	
		}
		
		String mem_name = mbrDtoDB.getMem_name();
		String mem_id	= mbrDtoDB.getMem_id();
		
		model.addAttribute("NAME", mem_name);
		model.addAttribute("ID", mem_id);
		
		return "findIDPW";
	}

	@Override	//PW찾기
	public String searchPW(Model model, MbrDto mbrDto) {
		
		MbrDto mbrDtoDB = mbrDao.findPw(mbrDto);
		
		if( mbrDtoDB == null )
		{
			model.addAttribute("MSG", "일치하는 정보가 없습니다.<br>확인 후 다시 입력해 주십시오.");
			return "findIDPW";	//일치하는 정보 없다 메세지 띄우기
		}
		
		String mem_lvl  = mbrDtoDB.getMem_lvl();
		
		if( "9".equals(mem_lvl) )
		{
			model.addAttribute("MSG", "일치하는 정보가 없습니다.<br>확인 후 다시 입력해 주십시오.");
			return "findIDPW";	
		}
		
		String mem_name = mbrDtoDB.getMem_name();
		String mem_pw	= mbrDtoDB.getMem_pw();
		
		model.addAttribute("NAME", mem_name);
		model.addAttribute("PW", mem_pw);

		return "findIDPW";
	}

	@Override	//로그인 실행
	public String login(Model model, HttpSession session, MbrDto mbrDto) {
		
		String mem_id = mbrDto.getMem_id();
		
		MbrDto mbrDtoDB = mbrDao.mbrIdChk(mem_id);
		
		if( mbrDtoDB == null )						//로그인 정보 없을 경우
		{
			model.addAttribute("MSG", "아이디 또는 비밀번호를 다시 확인하세요.\r\n" + 
									  "gayage에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			
			return "login";
		}		
		
		String pw1 = mbrDto.getMem_pw();			//로그인 PW
		String pw2 = mbrDtoDB.getMem_pw();			//DB   PW
		String lvl = mbrDtoDB.getMem_lvl();			//회원정상 여부
		
		if( pw1.equals(pw2) && !"9".equals(lvl) )	//정상 로그인
		{
			session.setAttribute("MEM_NUM", mbrDtoDB.getMem_num());
			session.setAttribute("MEM_NAME", mbrDtoDB.getMem_name());
			session.setAttribute("MEM_LVL", mbrDtoDB.getMem_lvl());
			
			return "forward:/main.jsp";
		}
		
		model.addAttribute("MSG", "아이디 또는 비밀번호를 다시 확인하세요.<br> gayage에 등록되지 않은 아이디이거나,<br>아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		
		return "login";						//로그인 실패
	}

	@Override	//회원PK 단건조회
	public String mbrSltOne(Model model, int mem_num) {

		MbrDto mbrDto = mbrDao.mbrSltOne(mem_num);
		
		if( mbrDto != null )
		{
			model.addAttribute("MBRDTO", mbrDto);
			
			return "myPage";
		}
		else
		{
			return "error";
		}
	}
	
}