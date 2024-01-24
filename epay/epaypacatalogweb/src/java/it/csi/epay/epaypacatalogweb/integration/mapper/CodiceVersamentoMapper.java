/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaypacatalogweb.frontend.util.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogweb.integration.mapper.stubs.CodiceVersamentoMapperStub;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoCollegatoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RicercaCodiceVersamentoFiltroVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.RisultatoRicercaCodiceVersamentoVO;


public abstract class CodiceVersamentoMapper extends ParentMapper {

	private static final String PRINCIPALE = "Principale";
    private static final String SECONDARIO = "Secondario";

    public static RicercaCodiceVersamentoInput map ( RicercaCodiceVersamentoFiltroVO input ) {
		RicercaCodiceVersamentoInput mapped = new RicercaCodiceVersamentoInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setCodiceVoceEntrata ( input.getCodiceVoceEntrata () );
		mapped.setCodiceMultibeneficiario ( input.getCodiceMultibeneficiario () );

		return mapped;
	}

	public static RisultatoRicercaCodiceVersamentoVO map ( RicercaCodiceVersamentoOutputDto input ) {
		RisultatoRicercaCodiceVersamentoVO mapped = new RisultatoRicercaCodiceVersamentoVO ();

		List<RicercaCodiceVersamentoOutputDto> collegatiBackup = new ArrayList<> ();
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
		mapped.setTematicaEsclusa ( input.isTematicaEsclusa () );
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		mapped.setFlagCodiceCorrentePostaleTesoreria(input.getIbanPostale());
		mapped.setFlagCodiceCorrentePostaleAppoggio(input.getIbanAppoggioPostale());
		mapped.setFattura ( input.getFattura () );
		mapped.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());
		mapped.setIdPadre(input.getIdPadre());
		
		if ( Boolean.TRUE.equals ( input.getFlagMbPrimario () )) {
		    mapped.setMultibeneficiario ( PRINCIPALE );
        }
		
		if ( Boolean.TRUE.equals ( input.getFlagMbSecondario () )) {
            mapped.setMultibeneficiario ( SECONDARIO );
        }

		if ( collegatiBackup != null ) {
			for ( RicercaCodiceVersamentoOutputDto collegato: collegatiBackup ) {
				mapped.setFlagPresenzaBollettinoPostale(collegato.getFlagPresenzaBollettinoPostale());
				mapped.setFlagCodiceCorrentePostaleTesoreria(collegato.getIbanPostale());
				mapped.setFlagCodiceCorrentePostaleAppoggio(collegato.getIbanAppoggioPostale());
				mapped.getCodiciVersamentoCollegati ().add ( map ( collegato ) );
			}
		}

