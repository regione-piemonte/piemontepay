/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegato;
import it.csi.epay.epayservices.integration.db.entities.EpayRTipoPagamentoCollegatoKey;
import it.csi.epay.epayservices.integration.db.entities.EpayTEnti;
import it.csi.epay.epayservices.integration.db.entities.EpayTTipoPagamento;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.Ente;
import it.csi.epay.epayservices.model.TipoPagamento;
import it.csi.epay.epayservices.model.TipoPagamentoLight;
import it.csi.epay.epayservices.model.TipoPagamentoPassphrase;
import it.csi.epay.epayservices.utilities.EncryptionDecryptionUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;


@Stateless ( name = "TipoPagamentoManager", mappedName = "TipoPagamentoManager" )
public class TipoPagamentoManager extends _Manager {
    
    private static final String PROP_PASSWORD_PASSPHRASE = "password.passphrase";

    private static final String INVIO_NOTIFY_PROPERTIES = "META-INF/invioNotify.properties";

    private static final String COD_ENTE_DEFALUT = "PagoPa";

    private static final String CONFIG_MAX_MESI_INVIO_NOTIFY = "CONFIG_MAX_MESI_INVIO_NOTIFY";


    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    private ConfigurazioneManager configurazioneManager;


    public TipoPagamento getById ( Long id ) {
        EpayTTipoPagamento tTipoPagamento = entityManager.find ( EpayTTipoPagamento.class, id );
		return map ( tTipoPagamento, TipoPagamento.class );
    }

	public TipoPagamento getByIdPagamento ( Long idPagamento ) {
		TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.getByIdPagamento", EpayTTipoPagamento.class );
		query.setParameter ( "idPagamento", idPagamento );
		return map ( query.getSingleResult (), TipoPagamento.class );
	}

    public List<TipoPagamentoLight> getListaByEnte ( Ente ente ) {
        TypedQuery<EpayTTipoPagamento> query
        = entityManager.createNamedQuery ( "EpayTTipoPagamento.findPagamentiSpontaneiAttiviByIdEnte", EpayTTipoPagamento.class );
        query.setParameter ( "idEnte", ente.getIdEnte () );
        List<EpayTTipoPagamento> tTipiPagamento = query.getResultList ();
		return mapList ( tTipiPagamento, TipoPagamentoLight.class );
    }
    
    public List<TipoPagamentoLight> getListaVisibiliByEnte ( Ente ente ) {
        TypedQuery<EpayTTipoPagamento> query
        = entityManager.createNamedQuery ( "EpayTTipoPagamento.findPagamentiVisibiliSpontaneiAttiviByIdEnte", EpayTTipoPagamento.class );
        query.setParameter ( "idEnte", ente.getIdEnte () );
        List<EpayTTipoPagamento> tTipiPagamento = query.getResultList ();
        return mapList ( tTipiPagamento, TipoPagamentoLight.class );
    }
    
    

    public List<TipoPagamento> getListaPerInvioPagamenti ( Ente ente ) throws NoDataException {
        try {
            TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.elencoPerInvioPagamenti", EpayTTipoPagamento.class );
            query.setParameter ( "idEnte", ente.getIdEnte () );
            List<EpayTTipoPagamento> tTipiPagamento = query.getResultList ();
			return mapList ( tTipiPagamento, TipoPagamento.class );
        } catch ( NoResultException nre ) {
            throw new NoDataException ( "Nessun tipo pagamento da inviare", nre.getCause () );
        }
    }

    public List<TipoPagamento> getByEnteECodiceVersamento ( Ente ente, String codiceVersamento ) {
        TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.findByIdEnteECodiceVersamento", EpayTTipoPagamento.class );
        query.setParameter ( "idEnte", ente.getIdEnte () );
        query.setParameter ( "codiceVersamento", codiceVersamento );
        List<EpayTTipoPagamento> tTipiPagamento = query.getResultList ();
		return mapList ( tTipiPagamento, TipoPagamento.class );
    }

