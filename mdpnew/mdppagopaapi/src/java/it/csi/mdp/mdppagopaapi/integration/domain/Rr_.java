/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.903+0200")
@StaticMetamodel(Rr.class)
public class Rr_ {
	public static volatile SingularAttribute<Rr, Integer> idRr;
	public static volatile SingularAttribute<Rr, String> applicationId;
	public static volatile SingularAttribute<Rr, String> codiceContestoPagamento;
	public static volatile SingularAttribute<Rr, String> codiceIdentificativoUnivocoAttestante;
	public static volatile SingularAttribute<Rr, Timestamp> dataOraMessaggioRevoca;
	public static volatile SingularAttribute<Rr, String> denominazioneIstitutoAttestante;
	public static volatile SingularAttribute<Rr, String> idDominio;
	public static volatile SingularAttribute<Rr, String> identificativoMessaggioRevoca;
	public static volatile SingularAttribute<Rr, BigDecimal> importoTotaleRevocato;
	public static volatile SingularAttribute<Rr, String> iuv;
	public static volatile SingularAttribute<Rr, byte[]> xmlRr;
	public static volatile ListAttribute<Rr, DatiSingolaRevoca> datiSingolaRevocas;
	public static volatile SingularAttribute<Rr, TipoRevoca> tipoRevocaBean;
}
