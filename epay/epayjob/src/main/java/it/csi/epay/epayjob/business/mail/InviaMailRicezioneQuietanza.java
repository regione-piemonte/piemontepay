/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business.mail;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.model.ErData;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.Attached;
import it.csi.epay.epayservices.model.EsitiRicevuti;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epay.epayservices.model.ParamNameXPdf;
import it.csi.epay.epayservices.model.Parametro;

public class InviaMailRicezioneQuietanza {

    private static LogUtil log;

    private Properties properties;
    private JobFacade jobFacade;
    private MailFacade mailFacade;

    public InviaMailRicezioneQuietanza(JobFacade jobFacade, MailFacade mailFacade) {
        log = new LogUtil(this.getClass());
        this.jobFacade = jobFacade;
        this.mailFacade = mailFacade;
        this.properties = new Properties ();

        List<Parametro> listaParametri = this.jobFacade.elencoParametriPerGruppo ( "job_generaPdfReceipt" );

        for (Parametro parametro : listaParametri) {
            properties.put(parametro.getCodice(), parametro.getValore());
        }
    }

    public void execute ( ErData erData ) throws NamingException {
        final String methodName = "execute";
        log.info(methodName, "Executed  at " + new Date());

        Map<ParamNameXPdf, Object> param = erData.getParam ();
        EsitiRicevuti er = erData.getEr ();

        try {
            String iuv = (String)param.get(ParamNameXPdf.C5_IUV);
            String esitoPagamento = (String) param.get ( ParamNameXPdf.E5_ESITO_SINGOLO_PAGAMENTO );
            String ente = (String)param.get(ParamNameXPdf.C1_DESCRIZIONE_ENTE);
            String cfEnte = (String)param.get(ParamNameXPdf.C2_CF_ENTE);

            String tipoPagamento = (String)param.get(ParamNameXPdf.B2_DESCRIZIONE_TASSA);
            String idTransazione = (String)param.get(ParamNameXPdf.E1_ID_TRANSAZIONE);
            String codiceAvviso = (String)param.get(ParamNameXPdf.C4_CODICE_AVVISO);
            String iuvTransazione = er.getIuv ();
            String ragioneSocialePSP = (String)param.get(ParamNameXPdf.E2_DESCRIZIONE_PRESTATORE);
            String dataOra = (String) param.get ( ParamNameXPdf.E3_DATA_ORA_OPERAZIONE );
            //String dataApplicativa = (String) param.get ( ParamNameXPdf.E3_DATA_APPLICATIVA );
	        String dataEsitoPagamento = (String) param.get ( ParamNameXPdf.E3_DATA_ESITO_PAGAMENTO );
            String importo = ((BigDecimal)param.get(ParamNameXPdf.C3_IMPORTO)).toPlainString();
            //multibeneficiario
            String enteSecondario = (String) param.get ( ParamNameXPdf.F1_DESCRIZIONE_ENTE_SECONDARIO );
            String cfEnteSecondario = null;
            String tipoPagamentoSecondario = null;
            String importoSecondario = null;
            String importoPrincipale = null;
            if ( null != enteSecondario ) {
                cfEnteSecondario = (String) param.get ( ParamNameXPdf.F2_CF_ENTE_SECONDARIO );
                tipoPagamentoSecondario = (String) param.get ( ParamNameXPdf.F3_DESCRIZIONE_TASSA_SECONDARIO );
                importoSecondario = ( (BigDecimal) param.get ( ParamNameXPdf.F4_IMPORTO_SECONDARIO ) ).toPlainString ();
                importoPrincipale = ( (BigDecimal) param.get ( ParamNameXPdf.F4_IMPORTO_PRINCIPALE ) ).toPlainString ();
            }

            StringBuilder sb;
            try {
                sb = new StringBuilder();
                //sb.append(getMessaggio("riga1"));
                sb.append ( getMessaggio ( "riga 2", iuv ) );
                sb.append("\n");
                sb.append(getMessaggio("esito", esitoPagamento));
                sb.append("\n");
                sb.append(getMessaggio("dati"));
                sb.append("\n");
                sb.append(getMessaggio("dati01", ente));
                sb.append(getMessaggio("dati02", cfEnte));
                sb.append("\n");
                sb.append(getMessaggio("dati03", tipoPagamento));
                if ( null != enteSecondario ) {
                    sb.append ( getMessaggio ( "dati04", importoPrincipale ) );
                } else {
                    sb.append ( getMessaggio ( "dati04", importo ) );
                }
                sb.append("\n");
                if ( null != enteSecondario ) {
                    sb.append ( getMessaggio ( "dati05", enteSecondario ) );
                    sb.append ( getMessaggio ( "dati06", cfEnteSecondario ) );
                    sb.append ( "\n" );
                    sb.append ( getMessaggio ( "dati07", tipoPagamentoSecondario ) );
                    sb.append ( getMessaggio ( "dati08", importoSecondario ) );
                    sb.append ( "\n" );
                }
                sb.append ( getMessaggio ( "dati09", idTransazione ) );
                if (StringUtils.isNotBlank(codiceAvviso)) {
                    sb.append ( getMessaggio ( "dati10", codiceAvviso ) );
                }
                sb.append ( getMessaggio ( "dati11", iuvTransazione ) );
                sb.append ( getMessaggio ( "dati12", ragioneSocialePSP ) );
                sb.append("\n");
                if ( null != dataOra ) {
                    sb.append ( getMessaggio ( "dati13", dataOra ) );
                    sb.append ( "\n" );
                }
                if ( null != dataEsitoPagamento ) {
                    sb.append ( getMessaggio ( "dati14", dataEsitoPagamento ) );
                    sb.append ( "\n" );
                }
                sb.append ( getMessaggio ( "dati15", importo ) );
                sb.append ( "\n" );

                sb.append(getMessaggio("saluti"));
                sb.append("\n");
                sb.append(getMessaggio("firma"));
            } catch ( Exception e ) {
                log.error ( methodName, "Errore in fase di creazione del body della email", e );
                throw e;
            }

            Attached mailAttachedPdf = new Attached();
            mailAttachedPdf.setName ( "QUIETANZA_" + iuv + ".pdf" );
            mailAttachedPdf.setType(Attached.MimeType.PDF);
            mailAttachedPdf.setData ( erData.getQuietanza () );

            Mail mail = new Mail();
            mail.setFrom ( "no-reply.piemontepay@csi.it" );
            mail.setTo ( erData.getPagamento ().getPagatore ().getEmail () );
            mail.setSubject(getMessaggio("oggetto",iuv));
            mail.setText(sb.toString());
            mail.getAttachedFiles().add(mailAttachedPdf);


            mailFacade.inviaMail(mail);
        } catch (UnsupportedEncodingException | MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getMessaggio ( String riga, String... parametri ) {
        String row;
        try {
            row = properties.getProperty ( riga );
        } catch ( Exception e ) {
            throw new RuntimeException (
                "Errore reperimento riga : " + riga + " dal template durante la creazione della mail con Quietanza", e );
        }
        if ( null == row ) {
            throw new RuntimeException (
                "Errore reperimento riga : " + riga + " dal template durante la creazione della mail con Quietanza" );
        }
        try {
            int i = 0;
            for ( String parametro: parametri ) {
                row = row.replace ( "$" + i++, parametro );
            }
            return row + "\n";
        } catch ( Exception e ) {
            throw new RuntimeException ( "Errore preparazione valore della riga: " + riga + " della mail con Quietanza", e );
        }
    }
}
