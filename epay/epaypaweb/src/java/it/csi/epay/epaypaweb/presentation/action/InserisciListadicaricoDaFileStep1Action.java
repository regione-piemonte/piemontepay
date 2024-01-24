/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.ActionScope;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;

import it.csi.epay.epaypaweb.util.Util;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
	//@formatter:off
	@Result(name = INPUT, location = "pages/inserisci-listadicarico-da-file-step1.jsp"),
	@Result(name = SUCCESS, location = "pages/inserisci-listadicarico-da-file-step2.jsp")
	//@formatter:on
})
public class InserisciListadicaricoDaFileStep1Action extends InserisciFlussoDaFileStep1Action {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = InserisciListadicaricoDaFileStep1Action.class.getSimpleName ();

	private Integer idCodVersamento;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private Integer numeroPosizioniDebitorie;
	private BigDecimal totaleImportoPosizioniDebitorie;

	// campi usati per la validazione
	private String strDataInizioValidita;
	private String strDataFineValidita;
	private String strNumeroPosizioniDebitorie;
	private String strTotaleImportoPosizioniDebitorie;

	private String multibeneficiario;

	private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

	@Action("entry-inserisci-listadicarico-da-file-step1")
	@Authorization(cdu = "INS_LDC")
	@SkipValidation
	public String entryInserisciListadicaricoDaFileStep1() {
		String methodName = "entryInserisciListadicaricoDaFileStep1";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
		codiciVersamentoListeDiCarico = new LinkedList<> ();
		for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
			if ( cov.getFlagMbSecondario () == null || ( cov.getFlagMbSecondario () != null && !cov.getFlagMbSecondario () ) ) {
				codiciVersamentoListeDiCarico.add ( cov );
			}
		}

		ActionScope actionScope = getSessionContext().getActionScope();
		if (isInit()) {
			actionScope.reset();
			tipoFormato = TipoFormatoFileEnum.XLSX;

		} else {
			idCodVersamento = actionScope.getValue("idCodVersamento");
			strDataInizioValidita = Util.date2strdate(actionScope.getValue("dataInizioValidita"));
			strDataFineValidita = Util.date2strdate(actionScope.getValue("dataFineValidita"));
			strNumeroPosizioniDebitorie = Util.int2str(actionScope.getValue("numeroPosizioniDebitorie"));
			strTotaleImportoPosizioniDebitorie = Util.bd2str(actionScope.getValue("totaleImportoPosizioniDebitorie"));
			tipoFormato = actionScope.getValue("tipoFormato");
			multibeneficiario = actionScope.getValue("multibeneficiario");


		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return INPUT;
	}

	@Action("inserisci-listadicarico-da-file-step1")
	@Authorization(cdu = "INS_LDC")
	public String inserisciListadicaricoDaFileStep1() {
		String methodName = "inserisciListadicaricodaFileStep1";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		ActionScope actionScope = getSessionContext().getActionScope();
		actionScope.putValue("idCodVersamento", idCodVersamento);
		actionScope.putValue("dataInizioValidita", dataInizioValidita);
		actionScope.putValue("dataFineValidita", dataFineValidita);
		actionScope.putValue("numeroPosizioniDebitorie", numeroPosizioniDebitorie);
		actionScope.putValue("totaleImportoPosizioniDebitorie", totaleImportoPosizioniDebitorie);
		actionScope.putValue("tipoFormato", tipoFormato);

		SessionContext sessionContext = getSessionContext ();
		for ( CodiceVersamentoDto item: sessionContext.getCodiciVersamento () ) {
			if ( item.getId ().equals ( idCodVersamento ) ) {
				Boolean flagMbPrimario = item.getFlagMbPrimario ();;
				if ( null != flagMbPrimario && flagMbPrimario ) {
					actionScope.putValue ( "multibeneficiario", "SI" );
					multibeneficiario = "SI";
				} else {
					actionScope.putValue ( "multibeneficiario", "NO" );
					multibeneficiario = "NO";
				}
				break;
			}
		}
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return SUCCESS;
	}

