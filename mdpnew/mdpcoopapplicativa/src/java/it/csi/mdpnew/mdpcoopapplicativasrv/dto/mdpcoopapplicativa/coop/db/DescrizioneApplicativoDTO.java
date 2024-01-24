/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.db;

/**
 * 
 * @author Paolo 
 *
 */
public class DescrizioneApplicativoDTO extends AbstractDTO{
	
	private String partitaIva;
	private String descrizione;
	private String idApplication;
	private String applicationName;
	private String referenteCsi;
	private String cliente;
	private String idEnte;
	private String codiceSegregazione;
	
	
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getIdApplication() {
		return idApplication;
	}
	public void setIdApplication(String idApplication) {
		this.idApplication = idApplication;
	}
	public String getReferenteCsi() {
		return referenteCsi;
	}
	public void setReferenteCsi(String referenteCsi) {
		this.referenteCsi = referenteCsi;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getIdEnte() {
		return idEnte;
	}
	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}
	public String getCodiceSegregazione() {
		return codiceSegregazione;
	}
	public void setCodiceSegregazione(String codiceSegregazione) {
		this.codiceSegregazione = codiceSegregazione;
	}
	

}
