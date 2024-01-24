/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodricweb.business.manager.RicercaElaborazionePrecedenteManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.mapper.FlussoMapper;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.elaborazione.ElaborazioneVO;
import it.csi.epay.epaymodricweb.model.elaborazione.RicercaElaborazionePrecedenteFiltroVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoOrigineVO;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;

@Service
public class RicercaElaborazionePrecedenteManagerImpl implements RicercaElaborazionePrecedenteManager {

    @Autowired
    private EpaymodricWsFacade service;


    @Override
    public List<ElaborazioneVO> ricercaElaborazionePrecedente ( RicercaElaborazionePrecedenteFiltroVO filtro ) throws OperationFailedException {
        return service.ricercaElaborazionePrecedente(filtro);
    }

    @Override
    public List<GenericVO> elencaStatiElaborazione () throws OperationFailedException {
        return service.ricercaStatiElaborazione ();
    }

    @Override
    public List<FlussoOrigineVO> ricercaFlussiOrigineAssociatiAElaborazione ( String listaFlussi ) throws OperationFailedException {
        List<FlussoOrigineVO> result = new LinkedList<> ();
        RicercaFlussoFiltroVO filtro = new RicercaFlussoFiltroVO ();

        if ( null != listaFlussi && StringUtils.hasText ( listaFlussi ) ) {
            String [] listaFlussiArr = listaFlussi.split ( ";" );
            filtro.setIdentificativiFlusso ( Arrays.asList ( listaFlussiArr ) );
            // Non e' bello ma funziona. Verra' migliorato in seguito.
            filtro.setStart ( 0 );
            filtro.setLength ( 1000 );
            DtoOutputWsFlussoOrigine res = service.ricercaFlussoOrigine ( filtro );
            List<FlussoOrigineVO> outputList = new LinkedList<> ();
            for ( DtoFlussoOrigine dto: res.getFlussiOrigine () ) {
                try {
                    outputList.add ( FlussoMapper.parseFlussoOrigineVO ( dto ) );
                } catch ( ParseException e ) {
                }
            }

            return outputList;
        }

        return result;
    }

}
