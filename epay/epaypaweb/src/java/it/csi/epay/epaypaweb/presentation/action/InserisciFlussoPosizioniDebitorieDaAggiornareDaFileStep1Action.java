/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

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

import it.csi.epay.epaypaweb.util.Util;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = INPUT, location = "pages/inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1.jsp"),
	@Result(name = SUCCESS, location = "pages/inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step2.jsp")
//@formatter:on
})
public class InserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1Action extends InserisciFlussoDaFileStep1Action {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = InserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1Action.class.getSimpleName ();

	private Integer idCodVersamento;
	private Integer numeroPosizioniDebitorie;

	// campi usati per la validazione
	private String strNumeroPosizioniDebitorie;

	private List<CodiceVersamentoDto> codiciVersamentoListeDiCarico;

	private static final String SI = "SI";

	private static final String NO = "NO";

	private String multibeneficiario = NO;

	@Action("entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1")
	@Authorization(cdu = "INS_AGPD")
	@SkipValidation
	public String entryInserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1() {
		String methodName = "entryInserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		List<CodiceVersamentoDto> listaSenzaFiltriMultibeneficiario = getSessionContext ().getCodiciVersamentoListeDiCarico ();
		codiciVersamentoListeDiCarico = new LinkedList<>();
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
			strNumeroPosizioniDebitorie = Util.int2str(actionScope.getValue("numeroPosizioniDebitorie"));
			tipoFormato = actionScope.getValue("tipoFormato");
		}

		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return INPUT;
	}

	@Action("inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1")
	@Authorization(cdu = "INS_AGPD")
	public String inserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1() {
		String methodName = "inserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep1";
		
		log.info ( CLASSNAME + " " + methodName + " - START" );

		ActionScope actionScope = getSessionContext().getActionScope();
		actionScope.putValue("idCodVersamento", idCodVersamento);
		actionScope.putValue("numeroPosizioniDebitorie", numeroPosizioniDebitorie);
		actionScope.putValue("tipoFormato", tipoFormato);

		Boolean flagMbPrimario = super.getCov ( idCodVersamento ).getFlagMbPrimario ();
		if ( null != flagMbPrimario && flagMbPrimario ) {
			actionScope.putValue ( "multibeneficiario", SI );
			multibeneficiario = SI;
		} else {
			actionScope.putValue ( "multibeneficiario", NO );
			multibeneficiario = NO;
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

			if (!fieldErrorKeys.contains("strNumeroPosizioniDebitorie")) {
                setNumeroPosizioniDebitorieAndCheckRange();
            }

            if ( !fieldErrorKeys.contains ( "idCodVersamento" ) ) {
                if ( !this.isCodVersamentoVisibile ( idCodVersamento ) ) {
                    addFieldError ( "idCodVersamento", getText ( "campo.nonvalido.codiceversamento" ) );
                }
            }

			if (hasFieldErrors()) {
				addActionMessage(getText("message.valorizzare.parametri"));
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

	public Integer getNumeroPosizioniDebitorie() {
		return numeroPosizioniDebitorie;
	}

	private void setNumeroPosizioniDebitorieAndCheckRange() {
		numeroPosizioniDebitorie = Integer.parseInt(strNumeroPosizioniDebitorie);
		if (numeroPosizioniDebitorie < 1 || numeroPosizioniDebitorie > 1000) {
			addFieldError("strNumeroPosizioniDebitorie", getText("campo.formato.intero.tra.1e1000"));
		}
	}

	public String getStrNumeroPosizioniDebitorie() {
		return strNumeroPosizioniDebitorie;
	}

	@RequiredStringValidator(key = "campo.obbligatorio")
	@RegexFieldValidator(key = "campo.formato.intero.tra.1e1000", regex = "^\\d{1,4}")
	public void setStrNumeroPosizioniDebitorie(String strNumeroPosizioniDebitorie) {
		this.strNumeroPosizioniDebitorie = strNumeroPosizioniDebitorie;
	}

	public List<CodiceVersamentoDto> getCodiciVersamentoListeDiCarico () {
		return codiciVersamentoListeDiCarico;
	}

	public void setCodiciVersamentoListeDiCarico ( List<CodiceVersamentoDto> codiciVersamentoListeDiCarico ) {
		this.codiciVersamentoListeDiCarico = codiciVersamentoListeDiCarico;
	}

	public String getMultibeneficiario () {
		return multibeneficiario;
	}

	public void setMultibeneficiario ( String multibeneficiario ) {
		this.multibeneficiario = multibeneficiario;
	}

}
