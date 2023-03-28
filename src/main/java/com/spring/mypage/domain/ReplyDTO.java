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
	
	@Override
	public String toString() {
	      return "ReplyDTO [reply_no=" + reply_no + 
	                    ", article_no="+ article_no + 
	                    ", reply_text="+ reply_text + 
	                    ", reply_writer="+ reply_writer + 
	                    ", reg_date="+ reg_date + 
	                    ", update_date="+ update_date+ "]" ;
	}

}
