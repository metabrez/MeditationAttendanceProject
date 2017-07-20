package edu.mum.cs.projects.attendance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
	Student findByBarcode(String barcode);
	List<Student> findByEntryDate(Date entryDate);
	List<Student> findAll();
	List<Student> findByStudentIdContaining(@Param("studentId") String studentId);	
}
