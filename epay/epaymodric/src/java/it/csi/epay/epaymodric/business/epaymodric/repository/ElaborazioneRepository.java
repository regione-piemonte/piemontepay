/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


public interface ElaborazioneRepository extends IRepository<CblTElaborazione, Long>, JpaSpecificationExecutor<CblTElaborazione> {

    /**
     * necessario per recuperare record che non hanno il campo idErrore valorizzato hibernate crea la query con la join se utilizzato il findOne
     * 
     * @param id
     * @return
     */
    public CblTElaborazione findById ( Long id );

    public List<CblTElaborazione> findByCblTEnteIdEnte ( String idEnte );

    public List<CblTElaborazione> findByCblDStatoElaborazioneCodiceStato ( String statoElaborazione );

    public List<CblTElaborazione> findByCblTEnteIdEnteAndListaFlussi (
        String idEnte, String listaFlussi );

    public CblTElaborazione findOneByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato (
        String idEnte, String statoElaborazione );

    public List<CblTElaborazione> findAllByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato (
        String idEnte, String statoElaborazione );

    public List<CblTElaborazione> findByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStatoAndListaFlussi (
        String idEnte, String statoElaborazione, String listaFlussi );

    public List<CblTElaborazione> findByCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStatoAndCblDErrore (
        String idEnte, String statoElaborazione, CblDErrore errore );

    @Query ( value = "select e from cbl_t_elaborazione e where id_ente = ?1 and stato_elaborazione in ?2 and data_elaborazione <= ?3", nativeQuery = true )
    public List<CblTElaborazione> recuperaSchedulazioneValidaPerEnte (
        String idEnte, List<String> statoElaborazione, Timestamp dataElaborazione );

    @Modifying
    @Query ( "update CblTElaborazione set listaFlussi = ?1 where id = ?2" )
    public void updateListaFlussi ( String listaFlussi, Long id );

    @Modifying
    @Query ( "update CblTElaborazione set msgErrore = ?1 where id = ?2" )
    public void aggiornaMessaggioErrore ( String descrizioneErrore, Long idElaborazione );

    @Modifying
    @Query ( "update CblTElaborazione set dataInizio = ?1 where id = ?2" )
    public void aggiornaDataInizioElaborazione ( Timestamp dataInizio, Long idElaborazione );

    @Query ( value = "select count(*) from cbl_t_elaborazione where (CAST (data_fine AS DATE) = CAST (now() AS DATE) and stato_elaborazione = 'TERMINATA' and id_ente = ?1)",
                    nativeQuery = true )
    public BigInteger esisteElaborazioneTerminataOggi ( String idEnte );

    public List<CblTElaborazione> findByCblTEnteIdEnteInAndCblDStatoElaborazioneCodiceStatoIn ( List<String> idEnteList, List<String> statiElaborazione );

    public List<CblTElaborazione> findByListaFlussiAndCblTEnteIdEnteAndCblDStatoElaborazioneCodiceStato ( String listaFlussi, String idEnte,
        String statoElaborazione );

}
