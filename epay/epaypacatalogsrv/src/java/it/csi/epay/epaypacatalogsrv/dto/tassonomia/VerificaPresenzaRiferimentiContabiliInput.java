/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;

import java.util.Date;



public class VerificaPresenzaRiferimentiContabiliInput extends ParentInput {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long idCodiceVersamento;

    
    private Integer annoEsercizio;

    
    private Boolean generico;
    
    
    private Date dataInizioValidita;
    
    
    private Date dataFineValidita;
    
    
    private Long idRiferimentoContabile;

	/**
	 * @return the idCodiceVersamento
	 */
	public Long getIdCodiceVersamento() {
		return idCodiceVersamento;
	}


	/**
	 * @param idCodiceVersamento the idCodiceVersamento to set
	 */
	public void setIdCodiceVersamento(Long idCodiceVersamento) {
		this.idCodiceVersamento = idCodiceVersamento;
	}


	/**
	 * @return the annoEsercizio
	 */
	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}


	/**
	 * @param annoEsercizio the annoEsercizio to set
	 */
	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}


	/**
	 * @return the isGenerico
	 */
	public Boolean isGenerico() {
		return generico;
	}


	/**
	 * @param generico the isGenerico to set
	 */
	public void setGenerico(Boolean generico) {
		this.generico = generico;
	}


    
    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }


    
    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }


    
    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }


    
    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }


    
    /**
     * @return the idRiferimentoContabile
     */
    public Long getIdRiferimentoContabile () {
        return idRiferimentoContabile;
    }


    
    /**
     * @param idRiferimentoContabile the idRiferimentoContabile to set
     */
    public void setIdRiferimentoContabile ( Long idRiferimentoContabile ) {
        this.idRiferimentoContabile = idRiferimentoContabile;
    }
    
    
    
    

}
