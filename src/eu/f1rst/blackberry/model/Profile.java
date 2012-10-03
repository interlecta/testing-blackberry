package eu.f1rst.blackberry.model;

public class Profile extends BaseModel {

	private String displayName;
	private String email;

	private int defaultLanguage;
	private int language;


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
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", userCode=");
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
		result = (prime * result);
		result = (prime * result) + defaultLanguage;
		result = (prime * result)
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = (prime * result) + ((email == null) ? 0 : email.hashCode());
		result = (prime * result) + language;
		result = (prime * result);
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
		return true;
	}

}
