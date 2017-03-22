package gov.gsa.dcoi.security;

import java.util.ArrayList;
import java.util.List;

import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.service.ReferenceValueListService;

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
	private List<String> userFieldOffices;
	
	public User(){
		
	}
	
	public User(String email){
		this.emailAddress = email;
	}

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

	/**
	 * Get the user role ids based on the name of the user roles
	 */
	public List<Integer> getRoleIds() {
		List<Integer> roleIds = new ArrayList<>();
		for (UserRole role : this.roles) {
			roleIds.add(role.getDcoiRoleId());
		}
		return roleIds;
	}

	/**
	 * Get the field office ids based on the field office names
	 */
	public List<Integer> getFieldOfficeIds() {
		List<Integer> fieldOfficeIds = new ArrayList<>();
		List<GenericReferenceValueObject> refFieldOffices = ReferenceValueListService.refValueLists
				.get("componentRefValueList");
		for (String fieldOffice : this.userFieldOffices) {
			for (GenericReferenceValueObject refFieldOffice : refFieldOffices) {
				if (fieldOffice.equalsIgnoreCase(refFieldOffice.getValue())) {
					fieldOfficeIds.add(refFieldOffice.getId());
				}
			}
		}
		return fieldOfficeIds;
	}

	public List<String> getUserFieldOffices() {
		return userFieldOffices;
	}

	public void setUserFieldOffices(List<String> userFieldOffices) {
		this.userFieldOffices = userFieldOffices;
	}
}
