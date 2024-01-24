/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.interfacews.epaysim;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
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
import it.csi.epay.epaysim.exception.epaysim.UnrecoverableException;


/**
 *
 */

@WebService ( name = "EpaysimulatorDataws" )
public interface EpaysimulatorDataws {

    /**
     * Metodo che controlla se il giornale di cassa e' gia' presente.
     *
     * @param GiornaleDiCassaInputDTO DTO da controllare.
     * @return GiornaleDiCassaOutputDTO contiente l'entita' controllata.
     */
    @WebResult ( name = "result" )
    @WebMethod
    GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( @WebParam ( name = "param" ) GiornaleDiCassaInputDTO giornaleDiCassaInputDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    /**
     * Metodo che inserisce un provvisorio.
     *
     * @param InserisciProvvisorioInputDTO DTO da inserire.
     * @return InserisciProvvisorioOutputDTO contiente l'oggetto di controllo e l'entita' appena inserita.
     */
    @WebResult ( name = "result" )
    @WebMethod
    InserisciProvvisorioOutputResponse inserisciProvvisorio ( @WebParam ( name = "param" ) InserisciProvvisorioInputDTO inserisciProvvisorioInputDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    /**
     * Metodo che aggiorna lo stato del flusso.
     *
     * @param UpdateEsitoStatoElaborazioneFlussoInputDTO DTO che contiene l'identificativo del flusso ed il nuovo stato.
     * @return UpdateEsitoStatoElaborazioneFlussoOutputDTO contiente l'oggetto di controllo dell'esito della operazione.
     */
    @WebResult ( name = "result" )
    @WebMethod
    UpdateEsitoStatoElaborazioneFlussoOutputDTO
    updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO updateEsitoStatoElaborazioneFlussoInputDTO )
                    throws EpaysimulatorException;//, UnrecoverableException, Exception;

    //Metodo di ricerca di un flusso
    @WebResult ( name = "result" )
    @WebMethod
    RicercaFlussoOutputResponse ricercaFlusso ( @WebParam ( name = "param" ) RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException;

    //Gestione dei provvisori - SOLA LETTURA

    @WebMethod
    public DTOOutputWsProvvisori ricercaProvvisori (
        @WebParam ( name = "listaProvvisoriDaCercare" ) DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori ) throws EpaysimulatorException;

    @WebMethod
    List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( @WebParam ( name = "param" ) RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException;
    @WebMethod
    FlussoDettaglioPagopaOutputDTO ricercaDettaglioPagoPa ( @WebParam ( name = "param" ) FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO )
                    throws EpaysimulatorException;


    @WebMethod
    FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( @WebParam ( name = "param" ) FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO )
                    throws EpaysimulatorException;

    @WebResult ( name = "result" )
    @WebMethod
    FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO )
                    throws EpaysimulatorException, UnrecoverableException, Exception;

    @WebResult ( name = "result" )
    @WebMethod
    FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO ) throws EpaysimulatorException;
}
