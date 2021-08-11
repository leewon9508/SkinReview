package com.icia.project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.project.dto.CommentDTO;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;

	
	// 댓글 등록
	public int CommentWrite(CommentDTO comment) {
		System.out.println("cdao + commentwrite : " + comment);
		return sql.insert("cm.commentwrite",comment);
	}

	// 댓글 목록
	public List<CommentDTO> CommentList(int cbnumber) {
		System.out.println("cdao + commentList : " + cbnumber);
		return sql.selectList("cm.commentlist", cbnumber);
	}

	// 댓글 삭제
	public void CommentDelete(CommentDTO comment) {
		System.out.println("cdao.commentDelete : " + comment);
		sql.delete("cm.commentdelete", comment);
		
	}

//	// 댓글 삭제
//	public void CommentDelete(int cnumber) {
//		System.out.println("cdao.commentDelete : " + cnumber);
//		sql.delete("cm.commentdelete", cnumber);
//	}
	
}
