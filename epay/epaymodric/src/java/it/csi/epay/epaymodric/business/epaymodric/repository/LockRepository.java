/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTLockApplicativo;


public interface LockRepository extends JpaRepository<CblTLockApplicativo, Long> {

    public List<CblTLockApplicativo> findByCblTEnteAndIdUtenteAndDataInizioGreaterThanEqualAndDataFineLessThanEqualOrDataFineIsNull ( CblTEnte ente,
        String idUtente, Date dataInizio, Date dataFine );

    public List<CblTLockApplicativo> findByDataInizioGreaterThanEqual(Date dataInizio);
    
    public List<CblTLockApplicativo> findByCblTEnteAndDataInizioGreaterThanEqualAndDataFineLessThanEqualOrDataFineIsNull ( CblTEnte ente, Date dataInizio,
        Date dataFine );

    public List<CblTLockApplicativo> findByDataInizioGreaterThanEqualAndDataFineLessThanEqual ( Date dataInizio, Date dataFine );

    public List<CblTLockApplicativo> findByCblTEnte ( CblTEnte ente );

}
