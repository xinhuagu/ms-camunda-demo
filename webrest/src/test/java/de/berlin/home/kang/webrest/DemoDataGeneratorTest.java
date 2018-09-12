package de.berlin.home.kang.webrest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * 
 * test cases here should cover all methods in DemoDatagenerator class
 * @author xinhua
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoDataGeneratorTest {

	@Mock
	protected ProcessEngine processEngine;
	
	@Mock
	protected IdentityService identityService;
	
	@Mock
	protected User userMock;
	
	@Mock
	protected UserQuery userQuery;
	
	@Mock
	protected Group goupMock;
	
	
	/**
	 * if user 'xinhua' exists then no more user will be created
	 */
	@Test
	public void testCreateUsers() {
		
		DemoDataGenerator generator = new DemoDataGenerator();
		
		when(processEngine.getIdentityService()).thenReturn(identityService);
		when(identityService.createUserQuery()).thenReturn(userQuery);
		when(userQuery.userId(anyString())).thenReturn(userQuery);
		when(userQuery.singleResult()).thenReturn(userMock);
		
		
		generator.createUsers(processEngine);
		
		verify(identityService, times(1)).isReadOnly();
		verify(identityService, never()).saveUser(any());
		
		
		
		
		
	}

}
