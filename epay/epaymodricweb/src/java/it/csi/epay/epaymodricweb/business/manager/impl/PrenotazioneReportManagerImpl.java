/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import it.csi.epay.epaymodricweb.business.manager.PrenotazioneReportManager;
import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.mapper.ParentMapper;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;
import it.csi.epay.epaymodricweb.model.report.PrenotazioneRicercaReportFormVO;
import it.csi.epay.epaymodricweb.model.report.RisultatiRicercaReportVO;
import it.csi.epay.epaymodricweb.security.UserDetails;
import it.csi.epay.epaymodricweb.util.DateUtils;
import it.csi.epay.epaymodricweb.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneReportManagerImpl implements PrenotazioneReportManager{

	@Autowired
	EpaymodricWsFacade service;
	
	@Override
	public List<RisultatiRicercaReportVO> ricercaReport() throws OperationFailedException{
		
		try {
			return service.ricercaReport();
		} catch (OperationFailedException | UnrecoverableException_Exception | Exception_Exception
				| EpaymodricException_Exception e) {
			throw new OperationFailedException("errore nella ricerca", e);
			
		}
	}
	
	@Override
	public void inserisciPrenotazioneReport(PrenotazioneRicercaReportFormVO filtro) throws OperationFailedException {
		
		DtoInputWsInserisciPrenotazioneReport input = new DtoInputWsInserisciPrenotazioneReport();
		UserDetails user= SecurityUtils.getUser ();
		input.setIdEnte(String.valueOf(user.getEnte().getId()));
		input.setCodiceFiscaleEnte(user.getEnte().getCodiceFiscale());
		input.setIdUtente(String.valueOf(user.getUtente().getId()));
		input.setCodiceFiscaleUtente(user.getUtente().getCodiceFiscale());
//		 idStato - va valorizzato backend 
//		idfile - da dove ricavo l'id file? per ora null su indicazione 
//		idtipofile excel o csv, al momento non lo valorizzo, dipende dal pulsante che si sceglie
		input.setIdTipoFile(StringUtils.isNotEmpty(filtro.getTipoFile())?filtro.getTipoFile():Constants.TIPO_FILE_XLS);
//		idTipoReport dipende dal radio button, non obbligatorio
		input.setIdTipoReport(Constants.TIPO_REPORT_FLUSSI_COMPLETI);
//		input.setIdTipoReport(filtro.getTipoReport());
		input.setNominativoExport(filtro.getNomeReport());
		input.setIdentificativoFlusso(filtro.getIdentificativoFlusso());
//		  causale provvisorio- devo fare un distinguo con identificativo flusso? 
		input.setIuv(filtro.getIuv());
		input.setIdCodiceVersamento(filtro.getIdCodVersamento());
		input.setIdStatoFlusso(filtro.getStatoFlusso());
		input.setDataElaborazioneDa(DateUtils.
				getXmlGregorianCalendarDate(filtro.getDataElaborazioneDa()));
		input.setDataElaborazioneA(DateUtils.
				getXmlGregorianCalendarDate(filtro.getDataElaborazioneA()));
		input.setDataRicezioneDa(DateUtils.
				getXmlGregorianCalendarDate(filtro.getDataRicezioneDa()));
		input.setDataRicezioneA(DateUtils.
				getXmlGregorianCalendarDate(filtro.getDataRicezioneA()));

		input.setPsp(filtro.getPsp());

		try {
			DtoOutputWsInserisciPrenotazioneReport esito =service.inserisciPrenotazioneReport(input);
			if ("KO".equals(esito.getEsito()))
			{
				throw new OperationFailedException(esito.getDescrizione());
			}
			 
		} catch (UnrecoverableException_Exception | Exception_Exception | EpaymodricException_Exception e) {
			throw new OperationFailedException(e.getMessage(), e);
		}
	}

	@Override
	public FileReportVO aggiornaStato(Long idExport) throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception{
		return getFileVoFromDto(service.aggiornaStato(idExport));
		
	}
	
	public FileReportVO getFileVoFromDto(DtoOutputWsAggiornaStatoReport dto) throws OperationFailedException {
		FileReportVO fileVo = new FileReportVO();
		if(dto.getEsito().equals("SUCCESS")) {
		fileVo.setId(dto.getFile().getId());
		fileVo.setNomeFile(dto.getFile().getNomeFile());
		fileVo.setContenutoFile(dto.getFile().getContenuto());
		fileVo.setDataInserimento(ParentMapper.parseDateFromXmlGregorianCalendar(dto.getFile().getDataInserimento()));
		fileVo.setDataModifica(ParentMapper.parseDateFromXmlGregorianCalendar(dto.getFile().getDataModifica()));
		fileVo.setDataInizioElaborazione(ParentMapper.parseDateFromXmlGregorianCalendar(dto.getFile().getDataInizioElaborazione()));
		return fileVo;
		}else {
			throw new OperationFailedException("Errore nel reperimento del file");
		}
		
	}

}
