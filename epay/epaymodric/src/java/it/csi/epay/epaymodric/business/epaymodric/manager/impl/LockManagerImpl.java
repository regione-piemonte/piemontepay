/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.LockManager;
import it.csi.epay.epaymodric.business.epaymodric.manager.utils.LockUtility;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTLockApplicativo;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.LockRepository;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsLock;


@Service
@Transactional
public class LockManagerImpl implements LockManager {

    @Autowired
    private LockRepository lockRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Override
    public List<DTOOutputWsLock> lockFind ( DTOInputWsLock in ) {

        List<DTOOutputWsLock> lockDto = new ArrayList<DTOOutputWsLock> ();

        if(in == null) return lockDto;
        
        CblTEnte ente = enteRepository.findByCodiceFiscale ( in.getCodiceFiscaleEnte () );

        List<CblTLockApplicativo> locks = null;

        if(in.getDataInizio () == null) in.setDataInizio ( new Date(0) );

        if ( ( in.getIdUtente () != null ) && ( !in.getIdUtente ().isEmpty () ) ) {
            locks = lockRepository.findByCblTEnteAndIdUtenteAndDataInizioGreaterThanEqualAndDataFineLessThanEqualOrDataFineIsNull ( ente,
                in.getIdUtente (), in.getDataInizio (), in.getDataFine () );
        } 
        else {
            if(ente != null) {
                locks = lockRepository.findByCblTEnteAndDataInizioGreaterThanEqualAndDataFineLessThanEqualOrDataFineIsNull ( ente, in.getDataInizio (),
                    in.getDataFine () );
            } else {
                if(in.getDataFine () == null) {
                    locks = lockRepository.findByDataInizioGreaterThanEqual ( in.getDataInizio ());
                } else {
                    locks = lockRepository.findByDataInizioGreaterThanEqualAndDataFineLessThanEqual( in.getDataInizio (),in.getDataFine () );
                }
            }
        }

		for ( CblTLockApplicativo lock : locks ) {
			DTOOutputWsLock dto = LockUtility.toDto ( lock );

			dto.setDescrizioneEnte ( enteRepository.findByIdEnte ( dto.getIdEnte () ).getDenominazione () );

			lockDto.add ( dto );
		}

        return lockDto;
    }

    @Override
    public Boolean lockBreak ( Long idLock ) {
        CblTLockApplicativo lock = lockRepository.findOne ( idLock );

        lock.setDataFine ( null );

        CblTLockApplicativo res = lockRepository.save ( lock );

        return ( res.getDataFine () == null );
    }

}
