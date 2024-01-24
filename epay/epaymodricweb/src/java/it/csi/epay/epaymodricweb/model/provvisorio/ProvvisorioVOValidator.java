/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.provvisorio;

import java.util.Date;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class ProvvisorioVOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object raw, Errors errors) {

        if ( raw instanceof ProvvisorioVO ) {
            ProvvisorioVO o = (ProvvisorioVO) raw;

            if ( !StringUtils.hasText ( o.getIdentificativoFlusso () ) ) {
                errors.rejectValue(
                    "identificativoFlusso",
                    "identificativoFlusso.validitaGenerica",
                    "E' necessario valorizzare l'identificativo flusso"
                                );
            }
            if ( null == o.getDataMovimento () ) {
                errors.rejectValue (
                    "dataMovimento",
                    "dataMovimento.validitaGenerica",
                    "E' necessario inserire la data movimento" );
            }
            if ( null == o.getAnnoEsercizio () ) {
                errors.rejectValue (
                    "annoEsercizio",
                    "annoEsercizio.validitaGenerica",
                    "E' necessario inserire l'anno esercizio" );
            }
            if ( null == o.getAnnoProvvisorio () ) {
                errors.rejectValue (
                    "annoProvvisorio",
                    "annoProvvisorio.validitaGenerica",
                    "E' necessario inserire l'anno provvisorio" );
            }
            if ( null == o.getNumeroProvvisorio () ) {
                errors.rejectValue (
                    "numeroProvvisorio",
                    "numeroProvvisorio.validitaGenerica",
                    "E' necessario inserire il numero provvisorio" );
            }
            if ( null == o.getImportoProvvisorio () ) {
                errors.rejectValue (
                    "importoProvvisorio",
                    "importoProvvisorio.validitaGenerica",
                    "E' necessario inserire l'importo provvisorio" );
            }
            if (!StringUtils.hasText( o.getStato () ))
            		o.setStato(StatoProvvisorio.VALIDO.name());
            else if ( !StatoProvvisorio.VALIDO.name ().equals ( o.getStato () )
                                            && !StatoProvvisorio.ANNULLATO.name ().equals ( o.getStato () ) )  {
                errors.rejectValue (
                    "stato",
                    "stato.validitaGenerica",
                    "E' necessario inserire lo stato: [VALIDO]/[ANNULLATO]" );
            }
            if ( !StringUtils.hasText ( o.getDescrizione () ) ) {
                errors.rejectValue (
                    "descrizione",
                    "descrizione.validitaGenerica",
                    "E' necessario inserire la descrizione" );
            }
        } else if ( raw instanceof RicercaProvvisorioFiltroVO ) {
            RicercaProvvisorioFiltroVO o = (RicercaProvvisorioFiltroVO) raw;
            if ( o.isEmpty () ) {
                errors.rejectValue (
                    "validitaGenerica",
                    "validitaGenerica.validitaGenerica",
                                "E' necessario inserire almeno un criterio di ricerca" );
            }

            if ( ( o.getDataElaborazioneDa () != null && o.getDataElaborazioneA () == null ) ) {
                o.setDataElaborazioneA ( new Date () );
            }

            if ( ( o.getDataElaborazioneDa () == null && o.getDataElaborazioneA () != null ) ) {
                errors.rejectValue (
                    "dataElaborazioneDa",
                    "dataElaborazioneDa.validitaGenerica",
                    "E' necessario inserire la data di inizio periodo" );
            }

            if ( o.getDataElaborazioneA () != null
                            && o.getDataElaborazioneDa () != null
                            && ( o.getDataElaborazioneDa ().after ( o.getDataElaborazioneA () ) ) ) {
                errors.rejectValue (
                    "dataElaborazioneDa",
                    "dataElaborazioneDa.validitaGenerica",
                                "La \"data movimento DA\" deve essere precedente o uguale alla \"data movimento A\"" );
            }
        } else if ( raw instanceof FileVO ) {
            FileVO o = (FileVO) raw;
            if ( o.getFileContent () == null || o.getFileContent ().isEmpty () ) {
                errors.rejectValue (
                    "fileContent",
                    "fileContent.validitaGenerica",
                                "E' necessario selezionare un file" );
            }
        }
    }

}
