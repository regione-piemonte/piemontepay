/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaNumeroRiferimentiContabiliInVitaPerCovOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.VerificaPresenzaRiferimentiContabiliOutput;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;

import java.util.List;

public interface RiferimentoContabileService {

    RiferimentoContabile getPosizionePrecedente ( RiferimentoContabile entity );

    List<RiferimentoContabile> getAttiviByIdCodiceVersamento ( Long idCodiceVersamento );

    List<RiferimentoContabile> getInVitaByIdCodiceVersamento ( Long idCodiceVersamento );

    StoricoRiferimentoContabile generaVoceStorico ( RiferimentoContabile entity );

    String generaChiaveIntersistema ( RiferimentoContabile entity );

    RiferimentoContabile generaVoceDaStorico ( StoricoRiferimentoContabile entity );

    List<StoricoRiferimentoContabile> getStoricoPosizioniPrecedenti ( RiferimentoContabile entity );

    StoricoRiferimentoContabile salvaVoceStoricoPerModifica ( RiferimentoContabile entity );
    
    VerificaPresenzaRiferimentiContabiliOutput verificaPresenzaRiferimentiContabili ( VerificaPresenzaRiferimentiContabiliInput entity );
    
    VerificaNumeroRiferimentiContabiliInVitaPerCovOutput verificaNumeroRiferimentiContabiliInVitaPerCov ( VerificaNumeroRiferimentiContabiliInVitaPerCovInput entity );

	List<RiferimentoContabile> getByIdTassonomia ( Long idTassonomia );

	void aggiornaDatoSpecificoRiscossione ( Long idTassonomia, String tassonomiaEsistente, String tassonomiaNuova );
}
