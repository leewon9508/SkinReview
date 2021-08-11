package com.icia.project.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dto.BoardDTO;
import com.icia.project.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService bs;
	private ModelAndView mav;

	// 글 쓰기 창으로 이동
	@RequestMapping(value = "/writepage")
	public String writepage() {
		System.out.println("controller + writepage");
		return "BoardWriter";
	}

	// 글 쓰기
	@RequestMapping(value = "/boardwriter")
	public ModelAndView BoardWriter(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("controller + boardwriter : " + board);
		mav = bs.BoardWriter(board);
		return mav;
	}

	// 페이징 목록
	@RequestMapping(value = "/paging")
	public ModelAndView BoardPaging(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + paging로 이동");
		mav = bs.BoardPaging(page);
		return mav;
	}

	// 글 상세 보기 + 조회수 증감
	@RequestMapping(value = "/boardview")
	public ModelAndView BoardView(@RequestParam("bnumber") int bnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + boardview : " + bnumber);
		mav = bs.BoardView(bnumber, page);
		return mav;
	}

	// 글 수정 요청
	@RequestMapping(value = "/boardupdate")
	public ModelAndView BoardUpdate(@RequestParam("bnumber") int bnumber) {
		System.out.println("controller + boardupdate : " + bnumber);
		mav = bs.BoardUpdate(bnumber);
		return mav;
	}

	// 글 수정 처리
	@RequestMapping(value = "/bupdateprocess")
	public ModelAndView UpdateProcess(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("controller + updateprocess : " + board);
		mav = bs.UpdateProcess(board);
		return mav;
	}

	// 내가 작성한 글 보기
	@RequestMapping(value = "/mywritelist")
	public ModelAndView MyWriteList(@RequestParam(value = "mid", required = false) String bwriter) {
		System.out.println("controller + MyWriteList : " + bwriter);
		mav = bs.MyWriteList(bwriter);
		return mav;
	}

	// 관리자 글 강제 삭제
	@RequestMapping(value = "/mboarddelete")
	public ModelAndView mBoardDelete(@RequestParam("bnumber") int bnumber,
			@RequestParam(value = "bwriter", required = false) String bwriter) {
		System.out.println("controller + mBoardDelete : " + bnumber + "+" + bwriter);
		mav = bs.mBoardDelete(bnumber, bwriter);
		return mav;
	}

	// 글 삭제
	@RequestMapping(value = "/boarddelete")
	public ModelAndView BoardDelete(@RequestParam("bnumber") int bnumber) {
		System.out.println("controller + boardelete : " + bnumber);
		mav = bs.BoardDelete(bnumber);
		return mav;
	}

	// 검색
	@RequestMapping(value = "/search")
	public ModelAndView BoardSearch(@RequestParam(value = "searchtype", required = false) String searchType,
			@RequestParam("keyword") String keyword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + boardSearch : " + searchType + "+" + keyword);
		mav = bs.BoardSearch(searchType, keyword, page);
		return mav;
	}

	// 스킨 리스트 가지고 오기
	@RequestMapping(value = "/skinpage")
	public ModelAndView SkinPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + skinpage : " + page);
		mav = bs.SkinPage(page); 
		return mav;
	}

	// 로션 리스트 가지고 오기
	@RequestMapping(value = "/lotionpage")
	public ModelAndView LotionPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + lotionpage : " + page);
		mav = bs.LotionPage(page);
		return mav;
	}

	// 립밤 리스트 가지고 오기
	@RequestMapping(value = "/lippage")
	public ModelAndView LipPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + lippage : " + page);
		mav = bs.LipPage(page);
		return mav;
	}

}