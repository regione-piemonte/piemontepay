/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.business.enums.StatoMultibeneficiarioEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogsrv.business.service.ConfigurazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.DecodificaService;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EncryptionDecryptionUtil;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.ManagedException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.ModalitaDiIntegrazioneType;
import it.csi.epay.epaypacatalogsrv.integration.stubs.coopapplicativapec.TipoPagamentoType;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.TipoPagamento;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaIntegrazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoMultibeneficiarioRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipoPagamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.VoceEntrataRepository;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = InserisciCodiceVersamentoInput.class, produces = InserisciCodiceVersamentoOutput.class )
@Component
public class InserisciCodiceVersamentoOperation implements OperationHandler<InserisciCodiceVersamentoInput, InserisciCodiceVersamentoOutput> {


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
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private DecodificaService decodificaService;
    
    @Autowired
    private StatoMultibeneficiarioRepository statoMultibeneficiarioRepository;
    
    @Autowired
    private ConfigurazioneService configurazioneServices;
   
    

    @Override
    public void authorize ( InserisciCodiceVersamentoInput input,
        OperationDispatchingContext<InserisciCodiceVersamentoInput, InserisciCodiceVersamentoOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.INSERISCI_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( InserisciCodiceVersamentoInput input,
        OperationDispatchingContext<InserisciCodiceVersamentoInput, InserisciCodiceVersamentoOutput> context ) {

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
            //throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "flag invio flussi" );
        	input.setFlagInvioFlussi(false);
        }

        else if ( input.getFlagInvioFlussi () ) {
            if ( StringUtils.isBlank ( input.getEmail () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "email" );
            }
        }

        if ( input.getEmail () != null ) {
            if ( !EntityUtils.isValidEmail ( input.getEmail () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "email" );
            }
        }

        if ( input.getIdCodiceVersamentoPadre () != null ) {
            if ( input.getIdCodiceVersamentoPadre () <= 0L ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID codice versamento padre" );
            }

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
                Ente enteCorrente = enteRepository.findOneByCodiceFiscale ( SecurityUtils.getCurrentCodiceFiscaleEnte () );
                if ( StringUtils.isBlank ( enteCorrente.getIban () ) ) {
                    throw new BadRequestException ( Constants.MESSAGES.IBAN_CV_OR_ENTE_OBBLIGATORIO );
                }
            }
        }
        
        
        if ( Boolean.TRUE.equals ( input.getFlagElementiMultibeneficiario () ) ) {
            if ( StringUtils.isEmpty ( input.getModalitaAssociazioneMultibeneficiario () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Modalita Associazione Multibeneficiario" );
            }

            else if ( TipoAssociazioneMultibeneficiario.PRIMARIO.getId ().equals ( input.getModalitaAssociazioneMultibeneficiario () ) ) {
                if ( null == input.getEnteSecondarioAssociazioneMultibeneficiario () ) {
                    throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Ente Secondario" );
                }

                if ( null == input.getCovAssociatoAssociazioneMultibeneficiario () ) {
                    throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "Codice Versamento Secondario" );
                }
            }
        }
        else
        {
        	if (input.getFlagMbPrimario() || input.getFlagMbSecondario())
        	{
        		 throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Flag Multi Beneficiario" );
        		 
        		
        	}
        	if (null != input.getEnteSecondarioAssociazioneMultibeneficiario () || null!= input.getCovAssociatoAssociazioneMultibeneficiario())
        	{
        		 throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Codice versamento secondario associato" );
        	}
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
    public InserisciCodiceVersamentoOutput execute ( InserisciCodiceVersamentoInput input,
        OperationDispatchingContext<InserisciCodiceVersamentoInput, InserisciCodiceVersamentoOutput> context ) {
        Ente enteCorrente = enteRepository.findOneByCodiceFiscale ( SecurityUtils.getCurrentCodiceFiscaleEnte () );
        CodiceVersamento entity = map ( input, enteCorrente, context );
        

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        entity.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );

        EntityUtils.populateEditFields ( entity );

        // l'utente deve avere visibilita' totale sulla tematica
        SecurityUtils.assertVisibilitaTotaleSuTematica ( entity.getVoceEntrata ().getTematica ().getCodice () );

        entity = repository.save ( entity );

        if ( entity.getId () == null ) {
            throw new ManagedException ();
        }

        EsitoPropagazioneDTO risultatoPropagazione = null;
//        if ( Boolean.FALSE.equals ( input.getFlagElementiMultibeneficiario () ) ) {
            risultatoPropagazione = propagazioneService.propagaCodiceVersamento ( entity, AzioneDaPropagare.INSERIMENTO );

            if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
                throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                    risultatoPropagazione.getMessaggio () );
            }
            entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
            entity.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
//        }
       

        //TODOPPU_ SRV_PEC
        //
        //		if ( risultatoPropagazione.getEsito () == EsitoPropagazione.PENDING) {
        //            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
        //                risultatoPropagazione.getMessaggio () );
        //        }

       
        entity.setModalitaIntegrazione ( modalitaIntegrazioneRepository.findOneByCodice ( input.getCodiceModalitaIntegrazione () ) );

        if ( entity.getStatoAggiornamento () == null ) {
            entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_KO ) );
        }

        repository.save ( entity );

        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
