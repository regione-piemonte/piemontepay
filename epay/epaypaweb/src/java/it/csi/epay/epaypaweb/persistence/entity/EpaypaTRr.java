/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

@Entity
@Table(name="epaypa_t_RR")
@NamedQueries({
    @NamedQuery(//<!-- CSI_PAG-184 -->
        name = "EpaypaTRr.findRevocaByIdRr",
        query ="select p "+
                        "FROM  EpaypaTRr p"+ 
                        " where p.idRr = :idRr"
                    ),
    @NamedQuery(
        name = "EpaypaTRr.findAll",
        query = "select p FROM  EpaypaTRr p, EpaypaTTipoRevoca t "+
                        "where p.denominazioneIstitutoAttestante = :denominazione_istituto_attestante "+
                        "or p.iuv = :iuv "+
                        "or t.id = :tipo_revoca "+
                        "or (p.dataOraMessaggioRevoca between :data_ora_messaggio_revocaDa and :data_ora_messaggio_revocaAl) "+
                        "or p.identificativoDominio = :identificativo_dominio "
                    )

})
public class EpaypaTRr {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_rr")
    private Long idRr;
    @Column(name="identificativo_dominio")
    private String identificativoDominio;
    @Column(name="application_id")
    private String applicationId;
    @Column(name="identificativo_messaggio_revoca")
    private String identificativoMessaggioRevoca;
    @Column(name="data_ora_messaggio_revoca")
    private Timestamp dataOraMessaggioRevoca;
    @Column(name="codice_identificativo_univoco_attestante")
    private String codiceIdentificativoUnivocoAttestante;
    @Column(name="denominazione_istituto_attestante")
    private String denominazioneIstitutoAttestante;
    @Column(name="importo_totale_revocato")
    private BigDecimal importoTotaleTevocato;
    private String iuv;
    @Column(name="codice_contesto_pagamento")
    private String codiceContestoPagamento;
    @Column(name="xml_rr")
    private byte[] xmlRr;


    @Column(name="tipo_revoca")
    private Integer tipoRevoca;
    
    @Transient
    private String nomeCognome;
    @Transient
    private String codiceFiscale;

    //bi-directional one-to-one association to EpaypaTTipoRevoca
//    @OneToOne( cascade=CascadeType.ALL)
//    @JoinColumn(name="tipo_revoca", updatable=false, insertable=false, nullable=false)
//    private EpaypaTTipoRevoca epaypaTTipoRevoca;


    
//    public EpaypaTTipoRevoca getEpaypaTTipoRevoca () {
//        return epaypaTTipoRevoca;
//    }
//
//
//
//    
//    public void setEpaypaTTipoRevoca ( EpaypaTTipoRevoca epaypaTTipoRevoca ) {
//        this.epaypaTTipoRevoca = epaypaTTipoRevoca;
//    }



    public String getCodiceFiscale () {
        return codiceFiscale;
    }



    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }


    public String getNomeCognome () {
        return nomeCognome;
    }


    public void setNomeCognome ( String nomeCognome ) {
        this.nomeCognome = nomeCognome;
    }

    public Long getIdRr () {
        return idRr;
    }

    public void setIdRr ( Long idRr ) {
        this.idRr = idRr;
    }

    public String getIdentificativoDominio () {
        return identificativoDominio;
    }

    public void setIdentificativoDominio ( String identificativoDominio ) {
        this.identificativoDominio = identificativoDominio;
    }

    public String getApplicationId () {
        return applicationId;
    }

    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    public String getIdentificativoMessaggioRevoca () {
        return identificativoMessaggioRevoca;
    }

    public void setIdentificativoMessaggioRevoca ( String identificativoMessaggioRevoca ) {
        this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
    }

    public Timestamp getDataOraMessaggioRevoca () {
        return dataOraMessaggioRevoca;
    }

    public void setDataOraMessaggioRevoca ( Timestamp dataOraMessaggioRevoca ) {
        this.dataOraMessaggioRevoca = dataOraMessaggioRevoca;
    }

    public String getCodiceIdentificativoUnivocoAttestante () {
        return codiceIdentificativoUnivocoAttestante;
    }

    public void setCodiceIdentificativoUnivocoAttestante ( String codiceIdentificativoUnivocoAttestante ) {
        this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
    }

    public String getDenominazioneIstitutoAttestante () {
        return denominazioneIstitutoAttestante;
    }

    public void setDenominazioneIstitutoAttestante ( String denominazioneIstitutoAttestante ) {
        this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
    }

    public BigDecimal getImportoTotaleTevocato () {
        return importoTotaleTevocato;
    }

    public void setImportoTotaleTevocato ( BigDecimal importoTotaleTevocato ) {
        this.importoTotaleTevocato = importoTotaleTevocato;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodiceContestoPagamento () {
        return codiceContestoPagamento;
    }

    public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
        this.codiceContestoPagamento = codiceContestoPagamento;
    }

//    public Integer getTipoRevoca () {
//        return epaypaTTipoRevoca.getId ();
//    }

    public byte[] getXmlRr () {
        return xmlRr;
    }

   
    /**
	 * @return the tipoRevoca
	 */
	public Integer getTipoRevoca() {
		return tipoRevoca;
	}



	/**
	 * @param tipoRevoca the tipoRevoca to set
	 */
	public void setTipoRevoca(Integer tipoRevoca) {
		this.tipoRevoca = tipoRevoca;
	}



	public void setXmlRr ( byte[] xmlRr ) {
        this.xmlRr = xmlRr;
    }
//    public void setTipoRevoca ( BigDecimal tipo_revoca ) {
//        this.tipoRevoca = tipo_revoca;
//    }
}
