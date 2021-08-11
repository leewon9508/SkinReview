package com.icia.project.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 회원 가입
	public void MemberJoin(MemberDTO member) {
		System.out.println("dao + memberjoin : " + member);
		sql.insert("mm.memberjoin", member);
	}

	// 아이디 중복 확인
	public String IdCheck(String mid) {
		System.out.println("dao + memberIdCheck : " + mid);
		return sql.selectOne("mm.idcheck", mid);
	}
	
	// 아이디 + 비밀번호 체크 후 로그인
	public String IdPwdCheck(MemberDTO member) {
		System.out.println("dao + IdPwdCheck : " + member);
		return sql.selectOne("mm.idpwdcheck", member);
	}

	// 로그인
	public String MemberLogin(MemberDTO member) {
		System.out.println("dao + memberLogin + member");
		return sql.selectOne("mm.memberlogin", member);
	}

	// 회원 목록 조회
	public List<MemberDTO> MemberList() {
		System.out.println("dao + memberList");
		return sql.selectList("mm.memberlist");
	}

	// 회원 상세 조회
	public MemberDTO MemberView(String mid) {
		System.out.println("dao + memberVIew : " + mid);
		return sql.selectOne("mm.memberview", mid);
	}

	// 관리자 회원 강제 탈퇴
	public void mMemberDelete(String mid) {
		System.out.println("dao + mmemberDelete : " + mid);
		sql.delete("mm.mmemberdelete", mid);
	}

	// 회원 탈퇴
	public void MemberDelete(String mid) {
		System.out.println("dao + memberDelete : " + mid);
		sql.delete("mm.memberdelete", mid);
	}

	// 마이페이지
	public MemberDTO MyPage(String mid) {
		System.out.println("dao + mypage : " + mid);
		return sql.selectOne("mm.mypage", mid);

	}

	// 회원 정보 수정 요청
	public MemberDTO MemberUpdate(String loginId) {
		System.out.println("dao + memberupdate : " + loginId);
		return sql.selectOne("mm.memberupdate", loginId);
	}

	// 회원 정보 수정 처리
	public int UpdateProcess(MemberDTO member) {
		System.out.println("dao + updateprocess : " + member);
		return sql.update("mm.updateprocess", member);
	}

	

}
