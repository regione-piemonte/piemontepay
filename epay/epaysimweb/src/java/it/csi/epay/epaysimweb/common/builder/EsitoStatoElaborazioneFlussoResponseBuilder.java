/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.common.builder;

import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.UpdateEsitoStatoElaborazioneFlussoResponse;


public class EsitoStatoElaborazioneFlussoResponseBuilder implements AbstractBuilder<UpdateEsitoStatoElaborazioneFlussoResponse> {

    private String codiceEsito;

    private String codiceMessaggio;

    private Integer codiceStato;

    private String descrizioneEsito;

    @Override
    public UpdateEsitoStatoElaborazioneFlussoResponse build () {
        UpdateEsitoStatoElaborazioneFlussoResponse res = new UpdateEsitoStatoElaborazioneFlussoResponse ();

        res.setResult ( new UpdateEsitoStatoElaborazioneFlussoOutputDTO () );

        res.getResult ().setCodiceEsito ( codiceEsito );
        res.getResult ().setCodiceMessaggio ( codiceMessaggio );
        res.getResult ().setCodiceStato ( codiceStato );
        res.getResult ().setDescrizioneEsito ( descrizioneEsito );

        return res;
    }

    
    public EsitoStatoElaborazioneFlussoResponseBuilder withCodiceEsito ( String codiceEsito ) {
        this.codiceEsito = codiceEsito;
        return this;
    }

    
    public EsitoStatoElaborazioneFlussoResponseBuilder withCodiceMessaggio ( String codiceMessaggio ) {
        this.codiceMessaggio = codiceMessaggio;
        return this;
    }

    
    public EsitoStatoElaborazioneFlussoResponseBuilder withCodiceStato ( Integer codiceStato ) {
        this.codiceStato = codiceStato;
        return this;
    }

    
    public EsitoStatoElaborazioneFlussoResponseBuilder withDescrizioneEsito ( String descrizioneEsito ) {
        this.descrizioneEsito = descrizioneEsito;
        return this;
    }

}
