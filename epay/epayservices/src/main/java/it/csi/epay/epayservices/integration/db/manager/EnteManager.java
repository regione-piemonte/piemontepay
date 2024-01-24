/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTEnti;
import it.csi.epay.epayservices.integration.db.entities.EpayTTipoPagamento;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.EntePassphrase;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoPassphrase;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


@Stateless ( name = "EnteManager", mappedName = "EnteManager" )
public class EnteManager extends _Manager {

	private static final String PROP_PASSWORD_PASSPHRASE = "password.passphrase";

	private static final String INVIO_NOTIFY_PROPERTIES = "META-INF/invioNotify.properties";

	private static final String COD_ENTE_DEFALUT = "PagoPa";

	private static final String CONFIG_MAX_MESI_INVIO_NOTIFY = "CONFIG_MAX_MESI_INVIO_NOTIFY";

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private ConfigurazioneManager configurazioneManager;

	public Ente getById ( Long id ) {
		EpayTEnti tEnte = entityManager.find ( EpayTEnti.class, id );
		return map ( tEnte, Ente.class );
	}

	public Ente getByCF ( String codiceFiscale ) throws NoDataException {
		try {
			TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.ricercaPerCodiceFiscale", EpayTEnti.class );
			query.setParameter ( "codiceFiscale", codiceFiscale );
			EpayTEnti tEnte = query.getSingleResult ();
			return map ( tEnte, Ente.class );
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun ente trovato", nre.getCause () );
		}
	}

	public Ente getByIdPagamentoSecondario ( Long idPagamentoSecondario ) throws NoDataException {
		try {
			TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.getByIdPagamentoSecondario", EpayTEnti.class );
			query.setParameter ( "idPagamentoSecondario", idPagamentoSecondario );
			EpayTEnti tEnte = query.getSingleResult ();
			return map ( tEnte, Ente.class );
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun ente trovato", nre.getCause () );
		}
	}

	public List<Ente> getListaEntiConTasse () {
		TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoConTasseAttive", EpayTEnti.class );
		List<EpayTEnti> tEnti = query.getResultList ();
		return mapList ( tEnti, Ente.class );
	}

	public List<Ente> getListaEntiConTasse ( String nomeEnte ) {
		TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoConTasseAttiveSenzaLogoEConNomeEnte", EpayTEnti.class );
		query.setParameter ( "nomeEnte", "%" + nomeEnte + "%" );
		List<EpayTEnti> tEnti = query.getResultList ();
		return mapList ( tEnti, Ente.class );
	}
	
	public List<Ente> getListaEntiConTasseVisibili ( String nomeEnte ) {
        TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoConTasseAttiveVisibiliSenzaLogoEConNomeEnte", EpayTEnti.class );
        query.setParameter ( "nomeEnte", "%" + nomeEnte + "%" );
        List<EpayTEnti> tEnti = query.getResultList ();
        return mapList ( tEnti, Ente.class );
    }

	public List<Ente> getListaEntiConPagamentiSpontanei () {
		TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoConTasseAttiveSenzaLogo", EpayTEnti.class );
		List<EpayTEnti> tEnti = query.getResultList ();
		return mapList ( tEnti, Ente.class );
	}
	
	

	public List<Ente> getListaEntiConPagamentiConIuvPagabili () {
		TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoConTasseAttiveConIuv", EpayTEnti.class );
		List<EpayTEnti> tEnti = query.getResultList ();
		return mapList ( tEnti, Ente.class );
	}

	public List<Ente> getListaEntiPerInvioRichiesteDiRevoca () throws NoDataException {
		try {
			TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.findAll", EpayTEnti.class );
			List<EpayTEnti> tEnti = query.getResultList ();
			return mapList ( tEnti, Ente.class );
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun ente da inviare", nre.getCause () );
		}
	}

	public List<Ente> getListaEntiPerInvioPagamenti () throws NoDataException {
		try {
			// FIX PER EPAY-188 | [EPAYJOB] Errore OutOfMemory sulle notifiche di pagamento per inserimento logo di grandi dimensioni
			// TypedQuery<EpayTEnti> query = entityManager.createNamedQuery("EpayTEnti.elencoPerInvioPagamenti", EpayTEnti.class);
			TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoPerInvioPagamentiSenzaLogo", EpayTEnti.class );

			List<EpayTEnti> tEnti = query.getResultList ();
			return mapList ( tEnti, Ente.class );
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun ente da inviare", nre.getCause () );
		}
	}

