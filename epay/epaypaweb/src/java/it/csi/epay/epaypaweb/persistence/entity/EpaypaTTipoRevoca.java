/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="epaypa_t_tipo_revoca")
@NamedQuery(//<!-- CSI_PAG-184 -->
    name= "EpaypaTTipoRevoca.findTipoRevocaById",
    query="select t "+//,a.idRR,a.iuv,a.idNotificaPagamento "+
                    "FROM  EpaypaTTipoRevoca t "+ 
                    " where t.id = :id")
public class EpaypaTTipoRevoca {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
//    @OneToOne(mappedBy = "epaypaTTipoRevoca")
//    @JoinColumn(name = "id", nullable = false)
//    private EpaypaTRr epaypaTRr;
    
    private String codice ;
    
    private String descrizione ;

    
    public Integer getId () {
        return id;
    }

    
    public void setId ( Integer id ) {
        this.id = id;
    }

    
    public String getCodice () {
        return codice;
    }

    
    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    
    public String getDescrizione () {
        return descrizione;
    }

    
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }
    
}
