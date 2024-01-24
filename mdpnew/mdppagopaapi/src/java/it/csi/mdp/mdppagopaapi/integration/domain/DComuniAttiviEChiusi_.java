/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.787+0200")
@StaticMetamodel(DComuniAttiviEChiusi.class)
public class DComuniAttiviEChiusi_ {
	public static volatile SingularAttribute<DComuniAttiviEChiusi, Long> idComune;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, BigDecimal> altitudine;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> attiva;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> cap;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> codCatasto;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, Timestamp> dataUpd;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> descComune;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> descZonaAltimetrica;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> istatComune;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, Long> istatComuneKtl;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> istatProvincia;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> istatRegione;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, String> istatZonaAltimetrica;
	public static volatile SingularAttribute<DComuniAttiviEChiusi, BigDecimal> superficieHm2;
}
