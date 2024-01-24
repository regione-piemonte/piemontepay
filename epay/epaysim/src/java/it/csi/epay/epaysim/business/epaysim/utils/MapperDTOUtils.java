/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.utils;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaysim.business.epaysim.builders.FlussoDettaglioPagopaOutputDTOBuilder;
import it.csi.epay.epaysim.business.epaysim.builders.FlussoOriginePagopaOutputDTOBuilder;
import it.csi.epay.epaysim.business.epaysim.builders.FlussoSintesiPagopaOutputDTOBuilder;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.dto.EsitoDTO;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysim.interfacews.epaysim.CostantiErrori;


/**
 *
 */

public class MapperDTOUtils {

    public static FlussoOriginePagopaOutputDTO trasformaInFlussoOriginePagopaOutputDTO ( SimTFlussoOriginePagopa simTFlussoOriginePagopa ) {
        return new FlussoOriginePagopaOutputDTOBuilder ()
                        .withCfEnteCreditore ( simTFlussoOriginePagopa.getCfEnteCreditore () )
                        .withDataOraMessaggio ( simTFlussoOriginePagopa.getDataOraMessaggio () )
                        .withDataRegolamento ( simTFlussoOriginePagopa.getDataRegolamento () )
                        .withDenominazioneEnte ( simTFlussoOriginePagopa.getDenominazioneEnte () )
                        .withDenominazionePsp ( simTFlussoOriginePagopa.getDenominazionePsp () )
                        .withId ( simTFlussoOriginePagopa.getId () )
                        .withIdentificativoFlusso ( simTFlussoOriginePagopa.getIdentificativoFlusso () )
                        .withIdentificativoPsp ( simTFlussoOriginePagopa.getIdentificativoPsp () )
                        .withIdentificativoUnivocoRegolamento ( simTFlussoOriginePagopa.getIdentificativoUnivocoRegolamento () )
                        .withIdMessaggio ( simTFlussoOriginePagopa.getIdMessaggio () )
                        .withImportoTotalePagamenti ( simTFlussoOriginePagopa.getImportoTotalePagamenti () )
                        .withImportoTotalePagamentiIntermediati ( simTFlussoOriginePagopa.getImportoTotalePagamentiIntermediati () )
                        .withNumeroTotalePagamenti ( simTFlussoOriginePagopa.getNumeroTotalePagamenti () )
                        .withNumeroTotalePagamentiIntermediati ( simTFlussoOriginePagopa.getNumeroTotalePagamentiIntermediati () )
                        .withProvvisorioAnno ( simTFlussoOriginePagopa.getProvvisorioAnno () )
            .withProvvisorioNumero ( simTFlussoOriginePagopa.getProvvisorioNumero () )
                        .withStatoElaborazioneFlusso ( simTFlussoOriginePagopa.getStatoElaborazioneFlusso () )
                        .withUtenteInserimento ( simTFlussoOriginePagopa.getUtenteInserimento () )
                        .withUtenteModifica ( simTFlussoOriginePagopa.getUtenteModifica () )
                        .withDataInserimento ( simTFlussoOriginePagopa.getDataInserimento () )
                        .withDataModifica ( simTFlussoOriginePagopa.getDataModifica () )
                        .build ();
    }

    public static FlussoSintesiPagopaOutputDTO trasformaInFlussoSintesiPagopaOutputDTO ( SimTFlussoSintesiPagopa flussoSintesiPagopa ) {
        return new FlussoSintesiPagopaOutputDTOBuilder ()
                        .withAccertamentoAnno ( flussoSintesiPagopa.getAccertamentoAnno () )
                        .withAccertamentoNumero ( flussoSintesiPagopa.getAccertamentoNumero () )
                        .withArticolo ( flussoSintesiPagopa.getArticolo () )
                        .withCapitolo ( flussoSintesiPagopa.getCapitolo () )
                        .withCodiceVersamento ( flussoSintesiPagopa.getCodiceVersamento () )
                        .withDatiSpecificiDiRiscossione ( flussoSintesiPagopa.getDatiSpecificiDiRiscossione () )
                        .withDescrizioneCodiceVersamento ( flussoSintesiPagopa.getDescrizioneCodiceVersamento () )
                        .withDescrizioneDatiSpecifici ( flussoSintesiPagopa.getDescrizioneDatiSpecifici () )
                        .withId ( flussoSintesiPagopa.getId () )
                        .withImportoQuotaAggregazione ( flussoSintesiPagopa.getImportoQuotaAggregazione () )
                        .withMacrotipo ( flussoSintesiPagopa.getMacrotipo () )
                        .withNumeroPagamentiAggregazione ( flussoSintesiPagopa.getNumeroPagamentiAggregazione () )
                        .withPdc ( flussoSintesiPagopa.getPdc () )
            .withProgressivoFlussoSintetico ( flussoSintesiPagopa.getProgressivoFlussoSintetico () )
                        .withFlussoOriginePagopaOutputDTO ( trasformaInFlussoOriginePagopaOutputDTO ( flussoSintesiPagopa.getSimTFlussoOriginePagopa () ) )
                        .withTematica ( flussoSintesiPagopa.getTematica () )
                        .build ();

    }

