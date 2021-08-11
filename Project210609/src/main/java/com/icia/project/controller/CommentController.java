package com.icia.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.project.dto.CommentDTO;
import com.icia.project.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService cs;

	// 댓글
	@RequestMapping(value = "comment/commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment) {
		System.out.println("controller + commentwrite : " + comment);
		List<CommentDTO> commentList = cs.CommentWrite(comment);
		return commentList;
	}

	// 댓글 삭제
	@RequestMapping(value = "comment/commentDelete")
	public @ResponseBody List<CommentDTO> CommentDelete(@ModelAttribute CommentDTO comment) {
		System.out.println("controller + commentDelete" + comment);
		List<CommentDTO> commentList = cs.CommentDelete(comment);
		return commentList;
	}
	
//	// 댓글 삭제
//		@RequestMapping(value = "comment/commentDelete")
//		public @ResponseBody List<CommentDTO> CommentDelete(@RequestParam(value="cnumber")int cnumber) {
//			System.out.println("controller + commentDelete" + cnumber);
//			List<CommentDTO> commentList=cs.CommentDelete(cnumber);
//			return commentList;
//		}


}
