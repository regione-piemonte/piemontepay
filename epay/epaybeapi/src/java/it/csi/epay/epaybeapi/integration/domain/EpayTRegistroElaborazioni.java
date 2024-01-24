/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayTRegistroElaborazioni" entity stored in table "epay_t_registro_elaborazioni" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_registro_elaborazioni", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTRegistroElaborazioni.countAll",  query="SELECT COUNT(x) FROM EpayTRegistroElaborazioni x" ),
  @NamedQuery ( name="EpayTRegistroElaborazioni.countById", query="SELECT COUNT(x) FROM EpayTRegistroElaborazioni x WHERE x.id = ?1 " )
} )
public class EpayTRegistroElaborazioni implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTRegistroElaborazioni reference ( Long id ) {
        EpayTRegistroElaborazioni reference = new EpayTRegistroElaborazioni ();
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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_inizio", nullable=false)
    private Date dataInizio ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_fine")
    private Date dataFine ;

    @Column(name="operazione", length=250)
    private String operazione ;

    @Column(name="id_messaggio", length=250)
    private String idMessaggio ;

    @Column(name="id_ente")
    private Long idEnte ;

    @Column(name="id_tipo_pagamento")
    private Long idTipoPagamento ;

    @Column(name="pagamenti_spontanei")
    private Boolean pagamentiSpontanei ;

    @Column(name="num_pagamenti")
    private Integer numPagamenti ;

    @Column(name="importo_tot_pagamenti")
    private BigDecimal importoTotPagamenti ;

    @Column(name="esito", nullable=false, length=250)
    private String esito ;

    @Column(name="message_fault", length=2000)
    private String messageFault ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @ManyToMany(mappedBy="listOfEpayTRegistroElaborazioni", targetEntity=EpayTPagamento.class)
	private List<EpayTPagamento> listOfEpayTPagamento ;
    
    @OneToMany(mappedBy="epayTRegistroElaborazioni", targetEntity=EpayTRegistroElaborazioniFault.class)
	private List<EpayTRegistroElaborazioniFault> listOfEpayTRegistroElaborazioniFault ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTRegistroElaborazioni() {
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
    //--- DATABASE MAPPING : data_inizio ( timestamp ) 
    public void setDataInizio( Date dataInizio ) {
        this.dataInizio = dataInizio;
    }
    public Date getDataInizio() {
        return this.dataInizio;
    }

    //--- DATABASE MAPPING : data_fine ( timestamp ) 
    public void setDataFine( Date dataFine ) {
        this.dataFine = dataFine;
    }
    public Date getDataFine() {
        return this.dataFine;
    }

    //--- DATABASE MAPPING : operazione ( varchar ) 
    public void setOperazione( String operazione ) {
        this.operazione = operazione;
    }
    public String getOperazione() {
        return this.operazione;
    }

    //--- DATABASE MAPPING : id_messaggio ( varchar ) 
    public void setIdMessaggio( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }
    public String getIdMessaggio() {
        return this.idMessaggio;
    }

    //--- DATABASE MAPPING : id_ente ( int8 ) 
    public void setIdEnte( Long idEnte ) {
        this.idEnte = idEnte;
    }
    public Long getIdEnte() {
        return this.idEnte;
    }

    //--- DATABASE MAPPING : id_tipo_pagamento ( int8 ) 
    public void setIdTipoPagamento( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }
    public Long getIdTipoPagamento() {
        return this.idTipoPagamento;
    }

    //--- DATABASE MAPPING : pagamenti_spontanei ( bool ) 
    public void setPagamentiSpontanei( Boolean pagamentiSpontanei ) {
        this.pagamentiSpontanei = pagamentiSpontanei;
    }
    public Boolean getPagamentiSpontanei() {
        return this.pagamentiSpontanei;
    }

    //--- DATABASE MAPPING : num_pagamenti ( int4 ) 
    public void setNumPagamenti( Integer numPagamenti ) {
        this.numPagamenti = numPagamenti;
    }
    public Integer getNumPagamenti() {
        return this.numPagamenti;
    }

    //--- DATABASE MAPPING : importo_tot_pagamenti ( numeric ) 
    public void setImportoTotPagamenti( BigDecimal importoTotPagamenti ) {
        this.importoTotPagamenti = importoTotPagamenti;
    }
    public BigDecimal getImportoTotPagamenti() {
        return this.importoTotPagamenti;
    }

    //--- DATABASE MAPPING : esito ( varchar ) 
    public void setEsito( String esito ) {
        this.esito = esito;
    }
    public String getEsito() {
        return this.esito;
    }

    //--- DATABASE MAPPING : message_fault ( varchar ) 
    public void setMessageFault( String messageFault ) {
        this.messageFault = messageFault;
    }
    public String getMessageFault() {
        return this.messageFault;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTPagamento( List<EpayTPagamento> listOfEpayTPagamento ) {
        this.listOfEpayTPagamento = listOfEpayTPagamento;
    }
    public List<EpayTPagamento> getListOfEpayTPagamento() {
        return this.listOfEpayTPagamento;
    }
    
    public void setListOfEpayTRegistroElaborazioniFault( List<EpayTRegistroElaborazioniFault> listOfEpayTRegistroElaborazioniFault ) {
        this.listOfEpayTRegistroElaborazioniFault = listOfEpayTRegistroElaborazioniFault;
    }
    public List<EpayTRegistroElaborazioniFault> getListOfEpayTRegistroElaborazioniFault() {
        return this.listOfEpayTRegistroElaborazioniFault;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(dataInizio);
        sb.append("|");
        sb.append(dataFine);
        sb.append("|");
        sb.append(operazione);
        sb.append("|");
        sb.append(idMessaggio);
        sb.append("|");
        sb.append(idEnte);
        sb.append("|");
        sb.append(idTipoPagamento);
        sb.append("|");
        sb.append(pagamentiSpontanei);
        sb.append("|");
        sb.append(numPagamenti);
        sb.append("|");
        sb.append(importoTotPagamenti);
        sb.append("|");
        sb.append(esito);
        sb.append("|");
        sb.append(messageFault);
        return sb.toString(); 
    } 

}
