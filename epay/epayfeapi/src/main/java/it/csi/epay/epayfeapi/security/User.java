/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.security;

import java.util.Date;


@SuppressWarnings ( "unused" )
public class User {

	private String name;

	private String remoteAddress;

	private Date timestamp;

	public User ( String name, String remoteAddress, Date timestamp ) {
		this.name = name;
		this.remoteAddress = remoteAddress;
		this.timestamp = timestamp;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getRemoteAddress () {
		return remoteAddress;
	}

	public void setRemoteAddress ( String remoteAddress ) {
		this.remoteAddress = remoteAddress;
	}

	public Date getTimestamp () {
		return timestamp;
	}

	public void setTimestamp ( Date timestamp ) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString () {
		return "{ " +
						"name:" + name +
						", remoteAddress:" + remoteAddress +
						", timestamp:" + timestamp +
						" }";
	}
}
