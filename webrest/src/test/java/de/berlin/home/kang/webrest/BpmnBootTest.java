package de.berlin.home.kang.webrest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.camunda.bpm.engine.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * test cases should cover all methods of BpmnBoot class
 * 
 * @author xinhua
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BpmnBootTest {

	/**
	 * mock of demoDataGenerator
	 */
	@Mock
	protected DemoDataGenerator demoData;

	/**
	 * camunda processEngine
	 */
	@Mock
	protected ProcessEngine engine;

	/**
	 * inject all mocks in to bpmnBoot
	 */
	@InjectMocks
	protected BpmnBoot bpmnBoot;

	/**
	 * createUsers method should be called by starting the process
	 */
	@Test
	public void test() {
		bpmnBoot.startFirstProcess(engine);

		verify(demoData, times(1)).createUsers(any());

	}

}
