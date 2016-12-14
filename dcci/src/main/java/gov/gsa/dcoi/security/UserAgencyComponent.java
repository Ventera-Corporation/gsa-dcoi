package gov.gsa.dcoi.security;

import java.util.List;

/**
 * Class to hold the User Agency Component (contraints) details.
 */
public class UserAgencyComponent {
	private int dcoiUserAgencyComponentId;
	private int dcoiUserId;
	private int agencyComponentId;
	private boolean activeFlag;

	public int getDcoiUserAgencyComponentId() {
		return dcoiUserAgencyComponentId;
	}

	public void setDcoiUserAgencyComponentId(int dcoiUserAgencyComponentId) {
		this.dcoiUserAgencyComponentId = dcoiUserAgencyComponentId;
	}

	public int getDcoiUserId() {
		return dcoiUserId;
	}

	public void setDcoiUserId(int dcoiUserId) {
		this.dcoiUserId = dcoiUserId;
	}

	public int getAgencyComponentId() {
		return agencyComponentId;
	}

	public void setAgencyComponentId(int agencyComponentId) {
		this.agencyComponentId = agencyComponentId;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	
}
