package com.icia.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dao.CommentDAO;
import com.icia.project.dto.CommentDTO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO cdao;
	private ModelAndView mav;
	
	// 댓글 등록-
	public List<CommentDTO> CommentWrite(CommentDTO comment) {
		System.out.println("service + commentwrite : " + comment);
		int writeResult = cdao.CommentWrite(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		if (writeResult > 0) {
			commentList = cdao.CommentList(comment.getCbnumber());
		} else {
			commentList = null; 
			
		}
		return commentList;
	}

	// 댓글 삭제
	public List<CommentDTO> CommentDelete(CommentDTO comment) {
		System.out.println("service + commentDelete : " + comment);
		mav = new ModelAndView();
		cdao.CommentDelete(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		System.out.println(comment.getCbnumber());
		commentList = cdao.CommentList(comment.getCbnumber());
		return commentList;
	}

//	// 댓글 삭제
//	public List<CommentDTO> CommentDelete(int cnumber) {
//		System.out.println("service + commentDelete : " + cnumber);
//		mav = new ModelAndView();
//		cdao.CommentDelete(cnumber);
//		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
//		commentList = cdao.CommentList(cnumber);
//		return commentList;
//	}

}
