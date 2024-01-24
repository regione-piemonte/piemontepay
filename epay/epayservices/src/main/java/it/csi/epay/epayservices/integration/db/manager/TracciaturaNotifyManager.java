/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTTracciaturaNotify;
import it.csi.epay.epayservices.model.TracciaturaNotify;

@Stateless(name="TracciaturaNotifyManager", mappedName = "TracciaturaNotifyManager")
public class TracciaturaNotifyManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
    public void inserisci ( TracciaturaNotify tracciaturaNotifyDto ) {

        entityManager.persist ( mapNotify(tracciaturaNotifyDto) );
      
    }

	
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public void inserisciMulti ( List<TracciaturaNotify> tracciaturaNotifyDtoList ) {
    	for (TracciaturaNotify tracciaturaNotifyDto:tracciaturaNotifyDtoList )
    	{
    	        entityManager.persist ( mapNotify(tracciaturaNotifyDto) );
    	}
       
      
    }
    

    private EpayTTracciaturaNotify mapNotify(TracciaturaNotify tracciaturaNotifyDto) {
    	
		EpayTTracciaturaNotify tTracciaturaNotify = new EpayTTracciaturaNotify();
        tTracciaturaNotify.setDataInivioNotify(tracciaturaNotifyDto.getDataInivioNotify());
        tTracciaturaNotify.setEsitoStatoInvioNotify(tracciaturaNotifyDto.getEsitoStatoInvioNotify());
        tTracciaturaNotify.setMessageUuid(tracciaturaNotifyDto.getMessageUuid());
        tTracciaturaNotify.setBulkId(tracciaturaNotifyDto.getBulkId());
        if (tracciaturaNotifyDto.getIdPagamento() != null) {
        	tTracciaturaNotify.setEpayTPagamento(entityManager.find(EpayTPagamento.class, tracciaturaNotifyDto.getIdPagamento()));
        }
		return tTracciaturaNotify;
	}

}
