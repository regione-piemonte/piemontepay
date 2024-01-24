/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import java.io.Serializable;

public class ReportQueueStatusDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private QueueStatusDto status;
	private boolean enabledEvent;
	private boolean disabledEvent;
	private boolean overThresholdEvent;
	private boolean underThresholdEvent;
	private int sizeThreshold;

	public QueueStatusDto getStatus() {
		return status;
	}

	public void setStatus(QueueStatusDto status) {
		this.status = status;
	}

	public boolean isEnabledEvent() {
		return enabledEvent;
	}

	public void setEnabledEvent(boolean enabledEvent) {
		this.enabledEvent = enabledEvent;
	}

	public boolean isDisabledEvent() {
		return disabledEvent;
	}

	public void setDisabledEvent(boolean disabledEvent) {
		this.disabledEvent = disabledEvent;
	}

	public boolean isOverThresholdEvent() {
		return overThresholdEvent;
	}

	public void setOverThresholdEvent(boolean overThresholdEvent) {
		this.overThresholdEvent = overThresholdEvent;
	}

	public boolean isUnderThresholdEvent() {
		return underThresholdEvent;
	}

	public void setUnderThresholdEvent(boolean underThresholdEvent) {
		this.underThresholdEvent = underThresholdEvent;
	}

	public int getSizeThreshold() {
		return sizeThreshold;
	}

	public void setSizeThreshold(int sizeThreshold) {
		this.sizeThreshold = sizeThreshold;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuffer s = new StringBuffer();
		s.append("{ ");
		s.append("status:").append(status).append(COMMA);
		s.append("enabledEvent:").append(enabledEvent).append(COMMA);
		s.append("disabledEvent:").append(disabledEvent).append(COMMA);
		s.append("overThresholdEvent:").append(overThresholdEvent).append(COMMA);
		s.append("underThresholdEvent:").append(underThresholdEvent).append(COMMA);
		s.append("sizeThreshold:").append(sizeThreshold);
		s.append(" }");
		return s.toString();
	}

}
