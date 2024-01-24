/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.business.messaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Messaggi {

	List<Messaggio> messaggi = new ArrayList<Messaggio>();
	
	public Messaggi() {
	}
	
	public Messaggi(Messaggio messaggio) {
		addMessaggio(messaggio);
	}

	public void addMessaggio(Messaggio messaggio) {
		messaggi.add(messaggio);
	}

	public void addMessaggio(LevelMessage level, String testo) {
		messaggi.add(new Messaggio(level, testo));
	}

	public List<Messaggio> getAllMessaggi() {
		return messaggi;
	}
	
	public Messaggio getFirstMessage() {
		if (messaggi.isEmpty()) {
			return new Messaggio(LevelMessage.SUCCESS, "");
		}
		return messaggi.get(0);
	}
	
	public List<Messaggio> getSuccessMessaggi() {
		return filter(LevelMessage.SUCCESS);
	}
	
	public List<Messaggio> getInfoMessaggi() {
		return filter(LevelMessage.INFO);
	}
	
	public List<Messaggio> getWarnungMessaggi() {
		return filter(LevelMessage.WARNING);
	}
	
	public List<Messaggio> getDangerMessaggi() {
		return filter(LevelMessage.DANGER);
	}

	private List<Messaggio> filter(LevelMessage level) {
		List<Messaggio> lista = new ArrayList<Messaggio>();
		for (Messaggio messaggio : messaggi) {
			if (messaggio.getLevel().equals(level)) {
				lista.addAll(messaggi);
			}
		}
		return lista;
	}
	
	public List<Messaggio> getOrderGravityMessaggi() {
		Collections.sort(messaggi, Collections.reverseOrder());
		return messaggi;
	}
	
	public Boolean isEmpty() {
		return messaggi.isEmpty();
	}

	public Boolean hasSuccess() {
		return hasType(LevelMessage.SUCCESS);
	}
	
	public Boolean hasInfo() {
		return hasType(LevelMessage.INFO);
	}
	
	public Boolean hasWarning() {
		return hasType(LevelMessage.WARNING);
	}
	
	public Boolean hasDanger() {
		return hasType(LevelMessage.DANGER);
	}
	
	private Boolean hasType(LevelMessage level) {
		for (Messaggio messaggio : messaggi) {
			if (messaggio.getLevel().equals(level)) {
				return true;
			}
		}
		return false;
	}
	
}
