package de.berlin.home.kang.webrest;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

/**
 * 
 * @author xinhua
 *
 * the boot class to start the rest interface
 *
 */

@ProcessApplication
public class BpmnBoot extends ServletProcessApplication {
	
	/**
	 * use to create a new login account by deploy the app
	 * 
	 */
	private DemoDataGenerator demoDataGenerator = new DemoDataGenerator();

	/**
	 * 
	 * use DemoDataGenerator class from camunda in-box invoice example to create demo account
	 * 
	 * @param processEngine
	 * 
	 */
	@PostDeploy
	public void startFirstProcess(final ProcessEngine processEngine) {

		this.demoDataGenerator.createUsers(processEngine);
	}

}
