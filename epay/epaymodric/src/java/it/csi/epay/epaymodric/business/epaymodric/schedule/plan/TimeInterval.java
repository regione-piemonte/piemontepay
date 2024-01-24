/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule.plan;

public class TimeInterval {
	// ora minuti in formato intero ottenuto con la seguente formula: hh * 60 + mm
	private int startTime;
	private int endTime;

	public TimeInterval() {
	}

	public TimeInterval(int startHH, int startMM, int endHH, int endMM) {
		setStartTime(startHH, startMM);
		setEndTime(endHH, endMM);
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setEndTime(int hh, int mm) {
		checkInput(hh, mm);
		endTime = hh * 60 + mm;
	}

	public void setStartTime(int hh, int mm) {
		checkInput(hh, mm);
		startTime = hh * 60 + mm;
	}

	private void checkInput(int hh, int mm) {
		if (hh < 0 || hh > 23)
			throw new IllegalArgumentException("Hours out of range");

		if (mm < 0 || mm > 59)
			throw new IllegalArgumentException("Minutes out of range");
	}
}
