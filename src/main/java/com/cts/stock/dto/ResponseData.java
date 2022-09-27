package com.cts.stock.dto;

/**
 * The Class ResponseData.
 */
public class ResponseData {

	/** The data. */
	private CompanyDto data;
	
	/** The message. */
	private ResponseMessage message;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public CompanyDto getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(CompanyDto data) {
		this.data = data;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ResponseMessage getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(ResponseMessage message) {
		this.message = message;
	}
}
