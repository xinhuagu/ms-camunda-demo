package de.berlin.home.kang.webrest.control;

import org.junit.Before;
import org.junit.Test;


/**
 * test cases should cover all methods of PersistenceDAO
 * @author xinhua
 *
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(PersistenceDAO.class)
public class PersistenceDAOTest {


	/**
	 * TODO:
	 * 
	 * powerMockito.mockStatic does not work with my installed jdk
	 * Caused by: java.lang.IllegalAccessError: class jdk.internal.reflect.ConstructorAccessorImpl loaded by org/powermock/core/classloader/MockClassLoader cannot access jdk/internal/reflect superclass jdk.internal.reflect.MagicAccessorImpl
	 */
	@Before
	public void init() {
//		PowerMockito.mockStatic(PersistenceDAO.class );
//		PowerMockito.when(Persistence.createEntityManagerFactory(PersistenceDAO.PERSISTENCE_UNIT_NAME)).thenReturn(mock(EntityManagerFactory.class));
	}
	
	/**
	 * implementaton commes later
	 */
	@Test
	public void testFindByNumber() {
	
//		Long number = 1L;
//		PersistenceDAO.getEntityManager().findByOrderNumber(number);
		
//		verify(emMock,times(1)).find(Order.class, number);
	}

}
