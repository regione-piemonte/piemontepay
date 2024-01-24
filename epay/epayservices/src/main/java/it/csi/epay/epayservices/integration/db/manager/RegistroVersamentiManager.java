/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.*;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.RegistroVersamenti;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TransazioneMdp;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Stateless(name="PagamentoRisultatoMenager", mappedName = "PagamentoRisultatoMenager")
public class RegistroVersamentiManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private PagamentoManager pagamentoManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long inserisci(RegistroVersamenti registroVersamenti) {
        if (registroVersamenti == null) {
            throw new IllegalArgumentException("Argomento della funzione nullo!");
        }
        if (registroVersamenti.getIdPagamento() == null) {
            throw new IllegalArgumentException("L'id del pagamento non puo' essere nullo!");
        }
        EpayTRegistroVersamenti tRegistroVersamenti = new EpayTRegistroVersamenti();
        tRegistroVersamenti.setIuv(registroVersamenti.getIuv());
        tRegistroVersamenti.setEpayTPagamento(entityManager.find(EpayTPagamento.class, registroVersamenti.getIdPagamento()));
        tRegistroVersamenti.setDataOperazione(getTimestamp());
        if (registroVersamenti.getAnagraficaVersante() != null) {
            tRegistroVersamenti.setEpayTAnagrafica(entityManager.find(EpayTAnagrafica.class, registroVersamenti.getAnagraficaVersante().getIdAnagrafica()));
        }
        if (registroVersamenti.getIdTransazione() != null) {
            tRegistroVersamenti.setEpayTTransazioneMdp(entityManager.find(EpayTTransazioneMdp.class, registroVersamenti.getIdTransazione()));
        }
        tRegistroVersamenti.setEpayDStatoPagamento(entityManager.find(EpayDStatoPagamento.class, registroVersamenti.getIdStato()));
        tRegistroVersamenti.setDescEsitoPagamento(registroVersamenti.getRisultato());
        tRegistroVersamenti.setOrigineInserimento(registroVersamenti.getOrigineInserimento() == null ? "sconosciuta" : registroVersamenti.getOrigineInserimento());

        if (registroVersamenti.getEsitoRicevuto() != null) {
            List<EpayTEsitiRicevuti> elenco = new ArrayList<>(1);
            EpayTEsitiRicevuti elem = new EpayTEsitiRicevuti();
            elem.setCodEsitoPagamento(registroVersamenti.getEsitoRicevuto().getCodEsitoPagamento());
            elem.setDataPagamento(registroVersamenti.getEsitoRicevuto().getDataPagamento());
            elem.setDescEsitoPagamento(registroVersamenti.getEsitoRicevuto().getDescEsitoPagamento());
            elem.setEpayTRegistroVersamenti(tRegistroVersamenti);
            elem.setIdApplicazione(registroVersamenti.getEsitoRicevuto().getIdApplicazione());
            elem.setIdentificativoPsp(registroVersamenti.getEsitoRicevuto().getIdentificativoPsp());
            elem.setIdModalitaRicezione(registroVersamenti.getEsitoRicevuto().getIdModalitaRicezione());//4
            elem.setIdTransazione(registroVersamenti.getEsitoRicevuto().getIdTransazione());
            elem.setImporto(registroVersamenti.getEsitoRicevuto().getImporto());
            elem.setRagioneSocialePsp(registroVersamenti.getEsitoRicevuto().getRagioneSocialePsp());
            elem.setIuv ( registroVersamenti.getIuv () );
            //FIX Salvataggio IUR per EPAY-55
            elem.setIur ( registroVersamenti.getEsitoRicevuto ().getIur () );
            elem.setDataOraOperazione ( registroVersamenti.getEsitoRicevuto ().getDataOraOperazione () );
            elenco.add(elem);
            tRegistroVersamenti.setEpayTEsitiRicevutis(elenco);
        }
        if ( registroVersamenti.getIdPagamentoSecondario () != null ) {
            tRegistroVersamenti
            .setEpayTPagamentoSecondario ( entityManager.find ( EpayTPagamentoSecondario.class, registroVersamenti.getIdPagamentoSecondario () ) );
        }

