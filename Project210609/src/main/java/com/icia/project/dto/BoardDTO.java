package com.icia.project.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int bnumber;
	private String bproduct;
	private String bbrand;
	private String bwriter;
	private String bpassword;
	private String bmerit;
	private String bflaw;
	private String bcatrgort;
	private MultipartFile bfile;
	private String bfilename;
	private int bscore;
	private String bdate;
	private int bhits;
}
