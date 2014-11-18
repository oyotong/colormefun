package shop.common.context;

import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import shop.common.exception.ApplicationException;

/**
 * Application Lifecycle Listener implementation class ApplicationContextListener
 *
 */
public class ApplicationContextListener implements ServletContextListener {
	
	ApplicationContext context = ApplicationContext.getContext();

    /**
     * Default constructor. 
     */
    public ApplicationContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	ServletContext servletContext = arg0.getServletContext();
    	
		context.setApplication(arg0.getServletContext());
		Properties config = new Properties();
		try {
			config.load(this.getClass().getResourceAsStream(
					"/config/configuration.properties"));
			Set keys = config.keySet();

			for (Object k : keys) {				
				String value = config.getProperty(k.toString());
				context.addConfiguration(k.toString(), value);
			}
			
			//servletContext.setAttribute("ROOT", context.getConfiguration("Servlet.Context.ROOT"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
