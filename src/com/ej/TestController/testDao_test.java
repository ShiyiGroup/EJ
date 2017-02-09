package com.ej.TestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ej.dao.TestDao;
import com.ej.models.Test;

public class testDao_test {
	@Autowired
	private  TestDao testDao;
	
	public  void test(){
		List<Test> lists=testDao.findAll();
		System.out.println(lists.size());
	}
	
	public static void main(String [] main){
		testDao_test test=new testDao_test();
		test.test();
		
	}

}
