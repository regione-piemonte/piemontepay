/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.*;

import java.util.List;
/**
 * Persistent class for "EpayTRegistroVersamenti" entity stored in table "epay_t_registro_versamenti" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_registro_versamenti", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTRegistroVersamenti.countAll",  query="SELECT COUNT(x) FROM EpayTRegistroVersamenti x" ),
  @NamedQuery ( name="EpayTRegistroVersamenti.countById", query="SELECT COUNT(x) FROM EpayTRegistroVersamenti x WHERE x.idRegistro = ?1 " )
} )
public class EpayTRegistroVersamenti implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTRegistroVersamenti reference ( Long idRegistro ) {
        EpayTRegistroVersamenti reference = new EpayTRegistroVersamenti ();
        reference.setIdRegistro( idRegistro );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_registro", nullable=false)
    private Long idRegistro ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_operazione", nullable=false)
    private Date dataOperazione ;

    @Column(name="iuv", length=25)
    private String iuv ;

    @Column(name="desc_esito_pagamento", length=255)
    private String descEsitoPagamento ;

    @Column(name="origine_inserimento", length=255)
    private String origineInserimento ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    
    @OneToMany(mappedBy="epayTRegistroVersamenti", targetEntity=EpayTEsitiRicevuti.class)
	private List<EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti ;
    
    @ManyToOne
    @JoinColumn(name="id_transazione", referencedColumnName="id_transazione")
	private EpayTTransazioneMdp epayTTransazioneMdp ;
    
    @ManyToOne
    @JoinColumn(name="id_pagamento", referencedColumnName="id_pagamento")
	private EpayTPagamento epayTPagamento ;
    
    @ManyToOne
    @JoinColumn(name="id_stato", referencedColumnName="id_stato")
	private EpayDStatoPagamento epayDStatoPagamento ;
    
    @ManyToOne
    @JoinColumn(name="id_anagrafica_versante", referencedColumnName="id_anagrafica")
	private EpayTAnagrafica epayTAnagrafica ;
    
    @OneToMany(mappedBy="epayTRegistroVersamenti", targetEntity=EpayTRt.class)
	private List<EpayTRt> listOfEpayTRt ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTRegistroVersamenti() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdRegistro();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdRegistro( Long idRegistro ) {
        this.idRegistro = idRegistro ;
    }
    public Long getIdRegistro() {
        return this.idRegistro;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : data_operazione ( timestamp ) 
    public void setDataOperazione( Date dataOperazione ) {
        this.dataOperazione = dataOperazione;
    }
    public Date getDataOperazione() {
        return this.dataOperazione;
    }

    //--- DATABASE MAPPING : iuv ( varchar ) 
    public void setIuv( String iuv ) {
        this.iuv = iuv;
    }
    public String getIuv() {
        return this.iuv;
    }

    //--- DATABASE MAPPING : desc_esito_pagamento ( varchar ) 
    public void setDescEsitoPagamento( String descEsitoPagamento ) {
        this.descEsitoPagamento = descEsitoPagamento;
    }
    public String getDescEsitoPagamento() {
        return this.descEsitoPagamento;
    }

    //--- DATABASE MAPPING : origine_inserimento ( varchar ) 
    public void setOrigineInserimento( String origineInserimento ) {
        this.origineInserimento = origineInserimento;
    }
    public String getOrigineInserimento() {
        return this.origineInserimento;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setListOfEpayTEsitiRicevuti( List<EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti ) {
        this.listOfEpayTEsitiRicevuti = listOfEpayTEsitiRicevuti;
    }
    public List<EpayTEsitiRicevuti> getListOfEpayTEsitiRicevuti() {
        return this.listOfEpayTEsitiRicevuti;
    }
    
    public void setEpayTTransazioneMdp( EpayTTransazioneMdp epayTTransazioneMdp ) {
        this.epayTTransazioneMdp = epayTTransazioneMdp;
    }
    public EpayTTransazioneMdp getEpayTTransazioneMdp() {
        return this.epayTTransazioneMdp;
    }
    
    public void setEpayTPagamento( EpayTPagamento epayTPagamento ) {
        this.epayTPagamento = epayTPagamento;
    }
    public EpayTPagamento getEpayTPagamento() {
        return this.epayTPagamento;
    }
    
    public void setEpayDStatoPagamento( EpayDStatoPagamento epayDStatoPagamento ) {
        this.epayDStatoPagamento = epayDStatoPagamento;
    }
    public EpayDStatoPagamento getEpayDStatoPagamento() {
        return this.epayDStatoPagamento;
    }
    
    public void setEpayTAnagrafica( EpayTAnagrafica epayTAnagrafica ) {
        this.epayTAnagrafica = epayTAnagrafica;
    }
    public EpayTAnagrafica getEpayTAnagrafica() {
        return this.epayTAnagrafica;
    }
    
    public void setListOfEpayTRt( List<EpayTRt> listOfEpayTRt ) {
        this.listOfEpayTRt = listOfEpayTRt;
    }
    public List<EpayTRt> getListOfEpayTRt() {
        return this.listOfEpayTRt;
    }
    

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(idRegistro);
        sb.append("]:"); 
        sb.append(dataOperazione);
        sb.append("|");
        sb.append(iuv);
        sb.append("|");
        sb.append(descEsitoPagamento);
        sb.append("|");
        sb.append(origineInserimento);
        return sb.toString(); 
    } 

}
