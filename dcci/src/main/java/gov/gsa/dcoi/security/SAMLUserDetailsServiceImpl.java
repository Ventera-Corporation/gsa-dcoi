package gov.gsa.dcoi.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;

import gov.gsa.dcoi.security.User;

import gov.gsa.dcoi.repository.UserRepository;

@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

	// Logger
	private static final Logger LOGGER = LoggerFactory.getLogger(SAMLUserDetailsServiceImpl.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SecurityUtils securityUtils;
	
	@Autowired
	DBAuthenticationProvider authProvider;

	public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {

		// The method is supposed to identify local account of user referenced
		// by
		// data in the SAML assertion and return UserDetails object describing
		// the user.

		String userID = credential.getNameID().getValue();

		LOGGER.info(userID + " is logged in");
		
		return authProvider.loadUserByUsername(userID);
		
		//List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		//authorities.add(authority);

		// Connect up to AppUserDetails/SecurityUtils/SecurityController
		//return returnUser;
	}

}
