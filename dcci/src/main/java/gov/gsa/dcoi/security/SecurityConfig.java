package gov.gsa.dcoi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String REMEMBER_ME_KEY = "rememberme_key";

	@Autowired
	private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private RestAccessDeniedHandler restAccessDeniedHandler;

	@Autowired
	private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;

	@Autowired
	private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

	@Autowired
	private DBAuthenticationProvider dbAuthenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(dbAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/app/**")
			.antMatchers("/assets/**")
			.antMatchers("/plugins/**")
			.antMatchers("/actuator/*")
			.antMatchers("/index.html", "/login.html", "/");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.headers().disable()
			.csrf().disable()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(restAuthenticationEntryPoint)
			.accessDeniedHandler(restAccessDeniedHandler)
			.and()
			.formLogin().loginProcessingUrl("/authenticate")
			.successHandler(restAuthenticationSuccessHandler)
			.failureHandler(restAuthenticationFailureHandler)
			.usernameParameter("username").passwordParameter("password").permitAll()
			.and()
			.logout().logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
			.deleteCookies("JSESSIONID").permitAll();
	}
}
