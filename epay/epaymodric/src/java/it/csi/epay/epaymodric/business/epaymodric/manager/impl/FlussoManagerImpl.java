/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.manager.FlussoManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtility;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.FlussiUtilityWS;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiceVersamentoRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.CodiciVersamentoConfigRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoOrigineRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.FlussoSintesiRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.StatoFlussoErroreRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;


/**
 * @author vsgro
 */
@Service
public class FlussoManagerImpl implements FlussoManager {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private FlussoSintesiRepository repository;

    @Autowired
    private FlussoOrigineRepository flussoOrigineRepository;

    @Autowired
    private StatoFlussoErroreRepository statoFlussoErroreRepository;

    @Autowired
    ErroreRepository erroreRepository;
    
    @Autowired
    CodiceVersamentoRepository codiceVersamentoRepository;

    @Override
    @Transactional
    public void aggiornaDatiProvvisorio ( FlussoSintesi flussoSintesi ) {
        logger.info ( "FlussoManagerImpl.aggiornaDatiProvvisorio: INIZIO" );
        CblTFlussoSintesi flussoSintesiEntity = repository.findOne ( flussoSintesi.getId () );

        flussoSintesiEntity.setCapitolo ( flussoSintesi.getCapitolo () );
        flussoSintesiEntity.setArticolo ( flussoSintesi.getArticolo () );
        flussoSintesiEntity.setPianoDeiConti ( flussoSintesi.getPianoDeiConti () );
        flussoSintesiEntity.setAccertamentoAnno ( flussoSintesi.getAccertamentoAnno () );
        flussoSintesiEntity.setAccertamentoNumero ( flussoSintesi.getAccertamentoNumero () );
        flussoSintesiEntity.setDescrizioneCodiceVersamento ( flussoSintesi.getDescrizioneCodiceVersamento () );
        flussoSintesiEntity.setProvvisorioAnno ( flussoSintesi.getProvvisorioAnno () );
        flussoSintesiEntity.setProvvisorioNro ( flussoSintesi.getProvvisorioNumero () );
        flussoSintesiEntity.setDatiSpecificiDiRiscossione( flussoSintesi.getDatiSpecificiDiRiscossione () );

        flussoSintesiEntity.setUtenteModifica (
            StringUtils.hasText ( flussoSintesi.getUtenteModifica () ) ? flussoSintesi.getUtenteModifica () : Costanti.DEFAULT_UTENTE_SISTEMA );
        flussoSintesiEntity
        .setDataModifica ( null != flussoSintesi.getDataModifica () ? flussoSintesi.getDataModifica () : new Timestamp ( System.currentTimeMillis () ) );

        repository.save ( flussoSintesiEntity );
        logger.info ( "FlussoManagerImpl.aggiornaDatiProvvisorio: INIZIO" );
    }

    @Override
    @Transactional
    public FlussoSintesi inserisciSintesiDaSintesi ( FlussoSintesi flussoSintesi ) {
        logger.info ( "FlussoManagerImpl.inserisciSintesiDaSintesi: INIZIO" );
        CblTFlussoSintesi flussoSintesiEntity = repository.findOne ( flussoSintesi.getId () );
        
        if(flussoSintesiEntity!=null) {
        	  CblTFlussoSintesi nuovoFlussoSintesiEntity = new CblTFlussoSintesi();
              
              nuovoFlussoSintesiEntity.setCodiceVersamento(flussoSintesiEntity.getCodiceVersamento());
              nuovoFlussoSintesiEntity.setNumeroVersQuotaAggregazione(flussoSintesiEntity.getNumeroVersQuotaAggregazione());
              nuovoFlussoSintesiEntity.setProvvisorioAnno(flussoSintesiEntity.getProvvisorioAnno());
              nuovoFlussoSintesiEntity.setProvvisorioNro(flussoSintesiEntity.getProvvisorioNro());
              nuovoFlussoSintesiEntity.setCblTEnte(flussoSintesiEntity.getCblTEnte());
              nuovoFlussoSintesiEntity.setCblTFlussoOrigine(flussoSintesiEntity.getCblTFlussoOrigine());
              nuovoFlussoSintesiEntity.setDataPagamentoCalcolata(flussoSintesiEntity.getDataPagamentoCalcolata());

              nuovoFlussoSintesiEntity.setCapitolo ( flussoSintesi.getCapitolo () );
              nuovoFlussoSintesiEntity.setArticolo ( flussoSintesi.getArticolo () );
              nuovoFlussoSintesiEntity.setPianoDeiConti ( flussoSintesi.getPianoDeiConti () );
              nuovoFlussoSintesiEntity.setAccertamentoAnno ( flussoSintesi.getAccertamentoAnno () );
              nuovoFlussoSintesiEntity.setAccertamentoNumero ( flussoSintesi.getAccertamentoNumero () );
              nuovoFlussoSintesiEntity.setDescrizioneCodiceVersamento ( flussoSintesi.getDescrizioneCodiceVersamento () );
              nuovoFlussoSintesiEntity.setProvvisorioAnno ( flussoSintesi.getProvvisorioAnno () );
              nuovoFlussoSintesiEntity.setProvvisorioNro ( flussoSintesi.getProvvisorioNumero () );
              nuovoFlussoSintesiEntity.setDatiSpecificiDiRiscossione( flussoSintesi.getDatiSpecificiDiRiscossione () );
              nuovoFlussoSintesiEntity.setImportoQuotaAggregazione(flussoSintesi.getImportoQuotaAggregazione());

              nuovoFlussoSintesiEntity.setUtenteInserimento(Costanti.DEFAULT_UTENTE_SISTEMA); 
              nuovoFlussoSintesiEntity.setDataInserimento(new Timestamp ( System.currentTimeMillis ()) );

              nuovoFlussoSintesiEntity = repository.save ( nuovoFlussoSintesiEntity );

              logger.info ( "FlussoManagerImpl.inserisciSintesiDaSintesi: FINE" );
              return FlussiUtility.getFlusso ( nuovoFlussoSintesiEntity, false, nuovoFlussoSintesiEntity.getCblTFlussoOrigine().getIdentificativoFlusso() );
        } else {
            logger.info ( String.format("Nessun flusso sintesi con identificativo %s trovato", flussoSintesi.getIdentificativoFlusso()) );
            logger.info ( "FlussoManagerImpl.inserisciSintesiDaSintesi: FINE" );
            return null;
        }
      
    }
    
