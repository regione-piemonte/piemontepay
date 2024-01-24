/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.util;

import it.csi.epay.epaysimweb.common.Constants;
import it.csi.epay.epaysimweb.common.builder.FlussoDettaglioVOBuilder;
import it.csi.epay.epaysimweb.common.builder.FlussoOrigineVOBuilder;
import it.csi.epay.epaysimweb.common.builder.FlussoSintesiVOBuilder;
import it.csi.epay.epaysimweb.common.builder.RicercaFlussoInputDTOBuilder;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoInputDTO;
import it.csi.epay.epaysimweb.model.flussi.FlussoDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoOrigineVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.RicercaFlussoFiltroVO;


public class VOConverterUtils {

    /**
     * Metodo che crea l'oggetto filtro da passare al WS
     *
     * @param filtro filtro del FE
     * @return RicercaFlussoInputDTO result DTO.
     */
    public static RicercaFlussoInputDTO createRicercaFlussoInputDTO ( RicercaFlussoFiltroVO filtro ) {
        return new RicercaFlussoInputDTOBuilder ()
                        .withDateEnd ( filtro.getDataElaborazioneA () )
                        .withDateStart ( filtro.getDataElaborazioneDa () )
                        .withIdentificativoFlusso ( filtro.getIdentificativoFlusso () )
                        .withStatoElaborazioneFlusso ( filtro.getStatoFlusso () )
                        .build ();
    }

    public static FlussoOrigineVO createFlussoOrigineVO ( FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO ) {
        return new FlussoOrigineVOBuilder ()
                        .withCfEnteCreditore ( flussoOriginePagopaOutputDTO.getCfEnteCreditore () )
                        .withDataInserimento ( flussoOriginePagopaOutputDTO.getDataInserimento () )
                        .withDataModifica ( flussoOriginePagopaOutputDTO.getDataModifica () )
                        .withDataOraMessaggio ( flussoOriginePagopaOutputDTO.getDataOraMessaggio () )
                        .withDataRegolamento ( flussoOriginePagopaOutputDTO.getDataRegolamento () )
                        .withDenominazioneEnte ( flussoOriginePagopaOutputDTO.getDenominazioneEnte () )
                        .withDenominazionePsp ( flussoOriginePagopaOutputDTO.getDenominazionePsp () )
                        .withId ( flussoOriginePagopaOutputDTO.getId () )
                        .withIdentificativoFlusso ( flussoOriginePagopaOutputDTO.getIdentificativoFlusso () )
                        .withIdentificativoPsp ( flussoOriginePagopaOutputDTO.getIdentificativoPsp () )
                        .withIdentificativoUnivocoRegolamento ( flussoOriginePagopaOutputDTO.getIdentificativoUnivocoRegolamento () )
                        .withIdMessaggio ( flussoOriginePagopaOutputDTO.getIdMessaggio () )
                        .withImportoTotalePagamenti ( flussoOriginePagopaOutputDTO.getImportoTotalePagamenti () )
                        .withImportoTotalePagamentiIntermediati ( flussoOriginePagopaOutputDTO.getImportoTotalePagamentiIntermediati () )
                        .withNumeroTotalePagamenti ( flussoOriginePagopaOutputDTO.getNumeroTotalePagamenti () )
                        .withNumeroTotalePagamentiIntermediati ( flussoOriginePagopaOutputDTO.getNumeroTotalePagamentiIntermediati () )
                        .withProvvisorioAnno ( flussoOriginePagopaOutputDTO.getProvvisorioAnno () )
                        .withProvvisorioNumero ( flussoOriginePagopaOutputDTO.getProvvisorioNumero () )
                        .withUtenteInserimento ( flussoOriginePagopaOutputDTO.getUtenteInserimento () )
                        .withUtenteModifica ( flussoOriginePagopaOutputDTO.getUtenteModifica () )
                        .withStatoElaborazioneFlusso ( ( null != flussoOriginePagopaOutputDTO.isStatoElaborazioneFlusso () )
                            ? ( flussoOriginePagopaOutputDTO.isStatoElaborazioneFlusso () ? Constants.STATO_FLUSSO_GENERATO
                                            : Constants.STATO_FLUSSO_NON_GENERATO )
                                            : Constants.STATO_FLUSSO_NON_ELABORATO )
                        .build ();
    }