    public List<TipoPagamento> getByEnteMarca ( Ente ente ) {
        TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.findByIdEnteMarca", EpayTTipoPagamento.class );
        query.setParameter ( "idEnte", ente.getIdEnte () );
        List<EpayTTipoPagamento> tTipiPagamento = query.getResultList ();
		return mapList ( tTipiPagamento, TipoPagamento.class );
    }

    public void inserisci ( TipoPagamento enteDTO ) {
        EpayTTipoPagamento tEnte = map ( enteDTO, EpayTTipoPagamento.class );
        entityManager.persist ( tEnte );
    }

    public void aggiorna ( TipoPagamento enteDTO ) {
        EpayTTipoPagamento tEnte = entityManager.find ( EpayTTipoPagamento.class, enteDTO.getIdTipoPagamento () );
        tEnte.setPassphrase ( null );
        tEnte.setCredenzialiPnd ( null );
        EpayTEnti enteOriginale = tEnte.getEpayTEnti ();

        TipoPagamento inputClonato = new TipoPagamento ();
        map ( enteDTO, inputClonato );
        inputClonato.setEpayTEnti ( null );
        
        map ( inputClonato, tEnte );
        tEnte.setEpayTEnti ( enteOriginale );
    }

    public void deleteById ( Long id ) {
        EpayTTipoPagamento entity = entityManager.find ( EpayTTipoPagamento.class, id );
        entityManager.remove ( entity );
    }

    //CSI_PAG-1888 INIZIO
    public List<EpayRTipoPagamentoCollegato> getPagamentoSecondarioByidPagamentoPrincipale ( TipoPagamento tipoPagamentoPrincipale ) {
        TypedQuery<EpayRTipoPagamentoCollegato> query
        = entityManager.createNamedQuery ( "EpayRTipoPagamentoCollegato.findByIdTipoPagamentoPrincipale", EpayRTipoPagamentoCollegato.class );
        query.setParameter ( "idTipoPagamentoPrincipale", tipoPagamentoPrincipale.getIdTipoPagamento () );
		return query.getResultList ();
    }

    //CSI_PAG-1888 FINE
    
    public Long countByIdPagamentoSecondario ( TipoPagamento tipoPagamentoSecondario ) {
        TypedQuery<Long> query
        = entityManager.createNamedQuery ( "EpayRTipoPagamentoCollegato.countByIdPagamentoSecondario", Long.class );
        query.setParameter ( "idTipoPagamentoSecondario", tipoPagamentoSecondario.getIdTipoPagamento () );
        return  query.getSingleResult ();
    }
    
    public void inserisciAssociazioneTipoPagamentoSecondario ( Long idTipoPagamentoPrimario, Long idTipoPagamentoSecondario )  {
        EpayRTipoPagamentoCollegato entity = new EpayRTipoPagamentoCollegato ();
        entity.setIdTipoPagamentoPrincipale  ( idTipoPagamentoPrimario );
        entity.setIdTipoPagamentoSecondario  ( idTipoPagamentoSecondario );
        entityManager.persist ( entity );
        entityManager.flush ();
    }
    
    public void deleteAssociazioneTipoPagamentoSecondario ( Long idTipoPagamentoPrimario, Long idTipoPagamentoSecondario ) {
        
        EpayRTipoPagamentoCollegatoKey kwy= new EpayRTipoPagamentoCollegatoKey();
        kwy.setIdTipoPagamentoPrincipale ( idTipoPagamentoPrimario );
        kwy.setIdTipoPagamentoSecondario ( idTipoPagamentoSecondario );
        EpayRTipoPagamentoCollegato entity = entityManager.find ( EpayRTipoPagamentoCollegato.class, kwy );
        entityManager.remove ( entity );
    }
    
    public void deleteAssociazioneTipoPagamentoSecondario ( EpayRTipoPagamentoCollegato entity ) {
        entityManager.remove ( entity );
    }
    
