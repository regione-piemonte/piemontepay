/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;

public interface FlussoDettaglioRepository extends JpaRepository<CblTFlussoDettaglio, Long> {

    public List<CblTFlussoDettaglio> findByIdIn ( List<Long> id );

    @Modifying
    @Query("delete from CblTFlussoSintesi where id_flusso_origine = ?1")
    public void deleteByCblTFlussoOrigine(Long id);

    public List<CblTFlussoDettaglio> findByCblTFlussoSintesi( CblTFlussoSintesi cblTFlussoSintesi );

    public List<CblTFlussoDettaglio> findByCblTFlussoSintesiId( Long idFlussoSintesi );

    public List<CblTFlussoDettaglio> findByCblTFlussoSintesiIn( List<CblTFlussoSintesi> cblTFlussoSintesi );
    
    public List<CblTFlussoDettaglio> findByIdentificativoUnicoVersamento( String identificativoUnicoVersamento );

    @Query ( value = "select count(*) from cbl_t_flusso_dettaglio where id_flusso_sintesi = ?1", nativeQuery = true )
    public BigInteger countByIdFlussoSintesi ( Long idFlussoSintesi );

}
