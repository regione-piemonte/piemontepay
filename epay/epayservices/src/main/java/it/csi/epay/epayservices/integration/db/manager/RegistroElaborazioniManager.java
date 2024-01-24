/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayservices.integration.db.entities.EpayDModalitaRicezioneEsito;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.integration.db.entities.EpayTRegistroElaborazioni;
import it.csi.epay.epayservices.integration.db.entities.EpayTRegistroElaborazioniFault;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.RegistroElaborazioni;
import it.csi.epay.epayservices.model.RegistroElaborazioniFault;
import it.csi.epay.epayservices.utilities.LogUtil;

@Stateless(name="RegistroElaborazioniManager", mappedName = "RegistroElaborazioniManager")
public class RegistroElaborazioniManager extends _Manager {

    protected LogUtil log = new LogUtil ( this.getClass () );

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	PagamentoManager pagamentoManager;

	public  RegistroElaborazioni getRegistroElaborazioni(Long id) {
		//final String methodName = "getRegistroElaborazioni";

		EpayTRegistroElaborazioni tRegistroElaborazioni = entityManager.find(EpayTRegistroElaborazioni.class, id);
		RegistroElaborazioni registroElaborazioni = map(tRegistroElaborazioni, RegistroElaborazioni.class);
		return registroElaborazioni;
	}

	public List<RegistroElaborazioni> getRegistroElaborazioniByOperazioneEIdMessaggio(String operazione, String idMessaggio) throws NoDataException {
        final String methodName = "getRegistroElaborazioni";
        log.debugStart ( methodName );
        if ( log.isDebugEnabled () ) {
            log.debug ( methodName, "operazione = " + operazione );
            log.debug ( methodName, "idMessaggio = " + idMessaggio );
        }

		try {
            log.debug ( methodName, "verifica di accesso a tabella generica" );
            entityManager.find ( EpayDModalitaRicezioneEsito.class, -1 );
            log.debug ( methodName, "verifica di accesso a tabella generica completata" );

            log.debug ( methodName, "verifica di accesso alla tabella registro" );
            entityManager.find ( EpayTRegistroElaborazioni.class, -1L );
            log.debug ( methodName, "verifica di accesso alla tabella registro completata" );

            log.debug ( methodName, "lancio query di select" );
			TypedQuery<EpayTRegistroElaborazioni> query = entityManager.createNamedQuery("EpayTRegistroElaborazioni.ricercaByOperazioneEIdMessaggio", EpayTRegistroElaborazioni.class);
			query.setParameter("operazione", operazione);
			query.setParameter("idMessaggio", idMessaggio);

            log.debug ( methodName, "lancio fetch risultati della query" );
			List<EpayTRegistroElaborazioni> tRegistroElaborazioni = query.getResultList();

            log.debug ( methodName, "eseguo mapping dei risultati" );
			List<RegistroElaborazioni> registroElaborazioni = mapList(tRegistroElaborazioni, RegistroElaborazioni.class);
			return registroElaborazioni;
		} catch (NoResultException e) {
            log.error ( methodName, "Errore no-data nel reperimento del registro elaborazioni", e );
            throw new NoDataException ( "Nessun Registro Elaborazioni trovato per id messaggio = " + idMessaggio );
        } catch ( Throwable e ) {
            log.error ( methodName, "Errore generico nel reperimento del registro elaborazioni", e );
            throw e;
        } finally {
            log.debugEnd ( methodName );
		}
	}

	public Long inserisci(RegistroElaborazioni registroElaborazioni) {
		EpayTRegistroElaborazioni tRegistroElaborazioni = map(registroElaborazioni, EpayTRegistroElaborazioni.class);
		entityManager.persist(tRegistroElaborazioni);
		return tRegistroElaborazioni.getId();
	}

	public void aggiorna(RegistroElaborazioni registroElaborazioni) {
		aggiorna(registroElaborazioni, Boolean.FALSE);
	}

	public void aggiorna(RegistroElaborazioni registroElaborazioni, Boolean flagInviato) {
		Long id = registroElaborazioni.getId();
		EpayTRegistroElaborazioni tRegistroElaborazioni = entityManager.find(EpayTRegistroElaborazioni.class, id);
		map(registroElaborazioni, tRegistroElaborazioni);

		if (!registroElaborazioni.getPagamenti().isEmpty())	{
			if (tRegistroElaborazioni.getEpayTPagamentos() == null) {
				tRegistroElaborazioni.setEpayTPagamentos(new ArrayList<EpayTPagamento>());
			}
			for(Pagamento pagamento : registroElaborazioni.getPagamenti()) {
				EpayTPagamento tPagamento = pagamentoManager.inviato(pagamento, flagInviato);
				tRegistroElaborazioni.getEpayTPagamentos().add(tPagamento);
			}
		}

		if (!registroElaborazioni.getFaults().isEmpty())	{
			List<EpayTRegistroElaborazioniFault> tRegElabFaults = tRegistroElaborazioni.getEpayTRegistroElaborazioniFaults();
			if (tRegElabFaults == null) {
				tRegElabFaults = new ArrayList<>();
				tRegistroElaborazioni.setEpayTRegistroElaborazioniFaults(tRegElabFaults);
			}

			for(RegistroElaborazioniFault fault : registroElaborazioni.getFaults()) {

				EpayTRegistroElaborazioniFault tRegistroElaborazioniFault = new EpayTRegistroElaborazioniFault();
				tRegistroElaborazioniFault.setEpayTRegistroElaborazioni(tRegistroElaborazioni);
				tRegistroElaborazioniFault.setIdPagamento(fault.getIdPagamento());
				tRegistroElaborazioniFault.setCodicePagamentoRifEnte(fault.getCodicePagamentoRifEnte());
				tRegistroElaborazioniFault.setCodiceMessaggio(fault.getCodiceMessaggio());
				tRegistroElaborazioniFault.setDescrizioneMessaggio(StringUtils.substring ( fault.getDescrizioneMessaggio(), 0, 200 ));
				tRegElabFaults.add(tRegistroElaborazioniFault);
			}
		}

		entityManager.flush();
	}

}
