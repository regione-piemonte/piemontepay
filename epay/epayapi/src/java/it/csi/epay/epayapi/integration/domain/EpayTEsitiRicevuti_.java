/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 *	Metamodel per la classe EpayTEsitiRicevuti
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTEsitiRicevuti.class )
public class EpayTEsitiRicevuti_ {
	public static volatile SingularAttribute<EpayTEsitiRicevuti, Long> idEsito; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, Integer> idModalitaRicezione; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> idApplicazione; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> idTransazione; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, Date> dataPagamento; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, Integer> codEsitoPagamento; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> descEsitoPagamento; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, BigDecimal> importo; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> identificativoPsp; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> ragioneSocialePsp; 
    public static volatile SingularAttribute<EpayTEsitiRicevuti, String> iur; 
	public static volatile SingularAttribute<EpayTEsitiRicevuti, EpayDModalitaPagamento> epayDModalitaPagamento;
	public static volatile SingularAttribute<EpayTEsitiRicevuti, EpayTRegistroVersamenti> epayTRegistroVersamenti;
}
