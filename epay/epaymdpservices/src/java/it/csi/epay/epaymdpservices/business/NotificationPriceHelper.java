/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymdpservices.business;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.util.CollectionUtils;
import org.springframework.util.StringUtils;

import it.csi.epay.epayservices.model.ComponentiImporto;
import it.csi.epay.epayservices.model.ComponentiImportoSecondario;
import it.csi.epay.epayservices.model.NotificationPriceOutput;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PersonaFisica;
import it.csi.epay.epayservices.model.PersonaGiuridica;
import it.csi.epay.epayservices.model.RiferimentiPagamento;
import it.csi.epay.epayservices.model.SoggettoPagatore;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput;

public class NotificationPriceHelper {

	public static AggiornaPosizioneDebitoriaChiamanteEsternoInput getAggiornaPosizioneDebitoriaChiamanteEsternoInput(Pagamento pagamento, NotificationPriceOutput rispostaNotificationPrice) {
		Date dataOdierna = new Date();
		AggiornaPosizioneDebitoriaChiamanteEsternoInput input = new AggiornaPosizioneDebitoriaChiamanteEsternoInput();
		input.setTimestampChiamata(dataOdierna);
		input.setIpChiamante(pagamento.getTipoPagamento().getUrlAttualizzazionePnd()!=null?pagamento.getTipoPagamento().getUrlAttualizzazionePnd().substring(0, 50):"127.0.0.1"); 
		input.setCodiceChiamante(pagamento.getTipoPagamento().getCredenzialiPnd()!=null?new String(pagamento.getTipoPagamento().getCredenzialiPnd(), StandardCharsets.UTF_8):"attualizzazioneImporto"); 
		input.setIuv(pagamento.getIuv());
		input.setCodiceFiscaleEnte(pagamento.getEnte().getCodiceFiscale());
		input.setTipoPagamento(pagamento.getTipoPagamento().getCodiceVersamento());
		input.setDataScadenza(pagamento.getDataScadenza());
		input.setDataInizioValidita(pagamento.getInizioValidita());
		input.setDataFineValidita(pagamento.getFineValidita());
		input.setImportoTotale(pagamento.getImporto());
		if (!org.springframework.util.CollectionUtils.isEmpty ( rispostaNotificationPrice.getComponentiPagamento () ) )
		{
		    input.setComponentiImporto(composizioneComponenti(pagamento, rispostaNotificationPrice));
		}
		
		input.setDescrizioneCausaleVersamento(pagamento.getCausale());
		input.setMotivazione(pagamento.getNote());
		input.setRiferimentiPagamento(new ArrayList<RiferimentiPagamento>());
		input.setIdentificativoPagamento(""+pagamento.getIdPagamento());
		input.setSoggettoPagatore(new SoggettoPagatore());
		input.getSoggettoPagatore().setEmail(pagamento.getPagatore().getEmail());
		input.getSoggettoPagatore().setIdentificativoUnivocoFiscale(pagamento.getPagatore().getCodiceFiscale());
		if (StringUtils.hasText(pagamento.getPagatore().getRagioneSociale())) {
			input.getSoggettoPagatore().setPersonaGiuridica(new PersonaGiuridica());
			input.getSoggettoPagatore().getPersonaGiuridica().setRagioneSociale(pagamento.getPagatore().getRagioneSociale());
		} else if (StringUtils.hasText(pagamento.getPagatore().getCognome()) || StringUtils.hasText(pagamento.getPagatore().getNome())) {
			input.getSoggettoPagatore().setPersonaFisica(new PersonaFisica());
			input.getSoggettoPagatore().getPersonaFisica().setNome(pagamento.getPagatore().getNome());
			input.getSoggettoPagatore().getPersonaFisica().setCognome(pagamento.getPagatore().getCognome());
		}
		return input;
	}
	
