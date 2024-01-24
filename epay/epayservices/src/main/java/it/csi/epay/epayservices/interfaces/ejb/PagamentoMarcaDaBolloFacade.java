/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ChiamanteEsterno;
import it.csi.epay.epayservices.model.ChiamataEsternaSincronaNonValida;
import it.csi.epay.epayservices.model.PagamentoMarcaBolloChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;
import it.csi.epay.epayservices.model.TracciabilitaChiamanteEsternoSincrono;


@Remote
public interface PagamentoMarcaDaBolloFacade {

    /**
     * Effettua il pagamento di una marca da bollo con o senza istanza collegata
     * 
     * @param input i dati di identificazione del pagamento e marca da bollo
     * @return l'url presso il quale effettuare il pagamento
     * 
     */
    public PagamentoMarcaBolloChiamanteEsternoOutput pagamentoMarcaDaBollo ( PagamentoMarcaDaBolloChiamanteEsternoInput input );

    public Boolean verificaAurizzazioneChiamanteEsternoSincrono ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona )
                    throws NoDataException, IllegalArgumentException;

    public Long inserisci ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona ) throws IllegalArgumentException;

    public TracciabilitaChiamanteEsternoSincrono ricerca ( String idTransazione ) throws NoDataException, IllegalAccessException;

    public Long ricercaIdentificativoPagamento ( String identificativoPagamento ) throws NoDataException, IllegalAccessException;

    public Long aggiorna ( TracciabilitaChiamanteEsternoSincrono chiamataEsternaSincrona ) throws NoDataException, IllegalAccessException;

    public ChiamanteEsterno recuperaChiamanteEsterno ( String codiceChiamante ) throws NoDataException, IllegalAccessException;

    public Long inserisciChiamataEsternaSincronaNonValida ( ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida ) throws IllegalArgumentException;

    public boolean validaAutorizzazioneChiamanteTipoPagamento ( String codiceChiamante, Long idTipoPagamento );

    public ChiamataEsternaSincronaNonValida ricercaChiamataEsternaSincronaNonValida ( ChiamataEsternaSincronaNonValida chiamataEsternaSincronaNonValida )
                    throws NoDataException, IllegalAccessException;
}
