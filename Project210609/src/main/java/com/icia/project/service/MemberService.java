package com.icia.project.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.icia.project.dao.MemberDAO;
import com.icia.project.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;
	private ModelAndView mav;

	@Autowired
	private HttpSession session;

	// 회원가입
	public ModelAndView MemberJoin(MemberDTO member) {
		System.out.println("service + memberjoin : " + member);
		mav = new ModelAndView();
		mdao.MemberJoin(member);
		mav.setViewName("redirect:/memberloginpage");
		return mav;
	}

	// 아이디 중복 확인
	public String IdCheck(String mid) {
		System.out.println("service + memberIdCheck : " + mid);
		String CheckResult = mdao.IdCheck(mid);
		String result = "";
		if (CheckResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		return result;
	}
	
	// 아이디 + 비밀번호 체크 후 로그인
	public String IdPwdCheck(MemberDTO member) {
		System.out.println("service + idpwdcheck : " + member);
		String idpwdcheck = mdao.IdPwdCheck(member);
		String result="";
		if (idpwdcheck == null) {
				System.out.println("service + idpwdcheck = 아이디, 비밀번호 정보 없음");
				result = "no";
		}else {
			System.out.println("service + idpwdcheck = 아이디, 비밀번호 정보 일치");
			result = "ok";
		}
		return result;	
	}

	// 로그인
	public ModelAndView MemberLogin(MemberDTO member) {
		System.out.println("service + memberlogin : " + member);
		mav = new ModelAndView();
		String loginId = mdao.MemberLogin(member);
		if (loginId != null) {
			session.setAttribute("loginMember", loginId);
			mav.setViewName("redirect:/paging");
		} else {
			mav.setViewName("MemberLogin");
		}
		return mav;
	}

	// 회원 목록 조회
	public ModelAndView MemberList() {
		System.out.println("service + memberlist");
		mav = new ModelAndView();
		List<MemberDTO> memberList = mdao.MemberList();
		mav.addObject("MemberList", memberList);
		mav.setViewName("MemberList");
		return mav;
	}

	// 회원 상세 조회
	public ModelAndView MmebmerVist(String mid) {
		System.out.println("service + memberView : " + mid);
		mav = new ModelAndView();
		MemberDTO member = mdao.MemberView(mid);
		mav.addObject("View", member);
		mav.setViewName("MemberView");
		return mav;
	}

	// 관리자 회원 강제 탈퇴
	public ModelAndView mMemberDelete(String mid) {
		System.out.println("service + mMemberDelete : " + mid);
		mav = new ModelAndView();
		mdao.mMemberDelete(mid);
		mav.setViewName("redirect:/memberlist");
		return mav;
	}

	// 회원 탈퇴
	public ModelAndView MemberDelete(String mid) {
		System.out.println("service + MemberDelete : " + mid);
		mav = new ModelAndView();
		mdao.MemberDelete(mid);
		mav.setViewName("home");
		return mav;
	}

	// 마이 페이지
	public ModelAndView MyPage(String mid) {
		System.out.println("service + my page : " + mid);
		mav = new ModelAndView();
		MemberDTO member = mdao.MyPage(mid);
		mav.addObject("member", member);
		mav.setViewName("MemberMyPage");
		return mav;
	}

	// 회원 정보 수정 요청
	public ModelAndView MemberUpdate() {
		System.out.println("service + memberupdate");
		mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginMember");
		MemberDTO member = mdao.MemberUpdate(loginId);
		mav.addObject("member", member);
		mav.setViewName("MemberUpdate");
		return mav;
	}

	// 회원 정보 수정 처리
	public ModelAndView UpdateProcess(MemberDTO member, String mid) {
		System.out.println("service + updateprocess : " + member);
		mav = new ModelAndView();
		int updateResult = mdao.UpdateProcess(member);
		MemberDTO member1 = mdao.MyPage(mid);
		if (updateResult > 0) {
			System.out.println("성공");
			mav.addObject("member", member1);
			mav.setViewName("MemberMyPage");
		} else {
			System.out.println("실패");
			mav.setViewName("MemberUpdate");
		}
		return mav;
	}



}
