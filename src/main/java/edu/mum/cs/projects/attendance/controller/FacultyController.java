package edu.mum.cs.projects.attendance.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.service.IServiceFacade;
import edu.mum.cs.projects.attendance.util.DateUtil;
import edu.mum.cs.projects.attendance.util.SecurityUtil;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	@Autowired
	private IServiceFacade serviceFacade;
	
	private final static int NUMBER_OF_LAST_MONTHS = 6;

	@RequestMapping(value = "/facultyPage", method = { RequestMethod.GET })
	public String listFacultyCourses(
			@RequestParam(value = "name", required = false, defaultValue = "NoCourse") String name, Model model) {
		System.out.println("============= calling faculty controller - listFacultyCourses =================");
		Faculty faculty = serviceFacade.getFacultyByUserName(SecurityUtil.getLoggedInUserName());
		System.out.println("Faculty name: " + faculty.getFirstName());
		List<CourseOffering> courseOfferingList = serviceFacade.getCourseOfferings(faculty).stream()
				.filter(co -> co.getStartDate()
						.after(DateUtil.convertLocalDateToDate(LocalDate.now().minusMonths(NUMBER_OF_LAST_MONTHS))))
				.sorted((co1, co2) -> co1.getBlock().getId().compareTo(co2.getBlock().getId()))
				.collect(Collectors.toList());
		model.addAttribute("courseOfferingList", courseOfferingList);
		
		CourseOffering choosenCourseOffering;
		if (name.equals("NoCourse"))
			choosenCourseOffering = courseOfferingList.get(courseOfferingList.size() - 1);
		else
			choosenCourseOffering = courseOfferingList.stream()
					.filter(item -> item.getId().equals(Long.parseLong(name))).collect(Collectors.toList()).get(0);
		List<StudentAttendance> studentAttendance = serviceFacade.retrieveStudentAttendanceRecords(choosenCourseOffering);
		model.addAttribute("facultyCourseAttendance", studentAttendance);
		return "/faculty/faculty";
	}
}
