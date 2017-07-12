package edu.mum.cs.projects.attendance.service;

import edu.mum.cs.projects.attendance.domain.entity.User;

public interface UserService {
	User login(String userName, String password);
}
