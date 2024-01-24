/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.ParametriInserimentoFlussoDto;
import it.csi.epay.epaypaweb.dto.ParseErrorFieldDto;
import it.csi.epay.epaypaweb.dto.ParseResultDto;
import it.csi.epay.epaypaweb.enumeration.TipoAggiornamentoPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.enumeration.TipologiaSoggettoEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;

import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.ejb.EJB;
import java.util.List;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.util.Util.quote;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
    //@formatter:off
    @Result(name = INPUT, location = "pages/inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step2.jsp"),
    @Result(name = SUCCESS, location = "pages/inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step2.jsp")
    //@formatter:on
})
public class InserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep2Action extends InserisciFlussoDaFileStep2Action {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = InserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep2Action.class.getSimpleName ();

    private Integer idCodVersamento;
    private Integer numeroPosizioniDebitorie;
    private String multibeneficiario;

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Action("entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step2")
    @Authorization(cdu = "INS_AGPD")
    @SkipValidation
    public String entryInserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep2() {
        String methodName = "entryInserisciFlussoPosizioniDebitorieDaAggiornareDaFileStep2";
        
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

    @Action("elabora-flusso-posizionidebitoriedaaggiornare")
    @Authorization(cdu = "INS_AGPD")
    //@formatter:off
    public String elaboraFlussoPosizioniDebitorieDaAggiornare() {
        String methodName = "elaboraFlussoPosizioniDebitorieDaAggiornare";
        
        log.info ( CLASSNAME + " " + methodName + " - START" );

		boolean multibeneficiarioPrimario = false;
		String codVersamento = super.getCodVersamento ( idCodVersamento );
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
            null, // non valorizzato
            null, // non valorizzato
            numeroPosizioniDebitorie,
			null, // non valorizzato
			multibeneficiarioPrimario );

        super.elaboraFlussoDaFile(params );

        log.info ( CLASSNAME + " " + methodName + " - STOP" );
        return SUCCESS;
    }
    //@formatter:on

