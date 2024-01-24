/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.business.manager.ProvvisoriManager;
import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaysimulatorDataWsFacade;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa.InformazioniContoEvidenza;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa.InformazioniContoEvidenza.MovimentoContoEvidenza;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa.TestataMessaggio;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioInputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioOutputResponse;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.ProvvisorioDTO;
import it.csi.epay.epaysimweb.util.DateUtils;



@Service
public class ProvvisoriManagerImpl implements ProvvisoriManager {

    private final static String STATO = "VALIDO";

    private final static String [] CODICI_CAUSALE = new String [] { "/PUR/LGPE-RIVERSAMENTO/URI/", "/RFS/", "/RFB/" };

    @Autowired
    private EpaysimulatorDataWsFacade simulatorFacade;

    public ProvvisoriManagerImpl () {

    }

    @Override
    public GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( FlussoGiornaleDiCassa flussoInput )
                    throws OperationFailedException {

        GiornaleDiCassaInputDTO input = new GiornaleDiCassaInputDTO ();
        try {
            input.setIdentificativoFlussoBT ( flussoInput.getIdentificativoFlussoBT () );
            return simulatorFacade.ricercaGiornaleDiCassa ( input );
        } catch ( Exception ex ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", ex );
        }
    }

    @Override
    public InserisciProvvisorioOutputResponse salvaFlussoProvvisori ( FlussoGiornaleDiCassa flussoInput, String utenteInsVar, String xmlFlusso )
                    throws OperationFailedException {
        InserisciProvvisorioInputDTO input = new InserisciProvvisorioInputDTO ();
        try {
            input.setXmlFlusso ( xmlFlusso );
            input.setIdentificativoFlussoBT ( flussoInput.getIdentificativoFlussoBT () );

            input.getProvvisorioDTOList ().addAll ( buildProvvisorioList ( flussoInput, utenteInsVar ) );
            if ( input.getProvvisorioDTOList ().size () > 0 ) {
                return simulatorFacade.inserisciProvvisorio ( input );
            }
            return new InserisciProvvisorioOutputResponse ();
        } catch ( Exception ex ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", ex );
        }

    }

    private List<ProvvisorioDTO> buildProvvisorioList ( FlussoGiornaleDiCassa flussoInput, String utenteInsVar ) throws DatatypeConfigurationException {
        List<ProvvisorioDTO> list = new ArrayList<> ();

        if ( flussoInput != null && flussoInput.getTestataMessaggio () != null && CollectionUtils.isNotEmpty ( flussoInput.getInformazioniContoEvidenza () ) ) {
            TestataMessaggio testata = flussoInput.getTestataMessaggio ();
            for ( InformazioniContoEvidenza info: flussoInput.getInformazioniContoEvidenza () ) {
                if ( CollectionUtils.isNotEmpty ( info.getMovimentoContoEvidenza () ) ) {
                    for ( MovimentoContoEvidenza movimento: info.getMovimentoContoEvidenza () ) {
                        if ( movimento.getCausale () != null && StringUtils.startsWithAny ( movimento.getCausale (), CODICI_CAUSALE ) ) {
                            String causale = movimento.getCausale ();
                            ProvvisorioDTO provv = new ProvvisorioDTO ();
                            provv.setCodiceFiscaleEnte ( testata.getCodiceFiscaleEnte () );
                            for ( String codice_causale: CODICI_CAUSALE ) {
                                if ( StringUtils.startsWith ( causale, codice_causale ) ) {
                                    //EPAY-131
                                    String extractIdentFlusso = causale.replace ( codice_causale, "" ).split ( " " ) [0];
                                    if ( extractIdentFlusso.contains ( "/" ) ) {
                                        extractIdentFlusso = extractIdentFlusso.split ( "/" ) [0];
                                    }
                                    provv.setIdentificativoFlusso ( extractIdentFlusso );
                                    break;
                                }
                            }
                            provv.setCausale ( causale.split ( " " ) [0] );
                            provv.setDataMovimento ( DateUtils.setTime ( movimento.getDataMovimento () ) );
                            provv.setAnnoEsercizio ( movimento.getDataMovimento ().getYear () );
                            provv.setAnnoProvvisorio ( movimento.getDataMovimento ().getYear () );
                            provv.setNumeroProvvisorio ( movimento.getNumeroDocumento () );
                            provv.setImportoProvvisorio ( movimento.getImporto () );
                            provv.setStato ( STATO );
                            provv.setDataInsVar ( DateUtils.getNowXmlGregorianCalendar () );
                            provv.setUtenteInsVar ( utenteInsVar );
                            //provv.setDescrizione ("Provvisorio per Incasso n." + movimento.getNumeroDocumento () + "/" + movimento.getDataMovimento ().getYear () );
                            //                            provv.setTipoMovimento ( movimento.getTipoDocumento () );
                            list.add ( provv );
                        }
                    }
                }
            }
        }

        return list;
    }

    @Override
    public DtoOutputWsProvvisori ricercaProvvisori ( DtoInputWsRicercaProvvisori filtroricerca ) throws OperationFailedException {

        DtoOutputWsProvvisori foundItems = new DtoOutputWsProvvisori ();
        try {
            foundItems = simulatorFacade.ricercaProvvisori ( filtroricerca );
        } catch ( Exception ex ) {
            throw new OperationFailedException ( "Errore imprevisto nell'esecuzione dell'operazione remota", ex );
        }
        return foundItems;

    }

}
