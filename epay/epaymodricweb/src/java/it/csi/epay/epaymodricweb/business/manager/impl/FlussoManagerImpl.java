/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.business.manager.FlussoManager;
import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsEsito;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteEsportazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.TipoFileReportEnum;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiSintesiVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;

@Service
public class FlussoManagerImpl implements FlussoManager {

    @Autowired
    private EpaymodricWsFacade service;

    @Override
    public DtoOutputWsFlussoOrigine ricercaFlussoOrigine(RicercaFlussoFiltroVO filtro, String sortingCol, String sortingDir) throws OperationFailedException {
        return service.ricercaFlussoOrigineConOrdinamento(filtro, sortingCol, sortingDir);
    }

    @Override
    public FlussiSintesiVO ricercaFlussiSintesi(Long idFlussoOrigine) throws OperationFailedException {
        return service.ricercaFlussiSintesi(idFlussoOrigine);
    }

    @Override
    public FlussiDettaglioVO ricercaFlussiDettaglio(Long idFlussoSintesi) throws OperationFailedException {
        return service.ricercaFlussiDettaglio(idFlussoSintesi);
    }

    @Override
    public FlussoDettaglioVO ricercaFlussoDettaglio(Long idFlussoSintesi, Long idFlussoDettaglio) throws OperationFailedException {
        FlussiDettaglioVO flussi = service.ricercaFlussiDettaglio(idFlussoSintesi);
        if (flussi != null && flussi.getFlussiDettaglio() != null) {
            for (FlussoDettaglioVO flusso : flussi.getFlussiDettaglio()) {
                if (flusso.getId().equals(idFlussoDettaglio)) {
                    return flusso;
                }
            }
        }
        throw new OperationFailedException("Il flusso di dettaglio richiesto non  stato trovato");
    }

    @Override
    public FileReportVO ricercaFlussiDaEsportare ( RicercaFlussoFiltroVO filtro, TipoFileReportEnum tipoFileReport) throws OperationFailedException {
        return service.ricercaFlussiDaEsportare ( filtro, tipoFileReport );
    }

    @Override
    public GenericVO inserisciFlussiDaRielaborare ( List<String> identificativiFlussoDaRielaborare ) throws OperationFailedException {
        DtoInputWsInserisciFlussiDaRielaborare input = new DtoInputWsInserisciFlussiDaRielaborare ();
        GenericVO esito = new GenericVO ();
        input.getIdentificativiFlussoDaRielaborare ().addAll ( identificativiFlussoDaRielaborare );
        DtoOutputWsEsito esitoWs = null;
        try {
            esitoWs = service.inserisciFlussiDaRielaborare ( input );
        } catch ( UnrecoverableException_Exception e ) {
            throw new OperationFailedException ( e );
        } catch ( Exception_Exception e ) {
            throw new OperationFailedException ( e );
        } catch ( EpaymodricException_Exception e ) {
            throw new OperationFailedException ( e );
        }
        esito.setCodice ( esitoWs.getCodiceErrore () );
        esito.setDescrizione ( esitoWs.getDescrizione () );
        return esito;
    }

	@Override
	public DtoOutputWsRicercaLimiteEsportazione ricercaLimiteEsportazione() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
		return service.ricercaLimiteEsportazione();
	}
	
	@Override
	public DtoOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception {
		return service.ricercaLimiteElaborazioneReport();
	}
    
    

}
