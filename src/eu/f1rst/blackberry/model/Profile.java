package eu.f1rst.blackberry.model;

public class Profile extends BaseModel {

	private String customerCode;
	private String displayName;
	private String email;
	private String userCode;
	private int defaultLanguage;
	private int language;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(int defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	// @Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Profile [customerCode=");
		builder.append(customerCode);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", userCode=");
		builder.append(userCode);
		builder.append(", defaultLanguage=");
		builder.append(defaultLanguage);
		builder.append(", language=");
		builder.append(language);
		builder.append("]");
		return builder.toString();
	}

	// @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result)
				+ ((customerCode == null) ? 0 : customerCode.hashCode());
		result = (prime * result) + defaultLanguage;
		result = (prime * result)
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + language;
		result = (prime * result)
				+ ((userCode == null) ? 0 : userCode.hashCode());
		return result;
	}

	// @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Profile other = (Profile) obj;
		if (customerCode == null) {
			if (other.customerCode != null) {
				return false;
			}
		} else if (!customerCode.equals(other.customerCode)) {
			return false;
		}
		if (defaultLanguage != other.defaultLanguage) {
			return false;
		}
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (language != other.language) {
			return false;
		}
		if (userCode == null) {
			if (other.userCode != null) {
				return false;
			}
		} else if (!userCode.equals(other.userCode)) {
			return false;
		}
		return true;
	}

}
