/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;


/**
 * The persistent class for the cbl_t_codice_versamento_temp database table.
 * 
 */
@Entity
@Table(name="cbl_t_codice_versamento_temp")
@NamedQuery(name="CblTCodiceVersamentoTemp.findAll", query="SELECT c FROM CblTCodiceVersamentoTemp c")
public class CblTCodiceVersamentoTemp extends DatiTecniciParentEntity implements Serializable, Comparable<CblTCodiceVersamento> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CBL_T_CODICE_VERSAMENTO_TEMP_ID_GENERATOR", sequenceName="CBL_T_CODICE_VERSAMENTO_TEMP_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CBL_T_CODICE_VERSAMENTO_TEMP_ID_GENERATOR")
	private Long id;

	private String azione;

	@Column(name="codice_fiscale_ente")
	private String codiceFiscaleEnte;

	@Column(name="codice_versamento")
	private String codiceVersamento;

	@Column(name="data_fine_validita")
	private Timestamp dataFineValidita;

	@Column(name="data_inizio_validita")
	private Timestamp dataInizioValidita;

	@Column(name="descrizione_versamento")
	private String descrizioneVersamento;

	@Column(name="flag_annullato")
	private Boolean flagAnnullato;

	@Column(name="id_ente")
	private String idEnte;

	@Column(name="id_operazione")
	private String idOperazione;

    @Column(name="codice_modalita_integrazione")
    private String codiceModalitaIntegrazione;	
    
    @Column ( name = "fattura" )
    private Boolean fattura;
	
	public CblTCodiceVersamentoTemp() {
	}

	@Override
    public int compareTo ( CblTCodiceVersamento o ) {
        return new CompareToBuilder ().append ( codiceVersamento, o.getCodiceVersamento () ).append ( idEnte, o.getIdEnte () ).toComparison ();
    }

	public String getAzione() {
		return this.azione;
	}

	public String getCodiceFiscaleEnte() {
		return this.codiceFiscaleEnte;
	}

	public String getCodiceVersamento() {
		return this.codiceVersamento;
	}

	public Timestamp getDataFineValidita() {
		return this.dataFineValidita;
	}

	public Timestamp getDataInizioValidita() {
		return this.dataInizioValidita;
	}

	@Override
    public Timestamp getDataInserimento() {
		return this.dataInserimento;
	}

	@Override
    public Timestamp getDataModifica() {
		return this.dataModifica;
	}

	public String getDescrizioneVersamento() {
		return this.descrizioneVersamento;
	}

	public Boolean getFlagAnnullato() {
		return this.flagAnnullato;
	}

	public Long getId() {
		return this.id;
	}

	public String getIdEnte() {
		return this.idEnte;
	}

	public String getIdOperazione() {
		return this.idOperazione;
	}

	@Override
    public String getPrimaryKeyRepresentation () {
        if ( id == null ) {
            return "null";
        } else {
            return id.toString ();
        }
    }

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public void setDataInizioValidita(Timestamp dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public void setDescrizioneVersamento(String descrizioneVersamento) {
		this.descrizioneVersamento = descrizioneVersamento;
	}

	public void setFlagAnnullato(Boolean flagAnnullato) {
		this.flagAnnullato = flagAnnullato;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
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

    public Boolean getFattura () {
        return fattura;
    }

    
    public void setFattura ( Boolean fattura ) {
        this.fattura = fattura;
    }
    
}
