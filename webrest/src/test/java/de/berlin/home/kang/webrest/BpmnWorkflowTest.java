package de.berlin.home.kang.webrest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;
import static org.mockito.Mockito.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.berlin.home.kang.webrest.model.Order;

/**
 *
 * test class for the workflow
 * every possible way in workflow should be covered
 *
 * @author xinhua
 *
 */
public class BpmnWorkflowTest {

	/**
	 * a must have for camunda unit test
	 */
	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();


	/**
	 * the entrance class to bpmn engine
	 */
	private RuntimeService runtimeService;

	/**
	 * init the entrance class runtimeService to bpmn engine
	 * and init all mock objects and regist them in Camunda Mocks so that Camunda engine can find them
	 */
	@Before
	public void initMock()
	{
		this.runtimeService = this.rule.getProcessEngine().getRuntimeService();

		Mocks.register("createOrderService", mock(CreateOrderService.class));
		Mocks.register("validationOrderService",new ValidationOrderService());

	}

	/**
	 * test case for the order which has name 'test'
	 */
	@Test()
	@Deployment( resources = {"webrest_demo.bpmn"} )
	public void testOrderWithNameTest() {

		//given
		final Map<String,Object> vars = new ConcurrentHashMap<>();
		vars.put("order", new Order(2L,"test"));

		//when
		final ProcessInstance instance = this.runtimeService.startProcessInstanceByKey("wsrest_demo_process",vars);


		//then
		assertThat(instance).isStarted().hasPassed("createOrderService").isWaitingAt("checkOrderTask").isNotEnded();

		complete(task());
		assertThat(instance.isEnded());

	}


	/**
	 * test case for the order which has name 'fake'
	 */
	@Test()
	@Deployment( resources = {"webrest_demo.bpmn"} )
	public void testInvalidOrderWithNameTest() {

		//given
		final Map<String,Object> vars = new ConcurrentHashMap<>();
		vars.put("order", new Order(2L,"fake"));

		//when
		final ProcessInstance instance = this.runtimeService.startProcessInstanceByKey("wsrest_demo_process",vars);


		//then
		assertThat(instance).isStarted().hasNotPassed("createOrderService").hasPassed("validationFailureService").isEnded();

		assertThat(instance.isEnded());

	}

	/**
	 * do clean up for camunda Mocks
	 * meanwhile the test coverage will be calculated
	 * @throws Exception
	 */
	@After
	public void  calCoverage()
	{
		Mocks.reset();
		ProcessTestCoverage.calculate(rule.getProcessEngine());
	}

}
