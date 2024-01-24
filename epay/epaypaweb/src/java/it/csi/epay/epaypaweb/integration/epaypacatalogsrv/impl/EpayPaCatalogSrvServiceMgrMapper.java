/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaypacatalogsrv.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.csi.epay.epaypaweb.dto.CategoriaCduDto;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneEpayPaCatalogSrvDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.dto.TematicaDto;
import it.csi.epay.epaypaweb.dto.UtenteCatalogDto;
import it.csi.epay.epaypaweb.integration.EPaypaIntegrationServiceBase;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetEntiAssociatiOutputDto;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteCorrenteCduOutputDto;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteCorrenteTematicaOutputDto;
import it.csi.epay.epaypaweb.integration.epaypacatalogsrv.dto.GetProfilazioneUtenteOutput;


public abstract class EpayPaCatalogSrvServiceMgrMapper {

	public static List<EnteDto> map ( List<GetEntiAssociatiOutputDto> input ) {

		List<EnteDto> output = new ArrayList<> ();
		for ( GetEntiAssociatiOutputDto entry: input ) {
			output.add ( new EnteDto (
				entry.getId ().intValue (),
				entry.getCodiceFiscale (),
				entry.getDenominazione (),
				entry.getEmail () ) );
		}

		return output;
	}

	public static ProfilazioneEpayPaCatalogSrvDto map ( GetProfilazioneUtenteOutput input ) {
		ProfilazioneEpayPaCatalogSrvDto output = new ProfilazioneEpayPaCatalogSrvDto ();

		if ( input.getEnte () != null ) {
			EnteDto ente = new EnteDto ( input.getEnte ().getId ().intValue (),
				input.getEnte ().getCodiceFiscale (),
				input.getEnte ().getDenominazione (),
				input.getEnte ().getEmail () );

			output.setEnte ( ente );
		}

		RuoloDto ruolo = new RuoloDto ( 0, "ROLE_DEFAULT" );
		ruolo.setDes ( "EpayPaCatalog Client Role" );
		output.setRuolo ( ruolo );

		ProfiloDto profilo = new ProfiloDto ( 0 );
		profilo.setDes ( "EpayPaCatalog Client Profile" );
		output.setProfilo ( profilo );

		output.getTematiche ().clear ();

		if ( input.getUtente () != null ) {
			UtenteCatalogDto utente = new UtenteCatalogDto (
				input.getUtente ().getId (),
				input.getUtente ().getCodiceFiscale () );

			utente.setNome ( input.getUtente ().getNome () );
			utente.setCognome ( input.getUtente ().getCognome () );
			utente.setNomeCompleto ( input.getUtente ().getNome () + " " + input.getUtente ().getCognome () );
			utente.setDataInizioValidita ( EPaypaIntegrationServiceBase.fromXMLGregorianCalendar ( input.getUtente ().getDataInizioValidita () ) );
			utente.setDataFineValidita ( EPaypaIntegrationServiceBase.fromXMLGregorianCalendar ( input.getUtente ().getDataFineValidita () ) );

			//EPAY-80
			utente
			.setDataInizioValiditaCurrent ( EPaypaIntegrationServiceBase.fromXMLGregorianCalendar ( input.getUtente ().getDataInizioValiditaCurrent () ) );
			utente.setDataFineValiditaCurrent ( EPaypaIntegrationServiceBase.fromXMLGregorianCalendar ( input.getUtente ().getDataFineValiditaCurrent () ) );

			output.setUtente ( utente );
		}

		if ( input.getTematiche () != null ) {
			Set<CodiceVersamentoDto> codiciVersamento = new HashSet<CodiceVersamentoDto> ();
			for ( GetProfilazioneUtenteCorrenteTematicaOutputDto tematica: input.getTematiche () ) {

				TematicaDto mappata = new TematicaDto ( tematica.getId ().intValue (), tematica.getCodice () );
				mappata.setDes ( tematica.getDescrizione () );
				mappata.setVisibilitaTotale ( tematica.isFlagVisibilitaTotale () != null && tematica.isFlagVisibilitaTotale () );
				output.getTematiche ().add ( mappata );

				if ( tematica.getCodiciVersamento () != null ) {

					for ( GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto cv: tematica.getCodiciVersamento () ) {
						CodiceVersamentoDto authCodiceVersamento = new CodiceVersamentoDto ( cv.getId ().intValue () );
						authCodiceVersamento.setCod ( cv.getCodice () );
						authCodiceVersamento.setDes ( cv.getDescrizione () );
						authCodiceVersamento.setEnteDto ( output.getEnte () );
						authCodiceVersamento.setFlagMbPrimario ( cv.isFlagMbPrimario () );
						authCodiceVersamento.setFlagMbSecondario ( cv.isFlagMbSecondario () );
						codiciVersamento.add ( authCodiceVersamento );
					}
				}
			}
			output.getCodiciVersamento ().addAll ( codiciVersamento );
		}

		if ( input.getUtente () != null && input.getUtente ().getCdu () != null ) {
			Set<String> codiciCategoriaGiaInseriti = new HashSet<> ();

			for ( GetProfilazioneUtenteCorrenteCduOutputDto cdu: input.getUtente ().getCdu () ) {
				CategoriaCduDto categoriaMapped = new CategoriaCduDto (
					cdu.getCodiceCategoria ().hashCode (),
					cdu.getCodiceCategoria () );
				categoriaMapped.setDes ( cdu.getDescrizioneCategoria () );

				CduDto authCdu = new CduDto (
					cdu.getId ().intValue (),
					cdu.getCodice (),
					categoriaMapped );
				authCdu.setDes ( cdu.getDescrizione () );

				output.getListaCdu ().add ( authCdu );

				/*
				 * per prevenire eventuali futuri problemi di sovrapposizione di codici
				 * aggiungo anche il cdu nel formato CODICE_CATEGORIA.CODICE_CDU
				 */
				authCdu = new CduDto (
					cdu.getId ().intValue (),
					categoriaMapped.getCod () + "." + cdu.getCodice (),
					categoriaMapped );
				authCdu.setDes ( cdu.getDescrizione () );

				output.getListaCdu ().add ( authCdu );

				if ( !codiciCategoriaGiaInseriti.contains ( cdu.getCodiceCategoria () ) ) {
					codiciCategoriaGiaInseriti.add ( cdu.getCodiceCategoria () );
					output.getCategorieCdu ().add ( categoriaMapped );
				}
			}

			output.getCategorieCdu ();

		}

		return output;
	}
}
