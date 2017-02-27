package com.ej.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ej.mappers.TestMapper;
import com.ej.mappers.UserMapper;
import com.ej.models.Test;
import com.ej.models.User;

@Repository
public class UserDao implements UserMapper{
	
	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	@Override
	public User findByPhone(String phone) {
		
		return userMapper.findByPhone(phone);
	}

	@Override
	public void add(User user) {
		userMapper.add(user);
		
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
		
	}




	

}
