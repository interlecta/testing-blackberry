package eu.f1rst.blackberry.model;

import net.rim.device.api.util.Persistable;

public class QuickInfoPair implements QuickInfo, Persistable {

//	private static final long serialVersionUID = 1315185529597811793L;

	private String key;
	private String value;

	// @Override
	public boolean isSection() {
		return false;
	}

	// @Override
	public String getKey() {
		return key;
	}

	// @Override
	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	// @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((key == null) ? 0 : key.hashCode());
		result = (prime * result) + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	// @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		QuickInfoPair other = (QuickInfoPair) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	// @Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("QuickInfoPair [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
