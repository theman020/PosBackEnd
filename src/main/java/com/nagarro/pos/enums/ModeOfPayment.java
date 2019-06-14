package com.nagarro.pos.enums;

/**
 * this is ModeOfPayment Enum class
 * 
 * @author damanpreetbrar
 *
 */
public enum ModeOfPayment {

	CASH("Cash"), CARD("Card");

	private String value;

	ModeOfPayment(String value) {
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
