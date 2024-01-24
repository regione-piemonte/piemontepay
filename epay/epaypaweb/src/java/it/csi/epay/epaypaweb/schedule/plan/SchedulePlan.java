/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.schedule.plan;

import java.util.Calendar;

public class SchedulePlan {
	static private final SchedulePlan always;
	static private final SchedulePlan never;

	//@formatter:off
	static {
		always = new SchedulePlan();
		always.setDays(new Integer[] {
			Calendar.MONDAY, 
			Calendar.TUESDAY,
			Calendar.WEDNESDAY,
			Calendar.THURSDAY,
			Calendar.FRIDAY,
			Calendar.SATURDAY,
			Calendar.SUNDAY
		});

		never = new SchedulePlan();
		never.setDays(new Integer[] {});
	}
	//@formatter:on

	private Integer[] days;
	private TimeInterval[] timeIntervals;

	static public SchedulePlan getAlwaysRange() {
		return always;
	}

	static public SchedulePlan getNeverRange() {
		return never;
	}

	public Integer[] getDays() {
		return days;
	}

	public void setDays(Integer[] days) {
		this.days = days;
	}

	public TimeInterval[] getTimeIntervals() {
		return timeIntervals;
	}

	public void setTimeIntervals(TimeInterval[] timeIntervals) {
		this.timeIntervals = timeIntervals;
	}
}
