/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the mdp_stato_getpayment database table.
 *
 */
@Entity
@Table(name="mdp_stato_getpayment")
@NamedQuery(name="MdpStatoGetpayment.findAll", query="SELECT m FROM MdpStatoGetpayment m")
public class MdpStatoGetpayment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    //	@SequenceGenerator(name="MDP_STATO_GETPAYMENT_IDSTATOGETPAYMENT_GENERATOR", sequenceName="SEQ_GETPAYMENT_STATO", allocationSize = 1 )
    //	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MDP_STATO_GETPAYMENT_IDSTATOGETPAYMENT_GENERATOR")
    @Column(name="id_stato_getpayment")
    private Integer idStatoGetpayment;

    @Column ( name = "cod_stato_getpayment" )
    private String codStatoGetpayment;

    @Column ( name = "descrizione" )
    private String descrizione;

    @Column(name="note_stato")
    private String noteStato;

    public MdpStatoGetpayment() {
    }

    public Integer getIdStatoGetpayment() {
        return this.idStatoGetpayment;
    }

    public void setIdStatoGetpayment(Integer idStatoGetpayment) {
        this.idStatoGetpayment = idStatoGetpayment;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNoteStato() {
        return this.noteStato;
    }

    public void setNoteStato(String noteStato) {
        this.noteStato = noteStato;
    }

    public String getCodStatoGetpayment () {
        return codStatoGetpayment;
    }

    public void setCodStatoGetpayment ( String codStatoGetpayment ) {
        this.codStatoGetpayment = codStatoGetpayment;
    }

}
