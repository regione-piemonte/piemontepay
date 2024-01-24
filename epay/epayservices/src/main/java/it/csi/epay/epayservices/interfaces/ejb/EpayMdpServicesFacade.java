/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import java.math.BigDecimal;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AggiornamentoDatiMarcaDaBollo;
import it.csi.epay.epayservices.model.Errori;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.Rt;
import it.csi.epay.epayservices.model.TaglioMarca;
import it.csi.epay.epayservices.model.TransazioneMdp;


@Remote
public interface EpayMdpServicesFacade {

    public Long inserisciRt ( Rt rt );

    public void inserisciErrore ( Errori errore );

    public TransazioneMdp ricercaTransazione ( TransazioneMdp parametriRicerca ) throws NoDataException;

    public RegistroVersamenti ricercaUltimaRegistrazioneVersamento ( String iuv, String idTransazione ) throws NoDataException;

    public void aggiornaTransazione ( TransazioneMdp transazione );

    public void aggiornaMarcaDaBollo ( AggiornamentoDatiMarcaDaBollo marca ) throws NoDataException;
    
    public MarcaDigitale ricercaMarcaDaBolloByIuv ( String iuv ) throws NoDataException;
    
    public TaglioMarca ricercaTaglioMarcaByImporto ( BigDecimal importo ) throws NoDataException;
    
    
     

}
