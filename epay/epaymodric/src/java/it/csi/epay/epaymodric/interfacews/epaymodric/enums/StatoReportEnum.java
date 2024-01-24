/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.interfacews.epaymodric.enums;

public enum StatoReportEnum {
	INSERTED(1,"INSERTED"),
	WIP(2,"WIP"),
	COMPLETED(3,"COMPLETED"),
	FAILED(4,"FAILED"),
	DOWNLOADED(5,"DOWNLOADED"),
	EMPTY(6,"EMPTY"),
	OVERFULL(7,"OVERFULL");
	
	private String code;
	private int id;
	
	private StatoReportEnum(int id,String code) {
		this.id = id;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public int getId() {
		return id;
	}
	
	static public StatoReportEnum fromId(String code) {
		for (StatoReportEnum en : StatoReportEnum.values()) {
			if (en.code.equals(code)) {
				return en;
			}
		}
		return null;
	}
}
