package com.spring.mypage.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	@Test
	public void testConnection2()throws Exception {
		
		Class clz = Class.forName("org.mariadb.jdbc.Driver");
		
		long start = System.currentTimeMillis();
		for(int i=0; i<100; i++) {
		
		Connection con = 
					DriverManager.getConnection(
							"jdbc:mariadb://localhost:3306/webtest",
							"web",
							"1234");
		log.info(clz);
		
		con.close();
		
		}
		long end = System.currentTimeMillis();
		log.info("======================");
		
		log.info(end-start);
	}
}