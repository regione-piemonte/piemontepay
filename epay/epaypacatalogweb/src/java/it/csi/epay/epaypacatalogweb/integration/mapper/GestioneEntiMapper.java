/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaEnteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteOutputCodiceVersamentoAssociatoDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteOutputDto;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

public abstract class GestioneEntiMapper extends ParentMapper {

	public static AggiornaEnteInput map(ModificaEnteVO input) {
		if (input == null) {
			return null;
		}

		AggiornaEnteInput output = new AggiornaEnteInput();

		output.setCap(input.getCap());
		output.setCodiceModalitaAcquisizioneProvvisori(input.getCodiceModalitaAcquisizioneProvvisori());
		output.setCodicePeriodicitaSchedulazioneRiconciliazione(
				input.getCodicePeriodicitaSchedulazioneRiconciliazione());
		output.setCodiceTipologiaAccertamento(input.getCodiceTipologiaAccertamento());
		output.setDenominazione(input.getDenominazione());
		output.setFlagAccertamento(input.getFlagAccertamento());
		output.setFlagEntePlurintermediato(input.getFlagEntePlurintermediato());
		output.setFlagQualsiasiCodiceVersamento(input.getFlagQualsiasiCodiceVersamento());
		output.setFlagRicezioneErrori(input.getFlagRicezioneErrori());
		output.setFlagAdesioneCittaFacile ( input.getFlagAdesioneCittaFacile () );
		output.setFlagRicezioneFlussoBaseRendicontazione(input.getFlagRicezioneFlussoBaseRendicontazione());
		//FIX-INTEGRAZIONE output.setCodiceModalitaIntegrazione ( input.getCodiceModalitaIntegrazione () );
		output.setFlagRiconciliazioneVersamenti(input.getFlagRiconciliazioneVersamenti());
		output.setGiornoSchedulazione(input.getGiornoSchedulazione() != null && !input.getGiornoSchedulazione().isEmpty() ?
				Integer.valueOf(input.getGiornoSchedulazione()) : null);
		output.setId(input.getId());
		output.setIndirizzo(input.getIndirizzo());
		output.setLocalita(input.getLocalita());
		output.setLogoContent(input.getLogoContent());
		output.setLogoFileName(input.getLogoFileName());
		output.setLogoFileSize(input.getLogoFileSize());
		output.setSiglaProvincia(input.getSiglaProvincia());
		output.setCancellaLogo(input.getCancellaLogo());
		output.setCodiceIstat ( input.getCodiceIstat () );
		output.setTemplateEmailId ( input.getTemplateEmailId () );
		output.setUrlDominio ( input.getUrlDominio () );
		output.setCodiceIpa  ( input.getCodiceIpa () );

        output.setCbill ( input.getCbill () );
        output.setCivico ( input.getCivico () );
        output.setEmail ( input.getEmail () );
        output.setGs1Gln ( input.getGs1Gln () );
        output.setIban ( input.getIban () );
        output.setBic(input.getBic());

		output.getIdCodiciVersamentoAssociati().clear();
		if (input.getIdCodiciVersamentoSelezionati() != null) {
			for (Entry<Long, Integer> associazione : input.getIdCodiciVersamentoSelezionati().entrySet()) {
				if (associazione.getValue() != null && associazione.getValue() > 0) {
					output.getIdCodiciVersamentoAssociati().add(associazione.getKey());
				}
			}
		}

		if (input.getCancellaLogo() == null || !input.getCancellaLogo()) {
			if (input.getNewLogo() != null && input.getNewLogo().getSize() > 0) {
				try {
					output.setLogoContent(input.getNewLogo().getBytes());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				output.setLogoFileName(input.getNewLogo().getOriginalFilename());
				output.setLogoFileSize(new Long(input.getNewLogo().getSize()).intValue());
				output.setLogoContentType(input.getNewLogo().getContentType());
			}
		}

		return output;
	}

	public static EnteVO map(GetEnteOutputDto input) {
		if (input == null) {
			return null;
		}

		EnteVO output = new EnteVO();

		output.setCap(input.getCap());
		output.setCodiceFiscale(input.getCodiceFiscale());
        //output.setCodiceFiscaleUtenteVariazione(input.getCodiceFiscaleUtenteVariazione());
		output.setCodiceModalitaAcquisizioneProvvisori(input.getCodiceModalitaAcquisizioneProvvisori());
		output.setCodicePeriodicitaSchedulazioneRiconciliazione(
				input.getCodicePeriodicitaSchedulazioneRiconciliazione());
		output.setCodiceStatoAggiornamento(input.getCodiceStatoAggiornamento());
		output.setCodiceTipologiaAccertamento(input.getCodiceTipologiaAccertamento());
        //output.setDataVariazione(calendarToLocalDateTime(input.getDataVariazione()));
		output.setDenominazione(input.getDenominazione());
		output.setDescrizioneStatoAggiornamento(input.getDescrizioneStatoAggiornamento());
		output.setDescrizioneUtenteAmministratore(input.getDescrizioneUtenteAmministratore());
		output.setFlagAccertamento(input.isFlagAccertamento());
		output.setFlagEntePlurintermediato(input.isFlagEntePlurintermediato());
		output.setFlagQualsiasiCodiceVersamento(input.isFlagQualsiasiCodiceVersamento());
		output.setFlagRicezioneErrori(input.isFlagRicezioneErrori());
		output.setFlagAdesioneCittaFacile ( input.isFlagAdesioneCittaFacile () );
		output.setFlagRicezioneFlussoBaseRendicontazione(input.isFlagRicezioneFlussoBaseRendicontazione());
		//FIX-INTEGRAZIONE output.setCodiceModalitaIntegrazione ( input.getCodiceModalitaIntegrazione () );
		output.setFlagRiconciliazioneVersamenti(input.isFlagRiconciliazioneVersamenti());
		output.setGiornoSchedulazione(input.getGiornoSchedulazione() != null ? input.getGiornoSchedulazione().toString() : null);
		output.setId(input.getId());
		output.setAdmins ( input.getAdmins ());
		output.setIndirizzo(input.getIndirizzo());
		output.setLocalita(input.getLocalita());
		output.setLogoContent(input.getLogoContent());
		output.setLogoFileName(input.getLogoFileName());
		output.setLogoFileSize(input.getLogoFileSize());
		output.setSiglaProvincia(input.getSiglaProvincia());
        output.setDescrizioneErroreAggiornamento ( input.getDescrizioneErroreAggiornamento () );
        output.setCbill ( input.getCbill () );
        output.setCivico ( input.getCivico () );
        output.setEmail ( input.getEmail () );
        output.setGs1Gln ( input.getGs1Gln () );
        output.setIban ( input.getIban () );
        output.setBic(input.getBic());
        output.setCodiceIstat ( input.getCodiceIstat () );
        output.setTemplateEmailId ( input.getTemplateEmailId () );
        output.setUrlDominio ( input.getUrlDominio () );
        output.setCodiceIpa  ( input.getCodiceIpa () );

        output.setCodiciVersamentoAssociati ( new ArrayList<> () );

        if ( StringUtils.isNotEmpty ( input.getDescrizioneErroreAggiornamento () ) ) {
            output.setDescrizioneStatoAggiornamento ( output.getDescrizioneStatoAggiornamento () + " - " + input.getDescrizioneErroreAggiornamento () );
        }

		if (input.getCodiciVersamentoAssociati() != null) {
			for (GetEnteOutputCodiceVersamentoAssociatoDto cv : input.getCodiciVersamentoAssociati()) {
				output.getCodiciVersamentoAssociati().add(new GenericVO(cv.getId(), cv.getCodice(), cv.getDescrizione()));
			}
		}

		return output;
	}

}
