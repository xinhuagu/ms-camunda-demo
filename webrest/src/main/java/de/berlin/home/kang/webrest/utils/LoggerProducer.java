package de.berlin.home.kang.webrest.utils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

/**
 * 
 * factory pattern of CDI to produce a Logger instance
 * 
 * @author xinhua
 *
 */
public class LoggerProducer {

	
	
	
	/**
	 * 
	 * init a new instance of Logger for CDI inject
	 * 
	 * @param injectionPoint
	 * @return
	 */
    @Produces
    @Dependent
    public Logger createLogger(final InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getBean().getBeanClass().getName());
    }
}
