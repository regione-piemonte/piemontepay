/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.CodiceVersamentoManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EnteManager;
import it.csi.epay.epaypacatalogweb.business.manager.VoceEntrataManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.mapper.CodiceVersamentoMapper;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoCollegatoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;


/**
 *
 */

@Service
public class CodiceVersamentoManagerImpl implements CodiceVersamentoManager {

	private static final String CODICE_OK = "OK";
	public static final String PRINCIPALE = "Principale";
	public static final String SECONDARIO = "Secondario";

	@Autowired
	private EpaypacatalogsrvFacade facade;

	@Autowired
	private DecodificheManager decodificheManager;

	@Autowired
	private VoceEntrataManager voceEntrataManager;

	@Autowired
	private EnteManager enteManager;

	@Override
	public List<RisultatoRicercaCodiceVersamentoVO> ricercaCodiciVersamento(RicercaCodiceVersamentoFiltroVO filtro) throws OperationFailedException {

		RicercaCodiceVersamentoInput ricercaCodiceVersamentoInput = CodiceVersamentoMapper.map(filtro);
		RicercaCodiceVersamentoOutput serviceOutput;
		try {
			serviceOutput = facade.ricercaCodiceVersamento(ricercaCodiceVersamentoInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}

		List<RisultatoRicercaCodiceVersamentoVO> output = new ArrayList<>();

		for (RicercaCodiceVersamentoOutputDto serviceOutputDto : serviceOutput.getRisultati()) {
			RisultatoRicercaCodiceVersamentoVO mappedDto = CodiceVersamentoMapper.map(serviceOutputDto);
			mappedDto.setBicAppoggio(serviceOutputDto.getBicAppoggio());
			mappedDto.setIbanAppoggio(serviceOutputDto.getIbanAppoggio());
			mappedDto.setFlagCodiceCorrentePostaleAppoggio(serviceOutputDto.getIbanAppoggioPostale());
			mappedDto.setFlagCodiceCorrentePostaleTesoreria(serviceOutputDto.getIbanPostale());
			mappedDto.setFlagPresenzaBollettinoPostale(serviceOutputDto.getFlagPresenzaBollettinoPostale());
			if ( serviceOutputDto.getFlagMbPrimario () != null && serviceOutputDto.getFlagMbPrimario () ) {
				mappedDto.setMultibeneficiario ( PRINCIPALE );
			}
			if ( serviceOutputDto.getFlagMbSecondario () != null && serviceOutputDto.getFlagMbSecondario () ) {
				mappedDto.setMultibeneficiario ( SECONDARIO );
			}
			output.add(mappedDto);
		}

		return output;
	}

	@Override
	public CodiceVersamentoVO getCodiceVersamento(Long id) throws OperationFailedException {
		GetCodiceVersamentoInput getCodiceVersamentoInput = new GetCodiceVersamentoInput();
		getCodiceVersamentoInput.setId(id);

		GetCodiceVersamentoOutput serviceOutput;
		try {
			serviceOutput = facade.getCodiceVersamento(getCodiceVersamentoInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}

		return CodiceVersamentoMapper.map(serviceOutput.getCodiceVersamento());
	}

	@Override
	public InserisciCodiceVersamentoOutput inserisciCodiceVersamento(ModificaCodiceVersamentoVO userInput) throws OperationFailedException {

		InserisciCodiceVersamentoInput serviceInput = CodiceVersamentoMapper.mapInserisci(userInput);

		InserisciCodiceVersamentoOutput serviceOutput;
		try {

			serviceOutput = facade.inserisciCodiceVersamento(serviceInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}

		return serviceOutput;
	}

	@Override
	public AggiornaCodiceVersamentoOutput aggiornaVersamento(ModificaCodiceVersamentoVO userInput) throws OperationFailedException {

		AggiornaCodiceVersamentoInput serviceInput = CodiceVersamentoMapper.mapAggiorna(userInput);

		AggiornaCodiceVersamentoOutput serviceOutput;
		try {

			serviceOutput = facade.aggiornaCodiceVersamento(serviceInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}

		return serviceOutput;
	}

	@Override
	public void eliminaCodiceVersamento(Long id) throws OperationFailedException {

		EliminaCodiceVersamentoInput serviceInput = new EliminaCodiceVersamentoInput();
		serviceInput.setId(id);

		EliminaCodiceVersamentoOutput serviceOutput;
		try {
			serviceOutput = facade.eliminaCodiceVersamento(serviceInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}
	}

	@Override
	public CodiceVersamentoVO mergeInputUtente(Long id, ModificaCodiceVersamentoVO input) throws OperationFailedException {
		return mergeInputUtente(id, input, null);
	}

	@Override
	public CodiceVersamentoVO istanziaNuovo() {
		return new CodiceVersamentoVO();
	}

	@Override
	public CodiceVersamentoVO istanziaNuovoCollegato(Long idCodiceVersamento) throws OperationFailedException {
		CodiceVersamentoVO o = new CodiceVersamentoVO();

		CodiceVersamentoVO padre = getCodiceVersamento(idCodiceVersamento);
		o.setCodiceVersamentoPadre(padre);
		o.setIban(padre.getIban());
		o.setBic(padre.getBic());
		o.setCodiceTipoPagamento ( padre.getCodiceTipoPagamento () );

		return o;
	}

	@Override
	public CodiceVersamentoVO getCodiceVersamentoCollegato(Long idCodiceVersamento, Long id) throws OperationFailedException {
		return getCodiceVersamento(id);
	}

	@Override
	public InserisciCodiceVersamentoOutput inserisciCodiceVersamentoCollegato(Long idCodiceVersamento, ModificaCodiceVersamentoCollegatoVO userInput) throws OperationFailedException {

		InserisciCodiceVersamentoInput serviceInput = CodiceVersamentoMapper.mapInserisci(userInput, idCodiceVersamento);

		InserisciCodiceVersamentoOutput serviceOutput;
		try {
			serviceOutput = facade.inserisciCodiceVersamento(serviceInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}
		
		return serviceOutput;
	}

	@Override
	public void aggiornaCodiceVersamentoCollegato(Long idCodiceVersamento, ModificaCodiceVersamentoCollegatoVO userInput) throws OperationFailedException {

		AggiornaCodiceVersamentoInput serviceInput = CodiceVersamentoMapper.mapAggiorna(userInput);

		AggiornaCodiceVersamentoOutput serviceOutput;
		try {

			serviceOutput = facade.aggiornaCodiceVersamento(serviceInput);
		} catch (Exception e) {
			throw new OperationFailedException("Errore imprevisto nell'esecuzione dell'operazione remota", e);
		}

		if (!CODICE_OK.equals(serviceOutput.getCodiceEsito())) {
			throw new OperationFailedException(serviceOutput.getDescrizioneEsito());
		}
	}

	@Override
	public void eliminaCodiceVersamentoCollegato(Long idCodiceVersamento, Long id) throws OperationFailedException {
		eliminaCodiceVersamento(id);
	}

	@Override
	public CodiceVersamentoVO mergeInputUtenteCollegato(Long idCodiceVersamento, Long id, ModificaCodiceVersamentoCollegatoVO input)
					throws OperationFailedException {

		CodiceVersamentoVO o = id == null ? istanziaNuovoCollegato(idCodiceVersamento) : getCodiceVersamento(id);

		o.setDescrizione(input.getDescrizione());
		o.setIban(input.getIban());
		o.setBic(input.getBic());
		o.setFlagInvioFlussi(input.getFlagInvioFlussi());
		o.setEmail(input.getEmail());
		o.setEnteSecondarioAssociazioneMultibeneficiario ( input.getEnteSecondarioAssociazioneMultibeneficiario () );

		return o;
	}

	@Override
	public CodiceVersamentoVO istanziaNuovo(String codiceVoceEntrata) throws OperationFailedException {
		VoceEntrataPPayVO voceEntrata = voceEntrataManager.getVoceEntrataByCodice(codiceVoceEntrata);
		if (voceEntrata == null) {
			throw new OperationFailedException("Voce di entrata non riconosciuta");
		}

		CodiceVersamentoVO codice = new CodiceVersamentoVO();
		codice.setCodiceMacrotipo(voceEntrata.getCodiceMacrotipo());
		codice.setCodiceTematica(voceEntrata.getCodiceTematica());
		codice.setCodiceVoceEntrata(codiceVoceEntrata);
		codice.setDescrizione(voceEntrata.getDescrizione());
		codice.setDescrizioneMacrotipo(voceEntrata.getDescrizioneMacrotipo());
		codice.setDescrizioneTematica(voceEntrata.getDescrizioneTematica());
		codice.setDescrizioneVoceEntrata(voceEntrata.getDescrizione());
		codice.setIdVoceEntrata(voceEntrata.getId());

		EnteVO ente = enteManager.getEnteById(SecurityUtils.getUser().getEnte().getId());
		codice.setIban(ente.getIban());
		codice.setBic(ente.getBic());

		return codice;
	}

	@Override
	public CodiceVersamentoVO mergeInputUtente(Long id, ModificaCodiceVersamentoVO input, String codiceVoceEntrata) throws OperationFailedException {
		CodiceVersamentoVO o;

		if (id == null) {
			if (codiceVoceEntrata == null) {
				o = istanziaNuovo();
			} else {
				o = istanziaNuovo(codiceVoceEntrata);
			}
		} else {
			o = getCodiceVersamento(id);
		}

		o.setDescrizione(input.getDescrizione());
		o.setIban(input.getIban());
		o.setBic(input.getBic());
		o.setFlagInvioFlussi(input.getFlagInvioFlussi());
		o.setEmail(input.getEmail());
		o.setBicAppoggio(input.getBicAppoggio());
		o.setIbanAppoggio(input.getIbanAppoggio());
		o.setFlagCodiceCorrentePostaleAppoggio(input.getFlagCodiceCorrentePostaleAppoggio());
		o.setFlagCodiceCorrentePostaleTesoreria(input.getFlagCodiceCorrentePostaleTesoreria());
		o.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());
		o.setFattura(input.getFattura());

		if (codiceVoceEntrata == null) {
			if (input.getCodiceVoceEntrata() == null) {
				o.setDescrizioneVoceEntrata(null);
			} else if (!input.getCodiceVoceEntrata().equals(o.getCodiceVoceEntrata())) {
				o.setCodiceVoceEntrata(input.getCodiceVoceEntrata());
				o.setDescrizioneVoceEntrata(null);
				try {
					o.setDescrizioneVoceEntrata(voceEntrataManager.getVoceEntrataByCodice(input.getCodiceVoceEntrata()).getDescrizione());
				} catch (OperationFailedException | RuntimeException e) {
					throw new RuntimeException("Voce entrata non valida");
				}
			}
		}

		//RDI-046
		/*
		 * if ( input.getCodiceTipoPagamento () == null ) { o.setDescrizioneTipoPagamento ( null ); } else
		 */
		if (null != input.getCodiceTipoPagamento() && !input.getCodiceTipoPagamento().equals(o.getCodiceTipoPagamento())) {
			o.setCodiceTipoPagamento(input.getCodiceTipoPagamento());
			o.setDescrizioneTipoPagamento(null);
			for (GenericVO opzione : decodificheManager.getListaOpzioniTipologiaAccertamento()) {
				if (opzione.getCodice().equals(input.getCodiceTipoPagamento())) {
					o.setDescrizioneTipoPagamento(opzione.getDescrizione());
					break;
				}
			}
		}
		
		o.setEnteSecondarioAssociazioneMultibeneficiario(input.getEnteSecondarioAssociazioneMultibeneficiario());
		o.setCovAssociatoAssociazioneMultibeneficiario(input.getCovAssociatoAssociazioneMultibeneficiario());
		
		
		if (null!= input.getModalitaAssociazioneMultibeneficiario())
		{
			o.setFlagElementiMultibeneficiario(new Boolean(true));
		}
		o.setDataInizioValidita ( input.getDataInizioValidita () );
		o.setDataFineValidita ( input.getDataFineValidita () );

		return o;
	}

	
}
