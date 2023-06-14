/**
 * 
 */
package com.usecase4.IncidentManagement.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LENOVO
 *
 */
@SpringBootTest
class IncidentDaoTest {

	@Autowired
	private IncidentDao inciDao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {

		long id = 122;

		inciDao.deleteById(id);
		System.out.println("Incident with id = 122 DELETED !!");
	}


	//This needs to be modified
//	@Test
//	void test() {
////		fail("Not yet implemented");
//		User user1 = new User(1, "Daniyal", Enums.Department.Development, new ArrayList<>());
//
//		Incident incident = new Incident(18, "Server DRC down", "random description", Enums.Priority.High, Enums.Status.In_Progress, Enums.Category.Accessory_Issues, user1 );
//		inciDao.save(incident);
//
//		List<Incident> list = inciDao.findByUserId(122);
//
//		assertThat(list).isNotEmpty().doesNotContainNull();
//
//	}

}
