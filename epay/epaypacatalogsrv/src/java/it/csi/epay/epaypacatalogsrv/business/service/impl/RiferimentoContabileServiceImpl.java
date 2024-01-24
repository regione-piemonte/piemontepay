/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service.impl;

import it.csi.epay.epaypacatalogsrv.api.util.CodiciEsito;
import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaNumeroRiferimentiContabiliInVitaPerCovOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.TipoPagamentoType;
import it.csi.epay.epaypacatalogsrv.model.*;
import it.csi.epay.epaypacatalogsrv.repository.*;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Component
@Transactional
public class RiferimentoContabileServiceImpl implements RiferimentoContabileService {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

	@Autowired
	private TassonomiaRepository tassonomiaRepository;

    @Autowired
    private StoricoRiferimentoContabileRepository storicoRepository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;
    
       
    @Autowired
    private DecodificaService decodificaService;
    
    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;
    
    @Autowired
    private EnteRepository enteRepository;

    @Override
    public RiferimentoContabile getPosizionePrecedente ( RiferimentoContabile entity ) {

        List<RiferimentoContabile> posizioni = getByIdCodiceVersamento ( entity );
        posizioni = filtraNonAnnullati ( posizioni );
        posizioni = filtraPerDiversoId ( posizioni, entity );
        posizioni = filtraPerAnnoEsercizio ( posizioni, entity.getAnnoEsercizio () );
        posizioni = filtraPerDatiSpecificiRiscossione ( posizioni, entity );

        if ( posizioni.size () < 1 ) {
            return null;
        } else if ( posizioni.size () > 1 ) {
            throw new RuntimeException (
                "posizione precedente non univoca : " + entity.getCodiceVersamento ().getId () + " - " + getHashDatiRiscossione ( entity ) );
        } else {
            return posizioni.get ( 0 );
        }
    }

    @Override
    public List<RiferimentoContabile> getAttiviByIdCodiceVersamento ( Long idCodiceVersamento ) {
        List<RiferimentoContabile> list = riferimentoContabileRepository.findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( idCodiceVersamento );
        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile dto: list ) {
            if ( EntityUtils.isTrue ( dto.getFlagAnnullato () ) ) {
                continue;
            }

            output.add ( dto );
        }

