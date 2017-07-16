package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.service.IServiceFacade;

@Controller
public class StaffController {
	
	@Autowired
	private IServiceFacade serviceFacade;
	
	@GetMapping("/studentCoursesAttendances")
    public String studentCoursesAttendances(@RequestParam(value="id", required=true) String studentID, Model model) {
        List<StudentAttendance> list = serviceFacade.getAttendanceForAllEnrollmentCourses(studentID);
		for(StudentAttendance sa : list) {
			System.out.println(sa.getStudent().getId());
			System.out.println(sa.getCourseOffering().getCourse().getName());
			System.out.println(sa.getCourseOffering().getCourse().getId());
			System.out.println(sa.getAttendance().toString());
			System.out.println(sa.getMeditationCount() + "|" + sa.getMeditaionPercentage() + "|" + sa.getMeditationExtraGrade());
			System.out.println("----");
		}
		model.addAttribute("attendances", list);
        return "studentAttendance";
    }
	
	/*@GetMapping("/studentCourseAttendanceDetail")
    public String studentCourseAttendanceDetail(@RequestParam(value="id", required=true) String offerId, Model model) {
        List<StudentAttendance> list = serviceFacade.getAttendanceDetail(studentID);
		for(StudentAttendance sa : list) {
			System.out.println(sa.getStudent().getId());
			System.out.println(sa.getCourseOffering().getCourse().getName());
			System.out.println(sa.getCourseOffering().getCourse().getId());
			System.out.println(sa.getAttendance().toString());
			System.out.println(sa.getMeditationCount() + "|" + sa.getMeditaionPercentage() + "|" + sa.getMeditationExtraGrade());
			System.out.println("----");
		}
		model.addAttribute("attendances", list);
        return "studentAttendance";
    }*/
}