    @Override
    @Transactional
    public void aggiornaIdErrore (
        StatoFlussoErrore statoFlussoErrore,
        Long idFlussoSintesi ) {
        logger.info ( "FlussoManagerImpl.aggiornaIdErrore: INIZIO" );
        if ( null != statoFlussoErrore.getId () ) {
            repository.aggiornaIdErrore (
                statoFlussoErroreRepository.findOne ( statoFlussoErrore.getId () ), idFlussoSintesi );
        } else {
            repository.aggiornaIdErrore (
                null, idFlussoSintesi );
        }

        logger.info ( "FlussoManagerImpl.aggiornaIdErrore: INIZIO" );
    }

    @Override
    public BigDecimal getImportoTotaleDettagli ( FlussoOrigine flussoOrigine) {
        BigDecimal importo = new BigDecimal ( 0D );
        importo = repository.getImportoTotaleDettagli ( FlussiUtility.getFlussoOrigine ( flussoOrigine ) );
        return importo;
    }

    @Override
    @Transactional
    public BigDecimal getImportoTotaleSintesi ( FlussoOrigine flussoOrigine ) {

        BigDecimal importoTotale = new BigDecimal ( 0D );

        List<FlussoSintesi> flussiSintesi = this.recuperaFlussiSintesiDaOrigine ( flussoOrigine );

        if ( null != flussiSintesi && flussiSintesi.size () > 0 ) {
            for ( FlussoSintesi flussoSintesi: flussiSintesi ) {
                importoTotale = importoTotale
                                .add ( null != flussoSintesi.getImportoQuotaAggregazione () ? flussoSintesi.getImportoQuotaAggregazione () : new BigDecimal ( 0D ) );
            }
        }

        return importoTotale;
    }

    public FlussoSintesiRepository getRepository () {
        return repository;
    }

    @Override
    public List<FlussoSintesi> leggi ( ArrayList<Long> ids ) {
        logger.info ( "FlussoManagerImpl.FindByIds: INIZIO" );
        List<FlussoSintesi> flussiToReturn = new LinkedList<> ();
        List<CblTFlussoSintesi> cblFlussoSintesi = repository.findByIdIn ( ids );
        for ( int i = 0; i < cblFlussoSintesi.size (); i++ ) {
            CblTFlussoSintesi cblTFlussoSintesi = cblFlussoSintesi.get ( i );
            FlussoSintesi flusso = valorizzaImportoTotaleDettagli ( cblTFlussoSintesi, true );
            flussiToReturn.add ( flusso );
        }
        logger.info ( "FlussoManagerImpl.FindByIds: FINE" );
        return flussiToReturn;
    }

    @Override
    @Transactional
    public List<FlussoSintesi> leggiPerIdFlussoOrigine ( Long idFlussoOrigine ) {
        logger.info ( "FlussoManagerImpl.findByCblTFlussoOrigine: INIZIO" );
        List<FlussoSintesi> flussiToReturn = new LinkedList<> ();
        List<CblTFlussoSintesi> cblFlussoSintesi = repository.findByIdFlussoOrigine ( idFlussoOrigine );
        for ( int i = 0; i < cblFlussoSintesi.size (); i++ ) {
            CblTFlussoSintesi cblTFlussoSintesi = cblFlussoSintesi.get ( i );
            FlussoSintesi flusso = valorizzaImportoTotaleDettagli ( cblTFlussoSintesi, true );
            flussiToReturn.add ( flusso );
        }
        logger.info ( "FlussoManagerImpl.findByCblTFlussoOrigine: FINE" );
        return flussiToReturn;
    }

