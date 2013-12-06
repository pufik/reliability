package ua.edu.lp.reliability.model.user;

public enum UserRole {
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private final String securityCode;

	private UserRole(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getSecurityCode() {
		return securityCode;
	}
}
