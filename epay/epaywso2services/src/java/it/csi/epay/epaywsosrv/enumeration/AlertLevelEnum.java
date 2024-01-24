/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.enumeration;

public enum AlertLevelEnum {
	NONE(0),
	WARNING(1),
	ERROR(2);
	
	private int level;
	
	AlertLevelEnum(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static AlertLevelEnum fromLevel(Integer level) {
		if (level != null) {
			for (AlertLevelEnum en : values()) {
				if (en.getLevel() == level)
					return en;
			}
		}
		return null;
	}
}
