package de.berlin.home.kang.webrest;

import de.berlin.home.kang.webrest.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.Map;

/**
 * the backend service task class for ServiceTask "createOrderService"
 * @author xinhua
 *
 */
@Slf4j
@Named("validationOrderService")
public class ValidationOrderService implements JavaDelegate{
	
	/**
	 *  the method which called in ServiceTask "createOrderService"
	 */
	@Override
	public void execute(final DelegateExecution execution) throws Exception {
		
		final Map<String,Object> vars = execution.getVariablesLocal();

		final Order order = (Order) vars.get("order");

		final boolean validated = !StringUtils.equals(order.getName(),"fake");

		log.info("order validated : {}"  ,validated);

		execution.setVariable("validated",validated);
			
	}

	public void whenFailure() throws Exception {
		log.info("no order will be created");

	}
	
	

}
