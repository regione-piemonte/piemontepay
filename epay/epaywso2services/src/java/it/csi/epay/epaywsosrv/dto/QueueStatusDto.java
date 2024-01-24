/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;

/** dto business <-> scheduler */
public class QueueStatusDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Long queueSize;
	private Long consumerCount;
	private Long enqueueCount;
	private Long dequeueCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(Long queueSize) {
		this.queueSize = queueSize;
	}

	public Long getConsumerCount() {
		return consumerCount;
	}

	public void setConsumerCount(Long consumerCount) {
		this.consumerCount = consumerCount;
	}

	public Long getEnqueueCount() {
		return enqueueCount;
	}

	public void setEnqueueCount(Long enqueueCount) {
		this.enqueueCount = enqueueCount;
	}

	public Long getDequeueCount() {
		return dequeueCount;
	}

	public void setDequeueCount(Long dequeueCount) {
		this.dequeueCount = dequeueCount;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuffer s = new StringBuffer();
		s.append("{ ");
		s.append("name:" + quote(name) + COMMA);
		s.append("queueSize:" + queueSize + COMMA);
		s.append("consumerCount:" + consumerCount + COMMA);
		s.append("enqueueCount:" + enqueueCount + COMMA);
		s.append("dequeueCount:" + dequeueCount);
		s.append(" }");
		return s.toString();
	}

}
