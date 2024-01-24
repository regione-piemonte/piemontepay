/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.builder;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.RicercaFlussoInputDTO;


/**
 * builder per la classe {@link RicercaFlussoInputDTO}
 */
public class RicercaFlussoInputDTOBuilder implements AbstractBuilder<RicercaFlussoInputDTO> {

    protected XMLGregorianCalendar dateEnd;

    protected XMLGregorianCalendar dateStart;

    protected String identificativoFlusso;

    protected String statoElaborazioneFlusso;

    public RicercaFlussoInputDTOBuilder () {

    }

    @Override
    public RicercaFlussoInputDTO build () {
        RicercaFlussoInputDTO ricercaFlussoInputDTO = new RicercaFlussoInputDTO ();
        ricercaFlussoInputDTO.setDateEnd ( dateEnd );
        ricercaFlussoInputDTO.setDateStart ( dateStart );
        ricercaFlussoInputDTO.setIdentificativoFlusso ( identificativoFlusso );
        ricercaFlussoInputDTO.setStatoElaborazioneFlusso ( statoElaborazioneFlusso );
        return ricercaFlussoInputDTO;
    }

    public RicercaFlussoInputDTOBuilder withDateEnd ( XMLGregorianCalendar dateEnd ) {
        this.dateEnd = dateEnd;
        return this;
    }

    public RicercaFlussoInputDTOBuilder withDateStart ( XMLGregorianCalendar dateStart ) {
        this.dateStart = dateStart;
        return this;
    }

    public RicercaFlussoInputDTOBuilder withDateEnd ( Date dateEnd ) {
        if ( null == dateEnd ) {
            this.dateEnd = null;
            return this;
        } else {
            XMLGregorianCalendar XMLDateEnd;
            try {
                GregorianCalendar c = new GregorianCalendar ();
                c.setTime ( dateEnd );
                XMLDateEnd = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
            } catch ( DatatypeConfigurationException e ) {
                XMLDateEnd = null;
            }
            this.dateEnd = XMLDateEnd;
            return this;
        }
    }

    public RicercaFlussoInputDTOBuilder withDateStart ( Date dateStart ) {
        if ( null == dateStart ) {
            this.dateEnd = null;
            return this;
        } else {
            XMLGregorianCalendar XMLDateStart;
            try {
                GregorianCalendar c = new GregorianCalendar ();
                c.setTime ( dateStart );
                XMLDateStart = DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
            } catch ( DatatypeConfigurationException e ) {
                XMLDateStart = null;
            }
            this.dateStart = XMLDateStart;
            return this;
        }
    }

    public RicercaFlussoInputDTOBuilder withIdentificativoFlusso ( String identificativoFlusso ) {
        this.identificativoFlusso = identificativoFlusso;
        return this;
    }

    public RicercaFlussoInputDTOBuilder withStatoElaborazioneFlusso ( String statoElaborazioneFlusso ) {
        this.statoElaborazioneFlusso = statoElaborazioneFlusso;
        return this;
    }

}
