/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTRegistroVersamenti
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTRegistroVersamenti.class )
public class EpayTRegistroVersamenti_ {
	public static volatile SingularAttribute<EpayTRegistroVersamenti, Long> idRegistro; 
    public static volatile SingularAttribute<EpayTRegistroVersamenti, Date> dataOperazione; 
    public static volatile SingularAttribute<EpayTRegistroVersamenti, String> iuv; 
    public static volatile SingularAttribute<EpayTRegistroVersamenti, String> descEsitoPagamento; 
    public static volatile SingularAttribute<EpayTRegistroVersamenti, String> origineInserimento; 
    public static volatile ListAttribute<EpayTRegistroVersamenti, EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti;
	public static volatile SingularAttribute<EpayTRegistroVersamenti, EpayTTransazioneMdp> epayTTransazioneMdp;
	public static volatile SingularAttribute<EpayTRegistroVersamenti, EpayTPagamento> epayTPagamento;
	public static volatile SingularAttribute<EpayTRegistroVersamenti, EpayDStatoPagamento> epayDStatoPagamento;
	public static volatile SingularAttribute<EpayTRegistroVersamenti, EpayTAnagrafica> epayTAnagrafica;
    public static volatile ListAttribute<EpayTRegistroVersamenti, EpayTRt> listOfEpayTRt;
}
