/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.enums.StatoMultibeneficiarioEnum;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EncryptionDecryptionUtil;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ModalitaDiIntegrazioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipoPagamentoType;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneRiferimentoContabilePrimarioSecondario;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.TipoPagamento;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneCodiceVersamentoPrimarioSecondarioRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneRiferimentoContabilePrimarioSecondarioRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaIntegrazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoMultibeneficiarioRepository;
import it.csi.epay.epaypacatalogsrv.repository.StoricoCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipoPagamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = AggiornaCodiceVersamentoInput.class, produces = AggiornaCodiceVersamentoOutput.class )
@Component
public class AggiornaCodiceVersamentoOperation implements OperationHandler<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> {

    

    
    private static final String CONFIG_PROPERTIES = "config.properties";

    
    @Autowired
    private CodiceVersamentoRepository repository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private VoceEntrataRepository voceEntrataRepository;

    @Autowired
    private ModalitaIntegrazioneRepository modalitaIntegrazioneRepository;
    
    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private DecodificaService decodificaService;

    @Autowired
    private StoricoCodiceVersamentoRepository storicoCodiceVersamentoRepository;
    
    @Autowired
    private StatoMultibeneficiarioRepository statoMultibeneficiarioRepository;
    
    @Autowired
    private AssociazioneCodiceVersamentoPrimarioSecondarioRepository associazioneCodiceVersamentoPrimarioSecondarioRepository;
    
    @Autowired
    private AssociazioneRiferimentoContabilePrimarioSecondarioRepository associazioneRiferimentoContabilePrimarioSecondarioRepository;
    
    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;
    
    @Autowired
    private ConfigurazioneService configurazioneServices;
    
    
    

    @Override
    public void preValidateInput ( AggiornaCodiceVersamentoInput input,
        OperationDispatchingContext<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( AggiornaCodiceVersamentoInput input,
        OperationDispatchingContext<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public void validateInput ( AggiornaCodiceVersamentoInput input,
    		OperationDispatchingContext<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> context ) {

    	CodiceVersamento current = repository.findOne ( input.getId () );
    	
    	if ( current == null ) {
    		throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
    	}

    	if ( EntityUtils.isTrue ( current.getFlagAnnullato () ) ) {
    		throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
    	}

    	if ( StringUtils.isBlank ( input.getDescrizione () ) ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "descrizione" );
    	}

    	if ( !StringUtils.isBlank ( input.getIban () ) && StringUtils.isBlank ( input.getBic () ) ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "BIC" );
    	}

    	if ( !StringUtils.isBlank ( input.getBic () ) && StringUtils.isBlank ( input.getIban () ) ) {
    		throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "IBAN" );
    	}

