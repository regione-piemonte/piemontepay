/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.business.manager;

import java.util.List;

import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaysimulatorDataWsFacade;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoResponse;
import it.csi.epay.epaysimweb.model.flussi.EsportazioneFlussoVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussiSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoDettaglioVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoOrigineVO;
import it.csi.epay.epaysimweb.model.flussi.FlussoSintesiVO;
import it.csi.epay.epaysimweb.model.flussi.RicercaFlussoFiltroVO;


/**
 * Servizio che opera con gestisce i flussi
 */
public interface FlussiManager {


    /**
     * Metodo che chiama il WS {@link EpaysimulatorDataWsFacade} ed effettua una ricerca in base ai parametri selezionati.
     * 
     * @param filtro input selezionato dall'utente.
     * @return List<FlussoOrigineVO> lista di oggetti testata flusso.
     * @throws OperationFailedException business exception.
     */
    List<FlussoOrigineVO> ricercaFlussi ( RicercaFlussoFiltroVO filtro ) throws OperationFailedException;

    UpdateEsitoStatoElaborazioneFlussoResponse updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO param ) throws OperationFailedException;

    FlussiSintesiVO ricercaFlussoOriginePagoPa ( FlussoOrigineVO flussoOrigine ) throws OperationFailedException;

    FlussiDettaglioVO ricercaFlussoSintesiPagoPa ( FlussoSintesiVO flussoSintesi ) throws OperationFailedException;

    FlussoDettaglioVO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioVO flussoDettaglioVO ) throws OperationFailedException;

    List<EsportazioneFlussoVO> ricercaFlussiDaEsportare ( RicercaFlussoFiltroVO filtro ) throws OperationFailedException;
}
