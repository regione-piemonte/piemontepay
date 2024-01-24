/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.TransazioneService;
import it.csi.mdp.mdppagopaapi.integration.domain.Transazione;
import it.csi.mdp.mdppagopaapi.integration.repository.StatoTransazioneRepository;
import it.csi.mdp.mdppagopaapi.integration.repository.TransazioneRepository;
import it.csi.mdp.mdppagopaapi.util.StatoTransazioneEnum;

@Service
public class TransazioneServiceImpl implements TransazioneService {

    @Autowired
    private TransazioneRepository transazioneRepository;
    
    @Autowired
    private StatoTransazioneRepository statoTransazioneRepository;

    @Override
    public Transazione initTransazione ( Transazione t ) {
        // TODO Auto-generated method stub

        return transazioneRepository.save ( t );
    }
    
    @Override
    public Transazione findById ( String idTransazione ) {
        // TODO Auto-generated method stub

        return transazioneRepository.findOne ( idTransazione );
    }

    @Override
    public BigInteger getSequenceNextVal () {
        return transazioneRepository.getSequenceNextVal ();
    }

    @Override
    public Transazione aggiornaStatoTransazione (Transazione t, Long codStato) {
        t.setOldstate (new BigDecimal ( t.getStatoTransazione ().getCodStato () ));
        t.setStatoTransazione ( statoTransazioneRepository.findOne ( codStato ) );
        t.setChangestatedate ( new Date ( System.currentTimeMillis () ) );
        if  (StatoTransazioneEnum.STARTED.getCodice ().equals ( codStato )
                     || StatoTransazioneEnum.ATTESA_RT.getCodice ().equals ( codStato )   )
        {
            t.setStartDate (  new Date ( System.currentTimeMillis () )  );
        }
        if  (StatoTransazioneEnum.UNSUCCESFUL.getCodice ().equals ( codStato )
                        || StatoTransazioneEnum.SUCCESFUL.getCodice ().equals ( codStato ))
        {
            t.setFinishDate (  new Date ( System.currentTimeMillis () )  );
        }

        return transazioneRepository.save ( t );
    }
    
    


}
