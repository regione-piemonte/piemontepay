/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.manager;



import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOrigineErrore;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;
import it.csi.epay.epaysim.dto.RicercaProvvisorioInputDTO;
import it.csi.epay.epaysim.dto.RicercaProvvisorioOutputDTO;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussiInErroreType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.FlussoSintesiType;
import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.TestataFlussoRiconciliazioneType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TestataTrasmissioneFlussiType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;
import it.csi.epay.epaysim.exception.epaysim.UnrecoverableException;


/**
 * service per la gestione dei flussi.
 */
public interface FlussiManager {

    /**
     * salva flussi in errore.
     *
     * @param trasmettiFlussiInErrorePagoPARequest input
     * @param flussiInErroreType input
     * @return SimTFlussoOrigineErrore riga di errore.
     */
    SimTFlussoOrigineErrore salvaFlussoInErrore ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest,
        FlussiInErroreType flussiInErroreType );

    /**
     * Salva una singola riga di sintesi
     *
     * @param trasmettiFlussiPagoPARequest input
     * @param flussoSintesiType riga da salvare.
     * @param simTFlussoOriginePagopa testata salvata
     * @return SimTFlussoSintesiPagopa riga di sintesi con il dettaglio
     */
    SimTFlussoSintesiPagopa salvaRigaFlussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest,
        FlussoSintesiType flussoSintesiType, SimTFlussoOriginePagopa simTFlussoOriginePagopa );

    /**
     * Salva testata flussi corretti
     *
     * @param trasmettiFlussiPagoPARequest input
     * @return SimTFlussoOriginePagopa Flusso originie salvato.
     */
    SimTFlussoOriginePagopa salvaTestataFlussoRiconciliazione ( TestataFlussoRiconciliazioneType testataFlussoRiconciliazioneType )
                    throws EpaysimulatorException;

    /**
     * Metodo che aggiorna lo stato del flusso.
     *
     * @param idFlusso l'identificativo del flusso
     * @param nuovoStatoFlusso il nuovo stato.
     * @throws EpaysimulatorException
     * @throws UnrecoverableException
     * @throws Exception
     */
    void updateEsitoStatoElaborazioneFlussoSingolo ( Long idFlusso, String cf_utente, Boolean nuovoStato )
                    throws EpaysimulatorException, UnrecoverableException, Exception;

    /**
     *
     * @param RicercaFlussoInputDTO contiene i parametri di ricerca come id flusso
     * @return RicercaFlussoOutputResponse elenco dei flussi origine trovati con tipo FlussoOriginePagopaDTO
     * @throws EpaysimulatorException
     * @throws DatatypeConfigurationException
     * @throws UnrecoverableException
     * @throws Exception
     */
    RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException;//, UnrecoverableException, Exception;, UnrecoverableException, Exception;

    /**
     * Metodo che cerca l'esito dei flussi.
     *
     * @param testataFlussoRiconciliazioneType DTO contenente i dati del filtro di ricerca.
     * @return TestataFlussoRiconciliazioneType contiene la lista dei risultati che soddisfano il filtro di ricerca.
     */
    EsitoFlussiPagoPAResponse esitoFlussiPagoPA ( TestataTrasmissioneFlussiType testataTrasmissioneFlussiType )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;, UnrecoverableException, Exception;

    /**
     *
     *
     *
     */
    List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;, UnrecoverableException, Exception;

    /**
     *
     *
     * @param inputRicercaProvvisorio
     * @return
     */
    RicercaProvvisorioOutputDTO ricercaProvvisori ( RicercaProvvisorioInputDTO inputRicercaProvvisorio )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;, UnrecoverableException, Exception;

    /**
     *
     *
     * @param inputFlussoDettaglioPagopaDTO
     * @return
     */
    FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO );

    /**
     *
     *
     * @param inputFlussoSintesiPagopaDTO
     * @return
     */
    FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO );

    /**
     *
     *
     * @param flussoOriginePagopaDTO
     * @return
     */
    FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO );

    int salvaFlussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception;

}
