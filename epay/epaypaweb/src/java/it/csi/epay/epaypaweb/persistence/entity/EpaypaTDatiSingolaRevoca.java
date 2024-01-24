/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="epaypa_t_dati_singola_revoca")
@NamedQuery(//<!-- CSI_PAG-184 -->
    name = "EpaypaTDatiSingolaRevoca.findRevocaByIdRr",
    query ="select p "+
                    "FROM  EpaypaTDatiSingolaRevoca p"+ 
                    " where p.epaypaTDatiSingolaRevocaId.idRr = :idRr"
                )
public class EpaypaTDatiSingolaRevoca {
    
    
    @EmbeddedId
    EpaypaTDatiSingolaRevocaId epaypaTDatiSingolaRevocaId;

    
    public EpaypaTDatiSingolaRevocaId getEpaypaTDatiSingolaRevocaId () {
        return epaypaTDatiSingolaRevocaId;
    }

    
    public void setEpaypaTDatiSingolaRevocaId ( EpaypaTDatiSingolaRevocaId epaypaTDatiSingolaRevocaId ) {
        this.epaypaTDatiSingolaRevocaId = epaypaTDatiSingolaRevocaId;
    }
    
}
