package com.eatory.mvc.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eatory.mvc.model.dto.User;

public interface UserDao {

	public List<User> selectAll();

	public void insertOne(User user);

	User selectOne(@Param("email") String email, @Param("password") String password);

}
