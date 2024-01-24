/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Gueye
 *
 */
@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoOutputRicercaElaborazionePrecedente" )
public class DTOOutputRicercaElaborazionePrecedente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
      
		private Long id;
		
		private Date dataElaborazione;

		
		private Date dataFine;

	
		private Date dataInizio;

	
		private String idEnte;
		
		private Long idErrore;
		
		private String msgErrore;
	
		
		private String utenteIns; 

		private String statoElaborazione;

		private String listaFlussi;
		
		private DTOOutputWsEsito esito;

		public Date getDataElaborazione() {
			return dataElaborazione;
		}


		public void setDataElaborazione(Date dataElaborazione) {
			this.dataElaborazione = dataElaborazione;
		}


		public Date getDataFine() {
			return dataFine;
		}


		public void setDataFine(Date dataFine) {
			this.dataFine = dataFine;
		}


		public Date getDataInizio() {
			return dataInizio;
		}


		public void setDataInizio(Date dataInizio) {
			this.dataInizio = dataInizio;
		}


		public String getIdEnte() {
			return idEnte;
		}


		public void setIdEnte(String idEnte) {
			this.idEnte = idEnte;
		}

		public String getListaFlussi() {
			return listaFlussi;
		}


		public void setListaFlussi(String string) {
			this.listaFlussi = string;
		}

		

		public String getMsgErrore() {
			return msgErrore;
		}


		public void setMsgErrore(String msgErrore) {
			this.msgErrore = msgErrore;
		}


		public String getUtenteIns() {
			return utenteIns;
		}


		public void setUtenteIns(String utenteIns) {
			this.utenteIns = utenteIns;
		}


		public String getStatoElaborazione() {
			return statoElaborazione;
		}


		public void setStatoElaborazione(String statoElaborazione) {
			this.statoElaborazione = statoElaborazione;
		}
		

		public DTOOutputWsEsito getEsito() {
			return esito;
		}


		public void setEsito(DTOOutputWsEsito esito) {
			this.esito = esito;
		}


		public Long getIdErrore() {
			return idErrore;
		}


		public void setIdErrore(Long idErrore) {
			this.idErrore = idErrore;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}
		
}