    public List<EpayRTipoPagamentoCollegato> getAssociazionePagamentoCollegatoByIdPagamentoSecondario ( Long idPagamentoSecondario ) {
        TypedQuery<EpayRTipoPagamentoCollegato> query
        = entityManager.createNamedQuery ( "EpayRTipoPagamentoCollegato.findByIdTipoPagamentoSecondario", EpayRTipoPagamentoCollegato.class );
        query.setParameter ( "tipoPagamentoSecondario", idPagamentoSecondario );
		return query.getResultList ();
    }
    
    public Boolean existsPagamentiByTipoPagamento ( Long idTipoPagamento ) {
        try {
            TypedQuery<Integer> query = entityManager.createNamedQuery ( "EpayTPagamento.existsPagamentiByTipoPagamento", Integer.class );
            query.setParameter ( "idTipoPagamento", idTipoPagamento );
            return query.getSingleResult () != null && query.getSingleResult ().compareTo ( 0 ) > 0;
        } catch ( NonUniqueResultException e ) {
            return true;
        } catch ( NoResultException e ) {
            return false;
        }
    }
    
    public List<TipoPagamentoPassphrase> getListaTipiPagamentoPerInvioNotificheTipizzatePerEnte (Long idEnte) throws NoDataException {
        try {
            TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.elencoPerInvioNotificheTipizzatePerEnte", EpayTTipoPagamento.class );
//            query.setParameter ( "passwordPp", getPasswordPassphrase () );
            query.setParameter ( "limiteData", estraiLimiteDataPerNotify () );
            query.setParameter ( "idEnte", idEnte );

            List<EpayTTipoPagamento> tTipoPagamento = query.getResultList ();
            List<TipoPagamentoPassphrase> tipiPagamentoDaConsiderare= new LinkedList <TipoPagamentoPassphrase> ();
            for ( EpayTTipoPagamento tp : tTipoPagamento ) {
                TipoPagamentoPassphrase tpp= TipoPagamentoPassphrase.builder ().withIdTipoPagamento  ( tp.getIdTipoPagamento  () ).withPassphrase ( tp.getPassphrase () ).build () ;
                String pp=    EncryptionDecryptionUtil.decrypt ( new String ( tpp.getPassphrase ())  , getPasswordPassphrase () );
                tpp.setPassphrase ( pp.getBytes () );
                tipiPagamentoDaConsiderare.add (tpp);
            }
            return tipiPagamentoDaConsiderare;
        } catch ( NoResultException nre ) {
            throw new NoDataException ( "Nessun tipo pagamento da inviare", nre.getCause () );
        } catch ( IOException e ) {
            throw new NoDataException ( "Errore in fase di reperimento della passphrase", e.getCause () );
        }
    }
    
    public List<TipoPagamentoPassphrase> getListaTipiPagamentoPerInvioNotificheTipizzateCittaFacilePerEnte (Long idEnte) throws NoDataException {
        try {
            TypedQuery<EpayTTipoPagamento> query = entityManager.createNamedQuery ( "EpayTTipoPagamento.elencoPerInvioNotificheTipizzateCittaFacilePerEnte", EpayTTipoPagamento.class );
//            query.setParameter ( "passwordPp", getPasswordPassphrase () );
            query.setParameter ( "limiteData", estraiLimiteDataPerNotify () );
            query.setParameter ( "idEnte", idEnte );

            List<EpayTTipoPagamento> tTipoPagamento = query.getResultList ();
            List<TipoPagamentoPassphrase> tipiPagamentoDaConsiderare= new LinkedList<> ();
            for ( EpayTTipoPagamento tp : tTipoPagamento ) {
                
                TipoPagamentoPassphrase tpp= TipoPagamentoPassphrase.builder ().withIdTipoPagamento  ( tp.getIdTipoPagamento  () ).withPassphrase ( tp.getPassphrase () ).build () ;
                String pp=    EncryptionDecryptionUtil.decrypt ( new String ( tpp.getPassphrase ())  , getPasswordPassphrase () );
                tpp.setPassphrase ( pp.getBytes () );
                tipiPagamentoDaConsiderare.add (tpp);
            }
            return tipiPagamentoDaConsiderare;
        } catch ( NoResultException nre ) {
            throw new NoDataException ( "Nessun tipo pagamento da inviare", nre.getCause () );
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
   
    
}
