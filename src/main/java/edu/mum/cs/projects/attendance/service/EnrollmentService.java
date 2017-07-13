package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Student;

public interface EnrollmentService {
	List<Enrollment> getEnrollmentByStudent(Student student);
}