	public List<EntePassphrase> getListaEntiPerInvioNotifiche () throws NoDataException {
		try {
			TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoPerInvioNotifiche", EpayTEnti.class );
			query.setParameter ( "passwordPp", getPasswordPassphrase () );
			query.setParameter ( "limiteData", estraiLimiteDataPerNotify () );

			List<EpayTEnti> tEnti = query.getResultList ();
			List<EntePassphrase> entiDaConsiderare = new LinkedList<> ();
			for ( EpayTEnti ente : tEnti ) {
				entiDaConsiderare
								.add ( EntePassphrase.builder ().withIdEnte ( ente.getIdEnte () ).withPassphrase ( ente.getPassphrase () ).build () );
			}
			return entiDaConsiderare;
		} catch ( NoResultException nre ) {
			throw new NoDataException ( "Nessun ente da inviare", nre.getCause () );
		} catch ( IOException e ) {
			throw new NoDataException ( "Errore in fase di reperimento della passphrase", e.getCause () );
		}
	}

	private String getPasswordPassphrase () throws IOException {
		Properties properties = new Properties ();
		InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( INVIO_NOTIFY_PROPERTIES );
		properties.load ( inputStream );
		return properties.getProperty ( PROP_PASSWORD_PASSPHRASE );
	}

	public byte[] getLogo ( Long id ) {
		EpayTEnti tEnte = entityManager.find ( EpayTEnti.class, id );
		return tEnte.getLogo ();
	}

	public void inserisci ( Ente enteDTO ) {
		EpayTEnti tEnte = map ( enteDTO, EpayTEnti.class );

		tEnte.setLogo ( enteDTO.getLogo () );
		tEnte.setFlagInvioNotificatore ( Boolean.TRUE );

		entityManager.persist ( tEnte );
	}

	public void aggiorna ( Ente enteDTO ) {
		EpayTEnti tEnte = entityManager.find ( EpayTEnti.class, enteDTO.getIdEnte () );
		map ( enteDTO, tEnte );

		tEnte.setLogo ( enteDTO.getLogo () );

		entityManager.merge ( tEnte );
	}

	private Timestamp estraiLimiteDataPerNotify () {

		Configurazione config = configurazioneManager.getConfigurazione ( CONFIG_MAX_MESI_INVIO_NOTIFY, COD_ENTE_DEFALUT );
		int mesi = 0;
		if ( null != config && null != config.getValore () ) {
			mesi = Integer.parseInt ( config.getValore () );
		}
		LocalDate dt = LocalDate.now ();
		dt = dt.minusMonths ( mesi );
		return Timestamp.valueOf ( dt.atStartOfDay () );

	}
	
	
	public List<EntePassphrase> getListaEntiPerInvioNotificheTipizzate () throws NoDataException {
        try {
            TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoPerInvioNotificheTipizzate", EpayTEnti.class );
//            query.setParameter ( "passwordPp", getPasswordPassphrase () );
            query.setParameter ( "limiteData", estraiLimiteDataPerNotify () );

            List<EpayTEnti> tEnti = query.getResultList ();
            List<EntePassphrase> entiDaConsiderare = new LinkedList<> ();
            for ( EpayTEnti ente : tEnti ) {
                entiDaConsiderare
                                .add ( EntePassphrase.builder ().withIdEnte ( ente.getIdEnte () ).withPassphrase ( ente.getPassphrase () ).build () );
            }
            return entiDaConsiderare;
        } catch ( NoResultException nre ) {
            throw new NoDataException ( "Nessun ente da inviare", nre.getCause () );
        } 
//        catch ( IOException e ) {
//            throw new NoDataException ( "Errore in fase di reperimento della passphrase", e.getCause () );
//        }
    }
	
	
	public List<EntePassphrase> getListaEntiPerInvioNotificheTipizzateCittaFacile () throws NoDataException {
       
        try {
            TypedQuery<EpayTEnti> query = entityManager.createNamedQuery ( "EpayTEnti.elencoPerInvioNotificheTipizzateCittaFacile", EpayTEnti.class );
//            query.setParameter ( "passwordPp", getPasswordPassphrase () );
            query.setParameter ( "limiteData", estraiLimiteDataPerNotify () );
           


            List<EpayTEnti> tEnti = query.getResultList ();
            List<EntePassphrase> entiDaConsiderare = new LinkedList<> ();
            for ( EpayTEnti ente : tEnti ) {
                entiDaConsiderare
                                .add ( EntePassphrase.builder ().withIdEnte ( ente.getIdEnte () ).withPassphrase ( ente.getPassphrase () ).build () );
           
            }
            
            return entiDaConsiderare;
        } catch ( NoResultException nre ) {
            throw new NoDataException ( "Nessun ente da inviare", nre.getCause () );
        } 
//        catch ( IOException e ) {
//            throw new NoDataException ( "Errore in fase di reperimento della passphrase", e.getCause () );
//        }
    }
       
	
	


}
