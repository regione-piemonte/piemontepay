/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.codiceversamento.validators;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.csi.epay.epaypacatalogweb.model.codiceversamento.ModificaCodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.validators.BicValidator;
import it.csi.epay.epaypacatalogweb.model.validators.EPayValidatorUtils;


public class ModificaCodiceVersamentoValidator implements Validator {

    @Override
    public boolean supports ( Class<?> paramClass ) {
        return ModificaCodiceVersamentoVO.class.equals ( paramClass );
    }

    @Override
    public void validate ( Object obj, Errors errors ) {

        ModificaCodiceVersamentoVO mevo = (ModificaCodiceVersamentoVO) obj;
        IBANCheckDigit ibanValidator = new IBANCheckDigit ();

        if (StringUtils.isBlank(mevo.getCodiceTipoPagamento())){
        	 ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "codiceTipoPagamento", "codiceTipoPagamento.required", "Tipo Pagamento Obbligatorio " );
       }
        
        ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "descrizione", "descrizione.required",
                        "Descrizione Codice versamento Obbligatoria" );
        ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "codiceVoceEntrata", "codiceVoceEntrata.required", "Voce Entrata PPay Obbligatoria" );
            
        if (!"REDS".equals ( mevo.getCodiceTipoPagamento () ))
        {
            ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "codiceModalitaIntegrazione", "codiceModalitaIntegrazione.required", "Modalita' di integrazione Obbligatoria" );
        }
       
        //RDI-046
        //ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "codiceTipoPagamento", "codiceTipoPagamento.required", "Tipo Pagamento Obbligatorio" );

        if ( mevo.getCodice () != null && mevo.getCodice ().startsWith ( "XT" ) ) {
            if ( !"MABL".equals ( mevo.getCodiceTipoPagamento () ) ) {
                errors.rejectValue ( "codiceTipoPagamento", "codiceTipoPagamento.validitaGenerica",
                    "Il tipo di pagamento deve essere Marca da bollo in base alla voce di entrata selezionata" );
            }
        }
        
        if ( "MABL".equals ( mevo.getCodiceTipoPagamento () ) ) {
        	 if ( mevo.getCodice () != null && !mevo.getCodice ().startsWith ( "XT" ) ) {
        		 errors.rejectValue ( "codiceTipoPagamento", "codiceTipoPagamento.validitaGenerica",
                         "La voce di entrata selezionata non e' compatibile con il tipo pagamento Marca da bollo" );
        	 }
        }
        
        if (StringUtils.isNotBlank(mevo.getIban()) || StringUtils.isNotBlank(mevo.getBic())) {
            if (StringUtils.isNotBlank(mevo.getIban())) {
                if ( mevo.getIban () != null && !mevo.getIban ().toLowerCase ().startsWith ( "it" ) ) {
                    errors.rejectValue ( "iban", "iban.validitaGenerica", "Il codice IBAN non risulta italiano." );
                } else if ( mevo.getIban () != null && !ibanValidator.isValid ( mevo.getIban () ) ) {
                    errors.rejectValue ( "iban", "iban.validitaGenerica", "Il codice IBAN non risulta corretto." );
                }
                mevo.setIban ( mevo.getIban ().toUpperCase () );
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "iban", "iban.required", "IBAN Obbligatorio" );
            }
            
            if (StringUtils.isNotBlank(mevo.getBic())) {
                
                BicValidator bicValidator = new BicValidator();
                
                if ( mevo.getBic () != null && !bicValidator.isValid ( mevo.getBic() ) ) {
                    errors.rejectValue ( "bic", "bic", "Il codice bic non risulta corretto." );
                }
                mevo.setBic ( mevo.getBic().toUpperCase () );
            } else {
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "bic", "bic.required", "BIC Obbligatorio" );
            }
        }
        
        
        if (StringUtils.isNotBlank(mevo.getIbanAppoggio()) || StringUtils.isNotBlank(mevo.getBicAppoggio())) {
            if (StringUtils.isNotBlank(mevo.getIbanAppoggio())) {
                if ( mevo.getIbanAppoggio () != null && !mevo.getIbanAppoggio ().toLowerCase ().startsWith ( "it" ) ) {
                    errors.rejectValue ( "ibanAppoggio", "iban.validitaGenerica", "Il codice IBAN di appoggio non risulta italiano." );
                } else if ( mevo.getIban () != null && !ibanValidator.isValid ( mevo.getIban () ) ) {
                    errors.rejectValue ( "ibanAppoggio", "iban.validitaGenerica", "Il codice IBAN di appoggio non risulta corretto." );
                }
                mevo.setIbanAppoggio ( mevo.getIbanAppoggio ().toUpperCase () );
            }            
            if (StringUtils.isNotBlank(mevo.getBicAppoggio())) {
                
                BicValidator bicValidator = new BicValidator();
                
                if ( mevo.getBicAppoggio () != null && !bicValidator.isValid ( mevo.getBicAppoggio() ) ) {
                    errors.rejectValue ( "bicAppoggio", "bic", "Il codice bic di appoggio non risulta corretto." );
                }
                mevo.setBicAppoggio ( mevo.getBicAppoggio().toUpperCase () );
            } 
        }
        
        if ( mevo.getFlagInvioFlussi () != null && mevo.getFlagInvioFlussi () ) {
            ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "email", "email.required", "Email Obbligatorio in caso di Flag Invio Flussi abilitato" );
        }

        if ( mevo.getEmail () != null && !mevo.getEmail ().isEmpty () ) {
            if ( !EPayValidatorUtils.isValidEmail ( mevo.getEmail () ) ) {
                errors.rejectValue ( "email", "email.validitaGenerica", "Email non valida" );
            }
        }
        
        if (!StringUtils.isEmpty ( mevo.getCodiceTipoPagamento () ) && !mevo.getCodiceTipoPagamento ().equals ( "LDC" ))
        {
            if (Boolean.TRUE.equals ( mevo.getFlagElementiMultibeneficiario() ) ||
                            !(StringUtils.isEmpty(mevo.getModalitaAssociazioneMultibeneficiario()) ))
            {
                
                errors.rejectValue ( "codiceTipoPagamento", 
                    "codiceTipoPagamento.validitaGenerica", 
                    "In caso di pagamento diverso da lista di carico, il codice versamento non puo' essere di tipo multibeneficiario" );
            }
        }
        
        if (!StringUtils.isEmpty ( mevo.getModalitaAssociazioneMultibeneficiarioOld () ))
        {
            if(!StringUtils.isEmpty ( mevo.getModalitaAssociazioneMultibeneficiario () )
                            &&  !mevo.getModalitaAssociazioneMultibeneficiarioOld () .equals (  mevo.getModalitaAssociazioneMultibeneficiario () ) )
            {
                errors.rejectValue ( "modalitaAssociazioneMultibeneficiario", 
                    "modalitaAssociazioneMultibeneficiario.validitaGenerica", 
                                "Non e' possibile modificare la tipologia di associazione multibeneficiario se gia' presente" );
            }
        }
        
        if (Boolean.TRUE.equals ( mevo.getFlagElementiMultibeneficiario() )) {
            if (StringUtils.isEmpty(mevo.getModalitaAssociazioneMultibeneficiario())  ) {
                errors.rejectValue ( "modalitaAssociazioneMultibeneficiario", 
                		"modalitaAssociazioneMultibeneficiario.validitaGenerica", 
                		"In caso di abilitazione gestione esterna il campo modalita associazione multibeneficiario va compilato" );
            }
            else if ("2".equals(mevo.getModalitaAssociazioneMultibeneficiario()))
            {
            	if (null==  mevo.getEnteSecondarioAssociazioneMultibeneficiario ()   )
            	{
            		 errors.rejectValue ( "enteSecondarioAssociazioneMultibeneficiario", 
                     		"enteSecondarioAssociazioneMultibeneficiario.validitaGenerica", 
                     		"Campo obbligatorio" );
            	}
            	
            	if (null== mevo.getCovAssociatoAssociazioneMultibeneficiario ()  )
            	{
            		 errors.rejectValue ( "covAssociatoAssociazioneMultibeneficiario", 
                     		"covAssociatoAssociazioneMultibeneficiario.validitaGenerica", 
                     		"Campo obbligatorio" );
            	}
            	
            	
            }
        }
        
        if (Boolean.TRUE.equals ( mevo.getFlagPersonalizzazioneCov () )) {
            if (StringUtils.isEmpty(mevo.getDescrizioneTextCov ())  ) {
                errors.rejectValue ( "descrizioneTextCov", 
                        "descrizioneTextCov.validitaGenerica", 
                        "Descrizione notifica codice versamento con nessun carattere incluso" );
            }
            
            if (!StringUtils.isEmpty(mevo.getDescrizioneTextCov ())  
                            && mevo.getDescrizioneTextCov ().length ()>98) {
                errors.rejectValue ( "descrizioneTextCov", 
                        "descrizioneTextCov.validitaGenerica", 
                        "Descrizione notifica codice versamento con numero di caratteri superiore al massimo consentito" );
            }
            
                       
        }
        
        
