/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;

public interface RiferimentoContabileRepositoryCustom {

    public List<RiferimentoContabile> ricerca ( RicercaRiferimentoContabileInput input, Long idEnte );

    public long findRiferimentoContabileDuplicatoPerCodiceVersamento ( Long idCodiceVersamento, Integer annoEsercizio, Date dataInizioValidita, Date dataFineValidita) ;

    public List<RiferimentoContabile> ricercaRiferimentiContabiliSecondariPerCov( Long idCov);

    public List<RiferimentoContabile> ricercaRiferimentiContabiliPrimariOSecondariPerCov(Long idCov);

    public List<RiferimentoContabile> ricercaInScadenza ( Long idEnte, Long maxNumDay );

    public List<RiferimentoContabile> ricercaDatoSpecificoRiscossione ( Long idCodiceVersamento, Long idEnte, Integer numeroAccertamento,
        Integer annoAccertamento, Integer annoEsercizio );
    public List<RiferimentoContabile> ricercaDatiSpecificiRiscossioneEmptyAcc ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio );

    public List<RiferimentoContabile> ricercaDatiSpecificiRiscossioneAnyAcc ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio );

    public long findRiferimentoDuplicato ( Long idCodiceVersamento, Date dataInizioValidita, Date dataFineValidita, String
        codTipologiaDatoSpecificoRiscossione, String datoSpecificoRiscossione,
        Integer annoEsercizio, Integer numeroAccertamento, Integer annoAccertamento) ;
    
    
    public List<RiferimentoContabile> ricercaDatiSpecificiRiscossione ( Long idCodiceVersamento, Long idEnte, Integer annoEsercizio );

    public List<RiferimentoContabile> ricercaRiferimentiContabiliInVitaPerCov ( Long idCov );


    public List<RiferimentoContabile> ricercaRiferimentiContabiliSovrappostiPerCov ( Long idCodiceVersamento, Date dataInizioValidita, Date dataFineValidita);

}
