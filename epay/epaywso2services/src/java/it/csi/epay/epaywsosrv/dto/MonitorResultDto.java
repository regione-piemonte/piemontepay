/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class MonitorResultDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String activeMQStatus;
	private String databaseStatus;
	private List<QueueStatusDto> activeMQInfo;

	@XmlElement(name = "activeMQStatus")
	public String getActiveMQStatus() {
		return activeMQStatus;
	}

	public void setActiveMQStatus(String activeMQStatus) {
		this.activeMQStatus = activeMQStatus;
	}

	@XmlElement(name = "databaseStatus")
	public String getDatabaseStatus() {
		return databaseStatus;
	}

	public void setDatabaseStatus(String databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	@XmlElement(name = "queueInfo")
	public List<QueueStatusDto> getActiveMQInfo() {
		return activeMQInfo;
	}

	public void setActiveMQInfo(List<QueueStatusDto> activeMQInfo) {
		this.activeMQInfo = activeMQInfo;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("activeMQStatus:").append(quote(activeMQStatus)).append(COMMA);
		s.append("databaseStatus:").append(quote(databaseStatus)).append(COMMA);
		s.append("activeMQInfo:").append(activeMQInfo);
		s.append(" }");
		return s.toString();
	}

}
