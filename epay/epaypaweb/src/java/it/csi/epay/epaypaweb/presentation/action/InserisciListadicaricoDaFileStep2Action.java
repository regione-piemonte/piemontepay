/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ParametriInserimentoFlussoDto;
import it.csi.epay.epaypaweb.dto.ParseErrorFieldDto;
import it.csi.epay.epaypaweb.dto.ParseResultDto;
import it.csi.epay.epaypaweb.dto.common.InserisciListadicaricoRequestDto;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
    //@formatter:off
    @Result(name = INPUT, location = "pages/inserisci-listadicarico-da-file-step2.jsp"),
    @Result(name = SUCCESS, location = "pages/inserisci-listadicarico-da-file-step2.jsp")
    //@formatter:on
})
public class InserisciListadicaricoDaFileStep2Action extends InserisciFlussoDaFileStep2Action {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = InserisciListadicaricoDaFileStep2Action.class.getSimpleName ();

    private Integer idCodVersamento;
    private Date dataInizioValidita;
    private Date dataFineValidita;
    private Integer numeroPosizioniDebitorie;
    private BigDecimal totaleImportoPosizioniDebitorie;
    
    private String multibeneficiario;

	private static final String THE_FILE = "Il file ";

	private static final String CLOSE_DD = "</dd>";

	private static final String CLOSE_DT = "</dt>";

	private static final String COLONNA = " COLONNA ";

	private static final String RIGA = "RIGA ";

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Action("entry-inserisci-listadicarico-da-file-step2")
    @Authorization(cdu = "INS_LDC")
    @SkipValidation
    public String entryInserisciListadicaricoDaFileStep2() {
        String methodName = "entryInserisciListadicaricoDaFileStep2";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return INPUT;
    }

    @Override
    public void validate() {
        String methodName = "validate";
        

        super.validate();

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
    }

    @Action("elabora-listadicarico")
    @Authorization(cdu = "INS_LDC")
    //@formatter:off
    public String elaboraListadicarico() {
        String methodName = "elaboraListadicarico";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

		boolean multibeneficiarioPrimario = false;
		String codVersamento = super.getCodVersamento ( idCodVersamento );//TODO
		CodiceVersamentoDto cvd = super.getCov ( idCodVersamento );
		Boolean flagMbPrimario = super.getCov ( idCodVersamento ).getFlagMbPrimario ();
		if ( null != flagMbPrimario && flagMbPrimario ) {
			multibeneficiarioPrimario = true;
		}

        ParametriInserimentoFlussoDto params = new ParametriInserimentoFlussoDto(
        	/* DEV/CSI_PAG-2411 - BEGIN */
        	getIdUtente(),
        	/* DEV/CSI_PAG-2411 - END */
            getCodUtente(),
            getCodFiscaleEnte(),
			codVersamento,
            dataInizioValidita,
            dataFineValidita,
            numeroPosizioniDebitorie,
			totaleImportoPosizioniDebitorie,
			multibeneficiarioPrimario );

        super.elaboraFlussoDaFile(params );

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return SUCCESS;
    }
    //@formatter:on

