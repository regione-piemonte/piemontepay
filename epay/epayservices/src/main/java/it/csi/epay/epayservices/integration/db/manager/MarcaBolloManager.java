/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.integration.db.manager;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayRMarcaDoc;
import it.csi.epay.epayservices.integration.db.entities.EpayTMarcaDigitale;
import it.csi.epay.epayservices.integration.db.entities.EpayTTaglioMarca;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.AggiornamentoDatiMarcaDaBollo;
import it.csi.epay.epayservices.model.MarcaDigitale;
import it.csi.epay.epayservices.model.TaglioMarca;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.XmlUtil;


/**
 *
 */
@Stateless ( name = "MarcaBolloManager", mappedName = "MarcaBolloManager" )
public class MarcaBolloManager extends _Manager {

    @PersistenceContext
    private EntityManager entityManager;

    private static LogUtil log = new LogUtil ( MarcaBolloManager.class );

    /**
     * Recupera il taglio della marca a partire dal codice
     *
     * @param codiceMarca
     * @param importoBollo
     * @return
     * @throws NoDataException
     */
    public TaglioMarca recuperaLimitiPerCodiceMarca ( String codiceMarca, BigDecimal importoBollo ) throws NoDataException {
        try {
            TypedQuery<EpayTTaglioMarca> query = entityManager.createNamedQuery ( "EpayTTaglioMarca.findByCodiceAndImporto", EpayTTaglioMarca.class );
            query.setParameter ( "codiceMarca", codiceMarca );
            query.setParameter ( "importo", importoBollo );
            EpayTTaglioMarca taglioMarcaRow = query.getSingleResult ();
            TaglioMarca taglioMarca = map ( taglioMarcaRow, TaglioMarca.class );
            return taglioMarca;
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

    public MarcaDigitale salvaDatiMarca ( MarcaDigitale daSalvare ) throws NoDataException {
        EpayTMarcaDigitale entity = map ( daSalvare, EpayTMarcaDigitale.class );
        log.debug ( "salvaDatiMarca", XmlUtil.obj2Xml ( entity ) );
        entityManager.persist ( entity );
        entityManager.flush ();
        return map ( entity, MarcaDigitale.class );
    }

    public void salvaAssociazioneMarca ( String iuvMarca, String iuvDocumento ) throws NoDataException {
        EpayRMarcaDoc entity = new EpayRMarcaDoc ();
        entity.setIuvIstanza ( iuvDocumento );
        entity.setIuvMarca ( iuvMarca );
        entityManager.persist ( entity );
        entityManager.flush ();
    }

    public void aggiornaDatiMarca ( AggiornamentoDatiMarcaDaBollo daSalvare ) throws NoDataException {
        TypedQuery<EpayTMarcaDigitale> query = entityManager.createNamedQuery ( "EpayTMarcaDigitale.findByIuv", EpayTMarcaDigitale.class );
        query.setParameter ( "iuv", daSalvare.getIuv () );
        EpayTMarcaDigitale entity = query.getSingleResult ();
        entity.setIubdMarca ( daSalvare.getIubd () );
        entity.setXmlReceiptMb ( daSalvare.getXmlReceiptMb () );
        entityManager.persist ( entity );
        entityManager.flush ();
    }
    
    public MarcaDigitale ricercaMarcaDaBolloByIuv ( String iuv ) throws NoDataException {
        try {
        TypedQuery<EpayTMarcaDigitale> query = entityManager.createNamedQuery ( "EpayTMarcaDigitale.findByIuv", EpayTMarcaDigitale.class );
        query.setParameter ( "iuv", iuv );
        EpayTMarcaDigitale entity = query.getSingleResult ();
        return map ( entity, MarcaDigitale.class );
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }
    
    
    public TaglioMarca ricercaTaglioMarcaByImporto ( BigDecimal importoBollo ) throws NoDataException {
        try {
            TypedQuery<EpayTTaglioMarca> query = entityManager.createNamedQuery ( "EpayTTaglioMarca.findByImporto", EpayTTaglioMarca.class );
            query.setParameter ( "importo", importoBollo );
            EpayTTaglioMarca taglioMarcaRow = query.getSingleResult ();
            TaglioMarca taglioMarca = map ( taglioMarcaRow, TaglioMarca.class );
            return taglioMarca;
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }
    
    public MarcaDigitale ricercaMarcaDaBolloByByIuvAndIdPagamento ( String iuv, Long idPagamento ) throws NoDataException {
        try {
        TypedQuery<EpayTMarcaDigitale> query = entityManager.createNamedQuery ( "EpayTMarcaDigitale.findByIuvAndIdPagamento", EpayTMarcaDigitale.class );
        query.setParameter ( "iuv", iuv );
        query.setParameter ( "idPagamento", idPagamento );
        EpayTMarcaDigitale entity = query.getSingleResult ();
        return map ( entity, MarcaDigitale.class );
        } catch ( NoResultException e ) {
            throw new NoDataException ();
        }
    }

    

}
