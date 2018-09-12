package de.berlin.home.kang.webrest.boundary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;

import de.berlin.home.kang.webrest.control.PersistenceDAO;
import de.berlin.home.kang.webrest.model.Order;

/**
 * 
 * REST Resource class for order
 * 
 * @author xinhua
 *
 */
@Path("order")
public class OrderResource {

	/**
	 * the key of the bpmn workflow
	 */
	public  static String PROCESS_KEY = "wsrest_demo_process";
	
	/**
	 * process engine of camunda
	 */
	protected ProcessEngine processEngine = BpmPlatform.getDefaultProcessEngine();

	/**
	 * TODO: should be injected through CDI, but there is a dependency problem need
	 * to fix it.
	 */
	protected PersistenceDAO dao = new PersistenceDAO();


	/**
	 * 
	 * get an order detail by its number
	 * 
	 * @param number
	 * @return
	 */
	@GET
	@Path("/{number}")
	public Response get(@PathParam("number") final Long number) {
		final Order order = this.dao.findByOrderNumber(number);
		return Response.ok(order).build();
	}

	/**
	 * get all order list
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response orders() {
		return Response.ok(this.dao.findAllOrders()).build();
	}

	/**
	 * 
	 * create an order the workflow will be started here
	 * 
	 * @param order
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(final Order order) {

		// variables which need be insert in workflow instance context
		final Map<String, Object> vars = new ConcurrentHashMap<>();

		vars.put("order", Variables.objectValue(order).serializationDataFormat("application/json").create());
		this.processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_KEY, order.getNumber().toString(), vars);
		return Response.ok("Order with number " + order.getNumber() + " is created").build();
	}

}
