package gov.gsa.dcoi;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import gov.gsa.dcoi.service.CommonHelper;
import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Main class bootstrapping the DCOI application.
 */
@SpringBootApplication
@EnableCaching
public class DcoiApplication {

	/**
	 * Entry method for bootstrapping the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DcoiApplication.class, args);
		ReferenceValueListService refListService = (ReferenceValueListService) context
				.getBean(ReferenceValueListService.class);
		refListService.initRefValueLists();

		CommonHelper.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

	}

	/**
	 * Create message source bean to point to messages.properties
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
}
