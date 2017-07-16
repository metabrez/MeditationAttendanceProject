package edu.mum.cs.projects.attendance.domain;

import java.util.Date;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Student;

public class StudentAttendanceDetail {

	private Student student;
	private CourseOffering courseOffering;
	private Date date;
	private boolean attendanceAM;
	private boolean attendanceFA;
	private boolean attendanceFE;
	private boolean attendanceFL;
	private boolean attendanceFM;
	private boolean attendanceOT;
	private boolean attendanceRN;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public CourseOffering getCourseOffering() {
		return courseOffering;
	}
	public void setCourseOffering(CourseOffering courseOffering) {
		this.courseOffering = courseOffering;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isAttendanceAM() {
		return attendanceAM;
	}
	public void setAttendanceAM(boolean attendanceAM) {
		this.attendanceAM = attendanceAM;
	}
	public boolean isAttendanceFA() {
		return attendanceFA;
	}
	public void setAttendanceFA(boolean attendanceFA) {
		this.attendanceFA = attendanceFA;
	}
	public boolean isAttendanceFE() {
		return attendanceFE;
	}
	public void setAttendanceFE(boolean attendanceFE) {
		this.attendanceFE = attendanceFE;
	}
	public boolean isAttendanceFL() {
		return attendanceFL;
	}
	public void setAttendanceFL(boolean attendanceFL) {
		this.attendanceFL = attendanceFL;
	}
	public boolean isAttendanceFM() {
		return attendanceFM;
	}
	public void setAttendanceFM(boolean attendanceFM) {
		this.attendanceFM = attendanceFM;
	}
	public boolean isAttendanceOT() {
		return attendanceOT;
	}
	public void setAttendanceOT(boolean attendanceOT) {
		this.attendanceOT = attendanceOT;
	}
	public boolean isAttendanceRN() {
		return attendanceRN;
	}
	public void setAttendanceRN(boolean attendanceRN) {
		this.attendanceRN = attendanceRN;
	}
}
