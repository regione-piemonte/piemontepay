/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models.validators.validatorModel;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayweb.frontend.models.ChiamataEsterna;
import it.csi.epay.epayweb.frontend.models.Riferimento;

@Component
public class ChiamataEsternaComplexValidator implements Validator {

	@Autowired
	HttpSession session;

	@Autowired
	private ChiamataEsternaFacade chiamataEsternaFacade;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Riferimento.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

	public void validateAndVerify(Object target, Errors errors) {
		
		if (target == null)
			return;
		ChiamataEsterna chiamataEsterna = (ChiamataEsterna) target;

		// TEST SUL CAMPO IUV
		if (errors.getFieldError("iuv") != null)
			return;
		
		if (StringUtils.isBlank(chiamataEsterna.getIuv())) {
			errors.rejectValue("iuv", null, "Iuv non presente");
		}
		
        if ( StringUtils.isBlank ( chiamataEsterna.getIdentificativoPagamento () ) ) {
            errors.rejectValue ( "identificativoPagamento", null, "Identificativo Pagamento non presente" );
        } else if ( chiamataEsterna.getIdentificativoPagamento ().length () > 50 ) {
            errors.rejectValue ( "identificativoPagamento", null, "Identificativo Pagamento deve essere lungo massimo 50 caratteri." );
		}
		
		if (StringUtils.isBlank(chiamataEsterna.getCodiceFiscale())) {
            errors.rejectValue ( "codiceFiscale", null, "Codice fiscale non presente" );
        } else if ( chiamataEsterna.getCodiceFiscale ().length () < 11 || chiamataEsterna.getCodiceFiscale ().length () > 35 ) {
            errors.rejectValue ( "codiceFiscale", null, "Codice fiscale deve essere lungo tra 11 e 35 caratteri." );
		}

		if (StringUtils.isBlank(chiamataEsterna.getDigest())) {
			errors.rejectValue("digest", null, "Digest fiscale non presente");
        } else if ( chiamataEsterna.getDigest ().length () > 100 ) {
            errors.rejectValue ( "digest", null, "Digest deve essere lungo massimo 100 caratteri." );
		}
		
        String digestCalcolato = calcolaDigest ( chiamataEsterna );
//        System.out.println ( "************************** digestCalcolato **************************" );
//        System.out.println ( "* " + digestCalcolato + " *" );
//        System.out.println ( "************************** digestCalcolato **************************" );
		if (null == digestCalcolato || !digestCalcolato.equalsIgnoreCase(chiamataEsterna.getDigest().replaceAll("[\n\r]",""))) {
			errors.rejectValue("digest", null, "Verifica digest fallita");
		}
		
		if (errors.getFieldError("digest") != null)
			return;
		
		if (errors.getFieldError("codiceFiscale") != null)
			return;
		
		if (errors.getFieldError("identificativoPagamento") != null)
			return;

	}

	private String calcolaDigest(ChiamataEsterna chiamataEsterna) {
		
		String digest = "";
		ChiamanteEsterno chiamanteEsterno;
		try {
			chiamanteEsterno = chiamataEsternaFacade.recuperaChiamanteEsterno(chiamataEsterna.getCodiceChiamante());
            String sToDigest
                = chiamanteEsterno.getPassphrase () + "%%%%" + chiamataEsterna.getCodiceChiamante () + chiamataEsterna.getIdentificativoPagamento ()
                + chiamataEsterna.getIuv () + chiamanteEsterno.getUrl () + "%%%%";
						
			byte[] bMac = DigestUtils.sha256(sToDigest.getBytes());
			digest = Base64.encodeBase64String (bMac).replaceAll("[\n\r]","");
			digest = digest.substring(0, digest.length() > 50 ? 50: digest.length());
		} catch (IllegalAccessException | NoDataException e) {
			e.printStackTrace();
		}
		
		
		return digest;
	}
}
