
package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.service.IServiceFacade;
import edu.mum.cs.projects.attendance.util.SecurityUtil;

/**
 * @author Sushan
 *Jul 16, 2017
 */

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IServiceFacade serviceFacade;
	
	@RequestMapping(value = "/studentCourseList")
	public String getStudentEnrolledCourses(Model model){
		List<Enrollment> enrollmentList = serviceFacade.getEnrollmentCourseListForStudent(SecurityUtil.getLoggedInUserName());
		model.addAttribute("enrollmentList", enrollmentList);
		return "student/CourseList";
	}
	
	@RequestMapping(value = "/studentCourseMeditation", method = RequestMethod.GET)
	public String getStudentCourseMeditation(@RequestParam(value = "courseID") Long id, Model model){
		List<BarcodeRecord> barcodeRecords = serviceFacade.getCourseAttendanceDetails(id, SecurityUtil.getLoggedInUserName());
		model.addAttribute("attendanceDetails", barcodeRecords);
		return "student/StudentCourseMeditationReport";
	}	
}