    @Override
    @Transactional
    public List<FlussoSintesi> recuperaFlussiSintesiDaOrigine ( FlussoOrigine flussoOrigine ) {
        logger.info ( "FlussoManagerImpl.recuperaFlussiSintesiDaOrigine: INIZIO" );
        List<FlussoSintesi> flussiToReturn = new ArrayList<> ();
        List<CblTFlussoSintesi> cblFlussoSintesiList = repository.findByCblTFlussoOrigine ( FlussiUtility.getFlussoOrigine ( flussoOrigine ) );
        if ( null != cblFlussoSintesiList ) {
            for ( CblTFlussoSintesi flussoSintesi: cblFlussoSintesiList ) {
                flussiToReturn.add ( FlussiUtility.getFlusso ( flussoSintesi, true, flussoOrigine.getIdentificativoFlusso() ) );
            }
        }
        logger.info ( "FlussoManagerImpl.recuperaFlussiSintesiDaOrigine: FINE" );
        return flussiToReturn;
    }

    @Override
    public DTOOutputWsFlussoSintesi ricercaFlussoSintesi ( Long idFlussoOrigine ) {
        DTOOutputWsFlussoSintesi outputWs = new DTOOutputWsFlussoSintesi ();
        ArrayList<DTOFlussoSintesi> listaSintesi = new ArrayList<> ();
        DTOOutputWsEsito dtoEsito = null;
        logger.info ( "FlussoManagerImpl.ricercaFlussoSintesi: INIZIO" );
        try {
            CblDErrore cblDErrore = erroreRepository.findByCodiceErrore ( CostantiErrori.WS_ESITO_OK_DEFAULT );
            dtoEsito = FlussiUtilityWS.getDtoEsitoFromCblDErrore ( cblDErrore );
            outputWs.setEsito ( dtoEsito );

            CblTFlussoOrigine cblTFlussoOrigine = flussoOrigineRepository.findOne ( idFlussoOrigine );
            outputWs.setFlussoOrigine ( FlussiUtilityWS.getDTOFlussoOrigine ( cblTFlussoOrigine ) );
            if ( cblTFlussoOrigine != null ) {
                List<CblTFlussoSintesi> cblTFlussiSintesi = repository.findByCblTFlussoOrigine ( cblTFlussoOrigine );
                for ( int i = 0; i < cblTFlussiSintesi.size (); i++ ) {
                    CblTFlussoSintesi cblTFlussoSintesi = cblTFlussiSintesi.get ( i );
                    cblTFlussoSintesi.setDescrizioneCodiceVersamento(
                    		findDescrizioneCodiceVersamento(cblTFlussoSintesi.getCblTEnte().getIdEnte(), cblTFlussoSintesi.getCodiceVersamento()));
                    DTOFlussoSintesi dtoFlussoSintesi = FlussiUtilityWS.getDTOFlussoSintesi ( cblTFlussoSintesi );
                    listaSintesi.add ( dtoFlussoSintesi );
                }
                outputWs.setFlussiSintesi ( listaSintesi );
            }
        } catch ( Exception e ) {
            logger.error ( "FlussoManagerImpl.ricercaFlussoSintesi: errore " + e.getMessage () );
            CblDErrore cblDErrore = erroreRepository.findByCodiceErrore ( CostantiErrori.ERRORE_DI_SISTEMA );
            dtoEsito = FlussiUtilityWS.getDtoEsitoFromCblDErrore ( cblDErrore );
            outputWs.setEsito ( dtoEsito );
        }
        logger.info ( "FlussoManagerImpl.ricercaFlussoSintesi: FINE" );
        return outputWs;
    }

	private String  findDescrizioneCodiceVersamento(String idEnte, String codiceVersamento) {
		CblTCodiceVersamento cblTCodiceVersamento= codiceVersamentoRepository.findOneByIdEnteAndCodiceVersamento(
				idEnte, codiceVersamento);
		if (null!= cblTCodiceVersamento)
		{
			return cblTCodiceVersamento.getDescrizioneVersamento();
		}
		return "--";
		
	}

    public void setRepository ( FlussoSintesiRepository repository ) {
        this.repository = repository;
    }

    private FlussoSintesi valorizzaImportoTotaleDettagli ( CblTFlussoSintesi cblTFlussoSintesi, boolean withDettagli ) {
        FlussoSintesi flusso = FlussiUtility.getFlusso ( cblTFlussoSintesi, withDettagli );
        BigDecimal importoTotaleFlusso = repository.getImportoTotaleDettagli ( cblTFlussoSintesi.getCblTFlussoOrigine () );
        flusso.setImportoTotaleDettagli ( importoTotaleFlusso );
        return flusso;
    }

}
