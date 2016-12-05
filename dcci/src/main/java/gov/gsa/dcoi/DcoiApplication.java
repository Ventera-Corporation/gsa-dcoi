package gov.gsa.dcoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Main class bootstrapping the DCOI application.
 */
@SpringBootApplication
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DcoiApplication {

	private DcoiApplication() {}
	
	/**
	 * Entry method for bootstrapping the application
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DcoiApplication.class, args);
		ReferenceValueListService refListService = (ReferenceValueListService) context.getBean(ReferenceValueListService.class);
		refListService.initRefValueLists();
	}
}
