package de.berlin.home.kang.webrest.control;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.berlin.home.kang.webrest.model.Order;

/**
 * this class is the persistence interface
 * which contains CRUD operations
 * 
 * @author xinhua
 *
 */
public class PersistenceDAO {



	/*
	 * because tomcat is not a EE container 
	 * we need use emFactory to get EM manually
	 */
	private static EntityManagerFactory emFactory;
	
	//
	/*
	 * unit name which is defined in persistence.xml
	 */
	public static final String PERSISTENCE_UNIT_NAME = "webrest";
	


	/**
	 * here we get EM from emFactory
	 * @return
	 */
	public final static EntityManager getEntityManager() {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		return emFactory.createEntityManager();
	}
	
	/**
	 * find order by number
	 * @param order number
	 * @return order object
	 * 
	 * TODO: 
	 * need to handle EntityNotFoundException
	 */
	public Order findByOrderNumber(final Long number) {
		/*
		 * get EM here
		 */
		final EntityManager entityMgr = getEntityManager();
		return entityMgr.find(Order.class, number);
	}

	/**
	 * find all orders
	 * @return an immutable collection which contains the orders or empty collection
	 */
	public Collection<Order> findAllOrders() {
		final EntityManager entityMgr = getEntityManager();
		final TypedQuery<Order> findAll = entityMgr.createNamedQuery(Order.FIND_ALL, Order.class);
		return Collections.unmodifiableCollection(findAll.getResultList());
	}

	/**
	 * create an order
	 * @param order
	 * 
	 * TODO:
	 * consider of possible exceptions 
	 */
	public void createOrder(final Order order) {

		final EntityManager entityMgr = getEntityManager();
		entityMgr.getTransaction().begin();

		entityMgr.persist(order);

		entityMgr.getTransaction().commit();

		entityMgr.clear();
		
		//TODO:
		//consider to use Logger
		System.out.printf("Order with number %s Successfully Inserted In The Database",order.getNumber());
	}
}