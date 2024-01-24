/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * DTO for entity "epay_t_registro_elaborazioni_fault" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author EII
 *
 */
public class EpayTRegistroElaborazioniFaultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id ; // Id or Primary Key


    private Long idPagamento ; 
    @Size( max = 200 )
    private String codicePagamentoRifEnte ; 
    @Size( max = 3 )
    private String codiceMessaggio ; 
    @Size( max = 200 )
    private String descrizioneMessaggio ; 

	private EpayTRegistroElaborazioniReferenceDTO epayTRegistroElaborazioni ;


    /**
     * Default constructor
     */
    public EpayTRegistroElaborazioniFaultDTO() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "bigserial", NotNull : true ) 
     * @param id
     */
	public void setId( Long id ) {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "bigserial", NotNull : true ) 
     * @return the field value
     */
	public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------
    
    /**
     * Set the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : false ) 
     * @param idPagamento
     */
    public void setIdPagamento( Long idPagamento ) {
        this.idPagamento = idPagamento;
    }
    /**
     * Get the "idPagamento" field value
     * This field is mapped on the database column "id_pagamento" ( type "int8", NotNull : false ) 
     * @return the field value
     */
    public Long getIdPagamento() {
        return this.idPagamento;
    }

    /**
     * Set the "codicePagamentoRifEnte" field value
     * This field is mapped on the database column "codice_pagamento_rif_ente" ( type "varchar", NotNull : false ) 
     * @param codicePagamentoRifEnte
     */
    public void setCodicePagamentoRifEnte( String codicePagamentoRifEnte ) {
        this.codicePagamentoRifEnte = codicePagamentoRifEnte;
    }
    /**
     * Get the "codicePagamentoRifEnte" field value
     * This field is mapped on the database column "codice_pagamento_rif_ente" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getCodicePagamentoRifEnte() {
        return this.codicePagamentoRifEnte;
    }

    /**
     * Set the "codiceMessaggio" field value
     * This field is mapped on the database column "codice_messaggio" ( type "bpchar", NotNull : false ) 
     * @param codiceMessaggio
     */
    public void setCodiceMessaggio( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
    }
    /**
     * Get the "codiceMessaggio" field value
     * This field is mapped on the database column "codice_messaggio" ( type "bpchar", NotNull : false ) 
     * @return the field value
     */
    public String getCodiceMessaggio() {
        return this.codiceMessaggio;
    }

    /**
     * Set the "descrizioneMessaggio" field value
     * This field is mapped on the database column "descrizione_messaggio" ( type "varchar", NotNull : false ) 
     * @param descrizioneMessaggio
     */
    public void setDescrizioneMessaggio( String descrizioneMessaggio ) {
        this.descrizioneMessaggio = descrizioneMessaggio;
    }
    /**
     * Get the "descrizioneMessaggio" field value
     * This field is mapped on the database column "descrizione_messaggio" ( type "varchar", NotNull : false ) 
     * @return the field value
     */
    public String getDescrizioneMessaggio() {
        return this.descrizioneMessaggio;
    }


    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR RELATIONSHIPS
    //----------------------------------------------------------------------
    public void setEpayTRegistroElaborazioni( EpayTRegistroElaborazioniReferenceDTO epayTRegistroElaborazioni ) {
        this.epayTRegistroElaborazioni = epayTRegistroElaborazioni;
    }
    public EpayTRegistroElaborazioniReferenceDTO getEpayTRegistroElaborazioni() {
        return this.epayTRegistroElaborazioni;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
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
