/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.frontend;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.DecodificheManager;
import it.csi.epay.epaypacatalogweb.business.manager.EnteManager;
import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.Messages;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.integration.mapper.ParentMapper;
import it.csi.epay.epaypacatalogweb.model.AssociazioneVO;
import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.model.OpzioneComboVO;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.OpzioniCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.validators.GestioneEnteValidator;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping ( value = "/enti" )
public class EnteController extends AuthenticatedParentController {

    private static final String PAGINA_MODIFICA = "/ente/modifica";

    private static final String URL_MODIFICA = "/enti/modifica";

    private static final String MODEL_MODIFICA_ENTE = "modifica_ente";

	@Autowired
    private EnteManager enteManager;

    @Autowired
    private DecodificheManager decodificheManager;

    @Autowired
    private AuthorizationManager authorizationManager;

    @InitBinder
    protected void initBinder ( WebDataBinder binder ) {
        binder.setValidator ( new GestioneEnteValidator () );
    }

    @Override
    public void loadComboboxes ( Model model ) {
        model.addAttribute ( "listaOpzioniModalitaAcquisizioneProvvisori", decodificheManager.getListaOpzioniModalitaAcquisizioneProvvisori () );
        model.addAttribute ( "listaOpzioniPeriodicitaSchedulazioneRiconciliazione",decodificheManager.getListaOpzioniPeriodicitaSchedulazioneRiconciliazione () );
        model.addAttribute ( "listaOpzioniTipologiaAccertamento", decodificheManager.getListaOpzioniTipologiaAccertamento () );
    }

