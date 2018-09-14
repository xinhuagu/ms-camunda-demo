package de.berlin.home.kang.webrest.control;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.berlin.home.kang.webrest.model.Order;


/**
 * test cases should cover all methods of PersistenceDAO
 * @author xinhua
 *
 */

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest(PersistenceDAO.class)
public class PersistenceDAOTest {


	private EntityManager emMock;
	
	/**
	 * use powerMockito to mock static method
	 */
	@Before
	public void init() {
		this.emMock = mock(EntityManager.class);
		PowerMockito.mockStatic(PersistenceDAO.class );
		PowerMockito.when(PersistenceDAO.getEntityManager()).thenReturn(this.emMock);
	}
	
	/**
	 * test find by number
	 */
	@Test
	public void testFindByNumber() {
	
		Long number = 1L;
		new PersistenceDAO().findByOrderNumber(number);
		verify(emMock,times(1)).find(Order.class, number);
	}

	/**
	 * test find by orders
	 */
	@Test
	public void testFindAllOrders() {
		when(emMock.createNamedQuery(Order.FIND_ALL, Order.class)).thenReturn(mock(TypedQuery.class));
		new PersistenceDAO().findAllOrders();
		verify(emMock,times(1)).createNamedQuery(Order.FIND_ALL,Order.class);
	}
	
	
	/**
	 * test create order
	 */
	@Test
	public void testcreateOrder()
	{
		Order order = mock(Order.class);
		when(emMock.getTransaction()).thenReturn(mock(EntityTransaction.class));
		new PersistenceDAO().createOrder(order);
		
		verify(emMock,times(1)).persist(order);
	}	
	
}
