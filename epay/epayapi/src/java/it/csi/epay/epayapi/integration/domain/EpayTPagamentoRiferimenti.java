/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayTPagamentoRiferimenti" entity stored in table "epay_t_pagamento_riferimenti" <br>
 * This class is a "record entity"  <br>
 *
 * @author fabio.fenoglio
 *
 */
@Entity
@Table(name="epay_t_pagamento_riferimenti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTPagamentoRiferimenti.countAll",  query="SELECT COUNT(x) FROM EpayTPagamentoRiferimenti x" ),
  @NamedQuery ( name="EpayTPagamentoRiferimenti.countById", query="SELECT COUNT(x) FROM EpayTPagamentoRiferimenti x WHERE x.idRiferimento = ?1 " )
} )
public class EpayTPagamentoRiferimenti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTPagamentoRiferimenti reference ( Long idRiferimento ) {
        EpayTPagamentoRiferimenti reference = new EpayTPagamentoRiferimenti ();
        reference.setIdRiferimento( idRiferimento );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id_riferimento", nullable=false)
    private Long idRiferimento ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="nome", nullable=false, length=70)
    private String nome ;

    @Column(name="progressivo", nullable=false)
    private Integer progressivo ;

    @Column(name="valore", nullable=false, length=70)
    private String valore ;

    @Column(name="utente_ultima_modifica", nullable=false, length=2147483647)
    private String utenteUltimaModifica ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_pagamento", referencedColumnName="id_pagamento")
	private EpayTPagamento epayTPagamento ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPagamentoRiferimenti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdRiferimento();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdRiferimento( Long idRiferimento ) {
        this.idRiferimento = idRiferimento ;
    }
    public Long getIdRiferimento() {
        return this.idRiferimento;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : nome ( varchar ) 
    public void setNome( String nome ) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    //--- DATABASE MAPPING : progressivo ( int4 ) 
    public void setProgressivo( Integer progressivo ) {
        this.progressivo = progressivo;
    }
    public Integer getProgressivo() {
        return this.progressivo;
    }

    //--- DATABASE MAPPING : valore ( varchar ) 
    public void setValore( String valore ) {
        this.valore = valore;
    }
    public String getValore() {
        return this.valore;
    }

    //--- DATABASE MAPPING : utente_ultima_modifica ( varchar ) 
    public void setUtenteUltimaModifica( String utenteUltimaModifica ) {
        this.utenteUltimaModifica = utenteUltimaModifica;
    }
    public String getUtenteUltimaModifica() {
        return this.utenteUltimaModifica;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTPagamento( EpayTPagamento epayTPagamento ) {
        this.epayTPagamento = epayTPagamento;
    }
    public EpayTPagamento getEpayTPagamento() {
        return this.epayTPagamento;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idRiferimento);
        sb.append("]:"); 
        sb.append(nome);
        sb.append("|");
        sb.append(progressivo);
        sb.append("|");
        sb.append(valore);
        sb.append("|");
        sb.append(utenteUltimaModifica);
        return sb.toString(); 
    } 

}
