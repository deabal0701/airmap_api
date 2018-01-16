package com.kt.airmap.etc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DBConnectionTest {
	
	@Autowired
	KMAMapperDao kMAMapperDao;
	
	@Test
	public void DBConnection() {

		try {
			Integer conn = (Integer) kMAMapperDao.getTest();
			if(conn ==1){
				System.out.println("Success Retrieve value from DB : " + conn);
			}
		} catch (Exception e) {
			System.out.println("Fail to connect DB");
			e.printStackTrace();
		}

	}
	
}
