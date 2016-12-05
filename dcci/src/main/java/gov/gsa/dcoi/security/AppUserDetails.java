package gov.gsa.dcoi.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 469878880183256843L;
	private User user;

	public AppUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAllUserAuthorities(user.getRoles());
	}

	public int getId() {
		return user.getDcoiUserId();
	}

	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmailAddress();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isActiveFlag();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	private List<GrantedAuthority> getAllUserAuthorities(List<UserRole> roles) {
		List<GrantedAuthority> authList = new ArrayList<>();
		for (UserRole role : roles) {
			authList.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authList;
	}
}
