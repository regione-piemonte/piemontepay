/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.DatiAvvisoPagamentoManager;
import it.csi.epay.epayservices.interfaces.ejb.DatiAvvisoPagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;


@Stateless ( name = "DatiAvvisoPagamentoFacade", mappedName = "DatiAvvisoPagamento" )
public class DatiAvvisoPagamentoBean extends _BaseBean implements DatiAvvisoPagamentoFacade {

    @EJB
    private DatiAvvisoPagamentoManager datiAvvisoPagamentoManager;

    @Override
    public DatiAvvisoPagamento ricerca ( Long idTipoPagamento ) throws NoDataException, IllegalArgumentException {
        DatiAvvisoPagamento datiAvvisoPagamento = datiAvvisoPagamentoManager.getDatiAvvisoPagamento ( idTipoPagamento );
        return datiAvvisoPagamento;
    }

    @Override
    public DatiPDFReport getDatiPDFReport ( String codice ) throws NoDataException {
        return datiAvvisoPagamentoManager.getDatiPDFReport ( codice );
    }

    @Override
    public void aggiornaTemplateCompilato ( Long id, byte [] templateCompilato ) throws NoDataException {
        datiAvvisoPagamentoManager.aggiornaTemplateCompilato ( id, templateCompilato );
    }
}
