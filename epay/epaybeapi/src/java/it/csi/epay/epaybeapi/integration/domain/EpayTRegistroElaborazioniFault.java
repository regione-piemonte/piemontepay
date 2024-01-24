/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;



import javax.persistence.*;

/**
 * Persistent class for "EpayTRegistroElaborazioniFault" entity stored in table "epay_t_registro_elaborazioni_fault" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_registro_elaborazioni_fault", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTRegistroElaborazioniFault.countAll",  query="SELECT COUNT(x) FROM EpayTRegistroElaborazioniFault x" ),
  @NamedQuery ( name="EpayTRegistroElaborazioniFault.countById", query="SELECT COUNT(x) FROM EpayTRegistroElaborazioniFault x WHERE x.id = ?1 " )
} )
public class EpayTRegistroElaborazioniFault implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTRegistroElaborazioniFault reference ( Long id ) {
        EpayTRegistroElaborazioniFault reference = new EpayTRegistroElaborazioniFault ();
        reference.setId( id );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="id_pagamento")
    private Long idPagamento ;

    @Column(name="codice_pagamento_rif_ente", length=200)
    private String codicePagamentoRifEnte ;

    @Column(name="codice_messaggio", length=3)
    private String codiceMessaggio ;

    @Column(name="descrizione_messaggio", length=200)
    private String descrizioneMessaggio ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToOne
    @JoinColumn(name="id_registro_elaborazioni", referencedColumnName="id")
	private EpayTRegistroElaborazioni epayTRegistroElaborazioni ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTRegistroElaborazioniFault() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getId();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : id_pagamento ( int8 ) 
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    //--- DATABASE MAPPING : codice_pagamento_rif_ente ( varchar ) 
    public void setCodicePagamentoRifEnte( String codicePagamentoRifEnte ) {
        this.codicePagamentoRifEnte = codicePagamentoRifEnte;
    }
    public String getCodicePagamentoRifEnte() {
        return this.codicePagamentoRifEnte;
    }

    //--- DATABASE MAPPING : codice_messaggio ( bpchar ) 
    public void setCodiceMessaggio( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }
    public String getCodiceMessaggio() {
        return this.codiceMessaggio;
    }

    //--- DATABASE MAPPING : descrizione_messaggio ( varchar ) 
    public void setDescrizioneMessaggio( String descrizioneMessaggio ) {
        this.descrizioneMessaggio = descrizioneMessaggio;
    }
    public String getDescrizioneMessaggio() {
        return this.descrizioneMessaggio;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTRegistroElaborazioni( EpayTRegistroElaborazioni epayTRegistroElaborazioni ) {
        this.epayTRegistroElaborazioni = epayTRegistroElaborazioni;
    }
    public EpayTRegistroElaborazioni getEpayTRegistroElaborazioni() {
        return this.epayTRegistroElaborazioni;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(idPagamento);
        sb.append("|");
        sb.append(codicePagamentoRifEnte);
        sb.append("|");
        sb.append(codiceMessaggio);
        sb.append("|");
        sb.append(descrizioneMessaggio);
        return sb.toString(); 
    } 

}
