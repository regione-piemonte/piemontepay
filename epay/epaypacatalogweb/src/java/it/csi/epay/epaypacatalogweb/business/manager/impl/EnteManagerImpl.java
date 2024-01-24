/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import it.csi.epay.epaypacatalogweb.business.manager.EnteManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.mapper.GestioneEntiMapper;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaEnteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaEnteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.DecodificaOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiConRiferimentoContabileSecondarioInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiConRiferimentoContabileSecondarioOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.RicercaEnteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.RisultatoRicercaEnteVO;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnteManagerImpl implements EnteManager {

	private final static String CODICE_OK = "OK";

	@Autowired
	private EpaypacatalogsrvFacade facade;

	public EnteManagerImpl() {

	}

	@Override
	public List<RisultatoRicercaEnteVO> ricercaEnti(RicercaEnteFiltroVO input) throws OperationFailedException {

        throw new NotImplementedException ( "NON IMPLEMENTATO" );

	}

	@Override
	public EnteVO getEnteById(Long input) throws OperationFailedException {
		GetEnteOutput rawOutput;

		try {
			GetEnteInput getEnteInput = new GetEnteInput();
			getEnteInput.setId(input);
			rawOutput = facade.getEnte(getEnteInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(rawOutput.getCodiceEsito())) {
			throw new OperationFailedException(rawOutput.getDescrizioneEsito());
		}

		return GestioneEntiMapper.map(rawOutput.getEnte());
	}

	@Override
	public void salvaEnte(ModificaEnteVO input) throws OperationFailedException {
		AggiornaEnteOutput rawOutput = null;

		try {
			AggiornaEnteInput mappedInput = GestioneEntiMapper.map(input);

			rawOutput = facade.aggiornaEnte(mappedInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(rawOutput.getCodiceEsito())) {
			throw new OperationFailedException(rawOutput.getDescrizioneEsito());
		}
	}
	
	
	  @Override
	    public List<EnteVO> getEntiConRiferimentoContabileSecondario () {
		  GetEntiConRiferimentoContabileSecondarioOutput output;
	        try {
	        	GetEntiConRiferimentoContabileSecondarioInput input = new GetEntiConRiferimentoContabileSecondarioInput ();
	        	output = facade.getEntiConRiferimentoContabileSecondario ( input );
	        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
	            throw new RuntimeException(e);
	        }

	        List<EnteVO> o = new ArrayList<>();
	        for (DecodificaOutputDto opziome : output.getOpzioni()) {
	        	EnteVO ente = new EnteVO();
	        	ente.setId(opziome.getId());
	        	ente.setDescrizione(opziome.getDescrizione());
	        	o.add(ente);
	            
	        }
	        return o;
	    }

}
