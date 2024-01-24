/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTEsitiRicevuti;
import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaEsito;
import it.csi.epay.epayservices.integration.db.entities.EpayTRegistroVersamenti;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.EsitiRicevuti;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name="EsitiRicevutiManager", mappedName = "EsitiRicevutiManager")
public class EsitiRicevutiManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    public  EsitiRicevuti ricercaEsitiRicevuti(Long id) throws NoDataException {
        //final String methodName = "ricercaEsitiRicevuti";
        try {
            EpayTEsitiRicevuti tEsitiRicevuti = entityManager.find(EpayTEsitiRicevuti.class, id);
			return map(tEsitiRicevuti, EsitiRicevuti.class);
        } catch (NoResultException e) {
            throw new NoDataException("EsitiRicevuti non trovati");
        }
    }

    public EsitiRicevuti ricercaEsitiRicevutiByIUV ( String iuv ) throws NoDataException {
        try {
            TypedQuery<EpayTEsitiRicevuti> query = entityManager.createNamedQuery ( "EpayTEsitiRicevuti.ricercaEsitiRicevutiByIuv", EpayTEsitiRicevuti.class );
            query.setParameter ( "iuv", iuv );
			query.setMaxResults(1);
            EpayTEsitiRicevuti tEsitiRicevuti = query.getSingleResult ();

            EsitiRicevuti er = map ( tEsitiRicevuti, EsitiRicevuti.class );
            if ( null != tEsitiRicevuti.getEpayTQuietanzaEsito () ) {
                er.setIdQuietanzaEsito ( tEsitiRicevuti.getEpayTQuietanzaEsito ().getIdQuietanzaEsito () );
                er.setRicevutaPdf ( tEsitiRicevuti.getEpayTQuietanzaEsito ().getRicevutaPdf () );
            }
            return er;
        } catch ( NoResultException e ) {
            throw new NoDataException ( "EsitiRicevuti non trovati" );
        }
    }

    public EsitiRicevuti ricercaEsitiRicevutiByIdRegistro(final Long idRegistro) throws NoDataException {
        try {
            TypedQuery<EpayTEsitiRicevuti> query = entityManager.createNamedQuery("EpayTRt.ricercaEsitiRicevutiByIdRegistro", EpayTEsitiRicevuti.class);
            query.setParameter("idRegistro", idRegistro);
            EpayTEsitiRicevuti tEsitiRicevuti = query.getSingleResult();
            return map(tEsitiRicevuti, EsitiRicevuti.class);
        } catch (NoResultException e) {
            throw new NoDataException("EsitiRicevuti non trovata (id registro = " + idRegistro + ")");
        }
    }

    public EsitiRicevuti ricercaEsitiRicevutiByIdPagamento(final Long idPagamento) throws NoDataException {
        try {

            TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery("EpayTRegistroVersamenti.ricercaUltimoByIdPagamento", EpayTRegistroVersamenti.class);
            query.setParameter("idPagamento", idPagamento);
            EpayTRegistroVersamenti tRegistroVersamenti = query.getSingleResult();
            if (tRegistroVersamenti.getEpayTEsitiRicevutis().isEmpty()) {
                throw new NoResultException();
            }
            return map(tRegistroVersamenti.getEpayTEsitiRicevutis().get(0), EsitiRicevuti.class);
        } catch (NoResultException e) {
            throw new NoDataException("EsitiRicevuti non trovato (id Pagamento = " + idPagamento + ")");
        }
    }

    public EsitiRicevuti ricercaEsitiRicevutiByIdPagamentoEStato(final Long idPagamento, final Integer idStato) throws NoDataException {
        try {

            TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery("EpayTRegistroVersamenti.ricercaUltimoByIdPagamentoEStato", EpayTRegistroVersamenti.class);
            query.setParameter("idPagamento", idPagamento);
            query.setParameter("idStato", idStato);
            EpayTRegistroVersamenti tRegistroVersamenti = query.getSingleResult();
            if (tRegistroVersamenti.getEpayTEsitiRicevutis().isEmpty()) {
                throw new NoResultException();
            }
            return map(tRegistroVersamenti.getEpayTEsitiRicevutis().get(0), EsitiRicevuti.class);
        } catch (NoResultException e) {
            throw new NoDataException("EsitiRicevuti non trovato (id Pagamento = " + idPagamento + ")");
        }
    }

    //MM
    public List<EsitiRicevuti> elencoEsitiSenzaQuietanza () throws NoDataException, NoResultException {
        try {

            TypedQuery<EpayTEsitiRicevuti> query = entityManager.createNamedQuery ( "EpayTEsitiRicevuti.ricercaEsitiSenzaQuietanza", EpayTEsitiRicevuti.class );

            query.setMaxResults ( 50 );
            List<EpayTEsitiRicevuti> tEsitiRicevuti = query.getResultList ();
            if ( tEsitiRicevuti.isEmpty () ) {
                throw new NoResultException ();
            }
            return mapList ( tEsitiRicevuti, EsitiRicevuti.class );
        } catch ( NoResultException e ) {
            throw new NoDataException ( "Nessun esito senza quietanza" );
        }
    }

    public Long aggiornaEsitoRicevuto ( EsitiRicevuti esito, Long idQuietanza ) {
        Long idEsito = esito.getIdEsito ();
        EpayTEsitiRicevuti tEsitiRicevuti = entityManager.find ( EpayTEsitiRicevuti.class, idEsito );
        map ( esito, tEsitiRicevuti );
        tEsitiRicevuti.setEpayTQuietanzaEsito ( ( entityManager.find ( EpayTQuietanzaEsito.class, idQuietanza ) ) );
        entityManager.flush ();
        return tEsitiRicevuti.getIdEsito ();
    }

    //MM
}