        entityManager.persist(tRegistroVersamenti);
        pagamentoManager.aggiornaStato(registroVersamenti.getIdPagamento(), registroVersamenti.getIdStato());
        entityManager.flush();
        return tRegistroVersamenti.getIdRegistro();
    }

    public RegistroVersamenti ricercaUltimaRegistrazioneByIdStato(Long idPagamento, Integer idStato ) throws NoDataException {
        TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery("EpayTRegistroVersamenti.ricercaUltimoByIdPagamentoEIdStato", EpayTRegistroVersamenti.class);
        query.setParameter("idPagamento", idPagamento);
        query.setParameter("idStato", idStato);
		return mappaRegistro(query);
    }

    public RegistroVersamenti ricercaUltimoRegistro(String iuv, String idTransazione) throws NoDataException {
        TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery("EpayTRegistroVersamenti.ricercaUltimoByIuvAndIdTransazione", EpayTRegistroVersamenti.class);
        query.setParameter("iuv", iuv);
        query.setParameter("idTransazione", idTransazione);
		return mappaRegistro(query);
    }

    public RegistroVersamenti ricercaUltimoRegistroByIUV (String iuv) throws NoDataException {
        TypedQuery<EpayTRegistroVersamenti> query
        = entityManager.createNamedQuery ( "EpayTRegistroVersamenti.ricercaUltimoByIUV", EpayTRegistroVersamenti.class );
        query.setParameter ( "iuv", iuv );
		return mappaRegistro ( query );
    }

    public RegistroVersamenti ricercaUltimoByIdPagamentoAndIdPagamentoSecondario (Long idPagamento, Long idPagamentoSecondario) throws NoDataException {
        TypedQuery<EpayTRegistroVersamenti> query
        = entityManager.createNamedQuery ( "EpayTRegistroVersamenti.ricercaUltimoByIdPagamentoAndIdPagamentoSecondario", EpayTRegistroVersamenti.class );
        query.setParameter ( "idPagamento", idPagamento );
        query.setParameter ( "idPagamentoSecondario", idPagamentoSecondario );
		return mappaRegistro ( query );
	}

	public boolean verificaOrigineChiamata ( Long idPagamento, String idTransazione, Integer idOrigineChiamata ) {
		TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery ( "EpayTRegistroVersamenti.verificaOrigineChiamata", EpayTRegistroVersamenti.class );
		query.setParameter ( "idPagamento", idPagamento );
		query.setParameter ( "idTransazione", idTransazione );
		query.setParameter ( "idOrigineChiamata", idOrigineChiamata );
		try {
			mappaRegistro ( query );

		} catch ( NoDataException e ) {
			return false;
		}
		return true;
	}
	
	public RegistroVersamenti ricercaUltimoByIdPagamentoPrimario ( Long idPagamento ) throws NoDataException {
		TypedQuery<EpayTRegistroVersamenti> query
			= entityManager.createNamedQuery ( "EpayTRegistroVersamenti.ricercaUltimoByIdPagamentoPrimario", EpayTRegistroVersamenti.class );
		query.setParameter ( "idPagamento", idPagamento );
		return mappaRegistro ( query );
	}

    public RegistroVersamenti ricercaByIdPagamentoInAttesaSecondaRT ( Long idPagamento ) throws NoDataException {
        RegistroVersamenti registroVersamenti = null;
        try {
            TypedQuery<EpayTRegistroVersamenti> query
            = entityManager.createNamedQuery ( "EpayTRegistroVersamenti.ricercaByIdPagamentoInAttesaSecondaRT", EpayTRegistroVersamenti.class );
            query.setParameter ( "idPagamento", idPagamento );
            registroVersamenti = mappaRegistro ( query );
        } catch ( NoDataException e ) {
            // non esistono stati di in attesa di seconda RT - non e' un errore
        }
        return registroVersamenti;
    }

	private RegistroVersamenti mappaRegistro ( TypedQuery<EpayTRegistroVersamenti> query ) throws NoDataException {
		try {
			EpayTRegistroVersamenti tRegistroVersamenti = query.setMaxResults ( 1 ).getSingleResult ();
			RegistroVersamenti registroVersamenti = map ( tRegistroVersamenti, RegistroVersamenti.class );
			registroVersamenti.setIdPagamento ( tRegistroVersamenti.getEpayTPagamento ().getIdPagamento () );
			registroVersamenti.setRisultato ( tRegistroVersamenti.getEpayDStatoPagamento ().getDescStato () );
			registroVersamenti.setIdStato ( tRegistroVersamenti.getEpayDStatoPagamento ().getIdStato () );
			// parte esiti ricevuti
			
			if (!CollectionUtils.isEmpty ( tRegistroVersamenti.getEpayTEsitiRicevutis () ))
			{
			    EpayTEsitiRicevuti epayTEsitiRicevuti = tRegistroVersamenti.getEpayTEsitiRicevutis ().get ( 0 );
			    EsitiRicevuti esitiRicevuti = new EsitiRicevuti ();
			    esitiRicevuti.setIdModalitaRicezione ( epayTEsitiRicevuti.getIdModalitaRicezione () );
			    if ( null!= epayTEsitiRicevuti.getEpayTQuietanzaEsito ())
			    {
			        esitiRicevuti.setIdQuietanzaEsito ( epayTEsitiRicevuti.getEpayTQuietanzaEsito ().getIdQuietanzaEsito () );
			    }
			    registroVersamenti.setEsitoRicevuto ( esitiRicevuti ); // fine parte esiti ricevuti
			}
			
			if ( tRegistroVersamenti.getEpayTAnagrafica () != null ) {
				registroVersamenti.setAnagraficaVersante ( map ( tRegistroVersamenti.getEpayTAnagrafica (), Anagrafica.class ) );
			}
			if ( tRegistroVersamenti.getEpayTTransazioneMdp () != null ) {
				registroVersamenti.setIdTransazione ( tRegistroVersamenti.getEpayTTransazioneMdp ().getIdTransazione () );
			}
			if ( tRegistroVersamenti.getEpayDOrigineChiamata () != null ) {
				registroVersamenti.setIdOrigineChiamata ( tRegistroVersamenti.getEpayDOrigineChiamata ().getId () );
				registroVersamenti.setCodiceOrigineChiamata ( tRegistroVersamenti.getEpayDOrigineChiamata ().getCodice () );
			}
			return registroVersamenti;
		} catch ( NoResultException e ) {
			throw new NoDataException ();
		}
	}

    public TransazioneMdp ricercaUltimaTransazioneMdp(Long idPagamento) throws NoDataException {
        TypedQuery<EpayTRegistroVersamenti> query = entityManager.createNamedQuery("EpayTRegistroVersamenti.ricercaUltimoByIdPagamento", EpayTRegistroVersamenti.class);
        query.setParameter("idPagamento", idPagamento);
        try {
            EpayTRegistroVersamenti tRegistroVersamenti = query.getSingleResult();
            EpayTTransazioneMdp tTransazioneMdp = tRegistroVersamenti.getEpayTTransazioneMdp();
			return map(tTransazioneMdp, TransazioneMdp.class);
        } catch (NoResultException e) {
            throw new NoDataException();
        }
    }

    public TransazioneMdp ricercaTransazioneMdp(String idTransazione) throws NoDataException {
        EpayTTransazioneMdp tTransazioneMdp = entityManager.find(EpayTTransazioneMdp.class, idTransazione);
        if (tTransazioneMdp == null) {
            throw new NoDataException();
        }
		return map(tTransazioneMdp, TransazioneMdp.class);
    }
}