    	if ( input.getFlagInvioFlussi () == null ) {
    		throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "flag invio flussi" );
    	}

    	if ( input.getFlagInvioFlussi () ) {
    		if ( StringUtils.isBlank ( input.getEmail () ) ) {
    			throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "email" );
    		}
    	}

    	if ( input.getEmail () != null ) {
    		if ( !EntityUtils.isValidEmail ( input.getEmail () ) ) {
    			throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "email" );
    		}
    	}

    	if ( current.getCodiceVersamentoPadre () != null ) {
    		if ( input.getCodiceVoceEntrata () != null ) {
    			throw new BadRequestException ( Constants.MESSAGES.FORBIDDEN_FIELD, "Voce Entrata" );
    		}
    		if ( input.getCodiceTipoPagamento () != null ) {
    			throw new BadRequestException ( Constants.MESSAGES.FORBIDDEN_FIELD, "Tipo Pagamento" );
    		}
    	} else {
    		if ( StringUtils.isBlank ( input.getCodiceVoceEntrata () ) ) {
    			throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Voce Entrata" );
    		}
    		if ( StringUtils.isBlank ( input.getCodiceTipoPagamento () ) ) {
    			throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Tipo Pagamento" );
    		}

    		if ( StringUtils.isBlank ( input.getIban () ) ) {
    			Ente enteCorrente = current.getEnte ();
    			if ( StringUtils.isBlank ( enteCorrente.getIban () ) ) {
    				throw new BadRequestException ( Constants.MESSAGES.IBAN_CV_OR_ENTE_OBBLIGATORIO );
    			}
    		}
    	}

    	if (Boolean.TRUE.equals(input.getFlagMbPrimario() )
    			&& Boolean.TRUE.equals(input.getFlagMbSecondario()) )
    	{
    		throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI );
    	}
    	if (Boolean.TRUE.equals(input.getFlagMbPrimario() ) && null==  input.getIdCovSecondarioAssocciato())
    	{
    		throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI );
    	}
    	
    	if (Boolean.FALSE.equals(input.getFlagMbPrimario() ) && null!=  input.getIdCovSecondarioAssocciato())
    	{
    		throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI );
    	}
    	
    	if (Boolean.TRUE.equals(input.getFlagMbPrimario() ) && Boolean.TRUE.equals(current.getFlagMbSecondario () ) )
    	    
    	{
    	    throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI );
    	}
    	
        if (Boolean.TRUE.equals(input.getFlagMbSecondario () ) && Boolean.TRUE.equals(current.getFlagMbPrimario () ) )
            
        {
            throw new BadRequestException ( Constants.MESSAGES.INCOERENZA_MULTIBENFICIARIO_COV_RIFERIMENTI );
        }
        
        
        if (Boolean.TRUE.equals ( input.getFlagPersonalizzazioneCov ()) ) {
            if (StringUtils.isEmpty(input.getDescrizioneTextCov ())  ) {
                throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Descrizione Testo Codice Versamento" );
            }
            
            if (!StringUtils.isEmpty(input.getDescrizioneTextCov ()) ) {
                
                ConfigurazioneVO conf= configurazioneServices.getParametro ( ParametriConfigurazione.CONFIG_MAX_NUM_CAR_DESC_COV_INVIO_NOTIFY );
                int maxNum =   Integer.parseInt ( conf.getValore () ) ;
                if (  input.getDescrizioneTextCov ().length ()>maxNum)
                {
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Descrizione Testo Codice Versamento" );
                }
                
            }
        }

    }

    @Override
    @Transactional
    public AggiornaCodiceVersamentoOutput execute ( AggiornaCodiceVersamentoInput input,
        OperationDispatchingContext<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> context ) {
        CodiceVersamento dtoOriginale = repository.findOne ( input.getId () );
        boolean isOriginalePrimario = Boolean.TRUE.equals(dtoOriginale.getFlagMbPrimario());
        boolean isOriginaleSecondario = Boolean.TRUE.equals(dtoOriginale.getFlagMbSecondario());
        

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( dtoOriginale );

        // storicizzo tutte le modifiche
        salvaVoceStorico ( dtoOriginale );

        String codiceTPOriginale = dtoOriginale.getTipoPagamento ().getCodice ();
        
        Long  idCodVersamentoSecondarioOriginale = codiceVersamentoSecondarioAssociatoPresente(dtoOriginale);

        Ente ente = enteRepository.findOneByCodiceFiscale ( SecurityUtils.getCurrentCodiceFiscaleEnte () );
        CodiceVersamento entity = map ( input, ente, context );

        EntityUtils.populateEditFields ( entity );

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( entity );
        
        //Se l'iban dell'ente coincide con l'iban nuovo
        // boolean ereditato = getEreditato ( ente.getIban (), input.getIban () );

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        entity.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        
        if ((Boolean.TRUE.equals(input.getFlagMbPrimario()) && !isOriginalePrimario)  || 
                        (Boolean.TRUE.equals(input.getFlagMbSecondario()) && !isOriginaleSecondario) )
        {
            entity.setStatoMultibeneficiario(statoMultibeneficiarioRepository.findOne(StatoMultibeneficiarioEnum.INCOMPLETE.getCodice()));
//            StatoMultibeneficiarioEnum.INCOMPLETE.getCodice()
        }
        if ((Boolean.FALSE.equals(input.getFlagMbPrimario()) && Boolean.FALSE.equals(input.getFlagMbSecondario())) )
        {
            entity.setStatoMultibeneficiario(statoMultibeneficiarioRepository.findOne(StatoMultibeneficiarioEnum.NONE.getCodice()));
        }

        if ((Boolean.FALSE.equals(input.getFlagMbPrimario()) && Boolean.FALSE.equals(input.getFlagMbSecondario())) ||
                        (null!= idCodVersamentoSecondarioOriginale && !idCodVersamentoSecondarioOriginale.equals ( input.getIdCovSecondarioAssocciato () ) ))
        {
            
            
          if (isOriginalePrimario || isOriginaleSecondario)
          {
              if (isOriginalePrimario && null!= idCodVersamentoSecondarioOriginale && !idCodVersamentoSecondarioOriginale.equals ( input.getIdCovSecondarioAssocciato () ) )
              {
                  entity.setStatoMultibeneficiario(statoMultibeneficiarioRepository.findOne(StatoMultibeneficiarioEnum.INCOMPLETE.getCodice()));
              }
              
              if (isOriginaleSecondario)
              {
                  associazioneCodiceVersamentoPrimarioSecondarioRepository.deleteByIdCodiceVersamentoSecondario(input.getId().intValue());
              }
              List<RiferimentoContabile> riferimenti= riferimentoContabileRepository.ricercaRiferimentiContabiliPrimariOSecondariPerCov(input.getId ());
              for (RiferimentoContabile rif : riferimenti)
              {
                  rif.setFlagMbPrimario(input.getFlagMbPrimario());
                  rif.setFlagMbSecondario(input.getFlagMbSecondario());
                  rif.setRiferimentiContabiliSecondariCollegati(null);
                  riferimentoContabileRepository.save(rif);
                  if(isOriginaleSecondario)
                  {
                      associazioneRiferimentoContabilePrimarioSecondarioRepository.deleteByIdRiferimentoContabileSecondario ( rif.getId ().intValue () );
                  }
                  
              }
          }
        }
        
        
        
        repository.save ( entity );
        


        if ( !EntityUtils.equals ( input.getCodiceTipoPagamento (), codiceTPOriginale ) ) {
            CodiceVersamento entityDto = repository.findOne ( input.getId () );
            for ( CodiceVersamento entityDtoCollegata: entityDto.getCodiciVersamentoCollegati () ) {
                entityDtoCollegata.setTipoPagamento ( entityDto.getTipoPagamento () );

                entityDtoCollegata.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
                entityDtoCollegata.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );

                repository.save ( entityDtoCollegata );

                EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaCodiceVersamento ( entityDtoCollegata, AzioneDaPropagare.MODIFICA );

                entityDtoCollegata.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
                entityDtoCollegata.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

                repository.save ( entityDtoCollegata );
            }
        }

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaCodiceVersamento ( entity, AzioneDaPropagare.MODIFICA );

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        entity.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

        repository.save ( entity );

        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( entity );
        }


        return AggiornaCodiceVersamentoOutput.ok ().conMessaggi ( context.getMessaggi () );
    }

    private Long  codiceVersamentoSecondarioAssociatoPresente ( CodiceVersamento dtoOriginale ) {
        if (!CollectionUtils.isEmpty ( dtoOriginale.getCodiciVersamentoSecondariCollegati () ))
        {
            return dtoOriginale.getCodiciVersamentoSecondariCollegati ().get ( 0 ).getId ();
            
        }
        return null;
        
    }

    /*
     * private boolean getEreditato ( String ibanEnte, String ibanCvNew ) { //In realta' il campo ha solo senso come "promemoria" per il record //la logica di
     * ereditarieta' sugli sparpagliatori e' locale a ognuno //di loro per non alterare il tracciato. Di base se il codice versamento //non ha un codice iban o
     * lo stesso e' uguale a quello dell'ente Boolean ereditato = false; if ( !StringUtils.isEmpty ( ibanEnte ) && !StringUtils.isEmpty ( ibanCvNew ) ) {
     * ereditato |= ibanEnte.equalsIgnoreCase ( ibanCvNew ); } ereditato |= StringUtils.isEmpty ( ibanCvNew ); return ereditato; }
     */

    private void inviaMail ( CodiceVersamento entity ) {
        Map<String, String> parametri = new HashMap<> ();
        parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );

        parametri.put ( "azione_oggetto", "Modifica" );
        parametri.put ( "azione", "modificato" );
        parametri.put ( "descrizione_ente", entity.getEnte ().getDenominazione () );
        parametri.put ( "descrizione_codice_versamento", entity.getCodice () + " - " + entity.getDescrizione () );

        invioMailService.inviaMail ( EmailEnum.CODICE_VERSAMENTO,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getEnte ().getId (),
            parametri );
    }

    public CodiceVersamento map ( AggiornaCodiceVersamentoInput input, Ente ente,
        OperationDispatchingContext<AggiornaCodiceVersamentoInput, AggiornaCodiceVersamentoOutput> context ) {

        CodiceVersamento output = repository.findOne ( input.getId () );

        if ( output.getCodiceVersamentoPadre () == null ) {
            if ( !input.getCodiceVoceEntrata ().equals ( output.getVoceEntrata ().getCodice () ) ) {
                if ( output.getCodiciVersamentoCollegati ().size () > 0 ) {
                    throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_VOCE_ENTRATA_NON_MODIFICABILE );
                } else {
                    if ( checkEsisteAttiva ( ente.getId (), input.getCodiceVoceEntrata () ) ) {
                        throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_ALREADY_EXISTING );
                    }
                    output.setCodice ( input.getCodiceVoceEntrata () );
                }
            }
        } else {

            // controllo che la descrizione sia diversa da quella del padre
            if ( input.getDescrizione ().trim ().equalsIgnoreCase ( output.getCodiceVersamentoPadre ().getDescrizione ().trim () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_DESCRIZIONE_UGUALE_PADRE );
            }

        }

        if ( output.getCodiceVersamentoPadre () == null ) {
            // e' un codice base

            output.setDescrizione ( input.getDescrizione () );
            output.setBic ( input.getBic () );
            output.setIban ( input.getIban () );
            output.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
            output.setEmail ( input.getEmail () );

            // CSI_PAG-289 Test RDI-10: modifica Codice Versamento
            output.setIbanAppoggio ( input.getIbanAppoggio () );
            output.setBicAppoggio ( input.getBicAppoggio () );
            output.setIbanPostale ( input.getIbanPostale () );
            output.setIbanAppoggioPostale ( input.getIbanAppoggioPostale () );
            output.setFlagPresenzaBollettinoPostale ( input.getFlagPresenzaBollettinoPostale () );
            output.setFattura ( input.getFattura () );

            VoceEntrata dtoVE = voceEntrataRepository.findOneByCodice ( input.getCodiceVoceEntrata () );
            if ( dtoVE != null ) {
                output.setVoceEntrata ( dtoVE );
            } else {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "voce entrata" );
            }

            TipoPagamento dtoTP = tipoPagamentoRepository.findOneByCodice ( input.getCodiceTipoPagamento () );
            if ( dtoTP != null ) {
                output.setTipoPagamento ( dtoTP );
            } else {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "tipo pagamento" );
            }

            if ( StringUtils.isBlank ( input.getIban () ) ) {
                output.setIban ( ente.getIban () );

                context.getMessaggi ().add ( decodificaService.getMessaggio (
                    Constants.MESSAGES.IBAN_CV_EREDITATO_DA_ENTE, ente.getIban () ) );
            }
            
            output.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov () );
            output.setDescrizioneTextCov ( input.getDescrizioneTextCov () );
            
            output.setDataInizioValidita  (input.getDtInizioValidita ()!=null? new Timestamp ( input.getDtInizioValidita ().getTime () ):null );
            output.setDataFineValidita (input.getDtFineValidita ()!=null? new Timestamp ( input.getDtFineValidita ().getTime () ):null );
            output.setFlagVisualizzaDaSportello ( input.getFlagVisualizzaDaSportello () );
            
