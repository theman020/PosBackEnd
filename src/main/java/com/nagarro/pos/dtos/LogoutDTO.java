package com.nagarro.pos.dtos;

/**
 * Logout request body will map to LogoutDTo object
 * 
 * @author damanpreetbrar
 *
 */
public class LogoutDTO {

	private long cashDrawerId;
	private long endingAmount;

	/**
	 * @return the cashDrawerId
	 */
	public long getCashDrawerId() {
		return cashDrawerId;
	}

	/**
	 * @param cashDrawerId the cashDrawerId to set
	 */
	public void setCashDrawerId(long cashDrawerId) {
		this.cashDrawerId = cashDrawerId;
	}

	/**
	 * @return the endingAmount
	 */
	public long getEndingAmount() {
		return endingAmount;
	}

	/**
	 * @param endingAmount the endingAmount to set
	 */
	public void setEndingAmount(long endingAmount) {
		this.endingAmount = endingAmount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogoutDTO [cashDrawerId=" + cashDrawerId + ", endingAmount=" + endingAmount + "]";
	}

}
