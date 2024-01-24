/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model.notify;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class IoContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("markdown")
	private String markdown;

	@JsonProperty("payment_data")
	private IoPaymentData paymentData;

	@JsonProperty("due_date")
	private String dueDate;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public IoPaymentData getPaymentData() {
		return paymentData;
	}

	public void setPaymentData(IoPaymentData paymentData) {
		this.paymentData = paymentData;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String toString()
	{
		
		  String result=  (null!= getSubject()? "subject: "+getSubject():"" )+" - "+
				(null!= getMarkdown()? "markdown: "+getMarkdown():"")+" - "+
					(null!= getDueDate()? "dueDate: "+getDueDate():"");
		  return result;
	}

}
