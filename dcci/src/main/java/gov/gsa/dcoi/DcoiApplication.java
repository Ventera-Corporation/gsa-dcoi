package gov.gsa.dcoi;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Main class bootstrapping the DCOI application.
 */
@SpringBootApplication
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DcoiApplication {

	@Autowired
	MessageSource messageSource;

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
	}

	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	public Validator getValidator() {
		return validator();
	}
}