    private void popolaModelModifica ( Model model, Long idEnte, ModificaEnteVO modelCorrente ) throws OperationFailedException {
        EnteVO originale = enteManager.getEnteById ( idEnte );
        SecurityUtils.getUser ();

        boolean haModelCorrente = ( modelCorrente != null );

        if ( !haModelCorrente ) {
            modelCorrente = new ModificaEnteVO ( originale );
        } else {
            modelCorrente.setAdmins ( originale.getAdmins ());
            modelCorrente.setDescrizioneUtenteAmministratore ( originale.getDescrizioneUtenteAmministratore () );
            modelCorrente.setCodiceStatoAggiornamento ( originale.getCodiceStatoAggiornamento () );
            modelCorrente.setDescrizioneStatoAggiornamento ( originale.getDescrizioneStatoAggiornamento () );
            modelCorrente.setLogoContent ( originale.getLogoContent () );
            modelCorrente.setLogoContentType ( originale.getLogoContentType () );
            modelCorrente.setLogoFileName ( originale.getLogoFileName () );
            modelCorrente.setLogoFileSize ( originale.getLogoFileSize () );
            modelCorrente.setCodiceFiscale ( originale.getCodiceFiscale () );
			modelCorrente.setCodiceIstat ( originale.getCodiceIstat () );
        }

        List<OpzioniCodiceVersamentoVO> possibiliCodiciVersamento = decodificheManager.getListaOpzioniCodiceVersamento ( false );

        Map<Long, Integer> idCodiciVersamentoGiaSelezionati = new HashMap<> ();
        if ( haModelCorrente && modelCorrente.getIdCodiciVersamentoSelezionati () != null ) {
			idCodiciVersamentoGiaSelezionati.putAll ( modelCorrente.getIdCodiciVersamentoSelezionati () );
        }

        modelCorrente.getIdCodiciVersamentoSelezionati ().clear ();
        Map<Long, GenericVO> mapOpzioniCodiciVersamento = new HashMap<> ();

        for ( GenericVO cv: possibiliCodiciVersamento ) {
            mapOpzioniCodiciVersamento.put ( cv.getId (), cv );

            int isAssociato = 0;
            if ( haModelCorrente ) {
                Integer raw = idCodiciVersamentoGiaSelezionati.get ( cv.getId () );
                isAssociato = ( raw != null && raw > 0 ) ? 1 : 0;
            } else {
                if ( originale.getCodiciVersamentoAssociati () != null ) {
                    for ( GenericVO associato: originale.getCodiciVersamentoAssociati () ) {
                        if ( associato.getId ().equals ( cv.getId () ) ) {
                            isAssociato = 1;
                            break;
                        }
                    }
                }
            }

            AssociazioneVO<GenericVO> ass = new AssociazioneVO<> ( cv, isAssociato != 0 );
            modelCorrente.getIdCodiciVersamentoSelezionati ().put ( cv.getId (), isAssociato );
            if ( ass.getSelezionata () ) {
                modelCorrente.getCodiciVersamentoSelezionati ().add ( ass );
            } else {
                modelCorrente.getCodiciVersamentoNonSelezionati ().add ( ass );
            }
        }

        if ( modelCorrente.getLogoContent () != null && modelCorrente.getLogoContent ().length > 0 ) {
            modelCorrente.setLogoContentEncoded ( java.util.Base64.getEncoder ().encodeToString ( modelCorrente.getLogoContent () ) );
        }

        // gestione apposita per campo giornoSchedulazione
        if ( modelCorrente.getCodicePeriodicitaSchedulazioneRiconciliazione () != null ) {
            if ( "ANN".equals ( modelCorrente.getCodicePeriodicitaSchedulazioneRiconciliazione () ) ) {
                modelCorrente.setGiornoSchedulazione ( ParentMapper.daGiornoAData ( modelCorrente.getGiornoSchedulazione () ) );
            }
        } else {
            modelCorrente.setGiornoSchedulazione ( null );
        }

        modelCorrente.setLogoAttuale (
            originale.getLogoFileSize () != null && originale.getLogoFileSize () > 0 ? originale.getLogoFileSize ().toString () : null );

        model.addAttribute ( "listaOpzioniCodiciVersamento", OpzioneComboVO.getOpzioni ( possibiliCodiciVersamento ) );
        model.addAttribute ( "mapOpzioniCodiciVersamento", mapOpzioniCodiciVersamento );

        model.addAttribute ( MODEL_MODIFICA_ENTE, modelCorrente );

        loadComboboxes ( model );
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_ENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/modifica", method = RequestMethod.GET )
    public String modificaEnte ( Model model ) {
        UserDetails user = SecurityUtils.getUser ();
        Long id = user.getEnte ().getId ();

        authorizationManager.require ( EnteVO.class, id, EntityAction.WRITE );

        try {
            popolaModelModifica ( model, id, null );
            return PAGINA_MODIFICA;
        } catch ( OperationFailedException e ) {
            throw new RuntimeException ( e );
        }
    }

    @PreAuthorize ( "hasRole('" + Constants.USE_CASES.MODIFICA_ENTE + "') and hasRole('" + Constants.USE_CASES.AMMINISTRATORE_ENTE + "')" )
    @RequestMapping ( value = "/modifica", method = RequestMethod.POST )
    public String salvaEnteAction (
        @Valid @ModelAttribute ( MODEL_MODIFICA_ENTE ) ModificaEnteVO modifica_ente,
        BindingResult bindingResult,
        Model model, RedirectAttributes redirectAttributes ) throws OperationFailedException {

        IBANCheckDigit ibanValidator = new IBANCheckDigit ();

        UserDetails user = SecurityUtils.getUser ();
        Long id = user.getEnte ().getId ();

        if ( modifica_ente != null ) {
            modifica_ente.setId ( id );
        }

        // gestione apposita per campo giornoSchedulazione
        if ( modifica_ente != null && modifica_ente.getCodicePeriodicitaSchedulazioneRiconciliazione () != null ) {
            if ( "ANN".equals ( modifica_ente.getCodicePeriodicitaSchedulazioneRiconciliazione () ) ) {
                modifica_ente.setGiornoSchedulazione ( ParentMapper.daDataAGiorno ( modifica_ente.getGiornoSchedulazione () ) );
            }
        } else {
			assert modifica_ente != null;
			modifica_ente.setGiornoSchedulazione ( null );
        }

        authorizationManager.require ( EnteVO.class, modifica_ente.getId (), EntityAction.WRITE );

        if ( modifica_ente.getNewLogo () != null && modifica_ente.getNewLogo ().getSize () < 1 ) {
            modifica_ente.setNewLogo ( null );
        }

        //Controllo sul campo IBAN
        if(StringUtils.isEmpty ( modifica_ente.getIban ())) {
            bindingResult.rejectValue ( "iban", "iban", "Il codice iban e' obbligatorio." );
            popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
            return PAGINA_MODIFICA;
        } else {
            if ( !modifica_ente.getIban ().toLowerCase ().startsWith ( "it" ) ) {
                bindingResult.rejectValue ( "iban", "iban", "Il codice iban non risulta italiano." );
                popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
                return PAGINA_MODIFICA;
            }

            if ( !ibanValidator.isValid ( modifica_ente.getIban () ) ) {
                bindingResult.rejectValue ( "iban", "iban", "Il codice iban non risulta corretto." );
                popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
                return PAGINA_MODIFICA;
            }
        }

        if(StringUtils.isEmpty ( modifica_ente.getBic ())) {
            bindingResult.rejectValue ( "bic", "bic", "Il codice bic e' obbligatorio." );
            popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
            return PAGINA_MODIFICA;
        }
        
        if ( ( modifica_ente.getCancellaLogo () != null && modifica_ente.getCancellaLogo () ) ||
                        ( modifica_ente.getNewLogo () == null && StringUtils.isEmpty ( modifica_ente.getLogoAttuale () ) ) ) {
            bindingResult.rejectValue ( "logoContent", "logoContent.validitaGenerica", "Il campo logo e' obbligatorio." );
            popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
            return PAGINA_MODIFICA;
        }

        if ( bindingResult.hasErrors () ) {

            if ( modifica_ente.getNewLogo () != null ) {
                bindingResult.rejectValue ( "logoContent", "logoContent.validitaGenerica",
                                "Non e' stato possibile completare il caricamento del file. Selezionalo nuovamente." );
            }

            popolaModelModifica ( model, modifica_ente.getId (), modifica_ente );
            return PAGINA_MODIFICA;
        } else {

            try {
                enteManager.salvaEnte ( modifica_ente );
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_INFO, Messages.SAVED_SUCCESSFULLY );
            } catch ( OperationFailedException e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, e.getMessage () ) );
            } catch ( Exception e ) {
                redirectAttributes.addFlashAttribute ( MODEL_MESSAGGIO_ERRORE,
                    String.format ( Messages.ERROR_SAVING_CHANGES, Messages.UNKNOWN_ERROR ) );
            }

            return "redirect:" + URL_MODIFICA;
        }
    }
}
