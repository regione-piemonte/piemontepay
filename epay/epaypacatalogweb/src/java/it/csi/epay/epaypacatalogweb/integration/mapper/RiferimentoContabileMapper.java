/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import it.csi.epay.epaypacatalogweb.frontend.util.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogweb.integration.mapper.stubs.ModificaRiferimentoContabileInputMapperStub;
import it.csi.epay.epaypacatalogweb.integration.mapper.stubs.RiferimentoContabileMapperStub;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.*;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class RiferimentoContabileMapper extends ParentMapper {

	public static RicercaRiferimentoContabileInput map ( RicercaRiferimentoContabileFiltroVO input ) {
		RicercaRiferimentoContabileInput mapped = new RicercaRiferimentoContabileInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setSoloRiferimentiInVita (input.getAncheRiferimentiNonInVita() == null || !input.getAncheRiferimentiNonInVita());

		return mapped;
	}

	public static RisultatoRicercaRiferimentoContabileVO map ( RicercaRiferimentoContabileOutputDto input ) {
		RisultatoRicercaRiferimentoContabileVO mapped = new RisultatoRicercaRiferimentoContabileVO ();

		try {
			RiferimentoContabileMapperStub stub = new RiferimentoContabileMapperStub ();
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
		mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );

        mapped.setDataInizioValiditaCodiceTassonomico( calendarToDate ( input.getDataInizioValiditaCodiceTassonomico () ) ); 
        mapped.setDataFineValiditaCodiceTassonomico( calendarToDate ( input.getDataFineValiditaCodiceTassonomico () ) ); 
        
		mapped.setInVita ( isInVita ( mapped.getDataInizioValidita (), mapped.getDataFineValidita () ) );
		mapped.setFlagMbPrimario ( input.getFlagMbPrimario () );
		mapped.setFlagMbSecondario ( input.getFlagMbSecondario () );
		mapped.setStorico ( new ArrayList<> () );

		if ( input.getStorico () != null ) {
			for ( RicercaRiferimentoContabileStoricoOutputDto voceStorico: input.getStorico () ) {
				mapped.getStorico ().add ( map ( voceStorico, input ) );
			}
		}

		return mapped;
	}

	public static RisultatoRicercaRiferimentoContabileStoricoVO map ( RicercaRiferimentoContabileStoricoOutputDto input,
		RicercaRiferimentoContabileOutputDto inputPadre ) {
		RisultatoRicercaRiferimentoContabileStoricoVO mapped = new RisultatoRicercaRiferimentoContabileStoricoVO ();

		try {
			RiferimentoContabileMapperStub stub = new RiferimentoContabileMapperStub ();
			PropertyUtils.copyProperties ( stub, inputPadre );
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
		mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );
		mapped.setInVita ( isInVita ( mapped.getDataInizioValidita (), mapped.getDataFineValidita () ) );

		mapped.setIdRiferimentoContabilePadre ( inputPadre.getId () );

		return mapped;
	}

	public static RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO map ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto input ) {
		RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO mapped = new RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO ();

		List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> collegatiBackup = new ArrayList<> ();
		if ( input.getCodiciVersamentoCollegati () != null ) {
			collegatiBackup.addAll ( input.getCodiciVersamentoCollegati () );
			input.getCodiciVersamentoCollegati ().clear ();
		}

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setFlagInvioFlussi ( input.isFlagInvioFlussi () );
		mapped.setCodiciVersamentoCollegati ( new ArrayList<> () );

		mapped.setRiferimentiContabili ( new ArrayList<> () );

		if ( input.getRiferimentiContabili () != null ) {
			for ( RicercaRiferimentoContabileOutputDto rc: input.getRiferimentiContabili () ) {
				mapped.getRiferimentiContabili ().add ( map ( rc ) );
			}
		}

		for ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto collegato : collegatiBackup ) {
			mapped.getCodiciVersamentoCollegati ().add ( map ( collegato ) );
		}

		return mapped;
	}

	public static RiferimentoContabileVO map ( GetRiferimentoContabileOutputDto input ) {
		RiferimentoContabileVO mapped = new RiferimentoContabileVO ();

		try {
			RiferimentoContabileMapperStub stub = new RiferimentoContabileMapperStub ();
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
		mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );
        mapped.setDataInizioValiditaCodiceTassonomico( calendarToDate ( input.getDataInizioValiditaCodiceTassonomico() ) );
        mapped.setDataFineValiditaCodiceTassonomico ( calendarToDate ( input.getDataFineValiditaCodiceTassonomico() ) );
		mapped.setInVita ( isInVita ( mapped.getDataInizioValidita (), mapped.getDataFineValidita () ) );
		mapped.setDescrizioneErroreAggiornamento ( input.getDescrizioneErroreAggiornamento () );
		mapped.setFlagElementiMultibeneficiario ( input.getFlagElementiMultibeneficiario () );
		mapped.setMbModalita ( input.getMbModalita () );
		mapped.setMbEnteSecondario ( input.getMbEnteSecondario () );
		mapped.setEnteSecondarioAssociato ( input.getMbEnteSecondario () );
		mapped.setMbCodiceVersamentoAssociato ( input.getMbRiferimentoContabileAssociato () );
		mapped.setCovSecondarioAssociato ( input.getMbCodiceVersamentoAssociato () );
		mapped.setRifContabileSecondarioAssociato (  input.getMbRiferimentoContabileAssociato ()  );
		mapped.setCodiceTipologiaDatoSpecificoRiscossione(input.getCodiceTipologiaDatoSpecificoRiscossione ());
		mapped.setCodiceDatoSpecificoRiscossione ( input.getCodiceTipoServizioTassonomia () );
		mapped.setIdTassonomia ( input.getIdTassonomia () );

		mapped.setFlagAssociaRifContPrimarioValue(TipoAssociazioneMultibeneficiario.SECONDARIO.getCode ().equals ( input.getMbModalita () ));
        mapped.setFlagAssociaRifContSecondarioValue(TipoAssociazioneMultibeneficiario.PRIMARIO.getCode ().equals ( input.getMbModalita () ));

		if ( StringUtils.isNotEmpty ( input.getDescrizioneErroreAggiornamento () ) ) {
			mapped.setDescrizioneStatoAggiornamento ( mapped.getDescrizioneStatoAggiornamento () + " - " + input.getDescrizioneErroreAggiornamento () );
		}

		mapped.setStorico ( new ArrayList<> () );

		if ( input.getStorico () != null ) {
			for ( GetRiferimentoContabileStoricoOutputDto voceStorico: input.getStorico () ) {
				mapped.getStorico ().add ( map ( voceStorico, input ) );
			}
		}
		mapped.setCodiceTributo ( input.getCodiceTributo () );

		return mapped;
	}

	public static RiferimentoContabileStoricoVO map ( GetRiferimentoContabileStoricoOutputDto input, GetRiferimentoContabileOutputDto inputPadre ) {
		RiferimentoContabileStoricoVO mapped = new RiferimentoContabileStoricoVO ();

		try {
			RiferimentoContabileMapperStub stub = new RiferimentoContabileMapperStub ();
			PropertyUtils.copyProperties ( stub, inputPadre );
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setDataInizioValidita ( calendarToDate ( input.getDataInizioValidita () ) );
		mapped.setDataFineValidita ( calendarToDate ( input.getDataFineValidita () ) );
		mapped.setInVita ( isInVita ( mapped.getDataInizioValidita (), mapped.getDataFineValidita () ) );
		mapped.setDescrizioneErroreAggiornamento ( input.getDescrizioneErroreAggiornamento () );

		if ( StringUtils.isNotEmpty ( input.getDescrizioneErroreAggiornamento () ) ) {
			mapped.setDescrizioneStatoAggiornamento ( mapped.getDescrizioneStatoAggiornamento () + " - " + input.getDescrizioneErroreAggiornamento () );
		}
		mapped.setCodiceTributo ( input.getCodiceTributo () );

		return mapped;
	}

	public static InserisciRiferimentoContabileInput mapInserisci ( ModificaRiferimentoContabileVO input ) {
		InserisciRiferimentoContabileInput mapped = new InserisciRiferimentoContabileInput ();

		try {
			ModificaRiferimentoContabileInputMapperStub stub = new ModificaRiferimentoContabileInputMapperStub ();
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}
		mapped.setDataInizioValidita ( dateToCalendar ( input.getDataInizioValidita () ) );
		mapped.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );
		mapped.setFlagAssociaRifContPrimario(input.getFlagAssociaRifContPrimarioValue ());
		mapped.setFlagAssociaRifContSecondario(input.getFlagAssociaRifContSecondarioValue ());
		mapped.setFlagElementiMultibeneficiario ( Boolean.TRUE.equals(input.getFlagAssociaRifContPrimarioValue())
			|| Boolean.TRUE.equals(input.getFlagAssociaRifContSecondarioValue() ));
		mapped.setCodiceTributo ( input.getCodiceTributo () );

		return mapped;
	}

	public static AggiornaRiferimentoContabileInput mapAggiorna ( ModificaRiferimentoContabileVO input ) {
		AggiornaRiferimentoContabileInput mapped = new AggiornaRiferimentoContabileInput ();

		try {
			ModificaRiferimentoContabileInputMapperStub stub = new ModificaRiferimentoContabileInputMapperStub ();
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setDataFineValidita ( dateToCalendar ( input.getDataFineValidita () ) );
		mapped.setNumeroCapitolo ( input.getNumeroCapitolo () );
		mapped.setLivelloPdc ( input.getLivelloPdc () );
		mapped.setTitolo ( input.getTitolo () );
		mapped.setTipologia ( input.getTipologia () );
		mapped.setCategoria ( input.getCategoria () );
		mapped.setNumeroArticolo ( input.getNumeroArticolo () );
		mapped.setDatoSpecificoRiscossione ( input.getDatoSpecificoRiscossione () );
		mapped.setDescrizioneDatoSpecificoRiscossione ( input.getDescrizioneDatoSpecificoRiscossione () );
		mapped.setCodiceTributo ( input.getCodiceTributo () );

		return mapped;
	}

	private static boolean isInVita ( Date dataInizio, Date dataFine ) {
		if ( dataInizio == null ) {
			return false;
		}
		if ( dataFine == null ) {
			return true;
		}
		LocalDate now = LocalDate.now ();
		LocalDate fine = dataFine.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
		return fine.isAfter ( now ) || fine.isEqual ( now );
	}

}
