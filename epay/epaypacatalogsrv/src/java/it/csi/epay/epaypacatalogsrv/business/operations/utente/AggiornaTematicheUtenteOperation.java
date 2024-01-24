/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.ComparatoreDiElementiDiversi;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.Coppia;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteTematicaInputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamentoPK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematicaPK;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicaPpayRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalCodiceVersamentoVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalTematicaVO;


@Operation ( consumes = AggiornaTematicheUtenteInput.class, produces = AggiornaTematicheUtenteOutput.class )
@Component
public class AggiornaTematicheUtenteOperation implements OperationHandler<AggiornaTematicheUtenteInput, AggiornaTematicheUtenteOutput> {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private TematicaPpayRepository tematicaPpayRepository;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private ProfilazioneService profilazioneService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void preValidateInput ( AggiornaTematicheUtenteInput input,
        OperationDispatchingContext<AggiornaTematicheUtenteInput, AggiornaTematicheUtenteOutput> context ) {
        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( AggiornaTematicheUtenteInput input,
        OperationDispatchingContext<AggiornaTematicheUtenteInput, AggiornaTematicheUtenteOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();

        Utente current = utenteRepository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ();
        }
    }

    @Override
    public void validateInput ( AggiornaTematicheUtenteInput input,
        OperationDispatchingContext<AggiornaTematicheUtenteInput, AggiornaTematicheUtenteOutput> context ) {

        if ( input.getTematiche () != null ) {
            for ( AggiornaTematicheUtenteTematicaInputDto tematica: input.getTematiche () ) {
                if ( StringUtils.isBlank ( tematica.getCodice () ) ) {
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "codice tematica" );
                }

                if ( EntityUtils.isTrue ( tematica.getFlagVisibilitaTotale () ) ) {
                    if ( tematica.getIdCodiciVersamento () != null && tematica.getIdCodiciVersamento ().size () > 0 ) {
                        logger.error ( "non e' consentito specificare dei singoli codici versamento se viene richiesta la visibilita' totale; tematica = "
                            + tematica.getCodice () );
                        throw new BadRequestException ( Constants.MESSAGES.FORBIDDEN_FIELD, "id codici versamento" );
                    }
                }
            }
        } else {
            input.setTematiche ( new ArrayList<> () );
        }
    }

    @Override
    @Transactional
    public AggiornaTematicheUtenteOutput execute ( AggiornaTematicheUtenteInput input,
        OperationDispatchingContext<AggiornaTematicheUtenteInput, AggiornaTematicheUtenteOutput> context ) {

        Long idEnteCorrente = SecurityUtils.getCurrentIdEnte ();

        Utente utenteCorrente = utenteRepository.findOne ( input.getId () );
        Ente enteCorrente = enteRepository.findOne ( idEnteCorrente );

        List<PrincipalTematicaVO> associazioniPrima = null;
        if ( !StringUtils.isEmpty ( utenteCorrente.getEmail () ) ) {
            associazioniPrima = profilazioneService.getTematicheAssociate ( utenteCorrente, enteCorrente );
        }

        aggiornaAssociazioniTematiche ( input, utenteCorrente, idEnteCorrente );

        aggiornaAssociazioniCodiciVersamento ( input, utenteCorrente, idEnteCorrente );

        aggiornaUtenteCorrente ( input, utenteCorrente );

        associazioneUtenteCodiceVersamentoRepository.flush ();
        associazioneUtenteTematicaRepository.flush ();
        entityManager.clear ();

        if ( !StringUtils.isEmpty ( utenteCorrente.getEmail () ) ) {
            List<PrincipalTematicaVO> associazioniDopo = profilazioneService.getTematicheAssociate ( utenteCorrente, enteCorrente );
            inviaMailPerCambiamenti ( utenteCorrente, enteCorrente, associazioniPrima, associazioniDopo );
        }

        AggiornaTematicheUtenteOutput output = AggiornaTematicheUtenteOutput.ok ( AggiornaTematicheUtenteOutput.class );
        output.setCodiceRisultatoSincronizzazione ( utenteCorrente.getStatoAggiornamento ().getCodice () );
        output.setDescrizioneRisultatoSincronizzazione ( utenteCorrente.getDescrizioneErroreAggiornamento () );
        return output;
    }

    private void aggiornaAssociazioniCodiciVersamento ( AggiornaTematicheUtenteInput input, Utente utenteCorrente, Long idEnteCorrente ) {

        List<AssociazioneUtenteCodiceVersamento> associazioniCodiciVersamentoCorrenti = associazioneUtenteCodiceVersamentoRepository.findByIdUtenteAndIdEnte (
            utenteCorrente.getId (), idEnteCorrente.intValue () );

        List<Long> idCodiciVersamentoInput = new ArrayList<> ();
        for ( AggiornaTematicheUtenteTematicaInputDto tematica: input.getTematiche () ) {
            if ( tematica.getIdCodiciVersamento () != null ) {
                idCodiciVersamentoInput.addAll ( tematica.getIdCodiciVersamento () );
            }
        }

        DifferenzaFraListeDiverse<AssociazioneUtenteCodiceVersamento, Long> differenzaTraCorrenteEdInput
            = ComparatoreDiListeDiverse.compareLists ( associazioniCodiciVersamentoCorrenti, idCodiciVersamentoInput,
                new ComparatoreDiElementiDiversi<AssociazioneUtenteCodiceVersamento, Long> () {

                    @Override
                    public boolean compara ( AssociazioneUtenteCodiceVersamento corrente, Long desiderato ) {
                        return corrente.getId ().getIdCodiceVersamento ().equals ( desiderato.intValue () );
                    }
                } );

        for ( AssociazioneUtenteCodiceVersamento daEliminare: differenzaTraCorrenteEdInput.getElementiSoloNellaPrima () ) {
            logger.debug ( "elimino associazione con codice versamento " + daEliminare.getId ().getIdCodiceVersamento () );
            associazioneUtenteCodiceVersamentoRepository.delete ( daEliminare );
        }

        for ( Long daInserire: differenzaTraCorrenteEdInput.getElementiSoloNellaSeconda () ) {
            if ( codiceVersamentoRepository.findOne ( daInserire ) == null ) {
                throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND, daInserire );
            }

            logger.debug ( "inserisco associazione con codice versamento " + daInserire );

            AssociazioneUtenteCodiceVersamento nuovo = new AssociazioneUtenteCodiceVersamento ();
            AssociazioneUtenteCodiceVersamentoPK id = new AssociazioneUtenteCodiceVersamentoPK ();
            id.setIdCodiceVersamento ( daInserire.intValue () );
            id.setIdEnte ( idEnteCorrente.intValue () );
            id.setIdUtente ( utenteCorrente.getId () );
            nuovo.setId ( id );
            associazioneUtenteCodiceVersamentoRepository.save ( nuovo );
        }
    }

    private void aggiornaAssociazioniTematiche ( AggiornaTematicheUtenteInput input, Utente utenteCorrente, Long idEnteCorrente ) {

        List<AssociazioneUtenteTematica> associazioniTematicheCorrenti = associazioneUtenteTematicaRepository.findByIdUtenteAndIdEnte (
            utenteCorrente.getId (), idEnteCorrente.intValue () );

        DifferenzaFraListeDiverse<AssociazioneUtenteTematica, AggiornaTematicheUtenteTematicaInputDto> differenzaTraCorrenteEdInput
            = ComparatoreDiListeDiverse.compareLists ( associazioniTematicheCorrenti, input.getTematiche (),
                new ComparatoreDiElementiDiversi<AssociazioneUtenteTematica, AggiornaTematicheUtenteTematicaInputDto> () {

                    @Override
                    public boolean compara ( AssociazioneUtenteTematica corrente, AggiornaTematicheUtenteTematicaInputDto desiderato ) {
                        return corrente.getId ().getCodTematica ().equals ( desiderato.getCodice () );
                    }
                } );

        for ( AssociazioneUtenteTematica daEliminare: differenzaTraCorrenteEdInput.getElementiSoloNellaPrima () ) {

            logger.debug ( "elimino associazione con tematica " + daEliminare.getId ().getCodTematica () );

            associazioneUtenteTematicaRepository.delete ( daEliminare );
        }

        for ( AggiornaTematicheUtenteTematicaInputDto daInserire: differenzaTraCorrenteEdInput.getElementiSoloNellaSeconda () ) {
            if ( tematicaPpayRepository.findOneByCodice ( daInserire.getCodice () ) == null ) {
                throw new NotFoundException ( Constants.MESSAGES.TEMATICA_NOT_FOUND, daInserire.getCodice () );
            }

            logger.debug ( "inserisco associazione con tematica " + daInserire.getCodice () );

            AssociazioneUtenteTematica nuovo = new AssociazioneUtenteTematica ();
            AssociazioneUtenteTematicaPK id = new AssociazioneUtenteTematicaPK ();
            id.setCodTematica ( daInserire.getCodice () );
            id.setIdEnte ( idEnteCorrente.intValue () );
            id.setIdUtente ( utenteCorrente.getId () );
            nuovo.setId ( id );
            nuovo.setFlagVisibilitaTotale ( daInserire.getFlagVisibilitaTotale () );
            associazioneUtenteTematicaRepository.save ( nuovo );
        }

        for ( Coppia<AssociazioneUtenteTematica, AggiornaTematicheUtenteTematicaInputDto> giaInserita: differenzaTraCorrenteEdInput.getElementiInTutteEDue () ) {
            if ( EntityUtils.isTrue ( giaInserita.getPrimo ().getFlagVisibilitaTotale () ) != EntityUtils
                .isTrue ( giaInserita.getSecondo ().getFlagVisibilitaTotale () ) ) {

                AssociazioneUtenteTematica esistente = giaInserita.getPrimo ();

                logger.debug ( "aggiorno flag visibilita per associazione con tematica " + esistente.getId ().getCodTematica () );

                esistente.setFlagVisibilitaTotale ( EntityUtils.isTrue ( giaInserita.getSecondo ().getFlagVisibilitaTotale () ) );
                associazioneUtenteTematicaRepository.save ( esistente );
            }
        }
    }

    private void aggiornaUtenteCorrente ( AggiornaTematicheUtenteInput input, Utente utenteCorrente ) {
        utenteCorrente.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_KO ) );
        utenteCorrente.setDescrizioneErroreAggiornamento ( "Servizio di propagazione non disponibile" );
        utenteRepository.save ( utenteCorrente );
    }

    private void inviaMailPerCambiamenti ( Utente utenteCorrente, Ente enteCorrente, List<PrincipalTematicaVO> associazioniPrima,
        List<PrincipalTematicaVO> associazioniDopo ) {

        List<PrincipalTematicaVO> tematicheTotaliPrima = new ArrayList<> ();
        List<PrincipalCodiceVersamentoVO> codiciVersamentoPrima = new ArrayList<> ();

        List<PrincipalTematicaVO> tematicheTotaliDopo = new ArrayList<> ();
        List<PrincipalCodiceVersamentoVO> codiciVersamentoDopo = new ArrayList<> ();

        for ( PrincipalTematicaVO entry: associazioniPrima ) {
            if ( EntityUtils.isTrue ( entry.getFlagVisibilitaTotale () ) ) {
                tematicheTotaliPrima.add ( entry );
            } else if ( entry.getCodiciVersamento () != null ) {
                for ( PrincipalCodiceVersamentoVO entryCV: entry.getCodiciVersamento () ) {
                    codiciVersamentoPrima.add ( entryCV );
                }
            }
        }

        for ( PrincipalTematicaVO entry: associazioniDopo ) {
            if ( EntityUtils.isTrue ( entry.getFlagVisibilitaTotale () ) ) {
                tematicheTotaliDopo.add ( entry );
            } else if ( entry.getCodiciVersamento () != null ) {
                for ( PrincipalCodiceVersamentoVO entryCV: entry.getCodiciVersamento () ) {
                    codiciVersamentoDopo.add ( entryCV );
                }
            }
        }

        DifferenzaFraListeDiverse<PrincipalTematicaVO, PrincipalTematicaVO> differenzaTematichePrimaDopo
            = ComparatoreDiListeDiverse.compareLists ( tematicheTotaliPrima, tematicheTotaliDopo,
                new ComparatoreDiElementiDiversi<PrincipalTematicaVO, PrincipalTematicaVO> () {

                    @Override
                    public boolean compara ( PrincipalTematicaVO prima, PrincipalTematicaVO dopo ) {
                        return prima.getCodice ().equals ( dopo.getCodice () );
                    }
                } );

        DifferenzaFraListeDiverse<PrincipalCodiceVersamentoVO, PrincipalCodiceVersamentoVO> differenzaCodiciVersamentoPrimaDopo
            = ComparatoreDiListeDiverse.compareLists ( codiciVersamentoPrima, codiciVersamentoDopo,
                new ComparatoreDiElementiDiversi<PrincipalCodiceVersamentoVO, PrincipalCodiceVersamentoVO> () {

                    @Override
                    public boolean compara ( PrincipalCodiceVersamentoVO prima, PrincipalCodiceVersamentoVO dopo ) {
                        return prima.getId ().equals ( dopo.getId () );
                    }
                } );

        if ( differenzaTematichePrimaDopo.getElementiSoloNellaSeconda ().size () > 0 ) {
            inviaMailPerAutorizzazioniTematicheAggiunte ( utenteCorrente, enteCorrente,
                differenzaTematichePrimaDopo.getElementiSoloNellaSeconda () );
        }

        if ( differenzaCodiciVersamentoPrimaDopo.getElementiSoloNellaSeconda ().size () > 0 ) {
            inviaMailPerAutorizzazioniCodiciVersamentoAggiunte ( utenteCorrente, enteCorrente,
                differenzaCodiciVersamentoPrimaDopo.getElementiSoloNellaSeconda () );
        }

        if ( differenzaTematichePrimaDopo.getElementiSoloNellaPrima ().size () > 0 ) {
            inviaMailPerAutorizzazioniTematicheRevocate ( utenteCorrente, enteCorrente,
                differenzaTematichePrimaDopo.getElementiSoloNellaPrima () );
        }

        if ( differenzaCodiciVersamentoPrimaDopo.getElementiSoloNellaPrima ().size () > 0 ) {
            // caso particolare: non invio mail revoca se e' stata concessa la visibilita' totale sulla tematica
            List<PrincipalCodiceVersamentoVO> filtrati = new ArrayList<> ();
            for ( PrincipalCodiceVersamentoVO voce: differenzaCodiciVersamentoPrimaDopo.getElementiSoloNellaPrima () ) {
                boolean aggiuntaVisTotale = false;
                for ( PrincipalTematicaVO voceTematica: differenzaTematichePrimaDopo.getElementiSoloNellaSeconda () ) {
                    if ( voceTematica.getCodice ().equals ( voce.getCodiceTematica () ) ) {
                        aggiuntaVisTotale = true;
                    }
                }
                if ( !aggiuntaVisTotale ) {
                    filtrati.add ( voce );
                }
            }

            if ( filtrati.size () > 0 ) {
                inviaMailPerAutorizzazioniCodiciVersamentoRevocate ( utenteCorrente, enteCorrente, filtrati );
            }
        }

    }

    private void inviaMailPerAutorizzazioniTematicheAggiunte ( Utente utenteCorrente, Ente enteCorrente, List<PrincipalTematicaVO> tematicheAggiunte ) {

        String body = "La informiamo che a partire da oggi, &egrave; autorizzato a operare con tutti i codici di versamento ";

        if ( tematicheAggiunte.size () > 1 ) {
            body += "delle seguenti tematiche:";
        } else {
            body += "della seguente tematica:";
        }

        body += "<br/> <ul>";
        for ( PrincipalTematicaVO aggiunta: tematicheAggiunte ) {
            body += " <li> " + aggiunta.getCodice () + " - " + aggiunta.getDescrizione () + "</li>";
        }
        body += " </ul>";

        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "body", body );

        invioMailService.inviaMail ( EmailEnum.AUTORIZZAZIONE_TEMATICA,
            utenteCorrente.getEmail (),
            enteCorrente.getId (),
            parametri );

    }

    private void inviaMailPerAutorizzazioniCodiciVersamentoAggiunte ( Utente utenteCorrente, Ente enteCorrente, List<PrincipalCodiceVersamentoVO> cvAggiunti ) {

        String body = "La informiamo che a partire da oggi, &egrave; autorizzato a operare ";

        if ( cvAggiunti.size () > 1 ) {
            body += "con i seguenti codici di versamento:";
        } else {
            body += "con il seguente codice di versamento:";
        }

        body += "<br/> <ul>";
        for ( PrincipalCodiceVersamentoVO aggiunto: cvAggiunti ) {
            body += "<li> " + aggiunto.getCodice () + " - " + aggiunto.getDescrizione () + "</li>";
        }
        body += " </ul>";

        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "body", body );

        invioMailService.inviaMail ( EmailEnum.AUTORIZZAZIONE_TEMATICA,
            utenteCorrente.getEmail (),
            enteCorrente.getId (),
            parametri );

    }

    private void inviaMailPerAutorizzazioniTematicheRevocate ( Utente utenteCorrente, Ente enteCorrente, List<PrincipalTematicaVO> tematicheRevocate ) {

        String body = "La informiamo che a partire da oggi non potr&agrave; pi&ugrave; operare con i codici di versamento ";

        if ( tematicheRevocate.size () > 1 ) {
            body += " delle seguenti tematiche:";
        } else {
            body += " della seguente tematica:";
        }

        body += "<br/> <ul>";
        for ( PrincipalTematicaVO revocata: tematicheRevocate ) {
            body += " <li> " + revocata.getCodice () + " - " + revocata.getDescrizione () + "</li>";
        }
        body += " </ul>";

        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "body", body );

        invioMailService.inviaMail ( EmailEnum.REVOCA_TEMATICA,
            utenteCorrente.getEmail (),
            enteCorrente.getId (),
            parametri );
    }

    private void inviaMailPerAutorizzazioniCodiciVersamentoRevocate ( Utente utenteCorrente, Ente enteCorrente, List<PrincipalCodiceVersamentoVO> cvRevocati ) {

        String body = "La informiamo che a partire da oggi non potr&agrave; pi&ugrave; operare ";

        if ( cvRevocati.size () > 1 ) {
            body += " con i seguenti codici di versamento:";
        } else {
            body += " con il seguente codice di versamento:";
        }

        body += "<br/> <ul>";
        for ( PrincipalCodiceVersamentoVO revocato: cvRevocati ) {
            body += " <li> " + revocato.getCodice () + " - " + revocato.getDescrizione () + "</li>";
        }
        body += " </ul>";

        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "body", body );

        invioMailService.inviaMail ( EmailEnum.REVOCA_TEMATICA,
            utenteCorrente.getEmail (),
            enteCorrente.getId (),
            parametri );
    }
}
