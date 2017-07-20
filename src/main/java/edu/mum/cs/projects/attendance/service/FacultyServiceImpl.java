package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.ComproEntry;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.User;
import edu.mum.cs.projects.attendance.repository.AcademicBlockRepository;
import edu.mum.cs.projects.attendance.repository.CourseOfferingRepository;
import edu.mum.cs.projects.attendance.repository.UserRepository;

/**
 * <h1>Maharishi University of Management<br/>
 * Computer Science Department</h1>
 * 
 * <p>
 * Service layer facade, hides away details of dataaccess layer from client.
 * </p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	CourseOfferingRepository dataAccess;

	//@Override
	public List<ComproEntry> getComproEntries(String startDate) {
		List<CourseOffering> offerings = dataAccess.findByCourse(new Course("Entry"));

		return offerings.stream().map(offering -> new ComproEntry(offering))
				.filter(ce -> ce.getDate().isAfter(LocalDate.parse(startDate)))
				.sorted(Comparator.comparing(ComproEntry::getDate)).distinct().collect(Collectors.toList());
	}

	@Autowired
	private AcademicBlockRepository academicBlockRepository;
	@Override
	public List<CourseOffering> getCourseOfferings(Faculty faculty) {		
		List<CourseOffering> result = dataAccess.findByFaculty(faculty);
		result.forEach(co -> co.setBlock(academicBlockRepository.findByBeginDate(co.getStartDate())));
		return result;
	}

//	@Override
//	public AcademicBlock getAcademicBlock(String blockBeginDate) {
//		return dataAccess.finAcademicBlockByBeginDate(blockBeginDate);
//	}

	//@Override
	public List<Enrollment> getEnrollment(CourseOffering offering) {
		return offering.getEnrollments().stream()
				.filter(o -> Enrollment.Status.SIGNEDUP.toString().equalsIgnoreCase(o.getStatus()))
				.collect(Collectors.toList());
	}

	@Autowired
	UserRepository userRepository;
	@Override
	public Faculty getFacultyByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		return user.getFaculty();
	}
}
