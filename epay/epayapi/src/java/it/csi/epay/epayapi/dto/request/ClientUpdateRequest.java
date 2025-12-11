/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.dto.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 */
public class ClientUpdateRequest extends BasicRequest {

    /**
     *
     */
    private static final long serialVersionUID = 4468647087431505355L;

    @NotNull ( message = "$MESSAGES.FIELD_NOT_NULL(\"descrizioneChiamante\")" )
    @Size ( min = 1, max = 250, message = "$MESSAGES.FIELD_LENGTH(\"descrizioneChiamante\", 1, 250)" )
    private String descrizioneChiamante;

    @NotNull ( message = "$MESSAGES.FIELD_NOT_NULL(\"url\")" )
    @Size ( min = 1, max = 200, message = "$MESSAGES.FIELD_LENGTH(\"url\", 1, 200)" )
    private String url;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    public String getDescrizioneChiamante () {
        return descrizioneChiamante;
    }

    public void setDescrizioneChiamante ( String descrizioneChiamante ) {
        this.descrizioneChiamante = descrizioneChiamante;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

}
