package gov.gsa.dcoi.security;

/**
 * Class to hold the User Roles
 */
public class UserRole {
	private int dcoiUserRoleId;
	private int dcoiUserId;
	private int dcoiRoleId;
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getDcoiUserRoleId() {
		return dcoiUserRoleId;
	}

	public void setDcoiUserRoleId(int dcoiUserRoleId) {
		this.dcoiUserRoleId = dcoiUserRoleId;
	}

	public int getDcoiUserId() {
		return dcoiUserId;
	}

	public void setDcoiUserId(int dcoiUserId) {
		this.dcoiUserId = dcoiUserId;
	}

	public int getDcoiRoleId() {
		return dcoiRoleId;
	}

	public void setDcoiRoleId(int dcoiRoleId) {
		this.dcoiRoleId = dcoiRoleId;
	}
	
	@Override
	public String toString() {
		return "UserRole [roleName=" + roleName + "]";
	}
}
