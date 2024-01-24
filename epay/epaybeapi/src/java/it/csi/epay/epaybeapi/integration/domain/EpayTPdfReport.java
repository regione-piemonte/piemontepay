/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;



import javax.persistence.*;

import org.hibernate.annotations.Type;
/**
 * Persistent class for "EpayTPdfReport" entity stored in table "epay_t_pdf_report" <br>
 * This class is a "record entity"  <br>
 *
 * @author EII
 *
 */
@Entity
@Table(name="epay_t_pdf_report", schema="epay" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="EpayTPdfReport.countAll",  query="SELECT COUNT(x) FROM EpayTPdfReport x" ),
  @NamedQuery ( name="EpayTPdfReport.countById", query="SELECT COUNT(x) FROM EpayTPdfReport x WHERE x.id = ?1 " )
} )
public class EpayTPdfReport implements IEntity<Long> {

    private static final long serialVersionUID = 1L;

	public static EpayTPdfReport reference ( Long id ) {
        EpayTPdfReport reference = new EpayTPdfReport ();
        reference.setId( id );
        return reference;
    }
    
    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
    private Long id ;



    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="codice", nullable=false, length=10)
    private String codice ;

    @Column(name="nome_template", nullable=false, length=100)
    private String nomeTemplate ;

    @Column(name="template", nullable=false)
    @Type(type="it.csi.epay.epaybeapi.util.SQLXMLType")
    private String template ;

    @Lob
    @Column(name="template_compilato")
    @Type ( type = "org.hibernate.type.BinaryType" )
    private byte[] templateCompilato ;


    //----------------------------------------------------------------------
    // ENTITY RELATIONSHIPS 
    //----------------------------------------------------------------------    

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public EpayTPdfReport() {
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
    //--- DATABASE MAPPING : codice ( varchar ) 
    public void setCodice( String codice ) {
        this.codice = codice;
    }
    public String getCodice() {
        return this.codice;
    }

    //--- DATABASE MAPPING : nome_template ( varchar ) 
    public void setNomeTemplate( String nomeTemplate ) {
        this.nomeTemplate = nomeTemplate;
    }
    public String getNomeTemplate() {
        return this.nomeTemplate;
    }

    //--- DATABASE MAPPING : template ( xml ) 
    public void setTemplate( String template ) {
        this.template = template;
    }
    public String getTemplate() {
        return this.template;
    }

    //--- DATABASE MAPPING : template_compilato ( bytea ) 
    public void setTemplateCompilato( byte[] templateCompilato ) {
        this.templateCompilato = templateCompilato;
    }
    public byte[] getTemplateCompilato() {
        return this.templateCompilato;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR RELATIONSHIPS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(codice);
        sb.append("|");
        sb.append(nomeTemplate);
        sb.append("|");
        sb.append(template);
        // attribute 'templateCompilato' not usable (type = byte[])
        return sb.toString(); 
    } 

}
