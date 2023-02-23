package com.spring.mypage.domain;

import java.sql.Timestamp;

import lombok.Data;


@Data
public class ArticleDTO {

	private Integer article_no;
	private String title;
	private String content;
	private String writer;
	private Timestamp regDate;
	private int viewCnt;
	
}
