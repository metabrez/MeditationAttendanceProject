package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.User;

public interface UserService {
	User login(String userName, String password);
	User findByUserName(String userName);
	List<User> findAllUsers();
	User createUser(User user);
	void deleteUserByUserName(String userName);
}
