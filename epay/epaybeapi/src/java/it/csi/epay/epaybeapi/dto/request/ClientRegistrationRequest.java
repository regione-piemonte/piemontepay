/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 */
public class ClientRegistrationRequest extends BasicRequest {

    /**
     *
     */
    private static final long serialVersionUID = -522844124325934206L;

    @NotNull ( message = "$MESSAGES.FIELD_NOT_NULL(\"codiceChiamante\")" )
    @Size ( min = 1, max = 50, message = "$MESSAGES.FIELD_LENGTH(\"codiceChiamante\", 1, 50)" )
    private String codiceChiamante;

    @NotNull ( message = "$MESSAGES.FIELD_NOT_NULL(\"descrizioneChiamante\")" )
    @Size ( min = 1, max = 250, message = "$MESSAGES.FIELD_LENGTH(\"descrizioneChiamante\", 1, 250)" )
    private String descrizioneChiamante;

    @NotNull ( message = "$MESSAGES.FIELD_NOT_NULL(\"url\")" )
    @Size ( min = 1, max = 200, message = "$MESSAGES.FIELD_LENGTH(\"url\", 1, 200)" )
    private String url;

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

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

}
