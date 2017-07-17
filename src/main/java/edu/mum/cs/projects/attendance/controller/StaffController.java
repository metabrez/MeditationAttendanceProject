package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.service.IServiceFacade;

@Controller
public class StaffController {
	
	@Autowired
	private IServiceFacade serviceFacade;
	
	@GetMapping("/studentCoursesAttendances")
    public String studentCoursesAttendances(@RequestParam(value="id", required=true) String studentID, Model model) {
        List<StudentAttendance> list = serviceFacade.getAttendanceForAllEnrollmentCourses(studentID);
        Student student = null;
        if(list.size() > 0) {
        	student = list.get(0).getStudent();
        }
//		for(StudentAttendance sa : list) {
//			System.out.println(sa.getStudent().getId());
//			System.out.println(sa.getCourseOffering().getCourse().getName());
//			System.out.println(sa.getCourseOffering().getCourse().getId());
//			System.out.println(sa.getAttendance().toString());
//			System.out.println(sa.getMeditationCount() + "|" + sa.getMeditaionPercentage() + "|" + sa.getMeditationExtraGrade());
//			System.out.println("----");
//		}
        model.addAttribute("student", student);
		model.addAttribute("attendances", list);
        return "studentAttendance";
    }
	
	@GetMapping("/studentCourseAttendanceDetail")
    public String studentCourseAttendanceDetail(@RequestParam(value="offerId", required=true) String offerId,
    											@RequestParam(value="studentId", required=true) String studentId,
    											@ModelAttribute("deleteResult") String delResult,
    											Model model) {
		switch(delResult) {
		case "OK":
			System.out.println("===============Delete OK");
			model.addAttribute("delResult", "Delete attendance record successfully!");
			break;
		case "NG":
			System.out.println("===============Delete NG");
			model.addAttribute("delResult", "Failed to delete attendance record!");
			break;
		}
		
		int courseOfferID;
		try {
			model.addAttribute("studentId", studentId);
			model.addAttribute("offerId", offerId);
			
			courseOfferID = Integer.valueOf(offerId);
			List<BarcodeRecord> list = serviceFacade.getCourseAttendanceDetails(courseOfferID, studentId);
			
			if(list != null) {
				model.addAttribute("attendanceDetails", list);
				list.forEach(System.out::println);
			}
			else {
				model.addAttribute("error", "There are no attendance for this Student ID [" + studentId + "].");
			}
		}
		catch(NumberFormatException nfEx) {
			System.out.println(nfEx.getMessage());
			model.addAttribute("error", "Invalid Course Offer ID!");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.addAttribute("error", ex.getMessage());
		}
		return "attendanceDetail";
    }
	
	@RequestMapping(name="/deleteAttendance", method=RequestMethod.POST)
    public String deleteAttendance(HttpServletRequest httpRequest, RedirectAttributes rattrs) {
        String studentId = httpRequest.getParameter("studentId");
        String barcodeRecordId = httpRequest.getParameter("barcodeRecordId");
        String offerId = httpRequest.getParameter("offerId");
        
        //System.out.println("====["+studentId+"]");
        //System.out.println("====["+barcodeRecordId+"]");
        //System.out.println("====["+offerId+"]");
        
        if(serviceFacade.deleteAttendanceRecord(Long.valueOf(barcodeRecordId)) == 0) {
        	System.out.println("................delete successfully record");
        	rattrs.addAttribute("deleteResult", "OK");
        }
        else {
        	System.out.println("................failed to delete successfully record");
        	rattrs.addAttribute("deleteResult", "NG");
        }
        
        return "redirect:/studentCourseAttendanceDetail?offerId="+offerId+"&studentId="+studentId;
    }
}
