/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.business.service.TassonomiaService;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.TassonomiaOutputDto;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;


@Component
@Transactional
public class TassonomiaServiceImpl implements TassonomiaService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );
    
    @Autowired
    private TassonomiaRepository repository;
    
    @Autowired
    private EnteRepository enteRepository;
    
    
    @Autowired
    private Mapper dozerBeanMapper;

    public ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput getElencoTassonomiaPerCodiceTipoEnteCreditore ( ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input ) {
    	ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput output= new ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput();
    	try {
    		
    		try {
    			Assert.notNull ( input, "Oggetto di input non valorizzato." );
    			Assert.notNull ( input.getCaller(), "Oggetto di input non valorizzato." );
    			Assert.notNull ( input.getCaller().getPrincipal(), "Oggetto di input non valorizzato." );
    			Assert.notNull ( input.getCaller().getPrincipal().getCodiceEnte(), "Oggetto di input non valorizzato." );
    		} catch ( IllegalArgumentException e1 ) {
    			output.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
    			output.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
    			return output;
    		}
    		Ente ente= enteRepository.findOneByCodiceFiscale(input.getCaller().getPrincipal().getCodiceEnte());
    		List<Tassonomia> listEntity = repository.findByCodTipoEnteCreditore(ente.getCodiceTipoEnteCreditore());

    		List<TassonomiaOutputDto> listDto = new LinkedList<TassonomiaOutputDto>();
    		output.setElencoTassonomia ( listDto );
    		
    		if ( !CollectionUtils.isEmpty ( listEntity ) )
    		{

    			for (Tassonomia entity : listEntity)
    			{
    				listDto.add(dozerBeanMapper.map ( entity, TassonomiaOutputDto.class )) ;
    			}

    			output.setCodiceEsito ( CodiciEsito.ESITO_OK.getCodice () );
    			output.setDescrizioneEsito ( CodiciEsito.ESITO_OK.getMessaggio () );
    		}
    		else
    		{
    			output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
    			output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
    		}
    	} catch (Exception e) {
    		logger.error ( CodiciEsito.DATI_NON_TROVATI, e );
    		output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
    		output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
    	}
    	return output;
    }
    
     
    
    
}
