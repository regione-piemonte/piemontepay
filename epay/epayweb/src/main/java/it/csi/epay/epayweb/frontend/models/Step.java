/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.util.ArrayList;
import java.util.List;

public class Step {
	public enum Percorsi {
        PAGA_FREE_CONIUV    (6, "Riferimenti (1/2)", "Riferimenti (2/2)", "Dati personali", "Riepilogo", "Pagamento", "Conclusione" ),
		PAGA_FREE_NOIUV		(5, "Riferimenti", "Dati personali", "Riepilogo", "Pagamento", "Conclusione"),
		PAGA_AUTH_CONIUV	(4, "Riferimenti", "Riepilogo e note", "Pagamento", "Conclusione"),
		PAGA_AUTH_CONIUV_RF (6, "Riferimenti (1/2)", "Riferimenti (2/2)", "Dati personali", "Riepilogo", "Pagamento", "Conclusione" ),
        PAGA_AUTH_NOIUV     (5, "Riferimenti", "Dati personali", "Riepilogo", "Pagamento", "Conclusione" ),
		PAGA_AUTH_CONIUV_3	(5, "Riferimenti", "Dati personali", "Riepilogo", "Pagamento", "Conclusione"),
		PAGA_AUTH_NOIUV_3	(5, "Riferimenti", "Dati personali", "Riepilogo", "Pagamento", "Conclusione"),
		VERIFICA_FREE		(2, "Riferimenti", "Verifica"),
		VERIFICA_AUTH		(1, "Verifica"),
		STAMPA_FREE			(3, "Riferimenti", "Dati personali", "Riepilogo e stampa"),
		STAMPA_AUTH			(3, "Riferimenti", "Dati personali", "Riepilogo e stampa");
		
		private int numero;
		private List<String> descrizione;
		
		private Percorsi(int numero, String ... descrizione ) {
			this.numero = numero;
			this.descrizione = new ArrayList<String>(numero);
			for(String desc : descrizione) {
				this.descrizione.add(desc);
		   }
		}
		
		public int getNumero() {
			return numero;
		}

		public String getDescrizione(int pos) {
			return descrizione.get(--pos);
		}
	}  
	
	private Percorsi percorso;
	private int attuale;
	
	public Step(Percorsi percorso, int attuale) {
		this.percorso = percorso;
		this.attuale = attuale;
	}
	
	public int getAttuale() {
		return attuale;
	}
	
	public int getNumero() {
		return percorso.getNumero();
	}
	
	public String getDescrizione() {
		return getDescrizione(attuale);		
	}
	
	public String getDescrizione(int step) {
		if (step > percorso.getNumero()) {
			return ""; 
		}
		return percorso.getDescrizione(step);		
	}
	
	public String getStato(int step) {
		if (step == attuale) return "active" ;
		if (step < attuale) return "past" ;
		if (step > attuale) return "next" ;
		return null;
	}	
}