    private static List<FlussoSintesiPagopaOutputDTO> mappingSimSintesiToDto ( List<SimTFlussoSintesiPagopa> listSimTFlussoSintesiPagopa ) {
        List<FlussoSintesiPagopaOutputDTO> listaFlussoSintesiPagopaOutputDTO = new ArrayList<FlussoSintesiPagopaOutputDTO> ();
        for ( SimTFlussoSintesiPagopa simTFlussoSintesiPagopa: listSimTFlussoSintesiPagopa ) {
            listaFlussoSintesiPagopaOutputDTO.add ( trasformaInFlussoSintesiPagopaOutputDTO ( simTFlussoSintesiPagopa ) );
        }

        return listaFlussoSintesiPagopaOutputDTO;
    }

    public static FlussoDettaglioPagopaOutputDTO trasformaInFlussoDettaglioPagopaOutputDTO ( SimTFlussoDettaglioPagopa flussoDettaglioPagopa ) {
        return new FlussoDettaglioPagopaOutputDTOBuilder ()
                        .withAnagraficaPagatore ( flussoDettaglioPagopa.getAnagraficaPagatore () )
                        .withCausale ( flussoDettaglioPagopa.getCausale () )
                        .withDataPagamento ( flussoDettaglioPagopa.getDataPagamento () )
                        .withDescrizioneCausaleVersamento ( flussoDettaglioPagopa.getDescrizioneCausaleVersamento () )
                        .withEsitoPagamento ( flussoDettaglioPagopa.getEsitoPagamento () )
                        .withId ( flussoDettaglioPagopa.getId () )
                        .withIdentificativoUnicoRiscossione ( flussoDettaglioPagopa.getIdentificativoUnicoRiscossione () )
                        .withIdentificativoUnicoVersamento ( flussoDettaglioPagopa.getIdentificativoUnicoVersamento () )
                        .withImportoSingoloVersamento ( flussoDettaglioPagopa.getImportoSingoloVersamento () )
                        .withIndiceSingoloVersamento ( flussoDettaglioPagopa.getIndiceSingoloVersamento () )
                        .withFlussoSintesiPagopaOutputDTO ( trasformaInFlussoSintesiPagopaOutputDTO ( flussoDettaglioPagopa.getSimTFlussoSintesiPagopa () ) )
                        .withTransactionid ( flussoDettaglioPagopa.getTransactionid () )
                        .build ();

    }

    private static List<FlussoDettaglioPagopaOutputDTO> mappingSimDettaglioToDto ( List<SimTFlussoDettaglioPagopa> listSimTFlussoDettaglioPagopa ) {
        List<FlussoDettaglioPagopaOutputDTO> listaFlussoDettaglioPagopaOutputDTO = new ArrayList<FlussoDettaglioPagopaOutputDTO> ();
        for ( SimTFlussoDettaglioPagopa simTFlussoDettaglioPagopa: listSimTFlussoDettaglioPagopa ) {
            listaFlussoDettaglioPagopaOutputDTO.add ( trasformaInFlussoDettaglioPagopaOutputDTO ( simTFlussoDettaglioPagopa ) );
        }

        return listaFlussoDettaglioPagopaOutputDTO;
    }

