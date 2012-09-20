package eu.f1rst.blackberry.model;

//import java.util.List;
import java.util.Vector;

import net.rim.device.api.util.Persistable;

public class QuickInfoItem implements QuickInfo, Persistable {

//	private static final long serialVersionUID = -2783810719543476165L;

	private String key;
	private/* List<QuickInfoPair> */Vector pairs;

	// @Override
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public/* List<QuickInfoPair> */Vector getPairs() {
		return pairs;
	}

	public void setPairs(/* List<QuickInfoPair> */Vector pairs) {
		this.pairs = pairs;
	}

	// @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((key == null) ? 0 : key.hashCode());
		result = (prime * result) + ((pairs == null) ? 0 : pairs.hashCode());
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
		QuickInfoItem other = (QuickInfoItem) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (pairs == null) {
			if (other.pairs != null) {
				return false;
			}
		} else if (!pairs.equals(other.pairs)) {
			return false;
		}
		return true;
	}

	// @Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("QuickInfoItem [key=");
		builder.append(key);
		builder.append(", pairs=");
		builder.append(pairs);
		builder.append("]");
		return builder.toString();
	}

	// @Override
	public boolean isSection() {
		return true;
	}

	// @Override
	public String getValue() {
		return "";
	}

}
