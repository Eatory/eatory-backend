package com.eatory.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eatory.model.dto.User;

public interface UserDao {

	public List<User> selectAll();

	public void insertOne(User user);

	User selectOne(@Param("userId") Long userId, @Param("password") String password);

}
