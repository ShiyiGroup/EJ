package com.ej.mappers;

import java.util.List;

import com.ej.models.Test;
import com.ej.models.User;

public interface UserMapper {
	public List<User> findAll();
	public User findByPhone(String phone);
	public void add(User user);
	public void update(User user);
}
