package de.berlin.home.kang.webrest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.berlin.home.kang.webrest.control.PersistenceDAO;
import de.berlin.home.kang.webrest.model.Order;


/**
 * test cases to cover the backen class CreateOrderService
 * @author xinhua
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class CreateOrderServiceTest {

	/*
	 * mock of dao object
	 */
	@Mock
	protected PersistenceDAO daoMock;
	
	/*
	 * mock of DelegateExecution
	 */
	@Mock
	protected DelegateExecution executionMock;
	
	@InjectMocks
	public CreateOrderService service;
	
	/**
	 * test the execute() method
	 * @throws Exception
	 */
	@Test
	public void testExecute() throws Exception {
		/*
		 * test object
		 */
		final Order order = new Order(2l,"test");
		
		final Map<String,Object> vars = new ConcurrentHashMap<>();
		vars.put("order", order );
		
		when(executionMock.getVariablesLocal()).thenReturn(vars);
		service.execute(executionMock);
		
		verify(daoMock,times(1)).createOrder(order);

	}


}
