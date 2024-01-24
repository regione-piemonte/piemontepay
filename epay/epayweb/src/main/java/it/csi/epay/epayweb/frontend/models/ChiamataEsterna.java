/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import it.csi.epay.epayweb.frontend.models.validators.annotation.CodiceFiscale_PartitaIva;
import it.csi.epay.epayweb.frontend.models.validators.annotation.Iuv;
import it.csi.epay.epayweb.frontend.models.validators.groups.DatiPersonaliSenzaIuvGroup;
import it.csi.epay.epayweb.frontend.models.validators.groups.RiferimentoConIuvGroup;

@Component
public class ChiamataEsterna extends _Model implements Serializable {
	
	private static final long serialVersionUID = -7671274919925719392L;

	@NotEmpty(message="Dato obbligatorio.")
	@NotNull(message="Dato obbligatorio.")
	private String codiceChiamante;
	
	@Size(max=100, message="Deve essere lungo massimo 100 caratteri.", groups=DatiPersonaliSenzaIuvGroup.class)
	@NotEmpty(message="Dato obbligatorio.")
    @NotNull(message="Dato obbligatorio.")
	private String digest;
	
	@Iuv(groups=RiferimentoConIuvGroup.class)
	@Pattern(regexp = "^[A-Za-z0-9]*$",  message="Caratteri non validi. Solo numeri e lettere.", groups=RiferimentoConIuvGroup.class)
	private String iuv;
	
	@Size(min=11, max=35, message="Deve essere lungo tra 11 e 35 caratteri.", groups=DatiPersonaliSenzaIuvGroup.class)
	@CodiceFiscale_PartitaIva(value=CodiceFiscale_PartitaIva.CfPi.CODICEFISCALE_OR_PARTITAIVA, groups=DatiPersonaliSenzaIuvGroup.class)
	@Pattern(regexp = "^[A-Za-z0-9]*$", message="Caratteri non validi. Solo numeri e lettere.", groups=DatiPersonaliSenzaIuvGroup.class)
	private String codiceFiscale;
	
    @Size ( max = 36, message = "Deve essere lungo massimo 36 caratteri.", groups = DatiPersonaliSenzaIuvGroup.class )
	@NotEmpty(message="Dato obbligatorio.")
	@NotNull(message="Dato obbligatorio.")
    private String identificativoPagamento;
	
	public String getCodiceChiamante() {
		return codiceChiamante;
	}

	public void setCodiceChiamante(String codiceChiamante) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

}
