/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsElaborazione;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciFlussiDaRielaborare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;


/**
 * @author vsgro
 */
public interface ElaborazioneManager {

    public Elaborazione leggi ( Long id );

    public List<Elaborazione> recuperaElaborazioniForzate ( String idEnte );
    //Nuru 2.2.12
    public CblTElaborazione aggiornaStatoElaborazioneInternal ( Long idElaborazione, String nuovoStato );

    public void salva ( Elaborazione elaborazione );

    public DTOOutputWsEsito inserisciElaborazione ( DTOInputWsElaborazione in );

    public boolean esistonoElaborazioniTerminateOggi ( String idEnte );

    public Elaborazione recuperaElaborazioneSchedulata ( String idEnte );

    public boolean isElaborazioneTerminataPerEnti ( List<CblTEnte> enti );

    public DTOOutputWsEsito inserisciElaborazionePerRielaborareFlussi ( DTOInputWsInserisciFlussiDaRielaborare input );

}
