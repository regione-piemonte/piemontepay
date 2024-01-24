/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.common.Caches;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.DecodificaCodiciVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.DecodificaOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetMessaggiInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetMessaggiOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetMessaggiOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteCduCduOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteCduOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteTematicaOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaIntegrazioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniStatoAggiornamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaCleanOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipoPagamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaAccertamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaDatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoRifContabileSecondarioInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoRifContabileSecondarioOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.OpzioniCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.ppay.MacrotipoPPayVO;
import it.csi.epay.epaypacatalogweb.model.ppay.TematicaPPayVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCategoriaCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteCduCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioneAssociazioneUtenteTematicaTematicaVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteCduVO;
import it.csi.epay.epaypacatalogweb.model.utente.OpzioniAssociazioneUtenteTematicaVO;

@Service
public class DecodificheManagerImpl implements DecodificheManager {

    @Autowired
    private EpaypacatalogsrvFacade service;

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniPeriodicitaSchedulazioneRiconciliazione() {
        GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput opzioni;
        try {
            opzioni = service.getOpzioniPeriodicitaSchedulazioneRiconciliazione(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<GenericVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new GenericVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniTipologiaAccertamento() {
        GetOpzioniTipologiaAccertamentoOutput opzioni;
        try {
            opzioni = service.getOpzioniTipologiaAccertamento(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<GenericVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new GenericVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniModalitaAcquisizioneProvvisori() {
        GetOpzioniModalitaAcquisizioneProvvisoriOutput opzioni;
        try {
            opzioni = service.getOpzioniModalitaAcquisizioneProvvisori(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<GenericVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new GenericVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<TematicaPPayVO> getListaOpzioniTematicheVociEntrata() {
        GetOpzioniTematicaOutput opzioni;
        try {
            opzioni = service.getOpzioniTematica(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<TematicaPPayVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new TematicaPPayVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<TematicaPPayVO> getListaOpzioniTematicheVociEntrataClean() {
        GetOpzioniTematicaCleanOutput opzioni;
        try {
            opzioni = service.getOpzioniTematicaClean(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<TematicaPPayVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new TematicaPPayVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<MacrotipoPPayVO> getListaOpzioniMacrotipiVociEntrata() {
        GetOpzioniMacrotipoOutput opzioni;
        try {
            opzioni = service.getOpzioniMacrotipo(new GetOpzioniInput());
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<MacrotipoPPayVO> o = new ArrayList<>();
        for (DecodificaOutputDto opziome : opzioni.getOpzioni()) {
            o.add(new MacrotipoPPayVO(opziome.getId(), opziome.getCodice(), opziome.getDescrizione()));
        }
        return o;
    }

    @Override
    public List<OpzioniCodiceVersamentoVO> getListaOpzioniCodiceVersamento ( Boolean soloBase ) {
        GetOpzioniCodiceVersamentoOutput opzioni;
        try {
            GetOpzioniCodiceVersamentoInput input = new GetOpzioniCodiceVersamentoInput ();
            input.setSoloBase ( soloBase );
            opzioni = service.getOpzioniCodiceVersamento ( input );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException(e);
        }

        List<OpzioniCodiceVersamentoVO> o = new ArrayList<>();
        for (DecodificaCodiciVersamentoOutputDto opziome : opzioni.getOpzioni()) {        	
        		
        	OpzioniCodiceVersamentoVO codiceVersamento= new OpzioniCodiceVersamentoVO(opziome.getId(),
        			opziome.getCodice(), opziome.getDescrizione(), null);
        	codiceVersamento.setFlagMbPrimario(opziome.getFlagMbPrimario());
        	codiceVersamento.setFlagMbSecondario(opziome.getFlagMbSecondario());
        	codiceVersamento.setEnteSecondario(opziome.getEnteSecondario());
            	codiceVersamento.setCodiceVersamentoSecondario(opziome.getCodiceVersamentoSecondario());
            	codiceVersamento.setTipoPagamento(opziome.getTipoPagamento ());
            	codiceVersamento.setIdCodiceVersamentoSecondario(opziome.getIdCodiceVersamentoSecondario  ());
            	codiceVersamento.setDescrizioneCodiceVersamentoSecondario(
            	    opziome.getDescrizioneCodiceVersamentoSecondario  ());
            	
        	
        	
            o.add(codiceVersamento);
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniStatoAggiornamento () {
        GetOpzioniStatoAggiornamentoOutput opzioni;
        opzioni = service.getOpzioniStatoAggiornamento ( new GetOpzioniInput () );

        List<GenericVO> o = new ArrayList<> ();
        for ( DecodificaOutputDto opziome: opzioni.getOpzioni () ) {
            o.add ( new GenericVO ( opziome.getId (), opziome.getCodice (), opziome.getDescrizione () ) );
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniTipoPagamento () {
        GetOpzioniTipoPagamentoOutput opzioni;
        try {
            opzioni = service.getOpzioniTipoPagamento ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DecodificaOutputDto opziome: opzioni.getOpzioni () ) {
            o.add ( new GenericVO ( opziome.getId (), opziome.getCodice (), opziome.getDescrizione () ) );
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniTipologiaDatoSpecificoRiscossioneRiferimentoContabile () {
        GetOpzioniTipologiaDatoSpecificoRiscossioneOutput opzioni;
        try {
            opzioni = service.getOpzioniTipologiaDatoSpecificoRiscossione ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DecodificaOutputDto opziome: opzioni.getOpzioni () ) {
            o.add ( new GenericVO ( opziome.getId (), opziome.getCodice (), opziome.getDescrizione () ) );
        }
        return o;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName" )
    @Override
    public List<GenericVO> getListaOpzioniModalitaIntegrazione () {
        GetOpzioniModalitaIntegrazioneOutput opzioni;
        try {
            opzioni = service.getOpzioniModalitaIntegrazione  ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        List<GenericVO> o = new ArrayList<> ();
        for ( DecodificaOutputDto opziome: opzioni.getOpzioni () ) {
            o.add ( new GenericVO ( opziome.getId (), opziome.getCodice (), opziome.getDescrizione () ) );
        }
        return o;
    }

    @Override
    public OpzioniAssociazioneUtenteCduVO getListaOpzioniAssociazioneUtenteCdu () {
        GetOpzioniAssociazioneUtenteCduOutput opzioni;

        try {
            opzioni = service.getOpzioniAssociazioneUtenteCdu ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        OpzioniAssociazioneUtenteCduVO output = new OpzioniAssociazioneUtenteCduVO ();
        output.setCategorie ( new ArrayList<> () );

        if ( opzioni.getCategorie () != null ) {
            for ( GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto o: opzioni.getCategorie () ) {
                OpzioneAssociazioneUtenteCduCategoriaCduVO categoria = new OpzioneAssociazioneUtenteCduCategoriaCduVO ();

                categoria.setId ( o.getId () );
                categoria.setCodice ( o.getCodice () );
                categoria.setDescrizione ( o.getDescrizione () );
                categoria.setCdu ( new ArrayList<> () );

                if ( o.getCdu () != null ) {
                    for ( GetOpzioniAssociazioneUtenteCduCduOutputDto o2: o.getCdu () ) {

                        OpzioneAssociazioneUtenteCduCduVO cdu = new OpzioneAssociazioneUtenteCduCduVO ();
                        cdu.setId ( o2.getId () );
                        cdu.setCodice ( o2.getCodice () );
                        cdu.setDescrizione ( o2.getDescrizione () );

                        categoria.getCdu ().add ( cdu );
                    }
                }

                output.getCategorie ().add ( categoria );
            }
        }

        return output;
    }

    @Override
    public OpzioniAssociazioneUtenteTematicaVO getListaOpzioniAssociazioneUtenteTematica () {
        GetOpzioniAssociazioneUtenteTematicaOutput opzioni;

        try {
            opzioni = service.getOpzioniAssociazioneUtenteTematica ( new GetOpzioniInput () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        OpzioniAssociazioneUtenteTematicaVO output = new OpzioniAssociazioneUtenteTematicaVO ();
        output.setTematiche ( new ArrayList<> () );

        if ( opzioni.getTematiche () != null ) {
            for ( GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto o: opzioni.getTematiche () ) {
                OpzioneAssociazioneUtenteTematicaTematicaVO tematica = new OpzioneAssociazioneUtenteTematicaTematicaVO ();

                tematica.setId ( o.getId () );
                tematica.setCodice ( o.getCodice () );
                tematica.setDescrizione ( o.getDescrizione () );
                tematica.setCodiciVersamento ( new ArrayList<> () );

                if ( o.getCodiciVersamento () != null ) {
                    for ( GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto o2: o.getCodiciVersamento () ) {

                        OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO codiceVersamento = new OpzioneAssociazioneUtenteTematicaCodiceVersamentoVO ();
                        codiceVersamento.setId ( o2.getId () );
                        codiceVersamento.setCodice ( o2.getCodice () );
                        codiceVersamento.setDescrizione ( o2.getDescrizione () );

                        tematica.getCodiciVersamento ().add ( codiceVersamento );
                    }
                }

                output.getTematiche ().add ( tematica );
            }
        }

        return output;
    }

    @Cacheable ( value = Caches.DECODIFICHE, key = "#root.methodName + \"_\" + #codice" )
    @Override
    public String getTestoMessaggio ( String codice ) {

        GetMessaggiInput getMessaggiInput = new GetMessaggiInput ();
        GetMessaggiOutput output;

        try {
            output = service.getMessaggi ( getMessaggiInput );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }

        for ( GetMessaggiOutputDto messaggio: output.getMessaggi () ) {
            if ( messaggio.getCodiceBreve ().equals ( codice ) ) {
                return messaggio.getMessaggio ();
            }
        }

        return null;
    }
    
    @Override
	public List<GenericVO>  ricercaCodiceVersamentoRifContabileSecondario(Long idEnte){
		
		RicercaCodiceVersamentoRifContabileSecondarioInput input = new RicercaCodiceVersamentoRifContabileSecondarioInput();
		input.setIdEnte(idEnte);
		List<GenericVO>  output = new LinkedList <GenericVO> ();
		try {
			RicercaCodiceVersamentoRifContabileSecondarioOutput serviceOutput = service.ricercaCodiceVersamentoRifContabileSecondario(input);
			if (serviceOutput.getRisultati()!= null && serviceOutput.getRisultati().size()>0)
			{
				for (DecodificaCodiciVersamentoOutputDto serviceOutputDto : serviceOutput.getRisultati()) {
					GenericVO codice = new GenericVO ();
					codice.setId(serviceOutputDto.getId());
					codice.setDescrizione(serviceOutputDto.getCodice()+"- "+serviceOutputDto.getDescrizione ());
					output.add(codice);
				}
			}
			
		} catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new RuntimeException ( e );
        }
		return output;
	}


}
