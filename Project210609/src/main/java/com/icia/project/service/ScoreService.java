package com.icia.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dao.BoardDAO;
import com.icia.project.dao.CommentDAO;
import com.icia.project.dao.ScoreDAO;
import com.icia.project.dto.BoardDTO;
import com.icia.project.dto.CommentDTO;
import com.icia.project.dto.ScoreDTO;

@Service
public class ScoreService {

	@Autowired
	private ScoreDAO sdao;
	private ModelAndView mav;
	@Autowired
	private BoardDAO bdao;
	@Autowired
	private CommentDAO cdao;

	// 별점주기
	public ModelAndView ScoreGive(ScoreDTO score, int sbnumber) {
		System.out.println("service + scoregive : " + score);
		mav = new ModelAndView();
		// 별점 주기
		sdao.ScoreGive(score);
		// 별점 출력
		List<ScoreDTO> scoreList = sdao.ScoreList(sbnumber);
		mav.addObject("scoreList", scoreList);
		// 보드 뷰
		BoardDTO board = bdao.BoardView(sbnumber);
		System.out.println("service + boardview : " + sbnumber);
		mav.addObject("board", board);
		// 댓글
		List<CommentDTO> commentList = cdao.CommentList(sbnumber);
		mav.addObject("commentList", commentList);
		// 별점 평균
		double score1 = sdao.ScoreAvg(sbnumber);
		System.out.println("평균 : " + score1);
		mav.addObject("score", score1);
		mav.setViewName("BoardView");
		return mav;
	}

	// 별점 글번호 + 작성자 중복 제어
	public String sWriterCheck(ScoreDTO score) {
		System.out.println("service + swriterCheck : " + score);
		String swritercheck = sdao.sWriterCheck(score);
		String result = "";
		if (swritercheck == null) {
			System.out.println("service + sWriterCheck = ok");
			result = "ok";
		} else {
			System.out.println("service + bnumberResult = no");
			result = "no";
		}
		return result;

	}

	// 별점 삭제
	public ModelAndView ScoreDelete(int snumber, int bnumber) {
		System.out.println("service + scoreDelete : " + snumber);
		mav = new ModelAndView();
		// 별점 삭제
		sdao.ScoreDelete(snumber);
		// 별점 출력
		List<ScoreDTO> scoreList = sdao.ScoreList(bnumber);
		mav.addObject("scoreList", scoreList);
		// 댓글
		List<CommentDTO> commentList = cdao.CommentList(bnumber);
		mav.addObject("commentList", commentList);
		// 보드
		BoardDTO board = bdao.BoardView(bnumber);
		System.out.println("보드 : " + board);
		mav.addObject("board", board);
		// 별점 평균
		double score = sdao.ScoreAvg(bnumber);
		System.out.println("평균 : " + score);
		mav.addObject("score", score);
		mav.setViewName("BoardView");
		return mav;
	}

}
