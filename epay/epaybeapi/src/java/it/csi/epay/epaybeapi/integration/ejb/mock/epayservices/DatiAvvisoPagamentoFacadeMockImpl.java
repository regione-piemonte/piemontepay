/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.interfaces.ejb.DatiAvvisoPagamentoFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;


@Service
public class DatiAvvisoPagamentoFacadeMockImpl implements DatiAvvisoPagamentoFacade{

	@Override
	public DatiAvvisoPagamento ricerca(Long idTipoPagamento) throws NoDataException, IllegalArgumentException {
		DatiAvvisoPagamento datiAvvisoPagamento = new DatiAvvisoPagamento();
        datiAvvisoPagamento.setSettore ( "settore Mock" );
        datiAvvisoPagamento.setIndirizzo ( "Indirizzo Mock" );
        datiAvvisoPagamento.setLocalita ( "Localita Mock" );
        datiAvvisoPagamento.setCap ( "Cap Mock" );
        datiAvvisoPagamento.setSiglaProvincia ( "Provincia Mock" );
		return datiAvvisoPagamento;
	}

    @Override
    public void aggiornaTemplateCompilato ( Long arg0, byte [] arg1 ) throws NoDataException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public DatiPDFReport getDatiPDFReport ( String arg0 ) throws NoDataException {
        // TODO Auto-generated method stub
        return null;
    }

}
