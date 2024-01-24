/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util;

import java.util.concurrent.TimeUnit;

public class Timekeeper {
	private long time;
	private String name = "";
	
	public Timekeeper() {
		this.time = System.nanoTime();
	}
	
	public Timekeeper(String name) {
		this.time = System.nanoTime();
		this.name = name;
	}
	
	public void rename(String name) {
		this.name = name;
	}
	
	public void restart() {
		this.time = System.nanoTime();
	}
	
	public void renameAndRestart(String name) {
		rename(name);
		restart();
	}
	
	public long stop() {
		time = System.nanoTime() - time;
		return time;
	}
	
	public String printTime() {
		long milliseconds = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) % 1000;
		long seconds = TimeUnit.SECONDS.convert(time, TimeUnit.NANOSECONDS);
		return String.format("%s - Elapsed %d ns (%02d ore %02d minuti %02d secondi %03d millisecondi)", name, time, (seconds/(60*60))%60, (seconds/60)%60, seconds%60, milliseconds);
	}
	
	public String stopAndPrintTime() {
		stop();
		return printTime();
	}

}
