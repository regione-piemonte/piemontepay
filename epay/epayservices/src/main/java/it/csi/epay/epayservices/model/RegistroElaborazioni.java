/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RegistroElaborazioni implements Serializable {
	private static final long serialVersionUID = -1626753802038316626L;
		
	private Long id;
	private Timestamp dataFine;
	private Timestamp dataInizio;
	private String esito;
	private String idMessaggio;
	private BigDecimal importoTotPagamenti;
	private String messageFault;
	private Integer numPagamenti;
	private String operazione;
	private Boolean pagamentiSpontanei;
	private List<Pagamento> pagamenti;
	private Long idEnte;
	private Long idTipoPagamento;
	private List<RegistroElaborazioniFault> faults;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the dataFine
	 */
	public Timestamp getDataFine() {
		return dataFine;
	}
	/**
	 * @param dataFine the dataFine to set
	 */
	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}
	/**
	 * @return the dataInizio
	 */
	public Timestamp getDataInizio() {
		return dataInizio;
	}
	/**
	 * @param dataInizio the dataInizio to set
	 */
	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}
	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}
	/**
	 * @param esito the esito to set
	 */
	public void setEsito(String esito) {
		this.esito = esito;
	}
	/**
	 * @return the idMessaggio
	 */
	public String getIdMessaggio() {
		return idMessaggio;
	}
	/**
	 * @param idMessaggio the idMessaggio to set
	 */
	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}
	/**
	 * @return the importoTotPagamenti
	 */
	public BigDecimal getImportoTotPagamenti() {
		return importoTotPagamenti;
	}
	/**
	 * @param importoTotPagamenti the importoTotPagamenti to set
	 */
	public void setImportoTotPagamenti(BigDecimal importoTotPagamenti) {
		this.importoTotPagamenti = importoTotPagamenti;
	}
	/**
	 * @return the messageFault
	 */
	public String getMessageFault() {
		return messageFault;
	}
	/**
	 * @param messageFault the messageFault to set
	 */
	public void setMessageFault(String messageFault) {
		this.messageFault = messageFault;
	}
	/**
	 * @return the numPagamenti
	 */
	public Integer getNumPagamenti() {
		return numPagamenti;
	}
	/**
	 * @param numPagamenti the numPagamenti to set
	 */
	public void setNumPagamenti(Integer numPagamenti) {
		this.numPagamenti = numPagamenti;
	}
	/**
	 * @return the operazione
	 */
	public String getOperazione() {
		return operazione;
	}
	/**
	 * @param operazione the operazione to set
	 */
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	/**
	 * @return the pagamentiSpontanei
	 */
	public Boolean getPagamentiSpontanei() {
		return pagamentiSpontanei;
	}
	/**
	 * @param pagamentiSpontanei the pagamentiSpontanei to set
	 */
	public void setPagamentiSpontanei(Boolean pagamentiSpontanei) {
		this.pagamentiSpontanei = pagamentiSpontanei;
	}
	/**
	 * @return the pagamenti
	 */
	public List<Pagamento> getPagamenti() {
		if (pagamenti == null) {
			pagamenti = new ArrayList<Pagamento>();
		}
		return pagamenti;
	}
	/**
	 * @param pagamenti the pagamenti to set
	 */
	public void setPagamenti(List<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}
	/**
	 * @return the idEnte
	 */
	public Long getIdEnte() {
		return idEnte;
	}
	/**
	 * @param idEnte the idEnte to set
	 */
	public void setIdEnte(Long idEnte) {
		this.idEnte = idEnte;
	}
	/**
	 * @return the idTipoPagamento
	 */
	public Long getIdTipoPagamento() {
		return idTipoPagamento;
	}
	/**
	 * @param idTipoPagamento the idTipoPagamento to set
	 */
	public void setIdTipoPagamento(Long idTipoPagamento) {
		this.idTipoPagamento = idTipoPagamento;
	}
	/**
	 * @return the faults
	 */
	public List<RegistroElaborazioniFault> getFaults() {
		if (faults == null) {
			faults = new ArrayList<RegistroElaborazioniFault>();
		}
		return faults;
	}
	/**
	 * @param faults the faults to set
	 */
	public void setFaults(List<RegistroElaborazioniFault> faults) {
		this.faults = faults;
	}
	
}


