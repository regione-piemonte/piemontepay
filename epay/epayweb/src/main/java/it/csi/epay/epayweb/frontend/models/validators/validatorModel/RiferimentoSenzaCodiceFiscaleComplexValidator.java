/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.validatorModel;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayweb.frontend.models.RiferimentoSenzaCodiceFiscale;
import it.csi.epay.epayweb.utilities.LogUtil;

@Component
public class RiferimentoSenzaCodiceFiscaleComplexValidator implements Validator {
	
	protected LogUtil log = new LogUtil ( this.getClass () );
	
	@Autowired
	HttpSession session;
	
	
	@Autowired
	private PagamentoFacade pagamentoFacade;

	@Override
	public boolean supports(Class<?> clazz) {
        return RiferimentoSenzaCodiceFiscale.class.equals ( clazz );
	}

	@Override
	public void validate(Object target, Errors errors) {
        if ( target == null ) {
            return;
        }
        RiferimentoSenzaCodiceFiscale riferimentoSenzaCodiceFiscale = (RiferimentoSenzaCodiceFiscale) target;

        // TEST SUL CAMPO IUV
        if ( errors.getFieldError ( "iuv" ) != null ) {
            return;
        }
        try {
            /**
             * 15<LENGTH(campo codice avviso/IUV)<17 AND campo codice avviso/IUV inizia per 0 prendere le ultime 15 cifre

                LENGTH(campo codice avviso/IUV)>=17 AND campo codice avviso/IUV inizia per 3 prendere le ultime 17 cifre
                LENGTH(campo codice avviso/IUV)>=17 AND campo codice avviso/IUV inizia per 0 prendere le ultime 15 cifre.
                
                15= LENGTH(campo codice avviso/IUV) prendere l'intera stringa ok
                
                campo codice avviso/IUV inizia per RF prendere l'intera stringa ok
             */
            String iuv = riferimentoSenzaCodiceFiscale.getIuv ();

            if ( !riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "RF" ) && riferimentoSenzaCodiceFiscale.getIuv ().length () != 15 &&
                riferimentoSenzaCodiceFiscale.getIuv ().length () >= 17 && riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "3" ) ) {
                iuv = riferimentoSenzaCodiceFiscale.getIuv ().substring ( riferimentoSenzaCodiceFiscale.getIuv ().length () - 17 );
            } else if ( !riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "RF" ) && riferimentoSenzaCodiceFiscale.getIuv ().length () != 15 &&
                riferimentoSenzaCodiceFiscale.getIuv ().length () >= 17 && riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "0" ) ) {
                iuv = riferimentoSenzaCodiceFiscale.getIuv ().substring ( riferimentoSenzaCodiceFiscale.getIuv ().length () - 15 );
            } else if ( !riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "RF" ) && riferimentoSenzaCodiceFiscale.getIuv ().length () != 15 &&
                riferimentoSenzaCodiceFiscale.getIuv ().length () < 17 && riferimentoSenzaCodiceFiscale.getIuv ().startsWith ( "0" ) ) {
                iuv = riferimentoSenzaCodiceFiscale.getIuv ().substring ( riferimentoSenzaCodiceFiscale.getIuv ().length () - 15 );
            }
            
            
            Pagamento pagamento = pagamentoFacade.ricerca ( iuv );
            if ( pagamento == null ) {
                errors.rejectValue ( "iuv", null, "Codice Iuv inesistente." );
                return;
            }
            Pagamento pagamentoAttivo = pagamentoFacade.ricercaSoloAttivi ( iuv );
            if ( pagamentoAttivo == null ) {
                errors.rejectValue ( "iuv", null,
                    "Codice Iuv riferito a pagamento effettuato, annullato o in attesa di ricevuta oppure non pi\u00F9 valido perch\u00E9 scaduto." );
                return;
            }
            
            if ( pagamento.getImporto ().compareTo ( new BigDecimal ( 0 ) ) <1) {
                errors.rejectValue ( "iuv", null,
                    "Non \u00E9 possibile procedere con le operazioni, l'importo della posizione debitoria \u00E9 0. Si consiglia di riprovare pi\u00F9 tardi, Se il problema dovesse persistere contattare l'assistenza" );
                return;
            }
           Rt rt = null;
           try {
        	   rt = pagamentoFacade.ricercaRtByIuv(iuv);
        	   if (null != rt && rt.getIdRR()!=null) {
            	   errors.rejectValue ( "iuv", null,
                           "Codice Iuv riferito a pagamento revocato." );
                       return;
               }
           } catch (NoDataException noDataExceptionRt) {
        	   log.debug("validate", "RT non trovata");
           }
           //FIXME RDI-34
           if (pagamento != null && pagamento.getTipoPagamento () != null && pagamento.getTipoPagamento ().getTipologiaPagamento () != null) {
               if ("MABL".equalsIgnoreCase ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () )
                   || "PABL".equalsIgnoreCase ( pagamento.getTipoPagamento ().getTipologiaPagamento ().getCodice () )) {
                   errors.rejectValue ( "iuv", null, "Iuv relativo a pagamento con marca da bollo. Non e' consentito il pagamento dallo Sportello PiemontePay, utilizzare l'apposito servizio" );
               }
           }

            session.setAttribute ( "pagamento", pagamento );
        } catch ( NoDataException e ) {
            session.removeAttribute ( "pagamento" );
            errors.rejectValue ( "iuv", null, "Codice Iuv invalido." );
        }
	}

    public void validateRiferimentiPagamento ( Object target, Errors errors ) {
        if ( target == null ) {
            return;
        }
        RiferimentoSenzaCodiceFiscale riferimentiPagamento = (RiferimentoSenzaCodiceFiscale) target;

        /*
         * Per la validazione, prendo i valori di pagamento e vedo se sono uguali. Nel caso restituisco errore.
         */
        for ( int i = 0; i < riferimentiPagamento.getRiferimentiPagamento ().size (); i++ ) {
            if ( StringUtils.isBlank ( riferimentiPagamento.getRiferimentiPagamento ().get ( i ).getValore () ) ) {
                errors.rejectValue ( "riferimentiPagamento[" + i + "].valore", null, ( "Valore non inserito" ) );

            }
        }
    }

    public void checkPagamentoRiferimenti ( Object target, Errors errors ) {

        RiferimentoSenzaCodiceFiscale riferimento = (RiferimentoSenzaCodiceFiscale) target;

        Pagamento pagamento = (Pagamento) session.getAttribute ( "pagamento" );

        for ( int i = 0; i < pagamento.getRiferimenti ().size (); i++ ) {
            if ( pagamento.getRiferimenti ().get ( i ).getNome ().equals ( riferimento.getRiferimentiPagamento ().get ( i ).getNome () ) &&
                !pagamento.getRiferimenti ().get ( i ).getValore ().equals ( riferimento.getRiferimentiPagamento ().get ( i ).getValore () ) ) {
                errors.rejectValue ( "riferimentiPagamento[" + i + "].valore", null, ( "Valore non corrispondente a quello in pagamento" ) );

            }
        }
    }
}
