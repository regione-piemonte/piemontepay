/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogo;

public interface CatalogoRepository extends JpaRepository<CblTCatalogo, Long> {

    List<CblTCatalogo> findByIdEnte ( String idEnte );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamento ( String idEnte, String codiceVersamento );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamentoAndAccertamentoAnnoAndAccertamentoNro (
        String idEnte, String codiceVersamento, Integer accertamentoAnno, Integer accertamentoNro );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamentoAndAnnoEsercizio(
        String idEnte, String codiceVersamento, Integer annoEsercizio
                    );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamentoAndAnnoEsercizioAndAccertamentoAnno(
        String idEnte, String codiceVersamento, Integer annoEsercizio, Integer accertamentoAnno
                    );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossione(
        String idEnte, String codiceVersamento, String datiSpecificiRiscossione
                    );

    List<CblTCatalogo> findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizio (
        String idEnte, String codiceVersamento, String datiSpecificiRiscossione, int year );

    CblTCatalogo findOneByChiaveIntersistema ( String chiaveIntersistema );

    CblTCatalogo findOneByIdEnteAndCodiceVersamento ( String idEnte, String codiceVersamento );

    CblTCatalogo findOneByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizio (
        String idEnte, String codiceVersamento, String datiSpecificiRiscossione, int year );

    @Query ( "SELECT catalogo FROM CblTCatalogo catalogo WHERE catalogo.idEnte = ?1 and catalogo.codiceVersamento = ?2 and catalogo.datiSpecificiRiscossione = ?3 and catalogo.annoEsercizio = ?4 and catalogo.dataInizioValidita <= ?5 and (catalogo.dataFineValidita is null or catalogo.dataFineValidita >= ?5) and (catalogo.flagAnnullato is null or catalogo.flagAnnullato = false)" )
    CblTCatalogo findOneAccertamento ( String idEnte, String codiceVersamento, String datiSpecificiRiscossione, Integer annoEsercizio,
        Date dataPagamento );
    
    @Query ( "SELECT catalogo FROM CblTCatalogo catalogo WHERE catalogo.idEnte = ?1 and catalogo.codiceVersamento = ?2 and catalogo.annoEsercizio = ?3 and catalogo.dataInizioValidita <= ?4 and (catalogo.dataFineValidita is null or catalogo.dataFineValidita >= ?4) and (catalogo.flagAnnullato is null or catalogo.flagAnnullato = false)" )
    List<CblTCatalogo> recuperaRiferimentoContabilePerAnnoCorrenteAttivo ( String idEnte, String codiceVersamento, Integer annoEsercizio, Date dataValidita );
    
    
    @Query ( "SELECT catalogo FROM CblTCatalogo catalogo WHERE catalogo.idEnte = ?1 and catalogo.datiSpecificiRiscossione = ?2 and catalogo.annoEsercizio = ?3 and catalogo.dataInizioValidita <= ?4 and (catalogo.dataFineValidita is null or catalogo.dataFineValidita >= ?4) and (catalogo.flagAnnullato is null or catalogo.flagAnnullato = false)" )
    List<CblTCatalogo> recuperaCodiceVersamentoByEnteAndDSRAndAnnoAndDataAndFlagAnnullato ( String idEnte, String datoSpecificoRiscossione, Integer annoEsercizio, Date dataValidita );
    
    
   }
