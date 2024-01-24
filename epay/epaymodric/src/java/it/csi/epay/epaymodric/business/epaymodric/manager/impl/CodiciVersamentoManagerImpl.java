/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import static it.csi.epay.epaymodric.business.epaymodric.repository.custom.CustomSpecifications.codiciAssociatiAFlussi;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodric.business.epaymodric.manager.CodiciVersamentoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.EsitoManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiceVersamentoRepository;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOCodiceVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori; 

@Service
public class CodiciVersamentoManagerImpl implements CodiciVersamentoManager {

	private final Logger logger = LogManager.getLogger (this.getClass());
	
	@Autowired
	private CodiceVersamentoRepository repository;

	@Autowired
	private EsitoManager esitoManager;
	
    @Override
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice ( DTOInputWsRicercaCodiciVersamento input ) {

        DTOOutputWsCodiciVersamento ret = new DTOOutputWsCodiciVersamento ();
        if ( null != input && !CollectionUtils.isEmpty ( input.getCodiciVersamento () )  && 
        		null !=  input.getCaller() && null != input.getCaller().getPrincipal() && 
        		 null != input.getCaller().getPrincipal().getCodiceEnte()) {
            ret.setCodiciVersamento ( repository.findByCodiceVersamentoInAndCodiceFiscaleEnteOrderByCodiceVersamentoAsc 
            		( input.getCodiciVersamento (),input.getCaller().getPrincipal().getCodiceEnte() ).parallelStream ()
                .map ( new Function<CblTCodiceVersamento, DTOCodiceVersamento> () {

                    @Override
                    public DTOCodiceVersamento apply ( CblTCodiceVersamento cod ) {
                        return DTOCodiceVersamento.builder ()
                            .withId ( cod.getId () )
                            .withCodice ( cod.getCodiceVersamento () )
                            .withDenominazione ( cod.getDescrizioneVersamento () )
                            .build ();
                    }
                } ).collect ( Collectors.toCollection ( new Supplier<LinkedList<DTOCodiceVersamento>> () {

                    @Override
                    public LinkedList<DTOCodiceVersamento> get () {
                        return new LinkedList<> ();
                    }
                } ) ) );
        }
        return ret;
    }

	@Override
	public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento(
			DTOInputWsRicercaCodiciVersamento ricercaCodiciVersamento) {
		
		List<DTOOutputWsCodiciVersamento> codici = new LinkedList<>();
		DTOOutputWsEsito esito = esitoManager.getEsito( CostantiErrori.WS_ESITO_OK_DEFAULT );
		if ( null !=  ricercaCodiciVersamento.getCaller() && null != ricercaCodiciVersamento.getCaller().getPrincipal() && 
				null != ricercaCodiciVersamento.getCaller().getPrincipal().getCodiceEnte()) {
			
			DTOOutputWsCodiciVersamento opzioni = new DTOOutputWsCodiciVersamento();
			try {
//				opzioni.setCodiciVersamento ( repository.findByCodiceFiscaleEnteAssociatiAFlussi( ricercaCodiciVersamento.getCaller ().getPrincipal ().getCodiceEnte () )
						opzioni.setCodiciVersamento ( repository.findByCodiceFiscaleEnteOrderByCodiceVersamentoAsc( ricercaCodiciVersamento.getCaller ().getPrincipal ().getCodiceEnte () )
						/*repository.findAll( codiciAssociatiAFlussi ( ricercaCodiciVersamento.getCaller ().getPrincipal ().getCodiceEnte () ) )*/
						.stream ().map(new Function<CblTCodiceVersamento, DTOCodiceVersamento> () {
							
							@Override
		                    public DTOCodiceVersamento apply ( CblTCodiceVersamento cod ) {
		                        return DTOCodiceVersamento.builder ()
		                            .withId ( cod.getId () )
		                            .withCodice ( cod.getCodiceVersamento () )
		                            .withDenominazione ( cod.getDescrizioneVersamento () )
		                            .build ();
		                    }
							
						} ).collect ( Collectors.toCollection ( new Supplier<LinkedList<DTOCodiceVersamento>> () {
	
		                    @Override
		                    public LinkedList<DTOCodiceVersamento> get () {
		                        return new LinkedList<> ();
		                    }
		                } ) ) );
			}catch(Exception e) {
				logger.error ( "CodiciVersamentoManagerImpl.RicercaCodiciVersamento: errore " + e.getMessage () );
                esito = esitoManager.getEsito ( CostantiErrori.ERRORE_DI_SISTEMA );
			}
			opzioni.setEsito ( esito );
			codici.add(opzioni);
		}
		return codici;
	}
}
