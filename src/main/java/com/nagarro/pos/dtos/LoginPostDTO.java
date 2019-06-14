package com.nagarro.pos.dtos;

/**
 * Login request body will map to LoginPostDTO object
 * 
 * @author damanpreetbrar
 *
 */
public class LoginPostDTO {
	private String username;
	private String password;
	private double startingAmount;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the startingAmount
	 */
	public double getStartingAmount() {
		return startingAmount;
	}

	/**
	 * @param startingAmount the startingAmount to set
	 */
	public void setStartingAmount(double startingAmount) {
		this.startingAmount = startingAmount;
	}

}
