/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.epay.epayservices.model.ComponentePagamentoEnteSecondaroInput;
import it.csi.epay.epayservices.model.GetIuvCommonChiamanteEsternoInput;


/**
 *
 */
@SuppressWarnings ( "unused" )
public class GetIuvMultibeneficiarioChiamanteEsternoInput extends GetIuvCommonChiamanteEsternoInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoPagamento;

	@JsonProperty ( "importoTotaleEntePrimario" )
	private BigDecimal importoTotaleEntePrimario;

	@JsonProperty ( "importoTotaleEntiSecondari" )
	private BigDecimal importoTotaleEntiSecondari;

	private BigDecimal importoTotale;

	private Integer numeroPosizioniDebitorie;

	@JsonProperty ( "componentiPagamentoEntiSecondari" )
	private List<ComponentePagamentoEnteSecondaroInput> componentiPagamentoEntiSecondari;

	@JsonProperty ( "componentiPagamentoEntePrimario" )
	private List<it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput> componentiPagamentoEntePrimario;

	

	public BigDecimal getImportoTotale () {
		return importoTotale;
	}

	public void setImportoTotale ( BigDecimal importoTotale ) {
		this.importoTotale = importoTotale;
	}

	public List<ComponentePagamentoEnteSecondaroInput> getComponentiPagamentoEntiSecondari () {
		return componentiPagamentoEntiSecondari;
	}

	public void setComponentiPagamentoEntiSecondari (
					List<ComponentePagamentoEnteSecondaroInput> componentiPagamentoEntiSecondari ) {
		this.componentiPagamentoEntiSecondari = componentiPagamentoEntiSecondari;
	}

	/**
	 * @return the numeroPosizioniDebitorie
	 */
	public Integer getNumeroPosizioniDebitorie () {
		return numeroPosizioniDebitorie;
	}

	/**
	 * @param numeroPosizioniDebitorie the numeroPosizioniDebitorie to set
	 */
	public void setNumeroPosizioniDebitorie ( Integer numeroPosizioniDebitorie ) {
		this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
	}

	
	/**
	 * @return the tipoPagamento
	 */
	public String getTipoPagamento () {
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento the tipoPagamento to set
	 */
	public void setTipoPagamento ( String tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

    
    public BigDecimal getImportoTotaleEntePrimario () {
        return importoTotaleEntePrimario;
    }

    
    public void setImportoTotaleEntePrimario ( BigDecimal importoTotaleEntePrimario ) {
        this.importoTotaleEntePrimario = importoTotaleEntePrimario;
    }

    
    public BigDecimal getImportoTotaleEntiSecondari () {
        return importoTotaleEntiSecondari;
    }

    
    public void setImportoTotaleEntiSecondari ( BigDecimal importoTotaleEntiSecondari ) {
        this.importoTotaleEntiSecondari = importoTotaleEntiSecondari;
    }

    
    public List<it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput> getComponentiPagamentoEntePrimario () {
        return componentiPagamentoEntePrimario;
    }

    
    public void setComponentiPagamentoEntePrimario (
        List<it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoComponentePagamentoInput> componentiPagamentoEntePrimario ) {
        this.componentiPagamentoEntePrimario = componentiPagamentoEntePrimario;
    }

    
  
	

}
