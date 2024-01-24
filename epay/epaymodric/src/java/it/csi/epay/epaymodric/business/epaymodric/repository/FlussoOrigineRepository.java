/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;


public interface FlussoOrigineRepository extends JpaRepository<CblTFlussoOrigine, Long>, JpaSpecificationExecutor<CblTFlussoOrigine> {

    public List<CblTFlussoOrigine> findByIdIn ( List<Long> id );

    public List<CblTFlussoOrigine> findByCblDStatoFlusso_Id ( List<Long> id );

    public List<CblTFlussoOrigine> findByIdentificativoIstitutoRiceventeOrderByIdAsc (String idEnte);

    public List<CblTFlussoOrigine> findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInAndCblDStatoFlussoInOrderByIdAsc (
        String codiceFiscale, List<String> identificativiFlusso, List<CblDStatoFlusso> cblDStatiFlusso );

    public List<CblTFlussoOrigine> findByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInOrderByIdAsc (
        String IdentificativoIstitutoRicevente, List<CblDStatoFlusso> cblDStatoFlusso );

    public List<CblTFlussoOrigine> findByIdentificativoIstitutoRiceventeAndIdentificativoFlussoInOrderByIdAsc (
        String codiceFiscale, List<String> identificativiFlusso
                    );

    public List<CblTFlussoOrigine> findByIdentificativoFlussoAndCblTEnteOrderByIdAsc (
        String identificativoFlusso, CblTEnte cblTEnte
                    );

    @Deprecated
    /**
     * sostituito con findByIdentificativoFlussoAndCblTEnteOrderByIdAsc
     * @param identificativoFlusso
     * @param cblTEnte
     * @return
     */
    public List<CblTFlussoOrigine> findByIdentificativoFlussoAndCblTEnte (
        String identificativoFlusso, CblTEnte cblTEnte
                    );

    public List<CblTFlussoOrigine> findByIdentificativoFlussoAndIdentificativoIstitutoRiceventeOrderByIdAsc (
        String identificativoFlusso, String codiceFiscaleEnte
                    );

    @Deprecated
    /**
     * sostituito con findByIdentificativoFlussoAndIdentificativoIstitutoRiceventeOrderByIdAsc
     * @param identificativoFlusso
     * @param codiceFiscaleEnte
     * @return
     */
    public List<CblTFlussoOrigine> findByIdentificativoFlussoAndIdentificativoIstitutoRicevente (
        String identificativoFlusso, String codiceFiscaleEnte
                    );

    public List<CblTFlussoOrigine> findByIdentificativoFlussoAndCblTEnteAndCblDStatoFlussoInOrderByIdAsc (
        String identificativoFlusso, CblTEnte cblTEnte, List<CblDStatoFlusso> cblDStatiFlusso
                    );

    public List<CblTFlussoOrigine> findByCblTEnteAndCblDStatoFlussoInOrderByIdAsc (
        CblTEnte cblTEnte, List<CblDStatoFlusso> statoFlusso
                    );

    @Deprecated
    /**
     * sostituito con findByCblTEnteAndCblDStatoFlussoInOrderByIdAsc
     * @param cblTEnte
     * @param statoFlusso
     * @return
     */
    public List<CblTFlussoOrigine> findByCblTEnteAndCblDStatoFlusso (
        CblTEnte cblTEnte, CblDStatoFlusso statoFlusso
                    );

    public List<CblTFlussoOrigine> findByCblTEnteAndCblDStatoFlussoOrderByIdAsc (
        CblTEnte cblTEnte, CblDStatoFlusso cblDStatoFlusso
                    );

    @Query("select e from CblTFlussoOrigine e where identificativo_istituto_ricevente = ?1 order by id asc ")
    public List<String> recuperaListaIdentificativi ( String codiceFiscaleEnte);

    @Query("select e from CblTFlussoOrigine e where identificativo_istituto_ricevente = ?1 and cblDStatoFlussos in ?2 order by id asc ")
    public List<String> recuperaListaIdentificativi ( String codiceFiscaleEnte, List<CblDStatoFlusso> cblDStatoFlussos );

