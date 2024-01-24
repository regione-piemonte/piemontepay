/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business.impl;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.csi.mdp.mdppagopaapi.business.MockService;
import it.csi.mdp.mdppagopaapi.util.Constants;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoChiediDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoRiceviEsito;
import it.csi.mdp.mdppagopaapi.util.clientws.EsitoVerificaDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriChiediDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriRiceviEsito;
import it.csi.mdp.mdppagopaapi.util.clientws.ParametriVerificaDatiPagamento;
import it.csi.mdp.mdppagopaapi.util.clientws.TransazioneExtraAttribute;


//CDU di riferimento:EPAY-363-CDU-01-V01-RDI45-paVerifyPayment_bozza (1)_ufficiale.docx


@Service
public class MockServiceImpl implements MockService {

    protected static final Logger logger = LoggerFactory.getLogger ( PaVerifyPaymentNoticeBuisnessServiceImpl.class );


    @Override
    public void esempioServzioMock() {
        throw new UnsupportedOperationException ();
    }



    @Override
    public EsitoRiceviEsito RiceviEsito ( ParametriRiceviEsito parametri ) {
        EsitoRiceviEsito esito = new EsitoRiceviEsito ();
        try {
            assertNotNull ( parametri.getApplicationId () );
            assertNotNull ( parametri.getIuv () );
            assertNotNull ( parametri.getMac () );
            assertNotNull ( parametri.getTimestamp () );
            assertNotNull ( parametri.getTransactionId () );
            assertNotNull ( parametri.getCodEsitoPagamento () );
            assertNotNull ( parametri.getDataEsitoSingoloPagamento () );
            assertNotNull ( parametri.getDataOraMsgRicevuta () );
            assertNotNull ( parametri.getDenominazionePSP () );
            assertNotNull ( parametri.getDescEsitoPagamento () );
            assertNotNull ( parametri.getElencoParametriAggiuntivi () );
            assertNotNull ( parametri.getIdentificativoPSP () );
            assertNotNull ( parametri.getIdentificativoUnivocoRiscossione () );
            assertNotNull ( parametri.getImportoPagato () );
        } catch ( Exception e ) {
            esito.setEsito ( Constants.KO );
            esito.setMessaggioErrore ( "Parametri di input mancanti" );
            return esito;
        }

        esito.setEsito ( Constants.OK );

        return esito;
    }

