/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
import it.csi.epay.epaysim.interfacews.epaysim.EpaysimulatorDataws;


@WebService ( serviceName = "EpaysimulatorDatawsService", portName = "EpaysimulatorDatawsPort",
                endpointInterface = "it.csi.epay.epaysim.interfacews.epaysim.EpaysimulatorDataws" )
public class EpaysimulatorDatawsImpl implements EpaysimulatorDataws {

    @Resource
    WebServiceContext wsContext;

    @Autowired
    private EpaysimulatorDatawsBusiness epaysimulatorDatawsBusiness;

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    @Override
    public GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( GiornaleDiCassaInputDTO giornaleDiCassaInputDTO ) throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaGiornaleDiCassa ( giornaleDiCassaInputDTO );
    }

    @Override
    public InserisciProvvisorioOutputResponse inserisciProvvisorio ( InserisciProvvisorioInputDTO inserisciProvvisorioInputDTO )
                    throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.inserisciProvvisorio ( inserisciProvvisorioInputDTO );
    }

    @Override
    public UpdateEsitoStatoElaborazioneFlussoOutputDTO
        updateEsitoStatoElaborazioneFlusso ( UpdateEsitoStatoElaborazioneFlussoInputDTO updateEsitoStatoElaborazioneFlussoInputDTO )
                        throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.updateEsitoStatoElaborazioneFlusso ( updateEsitoStatoElaborazioneFlussoInputDTO );
    }

    @Override
    public RicercaFlussoOutputResponse ricercaFlusso ( RicercaFlussoInputDTO ricercaFlussoInputDTO )
                    throws EpaysimulatorException, DatatypeConfigurationException {
        return epaysimulatorDatawsBusiness.ricercaFlusso ( ricercaFlussoInputDTO );
    }

    @Override
    public DTOOutputWsProvvisori ricercaProvvisori ( DTOInputWsRicercaProvvisori dtoInputWsRicercaProvvisori )
                    throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaProvvisori ( dtoInputWsRicercaProvvisori );
    }

    @Override
    public List<FlussoOrigineErrorePagopaOutputDTO> ricercaFlussiInErrore ( RicercaFlussoErroreInputDTO inputRicercaFlussoErrore )
                    throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaFlussiInErrore ( inputRicercaFlussoErrore );
    }

    @Override
    public FlussoDettaglioPagopaOutputDTO ricercaFlussoDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO )
                    throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaFlussoDettaglioPagoPa ( inputFlussoDettaglioPagopaDTO );
    }

    @Override
    public FlussoSintesiPagopaOutputDTO ricercaFlussoSintesiPagoPa ( FlussoSintesiPagopaDTO inputFlussoSintesiPagopaDTO )
                    throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaFlussoSintesiPagoPa ( inputFlussoSintesiPagopaDTO );
    }

    @Override
    public FlussoOriginePagopaOutputDTO ricercaFlussoOriginePagoPa ( FlussoOriginePagopaDTO flussoOriginePagopaDTO )
                    throws EpaysimulatorException, UnrecoverableException, Exception {
        return epaysimulatorDatawsBusiness.ricercaFlussoOriginePagoPa ( flussoOriginePagopaDTO );
    }

    @Override
    public FlussoDettaglioPagopaOutputDTO ricercaDettaglioPagoPa ( FlussoDettaglioPagopaDTO inputFlussoDettaglioPagopaDTO ) throws EpaysimulatorException {
        return epaysimulatorDatawsBusiness.ricercaFlussoDettaglioPagoPa ( inputFlussoDettaglioPagopaDTO );
    }

}
