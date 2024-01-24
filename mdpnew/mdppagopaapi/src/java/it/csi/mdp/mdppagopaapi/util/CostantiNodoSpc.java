/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;

import java.util.HashMap;
import java.util.Map;

public final class CostantiNodoSpc
{

    public static final String TEA_AUTENTICAZIONE_SOGGETTO = "autenticazioneSoggetto";

    public static final String TEA_TIPO_IDENTIFICATIVO_UNIVOCO_PAGATORE = "tipoIdentificativoUnivocoPagatore";

    public static final String TEA_CODICE_IDENTIFICATIVO_UNIVOCO_PAGATORE = "codiceIdentificativoUnivocoPagatore";

    public static final String TEA_ANAGRAFICA_PAGATORE = "anagraficaPagatore";

    public static final String TEA_INDIRIZZO_PAGATORE = "indirizzoPagatore";

    public static final String TEA_CIVICO_PAGATORE = "civicoPagatore";

    public static final String TEA_CAP_PAGATORE = "capPagatore";

    public static final String TEA_LOCALITA_PAGATORE = "localitaPagatore";

    public static final String TEA_PROVINCIA_PAGATORE = "provinciaPagatore";

    public static final String TEA_NAZIONE_PAGATORE = "nazionePagatore";

    public static final String TEA_MAIL_PAGATORE = "mailPagatore";

    public static final String TEA_IDENTIFICATIVO_UNIVOCO_VERSAMENTO = "identificativoUnivocoVersamento";

    public static final String TEA_IMPORTO_TOTALE_DA_VERSARE = "importoTotaleDaVersare";

    public static final String TEA_CAUSALE_VERSAMENTO = "causaleVersamento";

	public static final String TEA_DESCRIZIONE_CAUSALE_VERSAMENTO = "descrizioneCausaleVersamento";

    public static final String TEA_DATI_SPECIFICI_RISCOSSIONE = "datiSpecificiRiscossione";

    public static final String TEA_DUE_DATE = "dueDate";

    public static final String TEA_MULTIBENEFICIARIO = "multibeneficiario";

    public static final String TEA_RETENTION_DATE = "retentionDate";

    public static final String TEA_LAST_PAYMENT = "lastPayment";

    public static final String TEA_MULTIVERSAMENTO = "datiMultiversamento";

    public static final String TEA_DATI_AGGIUNTIVI = "datiAggiuntivi";

    //campi che finiscono nella RPT ma sono forniti nella configurazione
    public static final String APP_PARAM_DATI_SPECIFICI_RISCOSSIONE = "datiSpecificiRiscossione";
    //    public static final String APP_PARAM_NAZIONE_BENEFICIARIO = "nazioneBeneficiario";
    //    public static final String APP_PARAM_PROVINCIA_BENEFICIARIO = "provinciaBeneficiario";
    //    public static final String APP_PARAM_LOCALITA_BENEFICIARIO = "localitaBeneficiario";
    //    public static final String APP_PARAM_CAP_BENEFICIARIO = "capBeneficiario";
    //    public static final String APP_PARAM_CIVICO_BENEFICIARIO = "civicoBeneficiario";
    //    public static final String APP_PARAM_INDIRIZZO_BENEFICIARIO = "indirizzoBeneficiario";
    //    public static final String APP_PARAM_DENOM_UNIT_OPER_BENEFICIARIO = "denomUnitOperBeneficiario";
    //    public static final String APP_PARAM_CODICE_UNIT_OPER_BENEFICIARIO = "codiceUnitOperBeneficiario";
    //    public static final String APP_PARAM_DENOMINAZIONE_BENEFICIARIO = "denominazioneBeneficiario";
    //    public static final String APP_PARAM_CODICE_IDENTIFICATIVO_UNIVOCO_BENEFICIARIO = "codiceIdentificativoUnivocoBeneficiario";
    //    public static final String APP_PARAM_BIC_APPOGGIO = "bicAppoggio";
    //    public static final String APP_PARAM_IBAN_APPOGGIO = "ibanAppoggio";
    //    public static final String APP_PARAM_BIC_ACCREDITO = "bicAccredito";
    //    public static final String APP_PARAM_IBAN_ACCREDITO = "ibanAccredito";
    //    public static final String APP_PARAM_COMMISSIONE_CARICO_PA = "commissioneCaricoPA";
    //
    //
    //    public static final String APP_PARAM_PORTA_DI_DOMINIO = "portaDiDominio";
    //    public static final String APP_PARAM_PASSWORD_DOMINIO_NODO_SPC = "passwordDominioNodoSpc";
    //    public static final String APP_PARAM_IDENTIFICATIVO_STAZIONE_INTERMEDIARIO_PA = "identificativoStazioneIntermediarioPA";
    //    public static final String APP_PARAM_IDENTIFICATIVO_DOMINIO = "identificativoDominio";
    //    public static final String APP_PARAM_IDENTIFICATIVOINTERMEDIARIO_PA = "identificativointermediarioPA";
    //    public static final String APP_PARAM_FIRMA_RT = "firmaRT";
    //    public static final String APP_PARAM_ENDPONTI_SERVIZI_FRUITORE = "endpointServiziNodo";
    //    public static final String APP_PARAM_PASSPHRASE_FRUITORE = "passphrase";
    //
    //    public static final String APP_PARAM_PRIMITIVA = "primitiva";
    //    public static final String APP_PARAM_STORNO_PAGAMENTO = "stornoPagamento";
    //    public static final String APP_PARAM_BOLLO_DIGITALE = "bolloDigitale";
    //    public static final String APP_PARAM_TERZO_MODELLO_PAGAMENTO = "terzoModelloPagamento";
    //    public static final String APP_PARAM_ID_PSP = "idPsp";
    //    public static final String APP_PARAM_TIPO_VERSAMENTO = "tipoVersamento";
    //    public static final String APP_PARAM_URL_WISP_BACK = "urlWispBack";
    //    public static final String APP_PARAM_URL_WISP_RETURN = "urlWispReturn";
    //    public static final String APP_PARAM_CONTO_POSTE = "contoPoste";
    //
    //    public static final String VERSIONE_OGGETTO_RPT = "6.0";


    public static final Map<String, String> mappaCodiciStatoPagamento = new HashMap<String, String> ();

    static {
        mappaCodiciStatoPagamento.put ( "1", "Inviata con OK" );
        mappaCodiciStatoPagamento.put ( "2", "Inviata con KO" );
        mappaCodiciStatoPagamento.put ( "3", "Receipt ricevuta" );
    }
}
