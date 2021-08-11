package com.icia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.dto.ScoreDTO;
import com.icia.project.service.ScoreService;

@Controller
public class ScoreController {
	@Autowired
	private ScoreService sc;
	private ModelAndView mav;

	// 별점 주기
	@RequestMapping(value = "/scoregive")
	public ModelAndView ScoreGive(@ModelAttribute ScoreDTO score, @RequestParam("bnumber") int sbnumber) {
		System.out.println("controller + scoregive : " + score + "+" + sbnumber);
		mav = sc.ScoreGive(score, sbnumber);
		return mav;
	}
	
	// 별점 글번호 + 작성자 중복 제어
		@RequestMapping(value = "/sWriterCheck")
		public @ResponseBody String sWriterCheck(@ModelAttribute ScoreDTO score) {
			System.out.println("controller + swriterCheck : " + score);
			String result = sc.sWriterCheck(score);
			return result;
		}

	// 별점 삭제
	@RequestMapping(value = "/scoreDelete")
	private ModelAndView ScoreDelete(@RequestParam("snumber") int snumber, @RequestParam("bnumber") int bnumber) {
		System.out.println("controller + scoreDelete : " + snumber +"+" + bnumber);
		mav = sc.ScoreDelete(snumber,bnumber);
		return mav;
	}
}
