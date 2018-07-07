package com.my.microservices.currencyconversionservice.bean;

import java.math.BigDecimal;

/**
 * The Class CurrencyConversionBean.
 */
public class CurrencyConversionBean {

	/** The id. */
	private Long id;
	
	/** The fromm. */
	private String from;
	
	/** The to. */
	private String to;
	
	/** The conversion multiple. */
	private BigDecimal conversionMultiple;
	
	/** The quantity. */
	private BigDecimal quantity;
	
	/** The calculated amount. */
	private BigDecimal calculatedAmount;
	
	private int port;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the fromm.
	 *
	 * @return the fromm
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Sets the fromm.
	 *
	 * @param fromm the new fromm
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Gets the to.
	 *
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
	/**
	 * Sets the to.
	 *
	 * @param to the new to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	
	/**
	 * Gets the conversion multiple.
	 *
	 * @return the conversion multiple
	 */
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	
	/**
	 * Sets the conversion multiple.
	 *
	 * @param conversionMultiple the new conversion multiple
	 */
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Gets the calculated amount.
	 *
	 * @return the calculated amount
	 */
	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}
	
	/**
	 * Sets the calculated amount.
	 *
	 * @param calculatedAmount the new calculated amount
	 */
	public void setCalculatedAmount(BigDecimal calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurrencyConversionBean [id=" + id + ", fromm=" + from + ", to=" + to + ", conversionMultiple="
				+ conversionMultiple + ", quantity=" + quantity + ", calculatedAmount=" + calculatedAmount + "]";
	}
	
	/**
	 * Instantiates a new currency conversion bean.
	 *
	 * @param id the id
	 * @param fromm the fromm
	 * @param to the to
	 * @param conversionMultiple the conversion multiple
	 * @param quantity the quantity
	 * @param calculatedAmount the calculated amount
	 */
	public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple, BigDecimal quantity,
			BigDecimal calculatedAmount,int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.calculatedAmount = calculatedAmount;
		this.port = port;
	}
	
	/**
	 * Instantiates a new currency conversion bean.
	 */
	public CurrencyConversionBean() {
		
	}
}
