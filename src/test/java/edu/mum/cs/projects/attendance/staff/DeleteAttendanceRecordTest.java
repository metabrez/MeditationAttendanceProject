package edu.mum.cs.projects.attendance.staff;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.repository.BarcodeRecordRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeleteAttendanceRecordTest {

	//@Autowired
	//private IServiceFacade serviceFacade;
	//private IServiceFacade serviceFacade = new ServiceFacadeImpl();
	
	@MockBean
    private BarcodeRecordRepository barcodeRecordRepository;
	
	private BarcodeRecord record = null;
	
	@Before
	public void setUp() {
		long recordID = 1669;
		record = new BarcodeRecord();
		record.setId(recordID);
	 
	    Mockito.when(barcodeRecordRepository.findOne(record.getId())).thenReturn(record);
	}
	
	@Test
	public void test() {
		barcodeRecordRepository.delete(record);
		//assertTrue(res == 0); //expecting result is 0
	}

}
