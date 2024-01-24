/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.AnagraficaManager;
import it.csi.epay.epayservices.integration.db.manager.ErroriManager;
import it.csi.epay.epayservices.integration.db.manager.MarcaBolloManager;
import it.csi.epay.epayservices.integration.db.manager.RegistroVersamentiManager;
import it.csi.epay.epayservices.integration.db.manager.RtManager;
import it.csi.epay.epayservices.integration.db.manager.TransazioneMdpManager;
import it.csi.epay.epayservices.interfaces.ejb.EpayMdpServicesFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AggiornamentoDatiMarcaDaBollo;
import it.csi.epay.epayservices.model.Errori;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.TaglioMarca;
import it.csi.epay.epayservices.model.TransazioneMdp;


@Stateless ( name = "EpayMdpServicesFacade", mappedName = "EpayMdpServices" )
public class EpayMdpServicesBean extends _BaseBean implements EpayMdpServicesFacade {

    @EJB
    private RtManager rtManager;

    @EJB
    private AnagraficaManager anagraficaManager;

    @EJB
    private ErroriManager erroriManager;

    @EJB
    private TransazioneMdpManager transazioneMdpManager;

    @EJB
    private RegistroVersamentiManager registroVersamentiManager;

    @EJB
    private MarcaBolloManager marcaBolloManager;

    @Override
    public Long inserisciRt ( Rt rt ) {
        return rtManager.inserisci ( rt );
    }

    @Override
    public void inserisciErrore ( Errori errore ) {
        erroriManager.inserisci ( errore );
    }

    @Override
    public TransazioneMdp ricercaTransazione ( TransazioneMdp parametriRicerca ) throws NoDataException {
        return transazioneMdpManager.ricerca ( parametriRicerca.getIdTransazione () );
    }

    @Override
    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento ( String iuv, String idTransazione ) throws NoDataException {
        return registroVersamentiManager.ricercaUltimoRegistro ( iuv, idTransazione );
    }

    @Override
    public void aggiornaTransazione ( TransazioneMdp transazione ) {
        transazioneMdpManager.aggiorna ( transazione );
    }

    @Override
    public void aggiornaMarcaDaBollo ( AggiornamentoDatiMarcaDaBollo marca ) throws NoDataException {
        marcaBolloManager.aggiornaDatiMarca ( marca );
    }

    @Override
    public MarcaDigitale ricercaMarcaDaBolloByIuv ( String iuv ) throws NoDataException {
        return marcaBolloManager.ricercaMarcaDaBolloByIuv ( iuv );
        
    }

    @Override
    public TaglioMarca ricercaTaglioMarcaByImporto ( BigDecimal importo ) throws NoDataException {
        return marcaBolloManager.ricercaTaglioMarcaByImporto ( importo );
    }
    
    
}
