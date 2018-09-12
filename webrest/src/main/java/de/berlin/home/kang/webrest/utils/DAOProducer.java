package de.berlin.home.kang.webrest.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import de.berlin.home.kang.webrest.control.PersistenceDAO;

/**
 * 
 * factory pattern of CDI to produce a PersistenceDAO instance
 * 
 * @author xinhua
 *
 */
public class DAOProducer {
	

	/**
	 * create a new instance of PersistenceDAO for CDI inject
	 * @return
	 */
    @Produces
    @RequestScoped
    public PersistenceDAO createEntityManager() {
    	return new PersistenceDAO();
    }
    

}