    @Override
    public EsitoChiediDatiPagamento chiediDatiPagamento ( String endPointFruitore, ParametriChiediDatiPagamento parametri ) {

        EsitoChiediDatiPagamento esito = new EsitoChiediDatiPagamento ();

        try {
            assertNotNull ( parametri.getImportoVersamento () );
            assertNotNull ( parametri.getIuv () );
            assertNotNull ( parametri.getMac () );
            assertNotNull ( parametri.getTimestamp () );
            assertNotNull ( parametri.getTransactionId () );
        } catch ( Exception e ) {
            esito.setEsito ( Constants.KO );
            esito.setMessaggioErrore ( "Parametri di input mancanti" );
            return esito;
        }

        TransazioneExtraAttribute tea;

        esito.setEsito ( Constants.OK );
        esito.setMac ( parametri.getMac () );
        esito.setTimestamp ( parametri.getTimestamp () );

        List<TransazioneExtraAttribute> teaList = new ArrayList<TransazioneExtraAttribute> ();

        tea = new TransazioneExtraAttribute();
        tea.setExtraAttributeId ( 0 );
        tea.setName ( "autenticazioneSoggetto" );
        tea.setValue ( Constants.NOT_AVAILABLE );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 1 );
        tea.setName ( "tipoIdentificativoUnivocoPagatore" );
        tea.setValue ( "F" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 2 );
        tea.setName ( "codiceIdentificativoUnivocoPagatore" );
        tea.setValue ( "BSOSFN41T26B236I" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 3 );
        tea.setName ( "anagraficaPagatore" );
        tea.setValue ( "AZ. AGRICOLA" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 4 );
        tea.setName ( "mailPagatore" );
        tea.setValue ( "bosio.stefano@cia.legalmail.it" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 5 );
        tea.setName ( "identificativoUnivocoVersamento" );
        tea.setValue ( "22200760521202287" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 6 );
        tea.setName ( "importoTotaleDaVersare" );
        tea.setValue ( "50.78" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 7 );
        tea.setName ( "causaleVersamento" );
        tea.setValue ( "/RFB/22200760521202287/50.78/Tariffa fitosanitaria annuale SCADUTA - sanzione" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 8 );
        tea.setName ( "datiSpecificiRiscossione" );
        tea.setValue ( "sdgadgasga" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 9 );
        tea.setName ( "dueDate" );
        tea.setValue ( "2019-09-06" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 10 );
        tea.setName ( "multibeneficiario" );
        tea.setValue ( "true" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );
        //
        //        // multibeneficiario false
        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 11 );
        tea.setName ( "datiMultiversamento" );
        // multibeneficiario false
        //        tea.setValue (
        //            "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8bnMyOkVsZW5jb0RhdGlWZXJzYW1lbnRvIHhtbG5zOm5zMj0id3d3LmNzaS5pdC9tZHAiPgogICAgPGRhdGlTaW5nb2xvVmVyc2FtZW50bz4KICAgICAgICA8aW1wb3J0b1NpbmdvbG9WZXJzYW1lbnRvPjQ1PC9pbXBvcnRvU2luZ29sb1ZlcnNhbWVudG8+CiAgICAgICAgPGNhdXNhbGVWZXJzYW1lbnRvPi9SRkIvMjIyMDA3NjA1MjEyMDIyODcvNTAuNzgvVGFyaWZmYSBmaXRvc2FuaXRhcmlhIGFubnVhbGUgU0NBRFVUQSAtIHNhbnppb25lL1RYVC80NS4wMC9CYXNlPC9jYXVzYWxlVmVyc2FtZW50bz4KICAgICAgICA8ZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPjkvMDAwPC9kYXRpU3BlY2lmaWNpUmlzY29zc2lvbmU+CiAgICA8L2RhdGlTaW5nb2xvVmVyc2FtZW50bz4KICAgIDxkYXRpU2luZ29sb1ZlcnNhbWVudG8+CiAgICAgICAgPGltcG9ydG9TaW5nb2xvVmVyc2FtZW50bz41PC9pbXBvcnRvU2luZ29sb1ZlcnNhbWVudG8+CiAgICAgICAgPGNhdXNhbGVWZXJzYW1lbnRvPi9SRkIvMjIyMDA3NjA1MjEyMDIyODcvNTAuNzgvVGFyaWZmYSBmaXRvc2FuaXRhcmlhIGFubnVhbGUgU0NBRFVUQSAtIHNhbnppb25lL1RYVC80NS4wMC9CYXNlL1RYVC81LjAwL1Nhbnppb25lPC9jYXVzYWxlVmVyc2FtZW50bz4KICAgICAgICA8ZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPjkvMDA1PC9kYXRpU3BlY2lmaWNpUmlzY29zc2lvbmU+CiAgICA8L2RhdGlTaW5nb2xvVmVyc2FtZW50bz4KICAgIDxkYXRpU2luZ29sb1ZlcnNhbWVudG8+CiAgICAgICAgPGltcG9ydG9TaW5nb2xvVmVyc2FtZW50bz4wLjc4PC9pbXBvcnRvU2luZ29sb1ZlcnNhbWVudG8+CiAgICAgICAgPGNhdXNhbGVWZXJzYW1lbnRvPi9SRkIvMjIyMDA3NjA1MjEyMDIyODcvNTAuNzgvVGFyaWZmYSBmaXRvc2FuaXRhcmlhIGFubnVhbGUgU0NBRFVUQSAtIHNhbnppb25lL1RYVC80NS4wMC9CYXNlL1RYVC81LjAwL1Nhbnppb25lL1RYVC8wLjc4L0ludGVyZXNzaTwvY2F1c2FsZVZlcnNhbWVudG8+CiAgICAgICAgPGRhdGlTcGVjaWZpY2lSaXNjb3NzaW9uZT45LzAwNDwvZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPgogICAgPC9kYXRpU2luZ29sb1ZlcnNhbWVudG8+CjwvbnMyOkVsZW5jb0RhdGlWZXJzYW1lbnRvPgo=" );
        //multibeneficiario true
        tea.setValue (
                        "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8bnMyOkVsZW5jb0RhdGlWZXJzYW1lbnRvIHhtbG5zOm5zMj0id3d3LmNzaS5pdC9tZHAiPgoJPGRhdGlTaW5nb2xvVmVyc2FtZW50bz4KCQk8aW1wb3J0b1NpbmdvbG9WZXJzYW1lbnRvPjQ1PC9pbXBvcnRvU2luZ29sb1ZlcnNhbWVudG8+CgkJPGNhdXNhbGVWZXJzYW1lbnRvPi9SRkIvMjIyMDA3NjA1MjEyMDIyODcvNTAuNzgvVGFyaWZmYSBmaXRvc2FuaXRhcmlhIGFubnVhbGUgU0NBRFVUQSAtIHNhbnppb25lL1RYVC80NS4wMC9CYXNlPC9jYXVzYWxlVmVyc2FtZW50bz4KCQk8ZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPjkvMDAwPC9kYXRpU3BlY2lmaWNpUmlzY29zc2lvbmU+CgkJPGFwcGxpY2F0aW9uSWQ+RVBBWV8wMDAwMDAwMDE3MF8xPC9hcHBsaWNhdGlvbklkPgoJPC9kYXRpU2luZ29sb1ZlcnNhbWVudG8+Cgk8ZGF0aVNpbmdvbG9WZXJzYW1lbnRvPgoJCTxpbXBvcnRvU2luZ29sb1ZlcnNhbWVudG8+NTwvaW1wb3J0b1NpbmdvbG9WZXJzYW1lbnRvPgoJCTxjYXVzYWxlVmVyc2FtZW50bz4vUkZCLzIyMjAwNzYwNTIxMjAyMjg3LzUwLjc4L1RhcmlmZmEgZml0b3Nhbml0YXJpYSBhbm51YWxlIFNDQURVVEEgLSBzYW56aW9uZS9UWFQvNDUuMDAvQmFzZS9UWFQvNS4wMC9TYW56aW9uZTwvY2F1c2FsZVZlcnNhbWVudG8+CgkJPGRhdGlTcGVjaWZpY2lSaXNjb3NzaW9uZT45LzAwNTwvZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPgoJCTxhcHBsaWNhdGlvbklkPkVQQVlfMDAwMDAwMDAxNzBfMTwvYXBwbGljYXRpb25JZD4KCTwvZGF0aVNpbmdvbG9WZXJzYW1lbnRvPgoJPGRhdGlTaW5nb2xvVmVyc2FtZW50bz4KCQk8aW1wb3J0b1NpbmdvbG9WZXJzYW1lbnRvPjAuNzg8L2ltcG9ydG9TaW5nb2xvVmVyc2FtZW50bz4KCQk8Y2F1c2FsZVZlcnNhbWVudG8+L1JGQi8yMjIwMDc2MDUyMTIwMjI4Ny81MC43OC9UYXJpZmZhIGZpdG9zYW5pdGFyaWEgYW5udWFsZSBTQ0FEVVRBIC0gc2FuemlvbmUvVFhULzQ1LjAwL0Jhc2UvVFhULzUuMDAvU2FuemlvbmUvVFhULzAuNzgvSW50ZXJlc3NpPC9jYXVzYWxlVmVyc2FtZW50bz4KCQk8ZGF0aVNwZWNpZmljaVJpc2Nvc3Npb25lPjkvMDA0PC9kYXRpU3BlY2lmaWNpUmlzY29zc2lvbmU+CgkJPGFwcGxpY2F0aW9uSWQ+RVBBWV8wMDAwMDAwMDE3MF8xPC9hcHBsaWNhdGlvbklkPgoJPC9kYXRpU2luZ29sb1ZlcnNhbWVudG8+CjwvbnMyOkVsZW5jb0RhdGlWZXJzYW1lbnRvPg==" );
        tea.setTransactionId ( parametri.getTransactionId () );
        teaList.add ( tea );

        // dati aggiuntivi
        // dati aggiuntivi
        tea = new TransazioneExtraAttribute ();
        tea.setExtraAttributeId ( 12 );
        tea.setName ( "datiAggiuntivi" );
        String datiAggiuntiviXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" +
                        "<ns2:Metadata xmlns:ns2=\"www.csi.it/mdp\">\r\n" +
                        "        <mapEntry>          \r\n" +
                        "            <key>numeroVerbale</key>\r\n" +
                        "            <value>1234/2021</value>\r\n" +
                        "        </mapEntry>\r\n" +
                        "        <mapEntry>\r\n" +
                        "            <key>Targa</key>\r\n" +
                        "            <value>AA000BB</value>  \r\n" +
                        "        </mapEntry>\r\n" +
                        "</ns2:Metadata>";
        byte [] encrypted = datiAggiuntiviXML.getBytes ();
        datiAggiuntiviXML = new String ( Base64.encodeBase64 ( encrypted ) );
        tea.setValue ( datiAggiuntiviXML );
        teaList.add ( tea );

        esito.getTea ().clear ();
        esito.getTea ().addAll ( teaList );
        return esito;

    }

    @Override
    public EsitoVerificaDatiPagamento verificaDatiPagamento ( ParametriVerificaDatiPagamento request ) {
        SimpleDateFormat sdfTimestamp = new SimpleDateFormat ( "ddMMyyyy-hh:mm:ss:ms" );
        SimpleDateFormat sdfTDueDate = new SimpleDateFormat ( "yyyy-MM-dd" );
        EsitoVerificaDatiPagamento response = new EsitoVerificaDatiPagamento ();
        response.setEsito ( Constants.OK );
        response.setTimestamp ( sdfTimestamp.format ( new Date () ) );
        response.setImportoDovuto ( BigDecimal.valueOf ( 10000.99 ) );
        response.setIbanAccredito ( "IT89A03085010100001000025873" );
        response.setBicAccredito ( "BIC000" );
        response.setCredenzialiPagatore ( "Credenziali Pagatore" );
        response.setCausaleVersamento ( "Pagamento in unica soluzione per acquisto automobile" );
        response.setMac ( request.getMac () );
//        try {
//            response.setDueDate ( sdfTDueDate.parse ( "2021-12-31" ) );
//        } catch ( Exception e ) {
//            logger.error ( "Errore set DueDate", e );
//        }

        response.setDescrizionePagamento ( "Il pagamento e' stato effettuato con successo" );
        return response;
    }
}
