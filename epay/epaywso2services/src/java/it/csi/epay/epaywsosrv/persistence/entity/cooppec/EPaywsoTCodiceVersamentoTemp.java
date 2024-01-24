/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.entity.cooppec;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;


@Entity
@Table ( name = "epaywso_t_codice_versamento_temp", schema = "epaywso" )

@NamedQueries ( {

    @NamedQuery (
                    name = "EPaywsoTCodiceVersamentoTemp.findAllByIdOperazione",
                    query = "SELECT cv "
                        + "FROM EPaywsoTCodiceVersamentoTemp cv "
                        + "WHERE cv.idOperazione = :idOperazione" ),
    @NamedQuery (
                    name = "EPaywsoTCodiceVersamentoTemp.findAll",
                    query = "SELECT e FROM EPaywsoTCodiceVersamento e" )
} )

public class EPaywsoTCodiceVersamentoTemp implements Serializable, Comparable<EPaywsoTCodiceVersamento> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAYWSO_T_CODICE_VERSAMENTO_TEMP_ID_GENERATOR", sequenceName="epaywso.epaywso_t_codice_versamento_temp_seq" ,schema="epaywso", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAYWSO_T_CODICE_VERSAMENTO_TEMP_ID_GENERATOR")
    @Column ( name = "id_codice_versamento" )
    private Integer idCodiceVersamento;

    @Column ( name = "cod_versamento" )
    private String codVersamento;

    private String descrizione;

    @Column ( name = "cod_fiscale_ente" )
    private String codFiscaleEnte;

    @Column ( name = "dt_fine_validita" )
    private Timestamp dtFineValidita;

    @Column ( name = "dt_inizio_validita" )
    private Timestamp dtInizioValidita;

    private String azione;

    @Column ( name = "id_operazione" )
    private String idOperazione;

    @Column ( name = "codice_modalita_integrazione" )
    private String codiceModalitaIntegrazione;

    public EPaywsoTCodiceVersamentoTemp () {
    }

    @Override
    public int compareTo ( EPaywsoTCodiceVersamento o ) {
        return new CompareToBuilder ().append ( codVersamento, o.getCodVersamento () )
            .append ( codFiscaleEnte, o.getEpaywsoTApplicativo ().getEPaywsoTEnte ().getCodFiscaleEnte () ).toComparison ();
    }

    public Integer getIdCodiceVersamento () {
        return this.idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getCodVersamento () {
        return this.codVersamento;
    }

    public void setCodVersamento ( String codVersamento ) {
        this.codVersamento = codVersamento;
    }

    public String getDescrizione () {
        return this.descrizione;
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

    public Timestamp getDtFineValidita () {
        return this.dtFineValidita;
    }

    public void setDtFineValidita ( Timestamp dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }

    public Timestamp getDtInizioValidita () {
        return this.dtInizioValidita;
    }

    public void setDtInizioValidita ( Timestamp dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
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

}
