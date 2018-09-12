package de.berlin.home.kang.webrest;

import java.util.Map;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import de.berlin.home.kang.webrest.control.PersistenceDAO;
import de.berlin.home.kang.webrest.model.Order;

/**
 * the backend service task class for ServiceTask "createOrderService"
 * @author xinhua
 *
 */
@Named("createOrderService")
public class CreateOrderService implements JavaDelegate{
	
	/**
	 * TODO: there is deployment problem when using CDI inject
	 * did not have time to solve this problem, 
	 * thus, create an instance directly 
	 * 
	 */
	private PersistenceDAO dao = new PersistenceDAO();
	
	/**
	 *  the method which called in ServiceTask "createOrderService"
	 */
	@Override
	public void execute(final DelegateExecution execution) throws Exception {
		
		final Map<String,Object> vars = execution.getVariablesLocal();
		
		final Order order = (Order) vars.get("order");
			
		this.dao.createOrder(order);
	}


	
	

}
