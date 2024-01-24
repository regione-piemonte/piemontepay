/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


public interface CodiceVersamentoRepository extends IRepository<CblTCodiceVersamento, Long> {

    List<CblTCodiceVersamento> findAllByIdEnteAndCodiceVersamento ( String idEnte, String codiceVersamento );

    List<CblTCodiceVersamento> findByIdEnte ( String idEnte );
    
    List<CblTCodiceVersamento> findByCodiceFiscaleEnteOrderByCodiceVersamentoAsc ( String codiceFiscaleEnte );

    List<CblTCodiceVersamento> findByCodiceVersamentoInAndCodiceFiscaleEnteOrderByCodiceVersamentoAsc ( List<String> codiciVersamento, String idEnte );

    CblTCodiceVersamento findOneByIdEnteAndCodiceVersamento ( String idEnte, String codiceVersamento );

    @Query ( "select e from CblTCodiceVersamento e where codiceFiscaleEnte = ?1 and codiceVersamento = ?2" )
    CblTCodiceVersamento findOneByCodiceFiscaleEnteAndCodiceVersamento ( String codiceFiscaleEnte,
        String codiceVersamento);
    
    @Query ( "select e from CblTCodiceVersamento e where codiceFiscaleEnte = ?1 and codiceVersamento = ?2 and dataInizioValidita <= ?3 and ( dataFineValidita >= ?4 or dataFineValidita is null )" )
    CblTCodiceVersamento findOneByCodiceFiscaleEnteAndCodiceVersamentoAndDataInizioValiditaAndDataFineValidita ( String codiceFiscaleEnte,
        String codiceVersamento, Timestamp dataInizioValidita, Timestamp dataFineValidita );
    
    @Query ( value="select * from cbl_t_codice_versamento as cv where codice_fiscale_ente = ?1 "
    		+ "and codice_versamento in ( select codice_versamento from cbl_t_flusso_sintesi as flussosin "
    		+ "where flussosin.codice_versamento  = cv.codice_versamento) order by codice_versamento asc", nativeQuery=true )
    List<CblTCodiceVersamento> findByCodiceFiscaleEnteAssociatiAFlussi (String codiceFiscaleEnte);
}
