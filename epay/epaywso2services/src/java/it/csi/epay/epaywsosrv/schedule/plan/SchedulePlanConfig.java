/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule.plan;

import java.util.List;

public class SchedulePlanConfig {

	private List<SchedulePlan> schedulePlans;

	public List<SchedulePlan> getSchedulePlans() {
		return schedulePlans;
	}

	public void setSchedulePlans(List<SchedulePlan> schedulePlans) {
		this.schedulePlans = schedulePlans;
	}

}
