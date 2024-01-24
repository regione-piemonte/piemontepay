/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoSintesi;

/**
 * @author vsgro
 */
public interface FlussoManager {

    public List<FlussoSintesi> leggi ( ArrayList<Long> ids );

    public List<FlussoSintesi> leggiPerIdFlussoOrigine (Long idFlussoOrigine );

    public void aggiornaDatiProvvisorio(FlussoSintesi flusso);
    
    public FlussoSintesi inserisciSintesiDaSintesi(FlussoSintesi flusso);

    public void aggiornaIdErrore ( StatoFlussoErrore statoFlussoErrore, Long idFlussoSintesi);

    public DTOOutputWsFlussoSintesi ricercaFlussoSintesi (Long idFlussoOrigine);

    public BigDecimal getImportoTotaleDettagli ( FlussoOrigine flussoOrigine );

    public List<FlussoSintesi> recuperaFlussiSintesiDaOrigine ( FlussoOrigine flussoOrigine );

    public BigDecimal getImportoTotaleSintesi ( FlussoOrigine flussoOrigine );

}
