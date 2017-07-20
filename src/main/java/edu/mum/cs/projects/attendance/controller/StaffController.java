package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.cs.projects.attendance.domain.AttendanceRecord;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.service.IServiceFacade;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private IServiceFacade serviceFacade;
	
	@ModelAttribute("attendanceRecord")
    public AttendanceRecord getAttendanceRecord() {
        return new AttendanceRecord();         
    }
	
	@GetMapping("/findStudent")
	public String findStudent(Model model) {
        return "staff/findStudent";
    }
	
	@RequestMapping(value="/findStudentResult", method=RequestMethod.POST)
    public String findStudentResult(HttpServletRequest httpRequest, Model model) {
		System.out.println("============findStudentResult=============");
		String studentID = httpRequest.getParameter("searchStudentId");
		System.out.println("============["+ studentID + "]");
		List<Student> list = null;
		if(studentID != null && !studentID.equals("")) {
			list = serviceFacade.findByStudentIdContaining(studentID);
			model.addAttribute("students", list);
			System.out.println("============["+ list.size() + "]");
		}
        return "staff/findStudentResult";
    }
	
	@GetMapping("/studentCoursesAttendances")
    public String studentCoursesAttendances(@RequestParam(value="id", required=true) String studentID, Model model) {
        List<StudentAttendance> list = serviceFacade.getAttendanceForAllEnrollmentCourses(studentID);
        if(list == null) {
        	model.addAttribute("error", "Not found any information for Student ID ["+studentID+"]");
        }
        else {
        	Student student = null;
            if(list.size() > 0) {
            	student = list.get(0).getStudent();
            }
            model.addAttribute("student", student);
    		model.addAttribute("attendances", list);
        }
        
        return "staff/studentAttendance";
    }
	
	@GetMapping("/studentCourseAttendanceDetail")
    public String studentCourseAttendanceDetail(@RequestParam(value="offerId", required=true) String offerId,
    											@RequestParam(value="studentId", required=true) String studentId,
    											@ModelAttribute("deleteResult") String delResult,
    											Model model) {
		switch(delResult) {
		case "OK":
			model.addAttribute("delResult", "Delete attendance record successfully!");
			break;
		case "NG":
			model.addAttribute("delResult", "Failed to delete attendance record!");
			break;
		}
		
		Long courseOfferID;
		try {
			model.addAttribute("studentId", studentId);
			model.addAttribute("offerId", offerId);
			
			courseOfferID = Long.valueOf(offerId);
			List<BarcodeRecord> list = serviceFacade.getCourseAttendanceDetails(courseOfferID, studentId);
			
			if(list != null) {
				model.addAttribute("attendanceDetails", list);
				//list.forEach(System.out::println);
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
		return "staff/attendanceDetail";
    }
	
	@RequestMapping(value="/deleteAttendance", method=RequestMethod.POST)
    public String deleteAttendance(HttpServletRequest httpRequest, RedirectAttributes rattrs) {
        String studentId = httpRequest.getParameter("studentId");
        String barcodeRecordId = httpRequest.getParameter("barcodeRecordId");
        String offerId = httpRequest.getParameter("offerId");
        
        if(serviceFacade.deleteAttendanceRecord(Long.valueOf(barcodeRecordId)) == 0) {
        	rattrs.addAttribute("deleteResult", "OK");
        }
        else {
        	rattrs.addAttribute("deleteResult", "NG");
        }
        
        return "redirect:/staff/studentCourseAttendanceDetail?offerId="+offerId+"&studentId="+studentId;
    }
	
	@GetMapping("/createAttendance")
	public ModelAndView showForm(@ModelAttribute("saveResult") String saveResult, Model model) {
		switch(saveResult) {
		case "OK":
			model.addAttribute("createResult", "Create attendance record successfully!");
			break;
		case "NG":
			model.addAttribute("error", "Failed to create attendance record!");
			break;
		case "NOT_VALID_BARCODE":
			model.addAttribute("error", "Barcode not existing in system!");
			break;
		}
		
		model.addAttribute("timeslotList", serviceFacade.getAllTimeslots());
		model.addAttribute("locationList", serviceFacade.getAllLocations());
        return new ModelAndView("staff/createAttendance", "attendanceRecord", getAttendanceRecord());
    }
	
	@RequestMapping(value="/saveAttendance", method=RequestMethod.POST)
	public String saveAttendance(@Valid @ModelAttribute("attendanceRecord")AttendanceRecord attendanceRecord, BindingResult result, ModelMap model, RedirectAttributes rattrs) {
		if (result.hasErrors()) {
			System.out.println("has error............");
            return "staff/createAttendance";
        }
		
		//check valid barcode
		if(serviceFacade.findStudentByBarcode(attendanceRecord.getBarcode()) != null) {
			if(serviceFacade.createAttendanceRecord(attendanceRecord) == 0) {
				rattrs.addAttribute("saveResult", "OK");
			}
			else {
				rattrs.addAttribute("saveResult", "NG");
			}
		}
		else {
			rattrs.addAttribute("saveResult", "NOT_VALID_BARCODE");
		}
		
        return "redirect:/staff/createAttendance";
    }
}