        return output;
    }

    private String getHashDatiRiscossione ( RiferimentoContabile entity ) {
        String h = ( entity.getTassonomia() != null &&  entity.getTassonomia().getDatiSpecificiIncasso() != null ?
                        entity.getTassonomia().getDatiSpecificiIncasso().substring(0,1) : 
                            entity.getCodiceTipologiaDatoSpecificoRiscossione () ) + //TODO, da modificare e sostituire con stringa vuota dopo la bonifica
            "/" +
            ( entity.getDatoSpecificoRiscossione () != null ? entity.getDatoSpecificoRiscossione ().trim () : "" );

        return h.toUpperCase ();
    }

    private List<RiferimentoContabile> getByIdCodiceVersamento ( RiferimentoContabile entity ) {
		return riferimentoContabileRepository.findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( entity.getCodiceVersamento ().getId () );
    }

    private List<StoricoRiferimentoContabile> filtraSoloPosizioniPrecedenti ( List<StoricoRiferimentoContabile> inputList ) {
        List<StoricoRiferimentoContabile> output = new ArrayList<> ();

        for ( StoricoRiferimentoContabile input: inputList ) {
            if ( EntityUtils.isTrue ( input.getFlagPosizionePrecedente () ) ) {
                output.add ( input );
            }
        }

        return output;
    }

    private List<RiferimentoContabile> filtraNonAnnullati ( List<RiferimentoContabile> inputList ) {
        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile input: inputList ) {
            if ( !EntityUtils.isTrue ( input.getFlagAnnullato () ) ) {
                output.add ( input );
            }
        }

        return output;
    }

    private List<RiferimentoContabile> filtraPerDatiSpecificiRiscossione ( List<RiferimentoContabile> inputList, RiferimentoContabile filtro ) {
        String entityHash = getHashDatiRiscossione ( filtro );

        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile input: inputList ) {
            if ( getHashDatiRiscossione ( input ).equals ( entityHash ) ) {
                output.add ( input );
            }
        }

        return output;
    }

    private List<RiferimentoContabile> filtraPerAnnoEsercizio ( List<RiferimentoContabile> inputList, Integer annoEsercizio ) {
        if ( annoEsercizio == null ) {
            annoEsercizio = -1;
        }

        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile input: inputList ) {
            if ( annoEsercizio.equals ( input.getAnnoEsercizio () ) ) {
                output.add ( input );
            }
        }

        return output;
    }

    private List<RiferimentoContabile> filtraPerDiversoId ( List<RiferimentoContabile> inputList, RiferimentoContabile filtro ) {

        if ( filtro.getId () == null ) {
            return inputList;
        }

        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile input: inputList ) {
            if ( !input.getId ().equals ( filtro.getId () ) ) {
                output.add ( input );
            }
        }

        return output;
    }

    @Override
    public StoricoRiferimentoContabile generaVoceStorico ( RiferimentoContabile entity ) {
        StoricoRiferimentoContabile o = new StoricoRiferimentoContabile ();

        EntityUtils.copyProperties ( o, entity );

        o.setId ( null );
        o.setRiferimentoContabile ( entity );
        o.setFlagPosizionePrecedente ( true );
        o.setStorico ( new ArrayList<> () );
        o.setStoricoRiferimentoContabile ( null );

        return o;
    }

    @Override
    public StoricoRiferimentoContabile salvaVoceStoricoPerModifica ( RiferimentoContabile entity ) {
        StoricoRiferimentoContabile o = new StoricoRiferimentoContabile ();

        EntityUtils.copyProperties ( o, entity );

        o.setId ( null );
        o.setRiferimentoContabile ( entity );
        o.setFlagPosizionePrecedente ( false );

        o.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        o.setDescrizioneErroreAggiornamento ( "Le voci di storico non ripristinabili non sono soggette a propagazione" );

        o.setStoricoRiferimentoContabile ( null );
        o.setStorico ( new ArrayList<> () );

        return storicoRepository.save ( o );
    }

    @Override
    public RiferimentoContabile generaVoceDaStorico ( StoricoRiferimentoContabile entity ) {
        RiferimentoContabile o = new RiferimentoContabile ();

        EntityUtils.copyProperties ( o, entity );

        o.setId ( null );

        return o;
    }

    @Override
    public String generaChiaveIntersistema ( RiferimentoContabile entity ) {
        String chiave = UUID.randomUUID ().toString ();
        entity.setChiaveIntersistema ( chiave );
        return chiave;
    }

    @Override
    public List<StoricoRiferimentoContabile> getStoricoPosizioniPrecedenti ( RiferimentoContabile entity ) {
        if ( entity.getId () == null ) {
            return Collections.emptyList ();
        }

        List<StoricoRiferimentoContabile> list = storicoRepository.findByRiferimentoContabileOrderByIdDesc ( entity );
        list = filtraSoloPosizioniPrecedenti ( list );

        return list;
    }

    @Override
    public List<RiferimentoContabile> getInVitaByIdCodiceVersamento ( Long idCodiceVersamento ) {
        List<RiferimentoContabile> list = riferimentoContabileRepository.findByCodiceVersamento_IdOrderByDataInizioValiditaDesc ( idCodiceVersamento );
        List<RiferimentoContabile> output = new ArrayList<> ();

        for ( RiferimentoContabile dto: list ) {
            if ( EntityUtils.isTrue ( dto.getFlagAnnullato () ) ) {
                continue;
            }

            if ( EntityUtils.isRiferimentoInVita ( dto ) ) {
                output.add ( dto );
            }
        }
        return output;
    }
    


	@Override
	public VerificaPresenzaRiferimentiContabiliOutput verificaPresenzaRiferimentiContabili(
			VerificaPresenzaRiferimentiContabiliInput entity) {

		VerificaPresenzaRiferimentiContabiliOutput output= new VerificaPresenzaRiferimentiContabiliOutput();
		RicercaRiferimentoContabileInput ricercaInput = new RicercaRiferimentoContabileInput();

		try {
			Assert.notNull ( entity, "Oggetto di inputo non valorizzato." );
			Assert.notNull ( entity.getIdCodiceVersamento(), "Codice versamento  non valorizzato" );
			Assert.notNull ( entity.getAnnoEsercizio(), "Ente non valorizzato" );
			Assert.notNull ( entity.getDataInizioValidita (), "Data inizio validita non valorizzata" );
			Assert.notNull ( entity.getDataFineValidita (), "Data fine validita non valorizzata" );
			Assert.notNull ( entity.getCaller (), "Chiamante non valorizzato" );
			Assert.notNull ( entity.getCaller ().getPrincipal (), "Chiamante non valorizzato" );
			Assert.notNull ( entity.getCaller ().getPrincipal ().getCodiceEnte (), "Chiamante non valorizzato" );
		} catch ( IllegalArgumentException e1 ) {
			output.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
			output.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
			return output;
		}
		ricercaInput.setIdCodiceVersamento(entity.getIdCodiceVersamento());
		ricercaInput.setAnnoEsercizio(entity.getAnnoEsercizio());
	    ricercaInput.setCaller(entity.getCaller());
//	    ricercaInput.setSoloRiferimentiInVita (Boolean.TRUE);
		try {
		    
		    CodiceVersamento cv = codiceVersamentoRepository.findOne ( ricercaInput.getIdCodiceVersamento () );
		    if ( null== cv )
	        {
	            output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
	            output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
	            return output;
	        }
		    
		    Ente ente = enteRepository.findOneByCodiceFiscale ( entity.getCaller().getPrincipal ().getCodiceEnte () );
		    if ( null== ente )
            {
                output.setCodiceEsito ( CodiciEsito.DATI_NON_TROVATI.getCodice () );
                output.setDescrizioneEsito ( CodiciEsito.DATI_NON_TROVATI.getMessaggio () );
                return output;
            }
        
		    
//		    List<RiferimentoContabile> riferimentiContabili= repository.ricerca ( ricercaInput, ente.getId () );
		    List<RiferimentoContabile> riferimentiContabili=  riferimentoContabileRepository.ricercaRiferimentiContabiliSovrappostiPerCov  ( entity.getIdCodiceVersamento() ,
                entity.getDataInizioValidita (), entity.getDataFineValidita ()) ;


			if (!CollectionUtils.isEmpty ( riferimentiContabili ) )
			{
			    
			    
//			    Date now = new Date();
				if(StringUtils.equals ( TipoPagamentoType.PS.value(), cv.getTipoPagamento ().getCodice () )  )
				{
					
					for (RiferimentoContabile rif: riferimentiContabili)
					{
						if ( ((entity.getDataInizioValidita().compareTo ( rif.getDataInizioValidita () )<=0 && 
						                entity.getDataFineValidita ().compareTo ( rif.getDataInizioValidita () )>= 0  ) 
						                
						                || (entity.getDataInizioValidita().compareTo ( rif.getDataFineValidita () )<=0 && 
                                        entity.getDataFineValidita().compareTo ( rif.getDataFineValidita () )>= 0  ) 
						                ||
						                (rif.getDataInizioValidita().compareTo ( entity.getDataInizioValidita () )<=0
						                && rif.getDataFineValidita().compareTo ( entity.getDataFineValidita () )>= 0   ))
						                 && !rif.getId ().equals ( entity.getIdRiferimentoContabile () ) )
						    
						{
							output= VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
							
							output.setMessaggioErrore(String.format ( 
									decodificaService.getMessaggio(Constants.MESSAGES.RIFERIMENTO_PRESENTE)
									, cv.getCodice (), entity.getAnnoEsercizio() ));
							return output;
							
						}
					}
					

				}
//	          08/11/2022 su indicazione, in caso di modifica,quindi con id del riferimento contabile presente,  il controllo non va fatto
				else if (null!= entity.getIdRiferimentoContabile () && entity.getIdRiferimentoContabile ()>0)
				{
					int numRifGenerico=0;
					for (RiferimentoContabile rif: riferimentiContabili)
					{
						if (null== rif.getAnnoAccertamento() && null== rif.getNumeroAccertamento()
						                && ((entity.getDataInizioValidita().compareTo ( rif.getDataInizioValidita () )<=0 && 
				                                        entity.getDataFineValidita ().compareTo ( rif.getDataInizioValidita () )>= 0  ) 
				                                        
				                                        || (entity.getDataInizioValidita().compareTo ( rif.getDataFineValidita () )<=0 && 
				                                        entity.getDataFineValidita().compareTo ( rif.getDataFineValidita () )>= 0  ) 
				                                        ||
				                                        (rif.getDataInizioValidita().compareTo ( entity.getDataInizioValidita () )<=0
				                                        && rif.getDataFineValidita().compareTo ( entity.getDataFineValidita () )>= 0   )
						                                )
						                && !rif.getId ().equals ( entity.getIdRiferimentoContabile () ) )
						{
							numRifGenerico++;
						}
					}

//					if (numRifGenerico> 0  && ricercaOutput.getRisultati().get(0).getRiferimentiContabili().size()>1)
//					{
//						output= VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
//						output.setMessaggioErrore(String.format ( 
//								decodificaService.getMessaggio(Constants.MESSAGES.GENERICO_E_NON_GENERICO_PRESENTI)
//								, ricercaOutput.getRisultati().get(0).getCodice(), entity.getAnnoEsercizio() ));
//						return output;
//					}
					 if (numRifGenerico> 0  &&  entity.isGenerico())
					{
						output= VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
						output.setMessaggioErrore(String.format ( 
								decodificaService.getMessaggio(Constants.MESSAGES.GENERICO_PRESENTE)
								,cv.getCodice (), entity.getAnnoEsercizio() ));
						return output;
					}
					 
					
//					 if (entity.isGenerico() && ricercaOutput.getRisultati().get(0).getRiferimentiContabili().size()>1)
//					{
//						output= VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
//						output.setMessaggioErrore(String.format ( 
//								decodificaService.getMessaggio(Constants.MESSAGES.IMPOSSIBILE_INSERIMENTO_GENERICO)
//								, ricercaOutput.getRisultati().get(0).getCodice(), entity.getAnnoEsercizio() ));
//						return output;
//					}

				}
			}
//			08/11/2022 su indicazione, in caso di modifica,quindi con id del riferimento contabile presente,  il controllo non va fatto
			if (null!= entity.getIdRiferimentoContabile () && entity.getIdRiferimentoContabile ()>0 && Boolean.TRUE.equals ( cv.getFlagMbSecondario () ))
			{
//			    if (!CollectionUtils.isEmpty ( repository.ricercaRiferimentiContabiliSovrappostiPerCov  ( entity.getIdCodiceVersamento() , 
//			        entity.getDataInizioValidita (), entity.getDataFineValidita ()) ) )
			        
			        if (!CollectionUtils.isEmpty ( riferimentiContabili) )
	            {
	                output= VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
	                output.setMessaggioErrore(String.format ( 
	                        decodificaService.getMessaggio(Constants.MESSAGES.DOPPIO_RIFERIMENTO_SECONDARIO)
	                        , cv.getCodice (), entity.getAnnoEsercizio() ));
	                return output;
	            }
			    
			}
			
			
		} catch ( Exception e ) {
			logger.error ( String.format ( CodiciEsito.CODICE_ERRORE_GENERICO.getMessaggio ()), e );
			output.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_GENERICO.getCodice () );
			 output.setDescrizioneEsito (  CodiciEsito.CODICE_ERRORE_GENERICO.getCodice () );
			 return output;
		}


		return VerificaPresenzaRiferimentiContabiliOutput.ok(VerificaPresenzaRiferimentiContabiliOutput.class);
	}

    @Override
    public VerificaNumeroRiferimentiContabiliInVitaPerCovOutput
    verificaNumeroRiferimentiContabiliInVitaPerCov ( VerificaNumeroRiferimentiContabiliInVitaPerCovInput entity ) {

        VerificaNumeroRiferimentiContabiliInVitaPerCovOutput output= new VerificaNumeroRiferimentiContabiliInVitaPerCovOutput();

        try {
            Assert.notNull ( entity, "Oggetto di inputo non valorizzato." );
            Assert.notNull ( entity.getIdCodiceVersamento(), "Codice versamento  non valorizzato" );
            Assert.notNull ( entity.getCaller (), "Chiamante non valorizzato" );
            Assert.notNull ( entity.getCaller ().getPrincipal (), "Chiamante non valorizzato" );
            Assert.notNull ( entity.getCaller ().getPrincipal ().getCodiceEnte (), "Chiamante non valorizzato" );
        } catch ( IllegalArgumentException e1 ) {
            output.setCodiceEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getCodice () );
            output.setDescrizioneEsito ( CodiciEsito.DATI_INPUT_MANCANTI.getMessaggio () );
            return output;
        }

        try {
            List<RiferimentoContabile> riferimenti= riferimentoContabileRepository.ricercaRiferimentiContabiliInVitaPerCov  ( entity.getIdCodiceVersamento() ) ;
            output= VerificaNumeroRiferimentiContabiliInVitaPerCovOutput.ok(VerificaNumeroRiferimentiContabiliInVitaPerCovOutput.class);
            
            if (!CollectionUtils.isEmpty (riferimenti) )
            {
                output.setNumRiferimentiInVita ( riferimenti.size () );
            }
            else
            {
                output.setNumRiferimentiInVita ( 0 );
            }
        }
        catch ( Exception e ) {
            logger.error ( String.format ( CodiciEsito.CODICE_ERRORE_GENERICO.getMessaggio ()), e );
            output.setCodiceEsito ( CodiciEsito.CODICE_ERRORE_GENERICO.getCodice () );
            output.setDescrizioneEsito (  CodiciEsito.CODICE_ERRORE_GENERICO.getCodice () );
            return output;
        }


        return output;
    }

	@Override public List<RiferimentoContabile> getByIdTassonomia ( Long idTassonomia ) {
		return riferimentoContabileRepository.getByTassonomiaAndNotAnnullato ( idTassonomia );
	}

	@Override public void aggiornaDatoSpecificoRiscossione ( Long idTassonomia, String tassonomiaEsistente, String tassonomiaNuova ) {
		Tassonomia tassonomia = tassonomiaRepository.findById ( idTassonomia );
		List<RiferimentoContabile> riferimentiContabili = riferimentoContabileRepository.findByTassonomia ( tassonomia );
		for ( RiferimentoContabile rif : riferimentiContabili ) {
			if ( ( rif.getAnnoEsercizio () == Calendar.getInstance ().get ( Calendar.YEAR ) ) &&
							( rif.getFlagAnnullato () == null || !rif.getFlagAnnullato () ) &&
							( rif.getDataFineValidita () == null || rif.getDataFineValidita ().after ( new Date () ) )
			) {
				String nuovoDato = rif.getDatoSpecificoRiscossione ().replace ( tassonomiaEsistente, tassonomiaNuova );
				rif.setDatoSpecificoRiscossione ( nuovoDato );
				riferimentoContabileRepository.save ( rif );
			}
		}
	}
}
