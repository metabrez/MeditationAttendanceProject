package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.AttendanceRecord;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.Location;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Timeslot;
import edu.mum.cs.projects.attendance.domain.entity.User;

public interface IServiceFacade {
	//using for student
    public List<Enrollment> getEnrollmentCourseListForStudent(String studentID);
    public List<StudentAttendance> getCourseAttendance(String courseID);
	
	//using for user
    public User login(String userName, String password);
	
	//using for faculty
    public List<Course> getCourseListForFaculty(int facultyID);
    public List<StudentAttendance> getAttendance(String courseID);
	public Faculty getFacultyByUserName(String userName);
	List<CourseOffering> getCourseOfferings(Faculty faculty);
	List<StudentAttendance> retrieveStudentAttendanceRecords(CourseOffering courseOffering);
	
	//using for admin
    public User findUser(String userName);
    public User createUser(User user);
    public void deleteUserByUserName(String userName);
    public int updateUserRole(User user);
    public List<User> findAllUsers();
    
	//using for personnel
    public List<Student> findByStudentIdContaining(String studentID);
    public Student findStudentByBarcode(String barcode);
    public List<StudentAttendance> getAttendanceForAllEnrollmentCourses(String studentID);
    public List<BarcodeRecord> getCourseAttendanceDetails(Long offerID, String studentID);
    public int createAttendanceRecord(AttendanceRecord record);
    public int deleteAttendanceRecord(long id);
    public List<Timeslot> getAllTimeslots();
    public List<Location> getAllLocations();
}