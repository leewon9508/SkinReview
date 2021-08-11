package com.icia.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.NodeList;

import com.icia.project.dto.MemberDTO;
import com.icia.project.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;
	private ModelAndView mav;

	@Autowired
	private HttpSession session;

	// 회원 가입 화면으로 이동
	@RequestMapping(value = "/memberjoinpage")
	public String MemberJoinPage() {
		System.out.println("controller + memberjoinpage");
		return "MemberJoin";
	}

	// 회원가입
	@RequestMapping(value = "/memberjoin")
	public ModelAndView MemberJoin(@ModelAttribute MemberDTO member) {
		System.out.println("controller =memberjoinpage : " + member);
		mav = ms.MemberJoin(member);
		return mav;
	}

	// 아이디 중복 확인
	@RequestMapping(value = "/idcheck")
	public @ResponseBody String IdCheck(@RequestParam("mid") String mid) {
		System.out.println("controller + idcheck : " + mid);
		String result = ms.IdCheck(mid);
		return result;
	}

	// 로그인 화면으로 이동
	@RequestMapping(value = "/memberloginpage")
	public String MemberLoginPage() {
		System.out.println("controller + memberloginpage");
		return "MemberLogin";
	}

	// 아이디 + 비밀번호 체크 후 로그인
	@RequestMapping(value = "/idpwdcheck")
	public @ResponseBody String IdPwdCheck(@ModelAttribute MemberDTO member) {
		System.out.println("controller + idpedcheck : " + member);
		String result = ms.IdPwdCheck(member);
		return result;
	}

	// 로그인
	@RequestMapping(value = "/login")
	public ModelAndView MemberLogin(@ModelAttribute MemberDTO member) {
		System.out.println("controller + memberLogin : " + member);
		mav = ms.MemberLogin(member);
		return mav;
	}

	// 로그아웃
	@RequestMapping(value = "/logout")
	public String Logout() {
		System.out.println("controller + logout");
		session.invalidate();
		return "home";
	}

	// 회원 목록 조회
	@RequestMapping(value = "/memberlist")
	public ModelAndView MemberList() {
		System.out.println("controller + memberlist");
		mav = ms.MemberList();
		return mav;
	}

	// 회원 상제 조회
	@RequestMapping(value = "/memberview")
	public ModelAndView MemberVist(@RequestParam("mid") String mid) {
		System.out.println("controller + memeberview : " + mid);
		mav = ms.MmebmerVist(mid);
		return mav;
	}

	// 관리자 회원 강제 탈퇴
	@RequestMapping(value = "/mmemberdelete")
	public ModelAndView mMmemberDelete(@RequestParam("mid") String mid) {
		System.out.println("controller + mMemberDelete : " + mid);
		mav = ms.mMemberDelete(mid);
		return mav;
	}

	// 회원 탈퇴
	@RequestMapping(value = "/memberdelete")
	public ModelAndView MmemberDelete(@RequestParam("mid") String mid) {
		System.out.println("controller + MemberDelete : " + mid);
		mav = ms.MemberDelete(mid);
		return mav;
	}

	// 마이페이지
	@RequestMapping(value = "/mypage")
	public ModelAndView MyPage(@RequestParam("mid") String mid) {
		System.out.println("controller + mypage : " + mid);
		mav = ms.MyPage(mid);
		return mav;
	}

	// 회원 정보 수정 요청
	@RequestMapping(value = "/memberupdate")
	public ModelAndView MemberUpdate() {
		System.out.println("controller + memberupdate");
		mav = ms.MemberUpdate();
		return mav;
	}

	// 회원 정보 수정 처리
	@RequestMapping(value = "/mupdateprocess")
	public ModelAndView UpdateProcess(@ModelAttribute MemberDTO member, @RequestParam("mid") String mid) {
		System.out.println("controller + updateprocess : " + member + mid);
		mav = ms.UpdateProcess(member, mid);
		return mav;
	}

}
