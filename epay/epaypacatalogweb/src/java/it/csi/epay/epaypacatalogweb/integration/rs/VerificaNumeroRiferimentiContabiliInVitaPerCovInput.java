/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs;

import org.codehaus.jackson.annotate.JsonProperty;

public class VerificaNumeroRiferimentiContabiliInVitaPerCovInput extends ParentInput {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    @JsonProperty("idCodiceVersamento")
    private Long idCodiceVersamento;

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



}