    //@formatter:off
    @Override
    protected void inserisciFlusso ( ParametriInserimentoFlussoDto params, List<List<Object>> lines, String tipoFormato ) throws BusinessException {
        List<ParseResultDto> parseResultList = gestioneFlussiBusiness.inserisciFlussoPosizioniDebitorieDaAggiornare(params, lines);

        if (parseResultList.size() > 0) {
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
                    mes.append("Il file " + descrizioneTipoFormatoFile + " risulta vuoto");
                    mes.append("</dt>");
                    break;
                case PARSE_ERROR_ASSENZA_DATI:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " non contiene nessuna posizione debitoria");
                    mes.append("</dt>");
                    break;
                case PARSE_ERROR_NUMERO_POSIZIONI_DEBITORIE:
                    mes.append("<dt>");
                    mes.append("Il parametro " + quote("Numero posizioni debitorie") + " non quadra con il file " + descrizioneTipoFormatoFile);
                    mes.append("</dt>");
                    mes.append("<dd>");
                    mes.append(parseResult.getParseErrorFieldList().get(0).getFieldName() + ": " + parseResult.getParseErrorFieldList().get(0).getFieldValue());
                    mes.append("</dd>");
                    break;
                case PARSE_ERROR_INTESTAZIONE:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " ha una intestazione con numero di colonne diverso da quello atteso");
                    mes.append("</dt>");
                    break;
                case PARSE_ERROR_NUMERO_COLONNE:
                    List<ParseErrorFieldDto> parseErrorFieldList = parseResult.getParseErrorFieldList();
                    if (parseErrorFieldList.size() == 1 && parseErrorFieldList.get(0).getFieldRow() == 0) {
                        mes.append("<dt>");
                        mes.append("Il file " + descrizioneTipoFormatoFile + " ha un numero di colonne diverso da quello atteso");
                        mes.append("</dt>");
                    } else {
                        mes.append("<dt>");
                        mes.append("Il file " + descrizioneTipoFormatoFile + " ha un numero di colonne diverso da quello atteso " + (parseErrorFieldList.size() == 1 ? " alla riga" : " alle righe") + ":");
                        mes.append("</dt>");
                        for (ParseErrorFieldDto errorField : parseErrorFieldList) {
                            mes.append("<dd>");
                            mes.append("RIGA " + (1 + errorField.getFieldRow()));
                            mes.append("</dd>");
                        }
                    }
                    break;
                case PARSE_ERROR_MIN_MAX_LENGTH:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " ha dei campi con valori o dimensioni maggiori di quelli attesi");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_DATE:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " ha dei campi formato " + quote("Data") + " errati");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_TYPE_OR_FORMAT:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " contiene dei valori errati (ad esempio lettere in un numerico)");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_MANDATORY:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " non ha tutti i dati obbligatori");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_FIELD_NOT_REQUESTED:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " ha dei valori non coerenti con il tipo aggiornamento indicato");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_AT_LEAST_ONE_FIELD_REQUESTED:
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " ha dei tipi di aggiornamento \"" + TipoAggiornamentoPosizioneDebitoriaEnum.MODIFICA.getId() + "\" senza l'indicazione di nessun valore");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()));
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_TIPO_AGGIORNAMENTO_POSIZIONE_DEBITORIA:
                    final String COMMA = ", ";
                    StringBuffer posizioneDaAggiornareIds = new StringBuffer();
                    for (TipoAggiornamentoPosizioneDebitoriaEnum posizioneDaAggiornare : TipoAggiornamentoPosizioneDebitoriaEnum.values()) {
                        posizioneDaAggiornareIds.append(quote(posizioneDaAggiornare.getId()) + COMMA);
                    }
                    int lastIndexOfCOMMA = posizioneDaAggiornareIds.lastIndexOf(COMMA);
                    if (lastIndexOfCOMMA != -1) {
                        posizioneDaAggiornareIds.replace(lastIndexOfCOMMA, lastIndexOfCOMMA + COMMA.length(), "");
                    }
                    lastIndexOfCOMMA = posizioneDaAggiornareIds.lastIndexOf(COMMA);
                    if (lastIndexOfCOMMA != -1) {
                        posizioneDaAggiornareIds.replace(lastIndexOfCOMMA, lastIndexOfCOMMA + COMMA.length(), " e ");
                    }
                    //
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " indica un tipo di aggiornamento posizione debitoria errato. Gli unici valori ammessi sono: ");
                    mes.append(posizioneDaAggiornareIds);
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                    
                case PARSE_ERROR_TIPO_SOGGETTO_DEBITORE:
                    final String COMMAS = ", ";
                    StringBuffer tipologiaSoggettoIds = new StringBuffer();
                    for (TipologiaSoggettoEnum tipologiaSoggettoEnum : TipologiaSoggettoEnum.values()) {
                        tipologiaSoggettoIds.append(quote(tipologiaSoggettoEnum.getId()) + COMMAS);
                    }
                    int lastIndexOfCOMMAS = tipologiaSoggettoIds.lastIndexOf(COMMAS);
                    if (lastIndexOfCOMMAS != -1) {
                        tipologiaSoggettoIds.replace(lastIndexOfCOMMAS, lastIndexOfCOMMAS + COMMAS.length(), "");
                    }
                    lastIndexOfCOMMAS = tipologiaSoggettoIds.lastIndexOf(COMMAS);
                    if (lastIndexOfCOMMAS != -1) {
                        tipologiaSoggettoIds.replace(lastIndexOfCOMMAS, lastIndexOfCOMMAS + COMMAS.length(), " e ");
                    }
                    //
                    mes.append("<dt>");
                    mes.append("Il file " + descrizioneTipoFormatoFile + " indica una tipologia di soggetto debitore errata. Gli unici valori ammessi sono: ");
                    mes.append(tipologiaSoggettoIds);
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_COGNOME_NOME_SOGGETTO_DEBITORE:
                    mes.append("<dt>");
                    mes.append("I campi del cognome e del nome del soggetto debitore non devono essere valorizzati");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_RAGIONE_SOCIALE_SOGGETTO_DEBITORE:
                    mes.append("<dt>");
                    mes.append("Il campo della ragione sociale del soggetto debitore non deve essere valorizzato");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName());
                        mes.append("</dd>");
                    }
                    break;
                    
                case PARSE_ERROR_EMAIL:
                    mes.append("<dt>");
                    mes.append("Il campo della email debitore contiene un valore che non rispetta il formato di un indirizzo di posta elettronica");
                    mes.append("</dt>");
                    for (ParseErrorFieldDto errorField : parseResult.getParseErrorFieldList()) {
                        mes.append("<dd>");
                        mes.append("RIGA " + (1 + errorField.getFieldRow()) + " COLONNA " + (1 + errorField.getFieldColumn()) + " - " + errorField.getFieldName() + ": " + errorField.getFieldValue());
                        mes.append("</dd>");
                    }
                    break;
                case PARSE_ERROR_DUPLICATE_POS_DEBITORIA :
                    mes.append ( "<dt>" );
                    mes.append ( "Il campo Posizione debitoria esterna contiene un idetificativo ripetuto" );
                    mes.append ( "</dt>" );
                    for ( ParseErrorFieldDto errorField: parseResult.getParseErrorFieldList () ) {
                        mes.append ( "<dd>" );
                        mes.append ( "RIGA " + ( 1 + errorField.getFieldRow () ) + " COLONNA " + ( 1 + errorField.getFieldColumn () ) + " - "
                                        + errorField.getFieldName () + ": " + errorField.getFieldValue () );
                        mes.append ( "</dd>" );
                    }
                    break;
				case PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_SECONDARIE :
					mes.append ( "<dt>" );
					mes.append ( "Il campo Totale importo posizioni debitorie secondarie contiene un valore non valido con la somma." );
					mes.append ( "</dt>" );
					for ( ParseErrorFieldDto errorField: parseResult.getParseErrorFieldList () ) {
						mes.append ( "<dd>" );
						mes.append ( "RIGA: " + ( 1 + errorField.getFieldRow () ) + " " + errorField.getFieldName () + ": " + errorField.getFieldValue () );
						mes.append ( "</dd>" );
					}
					break;
					
				case PARSE_ERROR_TOTALE_IMPORTO_POSIZIONE_DEBITORIE_PRINCIPALI_E_SECONDARIE :
                    mes.append ( "<dt>" );
                    mes.append ( "Il parametro " + quote ( "Totale importo secondario e importo principale" ) + " non quadra con il file ");
                    mes.append ( "<dd>" );
                    for ( ParseErrorFieldDto errorField: parseResult.getParseErrorFieldList () ) {
                        mes.append ( "<dd>" );
                        mes.append ( "RIGA: " + ( 1 + errorField.getFieldRow () ) + " " + errorField.getFieldName () + ": " + errorField.getFieldValue () );
                        mes.append ( "</dd>" );
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

    public Integer getNumeroPosizioniDebitorie() {
        return numeroPosizioniDebitorie;
    }

    public void setNumeroPosizioniDebitorie(Integer numeroPosizioniDebitorie) {
        this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
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
