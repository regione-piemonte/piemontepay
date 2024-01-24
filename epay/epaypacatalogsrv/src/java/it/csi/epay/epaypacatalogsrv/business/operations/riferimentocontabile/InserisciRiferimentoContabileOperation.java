/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.StatoMultibeneficiarioEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.ManagedException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.integration.stubs.richiediapplicationid.TipoPagamentoType;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoMultibeneficiarioRepository;
import it.csi.epay.epaypacatalogsrv.repository.StoricoRiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Operation ( consumes = InserisciRiferimentoContabileInput.class, produces = InserisciRiferimentoContabileOutput.class )
@Component
public class InserisciRiferimentoContabileOperation implements OperationHandler<InserisciRiferimentoContabileInput, InserisciRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository repository;

    @Autowired
    private StoricoRiferimentoContabileRepository storicoRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;
    
    @Autowired
    private StatoMultibeneficiarioRepository statoMultibeneficiarioRepository;
   
     @Autowired
    private TassonomiaRepository tassonomiaRepository;
    
  
    @Override
    public void authorize ( InserisciRiferimentoContabileInput input,
        OperationDispatchingContext<InserisciRiferimentoContabileInput, InserisciRiferimentoContabileOutput> context ) {
        SecurityUtils.assertUseCase ( Constants.USE_CASES.INSERISCI_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( InserisciRiferimentoContabileInput input,
    		OperationDispatchingContext<InserisciRiferimentoContabileInput, InserisciRiferimentoContabileOutput> context ) {

    	if ( input.getIdCodiceVersamento () == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "id codice versamento" );
    	}

    	if ( input.getIdCodiceVersamento () <= 0L ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id codice versamento" );
    	}

    	CodiceVersamento codiceVersamento = codiceVersamentoRepository.findOne ( input.getIdCodiceVersamento () );
    	if ( codiceVersamento == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id codice versamento" );
    	}

    	if (TipoPagamentoType.PS.value().equals(codiceVersamento.getTipoPagamento().getCodice())){
    		if ( input.getAnnoAccertamento () == null ) {
    			throw new BadRequestException ( Constants.MESSAGES.ANNO_ACCERTAMENTO_OBBLIGATORIO_PS );
    		}
    		if ( input.getNumeroAccertamento() == null ) {
    			throw new BadRequestException ( Constants.MESSAGES.NUMERO_ACCERTAMENTO_OBBLIGATORIO_PS );
    		}
		}
    	else{
    		if ( input.getAnnoAccertamento () != null && input.getNumeroAccertamento () == null ){
    			throw new BadRequestException ( Constants.MESSAGES.ANNO_ACCERTAMENTO_NUMERO_ACCERTAMENTO_VALORZZATI );
    		}
    		if ( input.getAnnoAccertamento () == null && input.getNumeroAccertamento () != null ){
    			throw new BadRequestException ( Constants.MESSAGES.NUMERO_ACCERTAMENTO_ANNO_ACCERTAMENTO_VALORZZATI );
    		}
    	}


    	if ( input.getDataInizioValidita () == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "data inizio validita'" );
    	}

    	if ( input.getDataFineValidita() == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "data fine validita'" );
    	}

    	if ( input.getAnnoEsercizio () == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "anno esercizio" );
    	}

    	if ( input.getAnnoEsercizio () < 1000 ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "anno esercizio" );
    	}

    	if ( input.getNumeroArticolo () != null && input.getNumeroArticolo () < 0 ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "numero articolo" );
    	}

    	if ( input.getDataFineValidita () != null ) {
    		if ( input.getDataInizioValidita () != null ) {
    			if ( input.getDataFineValidita ().before ( input.getDataInizioValidita () ) ) {
    				throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_DATA_FINE_DATA_INIZIO );
    			}
    		}

    		/*
    		 * DA ABILITARE SE DATA FINE DEVE ESSERE FUTURA LocalDate today = LocalDate.now (); LocalDate ldFineValidita = input.getDataFineValidita
    		 * ().toInstant() .atZone(ZoneId.systemDefault()) .toLocalDate(); if (ldFineValidita.isBefore ( today )) { throw new BadRequestException (
    		 * Constants.MESSAGES.RIFERIMENTO_CONTABILE_DATA_FINE_FUTURA ); }
    		 */
    	}

    	Tassonomia tassonomia= tassonomiaRepository.findOne(input.getIdTassonomia());
    	if ( tassonomia == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id tassonomia" );
    	}
    	if (tassonomia.getDatiSpecificiIncasso().length()!= 12)
    	{
    		throw new BadRequestException ( Constants.MESSAGES.DATO_SPECIFICO_INCASSO_NUM_CARATTERI);
    	}

    	if ( tassonomia.getDataFineValidita () != null ) {
    		if ( tassonomia.getDataInizioValidita () != null ) {
    			if ( tassonomia.getDataFineValidita ().before ( tassonomia.getDataInizioValidita () ) ) {
    				throw new BadRequestException ( Constants.MESSAGES.DT_INIZIO_FINE_VALIDITA_COD_TASSONOMICO_NON_CONGRUENTI);
    			}
    		}
    	}

		if ( Boolean.TRUE.equals ( input.getFlagAssociaRifContSecondario () )
						&& Boolean.TRUE.equals ( input.getFlagAssociaRifContPrimario () ) ) {
			throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_RIF_CONTABILE );
		}
		if ( Boolean.TRUE.equals ( input.getFlagAssociaRifContSecondario () ) &&
						null == input.getIdRifContabileSecondarioAssociato () ) {
			throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_RIF_CONTABILE );
		}
    	
    }

    @Override
    @Transactional
    public InserisciRiferimentoContabileOutput execute ( InserisciRiferimentoContabileInput input,
        OperationDispatchingContext<InserisciRiferimentoContabileInput, InserisciRiferimentoContabileOutput> context ) {
    	
         //RDI-41
         //Controllare che il riferimento contabile non sia duplicato
    	 Tassonomia tassonomia= tassonomiaRepository.findOne(input.getIdTassonomia());
         long numRifContabDup= repository.findRiferimentoDuplicato(input.getIdCodiceVersamento(), input.getDataInizioValidita(), 
         		input.getDataFineValidita(), tassonomia.getDatiSpecificiIncasso().substring(0,1), 
         		 tassonomia.getDatiSpecificiIncasso().substring(2),
         		 Calendar.getInstance().get(Calendar.YEAR), input.getNumeroAccertamento(), input.getAnnoAccertamento());
         if (numRifContabDup>0) {
         	 throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_DUPLICATO  ); 
         }
         
         //RDI-41 
         
         VerificaPresenzaRiferimentiContabiliInput verificaInput = new VerificaPresenzaRiferimentiContabiliInput();
         verificaInput.setAnnoEsercizio(input.getAnnoEsercizio());
         verificaInput.setIdCodiceVersamento(input.getIdCodiceVersamento());
         verificaInput.setGenerico(null== input.getAnnoAccertamento());
         verificaInput.setDataFineValidita ( input.getDataFineValidita () );
         verificaInput.setDataInizioValidita ( input.getDataInizioValidita () );
         verificaInput.setIdCodiceVersamento ( input.getIdCodiceVersamento () );
         verificaInput.setCaller ( input.getCaller () );
         VerificaPresenzaRiferimentiContabiliOutput verificaOutput = riferimentoContabileService.verificaPresenzaRiferimentiContabili(verificaInput);
         if (!StringUtils.isEmpty(verificaOutput.getMessaggioErrore() )  )
         {
        	 throw new BadRequestException ( !StringUtils.isEmpty ( verificaOutput.getMessaggioErrore() )?  
        	                 verificaOutput.getMessaggioErrore():verificaOutput.getDescrizioneEsito () ); 
         }
         
         
		RiferimentoContabile entity = map ( input, tassonomia);

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( entity.getCodiceVersamento () );
        
      //in caso di codice versamento spontaneo verificare che non ci sia un riferimento contabile in vita con lo stesso anno esercizio
        CodiceVersamento codiceVersamento = codiceVersamentoRepository.findOne ( input.getIdCodiceVersamento () );
        if (codiceVersamento.getTipoPagamento()!= null && TipoPagamentoType.PS.value().equals(codiceVersamento.getTipoPagamento().getCodice()))
        {
        	if (repository.findRiferimentoContabileDuplicatoPerCodiceVersamento(input.getIdCodiceVersamento (), input.getAnnoEsercizio(), entity.getDataInizioValidita (), entity.getDataFineValidita())>0)
        	{
        		 throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_ESISTENTE,input.getIdCodiceVersamento (), input.getAnnoEsercizio()  );
        	}
        }
        
        if (Boolean.TRUE.equals (codiceVersamento.getFlagMbPrimario ()) && Boolean.TRUE.equals (entity.getFlagMbSecondario  ())) {
            throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI);
        }
        
        if (Boolean.TRUE.equals (codiceVersamento.getFlagMbSecondario ()) && Boolean.TRUE.equals (entity.getFlagMbPrimario  ())) {
            throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI);
        }

		EsitoPropagazioneDTO risultatoPropagazioneParziale = null;

        RiferimentoContabile precedente = riferimentoContabileService.getPosizionePrecedente ( entity );

        if ( precedente != null ) {
            if ( input.getDataInizioValidita ().getTime () <= precedente.getDataInizioValidita ().getTime () ) {
                throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_INVALID_DATE_SEQUENCE );
            }
			// questo sarebbe da fare solo in modifica? //FIXME
            // eliminato per gestione di duplicazione riferimenti contabili non in vita
