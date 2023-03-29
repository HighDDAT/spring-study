package com.spring.mypage.domain;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyDTO {
	
	private Integer reply_no;
	private Integer article_no;
	private String reply_text;
	private String reply_writer;
	private Date reg_date;
	private Date update_date;
	

}
