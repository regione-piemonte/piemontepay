/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import it.csi.epay.epayservices.interfaces.ejb.AnagraficaFacade;
import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.rs.exception.TassonomiaServiceException;
import it.csi.epay.epayservices.model.DatiSpecificiRiscossioneInput;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.PagamentoComponenti;
import it.csi.epay.epayservices.model.PagamentoRiferimenti;
import it.csi.epay.epayservices.model.PagamentoSecondarioComponenti;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsterno;
import it.csi.epay.epayweb.business.messaggi.LevelMessage;
import it.csi.epay.epayweb.business.messaggi.Messaggi;
import it.csi.epay.epayweb.business.messaggi.Messaggio;
import it.csi.epay.epayweb.frontend.models.DatiPersonali;
import it.csi.epay.epayweb.frontend.models.IdentitaShibboleth;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.frontend.models.RiferimentoSenzaCodiceFiscale;
import it.csi.epay.epayweb.frontend.models.Step;
import it.csi.epay.epayweb.frontend.models.ViewCommonData;
import it.csi.epay.epayweb.frontend.models._Model;
import it.csi.epay.epayweb.model.enumeration.Wizzard;
import it.csi.epay.epayweb.utilities.LogUtil;
import it.csi.epay.epayweb.utilities.MapId;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;


abstract class _Controller {

    protected LogUtil log = new LogUtil ( this.getClass () );
    @Autowired
    private AnagraficaFacade anagraficaFacade;
    
    protected final static String COVID19_TO_CF = "00514490010";
    protected final static String COVID19_TO_COD_VERSAMENTO = "XN00";

    protected void removeAttribute ( HttpSession session ) {
        removeAttributeFromSession ( RiferimentoSenzaCodiceFiscale.class, session );
        removeAttributeFromSession ( Riferimento.class, session );
        removeAttributeFromSession ( DatiPersonali.class, session );
        removeAttributeFromSession ( Pagamento.class, session );
        removeAttributeFromSession ( Wizzard.class, session );
        removeAttributeFromSession ( TracciabilitaChiamanteEsterno.class, session );
    }

    private final XStream xstream = new XStream ();

    protected String toXml ( Object source ) {
        try {
            return xstream.toXML ( source );
        } catch ( XStreamException e ) {
            return null;
        }
    }

	@Autowired
    private Mapper mapper;// = DozerBeanMapperSingletonWrapper.getInstance();

    protected <T> T map ( Object source, Class<T> clazz ) {
        if ( source == null )
            return null;
        return mapper.map ( source, clazz );
    }

    protected <T> T map ( Object source, Class<T> clazz, MapId mapId ) {
        if ( source == null )
            return null;
        return mapper.map ( source, clazz, mapId.getNameMapId () );
    }

    protected void map ( Object source, Object dest ) {
        if ( source == null )
            return;
        mapper.map ( source, dest );
    }

    protected void map ( Object source, Object dest, MapId mapId ) {
        if ( source == null )
            return;
        mapper.map ( source, dest, mapId.getNameMapId () );
    }

    protected void removeAttributeFromSession ( Class<?> clazz, HttpSession session ) {
        String nameAttribute = StringUtils.uncapitalize ( clazz.getSimpleName () );
        session.removeAttribute ( nameAttribute );
    }

    @SuppressWarnings ( "unchecked" )
    protected <T> T getAttributeFromSession ( Class<T> clazz, HttpSession session ) {
        String nameAttribute = StringUtils.uncapitalize ( clazz.getSimpleName () );
        return (T) session.getAttribute ( nameAttribute );
    }

    protected void saveAttributeIntoSession ( Object object, HttpSession session ) {
        String nameAttribute = StringUtils.uncapitalize ( object.getClass ().getSimpleName () );
        session.setAttribute ( nameAttribute, object );
    }

