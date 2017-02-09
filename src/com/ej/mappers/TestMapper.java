package com.ej.mappers;

import java.util.List;

import com.ej.models.Test;

public interface TestMapper {
	public List<Test> findAll();
	public Test findById(int id);
	public Test findByName(String username);
	public void add(Test test);
	public void modify(Test test);
	public void delete(int id);
}
