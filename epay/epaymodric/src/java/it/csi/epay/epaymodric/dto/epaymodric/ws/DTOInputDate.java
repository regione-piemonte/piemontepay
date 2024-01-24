/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;

public class DTOInputDate extends DTOInputBase {

    private static final long serialVersionUID = 422088937398221477L;

    private XMLGregorianCalendar dataElaborazioneA;

    private XMLGregorianCalendar dataElaborazioneDa;

    private XMLGregorianCalendar dataRicezioneA;

    private XMLGregorianCalendar dataRicezioneDa;

    
    /**
     * @return the dataElaborazioneA
     */
    @XmlSchemaType ( name = "dateTime" )
    public XMLGregorianCalendar getDataElaborazioneA () {
        return dataElaborazioneA;
    }

    
    /**
     * @param dataElaborazioneA the dataElaborazioneA to set
     */
    public void setDataElaborazioneA ( XMLGregorianCalendar dataElaborazioneA ) {
        this.dataElaborazioneA = dataElaborazioneA;
    }

    
    /**
     * @return the dataElaborazioneDa
     */
    @XmlSchemaType ( name = "dateTime" )
    public XMLGregorianCalendar getDataElaborazioneDa () {
        return dataElaborazioneDa;
    }

    
    /**
     * @param dataElaborazioneDa the dataElaborazioneDa to set
     */
    public void setDataElaborazioneDa ( XMLGregorianCalendar dataElaborazioneDa ) {
        this.dataElaborazioneDa = dataElaborazioneDa;
    }

    
    /**
     * @return the dataRicezioneA
     */
    @XmlSchemaType ( name = "dateTime" )
    public XMLGregorianCalendar getDataRicezioneA () {
        return dataRicezioneA;
    }

    
    /**
     * @param dataRicezioneA the dataRicezioneA to set
     */
    public void setDataRicezioneA ( XMLGregorianCalendar dataRicezioneA ) {
        this.dataRicezioneA = dataRicezioneA;
    }

    
    /**
     * @return the dataRicezioneDa
     */
    @XmlSchemaType ( name = "dateTime" )
    public XMLGregorianCalendar getDataRicezioneDa () {
        return dataRicezioneDa;
    }

    
    /**
     * @param dataRicezioneDa the dataRicezioneDa to set
     */
    public void setDataRicezioneDa ( XMLGregorianCalendar dataRicezioneDa ) {
        this.dataRicezioneDa = dataRicezioneDa;
    }

}
