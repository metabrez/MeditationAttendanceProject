package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mum.cs.projects.attendance.domain.entity.Faculty;

public interface FacultyRepository extends CrudRepository<Faculty, Long>{
	Faculty findById(Long facultyId);
}
