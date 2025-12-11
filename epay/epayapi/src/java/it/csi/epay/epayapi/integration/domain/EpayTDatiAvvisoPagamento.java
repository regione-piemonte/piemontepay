/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for "EpayTDatiAvvisoPagamento" entity stored in table "epay_t_dati_avviso_pagamento" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_dati_avviso_pagamento", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTDatiAvvisoPagamento.countAll",  query="SELECT COUNT(x) FROM EpayTDatiAvvisoPagamento x" ),
  @NamedQuery ( name="EpayTDatiAvvisoPagamento.countById", query="SELECT COUNT(x) FROM EpayTDatiAvvisoPagamento x WHERE x.idDatiAvvisoPagamento = ?1 " )
} )
public class EpayTDatiAvvisoPagamento implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTDatiAvvisoPagamento reference ( Long idDatiAvvisoPagamento ) {
        EpayTDatiAvvisoPagamento reference = new EpayTDatiAvvisoPagamento ();
        reference.setIdDatiAvvisoPagamento( idDatiAvvisoPagamento );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_dati_avviso_pagamento", nullable=false)
    private Long idDatiAvvisoPagamento ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="settore", length=50)
    private String settore ;

    @Column(name="indirizzo", length=30)
    private String indirizzo ;

    @Column(name="localita", length=30)
    private String localita ;

    @Column(name="cap", length=5)
    private String cap ;

    @Column(name="sigla_provincia", length=2)
    private String siglaProvincia ;

    @Column(name="email", length=27)
    private String email ;

    @Column(name="info_ente", length=100)
    private String infoEnte ;

    @Column(name="intestatario_cc_postale", length=50)
    private String intestatarioCcPostale ;

    @Column(name="numero_cc_postale", length=20)
    private String numeroCcPostale ;

    @Column(name="autorizzazione_da_poste_it", length=37)
    private String autorizzazioneDaPosteIt ;

    @Column(name="utente_ultima_modifica", nullable=false, length=50)
    private String utenteUltimaModifica ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_ultima_modifica", nullable=false)
    private Date dataUltimaModifica ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_codice_versamento", referencedColumnName="id_tipo_pagamento")
	private EpayTTipoPagamento epayTTipoPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTDatiAvvisoPagamento() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdDatiAvvisoPagamento();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdDatiAvvisoPagamento( Long idDatiAvvisoPagamento ) {
        this.idDatiAvvisoPagamento = idDatiAvvisoPagamento ;
    }
    public Long getIdDatiAvvisoPagamento() {
        return this.idDatiAvvisoPagamento;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : settore ( varchar ) 
    public void setSettore( String settore ) {
        this.settore = settore;
    }
    public String getSettore() {
        return this.settore;
    }

    //--- DATABASE MAPPING : indirizzo ( varchar ) 
    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    //--- DATABASE MAPPING : localita ( varchar ) 
    public void setLocalita( String localita ) {
        this.localita = localita;
    }
    public String getLocalita() {
        return this.localita;
    }

    //--- DATABASE MAPPING : cap ( varchar ) 
    public void setCap( String cap ) {
        this.cap = cap;
    }
    public String getCap() {
        return this.cap;
    }

    //--- DATABASE MAPPING : sigla_provincia ( varchar ) 
    public void setSiglaProvincia( String siglaProvincia ) {
        this.siglaProvincia = siglaProvincia;
    }
    public String getSiglaProvincia() {
        return this.siglaProvincia;
    }

    //--- DATABASE MAPPING : email ( varchar ) 
    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    //--- DATABASE MAPPING : info_ente ( varchar ) 
    public void setInfoEnte( String infoEnte ) {
        this.infoEnte = infoEnte;
    }
    public String getInfoEnte() {
        return this.infoEnte;
    }

    //--- DATABASE MAPPING : intestatario_cc_postale ( varchar ) 
    public void setIntestatarioCcPostale( String intestatarioCcPostale ) {
        this.intestatarioCcPostale = intestatarioCcPostale;
    }
    public String getIntestatarioCcPostale() {
        return this.intestatarioCcPostale;
    }

    //--- DATABASE MAPPING : numero_cc_postale ( varchar ) 
    public void setNumeroCcPostale( String numeroCcPostale ) {
        this.numeroCcPostale = numeroCcPostale;
    }
    public String getNumeroCcPostale() {
        return this.numeroCcPostale;
    }

    //--- DATABASE MAPPING : autorizzazione_da_poste_it ( varchar ) 
    public void setAutorizzazioneDaPosteIt( String autorizzazioneDaPosteIt ) {
        this.autorizzazioneDaPosteIt = autorizzazioneDaPosteIt;
    }
    public String getAutorizzazioneDaPosteIt() {
        return this.autorizzazioneDaPosteIt;
    }

    //--- DATABASE MAPPING : utente_ultima_modifica ( varchar ) 
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }

    //--- DATABASE MAPPING : data_ultima_modifica ( timestamp ) 
    public void setDataUltimaModifica( Date dataUltimaModifica ) {
        this.dataUltimaModifica = dataUltimaModifica;
    }
    public Date getDataUltimaModifica() {
        return this.dataUltimaModifica;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTTipoPagamento( EpayTTipoPagamento epayTTipoPagamento ) {
        this.epayTTipoPagamento = epayTTipoPagamento;
    }
    public EpayTTipoPagamento getEpayTTipoPagamento() {
        return this.epayTTipoPagamento;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idDatiAvvisoPagamento);
        sb.append("]:"); 
        sb.append(settore);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(localita);
        sb.append("|");
        sb.append(cap);
        sb.append("|");
        sb.append(siglaProvincia);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(infoEnte);
        sb.append("|");
        sb.append(intestatarioCcPostale);
        sb.append("|");
        sb.append(numeroCcPostale);
        sb.append("|");
        sb.append(autorizzazioneDaPosteIt);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        sb.append("|");
        sb.append(dataUltimaModifica);
        return sb.toString(); 
    } 

}