    public static FlussoSintesiVO createFlussoSintesiVO ( FlussoSintesiPagopaOutputDTO flussoSintesiPagopaOutputDTO ) {
        return new FlussoSintesiVOBuilder ()
                        .withAccertamentoAnno ( flussoSintesiPagopaOutputDTO.getAccertamentoAnno () )
                        .withAccertamentoNumero ( flussoSintesiPagopaOutputDTO.getAccertamentoNumero () )
                        .withArticolo ( flussoSintesiPagopaOutputDTO.getArticolo () )
                        .withCapitolo ( flussoSintesiPagopaOutputDTO.getCapitolo () )
                        .withCodiceVersamento ( flussoSintesiPagopaOutputDTO.getCodiceVersamento () )
                        .withDatiSpecificiDiRiscossione ( flussoSintesiPagopaOutputDTO.getDatiSpecificiDiRiscossione () )
                        .withDescrizioneCodiceVersamento ( flussoSintesiPagopaOutputDTO.getDescrizioneCodiceVersamento () )
                        .withDescrizioneDatiSpecifici ( flussoSintesiPagopaOutputDTO.getDescrizioneDatiSpecifici () )
                        .withId ( flussoSintesiPagopaOutputDTO.getId () )
            .withImportoQuotaAggregazione ( flussoSintesiPagopaOutputDTO.getImportoQuotaAggregazione ().doubleValue () )
                        .withMacrotipo ( flussoSintesiPagopaOutputDTO.getMacrotipo () )
                        .withNumeroPagamentiAggregazione ( flussoSintesiPagopaOutputDTO.getNumeroPagamentiAggregazione () )
                        .withPdc ( flussoSintesiPagopaOutputDTO.getPdc () )
                        .withProgressivoFlussoSintetico ( flussoSintesiPagopaOutputDTO.getProgressivoFlussoSintetico () )
                        .withTematica ( flussoSintesiPagopaOutputDTO.getTematica () )
                        .build ();
    }

    public static FlussoDettaglioVO createFlussoDettaglioVO ( FlussoDettaglioPagopaOutputDTO flussoDettaglioPagopaOutputDTO ) {
        return new FlussoDettaglioVOBuilder ()
                        .withAnagraficaPagatore ( flussoDettaglioPagopaOutputDTO.getAnagraficaPagatore () )
                        .withCausale ( flussoDettaglioPagopaOutputDTO.getCausale () )
                        .withDataPagamento ( flussoDettaglioPagopaOutputDTO.getDataPagamento () )
                        .withDescrizioneCausaleVersamento ( flussoDettaglioPagopaOutputDTO.getDescrizioneCausaleVersamento () )
                        .withEsitoPagamento ( flussoDettaglioPagopaOutputDTO.getEsitoPagamento () )
                        .withId ( flussoDettaglioPagopaOutputDTO.getId () )
                        .withIdentificativoUnicoRiscossione ( flussoDettaglioPagopaOutputDTO.getIdentificativoUnicoRiscossione () )
                        .withIdentificativoUnicoVersamento ( flussoDettaglioPagopaOutputDTO.getIdentificativoUnicoVersamento () )
                        .withImportoSingoloVersamento ( flussoDettaglioPagopaOutputDTO.getImportoSingoloVersamento () )
                        .withIndiceSingoloVersamento ( flussoDettaglioPagopaOutputDTO.getIndiceSingoloVersamento () )
                        .withTransactionid ( flussoDettaglioPagopaOutputDTO.getTransactionid () )

                        .build ();
    }
    /*
     * public static FlussoOrigineVO createFlussoOrigineVOOutputDTO ( FlussoOriginePagopaOutputDTO flussoOriginePagopaOutputDTO ) { return new
     * FlussoOrigineVOBuilder () .withCodiceFiscaleEnte ( flussoOriginePagopaOutputDTO.getCfEnteRicevente () ) .withDataInserimento (
     * flussoOriginePagopaOutputDTO.getDataInserimento () ) .withDataoraFlusso ( flussoOriginePagopaOutputDTO.getDataoraFlusso () ) .withDataRegolamento (
     * flussoOriginePagopaOutputDTO.getDataRegolamento () ) .withIbanRiversamentoFlusso ( flussoOriginePagopaOutputDTO.getIbanRiversamentoFlusso () ) .withId (
     * flussoOriginePagopaOutputDTO.getId () ) .withIdElaborazione ( flussoOriginePagopaOutputDTO.getIdElaborazione () ) .withIdentificativoFlusso (
     * flussoOriginePagopaOutputDTO.getIdentificativoFlusso () ) .withIdentificativoPsp ( flussoOriginePagopaOutputDTO.getIdentificativoPsp () )
     * .withIdentificativoUnivocoRegolamento ( flussoOriginePagopaOutputDTO.getIdentificativoUnivocoRegolamento () ) .withIdMessaggio (
     * flussoOriginePagopaOutputDTO.getIdMessaggio () ) .withImportoTotalePagamenti ( flussoOriginePagopaOutputDTO.getImportoTotalePagamenti () )
     * .withImportoTotalePagamentiIntermediati ( flussoOriginePagopaOutputDTO.getImportoTotalePagamentiIntermediati () ) .withNumeroTotalePagamenti (
     * flussoOriginePagopaOutputDTO.getNumeroTotalePagamenti () ) .withNumeroTotalePagamentiIntermediati (
     * flussoOriginePagopaOutputDTO.getNumeroTotalePagamentiIntermediati () ) .withStatoElaborazioneFlusso ( ( null !=
     * flussoOriginePagopaOutputDTO.isStatoElaborazioneFlusso () ) ? ( flussoOriginePagopaOutputDTO.isStatoElaborazioneFlusso () ?
     * Constants.STATO_FLUSSO_GENERATO : Constants.STATO_FLUSSO_NON_GENERATO ) : Constants.STATO_FLUSSO_NON_ELABORATO ) .build (); }
     */
}
