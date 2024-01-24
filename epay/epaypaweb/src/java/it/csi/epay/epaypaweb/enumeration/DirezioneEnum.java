/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum DirezioneEnum {
	INPUT("I"), OUTPUT("O");

	private String id;

	private DirezioneEnum(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	static public DirezioneEnum fromId(String id) {
		for (DirezioneEnum en : DirezioneEnum.values()) {
			if (en.id.equals(id)) {
				return en;
			}
		}
		return null;
	}
}