    @Query("select e from CblTFlussoOrigine e where id_stato_flusso is null and identificativo_istituto_ricevente = ?1 order by id asc")
    public List<CblTFlussoOrigine> findByCblTEnteAndCblDStatoFlussoIsNull ( String codiceFiscaleEnte);

    @Modifying
    @Query("update CblTFlussoOrigine set contatoreTentativi = ?1 where identificativoFlusso = ?2")
    public int aggiornaContatoreTentativi(Integer contatoreTentativi, String identificativoFlusso);

    @Modifying
    @Query("update CblTFlussoOrigine set cblTElaborazione = ?1, cblDStatoFlusso = ?2  where identificativoFlusso = ?3")
    public int aggiornaIdEStatoElaborazione(CblTElaborazione cblTElaborazione, CblDStatoFlusso cblDStatoFlusso,  String identificativoFlusso);

    @Modifying
    @Query("update CblTFlussoOrigine set cblTElaborazione = ?1 where identificativoFlusso = ?2")
    public int aggiornaIdEStatoElaborazione(CblTElaborazione cblTElaborazione,  String identificativoFlusso);

    @Modifying
    @Query("update CblTFlussoOrigine c set c.cblTEnte = (select e.id from CblTEnte e where e.codiceFiscale = c.identificativoIstitutoRicevente) where c.identificativoIstitutoRicevente = ?1 and c.identificativoFlusso in ( ?2 ) ")
    public void updateFK (String codiceEnte, List<String> identificativiFlusso);

    @Query(value="select sum(coalesce(dettaglio.importo_singolo_versamento, 0)) from cbl_t_flusso_sintesi sintesi " +
                    "left join cbl_t_flusso_dettaglio dettaglio on dettaglio.id_flusso_sintesi = sintesi.id " +
                    "where sintesi.id_flusso_origine = ?1", nativeQuery=true)
    public BigDecimal calcolaImportoTotaleFlussoOrigineByIdFlussoOrigine(Long idFlussoOrigine);

    @Query ( value = "select count(*) from cbl_t_flusso_sintesi sintesi " +
                    "left join cbl_t_flusso_dettaglio dettaglio on dettaglio.id_flusso_sintesi = sintesi.id " +
                    "where sintesi.id_flusso_origine = ?1", nativeQuery = true )
    public BigInteger calcolaNumeroPagamentiFlussoOrigineByIdFlussoOrigine ( Long idFlussoOrigine );

    @Query("update CblTFlussoOrigine set cblDStatoFlusso = ?1 where identificativoFlusso in ?2")
    @Modifying
    public void aggiornaStatoFlussoMassivo(CblDStatoFlusso cblDStatoFlusso, List<String> identificativiFlusso);

    //Nuru
    public List<CblTFlussoOrigine> findByIdentificativoFlussoIn ( List<String> string );

    public List<CblTFlussoOrigine> findByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInAndIdentificativoFlussoIn ( String identificativoIstitutoRicevente,
        List<CblDStatoFlusso> statiFlusso, List<String> identificativoFlusso );

    public List<CblTFlussoOrigine> findAllByCblTElaborazioneId ( Long idElaborazione );

    public List<CblTFlussoOrigine> findAllByCblTElaborazioneIdAndIdentificativoFlussoIn ( Long idElaborazione, List<String> identificativoFlussoList );

    public List<CblTFlussoOrigine> findAllByIdentificativoFlussoInAndCblTEnteIdEnte ( List<String> string, String idEnte );

    //LF 07/05/2021
    @Query ( "select count(*) from CblTFlussoOrigine flussi where flussi.identificativoIstitutoRicevente = ?1 and flussi.cblDStatoFlusso in (?2) and flussi.dataoraFlusso < (?3)" )
    public long countByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInOrderByIdAsc (
        String identificativoIstitutoRicevente, List<CblDStatoFlusso> statiFlusso, Date date );

    @Query ( "select count(*) from CblTFlussoOrigine flussi where flussi.identificativoIstitutoRicevente = ?1 and flussi.identificativoFlusso in (?2) and flussi.dataoraFlusso < (?3)" )
    public long countByIdentificativoIstitutoRiceventeAndCblDStatoFlussoInAndIdentificativoFlussoIn (
        String identificativoIstitutoRicevente, List<String> identificativoFlusso, Date date );
}