    //Trasforma un SimTFlussoOrigineErrorePagopa in FlussoOrigineErrorePagopaOutputDTO
    //TODO: mettere in un package decodifica
    public static FlussoOrigineErrorePagopaOutputDTO trasformaInFlussoOrigineErrorePagopaOutputDTO ( SimTFlussoOrigineErrore simTFlussoOrigineErrore ) {
        FlussoOrigineErrorePagopaOutputDTO flussoOrigineErrorePagopaOutputDTO = new FlussoOrigineErrorePagopaOutputDTO ();
        try {
            flussoOrigineErrorePagopaOutputDTO.setId ( simTFlussoOrigineErrore.getId () );
            flussoOrigineErrorePagopaOutputDTO.setIdentificativoFlusso ( simTFlussoOrigineErrore.getIdentificativoFlusso () );
            flussoOrigineErrorePagopaOutputDTO.setIdentificativoPsp ( simTFlussoOrigineErrore.getIdentificativoPsp () );
            flussoOrigineErrorePagopaOutputDTO.setNumeroTotalePagamenti ( simTFlussoOrigineErrore.getNumeroTotalePagamenti () );
            flussoOrigineErrorePagopaOutputDTO.setImportoTotalePagamenti ( simTFlussoOrigineErrore.getImportoTotalePagamenti () );
            flussoOrigineErrorePagopaOutputDTO.setIbanRiversamentoFlusso ( simTFlussoOrigineErrore.getIbanRiversamentoFlusso () );
            if ( null != simTFlussoOrigineErrore.getDataoraFlusso () )
                flussoOrigineErrorePagopaOutputDTO.setDataoraFlusso ( simTFlussoOrigineErrore.getDataoraFlusso () );
            if ( null != simTFlussoOrigineErrore.getDataInserimento () )
                flussoOrigineErrorePagopaOutputDTO.setDataInserimento ( simTFlussoOrigineErrore.getDataInserimento () );
            flussoOrigineErrorePagopaOutputDTO.setContatoreTentativi ( simTFlussoOrigineErrore.getContatoreTentativi () );
            flussoOrigineErrorePagopaOutputDTO.setIdElaborazione ( simTFlussoOrigineErrore.getIdElaborazione () );
            flussoOrigineErrorePagopaOutputDTO.setIdStatoFlussolusso ( simTFlussoOrigineErrore.getIdStatoFlusso () );
            flussoOrigineErrorePagopaOutputDTO.setImportoTotalePagamentiIntermediati ( simTFlussoOrigineErrore.getImportoTotalePagamentiIntermediati () );
            flussoOrigineErrorePagopaOutputDTO.setNumeroTotalePagamentiIntermediati ( simTFlussoOrigineErrore.getNumeroTotalePagamentiIntermediati () );
            if ( null != simTFlussoOrigineErrore.getDataRegolamento () )
                flussoOrigineErrorePagopaOutputDTO.setDataRegolamento ( simTFlussoOrigineErrore.getDataRegolamento () );
            flussoOrigineErrorePagopaOutputDTO.setCodiceVersamento ( simTFlussoOrigineErrore.getCodiceVersamento () );
            flussoOrigineErrorePagopaOutputDTO.setCodiceErrore ( simTFlussoOrigineErrore.getCodiceErrore () );
            flussoOrigineErrorePagopaOutputDTO.setDescrizioneErrore ( simTFlussoOrigineErrore.getDescrizioneErrore () );
            flussoOrigineErrorePagopaOutputDTO.setIdMessaggio ( simTFlussoOrigineErrore.getIdMessaggio () );
            flussoOrigineErrorePagopaOutputDTO.setIdentificativoUnivocoRegolamento ( simTFlussoOrigineErrore.getIdentificativoUnivocoRegolamento () );
            flussoOrigineErrorePagopaOutputDTO.setUtenteInserimento ( simTFlussoOrigineErrore.getUtenteInserimento () );
            flussoOrigineErrorePagopaOutputDTO.setUtenteModifica ( simTFlussoOrigineErrore.getUtenteModifica () );
            if ( null != simTFlussoOrigineErrore.getDataModifica () )
                flussoOrigineErrorePagopaOutputDTO.setDataModifica ( simTFlussoOrigineErrore.getDataModifica () );
            flussoOrigineErrorePagopaOutputDTO.setCfEnteRicevente ( simTFlussoOrigineErrore.getCfEnteRicevente () );
            flussoOrigineErrorePagopaOutputDTO.setEsito ( new EsitoDTO ( CostantiErrori.WS_ESITO_OK_DEFAULT, "Operazione completata correttamente" ) );

        } catch ( Exception e ) {
            e.printStackTrace ();
            flussoOrigineErrorePagopaOutputDTO.setEsito ( new EsitoDTO ( CostantiErrori.WS_ESITO_KO_DEFAULT, "Errore durante la chiamata al ws" ) );
        }

        return flussoOrigineErrorePagopaOutputDTO;

    }

}
