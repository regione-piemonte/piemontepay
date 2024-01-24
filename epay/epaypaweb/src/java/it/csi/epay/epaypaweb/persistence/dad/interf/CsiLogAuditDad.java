/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import it.csi.epay.epaypaweb.dto.common.PrincipalDto;
import it.csi.epay.epaypaweb.enumeration.CsiLogAuditOperationEnum;

public interface CsiLogAuditDad {

	/**
	 * Metodo per tracciare nel database che viene fatto un'operazione di inserimento in una tabella contenente dati sensibili
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato l'inserimento
	 * @param tabella e' la tabella in cui viene fatto l'inserimento
	 * @param oggetto e' un identificativo che permette di riconoscere l'oggetto che viene inserito
	 */
	void insert(PrincipalDto dto, String tabella, String oggetto,String descrizione);

	/**
	 * Metodo per tracciare nel database che viene fatto un'operazione di aggiornamento in una tabella contenente dati sensibili
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato la richiesta
	 * @param tabella e' la tabella in cui viene fatto aggiornamento
	 * @param oggetto e' un identificativo che permette di riconoscere l'oggetto che viene aggiornato (solitamente la primary key)
	 */
	void update(PrincipalDto dto, String tabella, String oggetto,String descrizione);
	
	/**
	 * Metodo per tracciare nel database che viene fatto un'operazione di eliminazione in una tabella contenente dati sensibili
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato la richiesta
	 * @param tabella e' la tabella in cui viene fatta l'eliminazione
	 * @param oggetto e' un identificativo che permette di riconoscere l'oggetto che viene eliminato
	 */
	void delete(PrincipalDto dto, String tabella, String oggetto,String descrizione);
	
	/**
	 * Metodo per tracciare nel database che viene fatto un'operazione di estrazione/ricerca in una tabella contenente dati sensibili
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato la richiesta
	 * @param tabella e' la tabella in cui viene fatto l'estrazione
	 * @param oggetto e' un identificativo che permette di riconoscere l'oggetto che viene estratto (solitamente la primary key)
	 */
	void extract(PrincipalDto dto, String tabella, String oggetto,String descrizione);
	
	/**
	 * Metodo per tracciare nel database che e' stato fatto un accesso da un utente consentito
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato l'accesso
	 * @param tabella e' la tabella in cui viene fatto l'accesso
	 * @param oggetto e' un identificativo che permette di riconoscere chi ha effettuato l'accesso
	 */
	void access(PrincipalDto dto, String tabella, String oggetto,String descrizione);
	
	/**
	 * Metodo per tracciare nel database che e' stato fatto un accesso non consentito
	 * @param dto e' il dto che contiene il codice utente e l'indirizzo ip di chi ha effettuato la richiesta
	 * @param tabella e' la tabella in cui viene fatto l'accesso
	 * @param oggetto e' un identificativo che permette di riconoscere chi ha tentato l'accesso
	 */
	void accessDenied(PrincipalDto dto, String tabella, String oggetto,String descrizione);
	
	/**
	 * Metodo per tracciare nel database un'operazione con accesso a dati sensibili.
	 */
	
	 void logAuditOperation(CsiLogAuditOperationEnum operation, PrincipalDto principalDto, String tabella, String oggetto, String descrizione);
}
