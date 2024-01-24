/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTTransazioneMdp
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTTransazioneMdp.class )
public class EpayTTransazioneMdp_ {
	public static volatile SingularAttribute<EpayTTransazioneMdp, String> idTransazione; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> iuv; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> idGateway; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> idPaymentMode; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, Integer> idInformativaPsp; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> identificativoPsp; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> ragioneSocialePsp; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> identificativoCanalePsp; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, String> tipoVersamentoPsp; 
    public static volatile SingularAttribute<EpayTTransazioneMdp, Integer> modelloPagamentoPsp; 
    public static volatile ListAttribute<EpayTTransazioneMdp, EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti;
}
