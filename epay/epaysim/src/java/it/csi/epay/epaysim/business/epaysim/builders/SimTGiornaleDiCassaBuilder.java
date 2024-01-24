/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.builders;

import java.util.Date;
import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTGiornaleDiCassa;
import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;


/**
 * builder per la classe {@link SimTGiornaleDiCassa}
 */
public class SimTGiornaleDiCassaBuilder extends AbstractBuilder<SimTGiornaleDiCassa> {

    private Long id;

    private String xml;

    private String identificativoFlussoBT;

    private List<SimTProvvisorio> simTProvvisorios;

    public SimTGiornaleDiCassaBuilder () {

    }

    @Override
    public SimTGiornaleDiCassa build () {
        return new SimTGiornaleDiCassa ( null != id ? id.intValue () : null, null == id ? dataInserimento : null, dataModifica,
            null == id ? utenteInserimento : null, utenteModifica, xml, identificativoFlussoBT, simTProvvisorios );
    }


    public SimTGiornaleDiCassaBuilder withId ( Long id ) {
        this.id = id;
        return this;
    }


    @Override
    public SimTGiornaleDiCassaBuilder withDataInserimento ( Date dataInserimento ) {
        this.dataInserimento = dataInserimento;
        return this;
    }


    public SimTGiornaleDiCassaBuilder withXml ( String xml ) {
        this.xml = xml;
        return this;
    }

    public SimTGiornaleDiCassaBuilder withIdentificativoFlussoBT ( String identificativoFlussoBT ) {
        this.identificativoFlussoBT = identificativoFlussoBT;
        return this;
    }

    public SimTGiornaleDiCassaBuilder withXml ( List<SimTProvvisorio> simTProvvisorios ) {
        this.simTProvvisorios = simTProvvisorios;
        return this;
    }
}
