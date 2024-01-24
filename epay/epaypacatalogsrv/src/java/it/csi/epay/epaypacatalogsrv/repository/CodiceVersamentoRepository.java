/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.custom.CodiceVersamentoRepositoryCustom;


public interface CodiceVersamentoRepository extends
JpaRepository<CodiceVersamento, Long>,
JpaSpecificationExecutor<CodiceVersamento>,
CodiceVersamentoRepositoryCustom {

    @Modifying
    @Query ( value = "DELETE FROM epaycat_r_ente_codice_versamento WHERE id_codice_versamento = ?1", nativeQuery = true )
    int deleteAssociazioniEnteCodiceVersamentoByCodiceVersamento ( Long idCodiceVersamento );

    List<CodiceVersamento> findByEnte_IdAndCodice ( Long idEnte, String codiceVoce );

    List<CodiceVersamento> findByEnte_Id ( Long idEnte, Sort sort );

    @Query ( "SELECT COUNT(c) FROM CodiceVersamento c WHERE c.id in ?1" )
    long countByIdIn ( List<Long> list );

    @Query ( "SELECT c FROM CodiceVersamento c WHERE c.voceEntrata.tematica.codice = ?1" )
    List<CodiceVersamento> findByCodiceTematica ( String codiceTematica, Sort sortClause );
    
    /**
     * Query implementata come nativa per ottimizzazione delle performance. Non modificare ordinamento. 
     * 
     * @param idEnte
     * @return
     */
    @Query ( value = "SELECT tp.id as tpid, tp.codice as tpcodice, tp.descrizione as tpdescrizione, cov.id as covid, cov.codice as covcodice, cov.descrizione as covdescrizione FROM epaycat_t_codice_versamento cov JOIN epaycat_t_voce_entrata vc ON (cov.codice_voce_entrata = vc.codice) JOIN epaycat_d_tematica_ppay tp ON (vc.codice_tematica_ppay = tp.codice) WHERE (cov.flag_annullato IS NULL or cov.flag_annullato = false) AND cov.id_ente = ?1 order by tp.id, tp.codice",
                    nativeQuery = true )
    List<Object []> codiciVersamentoPerEnte ( Long idEnte );

    @Query ( value = "SELECT codice FROM epaycat_t_codice_versamento WHERE " +
                    "(flag_annullato IS NULL or flag_annullato = false) AND id_padre IS NULL AND id_ente = ?1", nativeQuery = true )
    List<String> getCodiciUtilizzatiByIdEnte ( Long idEnte );

    List<CodiceVersamento> findByUtenteModifica ( String utenteModifica );

    @Query ( "SELECT c FROM CodiceVersamento c WHERE c.ente.id = ?1 AND c.voceEntrata.tematica.codice = ?2 AND c.codiceVersamentoPadre IS NULL AND (flagAnnullato IS NULL or flagAnnullato = false)" )
    List<CodiceVersamento> findByCodiceTematicaAndIdEnte ( Long idEnte, String codiceTematica );
    
    @Query ( "SELECT c FROM CodiceVersamento c WHERE c.ente.id = ?1 AND c.codice = ?2 AND (flagAnnullato IS NULL or flagAnnullato = false)" )
    CodiceVersamento findByCodiceAndIdEnte ( Long idEnte, String codice);
    
    CodiceVersamento findByIdAndEnte_Id (Long id, Long idEnte);

}
