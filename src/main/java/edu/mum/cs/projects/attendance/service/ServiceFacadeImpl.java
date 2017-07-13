package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.User;

@Service
public class ServiceFacadeImpl implements IServiceFacade {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Course> getCourseListForStudent(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentAttendance> getCourseAttendance(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String userName, String password) {
		return userService.login(userName, password);
	}

	@Override
	public List<Course> getCourseListForFaculty(int facultyID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentAttendance> getAttendance(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUser(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUserRole(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentAttendance> getAttendanceForAllEnrollmentCourses(String studentID) {
		List<StudentAttendance> list = null, listTmp;
		
		Student student = studentService.findStudentById(studentID);
		if(null != student) {
			List<Enrollment> listEnroll = enrollmentService.getEnrollmentByStudent(student);
			if(null != listEnroll) {
				list = new ArrayList<StudentAttendance>();
				for(Enrollment en : listEnroll) {
					listTmp = attendanceService.retrieveStudentAttendanceRecords(en.getOffering());					
					if(null != listTmp) {
						list.addAll(listTmp.stream().filter(sa->sa.getStudent().getId().equals(student.getId())).collect(Collectors.toList()));
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public int createAttendanceRecord(String barcode, LocalDate date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAttendanceRecord(String barcode, LocalDate date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> findStudents(String id, String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