		return mapped;
	}

	public static CodiceVersamentoVO map ( GetCodiceVersamentoOutputDto input ) {
		CodiceVersamentoVO mapped = new CodiceVersamentoVO ();

		try {
			CodiceVersamentoMapperStub stub = new CodiceVersamentoMapperStub ();
			PropertyUtils.copyProperties ( stub, input );
			PropertyUtils.copyProperties ( mapped, stub );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

//		mapped.setFlagInvioFlussi ( input.isFlagInvioFlussi () );
		mapped.setFlagCodiceCorrentePostaleTesoreria(input.getIbanPostale());
		mapped.setFlagCodiceCorrentePostaleAppoggio(input.getIbanAppoggioPostale());
		mapped.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		
		if (input.getFlagMbPrimario()!= null && input.getFlagMbPrimario())
		{
			mapped.setModalitaAssociazioneMultibeneficiario(TipoAssociazioneMultibeneficiario.PRIMARIO.getId ());
			mapped.setModalitaAssociazioneMultibeneficiarioOld(TipoAssociazioneMultibeneficiario.PRIMARIO.getId ());
			mapped.setMbModalita( TipoAssociazioneMultibeneficiario.PRIMARIO.getCode () );
		}
		else if (input.getFlagMbSecondario()!= null && input.getFlagMbSecondario())
		{
			mapped.setModalitaAssociazioneMultibeneficiario(TipoAssociazioneMultibeneficiario.SECONDARIO.getId ());
			mapped.setModalitaAssociazioneMultibeneficiarioOld(TipoAssociazioneMultibeneficiario.SECONDARIO.getId ());
			mapped.setMbModalita( TipoAssociazioneMultibeneficiario.SECONDARIO.getCode () );
		}
		mapped.setCovAssociatoAssociazioneMultibeneficiario(input.getIdCodiceVersamentoSecondarioCollegato());
		mapped.setCovAssociatoAssociazioneMultibeneficiarioOld(input.getIdCodiceVersamentoSecondarioCollegato());
		
		
	    
		mapped.setMbEnteSecondario( input.getMbEnteSecondario () );
		mapped.setMbCodiceVersamentoAssociato( input.getMbCodiceVersamentoAssociato () );

		mapped.setCodiciVersamentoCollegati ( new ArrayList<> () );

		if ( input.getCodiciVersamentoCollegati () != null ) {
			for ( GetCodiceVersamentoOutputDto cvc: input.getCodiciVersamentoCollegati () ) {
				CodiceVersamentoVO mappedCollegato = map ( cvc );
				mappedCollegato.setFlagCodiceCorrentePostaleTesoreria(cvc.getIbanPostale());
				mappedCollegato.setFlagCodiceCorrentePostaleAppoggio(cvc.getIbanAppoggioPostale());
				mappedCollegato.setFlagPresenzaBollettinoPostale(cvc.getFlagPresenzaBollettinoPostale());
				mappedCollegato.setCodiceVersamentoPadre ( mapped );
				mapped.getCodiciVersamentoCollegati ().add ( mappedCollegato );
			}
		}

		if ( input.getCodiceVersamentoPadre () != null ) {
			mapped.setCodiceVersamentoPadre ( map ( input.getCodiceVersamentoPadre () ) );
		}

		mapped.setCodiceModalitaIntegrazione ( input.getCodiceModalitaIntegrazione () );
		mapped.setDescrizioneErroreAggiornamento ( input.getDescrizioneErroreAggiornamento () );

		if ( StringUtils.isNotEmpty ( input.getDescrizioneErroreAggiornamento () ) ) {
			mapped.setDescrizioneStatoAggiornamento ( input.getDescrizioneStatoAggiornamento () + " - " + input.getDescrizioneErroreAggiornamento () );
		}
		
		if ((null!= input.getFlagMbPrimario() && input.getFlagMbPrimario())
				|| (null!= input.getFlagMbSecondario() && input.getFlagMbSecondario()))
		{
			mapped.setFlagElementiMultibeneficiario(Boolean.TRUE);
		}
		mapped.setEnteSecondarioAssociazioneMultibeneficiario(input.getIdEnteSecondarioCollegato());
		mapped.setCovAssociatoAssociazioneMultibeneficiario(input.getIdCodiceVersamentoSecondarioCollegato());
		
		mapped.setPassphrase ( input.getStrPassphrase () );
		
		mapped.setDataFineValidita (  input.getDtFineValidita ()  );
		mapped.setDataInizioValidita ( input.getDtInizioValidita () );
		return mapped;
	}

	public static InserisciCodiceVersamentoInput mapInserisci ( ModificaCodiceVersamentoVO input ) {
		InserisciCodiceVersamentoInput mapped = new InserisciCodiceVersamentoInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		mapped.setIbanAppoggioPostale(input.getFlagCodiceCorrentePostaleAppoggio());
		mapped.setIbanPostale ( input.getFlagCodiceCorrentePostaleTesoreria () );
		mapped.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());

		mapped.setFattura (input.getFattura ());
		mapped.setFlagMbPrimario (TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));
		mapped.setFlagMbSecondario (TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));
