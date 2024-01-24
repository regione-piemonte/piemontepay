/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static it.csi.epay.epaypaweb.util.Util.quote;

public class ParametriInserimentoFlussoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/* DEV/CSI_PAG-2411 - BEGIN1 */
	private Long idUtente;
	/* DEV/CSI_PAG-2411 - END1 */
	private String codUtente;
	private String codFiscaleEnte;
	private String codVersamento;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Integer numeroPosizioniDebitorie;
	private BigDecimal totaleImportoPosizioniDebitorie;

	private boolean multibeneficiario;

	//@formatter:off
	public ParametriInserimentoFlussoDto(
		/* DEV/CSI_PAG-2411 - BEGIN2 */
		Long idUtente,
		/* DEV/CSI_PAG-2411 - END2 */
		String codUtente,
		String codFiscaleEnte,
		String codVersamento,
		Date dataInizioValidita,
		Date dataFineValidita,
		Integer numeroPosizioniDebitorie,
		BigDecimal totaleImportoPosizioniDebitorie,
		boolean multibeneficiario )
	{
	//@formatter:on
		/* DEV/CSI_PAG-2411 - BEGIN3 */
		this.idUtente = idUtente;
		/* DEV/CSI_PAG-2411 - END3 */
		this.codUtente = codUtente;
		this.codFiscaleEnte = codFiscaleEnte;
		this.codVersamento = codVersamento;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
		this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
		this.totaleImportoPosizioniDebitorie = totaleImportoPosizioniDebitorie;
		this.multibeneficiario = multibeneficiario;
	}

	/* DEV/CSI_PAG-2411 - BEGIN4 */
	public Long getIdUtente() {
		return idUtente;
	}
	/* DEV/CSI_PAG-2411 - END4 */

	public String getCodUtente() {
		return codUtente;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public Integer getNumeroPosizioniDebitorie() {
		return numeroPosizioniDebitorie;
	}

	public BigDecimal getTotaleImportoPosizioniDebitorie() {
		return totaleImportoPosizioniDebitorie;
	}

	public boolean isMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( boolean multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		/* DEV/CSI_PAG-2411 - BEGIN5 */
		s.append("idUtente:").append(idUtente).append(COMMA);
		/* DEV/CSI_PAG-2411 - END5 */
		s.append("codUtente:").append(quote(codUtente)).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("codVersamento:").append(quote(codVersamento)).append(COMMA);
		s.append("dataInizioValidita:").append(dataInizioValidita).append(COMMA);
		s.append("dataFineValidita:").append(dataFineValidita).append(COMMA);
		s.append("numeroPosizioniDebitorie:").append(numeroPosizioniDebitorie).append(COMMA);
		s.append("totaleImportoPosizioniDebitorie:").append(totaleImportoPosizioniDebitorie);
		s.append(" }");
		return s.toString();
	}

}
