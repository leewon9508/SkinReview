package com.icia.project.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dao.BoardDAO;
import com.icia.project.dao.CommentDAO;
import com.icia.project.dao.ScoreDAO;
import com.icia.project.dto.BoardDTO;
import com.icia.project.dto.CommentDTO;
import com.icia.project.dto.PageDTO;
import com.icia.project.dto.ScoreDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bdao;
	private ModelAndView mav;
	@Autowired
	private CommentDAO cdao;
	@Autowired
	private ScoreDAO sdao;
	@Autowired
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;

	// 글 쓰기
	public ModelAndView BoardWriter(BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("sevice + boardwrtier : " + board);
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "-" + bfilename;
		String savePath = "D:\\Source_LEE\\string\\Project210609\\src\\main\\webapp\\resources\\upload\\" + bfilename;
		if (!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		bdao.BoardWriter(board);
		mav.setViewName("redirect:/paging");
		return mav;
	}

	// 페이징 목록
	public ModelAndView BoardPaging(int page) {
		System.out.println("service + BoardPaging");
		mav = new ModelAndView();
		int listCount = bdao.ListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		List<BoardDTO> boardList = bdao.BoardPaging(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println(boardList);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("Page");
		return mav;
	}

	// 글 상세 보기 + 조회수 증감
	public ModelAndView BoardView(int bnumber, int page) {
		System.out.println("service + boardview : " + bnumber);
		mav = new ModelAndView();
		// 조회수
		bdao.BoardHite(bnumber);
		// 보드
		BoardDTO board = bdao.BoardView(bnumber);
		System.out.println("보드 : " + board);
		mav.addObject("board", board);
		// 댓글
		List<CommentDTO> commentList = cdao.CommentList(bnumber);
		System.out.println("댓글 : " + commentList);
		mav.addObject("commentList", commentList);
		// 별점
		List<ScoreDTO> scoreList = sdao.ScoreList(bnumber);
		System.out.println("별점 : " + scoreList);
		mav.addObject("scoreList", scoreList);
		// 별점 평균
		double score = sdao.ScoreAvg(bnumber);
		System.out.println("평균 : " + score);
		mav.addObject("score", score);
		mav.setViewName("BoardView");
		return mav;
	}

	// 글 수정 요청
	public ModelAndView BoardUpdate(int bnumber) {
		System.out.println("service + boardupdate : " + bnumber);
		mav = new ModelAndView();
		BoardDTO board = bdao.BoardUpdate(bnumber);
		mav.addObject("board", board);
		mav.setViewName("BoardUpdate");
		return mav;
	}

	// 글 수정 처리
	public ModelAndView UpdateProcess(BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("service + updateprocess : " + board);
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "-" + bfilename;
		String savePath = "D:\\Source_LEE\\string\\Project210609\\src\\main\\webapp\\resources\\upload\\" + bfilename;
		if (!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int updateResult = bdao.UdateProcess(board);
		if (updateResult > 0) {
			System.out.println("성공");
			mav.addObject("board", board);
			mav.setViewName("BoardView");
		} else {
			System.out.println("실패");
			mav.setViewName("redirect:/boardview?bnumber=" + board.getBnumber());
		}
		return mav;
	}

	// 내가 작성한 글 보기
	public ModelAndView MyWriteList(String bwriter) {
		System.out.println("service + MyWriteList : " + bwriter);
		mav = new ModelAndView();
		List<BoardDTO> boardList = bdao.MyWriteList(bwriter);
		mav.addObject("boardList", boardList);
		mav.setViewName("MyWriteList");
		return mav;
	}

	// 관리자 글 강제 삭제
	public ModelAndView mBoardDelete(int bnumber, String bwriter) {
		System.out.println("service + mBoardDelete : " + bnumber);
		mav = new ModelAndView();
		bdao.mBoardDelete(bnumber);
		mav.setViewName("redirect:/mywritelist?mid=" + bwriter);
		return mav;
	}

	// 글 삭제
	public ModelAndView BoardDelete(int bnumber) {
		System.out.println("service + boarddelete : " + bnumber);
		mav = new ModelAndView();
		bdao.BoardDelete(bnumber);
		mav.setViewName("redirect:/paging");
		return mav;
	}

	// 검색
	public ModelAndView BoardSearch(String searchType, String keyword, int page) {
		System.out.println("service + boardSearch : " + searchType + "+" + keyword);
		mav = new ModelAndView();
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		System.out.println("service + boardSearch : " + searchType);
		System.out.println("service + boardSearch : " + keyword);
		System.out.println("service + boardSearch : " + page);
		// 검색
		List<BoardDTO> board = bdao.BoardSearch(searchMap);
		if (board.size() > 0) {
			System.out.println("service + boardSearch : 검색 내용 있음");
			// 게시글
			System.out.println("service + boardSearch : " + board);
			System.out.println("service + boardSearch : " + board.size());
			// 게시글 갯수
			int listCount = bdao.sListCount(searchMap);
			System.out.println("service + listCount : " + listCount);
			int startRow = (page - 1) * PAGE_LIMIT + 1;
			int endRow = page * PAGE_LIMIT;
			PageDTO paging = new PageDTO();
			// paging.setStartRow(startRow);
			// paging.setEndRow(endRow);
			// * 스타트, 엔드 값 int 에서 String 로 변환 후 map 담아 dao에 보내준다.
			String startRow2 = String.valueOf(startRow);
			String endRow2 = String.valueOf(endRow);
			searchMap.put("startRow", startRow2);
			searchMap.put("endRow", endRow2);
			System.out.println("service + startRow : " + startRow + 1);
			System.out.println("service + endRow : " + endRow + 1);
			System.out.println("service + startRow2 : " + startRow2 + 1);
			System.out.println("service + endRow2 : " + endRow2 + 1);
			System.out.println("service + paging : " + paging.toString());
			// 페이징
			List<BoardDTO> boardList = bdao.sBoardPaging(searchMap);
			int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
			int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
			int endPage = startPage + BLOCK_LIMIT - 1;
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			paging.setPage(page);
			paging.setStartPage(startPage);
			paging.setEndPage(endPage);
			paging.setMaxPage(maxPage);
			mav.addObject("boardList", boardList);
			mav.addObject("searchType", searchType);
			mav.addObject("keyword", keyword);
			mav.addObject("paging", paging);
			mav.setViewName("PageSearch");
			System.out.println("service + page : " + page);
			System.out.println("service + startPage : " + startPage);
			System.out.println("service + endPage : " + endPage);
			System.out.println("service + maxPage : " + maxPage);
			System.out.println("service + boardList.size() : " + boardList.size());
			System.out.println("service + boardList.toString() : " + boardList.toString());
		} else {
			System.out.println("service + boardSearch : 없음");
			mav.setViewName("PageSearchNone");
		}
		return mav;
	}

	// 스킨 리스트 가지고 오기
	public ModelAndView SkinPage(int page) {
		System.out.println("service + skinpage : " + page);
		mav = new ModelAndView();
		// 스킨 리스트 갯수 가지고 오기
		int listCount = bdao.skinListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("service + listCount : " + listCount);
		System.out.println("paging값 : " + paging.toString());
		// 스킨 리스트 가져오기
		List<BoardDTO> boardList = bdao.SkinPage(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		System.out.println("service + boardList : " + boardList);
		System.out.println("service + boardList.size() : " + boardList.size());
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("SkinMenuPage");
		return mav;
	}

	// 로션 리스트 가지고 오기
	public ModelAndView LotionPage(int page) {
		System.out.println("service + LotionPage : " + page);
		mav = new ModelAndView();
		// 로션 리스트 갯수 가지고 오기
		int listCount = bdao.LotionListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("service + listCount : " + listCount);
		System.out.println("paging값 : " + paging.toString());
		// 로션 리스트 가져오기
		List<BoardDTO> boardList = bdao.LotionPage(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		System.out.println("service + boardList : " + boardList);
		System.out.println("service + boardList.size() : " + boardList.size());
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("LotionMenuPage");
		return mav;
	}

	// 립밤 리스트 가지고 오기
	public ModelAndView LipPage(int page) {
		System.out.println("service + lippage : " + page);
		mav = new ModelAndView();
		// 립밤 리스트 갯수 가지고 오기
		int listCount = bdao.lipListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("service + listCount : " + listCount);
		System.out.println("paging값 : " + paging.toString());
		// 립밤 리스트 가져오기
		List<BoardDTO> boardList = bdao.LipPage(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		System.out.println("service + boardList : " + boardList);
		System.out.println("service + boardList.size() : " + boardList.size());
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("LipMenuPage");
		return mav;
	}
}
