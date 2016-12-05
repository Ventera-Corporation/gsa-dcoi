package gov.gsa.dcoi.security;

import java.util.List;

/**
 * Class to hold the User details.
 */
public class User {
	private int dcoiUserId;
	private String firstName;
	private String lastName;
	private String password;
	private String emailAddress;
	private boolean activeFlag;

	private List<UserRole> roles;

	public int getDcoiUserId() {
		return dcoiUserId;
	}

	public void setDcoiUserId(int dcoiUserId) {
		this.dcoiUserId = dcoiUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}
