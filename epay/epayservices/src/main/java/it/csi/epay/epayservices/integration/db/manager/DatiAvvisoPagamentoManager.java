/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTDatiAvvisoPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTPdfReport;
import it.csi.epay.epayservices.model.DatiAvvisoPagamento;
import it.csi.epay.epayservices.model.DatiPDFReport;
import it.csi.epay.epayservices.model.TipoPagamento;


@Stateless ( name = "DatiAvvisoPagamentoManager", mappedName = "DatiAvvisoPagamentoManager" )
public class DatiAvvisoPagamentoManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public DatiAvvisoPagamento getDatiAvvisoPagamento ( Long idTipoPagamento ) {
        try {
            TypedQuery<EpayTDatiAvvisoPagamento> query
                = entityManager.createNamedQuery ( "EpayTDatiAvvisoPagamento.findByCodiceVersamento", EpayTDatiAvvisoPagamento.class );
            query.setParameter ( "idTipoPagamento", idTipoPagamento );
            EpayTDatiAvvisoPagamento tDatiAvvisoPagamento = query.getSingleResult ();
            DatiAvvisoPagamento datiAvvisoPagamento = mappaDatiAvvisiPagamento ( tDatiAvvisoPagamento );
            return datiAvvisoPagamento;
        } catch ( NoResultException e ) {
            return null;
        }
    }

    public DatiPDFReport getDatiPDFReport ( String codice ) {
        try {
            TypedQuery<EpayTPdfReport> query
                = entityManager.createNamedQuery ( "EpayTPdfReport.findByCodice", EpayTPdfReport.class );
            query.setParameter ( "codice", codice );
            EpayTPdfReport tDatiPdf = query.getSingleResult ();
            return map ( tDatiPdf, DatiPDFReport.class );
        } catch ( NoResultException e ) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void aggiornaTemplateCompilato(final Long id, final byte[] templateCompilato) {
        EpayTPdfReport pdfReport = entityManager.find(EpayTPdfReport.class, id);
        pdfReport.setTemplateCompilato ( templateCompilato);
    }
    
    private DatiAvvisoPagamento mappaDatiAvvisiPagamento ( EpayTDatiAvvisoPagamento tDatiAvvisoPagamento ) {
        DatiAvvisoPagamento datiAvvisoPagamento = map ( tDatiAvvisoPagamento, DatiAvvisoPagamento.class );
        datiAvvisoPagamento.setTipoPagamento ( map ( tDatiAvvisoPagamento.getEpayTTipoPagamento (), TipoPagamento.class ) );
        datiAvvisoPagamento.setSettore ( tDatiAvvisoPagamento.getSettore () );
        datiAvvisoPagamento.setIndirizzo ( tDatiAvvisoPagamento.getIndirizzo () );
        datiAvvisoPagamento.setLocalita ( tDatiAvvisoPagamento.getLocalita () );
        datiAvvisoPagamento.setCap ( tDatiAvvisoPagamento.getCap () );
        datiAvvisoPagamento.setSiglaProvincia ( tDatiAvvisoPagamento.getSiglaProvincia () );
        datiAvvisoPagamento.setEmail ( tDatiAvvisoPagamento.getEmail () );
        datiAvvisoPagamento.setInfoEnte ( tDatiAvvisoPagamento.getInfoEnte () );
        datiAvvisoPagamento.setIntestatarioCCPostale ( tDatiAvvisoPagamento.getIntestatarioCCPostale () );
        datiAvvisoPagamento.setNumeroCCPostale ( tDatiAvvisoPagamento.getNumeroCCPostale () );
        datiAvvisoPagamento.setAutorizzazioneDaPosteIt ( tDatiAvvisoPagamento.getAutorizzazioneDaPosteIt () );
        return datiAvvisoPagamento;
    }
}