//            if ( precedente.getDataFineValidita () != null ) {
//                throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
//            }

            List<StoricoRiferimentoContabile> storiciDaAggiornare = precedente.getStorico ();

            entity.setId ( precedente.getId () );

            // storicizza posizione precedente
            StoricoRiferimentoContabile voceStorico = riferimentoContabileService.generaVoceStorico ( precedente );
            EntityUtils.populateEditFields ( voceStorico );
            Calendar cal = Calendar.getInstance ();
            cal.setTime ( entity.getDataInizioValidita () );
            cal.add ( Calendar.DATE, -1 );
            voceStorico.setDataFineValidita ( cal.getTime () );
            voceStorico.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
            voceStorico.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );

            storicoRepository.save ( voceStorico );
            
            risultatoPropagazioneParziale = propagazioneService.propagaRiferimentoContabile ( voceStorico, AzioneDaPropagare.MODIFICA );

            if ( risultatoPropagazioneParziale.getEsito () == EsitoPropagazione.KO ) {
                throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                    risultatoPropagazioneParziale.getMessaggio () );
            }

            voceStorico.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazioneParziale.getEsito ().name () ) );
            voceStorico.setDescrizioneErroreAggiornamento ( risultatoPropagazioneParziale.getMessaggio () );

            storicoRepository.save ( voceStorico );

            // aggiusto i riferimenti degli storici non ripristinabili
            if ( storiciDaAggiornare != null ) {
                for ( StoricoRiferimentoContabile storicoDaAggiornare: storiciDaAggiornare ) {
                    if ( !EntityUtils.isTrue ( storicoDaAggiornare.getFlagPosizionePrecedente () ) ) {
                        storicoDaAggiornare.setRiferimentoContabile ( null );
                        storicoDaAggiornare.setStoricoRiferimentoContabile ( voceStorico );
                        storicoRepository.save ( storicoDaAggiornare );
                    }
                }
            }

        }

		EntityUtils.populateEditFields ( entity );
        riferimentoContabileService.generaChiaveIntersistema ( entity );
        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        entity.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        repository.save ( entity );

        if ( entity.getId () == null ) {
            throw new ManagedException ();
        }

        EsitoPropagazioneDTO risultatoPropagazione;

        if ( risultatoPropagazioneParziale != null ) {
            risultatoPropagazione = propagazioneService.propagaRiferimentoContabile (
                entity,
                AzioneDaPropagare.INSERIMENTO,
                risultatoPropagazioneParziale.getIdTransazione (),
                true );
        } else {
            risultatoPropagazione = propagazioneService.propagaRiferimentoContabile ( entity, AzioneDaPropagare.INSERIMENTO );
        }

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        entity.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
        repository.save ( entity );
        
        	
        if ( null != codiceVersamento.getStatoMultibeneficiario ()
            && StatoMultibeneficiarioEnum.INCOMPLETE.getCodice ().equals ( codiceVersamento.getStatoMultibeneficiario ().getId () ) ) {
            codiceVersamento.setStatoMultibeneficiario ( statoMultibeneficiarioRepository.findOne ( StatoMultibeneficiarioEnum.OK.getCodice () ) );
        }
        codiceVersamentoRepository.save(codiceVersamento);
        

        // invia mail
        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( entity );
        }
        
        
        
        return InserisciRiferimentoContabileOutput.ok ( entity.getId () );
    }

    private void inviaMail ( RiferimentoContabile entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );
        parametri.put ( "azione_oggetto", "Inserimento" );
        parametri.put ( "azione", "inserito" );
        parametri.put ( "descrizione_ente", entity.getCodiceVersamento ().getEnte ().getDenominazione () );
        parametri.put ( "descrizione_codice_versamento", entity.getCodiceVersamento ().getCodice () + " - " + entity.getCodiceVersamento ().getDescrizione () );
        parametri.put ( "data_inizio_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataInizioValidita () ) );

        invioMailService.inviaMail ( EmailEnum.RIFERIMENTO_CONTABILE,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getCodiceVersamento ().getEnte ().getId (),
            parametri );
    }

    public RiferimentoContabile map ( InserisciRiferimentoContabileInput input, Tassonomia tassonomia ) {

        RiferimentoContabile output = new RiferimentoContabile ();
        EntityUtils.copyProperties ( output, input );
        output.setFlagMbPrimario(input.getFlagAssociaRifContSecondario ());
        output.setFlagMbSecondario(input.getFlagAssociaRifContPrimario ());
       
        if ( null != input.getAnnoAccertamento () && null != input.getNumeroAccertamento () ) {
            output.setDatoSpecificoRiscossione ( tassonomia.getDatiSpecificiIncasso ().substring ( 2 )
                + input.getAnnoAccertamento () + "." + input.getNumeroAccertamento () );
            output.setTassonomia ( tassonomia );
        } else {
            output.setDatoSpecificoRiscossione ( tassonomia.getDatiSpecificiIncasso ().substring ( 2 ) );
        }
        
        output.setCodiceTipologiaDatoSpecificoRiscossione(tassonomia.getDatiSpecificiIncasso().substring(0,1));
        output.setTassonomia(tassonomia);

        CodiceVersamento codiceVersamento = codiceVersamentoRepository.findOne ( input.getIdCodiceVersamento () );
        output.setCodiceVersamento ( codiceVersamento );

        if (null!= input.getIdRifContabileSecondarioAssociato()) {
        	RiferimentoContabile rifCont= repository.findOne(input.getIdRifContabileSecondarioAssociato());
        	if (null == rifCont) {
        		 throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Riferimento contabile secondario associato non esitente" );
        	}
        	output.getRiferimentiContabiliSecondariCollegati().add(rifCont);
        }

        output.setId ( null );
		output.setCodiceTributo ( input.getCodiceTributo () );

        return output;
    }
}
