/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.validatorModel;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epayservices.interfaces.ejb.PagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayweb.frontend.models.Riferimento;
import it.csi.epay.epayweb.utilities.LogUtil;

@Component
public class RiferimentoComplexValidator implements Validator {
	
    protected LogUtil log = new LogUtil ( this.getClass () );
    
	@Autowired
	HttpSession session;
	
	
	@Autowired
	private PagamentoFacade pagamentoFacade;

	@Override
	public boolean supports(Class<?> clazz) {
		return Riferimento.class.equals(clazz); 
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target == null) return;
		Riferimento riferimento = (Riferimento)target;
		
		// TEST SUL CAMPO IUV
		if(errors.getFieldError("iuv") != null) return;
		try {
			Pagamento pagamento = pagamentoFacade.ricerca ( riferimento.getIuv());
			if (testCodiceFiscale(pagamento, riferimento.getCodiceFiscale())) {
				errors.rejectValue("iuv", null, "Codice Iuv errato.");
			}		
			session.setAttribute("pagamento", pagamento);
		} catch (NoDataException e) {
			session.removeAttribute("pagamento");
			errors.rejectValue("iuv", null, "Codice Iuv invalido.");
		}
	}
	
    public void validateAndVerify ( Object target, Errors errors ) {
        if ( target == null )
            return;
        Riferimento riferimento = (Riferimento) target;

        // TEST SUL CAMPO IUV
        if ( errors.getFieldError ( "iuv" ) != null )
            return;
        try {
            Pagamento pagamento = pagamentoFacade.ricerca ( riferimento.getIuv () );
            if ( pagamento == null ) {
                errors.rejectValue ( "iuv", null, "Codice Iuv inesistente." );
                return;
            }
            Pagamento pagamentoAttivo = pagamentoFacade.ricercaSoloAttivi ( riferimento.getIuv () );
            if ( pagamentoAttivo == null ) {
                errors.rejectValue ( "iuv", null,
                    "Codice Iuv riferito a pagamento effettuato, annullato o in attesa di ricevuta oppure non pi\u00F9 valido perch\u00E9 scaduto." );
                return;
            }
            if ( testCodiceFiscale ( pagamentoAttivo, riferimento.getCodiceFiscale () ) ) {
                errors.rejectValue ( "iuv", null, "Codice Iuv non relativo al codice fiscale o partita iva." );
                return;
            }
            try {
                Rt rt = pagamentoFacade.ricercaRtByIuv ( riferimento.getIuv () );
                if ( null != rt && rt.getIdRR () != null ) {
                    errors.rejectValue ( "iuv", null,
                        "Codice Iuv riferito a pagamento revocato." );
                    return;
                }
            } catch ( NoDataException noDataExceptionRt ) {
                log.debug ( "validate", "RT non trovata" );
            }
            if ( pagamento.getIuv () != riferimento.getIuv () ) {
                riferimento.setIuv ( pagamento.getIuv () );
            }
            session.setAttribute ( "pagamento", pagamento );
        } catch ( NoDataException e ) {
            session.removeAttribute ( "pagamento" );
            errors.rejectValue ( "iuv", null, "Codice Iuv invalido." );
        }
    }

    public void validateActiveCode ( Object target, Errors errors ) {
        if ( target == null )
            return;
        Riferimento riferimento = (Riferimento) target;

        // TEST SUL CAMPO IUV
        if ( errors.getFieldError ( "iuv" ) != null )
            return;
        try {
            Pagamento pagamento = pagamentoFacade.ricercaSoloAttivi ( riferimento.getIuv () );
            /*
             * Il pagamento legato al codice IUV deve essere: - attivo - il tipo pagamento attivo - non deve risultare 'In attesa' o 'Successo' (gia' pagato) -
             * il codice fiscale deve corrispondere
             */
            if ( testCodiceFiscale ( pagamento, riferimento.getCodiceFiscale () ) ) {
                errors.rejectValue ( "iuv", null, "Codice Iuv errato." );
            } else {
                if ( pagamento.getIuv () != riferimento.getIuv () ) {
                    riferimento.setIuv ( pagamento.getIuv () );
                }
            }
            session.setAttribute ( "pagamento", pagamento );
        } catch ( NoDataException e ) {
            session.removeAttribute ( "pagamento" );
            errors.rejectValue ( "iuv", null, "Codice Iuv invalido." );
        }
    }

    private boolean testCodiceFiscale ( Pagamento pagamento, String codiceFiscale ) {
        return ( pagamento == null ||
            pagamento.getPagatore () == null ||
            pagamento.getPagatore ().getCodiceFiscale () == null ||
            !pagamento.getPagatore ().getCodiceFiscale ().toUpperCase ().equals ( codiceFiscale.toUpperCase () ) );
    }

    public void checkCodiceFiscale ( Object target, Errors errors ) {
        if ( target == null )
            return;
        Riferimento riferimento = (Riferimento) target;

        // TEST SUL CAMPO CODICE FISCALE
        if ( errors.getFieldError ( "codiceFiscale" ) != null )
            return;

        Pagamento pagamento = (Pagamento) session.getAttribute ( "pagamento" );

        if ( testCodiceFiscale ( pagamento, riferimento.getCodiceFiscale () ) ) {
            errors.rejectValue ( "codiceFiscale", null, "Codice Fiscale non relativo allo IUV precedentemente inserito." );
            return;
        }
    }
}
