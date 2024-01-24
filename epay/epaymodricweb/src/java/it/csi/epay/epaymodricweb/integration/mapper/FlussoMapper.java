/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.mapper;

import java.text.ParseException;

import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoDettaglio;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoSintesi;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoStatoFlusso;
import it.csi.epay.epaymodricweb.model.flusso.FlussoDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoOrigineVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoSintesiVO;

public abstract class FlussoMapper extends ParentMapper {

    public static FlussoDettaglioVO parseFlussoDettaglioVO(DtoFlussoDettaglio dto) throws ParseException {
        FlussoDettaglioVO mapped = new FlussoDettaglioVO();

        mapped.setAnagraficaPagatore(cleanString(dto.getAnagraficaPagatore()));
        mapped.setCausale(cleanString(dto.getCausale()));
        mapped.setCodiceFiscalePagatore(cleanString(dto.getCodiceFiscalePagatore()));
        mapped.setCodiceVersamento(cleanString(dto.getCodiceVersamento()));
        mapped.setDataPagamento(parseDateFromString(dto.getDataPagamento()));
        mapped.setDatiSpecificiDiRiscossione(cleanString(dto.getDatiSpecificiDiRiscossione()));
        mapped.setDescrizioneVersamento(cleanString(dto.getDescrizioneVersamento()));
        mapped.setEsitoPagamento(cleanString(dto.getEsitoPagamento()));
        mapped.setId(parseLongFromString(dto.getId()));
        mapped.setIdentificativoUnicoRiscossione(cleanString(dto.getIdentificativoUnicoRiscossione()));
        mapped.setIdentificativoUnicoVersamento(cleanString(dto.getIdentificativoUnicoVersamento()));
        mapped.setIdTransazione(cleanString(dto.getIdTransaction()));
        mapped.setImportoSingoloVersamento(parseDoubleFromCurrencyString(dto.getImportoSingoloVersamento()));
        mapped.setIndiceSingoloVersamento(parseIntegerFromString(dto.getIndiceSingoloVersamento()));
        mapped.setStatoInvioFruitore(cleanString(dto.getStatoInvioFruitore()));

        return mapped;
    }

    public static FlussoSintesiVO parseFlussoSintesiVO(DtoFlussoSintesi dto) {
        FlussoSintesiVO mapped = new FlussoSintesiVO();

        mapped.setCodiceVersamento(cleanString(dto.getCodiceVersamento()));
        mapped.setDatiSpecificiDiRiscossione(cleanString(dto.getDatiSpecificiDiRiscossione()));
        mapped.setId(parseLongFromString(dto.getId()));
        mapped.setIdentificativoFlusso(cleanString(dto.getIdentificativoFlusso()));
        mapped.setImportoQuotaAggregazione(parseDoubleFromCurrencyString(dto.getImportoQuotaAggregazione()));
        mapped.setNumeroVersamentoQuotaAggregazione(parseIntegerFromString(dto.getNumeroVersamentoQuotaAggregazione()));
        mapped.setProvvisorioAnno(parseIntegerFromString(dto.getProvvisorioAnno()));
        mapped.setProvvisorioNumero(parseIntegerFromString(dto.getProvvisorioNumero()));
        mapped.setDescrizioneVersamento ( cleanString ( dto.getDescrizioneVersamento () ) );
        mapped.setAnnoAccertamento(parseIntegerFromString(dto.getAnnoAccertamento()));
        mapped.setNumeroAccertamento(parseIntegerFromString(dto.getNumeroAccertamento()));

        return mapped;
    }

    public static FlussoOrigineVO parseFlussoOrigineVO(DtoFlussoOrigine dto) throws ParseException {
        if (dto == null) {
            return null;
        }

        FlussoOrigineVO mapped = new FlussoOrigineVO();

        mapped.setContatoreTentativi((dto.getContatoreTentativi()));
        mapped.setDataInserimento(parseDateFromXmlGregorianCalendar(dto.getDataInserimento()));
        mapped.setDataOraFlusso(parseDateFromXmlGregorianCalendar(dto.getDataOraFlusso()));
        mapped.setId(parseLongFromString(dto.getId()));
        mapped.setIbanRiversamentoFlusso(cleanString(dto.getIbanRiversamentoFlusso()));
        mapped.setIdentificativoFlusso(cleanString(dto.getIdentificativoFlusso()));
        mapped.setIdentificativoIstitutoRicevente (
            cleanString ( String.join ( " - ", dto.getIdentificativoIstitutoRicevente (), dto.getIdentificativoIstitutoRiceventeDescrizione () ) ) );
        mapped.setIdentificativoPsp ( cleanString ( String.join ( " - ", dto.getIdentificativoPsp (), dto.getIdentificativoPspDescrizione () ) ) );
        mapped.setImportoTotalePagamenti(parseDoubleFromBigDecimal(dto.getImportoTotalePagamenti()));
        mapped.setNumeroTotalePagamenti((dto.getNumeroTotalePagamenti()));
        mapped.setFlussoPlurintermediato((null!=dto.getFlagFlussoIntermediato() && dto.getFlagFlussoIntermediato())? Constants.FLUSSO_INTERMEDIATO_PRESENTE:"");

        DtoStatoFlusso dtoStato = dto.getStatoFlusso();
        if (dtoStato != null) {
            mapped.setIdStato(parseLongFromString(dtoStato.getId()));
            mapped.setCodiceStato(dtoStato.getCodiceStato());
            mapped.setDescrizioneStato(dtoStato.getDescrizioneStato());
            mapped.setPermettiRielaborazione ( dtoStato.isPermettiRielaborazione () );
        }

        return mapped;
    }

}
