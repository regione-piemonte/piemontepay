/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaRiferimentoContabileInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long idCodiceVersamento;

    private String descrizioneCodiceVersamento;

    private String codiceVoceEntrata;

    private String codiceMacrotipo;

    private String codiceTematica;

    private Boolean soloRiferimentiInVita;
    
    private Integer annoEsercizio;

    @Override
    public String toString () {
        return "RicercaRiferimentoContabileInput [idCodiceVersamento=" + idCodiceVersamento + ", descrizioneCodiceVersamento=" + descrizioneCodiceVersamento
            + ", codiceVoceEntrata=" + codiceVoceEntrata + ", codiceMacrotipo=" + codiceMacrotipo + ", codiceTematica=" + codiceTematica
            + ", soloRiferimentiInVita=" + soloRiferimentiInVita 
            + ", annoEsercizio=" + annoEsercizio 
            + "]";
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getDescrizioneCodiceVersamento () {
        return descrizioneCodiceVersamento;
    }

    public void setDescrizioneCodiceVersamento ( String descrizioneCodiceVersamento ) {
        this.descrizioneCodiceVersamento = descrizioneCodiceVersamento;
    }

    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public Boolean getSoloRiferimentiInVita () {
        return soloRiferimentiInVita;
    }

    public void setSoloRiferimentiInVita ( Boolean soloRiferimentiInVita ) {
        this.soloRiferimentiInVita = soloRiferimentiInVita;
    }

	public Integer getAnnoEsercizio() {
		return annoEsercizio;
	}

	public void setAnnoEsercizio(Integer annoEsercizio) {
		this.annoEsercizio = annoEsercizio;
	}

	
    

}
