/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager.impl;

import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.RiferimentoContabileManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.mapper.ParentMapper;
import it.csi.epay.epaypacatalogweb.integration.mapper.RiferimentoContabileMapper;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.services.RiferimentiContabiliService;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ChiudiRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ChiudiRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentiContabiliSecondariPerCovInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentiContabiliSecondariPerCovOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ChiudiRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.ModificaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RicercaRiferimentoContabileFiltroVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileStoricoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RisultatoRicercaRiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 */

@Service
public class RiferimentoContabileManagerImpl implements RiferimentoContabileManager {

    private static final String CODICE_OK = "OK";

    @Autowired
    private EpaypacatalogsrvFacade facade;

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private RiferimentiContabiliService riferimentiContabiliService;

    @Override
    public List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> ricerca ( RicercaRiferimentoContabileFiltroVO filtro )
                    throws OperationFailedException {

        RicercaRiferimentoContabileInput ricercaRiferimentoContabileInput = RiferimentoContabileMapper.map ( filtro );
        RicercaRiferimentoContabileOutput serviceOutput;
        try {
            serviceOutput = facade.ricercaRiferimentoContabile ( ricercaRiferimentoContabileInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> output = new ArrayList<> ();

        for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto serviceOutputDto: serviceOutput.getRisultati () ) {
            RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO mappedDto = RiferimentoContabileMapper.map ( serviceOutputDto );
            boolean filtraMb = Boolean.TRUE.equals ( filtro.getMultibeneficiarioCheckbox () );
            if ( filtraMb ) {
                output.add ( mappedDto );
            } else {
                List<RisultatoRicercaRiferimentoContabileVO> rifFiltred = new ArrayList<> ();
                for ( RisultatoRicercaRiferimentoContabileVO res: mappedDto.getRiferimentiContabili () ) {
                    boolean flagMbPrimario = Boolean.TRUE.equals ( res.getFlagMbPrimario () );
                    boolean flagMbSecondario = Boolean.TRUE.equals ( res.getFlagMbSecondario () );
                    if ( !flagMbPrimario && !flagMbSecondario ) {
                        rifFiltred.add ( res );
                    }
                 
                }
                mappedDto.setRiferimentiContabili ( rifFiltred );
                
                if (!CollectionUtils.isEmpty ( mappedDto.getCodiciVersamentoCollegati () ))
                {
                    for ( RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO serviceOutputDtoFiglio: mappedDto.getCodiciVersamentoCollegati () )
                    {
                        List<RisultatoRicercaRiferimentoContabileVO> rifFilgioFiltred = new ArrayList<> ();
                        for ( RisultatoRicercaRiferimentoContabileVO resFiglio: serviceOutputDtoFiglio.getRiferimentiContabili () ) 
                        {
                            if ( !Boolean.TRUE.equals ( resFiglio.getFlagMbPrimario () )&& !Boolean.TRUE.equals ( resFiglio.getFlagMbSecondario () )) 
                            {
                                rifFilgioFiltred.add ( resFiglio );
                            }
                            
                        }
                        serviceOutputDtoFiglio.setRiferimentiContabili ( rifFilgioFiltred );
                    }
                }
               
                output.add ( mappedDto );
            }
        }

        return output;
    }

    @Override
    public RiferimentoContabileVO get ( Long id ) throws OperationFailedException {

        GetRiferimentoContabileInput getRiferimentoContabileInput = new GetRiferimentoContabileInput ();
        getRiferimentoContabileInput.setId ( id );

        GetRiferimentoContabileOutput serviceOutput;
        try {
            serviceOutput = facade.getRiferimentoContabile ( getRiferimentoContabileInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

		return RiferimentoContabileMapper.map ( serviceOutput.getRiferimentoContabile () );
    }

    @Override
    public void inserisci ( ModificaRiferimentoContabileVO userInput ) throws OperationFailedException {

        InserisciRiferimentoContabileInput serviceInput = RiferimentoContabileMapper.mapInserisci ( userInput );

        InserisciRiferimentoContabileOutput serviceOutput;
        try {
            serviceOutput = facade.inserisciRiferimentoContabile ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }
        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }
	}

    @Override
    public void aggiorna ( ModificaRiferimentoContabileVO userInput ) throws OperationFailedException {

        AggiornaRiferimentoContabileInput serviceInput = RiferimentoContabileMapper.mapAggiorna ( userInput );

        AggiornaRiferimentoContabileOutput serviceOutput;
        try {
            serviceOutput = facade.aggiornaRiferimentoContabile ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }
    }

    @Override
    public void elimina ( Long id ) throws OperationFailedException {

        EliminaRiferimentoContabileInput serviceInput = new EliminaRiferimentoContabileInput ();
        serviceInput.setId ( id );

        EliminaRiferimentoContabileOutput serviceOutput;
        try {
            serviceOutput = facade.eliminaRiferimentoContabile ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }
	}

    @Override
    public RiferimentoContabileVO merge ( Long id, ModificaRiferimentoContabileVO input, boolean isDuplicato ) throws OperationFailedException {

        RiferimentoContabileVO o = id == null ? istanzia () : get ( id );
        boolean isNuovo = ( id == null );

        o.setAnnoAccertamento ( input.getAnnoAccertamento () );
        o.setAnnoEsercizio ( input.getAnnoEsercizio () );

        if ( isNuovo || isDuplicato ) {
            o.setDatoSpecificoRiscossione ( input.getDatoSpecificoRiscossione () );
            o.setDescrizioneDatoSpecificoRiscossione ( input.getDescrizioneDatoSpecificoRiscossione () );
            o.setDataInizioValidita ( input.getDataInizioValidita () );
            o.setCodiceDatoSpecificoRiscossione ( input.getCodiceDatoSpecificoRiscossione () );
            o.setFlagValoreInizialeIdCodiceVersamento ( input.getFlagValoreInizialeIdCodiceVersamento () );
            o.setIdCodiceVersamento ( input.getIdCodiceVersamento () );
            o.setFlagAssociaRifContPrimarioValue ( input.getFlagAssociaRifContPrimarioValue () );
            o.setFlagAssociaRifContSecondarioValue ( input.getFlagAssociaRifContSecondarioValue () );
            o.setIdTassonomia ( input.getIdTassonomia () );
        }

        o.setDataFineValidita ( input.getDataFineValidita () );

        o.setLivelloPdc ( input.getLivelloPdc () );
        o.setNumeroAccertamento ( input.getNumeroAccertamento () );
        o.setNumeroArticolo ( input.getNumeroArticolo () );
        o.setNumeroCapitolo ( input.getNumeroCapitolo () );
        o.setTitolo ( input.getTitolo () );
        o.setCategoria ( input.getCategoria () );
        o.setTipologia ( input.getTipologia () );
        o.setFlagPrincipale ( input.getFlagPrincipale () );

        if ( isNuovo || isDuplicato ) {
            if ( input.getCodiceTipologiaDatoSpecificoRiscossione () == null ) {
                o.setDescrizioneTipologiaDatoSpecificoRiscossione ( null );
            } else if ( !input.getCodiceTipologiaDatoSpecificoRiscossione ().equals ( o.getCodiceTipologiaDatoSpecificoRiscossione () ) ) {
                o.setCodiceTipologiaDatoSpecificoRiscossione ( input.getCodiceTipologiaDatoSpecificoRiscossione () );
                o.setDescrizioneTipologiaDatoSpecificoRiscossione ( null );
                for ( GenericVO opzione: decodificheManager.getListaOpzioniTipologiaDatoSpecificoRiscossioneRiferimentoContabile () ) {
                    if ( opzione.getCodice ().equals ( input.getCodiceTipologiaDatoSpecificoRiscossione () ) ) {
                        o.setDescrizioneTipologiaDatoSpecificoRiscossione ( opzione.getDescrizione () );
                        break;
                    }
                }
            }
        }

        return o;
    }

    @Override
    public RiferimentoContabileVO istanzia () {
		return new RiferimentoContabileVO ();
    }

    @Override
    public void chiudi ( ChiudiRiferimentoContabileVO userInput ) throws OperationFailedException {

        ChiudiRiferimentoContabileInput serviceInput = new ChiudiRiferimentoContabileInput ();
        serviceInput.setId ( userInput.getId () );
        serviceInput.setDataFineValidita ( ParentMapper.dateToCalendar ( userInput.getDataFineValidita () ) );

        ChiudiRiferimentoContabileOutput serviceOutput;
        try {

            serviceOutput = facade.chiudiRiferimentoContabile ( serviceInput );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }
	}

    @Override
    public RiferimentoContabileVO getStorico ( Long idPadre, Long id ) throws OperationFailedException {
        RiferimentoContabileVO padre = get ( idPadre );
        if ( padre == null ) {
            return null;
        }
        for ( RiferimentoContabileStoricoVO o: padre.getStorico () ) {
            if ( o.getId ().equals ( id ) ) {
                return o;
            }
        }
        return null;
    }

    @Override
    public String verificaPresenzaRiferimentiContabili ( Long idCodiceVersamento, Integer annoEsercizio, Boolean generico ,  Date dataInizioValidita, Date dataFineValidita, Long idRiferimentoContabile) throws OperationFailedException {

        String messaggio_errore;
        VerificaPresenzaRiferimentiContabiliInput input = new VerificaPresenzaRiferimentiContabiliInput ();
        input.setIdCodiceVersamento ( idCodiceVersamento );
        input.setAnnoEsercizio ( annoEsercizio );
        input.setGenerico ( generico );
        input.setDataInizioValidita ( dataInizioValidita );
        input.setDataFineValidita ( dataFineValidita );
        input.setIdRiferimentoContabile ( idRiferimentoContabile );

        try {
            VerificaPresenzaRiferimentiContabiliOutput output = riferimentiContabiliService.verificaPresenzaRiferimentiContabili ( input );
            if ( !CODICE_OK.equals ( output.getCodiceEsito () ) ) {
                throw new OperationFailedException ( output.getDescrizioneEsito () );
            }
            messaggio_errore = output.getMessaggioErrore ();

        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        return messaggio_errore;

    }

    @Override
    public List<GenericVO> ricercaRiferimentiContabiliSecondariPerCov ( Long idCov )
                    throws OperationFailedException {

        RicercaRiferimentiContabiliSecondariPerCovInput input = new RicercaRiferimentiContabiliSecondariPerCovInput ();
        input.setIdCodiceVersamento ( idCov );
        RicercaRiferimentiContabiliSecondariPerCovOutput serviceOutput;

        try {
            serviceOutput = facade.ricercaRiferimentiContabiliSecondariPerCov ( input );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        List<GenericVO> output = new ArrayList<> ();

        for ( RicercaRiferimentoContabileOutputDto serviceOutputDto: serviceOutput.getRisultati () ) {
            GenericVO mappedDto = new GenericVO ();
            mappedDto.setId ( serviceOutputDto.getId () );
			String descrizione= serviceOutputDto.getDescrizioneDatoSpecificoRiscossioneRifCont  () + "- Anno Esercizio: " +
                            serviceOutputDto.getAnnoEsercizio ()+" - Validita' dal "+DateUtils.getStringDate ( serviceOutputDto.getDataInizioValidita () );
            if (null!= serviceOutputDto.getDataFineValidita ()) {
                descrizione= descrizione+" al " + DateUtils.getStringDate ( serviceOutputDto.getDataFineValidita () );
            }
            mappedDto.setDescrizione ( descrizione );
            output.add ( mappedDto );
        }

        return output;
    }

    @Override
    public Integer verificaNumeroRiferimentiContabiliInVitaPerCov ( Long idCov ) throws OperationFailedException {
        VerificaNumeroRiferimentiContabiliInVitaPerCovInput input = new VerificaNumeroRiferimentiContabiliInVitaPerCovInput ();
        input.setIdCodiceVersamento ( idCov );
        VerificaNumeroRiferimentiContabiliInVitaPerCovOutput serviceOutput;
        
        try {
            serviceOutput = riferimentiContabiliService.verificaNumeroRiferimentiContabiliInVitaPerCov ( input );
        } catch ( Exception e ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", e );
        }

        if ( !CODICE_OK.equals ( serviceOutput.getCodiceEsito () ) ) {
            throw new OperationFailedException ( serviceOutput.getDescrizioneEsito () );
        }

        return serviceOutput.getNumRiferimentiInVita ();
    }

}
