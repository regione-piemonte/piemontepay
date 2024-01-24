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
import it.csi.epay.epayservices.integration.db.entities.EpayTTracciaturaNotifyCittaFacile;
import it.csi.epay.epayservices.model.TracciaturaNotifyCittaFacile;

@Stateless(name="TracciaturaNotifyCittaFacileManager", mappedName = "TracciaturaNotifyCittaFacileManager")
public class TracciaturaNotifyCittaFacileManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
    public void inserisci ( TracciaturaNotifyCittaFacile tracciaturaNotifyCittaFacileDto ) {

        entityManager.persist ( mapNotify(tracciaturaNotifyCittaFacileDto) );
      
    }

	
    @TransactionAttribute ( TransactionAttributeType.REQUIRED )
    public void inserisciMulti ( List<TracciaturaNotifyCittaFacile> tracciaturaNotifyCittaFacileDtoList ) {
    	for (TracciaturaNotifyCittaFacile tracciaturaNotifyCittaFacileDto:tracciaturaNotifyCittaFacileDtoList )
    	{
    	        entityManager.persist ( mapNotify(tracciaturaNotifyCittaFacileDto) );
    	}
    }
    

    private EpayTTracciaturaNotifyCittaFacile mapNotify(TracciaturaNotifyCittaFacile tracciaturaNotifyCittaFacileDto) {
    	
        EpayTTracciaturaNotifyCittaFacile tTracciaturaNotifyCittaFacile = new EpayTTracciaturaNotifyCittaFacile();
        tTracciaturaNotifyCittaFacile.setDataInivioNotify(tracciaturaNotifyCittaFacileDto.getDataInivioNotify());
        tTracciaturaNotifyCittaFacile.setEsitoStatoInvioNotify(tracciaturaNotifyCittaFacileDto.getEsitoStatoInvioNotify());
        tTracciaturaNotifyCittaFacile.setMessageUuid(tracciaturaNotifyCittaFacileDto.getMessageUuid());
        tTracciaturaNotifyCittaFacile.setBulkId(tracciaturaNotifyCittaFacileDto.getBulkId());
        if (tracciaturaNotifyCittaFacileDto.getIdPagamento() != null) {
            tTracciaturaNotifyCittaFacile.setEpayTPagamento(entityManager.find(EpayTPagamento.class, tracciaturaNotifyCittaFacileDto.getIdPagamento()));
        }
		return tTracciaturaNotifyCittaFacile;
	}

}
