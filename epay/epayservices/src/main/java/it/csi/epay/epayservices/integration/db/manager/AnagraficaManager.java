/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTAnagrafica;
import it.csi.epay.epayservices.integration.db.entities.EpayTAnagraficaTemp;
import it.csi.epay.epayservices.integration.db.entities.EpayTPagamento;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Anagrafica;
import it.csi.epay.epayservices.model.AnagraficaTemp;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless(name="AnagraficaManager", mappedName = "AnagraficaManager")
public class AnagraficaManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Anagrafica getAnagrafica(Long id) {
		EpayTAnagrafica tAnagrafica = entityManager.find(EpayTAnagrafica.class, id);
		return map(tAnagrafica, Anagrafica.class);
	}

	public void aggiorna(Long idPagamento, Anagrafica anagrafica) {
		EpayTPagamento tPagamento = entityManager.find(EpayTPagamento.class, idPagamento);
		EpayTAnagrafica tAnagrafica = tPagamento.getEpayTAnagrafica();
		map(anagrafica, tAnagrafica);
	}


	
	 @TransactionAttribute ( TransactionAttributeType.REQUIRED )
	    public Anagrafica inserisci ( Anagrafica anagrafica ) {
	        EpayTAnagrafica tAnagrafica = new EpayTAnagrafica ();
	        if ( StringUtils.isBlank ( anagrafica.getCodiceFiscale () ) ) {
	            throw new IllegalArgumentException ( "Codice fiscale o ragione sociale non valorizzata!" );
	        }
	        tAnagrafica = map ( anagrafica, EpayTAnagrafica.class );
	        tAnagrafica.setCodiceFiscale ( StringUtils.upperCase ( StringUtils.trimToNull ( anagrafica.getCodiceFiscale () ) ) );
	        entityManager.persist ( tAnagrafica );
	        anagrafica.setIdAnagrafica ( tAnagrafica.getIdAnagrafica () );
	        return anagrafica;
	    }
	

	
	
	public AnagraficaTemp getAnagraficaTemp() throws NoDataException, IllegalArgumentException {
		EpayTAnagraficaTemp tAnagrafica = null;
		try {
			TypedQuery<EpayTAnagraficaTemp> query = entityManager.createNamedQuery("EpayTAnagraficaTemp.findAnagrafica", EpayTAnagraficaTemp.class);
			query.setMaxResults(1);
			tAnagrafica = query.getSingleResult();
			return map(tAnagrafica, AnagraficaTemp.class);
			
		} catch (NoResultException  error ) { 
			return null;
		} catch (IllegalArgumentException e) {
			throw new NoDataException ( "Errore Anagrafica: ", e );
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void aggiornaInserisciAnagraficaTemp(AnagraficaTemp inserisciAnagrafica) {
		

		EpayTAnagraficaTemp anagraficaTemp = entityManager.find(EpayTAnagraficaTemp.class, inserisciAnagrafica.getIdAnagrafica());
		if(anagraficaTemp ==null) {
			anagraficaTemp = new EpayTAnagraficaTemp();
			anagraficaTemp.setCodiceFiscale(inserisciAnagrafica.getCodiceFiscale());
		}else {
			anagraficaTemp.setCodiceFiscale(inserisciAnagrafica.getCodiceFiscale());
		}
		
	    entityManager.persist ( anagraficaTemp );
	    entityManager.flush ();
	}
	
}
