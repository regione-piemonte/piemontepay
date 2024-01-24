/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model.notify;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("email")
	private Email email;

	@JsonProperty("sms")
	private Sms sms;

	@JsonProperty("push")
	private Push push;

	@JsonProperty("mex")
	private Mex mex;

	@JsonProperty("io")
	private Io io;

	@JsonProperty("memo")
	private Memo memo;

	@JsonProperty("tag")
	private String tag;

	@JsonProperty("correlation_id")
	private String correlationId;

	@JsonProperty("bulk_id")
	private String bulkId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Sms getSms() {
		return sms;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}

	public Push getPush() {
		return push;
	}

	public void setPush(Push push) {
		this.push = push;
	}

	public Mex getMex() {
		return mex;
	}

	public void setMex(Mex mex) {
		this.mex = mex;
	}

	public Io getIo() {
		return io;
	}

	public void setIo(Io io) {
		this.io = io;
	}

	public Memo getMemo() {
		return memo;
	}

	public void setMemo(Memo memo) {
		this.memo = memo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getBulkId() {
		return bulkId;
	}

	public void setBulkId(String bulkId) {
		this.bulkId = bulkId;
	}
	
	public String toString()
	{
		String result=  (null!= getId()? "id: "+getId():"") +" - "+
				(null!= getUserId()? "user_id: "+getUserId():"" )+" - "+
				(null!= getBulkId()? "bulkId: "+getBulkId():"" )+" - "+
				(null!= getIo()?  "Io: "+getIo().toString():"");
		return result ;
	}
	



}
