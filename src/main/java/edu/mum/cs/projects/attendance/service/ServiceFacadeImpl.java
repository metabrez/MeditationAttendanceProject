package edu.mum.cs.projects.attendance.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.AttendanceRecord;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.Location;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Timeslot;
import edu.mum.cs.projects.attendance.domain.entity.User;
import edu.mum.cs.projects.attendance.repository.AcademicBlockRepository;
import edu.mum.cs.projects.attendance.repository.BarcodeRecordRepository;
import edu.mum.cs.projects.attendance.repository.CourseOfferingRepository;
import edu.mum.cs.projects.attendance.repository.FacultyRepository;
import edu.mum.cs.projects.attendance.repository.RoleRepository;
import edu.mum.cs.projects.attendance.repository.UserRepository;
import edu.mum.cs.projects.attendance.util.DateUtil;

@Service
public class ServiceFacadeImpl implements IServiceFacade {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseOfferingRepository courseOfferingRepository;
	
	@Autowired
	private BarcodeRecordRepository barcodeRecordRepository;
	
	@Autowired
	private AcademicBlockRepository academicBlockRepository;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Override
	public User findUser(String userName) {
		return userService.findByUserName(userName);
	}

	@Override
	public List<StudentAttendance> getAttendanceForAllEnrollmentCourses(String studentID) {
		List<StudentAttendance> list = null, listTmp;
		
		Student student = studentService.findStudentById(studentID);
		if(null != student) {
			List<Enrollment> listEnroll = enrollmentService.getEnrollmentByStudent(student);
			if(null != listEnroll) {
				list = new ArrayList<StudentAttendance>();
				for(Enrollment en : listEnroll) {
					listTmp = attendanceService.retrieveStudentAttendanceRecords(en.getOffering());					
					if(null != listTmp) {
						list.addAll(listTmp.stream().filter(sa->sa.getStudent().getId().equals(student.getId())).collect(Collectors.toList()));
					}
				}
			}
		}
		
		return list;
	}
	
	@Override
	public List<BarcodeRecord> getCourseAttendanceDetails(Long offerID, String studentID) {
		List<BarcodeRecord> listRecord = null;

		CourseOffering offer = courseOfferingRepository.findOne(offerID);
		if(offer != null) {
			AcademicBlock block = academicBlockRepository.findByBeginDate(offer.getStartDate());
			if(block != null) {
				Student student = studentService.findStudentById(studentID);
				if(student != null) {
					//listRecord = barcodeService.getBarcodeRecordsList(block.getBeginDate(), block.getEndDate()).stream().filter(br->br.getBarcode().equals(student.getBarcode())).collect(Collectors.toList());
					listRecord = barcodeRecordRepository.findByDateBetweenAndBarcode(DateUtil.convertLocalDateToDate(block.getBeginDate()), DateUtil.convertLocalDateToDate(block.getEndDate()), student.getBarcode());
				}
			}
		}
		
		return listRecord;
	}

	@Override
	public int createAttendanceRecord(AttendanceRecord record) {
		BarcodeRecord br = new BarcodeRecord();
		br.setBarcode(record.getBarcode()); 
		br.setDate(DateUtil.convertDateToLocalDate(DateUtil.convertStringToDate(record.getDate())));
		br.setTime(LocalTime.of(0, 0, 0));
		br.setLocation(locationService.findById(record.getLocation()));
		br.setTimeslot(timeSlotService.findById(record.getTimeslot()));
		if(null != barcodeRecordRepository.save(br)) {
			return 0;
		}
		return 1;
	}

	@Override
	public int deleteAttendanceRecord(long id) {
		BarcodeRecord br = barcodeRecordRepository.findOne(id);
		if(br != null) {
			barcodeRecordRepository.delete(br);
			return 0;
		}
		
		return 1;
	}

	@Override
	public List<Student> findByStudentIdContaining(String studentID) {
		return studentService.findByStudentIdContaining(studentID);
	}

	@Override
	public List<Timeslot> getAllTimeslots() {
		return timeSlotService.getTimeSlot();
	}

	@Override
	public List<Location> getAllLocations() {
		return locationService.getAllLocations();
	}

	@Override
	public Student findStudentByBarcode(String barcode) {
		return studentService.findStudentByBarcode(barcode);
	}

	@Override
	public List<Enrollment> getEnrollmentCourseListForStudent(String studentID) {
		Student student = studentService.findStudentById(studentID);
		return enrollmentService.getEnrollmentByStudent(student);
	}

	@Override
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@Override
	public User createUser(String userName, String password, int roleId, String studentId, Long facultyId) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setRole(roleRepository.findById(roleId));
		if (facultyId == null) {
			user.setStudent(studentService.findStudentById(studentId));
			user.setFaculty(null);
		} 
		if (studentId == null || studentId.equals("")) {
			user.setStudent(null);
			user.setFaculty(facultyRepository.findById(facultyId));
		}

		return userRepository.save(user);
	}

	@Override
	public void deleteUserByUserName(String userName) {
		userService.deleteUserByUserName(userName);
	}

	@Override
	public Faculty getFacultyByUserName(String userName) {
		return facultyService.getFacultyByUserName(userName);
	}

	@Override
	public List<CourseOffering> getCourseOfferings(Faculty faculty) {
		return facultyService.getCourseOfferings(faculty);
	}

	@Override
	public List<StudentAttendance> retrieveStudentAttendanceRecords(CourseOffering courseOffering) {
		return attendanceService.retrieveStudentAttendanceRecords(courseOffering);
	}

	@Override
	public int updateUser(String userName, String password, int roleId, String studentId, Long facultyId) {
		return userRepository.userUpdate(userName, password, roleId, studentId, facultyId);
	}

}