//      PND
        
        if (!StringUtils.isEmpty ( mevo.getCodiceTipoPagamento () ))
        {
            if (!mevo.getCodiceTipoPagamento ().equals ( "LDC" ) && !mevo.getCodiceTipoPagamento ().equals ( "REDS" )
                            && !mevo.getCodiceTipoPagamento ().equals ( "PABL" ) && !mevo.getCodiceTipoPagamento ().equals ( "REDI" ))
            {
                if (!StringUtils.isEmpty ( mevo.getUrlAttualizzazionePnd () ) )
                {
                    ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "urlAttualizzazionePnd", "urlAttualizzazionePnd.required", "URL Obbligatorio" );
                }
                
                if (!StringUtils.isEmpty ( mevo.getStrCredenzialiPnd () ) )
                {
                    ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "strCredenzialiPnd", "strCredenzialiPnd.required", "Credenziali Obbligatorie" );
                }
                
            }
            else
            {
                if (!StringUtils.isEmpty ( mevo.getUrlAttualizzazionePnd () ) )
                {
                    ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "strCredenzialiPnd", "strCredenzialiPnd.required", "Credenziali Obbligatorie in caso di url presente" );
                    
                }
                
                if (!StringUtils.isEmpty ( mevo.getStrCredenzialiPnd () ) )
                {
                    ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "urlAttualizzazionePnd", "urlAttualizzazionePnd.required", "URL Obbligatorio in caso di credenziali presenti" );
                }
                
            }
        }
        
        
        if ( mevo.getDataInizioValidita () != null && mevo.getDataFineValidita () != null ) {
            if ( mevo.getDataFineValidita ().before ( mevo.getDataInizioValidita () ) ) {
                errors.rejectValue ( "dataFineValidita", "dataFineValidita.validitaGenerica",
                                "La data fine validita' deve essere successiva alla data inizio validita'" );
            }
        }
        
