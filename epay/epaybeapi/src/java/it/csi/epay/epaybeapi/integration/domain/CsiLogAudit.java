/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for "CsiLogAudit" entity stored in table "csi_log_audit" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="csi_log_audit", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CsiLogAudit.countAll",  query="SELECT COUNT(x) FROM CsiLogAudit x" ),
  @NamedQuery ( name="CsiLogAudit.countById", query="SELECT COUNT(x) FROM CsiLogAudit x WHERE x.idLog = ?1 " )
} )
public class CsiLogAudit implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static CsiLogAudit reference ( Long idLog ) {
        CsiLogAudit reference = new CsiLogAudit ();
        reference.setIdLog( idLog );
        return reference;
    }

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Column(name="id_log", nullable=false)
    private Long idLog ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    //----------------------------------------------------------------------
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_ora", nullable=false)
    private Date dataOra ;

    @Column(name="id_app", nullable=false, length=100)
    private String idApp ;

    @Column(name="ip_address", length=40)
    private String ipAddress ;

    @Column(name="utente", nullable=false, length=100)
    private String utente ;

    @Column(name="operazione", nullable=false, length=50)
    private String operazione ;

    @Column(name="ogg_oper", nullable=false, length=500)
    private String oggOper ;

    @Column(name="key_oper", nullable=false, length=500)
    private String keyOper ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CsiLogAudit() {
		super();
    }

	@Override
	public Long getPrimaryKey() {
		return getIdLog();
	}

    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdLog( Long idLog ) {
        this.idLog = idLog ;
    }
    public Long getIdLog() {
        return idLog;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : data_ora ( timestamp )
    public void setDataOra( Date dataOra ) {
        this.dataOra = dataOra;
    }
    public Date getDataOra() {
        return dataOra;
    }

    //--- DATABASE MAPPING : id_app ( varchar )
    public void setIdApp( String idApp ) {
        this.idApp = idApp;
    }
    public String getIdApp() {
        return idApp;
    }

    //--- DATABASE MAPPING : ip_address ( varchar )
    public void setIpAddress( String ipAddress ) {
        this.ipAddress = ipAddress;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    //--- DATABASE MAPPING : utente ( varchar )
    public void setUtente( String utente ) {
        this.utente = utente;
    }
    public String getUtente() {
        return utente;
    }

    //--- DATABASE MAPPING : operazione ( varchar )
    public void setOperazione( String operazione ) {
        this.operazione = operazione;
    }
    public String getOperazione() {
        return operazione;
    }

    //--- DATABASE MAPPING : ogg_oper ( varchar )
    public void setOggOper( String oggOper ) {
        this.oggOper = oggOper;
    }
    public String getOggOper() {
        return oggOper;
    }

    //--- DATABASE MAPPING : key_oper ( varchar )
    public void setKeyOper( String keyOper ) {
        this.keyOper = keyOper;
    }
    public String getKeyOper() {
        return keyOper;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idLog);
        sb.append("]:");
        sb.append(dataOra);
        sb.append("|");
        sb.append(idApp);
        sb.append("|");
        sb.append(ipAddress);
        sb.append("|");
        sb.append(utente);
        sb.append("|");
        sb.append(operazione);
        sb.append("|");
        sb.append(oggOper);
        sb.append("|");
        sb.append(keyOper);
        return sb.toString();
    }

}
