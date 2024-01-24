/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the epaypa_t_codice_versamento database table.
 *
 */
@Entity
@Table ( schema = "epaypa", name = "epaypa_t_codice_versamento" )
@NamedQuery ( name = "EpaypaTCodiceVersamento.findAll", query = "SELECT e FROM EpaypaTCodiceVersamento e" )
public class EpaypaTCodiceVersamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id_codice_versamento" )
    private Integer idCodiceVersamento;

    @Column ( name = "cod_versamento" )
    private String codVersamento;

    private String descrizione;

    @Column ( name = "id_ente" )
    private Integer idEnte;

    @Column ( name = "codice_modalita_integrazione" )
    private String codiceModalitaIntegrazione;
        
    @Transient
    private Integer idEnteCatalog;

	@Column ( name = "flag_mb_primario" )
	private Boolean flagMbPrimario;

	@Column ( name = "flag_mb_secondario" )
	private Boolean flagMbSecondario;
	
//	@Column ( name = "flag_personalizzazione_cov", nullable = false )
//    private Boolean flagPersonalizzazioneCov;
//    
//    @Column ( name = "flag_invio_notificatore", nullable = false )
//    private Boolean flagInvioNotificatore;
//    
//    @Column(name="passphrase", nullable=false)
//    private byte[] passphrase;
//    
//    @Column ( name = "descrizione_text_cov", nullable = true, length = 250 )
//    private String descrizioneTextCov;
//	
	

    public EpaypaTCodiceVersamento () {
    }

    public Integer getIdEnteCatalog () {
        return idEnteCatalog;
    }

    public void setIdEnteCatalog ( Integer idEnteCatalog ) {
        this.idEnteCatalog = idEnteCatalog;
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

    public Integer getIdEnte () {
        return idEnte;
    }

    public void setIdEnte ( Integer idEnte ) {
        this.idEnte = idEnte;
    }


    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

    
//    public Boolean getFlagPersonalizzazioneCov () {
//        return flagPersonalizzazioneCov;
//    }
//
//    
//    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
//        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
//    }
//
//    
//    public Boolean getFlagInvioNotificatore () {
//        return flagInvioNotificatore;
//    }
//
//    
//    public void setFlagInvioNotificatore ( Boolean flagInvioNotificatore ) {
//        this.flagInvioNotificatore = flagInvioNotificatore;
//    }
//
//    
//    public byte [] getPassphrase () {
//        return passphrase;
//    }
//
//    
//    public void setPassphrase ( byte [] passphrase ) {
//        this.passphrase = passphrase;
//    }
//
//    
//    public String getDescrizioneTextCov () {
//        return descrizioneTextCov;
//    }
//
//    
//    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
//        this.descrizioneTextCov = descrizioneTextCov;
//    }
//	
	
}
