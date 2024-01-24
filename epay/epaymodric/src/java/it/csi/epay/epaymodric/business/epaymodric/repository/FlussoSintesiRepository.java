/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;

public interface FlussoSintesiRepository extends JpaRepository<CblTFlussoSintesi, Long> {
    
    public List<CblTFlussoSintesi> findByIdIn ( List<Long> id );
    
    public List<CblTFlussoSintesi> findByCblTFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine );

    @Query("select s from CblTFlussoSintesi s where s.cblTFlussoOrigine = (select o from CblTFlussoOrigine o where o.id = ?1 ) and s.cblRStatoFlussoErrore is not null")
    public List<CblTFlussoSintesi> findByIdFlussoOrigineAndIdErroreIsNotNull ( Long idFlussoOrigine );

    @Query("select s from CblTFlussoSintesi s where s.cblTFlussoOrigine = (select o from CblTFlussoOrigine o where o.id = ?1 ) ")
    public List<CblTFlussoSintesi> findByIdFlussoOrigine ( Long idFlussoOrigine );

    public List<CblTFlussoSintesi> findByCblTEnte(CblTEnte cblTEnte);

    public List<CblTFlussoSintesi> findByCblTEnteAndCblTFlussoOrigineCblDStatoFlussoIn(
        CblTEnte cblTEnte, List<CblDStatoFlusso> idStatoFlusso);
    
    public List<CblTFlussoSintesi> findByCblTEnteAndCblTFlussoOrigineCblDStatoFlussoInAndCblTFlussoOrigine ( 
        CblTEnte ente, List<CblDStatoFlusso> cblDStatiFlusso, CblTFlussoOrigine cblTFlussoOrigine );
    
    public List<CblTFlussoSintesi> findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnteAndCblTFlussoOrigineCblDStatoFlussoIn(
        String identificativoFlusso, CblTEnte cblTEnte, List<CblDStatoFlusso> idStatoFlusso
    );
    
    public List<CblTFlussoSintesi> findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnte(
        String identificativoFlusso, CblTEnte cblTEnte
    );
    
    public List<CblTFlussoSintesi> findByCblTEnteAndCblTFlussoOrigineIdentificativoFlussoIn (
        CblTEnte cblTente, List<String> identificativoFlusso
    );
    
   
    public List<CblTFlussoSintesi> findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnteAndCblTFlussoOrigineCblDStatoFlusso (
        String identificativoFlusso, CblTEnte cblTEnte, CblDStatoFlusso idStatoFlusso
    );
    
    public List<CblTFlussoSintesi> findByCblTFlussoOrigineCblDStatoFlussoIn (List<CblDStatoFlusso> idStatoFlusso);

    public List<CblTFlussoSintesi> findByCblTEnteAndCblTFlussoOrigineIdentificativoFlussoInAndCblTFlussoOrigineCblDStatoFlussoIn (
        CblTEnte cblTente, List<String> identificativoFlusso,  List<CblDStatoFlusso> idStatoFlusso
    );
    
//    //Nuru 2.2.10
//    public List<CblTFlussoSintesi> findByCblTFlussoOrigineIdentificativoFlusso(
//            String identificativoFlusso, CblTEnte cblTEnte
//        );
//    //Nuru 2.2.10
//    public List<CblTFlussoSintesi> findByCblTFlussoOrigineIdentificativoFlussoAndCblTEnte(
//           List<String> identificativoFlusso, CblTEnte cblTEnte
//        );
    //Nuru 2.2.10
    public List<CblTFlussoSintesi> findByCblTEnteAndCblTFlussoOrigineId(CblTEnte ente, Long idFlussoOrigine);
    
    @Modifying
    @Query("update CblTFlussoSintesi set cblRStatoFlussoErrore = ?1 where cblTFlussoOrigine in ?2 and codiceVersamento = ?3 and datiSpecificiDiRiscossione = ?4 ")
    public void aggiornaCblDErrore (
        CblRStatoFlussoErrore cblRStatoFlussoErrore,  
        List<CblTFlussoOrigine> cblFlussoOrigine, 
        String codiceVersamento,
        String datiSpecificiDiRiscossione);
    
    @Modifying
    @Query("update CblTFlussoSintesi set cblRStatoFlussoErrore = ?1 where id = ?2 ")
    public void aggiornaIdErrore (
        CblRStatoFlussoErrore cblRStatoFlussoErrore,  
        Long id);
    
    @Modifying
    @Query("update CblTFlussoSintesi set provvisorioAnno = ?1, provvisorioNro = ?2 where id = ?3")
    public void aggiornaDatiProvvisori ( Integer annoProvvisorio, Integer numeroProvvisorio, Long id );
    
    @Query("select sum ( d.importoSingoloVersamento ) from CblTFlussoSintesi s, CblTFlussoDettaglio d where d.cblTFlussoSintesi = s.id and s.cblTFlussoOrigine = ?1 ")
    public BigDecimal getImportoTotaleDettagli( CblTFlussoOrigine cblTFlussoOrigine );
    
    @Modifying
    @Query("update CblTFlussoSintesi set capitolo = ?1, articolo = ?2, pianoDeiConti = ?3 where id = ?4")
    public void aggiornaDatiContabili ( String capitolo, Integer articolo, String pianoDeiConti, Long id );

	

}
