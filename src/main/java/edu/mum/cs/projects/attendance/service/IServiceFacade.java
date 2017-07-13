package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.List;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.User;

public interface IServiceFacade {
	//using for student
    public List<Course> getCourseListForStudent(String studentID);
    public List<StudentAttendance> getCourseAttendance(String courseID);
	
	//using for user
    public User login(String userName, String password);
	
	//using for faculty
    public List<Course> getCourseListForFaculty(int facultyID);
    public List<StudentAttendance> getAttendance(String courseID);
	
	//using for admin
    public User findUser(String userID);
    public int updateUserRole(User user);
	
	//using for personnel
    public List<Student> findStudents(String id, String firstName, String lastName);
    public List<StudentAttendance> getAttendanceForAllEnrollmentCourses(String studentID);
    public int createAttendanceRecord(String barcode, LocalDate date);
    public int deleteAttendanceRecord(String barcode, LocalDate date);
}