package de.berlin.home.kang.webrest.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import de.berlin.home.kang.webrest.control.PersistenceDAO;

/**
 * test cases should cover all DAOProducer class
 * @author xinhua
 *
 */
public class DAOProducerTest {

	/**
	 * test producer for EM
	 */
	@Test
	public void testCreateEntityManager() {
		
		final DAOProducer producer = new DAOProducer();
		
		final PersistenceDAO dao = producer.createEntityManager();
		assertNotNull(dao);
	}

}
