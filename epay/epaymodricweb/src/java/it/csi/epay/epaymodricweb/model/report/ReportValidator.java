/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.report;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//import com.sun.deploy.uitoolkit.impl.fx.Utils;

public class ReportValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object raw, Errors errors) {

        if (raw instanceof PrenotazioneRicercaReportFormVO) {
        	PrenotazioneRicercaReportFormVO o = (PrenotazioneRicercaReportFormVO)raw;
        	if (!StringUtils.isEmpty(o.getNomeReport()))
        	{
        		o.setNomeReport(o.getNomeReport().trim());
        	}
        	
        	 if (StringUtils.isEmpty(o.getNomeReport())) {
        		  errors.rejectValue(
                          "nomeReport",
                          "nomeReport.validitaGenerica",
                          "E' necessario inserire il nome report"
                                      );
        		 
        	 }
        	 
//        	 if (StringUtils.isEmpty(o.getTipoReport())) {
//       		  errors.rejectValue(
//                         "tipoReport",
//                         "tipoReport.validitaGenerica",
//                         "E' necessario inserire il tipo report"
//                                     );
//       		 
//       	 } 
            if (o.isEmpty()) {
                errors.rejectValue(
                    "validitaGenerica",
                    "validitaGenerica.validitaGenerica",
                    "E' necessario inserire almeno un criterio di ricerca"
                                );
            }

            if ( (o.getDataElaborazioneDa() != null && o.getDataElaborazioneA() == null) ) {
                o.setDataElaborazioneA ( new Date () );
            }

            if ( (o.getDataElaborazioneDa() == null && o.getDataElaborazioneA() != null)) {
                errors.rejectValue(
                    "dataElaborazioneDa",
                    "dataElaborazioneDa.validitaGenerica",
                    "E' necessario inserire anche la data di inizio periodo"
                                );
            }

            if ( o.getDataElaborazioneA () != null
                            && o.getDataElaborazioneDa () != null
                            && ( o.getDataElaborazioneDa ().after ( o.getDataElaborazioneA () ) ) ) {
                errors.rejectValue (
                    "dataElaborazioneDa",
                    "dataElaborazioneDa.validitaGenerica",
                                "La \"data elaborazione DA\" deve essere precedente o uguale alla \"data elaborazione A\"" );
            }

            if ( o.getDataRicezioneA () != null
                && o.getDataRicezioneDa () != null
                && ( o.getDataRicezioneDa ().after ( o.getDataRicezioneA () ) ) ) {
                errors.rejectValue (
                    "dataRicezioneDa",
                    "dataRicezioneeDa.validitaGenerica",
                    "La \"data ricezione DA\" deve essere precedente o uguale alla \"data ricezione A\"" );
            }


        }
    }
}
