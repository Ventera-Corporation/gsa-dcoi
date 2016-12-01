package gov.gsa.dcoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import gov.gsa.dcoi.service.ReferenceValueListService;

@SpringBootApplication
@EnableCaching
public class DcoiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DcoiApplication.class, args);
		ReferenceValueListService refListService = (ReferenceValueListService) context.getBean(ReferenceValueListService.class);
		refListService.initRefValueLists();
	}
}
