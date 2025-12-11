/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
/**
 *	Metamodel per la classe EpayTPagamento
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTPagamento.class )
public class EpayTPagamento_ {
    public static volatile SingularAttribute<EpayTPagamento, Long> idPagamento;
    public static volatile SingularAttribute<EpayTPagamento, Date> dataInserimento;
    public static volatile SingularAttribute<EpayTPagamento, String> causale;
    public static volatile SingularAttribute<EpayTPagamento, BigDecimal> importo;
    public static volatile SingularAttribute<EpayTPagamento, String> note;
    public static volatile SingularAttribute<EpayTPagamento, Boolean> consensoPrivacy;
    public static volatile SingularAttribute<EpayTPagamento, Date> inizioValidita;
    public static volatile SingularAttribute<EpayTPagamento, Date> fineValidita;
    public static volatile SingularAttribute<EpayTPagamento, String> iuvNumeroAvviso;
    public static volatile SingularAttribute<EpayTPagamento, String> auxDigit;
    public static volatile SingularAttribute<EpayTPagamento, String> applicationCode;
    public static volatile SingularAttribute<EpayTPagamento, String> codicePagamentoRifEnte;
    public static volatile SingularAttribute<EpayTPagamento, Integer> annoRiferimento;
    public static volatile SingularAttribute<EpayTPagamento, Date> dataScadenza;
    public static volatile SingularAttribute<EpayTPagamento, String> numeroRata;
    public static volatile SingularAttribute<EpayTPagamento, Boolean> pagamentoSpontaneo;
    public static volatile SingularAttribute<EpayTPagamento, Boolean> flagInviato;
    public static volatile SingularAttribute<EpayTPagamento, String> utenteUltimaModifica;
    public static volatile SingularAttribute<EpayTPagamento, Boolean> flagPagamentoAutenticato;

    public static volatile SingularAttribute<EpayTPagamento, BigDecimal> importoPrincipale;

    public static volatile SingularAttribute<EpayTPagamento, String> identificativoDominio;

    public static volatile ListAttribute<EpayTPagamento, EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti;
    public static volatile ListAttribute<EpayTPagamento, EpayTDatiSingoliVersamenti> listOfEpayTDatiSingoliVersamenti;
    public static volatile SingularAttribute<EpayTPagamento, EpayDStatoPagamento> epayDStatoPagamento;
    public static volatile ListAttribute<EpayTPagamento, EpayTPagamentoRiferimenti> listOfEpayTPagamentoRiferimenti;
    public static volatile ListAttribute<EpayTPagamento, EpayTRegistroElaborazioni> listOfEpayTRegistroElaborazioni;
    public static volatile SingularAttribute<EpayTPagamento, EpayTTipoPagamento> epayTTipoPagamento;
    public static volatile SingularAttribute<EpayTPagamento, EpayTAnagrafica> epayTAnagrafica;
    public static volatile ListAttribute<EpayTPagamento, EpayTPagamentoComponenti> listOfEpayTPagamentoComponenti;
}
