/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import it.csi.mdp.mdpboweb.dto.nsbackoffice.Applicazione;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Gateway;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.PaymentMode;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Utente;
import it.csi.mdp.mdpboweb.dto.nsbackoffice.Azione;

import java.util.Comparator;

public class Comparators {

	public static Comparator<Applicazione> APPLICAZIONE_COMPARATOR = new Comparator<Applicazione>() {
		public int compare(Applicazione a1, Applicazione a2) {
			return a1.getIdApplicazione().compareTo(
					a2.getIdApplicazione());
		}
	};
	
	public static Comparator<Applicazione> APPLICAZIONE_BY_DESCR_COMPARATOR = new Comparator<Applicazione>() {
		public int compare(Applicazione a1, Applicazione a2) {
			return a1.getNomeApplicazione().compareTo(
					a2.getNomeApplicazione());
		}
	};

	public static Comparator<Gateway> GATEWAY_COMPARATOR = new Comparator<Gateway>() {
		public int compare(Gateway a1, Gateway a2) {
			System.out.println("null???  " + a1.getDescrGateway());
			return a1.getDescrGateway().compareTo(
					a2.getDescrGateway());
		}
	};

	public static Comparator<Utente> USER_COMPARATOR = new Comparator<Utente>() {
		public int compare(Utente a1, Utente a2) {
			return a1.getDescrUtente().compareTo(
					a2.getDescrUtente());
		}
	};
	
	public static Comparator<PaymentMode> PM_COMPARATOR = new Comparator<PaymentMode>() {
		public int compare(PaymentMode a1, PaymentMode a2) {
			return a1.getDescrPayment().compareTo(
					a2.getDescrPayment());
		}
	};

	public static Comparator<Azione> AZIONE_COMPARATOR = new Comparator<Azione>() {
		public int compare(Azione a1, Azione a2) {
			return a1.getDescrAzione().compareTo(
					a2.getDescrAzione());
		}
	};
}