//        if ( Boolean.FALSE.equals ( input.getFlagElementiMultibeneficiario () ) && null != risultatoPropagazione
                        if ( null != risultatoPropagazione
            && risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( entity );
        }

        return InserisciCodiceVersamentoOutput.ok ( entity.getId () ).conMessaggi ( context.getMessaggi () );
    }

    /*
     * private boolean getEreditato ( String ibanEnte, String ibanCvNew ) { //In realta' il campo ha solo senso come "promemoria" per il record //la logica di
     * ereditarieta' sugli sparpagliatori e' locale a ognuno //di loro per non alterare il tracciato. Di base se il codice versamento //non ha un codice iban o
     * lo stesso e' uguale a quello dell'ente Boolean ereditato = false; if ( !StringUtils.isEmpty ( ibanEnte ) && !StringUtils.isEmpty ( ibanCvNew ) ) {
     * ereditato |= ibanEnte.equalsIgnoreCase ( ibanCvNew ); } ereditato |= StringUtils.isEmpty ( ibanCvNew ); return ereditato; }
     */

    private void inviaMail ( CodiceVersamento entity ) {
        Map<String, String> parametri = new HashMap<> ();

        if ( entity == null ) {
            parametri.put ( "stato_aggiornamento", "ESITO NON DEFINITO" );
            parametri.put ( "descrizione_ente", "DESCRIZIONE ENTE VUOTO" );
            parametri.put ( "descrizione_codice_versamento", "DESCRIZIONE CODICE VERS.  VUOTO" );
        } else {
            parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );
            parametri.put ( "descrizione_ente", entity.getEnte ().getDenominazione () );
            parametri.put ( "descrizione_codice_versamento", entity.getCodice () + " - " + entity.getDescrizione () );
        }

        parametri.put ( "azione_oggetto", "Inserimento" );
        parametri.put ( "azione", "inserito" );

        invioMailService.inviaMail ( EmailEnum.CODICE_VERSAMENTO,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getEnte ().getId (),
            parametri );
    }

    public CodiceVersamento map ( InserisciCodiceVersamentoInput input, Ente ente,
        OperationDispatchingContext<InserisciCodiceVersamentoInput, InserisciCodiceVersamentoOutput> context ) {

        CodiceVersamento output = new CodiceVersamento ();

        EntityUtils.copyProperties ( output, input );
        output.setModalitaIntegrazione ( modalitaIntegrazioneRepository.findOneByCodice ( input.getCodiceModalitaIntegrazione () ) );
        output.setEnte ( ente );

        output.setIban ( input.getIban () );
        output.setBic ( input.getBic () );
        output.setDataInizioValidita  (input.getDtInizioValidita ()!=null? new Timestamp ( input.getDtInizioValidita ().getTime () ):null );
        output.setDataFineValidita (input.getDtFineValidita ()!=null? new Timestamp ( input.getDtFineValidita ().getTime () ):null );

        if ( input.getFattura () == null ) {
            output.setFattura ( false );
        }

        if ( input.getIdCodiceVersamentoPadre () != null ) {
            CodiceVersamento padre = repository.findOne ( input.getIdCodiceVersamentoPadre () );
            if ( padre != null ) {
                if ( padre.getCodiceVersamentoPadre () != null ) {
                    // aoooo ma che e' ?
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID codice versamento padre" );
                }
                output.setCodiceVersamentoPadre ( padre );
            } else {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID codice versamento padre" );
            }

            if ( EntityUtils.isTrue ( padre.getFlagAnnullato () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID codice versamento padre" );
            }

            String codice = calcolaCodicePerCollegato ( padre, ente.getId () );
            output.setCodice ( codice );

            // controllo che la descrizione sia diversa da quella del padre
            if ( input.getDescrizione ().trim ().equalsIgnoreCase ( padre.getDescrizione ().trim () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_DESCRIZIONE_UGUALE_PADRE );
            }

            output.setTipoPagamento ( padre.getTipoPagamento () );
            output.setVoceEntrata ( padre.getVoceEntrata () );

        } else {
            if ( checkEsisteAttiva ( ente.getId (), input.getCodiceVoceEntrata () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_ALREADY_EXISTING );
            }

            output.setCodice ( input.getCodiceVoceEntrata () );

            VoceEntrata dtoVoceEntrata = voceEntrataRepository.findOneByCodice ( input.getCodiceVoceEntrata () );
            if ( dtoVoceEntrata != null ) {
                output.setVoceEntrata ( dtoVoceEntrata );
            } else {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "voce entrata" );
            }

            TipoPagamento dtoTipoPagamento = tipoPagamentoRepository.findOneByCodice ( input.getCodiceTipoPagamento () );
            if ( dtoTipoPagamento != null ) {
                output.setTipoPagamento ( dtoTipoPagamento );
            } else {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "tipo pagamento" );
            }

            // RID 02 2019
            if ( dtoTipoPagamento.getCodice ().equals ( TipoPagamentoType.REDS.name () ) ) {
                if ( output.getModalitaIntegrazione () == null
                    || !output.getModalitaIntegrazione ().getCodice ().equals ( ModalitaDiIntegrazioneType.SRV.name () ) ) {
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "modalita' di integrazione" );
                }
            }

            if ( StringUtils.isBlank ( input.getIban () ) ) {
                output.setIban ( ente.getIban () );
                output.setBic ( ente.getBic () );

                context.getMessaggi ().add ( decodificaService.getMessaggio (
                    Constants.MESSAGES.IBAN_CV_EREDITATO_DA_ENTE, ente.getIban () ) );
            }
        }
        
        if (Boolean.TRUE.equals ( input.getFlagElementiMultibeneficiario() )  )
        {
        	
        	output.setStatoMultibeneficiario(statoMultibeneficiarioRepository.findOne(StatoMultibeneficiarioEnum.INCOMPLETE.getCodice()));
        }
        
        if (null!= input.getCovAssociatoAssociazioneMultibeneficiario())
        {
        	CodiceVersamento covSecondario= repository.findOne(input.getCovAssociatoAssociazioneMultibeneficiario());
        	if (null == covSecondario)
        	{
        		 throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "Codice versamento secondario associato non esitente" );
        	}
        	output.getCodiciVersamentoSecondariCollegati().add(covSecondario);
        }
        
        if ( SecurityUtils.hasUseCase ( Constants.USE_CASES.ASSISTENZA ))
        {
            if (!StringUtils.isEmpty ( input.getStrPassphrase () ) || !StringUtils.isEmpty ( input.getStrCredenzialiPnd () ))

            {
                Properties properties;
                try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
                    properties = new Properties();
                    properties.load(inputStream);

                } catch (IOException e) {
                    throw new RuntimeException("Errore lettura parametri: " + e.getMessage());
                }
                if (!StringUtils.isEmpty ( input.getStrPassphrase () ) )
                {
                    output.setPassphrase ( EncryptionDecryptionUtil.encrypt ( input.getStrPassphrase (), properties.getProperty ( Constants.PASSWORD_PASSPHRASE ) ).getBytes ());
                }

                if (!StringUtils.isEmpty ( input.getStrCredenzialiPnd () ))
                {
                    String credenziali = Base64.getEncoder ().encodeToString ( input.getStrCredenzialiPnd ().getBytes ()) ;
                    
                    output.setCredenzialiPnd ( EncryptionDecryptionUtil.encrypt ( credenziali,properties.getProperty ( Constants.PASSWORD_NOTIFICATION_PRICE ) ).getBytes ());
                }
            }
        }
        
        output.setFlagPersonalizzazioneCov ( input.getFlagPersonalizzazioneCov ()!= null? input.getFlagPersonalizzazioneCov (): Boolean.FALSE );


        output.setFlagInvioNotificatore ( Boolean.TRUE );
       

        output.setId ( null );
        return output;
    }

    private String calcolaCodicePerCollegato ( CodiceVersamento padre, Long idEnte ) {

        //        // IMPLEMENTAZIONE PRECEDENTE
        //        if ( false ) {
        //            String codicePartenza = padre.getCodice ();
        //            String codice;
        //            int indice = 10;
        //
        //            while ( --indice > 0 ) {
        //                codice = codicePartenza.substring ( 0, 3 ) + String.valueOf ( indice );
        //                if ( checkEsisteAttiva ( idEnte, codice ) ) {
        //                    break;
        //                }
        //            }
        //
        //            if ( indice >= 9 ) {
        //                throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_NUM_COLLEGATI_EXCEEDED );
        //            }
        //
        //            codice = codicePartenza.substring ( 0, 3 ) + String.valueOf ( ( indice + 1 ) );
        //            return codice;
        //        }

        // controllo tutti i codici figli gia' esistenti
        char highest = '0';

        for ( CodiceVersamento codiceFiglio: padre.getCodiciVersamentoCollegati () ) {
            // escludo quelli cancellati logicamente
            if ( EntityUtils.isTrue ( codiceFiglio.getFlagAnnullato () ) ) {
                continue;
            }

            // verifico se il formato del codice e' lo stesso, i primi tre caratteri devono coincidere
            if ( !codiceFiglio.getCodice ().substring ( 0, 3 ).equals ( padre.getCodice ().substring ( 0, 3 ) ) ) {
                continue;
            }

            // verifico l'ultimo digit
            char lastDigitFiglio = codiceFiglio.getCodice ().toUpperCase ().charAt ( 3 );
            if ( lastDigitFiglio > highest ) {
                highest = lastDigitFiglio;
            }
        }

        if ( highest > '9' && highest < 'A' ) {
            highest += ( 'A' - '9' );
        }

        if ( highest >= 'Z' ) {
            throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_NUM_COLLEGATI_EXCEEDED );
        }

        return padre.getCodice ().substring ( 0, 3 ) + Character.toString ( ++highest );
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
    
    
}
