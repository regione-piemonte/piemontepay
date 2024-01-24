/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblRStatoFlussoErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;

public interface StatoFlussoErroreRepository extends JpaRepository<CblRStatoFlussoErrore, Long> {
    
    public List<CblRStatoFlussoErrore> findByCblTFlussoOrigine(CblTFlussoOrigine cblTFlussoOrigine);
    
    public List<CblRStatoFlussoErrore> findByCblTFlussoOrigineAndCblDErrore(
        CblTFlussoOrigine cblTFlussoOrigine, CblDErrore cblDErrore
    );

    public CblRStatoFlussoErrore findById(Long id);
    
}
