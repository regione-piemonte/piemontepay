/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.ente;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteOutput;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.StatoAggiornamentoEnte;
import it.csi.epay.epaypacatalogsrv.model.StoricoEnte;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.ModalitaAcquisizioneProvvisoriRepository;
import it.csi.epay.epaypacatalogsrv.repository.PeriodicitaSchedulazioneRiconciliazioneRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StoricoEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.TipologiaAccertamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Operation(consumes=AggiornaEnteInput.class, produces=AggiornaEnteOutput.class)
@Component
public class AggiornaEnteOperation implements OperationHandler<AggiornaEnteInput, AggiornaEnteOutput> {

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private StoricoEnteRepository storicoRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private StatoAggiornamentoEnteRepository saeRepository;

    @Autowired
    private ModalitaAcquisizioneProvvisoriRepository epaypacatDModalitaAcquisizioneProvvisoriRepository;

    @Autowired
    private PeriodicitaSchedulazioneRiconciliazioneRepository epaypacatDPeriodicitaSchedulazioneRiconciliazioneRepository;

    @Autowired
    private TipologiaAccertamentoRepository epaypacatDTipologiaAccertamentoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Override
    public void preValidateInput ( AggiornaEnteInput input, OperationDispatchingContext<AggiornaEnteInput, AggiornaEnteOutput> context ) {
        if ( input.getId () == null ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "ID");
        }
        if (input.getId() < 1L) {
            throw new BadRequestException(Constants.MESSAGES.INVALID_FIELD, "ID");
        }
    }

    @Override
    public void authorize ( AggiornaEnteInput input, OperationDispatchingContext<AggiornaEnteInput, AggiornaEnteOutput> context ) {

        SecurityUtils.assertUseCase(Constants.USE_CASES.MODIFICA_ENTE);
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        Ente current = enteRepository.findOne(input.getId());
        if ( current == null ) {
            throw new NotFoundException(Constants.MESSAGES.ENTE_NOT_FOUND);
        }

        SecurityUtils.assertAmministratoreEnteCorrente ();
    }

    @Override
    public void validateInput ( AggiornaEnteInput input, OperationDispatchingContext<AggiornaEnteInput, AggiornaEnteOutput> context ) {

        if ( EntityUtils.isTrue ( input.getCancellaLogo () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FORBIDDEN_FIELD, "cancella logo" );
        }

        if ( StringUtils.isBlank ( input.getDenominazione () ) ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "denominazione");
        }
        if ( StringUtils.isBlank ( input.getIndirizzo () ) ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "indirizzo");
        }
        if ( StringUtils.isBlank ( input.getLocalita () ) ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "localita");
        }
        if ( StringUtils.isBlank ( input.getCap () ) ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "cap");
        }
        if ( StringUtils.isBlank ( input.getSiglaProvincia () ) ) {
            throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "provincia");
        }

        if ( !StringUtils.isEmpty ( input.getEmail () ) ) {
            if ( !EntityUtils.isValidEmail ( input.getEmail () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "email" );
            }
        }

        if ( EntityUtils.isFalse ( input.getFlagRiconciliazioneVersamenti () ) ) {
            input.setFlagAccertamento(false);
            input.setFlagRicezioneErrori(false);
            input.setCodicePeriodicitaSchedulazioneRiconciliazione(null);
            input.setCodiceTipologiaAccertamento(null);
            input.setCodiceModalitaAcquisizioneProvvisori(null);
            input.setGiornoSchedulazione(null);
        }

        if ( EntityUtils.isTrue ( input.getFlagRiconciliazioneVersamenti () ) &&
                        !StringUtils.isEmpty ( input.getCodicePeriodicitaSchedulazioneRiconciliazione () ) ) {

            String codPeriodicita = input.getCodicePeriodicitaSchedulazioneRiconciliazione();
            Integer valPeriodicita = input.getGiornoSchedulazione();

            if ( "GIO".equals ( codPeriodicita ) || "SGF".equals ( codPeriodicita ) || "NOS".equals ( codPeriodicita ) ) {
                input.setGiornoSchedulazione(null);
            } else {
                if (valPeriodicita == null) {
                    throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "giorno schedulazione");
                } else {
                    if ("ANN".equals(codPeriodicita)) {
                        if ( valPeriodicita < 1 || valPeriodicita > 366 ) {
                            throw new BadRequestException(Constants.MESSAGES.INVALID_FIELD, "giorno schedulazione");
                        }
                    } else {
                        int max;

						switch ( codPeriodicita ) {
						case "SET":
							max = 7;
							break;
						case "MEN":
						case "BIM":
						case "TRI":
						case "QUA":
						case "SEM":
							max = 30;
							break;
						default:
							throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "codice periodicita' schedulazione riconciliazione" );
						}

                        if (valPeriodicita < 1 || valPeriodicita > max) {
                            throw new BadRequestException(Constants.MESSAGES.INVALID_FIELD, "giorno schedulazione");
                        }
                    }
                }
            }
        }

        if ( EntityUtils.isTrue ( input.getFlagRiconciliazioneVersamenti () ) ) {
            if ( StringUtils.isBlank ( input.getCodicePeriodicitaSchedulazioneRiconciliazione () ) ) {
                throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "periodicita' schedulazione riconciliazione");
            }
            if ( StringUtils.isBlank ( input.getCodiceTipologiaAccertamento () ) ) {
                throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "tipologia accertamento");
            }
            if ( StringUtils.isBlank ( input.getCodiceModalitaAcquisizioneProvvisori () ) ) {
                throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, "modalita' acquisizione provvisori");
            }
        }

        if ( EntityUtils.isTrue ( input.getFlagRicezioneFlussoBaseRendicontazione () ) &&
                        EntityUtils.isFalse ( input.getFlagQualsiasiCodiceVersamento () ) ) {
            // almeno una voce selezionata
            verificaAlmenoUnCodiceVersamento ( input );
        } else {
            input.setIdCodiciVersamentoAssociati ( new ArrayList<> () );
        }
        
        
        if (Boolean.TRUE.equals ( input.getFlagAdesioneCittaFacile () ) )
        {
            if (StringUtils.isEmpty ( input.getTemplateEmailId () ) ||
                            StringUtils.isEmpty ( input.getUrlDominio () )||
                            StringUtils.isEmpty ( input.getCodiceIpa () ) )
            {
                throw new BadRequestException(Constants.MESSAGES.FIELD_REQUIRED, 
                    "In caso di adesione a CittaFacile il template id, la url dominio e il codice ipa sono obbligatori");
            }
        }
        else
        {
            input.setUrlDominio ( "" );
            input.setTemplateEmailId ( "" );
            input.setCodiceIpa ("");
        }
    }

    private void verificaAlmenoUnCodiceVersamento ( AggiornaEnteInput input ) {
        boolean almenoUna = false;
        if ( input.getIdCodiciVersamentoAssociati () != null ) {
            for ( Long v: input.getIdCodiciVersamentoAssociati () ) {
                if ( v != null && v > 0L ) {
                    almenoUna = true;
                    break;
                } else {
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "id codice versamento associato" );
                }
            }
        }

        if ( !almenoUna ) {
            throw new BadRequestException ( Constants.MESSAGES.ENTE_WITHOUT_FLUSSI_ASSOCIATI );
        } else {
            if ( codiceVersamentoRepository.countByIdIn ( input.getIdCodiciVersamentoAssociati () ) != input.getIdCodiciVersamentoAssociati ()
                            .size () ) {
                throw new BadRequestException ( Constants.MESSAGES.NOT_FOUND, "id codice versamento associato" );
            }
        }
    }

    @Override
    @Transactional
    public AggiornaEnteOutput execute ( AggiornaEnteInput input, OperationDispatchingContext<AggiornaEnteInput, AggiornaEnteOutput> context ) {

        Ente current = enteRepository.findOne(input.getId());

        String ibanOriginale = current.getIban ();
        String bicOriginale = current.getBic ();
        
        salvaStorico(current);

        map(current, input);

        current.setStatoAggiornamentoEnte ( saeRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        current.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );

        enteRepository.save(current);

        AggiornaEnteOutput output = AggiornaEnteOutput.ok ( AggiornaEnteOutput.class );

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaEnte ( current, AzioneDaPropagare.MODIFICA, 
            ibanOriginale, bicOriginale);

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        StatoAggiornamentoEnte resPropaga = saeRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () );
        
        current.setStatoAggiornamentoEnte ( resPropaga );
        current.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

        enteRepository.save ( current );

        aggiornaCVAssociati(ibanOriginale,current);
         
        if(resPropaga != null) {
            output.setCodiceRisultatoSincronizzazione ( current.getStatoAggiornamentoEnte ().getCodice () );
        } else {
            output.setCodiceRisultatoSincronizzazione("KO");
        }
        
        output.setDescrizioneRisultatoSincronizzazione ( current.getDescrizioneErroreAggiornamento () );

        // invia mail
        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( current );
        }

        return output;
    }

    private void aggiornaCVAssociati(String ibanOriginale, Ente ente) {
        List<CodiceVersamento> cvAssociati = codiceVersamentoRepository.findByEnte_Id ( ente.getId (), new Sort ( "id" )  );
        
        for(CodiceVersamento cv:cvAssociati) {
            //Sui cv con iban uguale all'iban pre-modifica dell'ente
            if(ibanOriginale != null) {
                if(ibanOriginale.equalsIgnoreCase ( cv.getIban () )) {
                    cv.setIban ( ente.getIban () );
                    cv.setBic ( ente.getBic () );
                }
            } else {
                cv.setIban ( ente.getIban () );
                cv.setBic ( ente.getBic () );
            }
            codiceVersamentoRepository.save ( cv );
        }
    }
    
    private void inviaMail ( Ente current ) {
        Map<String, String> parametri = new HashMap<> ();
        parametri.put ( "stato_aggiornamento", current.getDescrizioneEsitoAggiornamento () );

        parametri.put ( "descrizione_ente", current.getDenominazione () );

        invioMailService.inviaMail ( EmailEnum.MODIFICA_ENTE,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            current.getId (),
            parametri );
    }

    private void salvaStorico(Ente current) {
        StoricoEnte storico = new StoricoEnte();

        EntityUtils.copyProperties ( storico, current );

        storico.setId(null);
        storico.setIdEnte(current.getId());

        storicoRepository.save(storico);
    }

    private void map(Ente current, AggiornaEnteInput input) {

        Ente backup = new Ente();

        EntityUtils.copyProperties ( backup, current );
        EntityUtils.copyProperties ( current, input );

		if ( !EntityUtils.isTrue ( input.getCancellaLogo () ) && input.getLogoContent () == null ) {
            current.setLogoContent(backup.getLogoContent());
            current.setLogoContentType(backup.getLogoContentType());
            current.setLogoFileName(backup.getLogoFileName());
            current.setLogoFileSize(backup.getLogoFileSize());
        }

        if (input.getCodicePeriodicitaSchedulazioneRiconciliazione() != null) {
            current.setPeriodicitaSchedulazioneRiconciliazione(
                epaypacatDPeriodicitaSchedulazioneRiconciliazioneRepository.findOneByCodice(
                    input.getCodicePeriodicitaSchedulazioneRiconciliazione()));

        } else {
            current.setPeriodicitaSchedulazioneRiconciliazione(null);
        }

        if (input.getCodiceTipologiaAccertamento() != null) {
            current.setTipologiaAccertamento(
                epaypacatDTipologiaAccertamentoRepository.findOneByCodice(
                    input.getCodiceTipologiaAccertamento()));

            if (current.getTipologiaAccertamento() == null) {
                throw new BadRequestException(Constants.MESSAGES.INVALID_FIELD,
                                "tipologia accertamento");
            }
        } else {
            current.setTipologiaAccertamento(null);
        }

        if (input.getCodiceModalitaAcquisizioneProvvisori() != null) {
            current.setModalitaAcquisizioneProvvisori(
                epaypacatDModalitaAcquisizioneProvvisoriRepository.findOneByCodice(
                    input.getCodiceModalitaAcquisizioneProvvisori()));

            if (current.getModalitaAcquisizioneProvvisori() == null) {
                throw new BadRequestException(Constants.MESSAGES.INVALID_FIELD,
                                "modalita' acquisizione provvisori");
            }
        } else {
            current.setModalitaAcquisizioneProvvisori(null);
        }

        if ( EntityUtils.isTrue ( input.getFlagRicezioneFlussoBaseRendicontazione () ) &&
                        EntityUtils.isFalse ( input.getFlagQualsiasiCodiceVersamento () ) ) {
            current.getCodiciVersamento().clear();
            for ( Long idCV: input.getIdCodiciVersamentoAssociati () ) {
                CodiceVersamento cv = new CodiceVersamento ();
                cv.setId ( idCV );
                current.getCodiciVersamento ().add ( cv );
            }
        } else {
            current.getCodiciVersamento ().clear ();
        }

        EntityUtils.populateEditFields(current);

        if ( current.getLogoFileSize () == null || current.getLogoFileSize () < 0 ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "logo" );
        }

	}
}