//      TODO controllo da eliminare : per ora solo commentando, da elimiare dopo la conferma dei test
//      if ( mevo.getDataFineValidita () != null ) {
//          
//          Calendar dataInizio = Calendar.getInstance ();
//          dataInizio.setTime ( mevo.getDataFineValidita () );
//          if ( dataInizio.get ( Calendar.YEAR ) > Calendar.getInstance ().get (Calendar.YEAR) ) {
//              errors.rejectValue ( "codiceTipoPagamento", "codiceTipoPagamento.validitaGenerica",
//                              "La data fine validita' non puo' essere superiore all'anno in corso" );
//          }
//      }
        
        if (!StringUtils.isEmpty (  mevo.getCodiceTipoPagamento () ) )
        {
            if (! mevo.getCodiceTipoPagamento ().equals ( "PS" ) ) {
                
                if ( mevo.getDataInizioValidita () != null || mevo.getDataFineValidita () != null ||Boolean.TRUE.equals (mevo.getFlagVisualizzaDaSportello () ) ) {
                    errors.rejectValue ( "codiceTipoPagamento", "codiceTipoPagamento.validitaGenerica",
                                    "I campi data fine validita', data inizio validita' e visualizzazione da sportello vanno valorizzati solo in caso di pagamento spontaneo" );
                }
            }
            
            if ( mevo.getCodiceTipoPagamento ().equals ( "PS" ) ) {
                
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "dataInizioValidita", "dataInizioValidita.required", "Data inizio validita' Obbligatoria in caso di pagamento spontaneo" );
                
                ValidationUtils.rejectIfEmptyOrWhitespace ( errors, "dataFineValidita", "dataFineValidita.required", "Data fine validita' Obbligatoria in caso di pagamento spontaneo" );
            }
        }
        
        
    }

}