	public static AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput getAggiornaPosizioneDebitoriaMultibeneficiario(Pagamento pagamento, NotificationPriceOutput rispostaNotificationPrice) {
		
		Date dataOdierna = new Date();
		
		AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput input = new AggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiarioInput();
		
		input.setCodiceChiamante(pagamento.getTipoPagamento().getCredenzialiPnd()!=null?new String(pagamento.getTipoPagamento().getCredenzialiPnd(), StandardCharsets.UTF_8):"attualizzazioneImporto");
		input.setCodiceFiscaleEnte(pagamento.getEnte().getCodiceFiscale());
		if (!org.springframework.util.CollectionUtils.isEmpty ( rispostaNotificationPrice.getComponentiPagamento() ))
		{
		    input.setComponentiImporto(composizioneComponenti(pagamento, rispostaNotificationPrice));
		}
		
		if (!org.springframework.util.CollectionUtils.isEmpty (rispostaNotificationPrice.getComponentiPagamentoEntiSecondari()))
        {
		    input.setComponentiImportoSecondario(composizioneComponentiMultibeneficiario(pagamento, rispostaNotificationPrice));
        }
		
		
		input.setDataFineValidita(pagamento.getFineValidita());
		input.setDataInizioValidita(pagamento.getInizioValidita());
		input.setDataScadenza(pagamento.getDataScadenza());
		input.setDescrizioneCausaleVersamento(pagamento.getCausale());
		input.setImportoPrincipale(rispostaNotificationPrice.getImportoTotaleEntePrimario());
		input.setImportoSecondario(rispostaNotificationPrice.getImportoTotaleEntiSecondari());
		input.setImportoTotale(pagamento.getImporto());
		input.setIpChiamante(pagamento.getTipoPagamento().getUrlAttualizzazionePnd()!=null?pagamento.getTipoPagamento().getUrlAttualizzazionePnd().substring(0, 50):"127.0.0.1");
		input.setIuv(pagamento.getIuv());
		input.setMotivazione(pagamento.getNote());
		
		input.setRiferimentiPagamento(new ArrayList<RiferimentiPagamento>());
		input.setSoggettoPagatore(new SoggettoPagatore());
		input.getSoggettoPagatore().setEmail(pagamento.getPagatore().getEmail());
		input.getSoggettoPagatore().setIdentificativoUnivocoFiscale(pagamento.getPagatore().getCodiceFiscale());
		
		if (StringUtils.hasText(pagamento.getPagatore().getRagioneSociale())) {
			input.getSoggettoPagatore().setPersonaGiuridica(new PersonaGiuridica());
			input.getSoggettoPagatore().getPersonaGiuridica().setRagioneSociale(pagamento.getPagatore().getRagioneSociale());
		} else if (StringUtils.hasText(pagamento.getPagatore().getCognome()) || StringUtils.hasText(pagamento.getPagatore().getNome())) {
			input.getSoggettoPagatore().setPersonaFisica(new PersonaFisica());
			input.getSoggettoPagatore().getPersonaFisica().setNome(pagamento.getPagatore().getNome());
			input.getSoggettoPagatore().getPersonaFisica().setCognome(pagamento.getPagatore().getCognome());
		}
		
		input.setTimestampChiamata(dataOdierna);
		input.setTipoPagamento(pagamento.getTipoPagamento().getCodiceVersamento());
		
		return input;
	}

	private static List<ComponentiImporto> composizioneComponenti(Pagamento pagamento,NotificationPriceOutput rispostaNotificationPrice) {
		
		List<ComponentiImporto> elencoComponenti = new ArrayList<ComponentiImporto>(rispostaNotificationPrice.getComponentiPagamento().size());
		
		for (Integer progressivo = 0; progressivo < rispostaNotificationPrice.getComponentiPagamento().size(); progressivo ++) {
			
			ComponentiImporto componente = new ComponentiImporto();
			componente.setAnnoAccertamento(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getAnnoAccertamento());
			componente.setCausale(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getCausale());
			componente.setCausaleDescrittiva(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getCausale());
			componente.setDatiSpecificiRiscossione(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getCausale());
			componente.setImporto(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getImporto());
			componente.setNumeroAccertamento(rispostaNotificationPrice.getComponentiPagamento().get(progressivo).getNumeroAccertamento());
			componente.setProgressivo(progressivo);
			
			elencoComponenti.add(componente);
		}
	
		return elencoComponenti;
	}
	

	private static List<ComponentiImportoSecondario> composizioneComponentiMultibeneficiario(Pagamento pagamento,
			NotificationPriceOutput rispostaNotificationPrice) {
		
		List<ComponentiImportoSecondario> elencoComponenti = new ArrayList<ComponentiImportoSecondario>(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().size());
		
		for (Integer progressivo = 0; progressivo < rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().size(); progressivo ++) {
			
			ComponentiImportoSecondario componente = new ComponentiImportoSecondario();
			componente.setAnnoAccertamento(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getAnnoAccertamento());
			componente.setCausale(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getCausale());
			componente.setCodiceFiscaleEnte(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getCodiceFiscaleEnte());
			componente.setDatiSpecificiRiscossione(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getCausale());
			componente.setImporto(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getImporto());
			componente.setNumeroAccertamento(rispostaNotificationPrice.getComponentiPagamentoEntiSecondari().get(progressivo).getNumeroAccertamento());
			componente.setProgressivo(progressivo);
			
			elencoComponenti.add(componente);
		}
		
		return elencoComponenti;
	}
}
