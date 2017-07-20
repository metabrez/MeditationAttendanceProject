package edu.mum.cs.projects.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	User findByUserName(String userName);
	User findByUserNameAndPassword(String userName, String password);
	List<User> findAll();
	
	@Modifying
	//@Query(value = "UPDATE User u SET u.password = ?2, u.student.studentId= ?4, u.faculty.id= ?5, u.role.id= ?3 WHERE u.userName= ?1")
	@Query(value = "UPDATE User u SET u.student.studentId= ?4, u.faculty.id= ?5, u.role.id= ?3 WHERE u.userName= ?1")
	int userUpdate(String userName, String password, int roleId, String studentId, Long facultyId);
}
