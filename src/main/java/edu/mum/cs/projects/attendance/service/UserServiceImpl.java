package edu.mum.cs.projects.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.entity.User;
import edu.mum.cs.projects.attendance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User login(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

}
