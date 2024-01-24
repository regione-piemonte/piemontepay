/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.TassonomiaManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.TassonomiaDto;
import it.csi.epay.epaypacatalogweb.integration.rs.services.TassonomiaService;
import it.csi.epay.epaypacatalogweb.model.tassonomia.RicercaTassonomiaFiltroVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;


@Service
public class TassonomiaManagerImpl implements TassonomiaManager {
	
//	 private Logger logger = LoggerFactory.getLogger ( TassonomiaManager.class );
	@Autowired
	private TassonomiaService service;
	
	 private final static String CODICE_OK = "OK";
	
	
	@Override
	public List<TassonomiaVO> getElencoTassonomiaPerCodiceTipoEnteCreditore() {
		
		List<TassonomiaVO> listTassonomiaVo= new LinkedList<TassonomiaVO>();
		ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input = new ElencoTassonomiaPerCodiceTipoEnteCreditoreInput();
		ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput  output= service.getElencoTassonomiaPerCodiceTipoEnteCreditore(input);
		try {
			for (TassonomiaDto temp: output.getElencoTassonomia())
			{
				TassonomiaVO tassonomia= new TassonomiaVO();
				PropertyUtils.copyProperties ( tassonomia, temp);
				listTassonomiaVo.add(tassonomia);
			}
			

			
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della richiesta" );
		}
		
		return listTassonomiaVo;
		
		
	}


	@Override
	public List<TassonomiaVO> ricercaTassonomia(RicercaTassonomiaFiltroVO input) throws OperationFailedException {

		List<TassonomiaVO> listTassonomiaVo= new LinkedList<TassonomiaVO>();
		RicercaTassonomiaInput mapped = new RicercaTassonomiaInput();
		 RicercaTassonomiaOutput   output;
		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della richiesta" );
		}
		
		 try {
			   output= service.ricercaTassonomia(mapped);
	        } catch ( Exception e ) {
	            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
	        }

	        if ( !CODICE_OK.equals ( output.getCodiceEsito () ) ) {
	            throw new OperationFailedException ( output.getDescrizioneEsito () );
	        }
		

		try {
			
			for (TassonomiaDto temp:  output.getRisultati())
			{
				TassonomiaVO tassonomia= new TassonomiaVO();
				PropertyUtils.copyProperties ( tassonomia, temp);
				tassonomia.setDesMotivoGiuridicoRiscossione(getDesMotivoGiuridicoRiscossione(tassonomia.getMotivoGiuridicoRiscossione()));
				listTassonomiaVo.add(tassonomia);
			}
			
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}
		return listTassonomiaVo;

	}


	private String getDesMotivoGiuridicoRiscossione(String motivoGiuridicoRiscossione) {
		
		switch (motivoGiuridicoRiscossione)
		{
		case "IM": 
			return "Imposta";
		case "TS": 
			return "Tassa";
		case "SP": 
			return "Servizio Pubblico";
		case "SA": 
			return "Sanzioni";
		case "AP": 
			return "Altri Pagamenti";
		default:
			return "";
		}
		
		
	}
	
	
	
	

  
}
