package com.icia.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.dto.ScoreDTO;

@Repository
public class ScoreDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 별점 주기
	public void ScoreGive(ScoreDTO score) {
		System.out.println("sdao + scoregive : " + score);
		sql.insert("sm.scoregive", score);
	}

	// 별점 출력
	public List<ScoreDTO> ScoreList(int sbnumber) {
		System.out.println("sdao + scorelist : " + sbnumber);
		return sql.selectList("sm.scorelist", sbnumber);
	}

	// 별점 글번호 + 작성자 중복 제어
	public String sWriterCheck(ScoreDTO score) {
		System.out.println("sdao + sWriterCheck : " + score);
		return sql.selectOne("sm.swritercheck",score);
	}
	
	// 별점 삭제
	public void ScoreDelete(int snumber) {
		System.out.println("sdao + scoreDelete : " + snumber);
		sql.delete("sm.scoredelete", snumber);
	}

	// 별점 평균
	public double ScoreAvg(int sbnumber) {
		System.out.println("sdao + ScoreAvg : " + sbnumber);
		return sql.selectOne("sm.scoreavg", sbnumber);
	}

}
