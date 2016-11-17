package gov.gsa.dcoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration(exclude =  {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableJpaRepositories(basePackages="gov.gsa.dcoi.repository", entityManagerFactoryRef="emf")
//@EntityScan("gov.gsa.dcoi.entity")
public class DcoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcoiApplication.class, args);
	}
}
