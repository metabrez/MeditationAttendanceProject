package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	User findByUserNameAndPassword(String userName, String password);
}
