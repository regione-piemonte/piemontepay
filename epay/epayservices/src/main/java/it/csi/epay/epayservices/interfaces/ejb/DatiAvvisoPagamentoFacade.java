/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import javax.ejb.Remote;

import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;


@Remote
public interface DatiAvvisoPagamentoFacade {

    /**
     * Cerca altre info per il pagamento in base al id tipo di pagamento da effettuare
     */
    DatiAvvisoPagamento ricerca ( Long idTipoPagamento ) throws NoDataException, IllegalArgumentException;

    DatiPDFReport getDatiPDFReport ( String codice ) throws NoDataException;
    
    void aggiornaTemplateCompilato(final Long id, final byte[] templateCompilato) throws NoDataException;
}
