/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cbl_t_storico_flusso_origine database table.
 *
 */
@Entity
@Table ( name = "cbl_t_storico_flusso_origine", schema = "epaymodric" )
@NamedQuery ( name = "CblTStoricoFlussoOrigine.findAll", query = "SELECT c FROM CblTStoricoFlussoOrigine c" )
public class CblTStoricoFlussoOrigine extends DatiTecniciParentEntity implements Serializable {

    /* necessaria per i metodi di audit */
    @Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "CBL_T_STORICO_FLUSSO_ORIGINE_ID_GENERATOR", sequenceName = "epaymodric.CBL_T_STORICO_FLUSSO_ORIGINE_ID_SEQ",
    schema = "epaymodric", allocationSize = 1 )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CBL_T_STORICO_FLUSSO_ORIGINE_ID_GENERATOR" )
    private Long id;

    @Column ( name = "contatore_tentativi" )
    private Integer contatoreTentativi;

    @Column ( name = "dataora_flusso" )
    private Timestamp dataoraFlusso;

    @Column ( name = "id_elaborazione" )
    private Long idElaborazione;

    @Column ( name = "id_istituto_ricevente" )
    private Long idIstitutoRicevente;

    @Column ( name = "id_stato_flusso" )
    private Long idStatoFlusso;

    @Column ( name = "identificativo_flusso" )
    private String identificativoFlusso;

    @Column ( name = "identificativo_istituto_ricevente" )
    private String identificativoIstitutoRicevente;

    @Column ( name = "identificativo_psp" )
    private String identificativoPsp;

    @Column ( name = "importo_totale_pagamenti" )
    private BigDecimal importoTotalePagamenti;

    @Column ( name = "importo_totale_pagamenti_intermediati" )
    private BigDecimal importoTotalePagamentiIntermediati;

    @Column ( name = "numero_totale_pagamenti" )
    private Integer numeroTotalePagamenti;

    @Column ( name = "numero_totale_pagamenti_intermediati" )
    private Integer numeroTotalePagamentiIntermediati;

    @Column ( name = "xml_flusso" )
    private String xmlFlusso;

    @Column ( name = "data_regolamento" )
    private Date dataRegolamento;

    @Column ( name = "identificativo_univoco_regolamento" )
    private String identificativoUnivocoRegolamento;

    //bi-directional many-to-one association to CblDTipoAcquisizione
    @ManyToOne
    @JoinColumn ( name = "tipo_acquisizione" )
    private CblDTipoAcquisizione cblDTipoAcquisizione;

    public CblTStoricoFlussoOrigine () {
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getContatoreTentativi () {
        return this.contatoreTentativi;
    }

    public void setContatoreTentativi ( Integer contatoreTentativi ) {
        this.contatoreTentativi = contatoreTentativi;
    }

    @Override
    public Timestamp getDataInserimento () {
        return this.dataInserimento;
    }

    @Override
    public void setDataInserimento ( Timestamp dataInserimento ) {
        this.dataInserimento = dataInserimento;
    }

    @Override
    public Timestamp getDataModifica () {
        return this.dataModifica;
    }

    @Override
    public void setDataModifica ( Timestamp dataModifica ) {
        this.dataModifica = dataModifica;
    }

    public Timestamp getDataoraFlusso () {
        return this.dataoraFlusso;
    }

    public void setDataoraFlusso ( Timestamp dataoraFlusso ) {
        this.dataoraFlusso = dataoraFlusso;
    }

    public Long getIdElaborazione () {
        return this.idElaborazione;
    }

    public void setIdElaborazione ( Long idElaborazione ) {
        this.idElaborazione = idElaborazione;
    }

    public Long getIdIstitutoRicevente () {
        return this.idIstitutoRicevente;
    }

    public void setIdIstitutoRicevente ( Long idIstitutoRicevente ) {
        this.idIstitutoRicevente = idIstitutoRicevente;
    }

    public Long getIdStatoFlusso () {
        return this.idStatoFlusso;
    }

    public void setIdStatoFlusso ( Long idStatoFlusso ) {
        this.idStatoFlusso = idStatoFlusso;
    }

    public String getIdentificativoFlusso () {
        return this.identificativoFlusso;
    }

    public void setIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
    }

    public String getIdentificativoIstitutoRicevente () {
        return this.identificativoIstitutoRicevente;
    }

    public void setIdentificativoIstitutoRicevente ( String identificativoIstitutoRicevente ) {
        this.identificativoIstitutoRicevente = identificativoIstitutoRicevente;
    }

    public String getIdentificativoPsp () {
        return this.identificativoPsp;
    }

    public void setIdentificativoPsp ( String identificativoPsp ) {
        this.identificativoPsp = identificativoPsp;
    }

    public BigDecimal getImportoTotalePagamenti () {
        return this.importoTotalePagamenti;
    }

    public void setImportoTotalePagamenti ( BigDecimal importoTotalePagamenti ) {
        this.importoTotalePagamenti = importoTotalePagamenti;
    }

    public BigDecimal getImportoTotalePagamentiIntermediati () {
        return this.importoTotalePagamentiIntermediati;
    }

    public void setImportoTotalePagamentiIntermediati ( BigDecimal importoTotalePagamentiIntermediati ) {
        this.importoTotalePagamentiIntermediati = importoTotalePagamentiIntermediati;
    }

    public Integer getNumeroTotalePagamenti () {
        return this.numeroTotalePagamenti;
    }

    public void setNumeroTotalePagamenti ( Integer numeroTotalePagamenti ) {
        this.numeroTotalePagamenti = numeroTotalePagamenti;
    }

    public Integer getNumeroTotalePagamentiIntermediati () {
        return this.numeroTotalePagamentiIntermediati;
    }

    public void setNumeroTotalePagamentiIntermediati ( Integer numeroTotalePagamentiIntermediati ) {
        this.numeroTotalePagamentiIntermediati = numeroTotalePagamentiIntermediati;
    }

    @Override
    public String getUtenteInserimento () {
        return this.utenteInserimento;
    }

    @Override
    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    @Override
    public String getUtenteModifica () {
        return this.utenteModifica;
    }

    @Override
    public void setUtenteModifica ( String utenteModifica ) {
        this.utenteModifica = utenteModifica;
    }

    public String getXmlFlusso () {
        return this.xmlFlusso;
    }

    public void setXmlFlusso ( String xmlFlusso ) {
        this.xmlFlusso = xmlFlusso;
    }

    public Date getDataRegolamento () {
        return dataRegolamento;
    }

    public void setDataRegolamento ( Date dataRegolamento ) {
        this.dataRegolamento = dataRegolamento;
    }

    public String getIdentificativoUnivocoRegolamento () {
        return identificativoUnivocoRegolamento;
    }

    public void setIdentificativoUnivocoRegolamento ( String identificativoUnivocoRegolamento ) {
        this.identificativoUnivocoRegolamento = identificativoUnivocoRegolamento;
    }

    public CblDTipoAcquisizione getCblDTipoAcquisizione () {
        return this.cblDTipoAcquisizione;
    }

    public void setCblDTipoAcquisizione ( CblDTipoAcquisizione cblDTipoAcquisizione ) {
        this.cblDTipoAcquisizione = cblDTipoAcquisizione;
    }
}
