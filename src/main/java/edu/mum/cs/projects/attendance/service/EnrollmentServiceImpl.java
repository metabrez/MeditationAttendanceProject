package edu.mum.cs.projects.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Override
	public List<Enrollment> getEnrollmentByStudent(Student student) {
		return enrollmentRepository.findByStudent(student);
	}

}
