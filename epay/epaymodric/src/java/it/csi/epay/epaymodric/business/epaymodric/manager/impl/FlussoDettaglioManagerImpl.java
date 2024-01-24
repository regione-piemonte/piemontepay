/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoDettaglioManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtilityWS;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiceVersamentoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoDettaglioRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoDettaglio;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;


/**
 * @author vsgro
 */
@Service
public class FlussoDettaglioManagerImpl implements FlussoDettaglioManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private FlussoDettaglioRepository repository;

    @Autowired
    private FlussoSintesiRepository flussoSintesiRepositoryRepository;

    @Autowired
    ErroreRepository erroreRepository;
    
    @Autowired
    CodiceVersamentoRepository codiceVersamentoRepository;


    @Override
    public List<FlussoDettaglio> leggi (ArrayList<Long> ids) {
        logger.info ( "FlussoManagerImpl.FindByIds: INIZIO" );
        List<FlussoDettaglio> flussiToReturn = new LinkedList<FlussoDettaglio> ();
        List<CblTFlussoDettaglio> cblTFlussoDettaglios = repository.findByIdIn ( ids );
        for ( int i = 0; i < cblTFlussoDettaglios.size (); i++ ) {
            flussiToReturn.add ( FlussiUtility.getFlussoDettaglio ( cblTFlussoDettaglios.get ( i ), true  ) );
        }
        logger.info ( "FlussoManagerImpl.FindByIds: FINE" );
        return flussiToReturn;
    }
    
    @Override
    public List<FlussoDettaglio> cercaDettagliDaSintesi ( Long idFlussoSintesi ){
    	logger.info ( "FlussoManagerImpl.cercaDettagliDaSintesi: INIZIO" );
        List<FlussoDettaglio> flussiToReturn = new LinkedList<FlussoDettaglio> ();
        List<CblTFlussoDettaglio> cblTFlussoDettaglios = repository.findByCblTFlussoSintesiId(idFlussoSintesi);
        for ( int i = 0; i < cblTFlussoDettaglios.size (); i++ ) {
            flussiToReturn.add ( FlussiUtility.getFlussoDettaglio ( cblTFlussoDettaglios.get ( i ), true  ) );
        }
        logger.info ( "FlussoManagerImpl.cercaDettagliDaSintesi: FINE" );
        return flussiToReturn;
    }

    @Override
    public DTOOutputWsFlussoDettaglio ricercaFlussiDettaglio ( Long idFlussoSintesi ) {
        DTOOutputWsFlussoDettaglio outputWs = new DTOOutputWsFlussoDettaglio ();
        ArrayList<DTOFlussoDettaglio> listaDettagli = new ArrayList<DTOFlussoDettaglio>();
        logger.info ( "FlussoDettaglioManagerImpl.ricercaFlussiDettaglio: INIZIO" );
        DTOOutputWsEsito dtoEsito = null;
        try {
            CblDErrore cblDErrore = erroreRepository.findByCodiceErrore ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            dtoEsito = FlussiUtilityWS.getDtoEsitoFromCblDErrore ( cblDErrore );
            outputWs.setEsito ( dtoEsito );

            CblTFlussoSintesi cblTFlussoSintesi =
                            flussoSintesiRepositoryRepository.findOne ( idFlussoSintesi );
            
            cblTFlussoSintesi.setCblTFlussoDettaglios (null);
            cblTFlussoSintesi.setCblTFlussoOrigine ( null );
            cblTFlussoSintesi.setDescrizioneCodiceVersamento(
            		findDescrizioneCodiceVersamento(cblTFlussoSintesi.getCblTEnte().getIdEnte(),  cblTFlussoSintesi.getCodiceVersamento()));
            
            outputWs.setFlussoSintesi (FlussiUtilityWS.getDTOFlussoSintesi (cblTFlussoSintesi ) );
            if (cblTFlussoSintesi != null) {
                List<CblTFlussoDettaglio> cblTFlussoDettagli =
                                repository.findByCblTFlussoSintesi ( cblTFlussoSintesi );
                for (int i=0; i<cblTFlussoDettagli.size ();i++) {
                    CblTFlussoDettaglio cblTFlussoDettaglio = cblTFlussoDettagli.get ( i );
                   
                    listaDettagli.add (
                        FlussiUtilityWS.getDTOFlussoDettaglio ( cblTFlussoDettaglio, cblTFlussoSintesi.getDescrizioneCodiceVersamento()  )
                                    );
                }
            }
            outputWs.setFlussiDettaglio ( listaDettagli );
        } catch ( Exception e ) {
            logger.error ( "FlussoManagerImpl.ricercaFlussiDettaglio: errore " + e.getMessage () );
            CblDErrore cblDErrore = erroreRepository.findByCodiceErrore ( CostantiErrori.ERRORE_DI_SISTEMA );
            dtoEsito = FlussiUtilityWS.getDtoEsitoFromCblDErrore ( cblDErrore );
            outputWs.setEsito ( dtoEsito );
        }
        logger.info ( "FlussoDettaglioManagerImpl.ricercaFlussiDettaglio: FINE" );
        return outputWs;
    }

    public FlussoDettaglioRepository getRepository () {
        return repository;
    }

    public void setRepository ( FlussoDettaglioRepository repository ) {
        this.repository = repository;
    }

    @Override
    public boolean controllaDettagliPerRiconciliazione ( FlussoSintesi flussoSintesi ) {

        BigInteger numeroDettagli = repository.countByIdFlussoSintesi ( flussoSintesi.getId () );

        boolean isRiconciliabile = true;

        if ( ( null == numeroDettagli || numeroDettagli.intValue () < 1 ) && !StringUtils.isEmpty ( flussoSintesi.getDatiSpecificiDiRiscossione () )
                        && !StringUtils.isEmpty ( flussoSintesi.getCodiceVersamento () ) ) {
            isRiconciliabile = false;
        }

        return isRiconciliabile;
    }

	@Override
	public void aggiornaDettaglio(FlussoDettaglio dettaglio) {
        logger.info ( "FlussoDettaglioManagerImpl.aggiornaDettaglio: INIZIO" );
        CblTFlussoDettaglio flussoDettaglioEntity = repository.findOne ( dettaglio.getId () );
        flussoDettaglioEntity.setDatiSpecificiDiRiscossione( dettaglio.getDatiSpecificiDiRiscossione () );

        flussoDettaglioEntity.setUtenteModifica (
            !StringUtils.isEmpty ( dettaglio.getUtenteModifica () ) ? dettaglio.getUtenteModifica () : Costanti.DEFAULT_UTENTE_SISTEMA );
        flussoDettaglioEntity
        .setDataModifica ( null != dettaglio.getDataModifica () ? dettaglio.getDataModifica () : new Timestamp ( System.currentTimeMillis () ) );

        repository.save ( flussoDettaglioEntity );
        logger.info ( "FlussoDettaglioManagerImpl.aggiornaDettaglio: INIZIO" );
		
	}

	@Override
	public void inserisciDettaglioDaDettaglio(FlussoDettaglio flussoDettaglio) {
        logger.info ( "FlussoDettaglioManagerImpl.inserisciDettaglioDaDettaglio: INIZIO" );
        CblTFlussoDettaglio flussoDettaglioEntity = repository.findOne ( flussoDettaglio.getId () );
        
        if(flussoDettaglioEntity!=null){
	        CblTFlussoDettaglio newFlussoDettaglioEntity = new CblTFlussoDettaglio();
	        
	        newFlussoDettaglioEntity.setCodiceVersamento(flussoDettaglioEntity.getCodiceVersamento());
	        newFlussoDettaglioEntity.setIdentificativoUnicoVersamento(flussoDettaglioEntity.getIdentificativoUnicoVersamento());
	        newFlussoDettaglioEntity.setIdentificativoUnicoRiscossione(flussoDettaglioEntity.getIdentificativoUnicoRiscossione());
	        newFlussoDettaglioEntity.setDataPagamento(flussoDettaglioEntity.getDataPagamento());
	        newFlussoDettaglioEntity.setEsitoPagamento(flussoDettaglioEntity.getEsitoPagamento());
	        newFlussoDettaglioEntity.setDescrizioneCausaleVersamento(flussoDettaglioEntity.getDescrizioneCausaleVersamento());
	        newFlussoDettaglioEntity.setTransactionid(flussoDettaglioEntity.getTransactionid());
	        newFlussoDettaglioEntity.setStatoInvioFruitore(flussoDettaglioEntity.getStatoInvioFruitore());
	        newFlussoDettaglioEntity.setAnagraficaPagatore(flussoDettaglioEntity.getAnagraficaPagatore());
	        newFlussoDettaglioEntity.setCodicefiscalePagatore(flussoDettaglioEntity.getCodicefiscalePagatore());
	        newFlussoDettaglioEntity.setIndiceSingoloVersamento(flussoDettaglioEntity.getIndiceSingoloVersamento());
	

	        newFlussoDettaglioEntity.setCblTFlussoSintesi(FlussiUtility.getFlusso (flussoDettaglio.getFlussoSintesi(),false));
	        newFlussoDettaglioEntity.setImportoSingoloVersamento(flussoDettaglio.getImportoSingoloVersamento());
	        newFlussoDettaglioEntity.setDatiSpecificiDiRiscossione( flussoDettaglio.getDatiSpecificiDiRiscossione () );
	        
	        newFlussoDettaglioEntity.setUtenteInserimento(Costanti.DEFAULT_UTENTE_SISTEMA);
	        newFlussoDettaglioEntity.setDataInserimento(new Timestamp ( System.currentTimeMillis () ));
	        
	        repository.save ( newFlussoDettaglioEntity );
		 } else {
	         logger.info ( String.format("Nessun flusso di dettaglio per il flusso di sintesi con identificativo %s trovato", flussoDettaglio.getFlussoSintesi().getIdentificativoFlusso()) );
	     }
        logger.info ( "FlussoDettaglioManagerImpl.inserisciDettaglioDaDettaglio: INIZIO" );
	}
	
	private String  findDescrizioneCodiceVersamento(String idEnte, String codiceVersamento) {
		CblTCodiceVersamento cblTCodiceVersamento= codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento(
				idEnte, codiceVersamento);
		if (null!= cblTCodiceVersamento) {
			return cblTCodiceVersamento.getDescrizioneVersamento();
		}
		return "--";
	}
}