//		mapped.setFlagElementiMultibeneficiario (input.getFlagElementiMultibeneficiario());
//		mapped.setModalitaAssociazioneMultibeneficiario (input.getModalitaAssociazioneMultibeneficiario ());
		
		mapped.setStrPassphrase ( input.getPassphrase () );
		
		mapped.setDtInizioValidita (input.getDataInizioValidita ()!= null? dateToCalendar ( input.getDataInizioValidita () ) :null);
        mapped.setDtFineValidita (input.getDataFineValidita ()!= null? dateToCalendar ( input.getDataFineValidita () ):null );
		


		if ( input.getCodiceTipoPagamento () != null && input.getCodiceTipoPagamento ().equals ( "REDS" ) ) {
			mapped.setCodiceModalitaIntegrazione ( "SRV" );
		}

		return mapped;
	}

	public static InserisciCodiceVersamentoInput mapInserisci ( ModificaCodiceVersamentoCollegatoVO input, Long idCodiceVersamento ) {
		InserisciCodiceVersamentoInput mapped = new InserisciCodiceVersamentoInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		mapped.setIbanAppoggioPostale(input.getFlagCodiceCorrentePostaleAppoggio());
		mapped.setIbanPostale ( input.getFlagCodiceCorrentePostaleTesoreria () );
		mapped.setFlagPresenzaBollettinoPostale(new Boolean (input.getFlagPresenzaBollettinoPostale()));
		
		mapped.setFlagMbPrimario (TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));
        mapped.setFlagMbSecondario (TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));

		mapped.setIdCodiceVersamentoPadre ( idCodiceVersamento );
		mapped.setStrPassphrase ( input.getPassphrase () );
		
		 mapped.setCodiceTipoPagamento ( null );

		mapped.setDtInizioValidita (input.getDataInizioValidita ()!= null? dateToCalendar ( input.getDataInizioValidita () ) :null);
        mapped.setDtFineValidita (input.getDataFineValidita ()!= null? dateToCalendar ( input.getDataFineValidita () ):null );

		return mapped;
	}

	public static AggiornaCodiceVersamentoInput mapAggiorna ( ModificaCodiceVersamentoVO input ) {
		AggiornaCodiceVersamentoInput mapped = new AggiornaCodiceVersamentoInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		mapped.setIbanAppoggioPostale(input.getFlagCodiceCorrentePostaleAppoggio());
		mapped.setIbanPostale(input.getFlagCodiceCorrentePostaleTesoreria());
		mapped.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());
		mapped.setFattura(input.getFattura());
		mapped.setIdCovSecondarioAssocciato(input.getCovAssociatoAssociazioneMultibeneficiario ());
		mapped.setFlagMbPrimario (TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));
		mapped.setFlagMbSecondario (TipoAssociazioneMultibeneficiario.SECONDARIO.getId ().equals(input.getModalitaAssociazioneMultibeneficiario ()));
		mapped.setStrPassphrase ( input.getPassphrase () );
		


		if ( input.getCodiceTipoPagamento () != null && input.getCodiceTipoPagamento ().equals ( "REDS" ) ) {
			mapped.setCodiceModalitaIntegrazione ( "SRV" );
		}
		
		mapped.setDtInizioValidita (input.getDataInizioValidita ()!= null? dateToCalendar ( input.getDataInizioValidita () ) :null);
        mapped.setDtFineValidita (input.getDataFineValidita ()!= null? dateToCalendar ( input.getDataFineValidita () ):null );
		
		

		return mapped;
	}

	public static AggiornaCodiceVersamentoInput mapAggiorna ( ModificaCodiceVersamentoCollegatoVO input ) {
		AggiornaCodiceVersamentoInput mapped = new AggiornaCodiceVersamentoInput ();

		try {
			PropertyUtils.copyProperties ( mapped, input );
		} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new RuntimeException ( "errore nel parsing della risposta" );
		}

		mapped.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
		mapped.setFlagPresenzaBollettinoPostale(input.getFlagPresenzaBollettinoPostale());
		mapped.setBicAppoggio(input.getBicAppoggio());
		mapped.setIbanAppoggio(input.getIbanAppoggio());
		mapped.setIbanPostale(input.getFlagCodiceCorrentePostaleTesoreria());
		mapped.setIbanAppoggioPostale(input.getFlagCodiceCorrentePostaleAppoggio());
		mapped.setStrPassphrase ( input.getPassphrase () );
		mapped.setCodiceTipoPagamento ( null );
		mapped.setDtInizioValidita (input.getDataInizioValidita ()!= null? dateToCalendar ( input.getDataInizioValidita () ) :null);
        mapped.setDtFineValidita (input.getDataFineValidita ()!= null? dateToCalendar ( input.getDataFineValidita () ):null );
        mapped.setCodiceTipoPagamento ( null );
		

		return mapped;
	}

}
