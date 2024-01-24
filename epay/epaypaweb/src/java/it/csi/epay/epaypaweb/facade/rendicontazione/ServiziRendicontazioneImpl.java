/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.facade.rendicontazione;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.exception.BusinessNotFoundException;
import it.csi.epay.epaypaweb.exception.BusinessTrasmettiFlussoCorpoNullException;
import it.csi.epay.epaypaweb.exception.BusinessTrasmettiFlussoExistsException;
import it.csi.epay.epaypaweb.exception.BusinessTrasmettiFlussoTestataNullException;
import it.csi.epay.epaypaweb.facade.EPaypaFacadeBase;
import it.csi.epay.epaypaweb.facade.EPaypaFacadeValidator;
import it.csi.epay.epaypaweb.facade.dto.common.ResponseType;
import it.csi.epay.epaypaweb.facade.rendicontazione.dto.TrasmettiFlussoRendicontazioneRequestType;
import it.csi.epay.epaypaweb.facade.rendicontazione.service.ServiziRendicontazione;


@Stateless
@WebService(name = "ServiziRendicontazione", portName = "ServiziRendicontazioneSOAP", targetNamespace = "http://www.csi.it/epay/epaywso/rendicontazione")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiziRendicontazioneImpl extends EPaypaFacadeBase implements ServiziRendicontazione {
	static private final String CLASSNAME = ServiziRendicontazioneImpl.class.getSimpleName();

	static private final String ERR_APP_101 = "101";
	static private final String ERR_APP_112 = "112";
	static private final String ERR_APP_113 = "113";
	static private final String ERR_SYS_200 = "200";

	@EJB
	private GestioneFlussiBusiness business;

	@Override
	public ResponseType trasmettiFlussoRendicontazione(TrasmettiFlussoRendicontazioneRequestType requestType) {
		String methodName = "trasmettiFlussoRendicontazione";
		

		ResponseType responseType = new ResponseType();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			// verifica
			EPaypaFacadeValidator.notNull("TrasmettiFlussoRendicontazioneRequestType", requestType);

			// business
			FlussoCompletoDto<RiversamentoDto> dto = toFlussoRendicontazioneDto(requestType);
			business.trasmettiFlussoRendicontazione(dto);

			// response ok
			responseType.setResult(buildResultOK());

		} catch (BusinessTrasmettiFlussoTestataNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Testata non valorizzata"));

		} catch (BusinessTrasmettiFlussoCorpoNullException e) {
			responseType.setResult(buildResult(ERR_APP_101, "Corpo non valorizzato"));

		} catch (BusinessTrasmettiFlussoExistsException e) {
			responseType.setResult(
					buildResult(ERR_APP_112, "Id Messaggio: " + e.getIdMessaggio() + ", Cod. Fiscale Ente: " + e.getCodFiscaleEnte() + ", Tipo flusso: " + e.getTipoFlusso()));

		} catch (BusinessNotFoundException e) {
			responseType.setResult(buildResult(ERR_APP_113, e.getObjName() + " non trovato per " + e.getKeyName() + ": " + e.getCod()));

		} catch (Throwable e) {
			responseType.setResult(buildResult(ERR_SYS_200, e));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return responseType;
	}

}
