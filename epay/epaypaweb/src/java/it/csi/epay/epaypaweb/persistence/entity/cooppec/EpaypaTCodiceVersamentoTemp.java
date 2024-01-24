/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity.cooppec;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

import it.csi.epay.epaypaweb.persistence.entity.EpaypaTCodiceVersamento;


@Entity
@Table ( name = "epaypa_t_codice_versamento_temp", schema = "epaypa" )

@NamedQueries ( {

    @NamedQuery (
                    name = "EpaypaTCodiceVersamentoTemp.findAllByIdOperazione",
                    query = "SELECT cv "
                        + "FROM EpaypaTCodiceVersamentoTemp cv "
                        + "WHERE cv.idOperazione = :idOperazione" ),
    @NamedQuery (
                    name = "EpaypaTCodiceVersamentoTemp.findAll",
                    query = "SELECT e FROM EpaypaTCodiceVersamento e" )
} )

public class EpaypaTCodiceVersamentoTemp implements Serializable, Comparable<EpaypaTCodiceVersamento> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column ( name = "id_codice_versamento" )
    private Integer idCodiceVersamento;

    @Column ( name = "cod_versamento" )
    private String codVersamento;

    @Column ( name = "bollettino_postale" )
    private Boolean bollettinoPostale;

    /**
	 * @return the bollettinoPostale
	 */
	public Boolean getBollettinoPostale() {
		return bollettinoPostale;
	}

	/**
	 * @param bollettinoPostale the bollettinoPostale to set
	 */
	public void setBollettinoPostale(Boolean bollettinoPostale) {
		this.bollettinoPostale = bollettinoPostale;
	}
    
    private String descrizione;

    //@Column(name="id_ente")
    //private Integer idEnte;

    @Column ( name = "cod_fiscale_ente" )
    private String codFiscaleEnte;

    private String azione;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    @Column(name="codice_modalita_integrazione")
    private String codiceModalitaIntegrazione;

    @Column ( name = "id_tipo_pagamento" )
    private Integer idTipoPagamento;
    
    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;

    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;


    public Integer getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Integer idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public EpaypaTCodiceVersamentoTemp () {
    }

    @Override
    public int compareTo ( EpaypaTCodiceVersamento o ) {
        return new CompareToBuilder ().append ( codVersamento, o.getCodVersamento () ).append ( codFiscaleEnte, o.getEpaypaTEnte ().getCodFiscaleEnte () )
            .toComparison ();
    }

    public Integer getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getCodVersamento () {
        return codVersamento;
    }

    public void setCodVersamento ( String codVersamento ) {
        this.codVersamento = codVersamento;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getCodFiscaleEnte () {
        return codFiscaleEnte;
    }

    public void setCodFiscaleEnte ( String codFiscaleEnte ) {
        this.codFiscaleEnte = codFiscaleEnte;
    }

    public String getAzione () {
        return azione;
    }

    public void setAzione ( String azione ) {
        this.azione = azione;
    }

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

    
    public Boolean getFlagMbPrimario () {
        return flagMbPrimario;
    }

    
    public void setFlagMbPrimario ( Boolean flagMbPrimario ) {
        this.flagMbPrimario = flagMbPrimario;
    }

    
    public Boolean getFlagMbSecondario () {
        return flagMbSecondario;
    }
    
    public void setFlagMbSecondario ( Boolean flagMbSecondario ) {
        this.flagMbSecondario = flagMbSecondario;
    }
    
    
}
