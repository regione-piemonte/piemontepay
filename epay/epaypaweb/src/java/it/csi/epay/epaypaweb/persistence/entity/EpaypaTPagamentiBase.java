/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epaypa_t_pagamenti database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_pagamenti" )
public class EpaypaTPagamentiBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "iuv" )
    private String iuv;

    @Column ( name = "data_pagamento" )
    private Timestamp dataPagamento;

    @Column ( name = "desc_stato" )
    private String descStato;

    @Column ( name = "id_stato" )
    private Integer idStato;

    @Column ( name = "importo_pagato" )
    private BigDecimal importoPagato;

    @Column ( name = "pagamento_spontaneo" )
    private Boolean pagamentoSpontaneo;

    @Column ( name = "cod_versamento" )
    private String codVersamento;

    @Column ( name = "descr_cod_versamento" )
    private String descrCodVersamento;

    @Column ( name = "cod_fiscale_ente" )
    private String codFiscaleEnte;

    @Column ( name = "note")
    private String note;

    @Column ( name = "id_unico_fiscale")
    private String idUnicoFiscale;

    @Column ( name = "cognome_rag_soc")
    private String cognomeRagioneSociale;

    @Column ( name = "nome")
    private String nome;

    @Column ( name = "tipologia_soggetto")
    private Boolean flagPersonaFisica;

    @Column ( name = "data_scadenza" )
    @Temporal(TemporalType.DATE)
    private Date dataScadenza;

    //RDI-23
    @Column ( name = "causale" )
    private String causale;
    
    //PND 
    @Column ( name = "requires_cost_update" )
    private Boolean requiresCostUpdate;

    public EpaypaTPagamentiBase () {
    }

    /**
     * @param iuv
     */
    public EpaypaTPagamentiBase ( String iuv ) {
        super ();
        this.iuv = iuv;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public Date getDataPagamento () {
        return dataPagamento;
    }

    public void setDataPagamento ( Timestamp dataPagamento ) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescStato () {
        return descStato;
    }

    public void setDescStato ( String descStato ) {
        this.descStato = descStato;
    }

    public Integer getIdStato () {
        return idStato;
    }

    public void setIdStato ( Integer idStato ) {
        this.idStato = idStato;
    }

    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    public String getCodVersamento () {
        return codVersamento;
    }

    public void setCodVersamento ( String codVersamento ) {
        this.codVersamento = codVersamento;
    }

    public String getDescrCodVersamento () {
        return descrCodVersamento;
    }

    public void setDescrCodVersamento ( String descrCodVersamento ) {
        this.descrCodVersamento = descrCodVersamento;
    }

    public String getCodFiscaleEnte () {
        return codFiscaleEnte;
    }

    public void setCodFiscaleEnte ( String codFiscaleEnte ) {
        this.codFiscaleEnte = codFiscaleEnte;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIdUnicoFiscale() {
        return idUnicoFiscale;
    }

    public void setIdUnicoFiscale(String idUnicoFiscale) {
        this.idUnicoFiscale = idUnicoFiscale;
    }

    public String getCognomeRagioneSociale() {
        return cognomeRagioneSociale;
    }

    public void setCognome_ragione_sociale(String cognomeRagioneSociale) {
        this.cognomeRagioneSociale = cognomeRagioneSociale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getFlagPersonaFisica() {
        return flagPersonaFisica;
    }

    public void setFlagPersonaFisica(Boolean flagPersonaFisica) {
        this.flagPersonaFisica = flagPersonaFisica;
    }
    
    

    public Boolean getRequiresCostUpdate() {
		return requiresCostUpdate;
	}

	public void setRequiresCostUpdate(Boolean requiresCostUpdate) {
		this.requiresCostUpdate = requiresCostUpdate;
	}

	/**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( iuv == null ) ? 0 : iuv.hashCode () );
        return result;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass () != obj.getClass () ) {
            return false;
        }
        EpaypaTPagamentiBase other = (EpaypaTPagamentiBase) obj;
        if ( iuv == null ) {
            if ( other.iuv != null ) {
                return false;
            }
        } else if ( !iuv.equals ( other.iuv ) ) {
            return false;
        }
        return true;
    }

}
