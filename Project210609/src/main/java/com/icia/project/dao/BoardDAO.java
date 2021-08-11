package com.icia.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dto.BoardDTO;
import com.icia.project.dto.PageDTO;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.BMPattern;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 글 쓰기
	public void BoardWriter(BoardDTO board) {
		System.out.println("bdao + boardwriter" + board);
		sql.insert("bm.boardwriter", board);
	}

	// 페이징 처리 목록
	// 게시글 갯수 가지고 오기
	public int ListCount() {
		System.out.println("bdao + ListCount");
		return sql.selectOne("bm.listcount");
	}

	// starRoe, endRow 기준으로 boardlist 가져오기
	public List<BoardDTO> BoardPaging(PageDTO paging) {
		System.out.println("bdao + BoardPaging : " + paging);
		return sql.selectList("bm.boardpaging", paging);
	}

	// 조회수 증감
	public void BoardHite(int bnumber) {
		System.out.println("bdao + BoardHite : " + bnumber);
		sql.update("bm.boardhits", bnumber);
	}

	// 글 상세 보기
	public BoardDTO BoardView(int bnumber) {
		System.out.println("bdao + BoardView : " + bnumber);
		return sql.selectOne("bm.boardview", bnumber);
	}

	// 글 수정 요청
	public BoardDTO BoardUpdate(int bnumber) {
		System.out.println("bdao + boardupdate : " + bnumber);
		return sql.selectOne("bm.boardupdate", bnumber);
	}

	// 글 수정 처리
	public int UdateProcess(BoardDTO board) {
		System.out.println("bdao + updateprocess : " + board);
		return sql.update("bm.updateprocess", board);
	}

	// 내가 작성한 글 보기
	public List<BoardDTO> MyWriteList(String bwriter) {
		System.out.println("bdao + MyWriteList : " + bwriter);
		return sql.selectList("bm.boardwritelist", bwriter);
	}

	// 관리자 글 강제 삭제
	public void mBoardDelete(int bnumber) {
		System.out.println("bdao + mBoardDelete : " + bnumber);
		sql.delete("bm.mboarddelete", bnumber);
	}

	// 글 삭제
	public void BoardDelete(int bnumber) {
		System.out.println("bdao + boarddelete : " + bnumber);
		sql.delete("bm.boarddelete", bnumber);
	}

	// 검색
	public List<BoardDTO> BoardSearch(Map<String, String> searchMap) {
		System.out.println("bdao + boardSearch : " + searchMap);
		return sql.selectList("bm.boardsearch", searchMap);
	}

	// 검색 게시글 수
	public int sListCount(Map<String, String> searchMap) {
		System.out.println("bdao + sListCount : " + searchMap);
		return sql.selectOne("bm.slistcount", searchMap);
	}

	// 검색 리스트 불러오기
	public List<BoardDTO> sBoardPaging(Map<String, String> searchMap) {
		System.out.println("bdao + sBoardPaging : " + searchMap);
		return sql.selectList("bm.sboardpaging", searchMap);
	}

	// 스킨 리스트 갯수 가지고 오기
	public int skinListCount() {
		System.out.println("bdao + skinListCount");
		return sql.selectOne("bm.skinlistcount");
	}

	// 스킨 리스트
	public List<BoardDTO> SkinPage(PageDTO paging) {
		System.out.println("bdao + SkinPage : " + paging);
		return sql.selectList("bm.skinpage", paging);
	}

	// 로션 갯수 가지고 오기
	public int LotionListCount() {
		System.out.println("bdao + LotionListCount");
		return sql.selectOne("bm.lotionlistcount");
	}

	// 로션 리스트 가지고 오기
	public List<BoardDTO> LotionPage(PageDTO paging) {
		System.out.println("bdao + LotionPage");
		return sql.selectList("bm.lotionpage", paging);
	}

	// 립밤 갯수 가지고 오기
	public int lipListCount() {
		System.out.println("bdao + lipListCount");
		return sql.selectOne("bm.liplistcount");
	}

	// 립밤 리스트 가지고 오기
	public List<BoardDTO> LipPage(PageDTO paging) {
		System.out.println("bdao + LipPage");
		return sql.selectList("bm.lippage", paging);
	}

}
