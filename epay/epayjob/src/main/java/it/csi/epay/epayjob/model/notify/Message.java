/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.model.notify;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	private String uuid;

	@JsonProperty("expire_at")
	private String expireAt;

	@JsonProperty("payload")
	private Notification payload;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}

	public Notification getPayload() {
		return payload;
	}

	public void setPayload(Notification payload) {
		this.payload = payload;
	}
	
	public String toString()
	{
		return "uuid: "+getUuid() +" - "+
				"expireAt: "+getExpireAt() +" - "+
				"payload: "+(null!= getPayload()? getPayload().toString():"");
				
		
	}


}
