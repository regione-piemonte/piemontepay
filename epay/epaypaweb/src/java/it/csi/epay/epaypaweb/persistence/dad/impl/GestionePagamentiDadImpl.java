/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.math.NumberUtils;

import it.csi.epay.epaypaweb.dto.ConfigurazioneDto;
import it.csi.epay.epaypaweb.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto;
import it.csi.epay.epaypaweb.dto.PagamentiDto;
import it.csi.epay.epaypaweb.dto.PagamentiExportDto;
import it.csi.epay.epaypaweb.dto.PagamentiFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.TotalSizeAndLightListDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNamePagamentiEnum;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.exception.RecordNumberGreaterThanThresholdException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBaseImpl;
import it.csi.epay.epaypaweb.persistence.dad.interf.CommonDad;
import it.csi.epay.epaypaweb.persistence.dad.interf.GestionePagamentiDad;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTAggPosizioneDebitoriaDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTCodiceVersamentoDao;
import it.csi.epay.epaypaweb.persistence.dao.interf.EpaypaTPagamentiDao;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPagamenti;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaMedium;



public class GestionePagamentiDadImpl extends EPaypaDadBaseImpl implements GestionePagamentiDad {

    static private final String CLASSNAME = GestionePagamentiDadImpl.class.getSimpleName ();

    @Inject
    private EpaypaTPagamentiDao epaypaTPagamentiDao;
    
    @Inject
    private EpaypaTCodiceVersamentoDao epaypaTCodiceVersamentoDao;
    
    @Inject
    private EpaypaTAggPosizioneDebitoriaDao epaypaTAggPosizioneDebitoriaDao;

    @Inject
    private CommonDad commonDad;


    @Override
    public TotalSizeAndLightListDto<PagamentiDto> ricerca ( PagamentiFilterDto filter,
        List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag )
                        throws PersistenceException, RecordNumberGreaterThanThresholdException {
        String methodName = "ricerca";
        
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            List<PagamentiDto> dtoList = new ArrayList<> ();
            if ( null != filter.getIdCodiceVersamento () ) {
                filter.setCodiceVersamento ( epaypaTCodiceVersamentoDao.findCodVersamentoById ( filter.getIdCodiceVersamento () ) );
            }
            TotalSizeAndLightListDto<EpaypaTPagamenti> entityList = epaypaTPagamentiDao.ricerca ( filter, ord, pag, -1 );

            for ( EpaypaTPagamenti pagamento: entityList.getLightList () ) {
                dtoList.add ( toPagamentiDto ( pagamento, null != pagamento.getPosizioneDebitoria () ? epaypaTAggPosizioneDebitoriaDao
                    .findAllByIdPosizioneDebitoriaEst ( pagamento.getPosizioneDebitoria ().getIdPosizioneDebitoriaEst () ) : null ) );
            }

            return new TotalSizeAndLightListDto<> ( entityList.getTotalSize (), dtoList );

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public TotalSizeAndLightListDto<PagamentiExportDto> esporta ( PagamentiFilterDto filter, List<CriterioOrdinamentoDto<ColumnNamePagamentiEnum>> ord, PaginazioneDto pag )
                    throws PersistenceException,RecordNumberGreaterThanThresholdException {
        String methodName = "esporta";
        
        
        

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            int thresholdRecordsNum = -1;
            if( pag==null ) {
                ConfigurazioneDto configurazioneDto =commonDad.findConfigurazioneByCodiceAndCodFiscaleEnte("NUM_MAX_RECORDS_EXPORT", "PagoPa");
                if(( configurazioneDto!=null ) && ( configurazioneDto.getValore()!=null ) && ( NumberUtils.isNumber(configurazioneDto.getValore()) ) ) {
                    thresholdRecordsNum = NumberUtils.toInt(configurazioneDto.getValore());
                }
            }

            List<PagamentiExportDto> dtoList = new ArrayList<> ();
            if ( null != filter.getIdCodiceVersamento () ) {
                filter.setCodiceVersamento ( epaypaTCodiceVersamentoDao.findCodVersamentoById ( filter.getIdCodiceVersamento () ) );
            }
            TotalSizeAndLightListDto<EpaypaTPagamenti> entityList = epaypaTPagamentiDao.ricerca ( filter, ord, pag, thresholdRecordsNum);

            for ( EpaypaTPagamenti pagamento: entityList.getLightList () ) {
                EpaypaTPosizioneDebitoriaMedium posizioneDebitoria = null;
                if ( pagamento.getPosizioneDebitoria () != null && pagamento.getPosizioneDebitoria ().getIdPosizioneDebitoria () != null ) {
                    posizioneDebitoria = pagamento.getPosizioneDebitoria ();
                }
                //                else if (pagamento.getPagamentoSpontaneo()) {
                //                	 pagamento.getNotificaPagamento();
                //
                //                }
                dtoList.add ( toPagamentiExportDto ( pagamento, posizioneDebitoria,
                    null != posizioneDebitoria
                                    ? epaypaTAggPosizioneDebitoriaDao.findAllByIdPosizioneDebitoriaEst ( posizioneDebitoria.getIdPosizioneDebitoriaEst () )
                                    : null ) );
            }

            return new TotalSizeAndLightListDto<PagamentiExportDto>(entityList.getTotalSize(),dtoList);

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
    }

    @Override
    public DettaglioPagamentiDto getDettaglio ( String iuv ) throws PersistenceException {
        String methodName = "getDettaglio";
        
        

        DettaglioPagamentiDto pagamentiDto = null;

        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );
            PagamentiFilterDto filter = new PagamentiFilterDto ();
            filter.setIuvEsatto ( iuv );

            EpaypaTPagamenti entityPerIuv = epaypaTPagamentiDao.findOneByIuv(iuv);

            if(entityPerIuv != null) {
                filter.setPagamentiSpontanei(entityPerIuv.getPagamentoSpontaneo());

                EpaypaTPagamenti entity = epaypaTPagamentiDao.findPagamentoByFilter(filter);

                if ( entity != null ) {
                    if ( entityPerIuv.getPagamentoSpontaneo () ) {
                        pagamentiDto = toDettaglioPagamentiDto ( entity, null );
                    } else {
                        pagamentiDto = toDettaglioPagamentiDto ( entity, null != entity.getPosizioneDebitoria () ? epaypaTAggPosizioneDebitoriaDao
                            .findAllByIdPosizioneDebitoriaEst ( entity.getPosizioneDebitoria ().getIdPosizioneDebitoriaEst () ) : null );
                    }
                    
                }
            }

        } finally {
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return pagamentiDto;

    }

}