	@Override
	// prima sono eseguiti controlli sintattici attraverso la validation by annotation, poi si fanno ulteriori controlli sui dati (precedenza date, range, ecc.)
	public void validate() {
		String methodName = "validate";
		

		Set<String> fieldErrorKeys = new TreeSet<>();
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			if (hasFieldErrors()) {
				fieldErrorKeys = getFieldErrors().keySet();
			}

			if (!fieldErrorKeys.contains("strDataInizioValidita") && !fieldErrorKeys.contains("strDataFineValidita")) {
				setDataInizioEFineValiditaAndCheckPrecedenza();
			}
			if (!fieldErrorKeys.contains("strNumeroPosizioniDebitorie")) {
				setNumeroPosizioniDebitorieAndCheckRange();
			}
			if (!fieldErrorKeys.contains("strTotaleImportoPosizioniDebitorie")) {
				setTotaleImportoTotalePosizioni();
			}

			if ( !fieldErrorKeys.contains ( "idCodVersamento" ) ) {
				if ( !this.isCodVersamentoVisibile ( idCodVersamento ) ) {
					addFieldError ( "idCodVersamento", getText ( "campo.nonvalido.codiceversamento" ) );
				}
			}

			if (hasFieldErrors()) {
				addActionMessage(getText("message.valorizzare.parametri"));
			}
			
			List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
	        codiciVersamentoListeDiCarico = new LinkedList<> ();
	        for ( CodiceVersamentoDto cov: listaSenzaFiltriMultibeneficiario ) {
	            if ( cov.getFlagMbSecondario () == null || ( cov.getFlagMbSecondario () != null && !cov.getFlagMbSecondario () ) ) {
	                codiciVersamentoListeDiCarico.add ( cov );
	            }
	        }

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}
	}

	public Integer getIdCodVersamento() {
		return idCodVersamento;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.codiceversamento")
	public void setIdCodVersamento(Integer idCodVersamento) {
		this.idCodVersamento = idCodVersamento;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	private void setDataInizioEFineValiditaAndCheckPrecedenza() throws ParseException {
		if (!isBlank(strDataInizioValidita) && !isBlank(strDataFineValidita)) {
			dataInizioValidita = sdf.parse(strDataInizioValidita);
			dataFineValidita = sdf.parse(strDataFineValidita);
			if (dataInizioValidita.after(dataFineValidita)) {
				addFieldError("strDataInizioValidita", getText("message.verificare.precedenza.date"));
				addFieldError("strDataFineValidita", getText("message.verificare.precedenza.date"));
			}
		}
	}

	public Integer getNumeroPosizioniDebitorie() {
		return numeroPosizioniDebitorie;
	}

	private void setNumeroPosizioniDebitorieAndCheckRange() {
		numeroPosizioniDebitorie = Integer.parseInt(strNumeroPosizioniDebitorie);
		if (numeroPosizioniDebitorie < 1 || numeroPosizioniDebitorie > 1000) {
			addFieldError("strNumeroPosizioniDebitorie", getText("campo.formato.intero.tra.1e1000"));
		}
	}

	public BigDecimal getTotaleImportoPosizioniDebitorie() {
		return totaleImportoPosizioniDebitorie;
	}

	private void setTotaleImportoTotalePosizioni() {
		totaleImportoPosizioniDebitorie = Util.str2importo(strTotaleImportoPosizioniDebitorie);
	}

	public String getStrDataInizioValidita() {
		return strDataInizioValidita;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setStrDataInizioValidita(String strDataInizioValidita) {
		this.strDataInizioValidita = strDataInizioValidita;
	}

	public String getStrDataFineValidita() {
		return strDataFineValidita;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	public void setStrDataFineValidita(String strDataFineValidita) {
		this.strDataFineValidita = strDataFineValidita;
	}

	public String getStrNumeroPosizioniDebitorie() {
		return strNumeroPosizioniDebitorie;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	@RegexFieldValidator(key = "campo.formato.intero.tra.1e1000", regex = "^\\d{1,4}")
	public void setStrNumeroPosizioniDebitorie(String strNumeroPosizioniDebitorie) {
		this.strNumeroPosizioniDebitorie = strNumeroPosizioniDebitorie;
	}

	public String getStrTotaleImportoPosizioniDebitorie() {
		return strTotaleImportoPosizioniDebitorie;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	@RegexFieldValidator(key = "campo.formato.importo", regex = "^\\d{1,9}(,\\d{1,2})?$")
	public void setStrTotaleImportoPosizioniDebitorie(String strTotaleImportoPosizioniDebitorie) {
		this.strTotaleImportoPosizioniDebitorie = strTotaleImportoPosizioniDebitorie;
	}

	public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico () {
		return codiciVersamentoListeDiCarico;
	}

	public void setCodiciVersamentoListeDiCarico ( List<CodiceVersamentoDto> codiciVersamentoListeDiCarico ) {
		this.codiciVersamentoListeDiCarico = codiciVersamentoListeDiCarico;
	}

	public void setDataInizioValidita ( Date dataInizioValidita ) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public void setDataFineValidita ( Date dataFineValidita ) {
		this.dataFineValidita = dataFineValidita;
	}

	public void setNumeroPosizioniDebitorie ( Integer numeroPosizioniDebitorie ) {
		this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
	}

	public void setTotaleImportoPosizioniDebitorie ( BigDecimal totaleImportoPosizioniDebitorie ) {
		this.totaleImportoPosizioniDebitorie = totaleImportoPosizioniDebitorie;
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

}