    //@formatter:off
    @Override
    protected void inserisciFlusso ( ParametriInserimentoFlussoDto params, List<List<Object>> lines, String tipoFormato ) throws BusinessException {
    	
    	InserisciListadicaricoRequestDto inserisciListadicaricoRequestDto = new InserisciListadicaricoRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),params,lines,tipoFormato);
    	
    	List<ParseResultDto> parseResultList = gestioneFlussiBusiness.inserisciListadicarico ( inserisciListadicaricoRequestDto );

		if ( !parseResultList.isEmpty () ) {
            esitoElaborazione = "warning";

            StringBuilder mes = new StringBuilder();
            mes.append("<p>");
            mes.append("Elaborazione del file " + fileFileName + " terminata con errori");
            mes.append("</p>");
            mes.append("<dl>");

            String descrizioneTipoFormatoFile = getDescrizioneTipoFormatoFile();

            for (ParseResultDto parseResult : parseResultList) {
                switch (parseResult.getParseErrorEnum()) {
                case PARSE_ERROR_FILE_VUOTO:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " risulta vuoto" );
					mes.append ( CLOSE_DT );
                    break;
                case PARSE_ERROR_ASSENZA_DATI:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " non contiene nessuna posizione debitoria" );
					mes.append ( CLOSE_DT );
                    break;
                case PARSE_ERROR_NUMERO_POSIZIONI_DEBITORIE:
                    mes.append("<dt>");
                    mes.append("Il parametro " + quote("Numero posizioni debitorie") + " non quadra con il file " + descrizioneTipoFormatoFile);
					mes.append ( CLOSE_DT );
                    mes.append("<dd>");
                    mes.append(parseResult.getParseErrorFieldList().get(0).getFieldName() + ": " + parseResult.getParseErrorFieldList().get(0).getFieldValue());
					mes.append ( CLOSE_DD );
                    break;
                case PARSE_ERROR_ANONIMO_POS_DEBITORIA :
                    mes.append ( "<dt>" );
                    mes.append (
                        "I dati del soggetto debitore non sono congrui a quelli da dover inserire nel caso in cui siano presenti dei riferimenti di pagamento" );
					mes.append ( CLOSE_DT );
                    for ( ParseErrorFieldDto errorField: parseResult.getParseErrorFieldList () ) {
                        mes.append ( "<dd>" );
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
                            + errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_TOTALE_IMPORTO_POSIZIONI_DEBITORIE:
                    mes.append("<dt>");
                    mes.append("Il parametro " + quote("Totale importo posizioni debitorie") + " non quadra con il file " + descrizioneTipoFormatoFile);
					mes.append ( CLOSE_DT );
                    mes.append("<dd>");
                    mes.append(parseResult.getParseErrorFieldList().get(0).getFieldName() + ": " + parseResult.getParseErrorFieldList().get(0).getFieldValue());
					mes.append ( CLOSE_DD );
                    break;
				case PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_SECONDARIE :
					mes.append ( "<dt>" );
					mes.append ( "Il parametro " + quote ( "Totale importo secondario" ) + " non quadra con il file " + descrizioneTipoFormatoFile
						+ " alla riga " + parseResult.getParseErrorFieldList ().get ( 0 ).getFieldRow () );
					mes.append ( CLOSE_DT );
					mes.append ( "<dd>" );
					mes.append ( parseResult.getParseErrorFieldList ().get ( 0 ).getFieldName () + ": "
						+ parseResult.getParseErrorFieldList ().get ( 0 ).getFieldValue () );
					mes.append ( CLOSE_DD );
					break;
				case PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_PRINCIPALI_E_SECONDARIE :
					mes.append ( "<dt>" );
					mes.append ( "Il parametro " + quote ( "Totale importo secondario e importo principale" ) + " non quadra con il file "
						+ descrizioneTipoFormatoFile + " alla riga " + parseResult.getParseErrorFieldList ().get ( 0 ).getFieldRow () );
					mes.append ( CLOSE_DT );
					mes.append ( "<dd>" );
					mes.append ( parseResult.getParseErrorFieldList ().get ( 0 ).getFieldName () + ": "
						+ parseResult.getParseErrorFieldList ().get ( 0 ).getFieldValue () );
					mes.append ( CLOSE_DD );
					break;
                case PARSE_ERROR_INTESTAZIONE:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " ha una intestazione con numero di colonne diverso da quello atteso" );
					mes.append ( CLOSE_DT );
                    break;
                case PARSE_ERROR_NUMERO_COLONNE:
                    List<ParseErrorFieldDto> parseErrorFieldList = parseResult.getParseErrorFieldList();
                    if (parseErrorFieldList.size() == 1 && parseErrorFieldList.get(0).getFieldRow() == 0) {
                        mes.append("<dt>");
						mes.append ( THE_FILE + descrizioneTipoFormatoFile + " ha un numero di colonne diverso da quello atteso" );
						mes.append ( CLOSE_DT );
                    } else {
                        mes.append("<dt>");
						mes.append ( THE_FILE + descrizioneTipoFormatoFile + " ha un numero di colonne diverso da quello atteso "
							+ ( parseErrorFieldList.size () == 1 ? " alla riga" : " alle righe" ) + ":" );
						mes.append ( CLOSE_DT );
                        for (ParseErrorFieldDto errorField : parseErrorFieldList) {
                            mes.append("<dd>");
							mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) );
							mes.append ( CLOSE_DD );
                        }
                    }
                    break;
                case PARSE_ERROR_MIN_MAX_LENGTH:
                    mes.append("<dt>");
                    mes.append("Il file Excel ha dei campi con valori o dimensioni maggiori di quelli attesi");
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
							+ errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_DATE:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " ha dei campi formato " + quote ( "Data" ) + " errati" );
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
							+ errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_TYPE_OR_FORMAT:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " contiene dei valori errati (ad esempio lettere in un numerico)" );
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
							+ errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_MANDATORY:
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " non ha tutti i dati obbligatori" );
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append (
							RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - " + errorField.getFieldName () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_TIPO_SOGGETTO_DEBITORE:
                    final String COMMA = ", ";
                    StringBuffer tipologiaSoggettoIds = new StringBuffer();
                    for (TipologiaSoggettoEnum tipologiaSoggettoEnum : TipologiaSoggettoEnum.values()) {
                        tipologiaSoggettoIds.append(quote(tipologiaSoggettoEnum.getId()) + COMMA);
                    }
                    int lastIndexOfCOMMA = tipologiaSoggettoIds.lastIndexOf(COMMA);
                    if (lastIndexOfCOMMA != -1) {
                        tipologiaSoggettoIds.replace(lastIndexOfCOMMA, lastIndexOfCOMMA + COMMA.length(), "");
                    }
                    lastIndexOfCOMMA = tipologiaSoggettoIds.lastIndexOf(COMMA);
                    if (lastIndexOfCOMMA != -1) {
                        tipologiaSoggettoIds.replace(lastIndexOfCOMMA, lastIndexOfCOMMA + COMMA.length(), " e ");
                    }
                    //
                    mes.append("<dt>");
					mes.append ( THE_FILE + descrizioneTipoFormatoFile + " indica una tipologia di soggetto debitore errata. Gli unici valori ammessi sono: " );
                    mes.append(tipologiaSoggettoIds);
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
							+ errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_COGNOME_NOME_SOGGETTO_DEBITORE:
                    mes.append("<dt>");
                    mes.append("I campi del cognome e del nome del soggetto debitore non devono essere valorizzati");
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append (
							RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - " + errorField.getFieldName () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_RAGIONE_SOCIALE_SOGGETTO_DEBITORE:
                    mes.append("<dt>");
                    mes.append("Il campo della ragione sociale del soggetto debitore non deve essere valorizzato");
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append (
							RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - " + errorField.getFieldName () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_EMAIL:
                    mes.append("<dt>");
                    mes.append("Il campo della email debitore contiene un valore che non rispetta il formato di un indirizzo di posta elettronica");
					mes.append ( CLOSE_DT );
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
							+ errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                case PARSE_ERROR_DUPLICATE_POS_DEBITORIA :
                    mes.append ( "<dt>" );
                    mes.append ( "Il campo Posizione debitoria esterna contiene un idetificativo ripetuto" );
					mes.append ( CLOSE_DT );
                    for ( ParseErrorFieldDto errorField: parseResult.getParseErrorFieldList () ) {
                        mes.append ( "<dd>" );
						mes.append ( RIGA + ( 1 + errorField.getFieldRow () ) + COLONNA + ( 1 + errorField.getFieldColumn () ) + " - "
                                        + errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( CLOSE_DD );
                    }
                    break;
                default:
                }
                mes.append("<ul>");
                mes.append("</ul>");
            }
            mes.append("</dl>");

            messaggioEsitoElaborazione = mes.toString();

        } else {
            esitoElaborazione = "success";
            messaggioEsitoElaborazione = "Elaborazione del file " + fileFileName + " terminata con successo";
        }
    }
    //@formatter:on

    private String getCodUtente() {
        return getSessionContext().getUtente().getCod();
    }

    private String getCodFiscaleEnte() {
        return getSessionContext().getEnte().getCodFiscale();
    }

    public Integer getIdCodVersamento() {
        return idCodVersamento;
    }

    public void setIdCodVersamento(Integer idCodVersamento) {
        this.idCodVersamento = idCodVersamento;
    }

    public Date getDataInizioValidita() {
        return dataInizioValidita;
    }

    public void setDataInizioValidita(Date dataInizioValidita) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita() {
        return dataFineValidita;
    }

    public void setDataFineValidita(Date dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }

    public Integer getNumeroPosizioniDebitorie() {
        return numeroPosizioniDebitorie;
    }

    public void setNumeroPosizioniDebitorie(Integer numeroPosizioniDebitorie) {
        this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
    }

    public BigDecimal getTotaleImportoPosizioniDebitorie() {
        return totaleImportoPosizioniDebitorie;
    }

    public void setTotaleImportoPosizioniDebitorie(BigDecimal totaleImportoPosizioniDebitorie) {
        this.totaleImportoPosizioniDebitorie = totaleImportoPosizioniDebitorie;
    }

    
    /**
     * @return the multibeneficiario
     */
    public String getMultibeneficiario () {
        return multibeneficiario;
    }

    
    /**
     * @param multibeneficiario the multibeneficiario to set
     */
    public void setMultibeneficiario ( String multibeneficiario ) {
        this.multibeneficiario = multibeneficiario;
    }
    
    

}
