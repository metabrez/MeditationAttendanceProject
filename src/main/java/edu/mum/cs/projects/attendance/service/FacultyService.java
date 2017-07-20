package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Service layer facade, hides away details of dataaccess layer from client.</p>
 *
 * @author Payman Salek
 * 
 * @version 2.0.0
 * @since 1.0.0
 */
public interface FacultyService {
	
	//List<Enrollment> getEnrollment(CourseOffering offering);

	//List<ComproEntry> getComproEntries(String startDate);

	List<CourseOffering> getCourseOfferings(Faculty faculty);
	Faculty getFacultyByUserName(String userName);
//	AcademicBlock getAcademicBlock(String blockStartDate);
	
}
