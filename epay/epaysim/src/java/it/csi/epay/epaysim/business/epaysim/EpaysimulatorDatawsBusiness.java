/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim;

import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoDettaglioPagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaDTO;
import it.csi.epay.epaysim.dto.FlussoOriginePagopaOutputDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaDTO;
import it.csi.epay.epaysim.dto.FlussoSintesiPagopaOutputDTO;
import it.csi.epay.epaysim.dto.GiornaleDiCassaInputDTO;
import it.csi.epay.epaysim.dto.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioInputDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysim.dto.RicercaFlussoErroreInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoInputDTO;
import it.csi.epay.epaysim.dto.RicercaFlussoOutputResponse;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoInputDTO;
import it.csi.epay.epaysim.dto.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysim.dto.ws.DTOInputWsRicercaProvvisori;
import it.csi.epay.epaysim.dto.ws.DTOOutputWsProvvisori;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;


/**
 * Interfaccia relativa alla definizione dei metodi di business utilizzati per la gestione CRUD dei dati relativi al flusso rendicontazione e dei provvisori
 */

public interface EpaysimulatorDatawsBusiness {

    /**
     * Metodo per controllare il giornale di cassa.
     *
     * @param GiornaleDiCassaInputDTO DTO da controllare.
     * @return GiornaleDiCassaOutputDTO contiente l'entita' appena controllata.
     */
    GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( GiornaleDiCassaInputDTO giornaleDiCassaInputDTO )
                    throws EpaysimulatorException;//, Exception, UnrecoverableException;

    /**
     * Metodo che inserisce un provvisorio.
     *
     * @param InserisciProvvisorioInputDTO DTO da inserire.
     * @return InserisciProvvisorioOutputDTO contiente l'oggetto di controllo e l'entita' appena inserita.
     */
    InserisciProvvisorioOutputResponse inserisciProvvisorio ( InserisciProvvisorioInputDTO inserisciProvvisorioInputDTO )
                    throws EpaysimulatorException;//, Exception, UnrecoverableException;

    /**
     * Metodo che aggiorna lo stato del flusso.
     *
     * @param UpdateEsitoStatoElaborazioneFlussoInputDTO DTO che contiene l'identificativo del flusso ed il nuovo stato.
     * @return UpdateEsitoStatoElaborazioneFlussoOutputDTO contiente l'oggetto di controllo dell'esito della operazione.
     */
    UpdateEsitoStatoElaborazioneFlussoOutputDTO
    updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO updateEsitoStatoElaborazioneFlussoInputDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException;//, UnrecoverableException, Exception;

    DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori );


    /*
     * NG
     */
    List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException;

    /*
     * NG
     */
    //RicercaProvvisorioOutputDTO ricercaProvvisori ( RicercaProvvisorioInputDTO inputRicercaProvvisorio )
    //                throws EpaysimulatorException, UnrecoverableException, Exception;

    /**
     * web method RicercaDettaglioPagoPa NG
     */
    FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    /**
     * Web method RicercaSintesiPagoPa NG
     */
    FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    /**
     *
     * Web method RicercaFlussoOriginePagoPa NG
     */

    FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO );
}
