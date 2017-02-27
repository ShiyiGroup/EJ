package com.ej.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ej.mappers.TestMapper;
import com.ej.models.Test;

@Repository
public class TestDao implements TestMapper{
	
	@Autowired
	TestMapper testMapper;

	public List<Test> findAll(){
		return testMapper.findAll();
	}
	
	public Test findById(int id){
		return testMapper.findById(id);
	}
	public void add(Test test){
		 testMapper.add(test);
	}
	public void modify(Test test){
		testMapper.modify(test);
	}
	public void delete(int id){
		testMapper.delete(id);
	}
	
	public static void main(String [] args){
		Test test=new Test();
		test.setUsername("d");
		test.setPassword("123");
		
		TestDao dao=new TestDao();
		List<Test> lists=dao.findAll();
		System.out.println(lists.size());
		//dao.add(test);
		
	}

	@Override
	public Test findByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
