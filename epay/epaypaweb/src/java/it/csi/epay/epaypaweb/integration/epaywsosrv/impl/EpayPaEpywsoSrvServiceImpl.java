/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaywsosrv.impl;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import it.csi.epay.epaypaweb.dto.StatoFlussoAggregatoDto;
import it.csi.epay.epaypaweb.exception.IntegrationException;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.integration.epaywsosrv.RicercaStatiAggregatiWsoRequestType;
import it.csi.epay.epaypaweb.integration.epaywsosrv.RicercaStatiAggregatiWsoResponseType;
import it.csi.epay.epaypaweb.integration.epaywsosrv.RicercaStatiAggregatiWsoTypeList;
import it.csi.epay.epaypaweb.integration.epaywsosrv.RicercaStatoAggregatoWsoType;



public class EpayPaEpywsoSrvServiceImpl extends EpayPaEpywsoSrvServiceRoot {

    static private final String CLASSNAME = EpayPaEpywsoSrvServiceImpl.class.getSimpleName ();

    public EpayPaEpywsoSrvServiceImpl (String endpoint ) throws IntegrationException {
        super (endpoint );
    }

	@Override
	public List<StatoFlussoAggregatoDto> ricercaStatiAggregatiWso( String idFlusso, String idMessaggio, String codFiscEnte , BigInteger idTipoRichiesta)throws PersistenceException
	{
	String methodName = "ricercaStatiAggregatiWso";
    
    

    List<StatoFlussoAggregatoDto> output = new LinkedList<StatoFlussoAggregatoDto>();

    try {
        log.info ( CLASSNAME + " " + methodName + " - START" );
        
        RicercaStatiAggregatiWsoRequestType request= new RicercaStatiAggregatiWsoRequestType();
		RicercaStatiAggregatiWsoTypeList list= new RicercaStatiAggregatiWsoTypeList();
		RicercaStatoAggregatoWsoType type= new RicercaStatoAggregatoWsoType ();
		type.setIdFlusso(idFlusso);
		type.setIdMessaggio(idMessaggio);
		type.setCodiceFiscaleEnte(codFiscEnte);
		list.getRicercaStatoAggregatoWso().add(type);
		request.setIdTipoRichiesta(idTipoRichiesta);
		request.setRicercaStatoAggregatoWsoList(list);
        
        RicercaStatiAggregatiWsoResponseType response = getSoapService ().ricercaStatiAggregatiWso(request);
        if (null != response && null!= response.getEsitoRicercaStatiAggregatiWsoList())
        {
        	 output = EpayPaEpyWsoSrvServiceMapper.map (response.getEsitoRicercaStatiAggregatiWsoList().getEsitoRicercaStatiAggregatiWso());
        }
        
         

    } catch ( Exception e ) {
        throw new PersistenceException( e.getMessage (), e );

    } finally {
        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    return output;
	}
    
    

   
}