//            if (SecurityUtils.hasUseCase ( Constants.USE_CASES.ASSISTENZA )  )
////                if (SecurityUtils.hasUseCase ( Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO ))
//                
//            {
//                if (!StringUtils.isEmpty ( input.getStrPassphrase () ))
//
//                { 
//                    Properties properties =  getProperties ();
//                    output.setPassphrase ( EncryptionDecryptionUtil.encrypt ( input.getStrPassphrase (), properties.getProperty ( PASSWORD_PASSPHRASE ) ).getBytes ());
//                }
//                else
//                {
//                    output.setPassphrase ( null );
//                }
//            }

           

        } else {
            // e' un codice collegato

            output.setDescrizione ( input.getDescrizione () );
            output.setBic ( input.getBic () );
            output.setIban ( input.getIban () );
            output.setFlagInvioFlussi ( input.getFlagInvioFlussi () );
            output.setEmail ( input.getEmail () );

            // CSI_PAG-289 Test RDI-10: modifica Codice Versamento
            output.setIbanAppoggio ( input.getIbanAppoggio () );
            output.setBicAppoggio ( input.getBicAppoggio () );
            output.setIbanPostale ( input.getIbanPostale () );
            output.setIbanAppoggioPostale ( input.getIbanAppoggioPostale () );
            output.setFlagPresenzaBollettinoPostale ( input.getFlagPresenzaBollettinoPostale () );
            output.setFattura ( input.getFattura () );
            
            output.setDescrizioneTextCov ( input.getDescrizioneTextCov () );
            output.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov () );

            output.setDataInizioValidita  (input.getDtInizioValidita ()!=null? new Timestamp ( input.getDtInizioValidita ().getTime () ):null );
            output.setDataFineValidita (input.getDtFineValidita ()!=null? new Timestamp ( input.getDtFineValidita ().getTime () ):null );
            output.setFlagVisualizzaDaSportello ( input.getFlagVisualizzaDaSportello () );
            
        }

        if ( input.getCodiceModalitaIntegrazione () != null ) {
            output.setModalitaIntegrazione (
                modalitaIntegrazioneRepository.findOneByCodice ( input.getCodiceModalitaIntegrazione () ) );

            if ( output.getModalitaIntegrazione () == null ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD,
                    "modalita' integrazione" );
            }
        } else {
            output.setModalitaIntegrazione ( null );
        }

        // RID 02 2019
        if ( output.getTipoPagamento () != null && output.getTipoPagamento ().getCodice ().equals ( TipoPagamentoType.REDS.name () ) ) {
            if ( output.getModalitaIntegrazione () == null
                || !output.getModalitaIntegrazione ().getCodice ().equals ( ModalitaDiIntegrazioneType.SRV.name () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "modalita' di integrazione" );
            }
        }

        if ( input.getFattura () == null ) {
            output.setFattura ( false );
        }
        
        output.setFlagMbPrimario(input.getFlagMbPrimario());
        output.setFlagMbSecondario(input.getFlagMbSecondario());
        
        if (null!= input.getIdCovSecondarioAssocciato())
        {
            if (CollectionUtils.isEmpty ( output.getCodiciVersamentoSecondariCollegati  () )
                            || !input.getIdCovSecondarioAssocciato().equals ( output.getCodiciVersamentoSecondariCollegati  ().get ( 0 ) .getId ()))
            {
                CodiceVersamento covSecondario= repository.findOne(input.getIdCovSecondarioAssocciato());
                if (null == covSecondario)
                {
                     throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Codice versamento secondario associato non esitente" );
                }
         
                List<CodiceVersamento> codiciSecondari= new ArrayList<CodiceVersamento> ();
                codiciSecondari.add ( covSecondario );
                output.setCodiciVersamentoSecondariCollegati (codiciSecondari);
            }
        	
        }
        else
        {
        	output.setCodiciVersamentoSecondariCollegati(null);
        }
        
        
        if (Boolean.FALSE.equals(input.getFlagMbPrimario()) && Boolean.FALSE.equals(input.getFlagMbPrimario()) )
        {
        	output.setStatoMultibeneficiario(statoMultibeneficiarioRepository.findOne(StatoMultibeneficiarioEnum.NONE.getCodice()));
        }
        

        
        
        if (SecurityUtils.hasUseCase ( Constants.USE_CASES.ASSISTENZA ))
