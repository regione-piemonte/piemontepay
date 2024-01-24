/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-20T11:35:18.341+0100")
@StaticMetamodel(CodiceVersamento.class)
public class CodiceVersamento_ extends AbstractPropagableParentEntity_ {
    public static volatile SingularAttribute<CodiceVersamento, Long> id;
    public static volatile SingularAttribute<CodiceVersamento, String> bic;
    public static volatile SingularAttribute<CodiceVersamento, String> codice;
    public static volatile SingularAttribute<CodiceVersamento, String> descrizione;
    public static volatile SingularAttribute<CodiceVersamento, String> email;
    public static volatile SingularAttribute<CodiceVersamento, String> bicAppoggio;
    public static volatile SingularAttribute<CodiceVersamento, String> ibanAppoggio;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagPresenzaBollettinoPostale;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> ibanAppoggioPostale;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> ibanPostale;
    public static volatile SingularAttribute<CodiceVersamento, ModalitaIntegrazione> modalitaIntegrazione;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagInvioFlussi;
    public static volatile SingularAttribute<CodiceVersamento, String> iban;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagAnnullato;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagMbSecondario;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagMbPrimario;
    public static volatile SingularAttribute<CodiceVersamento, TipoPagamento> tipoPagamento;
    public static volatile SingularAttribute<CodiceVersamento, CodiceVersamento> codiceVersamentoPadre;
    public static volatile ListAttribute<CodiceVersamento, CodiceVersamento> codiciVersamentoCollegati;
    public static volatile SingularAttribute<CodiceVersamento, Ente> ente;
    public static volatile SingularAttribute<CodiceVersamento, VoceEntrata> voceEntrata;
    public static volatile SingularAttribute<CodiceVersamento, StatoMultibeneficiario> statoMultibeneficiario;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> fattura;
    public static volatile ListAttribute<CodiceVersamento, CodiceVersamento> codiciVersamentoSecondariCollegati;
    public static volatile SingularAttribute<CodiceVersamento, Boolean> flagVisualizzaDaSportello;
    public static volatile SingularAttribute<CodiceVersamento, Timestamp> dataInizioValidita;
    public static volatile SingularAttribute<CodiceVersamento, Timestamp> dataFineValidita;
   }

