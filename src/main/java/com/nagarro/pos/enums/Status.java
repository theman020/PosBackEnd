package com.nagarro.pos.enums;

/**
 * this is Status Enum class
 * 
 * @author damanpreetbrar
 *
 */
public enum Status {

	ON_HOLD("On Hold"), COMPLETED("Completed"), REOPEN("Re-Open");

	private String value;

	Status(String value) {
		this.setValue(value);
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