    protected <T extends _Model> void mergeModel ( T target, T source ) {
        if ( target == null || source == null )
            return;

        for ( Method getMethod: target.getClass ().getMethods () ) {
            if ( !getMethod.getName ().startsWith ( "get" ) )
                continue;
            try {
                Object tValue = getMethod.invoke ( target );
                if ( tValue != null ) {
                    if ( tValue instanceof List ) {
                        if ( ! ( (List<?>) tValue ).isEmpty () )
                            continue;
                    } else {
                        continue;
                    }
                }
                Method setMetod = target.getClass ().getMethod ( "set" + getMethod.getName ().substring ( 3 ), getMethod.getReturnType () );
                Object sValue = getMethod.invoke ( source );
                setMetod.invoke ( target, sValue );
            } catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) {
                e.printStackTrace ( System.err );
            }
		}
    }

    protected <T extends _Model> void setAttributeIntoModel ( Class<T> clazz, Model model, HttpSession session ) {
        String nameAttribute = StringUtils.uncapitalize ( clazz.getSimpleName () );
        if ( model.containsAttribute ( nameAttribute ) ) {
            return;
        }
        Object data = session.getAttribute ( nameAttribute );
        if ( data != null ) {
            model.addAttribute ( nameAttribute, data );
            return;
        }
        try {
            @SuppressWarnings ( "unchecked" )
            T initModel = (T) initModel ( clazz.newInstance (), session );
            model.addAttribute ( nameAttribute, initModel );
            saveAttributeIntoSession ( initModel, session );
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new RuntimeException ( "Errore nella creazione l'oggetto di model.", e );
        }
    }

    protected String redirect ( String redirectPath, RedirectAttributes redirectAttributes, Object attibute, BindingResult bindingResult, Messaggi messaggi ) {
        if ( redirectAttributes != null ) {
            if ( attibute != null ) {
                String nameAttribute = StringUtils.uncapitalize ( attibute.getClass ().getSimpleName () );
                addFlashAttributeIfNotNull ( redirectAttributes, "org.springframework.validation.BindingResult." + nameAttribute, bindingResult );
                addFlashAttributeIfNotNull ( redirectAttributes, nameAttribute, attibute );
            }
            if ( messaggi != null ) {
                addFlashAttributeIfNotNull ( redirectAttributes, "messaggi", messaggi );
            }
        }
        return "redirect:" + redirectPath;
    }

    protected String redirect ( String redirectPath, RedirectAttributes redirectAttributes, Object attibute, BindingResult bindingResult ) {
        return redirect ( redirectPath, redirectAttributes, attibute, bindingResult, null );
    }

    protected String redirect ( String redirectPath, RedirectAttributes redirectAttributes, Messaggi messaggi ) {
        return redirect ( redirectPath, redirectAttributes, null, null, messaggi );
    }

    protected String redirect ( String redirectPath ) {
        return redirect ( redirectPath, null, null, null, null );
    }

    private void addFlashAttributeIfNotNull ( RedirectAttributes redirectAttributes, String name, Object attibute ) {
        if ( attibute == null ) {
            return;
        }
        redirectAttributes.addFlashAttribute ( name, attibute );
    }

    private Object initModel ( _Model o, HttpSession session ) {
        if ( o instanceof RiferimentoSenzaCodiceFiscale ) {
            return initModel ( (RiferimentoSenzaCodiceFiscale) o );
        }
        if ( o instanceof Riferimento ) {
            return initModel ( (Riferimento) o );
        }
        if ( o instanceof DatiPersonali ) {
            return initModel ( (DatiPersonali) o, session );
        }
        return o;
    }

    private Object initModel ( RiferimentoSenzaCodiceFiscale riferimentoSenzaCodiceFiscale ) {

        return riferimentoSenzaCodiceFiscale;
    }

    private Object initModel ( Riferimento riferimento ) {

        return riferimento;
    }

    private Object initModel ( DatiPersonali datiPersonali, HttpSession session ) {
        Pagamento pagamento = getAttributeFromSession ( Pagamento.class, session );
        if ( pagamento != null ) {
            datiPersonali.setSoggettoGiuridico ( pagamento.getPagatore ().getFlagPersonaFisica () ? "personaFisica" : "personaGiuridica" );
            datiPersonali.setRagioneSociale ( pagamento.getPagatore ().getRagioneSociale () );
            datiPersonali.setNome ( pagamento.getPagatore ().getNome () );
            datiPersonali.setCognome ( pagamento.getPagatore ().getCognome () );
            datiPersonali.setCodiceFiscale ( pagamento.getPagatore ().getCodiceFiscale () );
            datiPersonali.setEmail ( pagamento.getPagatore ().getEmail () );
            datiPersonali.setConfirmEmail ( pagamento.getPagatore ().getEmail () );
            datiPersonali.setImporto ( pagamento.getImporto () );
            if ( StringUtils.isEmpty ( pagamento.getNote () ) && !pagamento.getPagamentoSpontaneo () ) {
                datiPersonali.setNote ( " " );
            } else {
                datiPersonali.setNote ( pagamento.getNote () );
            }
            datiPersonali.setFlagPrivacy ( pagamento.getConsensoPrivacy () );
            if ( !CollectionUtils.isEmpty (pagamento.getComponenti () ) ) {
                for ( PagamentoComponenti pagamentoComponenti: pagamento.getComponenti () ) {
                    datiPersonali.addComponentiImporto ( pagamentoComponenti.getImporto (), pagamentoComponenti.getCausale () );
                }
            }
            if ( !CollectionUtils.isEmpty ( pagamento.getComponentiSecondari () ) ) {
                for ( PagamentoSecondarioComponenti pagamentoComponenti: pagamento.getComponentiSecondari () ) {
                    datiPersonali.addComponentiImporto ( pagamentoComponenti.getImporto (), pagamentoComponenti.getCausale () );
                }
            }
            if ( !pagamento.getRiferimenti ().isEmpty () ) {
                for ( PagamentoRiferimenti pagamentoRiferimenti: pagamento.getRiferimenti () ) {
                    datiPersonali.addRiferimentiPagamento ( pagamentoRiferimenti.getNome (), pagamentoRiferimenti.getValore () );
                }
            }
            datiPersonali.setFlagInitFromSession(false);
        } else {
			IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" );
			if ( identitaShibboleth != null ) {
				datiPersonali.setNome ( identitaShibboleth.getNome () );
				datiPersonali.setCognome ( identitaShibboleth.getCognome () );
				datiPersonali.setCodiceFiscale ( identitaShibboleth.getCodiceFiscale () );
				datiPersonali.setEmail ( identitaShibboleth.getEmail () );
				datiPersonali.setConfirmEmail ( identitaShibboleth.getEmail () );
			}
			datiPersonali.setSoggettoGiuridico ( "personaFisica" );
            datiPersonali.setFlagInitFromSession(true);
        }
        return datiPersonali;
    }

    ViewCommonData initCommonData ( String action, int step, Boolean indietro, Boolean prosegui, String descrizione, HttpSession session ) {
        ViewCommonData commonData = new ViewCommonData ();
        String name = this.getClass ().getSimpleName ();
        StringBuilder actionB = new StringBuilder ();
        boolean AccessoLiberoFlag = name.startsWith ( "AccessoLibero" );
        Locale locale =Locale.getDefault();
        commonData.setLinguaLocale(null!=locale?locale.getLanguage():"");
        commonData.setFlagDonazione( Boolean.FALSE );

        if ( AccessoLiberoFlag ) {
            commonData.setAccessoAutenticato ( false );
            actionB.append ( "/accessoLibero" );
        } else {
            commonData.setAccessoAutenticato ( true );
            actionB.append ( "/private" );
        }

        if ( name.contains ( "PagamentoSpontaneo" ) ) {
            commonData.setPagamentoSpontaneo ( true );
            commonData.setStampaAvviso ( false );
            if ( StringUtils.equals ( "Donazione", descrizione ) ) {
                commonData.setTitolo ( "Donazione" );
                commonData.setFlagDonazione( Boolean.TRUE );
               
                descrizione = null;
            } else {
                commonData.setTitolo ( "Pagamento spontaneo" );
            }
            commonData.setFlagCodFiscStraniero(true);
            actionB.append ( "/pagaSenzaIuv" );
            commonData.setStep ( new Step ( AccessoLiberoFlag ? Step.Percorsi.PAGA_FREE_NOIUV : Step.Percorsi.PAGA_AUTH_NOIUV, step ) );
        } else if ( name.contains ( "PagamentoConIuv" ) ) {
            commonData.setPagamentoSpontaneo ( false );
            commonData.setStampaAvviso ( false );
            commonData.setTitolo ( "Paga con PIEMONTEPAY <small>con IUV</small>" );
            actionB.append ( "/pagaConIuv" );
            commonData.setStep ( new Step ( AccessoLiberoFlag ? Step.Percorsi.PAGA_FREE_CONIUV : Step.Percorsi.PAGA_AUTH_CONIUV, step ) );
        } else if ( name.contains ( "PagamentoAltroRiferimentoConIuv" ) ) {
            commonData.setPagamentoSpontaneo ( false );
            commonData.setStampaAvviso ( false );
            commonData.setTitolo ( "Paga con PIEMONTEPAY <small>con IUV</small>" );
            actionB.append ( "/pagaAltroRiferimentoConIuv" );
            commonData.setStep ( new Step ( AccessoLiberoFlag ? Step.Percorsi.PAGA_FREE_CONIUV : Step.Percorsi.PAGA_AUTH_CONIUV_RF, step ) );
        } else if ( name.contains ( "Verifica" ) ) {
            commonData.setPagamentoSpontaneo ( false );
            commonData.setStampaAvviso ( false );
            commonData.setTitolo ( "Verifica pagamento" );
            actionB.append ( "/verifica" );
            commonData.setStep ( new Step ( AccessoLiberoFlag ? Step.Percorsi.VERIFICA_FREE : Step.Percorsi.VERIFICA_AUTH, step ) );
        } else if ( name.contains ( "StampaAvviso" ) ) {
            commonData.setPagamentoSpontaneo ( true );
            commonData.setStampaAvviso ( true );
            commonData.setTitolo ( "Stampa avviso" );
            commonData.setFlagCodFiscStraniero(true);
            actionB.append ( "/stampaAvviso" );
            commonData.setStep ( new Step ( AccessoLiberoFlag ? Step.Percorsi.STAMPA_FREE : Step.Percorsi.STAMPA_AUTH, step ) );
        }

        actionB.append ( "/" ).append ( action );
     
        commonData.setDescrizione ( descrizione );
        commonData.setFormAction ( actionB.toString () );

        if ( commonData.getAccessoAutenticato () && session != null ) {
            IdentitaShibboleth identitaShibboleth = (IdentitaShibboleth) session.getAttribute ( "identitaShibboleth" );
            if ( identitaShibboleth != null ) {
                
                commonData.setNome ( identitaShibboleth.getNome () );
                commonData.setCognome ( identitaShibboleth.getCognome () );
                commonData.setCodiceFiscale ( identitaShibboleth.getCodiceFiscale () );
                commonData.setMail ( identitaShibboleth.getEmail () );
			}
        }

        commonData.setIndietro ( indietro );
        commonData.setProsegui ( prosegui );
        return commonData;
    }

    ViewCommonData initCommonData ( String action, int step, Boolean indietro, Boolean prosegui, String descrizione ) {
        return initCommonData ( action, step, indietro, prosegui, descrizione, null );
    }

	protected String getErrorOnGetDatiSpecificiRiscossione ( PagamentoFacade pagamentoFacade, Riferimento riferimento ) {
		String errorOnGetDatiSpecificiRiscossione = null;
		try {
			DatiSpecificiRiscossioneInput request = new DatiSpecificiRiscossioneInput ();
			request.setCodiceFiscaleEnte ( riferimento.getCodiceFiscaleEnte () );
			request.setTipoPagamento ( riferimento.getCodiceVersamento () );
			request.setAnnoEsercizio ( Calendar.getInstance ().get ( Calendar.YEAR ) );
			pagamentoFacade.getDatiSpecificiRiscossione ( request );
		} catch ( TassonomiaServiceException e ) {
			errorOnGetDatiSpecificiRiscossione = e.getMessage ();
		} catch ( Exception e ) {
			errorOnGetDatiSpecificiRiscossione = "Errore in fase di invocazinoe del catalogo!";
		}
		return errorOnGetDatiSpecificiRiscossione;
	}

	protected Messaggi getForErrorOnGetDSR ( String errorOnGetDatiSpecificiRiscossione ) {
		Messaggi messaggi = new Messaggi ();
		messaggi.addMessaggio ( new Messaggio ( LevelMessage.DANGER, errorOnGetDatiSpecificiRiscossione ) );
		return messaggi;
	}

	Predicate<? super Pagamento> filtraPagamentiNonMarcaBollo () {
		return p -> (p.getTipoPagamento() == null || p.getTipoPagamento().getTipologiaPagamento() == null ||
						(p.getTipoPagamento().getTipologiaPagamento() != null && !"MABL".equalsIgnoreCase(p.getTipoPagamento().getTipologiaPagamento().getCodice())
										&&  !"PABL".equalsIgnoreCase(p.getTipoPagamento().getTipologiaPagamento().getCodice())));
	}

}