//          if (SecurityUtils.hasUseCase ( Constants.USE_CASES.MODIFICA_CODICE_VERSAMENTO ))
//          if (SecurityUtils.hasUseCase ( "PROVA"))
      {
          Properties properties = null;
          if (!StringUtils.isEmpty ( input.getStrPassphrase () ) ||!StringUtils.isEmpty ( input.getStrCredenzialiPnd () )  )
          {
              properties =  getProperties ();
          }

          if (!StringUtils.isEmpty ( input.getStrPassphrase () ))
          {
              output.setPassphrase ( EncryptionDecryptionUtil.encrypt ( input.getStrPassphrase (), properties.getProperty (Constants.PASSWORD_PASSPHRASE ) ).getBytes ());
          }
          else
          {
              output.setPassphrase ( null );
          }

          if (!StringUtils.isEmpty ( input.getStrCredenzialiPnd () )  )
          {
              String credenziali = Base64.getEncoder ().encodeToString ( input.getStrCredenzialiPnd ().getBytes ()) ;
              output.setCredenzialiPnd ( EncryptionDecryptionUtil.encrypt ( credenziali, properties.getProperty ( Constants.PASSWORD_NOTIFICATION_PRICE ) ).getBytes ());
          }
          else
          {
              output.setCredenzialiPnd ( null );
          }
          
          output.setUrlAttualizzazionePnd ( input.getUrlAttualizzazionePnd (  ) );
      }
        return output;
    }

    private Properties getProperties () {
        Properties properties;
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);
            
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
        }
        return properties;
    }

    private boolean checkEsisteAttiva ( Long idEnte, String codiceVoce ) {
        List<CodiceVersamento> list = repository.findByEnte_IdAndCodice ( idEnte, codiceVoce );
        for ( CodiceVersamento listDto: list ) {
            if ( !EntityUtils.isTrue ( listDto.getFlagAnnullato () ) ) {
                return true;
            }
        }
        return false;
    }

    private void salvaVoceStorico ( CodiceVersamento attuale ) {
        StoricoCodiceVersamento storico = new StoricoCodiceVersamento ();

        EntityUtils.copyProperties ( storico, attuale );

        storico.setId ( null );
        storico.setCodiceVersamento ( attuale );

        storicoCodiceVersamentoRepository.save ( storico );
    }
    
//    UPDATE epaycat_t_codice_versamento  
//
//    SET flag_mb_primario= FALSE , 
//
//    flag_mb_Secondario= FALSE ,  
//
//    stato_mb_config = '3' 
//
//    WHERE id='$ id' 
//
//     
//
//    UPDATE epaycat_t_riferimento_contabile  
//
//    SET flag_mb_primario= FALSE , 
//
//    flag_mb_Secondario= FALSE  
//
//    WHERE id_codice_versamento='$ id' 
//
//     
//
//    DELETE 
//
//    FROM epaycat_r_codice_versamento_mb ercvm 
//
//    WHERE id_codice_versamento_primario='$ id_COV  
//
//    OR id_codice_versamento_secondario='$ id_COV 
//
//     
//
//    Eliminare le relazioni esistenti nelle tabelle che gestiscono le configurazioni dipendenti dal COV 
//
//    SELECT id FROM epaycat_t_riferimento_contabile as LIST 
//
//    WHERE id_codice_versamento='$ id' 
//
//     
//
//    DELETE 
//
//    FROM epaycat_r_riferimento_contabile_mb errcm 
//
//    WHERE (id_riferimento_contabile_primario IN(LIST) OR id_riferimento_contabile_secondario IN(LIST)) 

     

     
}
