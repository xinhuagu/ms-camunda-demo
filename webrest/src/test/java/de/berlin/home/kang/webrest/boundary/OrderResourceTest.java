package de.berlin.home.kang.webrest.boundary;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.berlin.home.kang.webrest.control.PersistenceDAO;
import de.berlin.home.kang.webrest.model.Order;

/**
 * test cases to cover the OrderResource class
 * @author xinhua
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderResourceTest{

	@Mock
	PersistenceDAO daoMock;
	
	@Mock
	ProcessEngine processEngineMock;
	
	@Mock
	RuntimeService runtimeServiceMock;
	
	@InjectMocks
	OrderResource orderResource;
	
	
	private Order order;
	
	@Before
	public void init()
	{
		when(processEngineMock.getRuntimeService()).thenReturn(runtimeServiceMock);
		this.order = new Order(5L,"test");
	}
	
	
	/**
	 * test case for get order by number
	 */
	@Test
	public void testGet() {
		
		final Response response = this.orderResource.get(order.getNumber());
		
		when(daoMock.findByOrderNumber(anyLong())).thenReturn(order);
		
		verify(daoMock,times(1)).findByOrderNumber(order.getNumber());

        assertEquals(response.getStatus(), 200);
	
	}
	
	/**
	 * test case for creating an order
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testCreate() {
		
		final Response response = this.orderResource.create(order);
		
		verify(runtimeServiceMock,times(1)).startProcessInstanceByKey(anyString(), anyString(), anyMap());
        assertEquals(response.getStatus(), 200);
	
	}
	
	/**
	 * test case for list all orders
	 */
	@Test
	public void testListOrders() {
		
		final Response response = this.orderResource.orders();
		
		
		when(daoMock.findByOrderNumber(anyLong())).thenReturn(order);
		verify(daoMock,times(1)).findAllOrders();
		
        assertEquals(response.getStatus(), 200);
	
	}

}
