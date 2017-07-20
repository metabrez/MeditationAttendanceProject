package edu.mum.cs.projects.attendance.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	User findByUserName(String userName);
	User findByUserNameAndPassword(String userName, String password);
	List<User> findAll();
	
//	@Modifying
//	@Query(value = "UPDATE User u SET u.userName = ?2, u.password = ?3, u.studentId= ?5, u.facultyId= ?6, u.roleId= ?4 WHERE u.userId= ?1")
//	int userUpdate(int userId, String userName, String password, int roleId, int studentId, int facultyId);
}
