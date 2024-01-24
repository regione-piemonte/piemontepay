/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

public class StatisticaApplicazioneTransazioneDTO extends BaseDto {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String applicationId;
	Integer notInitialized=0;
	Integer initialized=0;
	Integer configured=0;
	Integer started=0;
	Integer successful=0;
	Integer unsuccessful=0;
	Integer canceled=0;
	Integer refunded=0;
	Integer toBeConfirmed=0;
	Integer attesaRT=0;
	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the notInitialized
	 */
	public Integer getNotInitialized() {
		return notInitialized;
	}
	/**
	 * @param notInitialized the notInitialized to set
	 */
	public void setNotInitialized(Integer notInitialized) {
		this.notInitialized = notInitialized;
	}
	/**
	 * @return the initialized
	 */
	public Integer getInitialized() {
		return initialized;
	}
	/**
	 * @param initialized the initialized to set
	 */
	public void setInitialized(Integer initialized) {
		this.initialized = initialized;
	}
	/**
	 * @return the configured
	 */
	public Integer getConfigured() {
		return configured;
	}
	/**
	 * @param configured the configured to set
	 */
	public void setConfigured(Integer configured) {
		this.configured = configured;
	}
	/**
	 * @return the started
	 */
	public Integer getStarted() {
		return started;
	}
	/**
	 * @param started the started to set
	 */
	public void setStarted(Integer started) {
		this.started = started;
	}
	/**
	 * @return the successful
	 */
	public Integer getSuccessful() {
		return successful;
	}
	/**
	 * @param successful the successful to set
	 */
	public void setSuccessful(Integer successful) {
		this.successful = successful;
	}
	/**
	 * @return the unsuccessful
	 */
	public Integer getUnsuccessful() {
		return unsuccessful;
	}
	/**
	 * @param unsuccessful the unsuccessful to set
	 */
	public void setUnsuccessful(Integer unsuccessful) {
		this.unsuccessful = unsuccessful;
	}
	/**
	 * @return the canceled
	 */
	public Integer getCanceled() {
		return canceled;
	}
	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(Integer canceled) {
		this.canceled = canceled;
	}
	/**
	 * @return the refunded
	 */
	public Integer getRefunded() {
		return refunded;
	}
	/**
	 * @param refunded the refunded to set
	 */
	public void setRefunded(Integer refunded) {
		this.refunded = refunded;
	}
	/**
	 * @return the toBeConfirmed
	 */
	public Integer getToBeConfirmed() {
		return toBeConfirmed;
	}
	/**
	 * @param toBeConfirmed the toBeConfirmed to set
	 */
	public void setToBeConfirmed(Integer toBeConfirmed) {
		this.toBeConfirmed = toBeConfirmed;
	}
	/**
	 * @return the attesaRT
	 */
	public Integer getAttesaRT() {
		return attesaRT;
	}
	/**
	 * @param attesaRT the attesaRT to set
	 */
	public void setAttesaRT(Integer attesaRT) {
		this.attesaRT = attesaRT;
	}
}
