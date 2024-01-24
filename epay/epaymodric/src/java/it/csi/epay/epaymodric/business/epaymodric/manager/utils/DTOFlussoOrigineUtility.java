/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;


public class DTOFlussoOrigineUtility {

    public static DTOFlussoOrigine getFlusso ( CblTFlussoOrigine flussoOrigine ) {
        DTOFlussoOrigine item = new DTOFlussoOrigine ();

        item.setContatoreTentativi ( flussoOrigine.getContatoreTentativi () );
        item.setDataInserimento ( flussoOrigine.getDataInserimento () );
        item.setDataOraFlusso ( flussoOrigine.getDataoraFlusso () );

        //TODO Tenere da conto il popolamento di questo campo!
        item.setIbanRiversamentoFlusso ( "" );

        item.setId ( "" + flussoOrigine.getId () );
        item.setIdentificativoFlusso ( flussoOrigine.getIdentificativoFlusso () );
        item.setIdentificativoIstitutoRicevente ( flussoOrigine.getIdentificativoIstitutoRicevente () );
        item.setIdentificativoIstitutoRiceventeDescrizione ( flussoOrigine.getCblTEnte ().getDenominazione () );
        item.setIdentificativoPsp ( flussoOrigine.getCblTPsp ().getIdentificativoPsp () );
        item.setIdentificativoPspDescrizione ( flussoOrigine.getCblTPsp ().getDenominazionePsp () );
        item.setImportoTotalePagamenti ( flussoOrigine.getImportoTotalePagamenti () );
        item.setNumeroTotalePagamenti ( flussoOrigine.getNumeroTotalePagamenti () );
        item.setFlagFlussoIntermediato(flussoOrigine.getFlagFlussoIntermediato());

        if ( flussoOrigine.getCblDStatoFlusso () != null ) {
            DTOStatoFlusso stato = new DTOStatoFlusso ();
            stato.setCodiceStato ( flussoOrigine.getCblDStatoFlusso ().getCodiceStato () );
            stato.setDescrizioneStato ( flussoOrigine.getCblDStatoFlusso ().getDescrizioneStato () );
            stato.setId ( "" + flussoOrigine.getCblDStatoFlusso ().getId () );
            stato.setPermettiRielaborazione ( flussoOrigine.getCblDStatoFlusso ().getPermettiRielaborazione () );
            item.setStatoFlusso ( stato );
        }
       

        return item;
    }

}
