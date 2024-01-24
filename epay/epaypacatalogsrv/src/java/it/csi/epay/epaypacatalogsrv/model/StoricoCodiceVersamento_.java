/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-01-15T12:44:19.706+0100")
@StaticMetamodel(StoricoCodiceVersamento.class)
public class StoricoCodiceVersamento_ extends AbstractPropagableParentEntity_ {
	public static volatile SingularAttribute<StoricoCodiceVersamento, Long> id;
	public static volatile SingularAttribute<StoricoCodiceVersamento, CodiceVersamento> codiceVersamento;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> bic;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> codice;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> descrizione;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> email;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> flagInvioFlussi;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> iban;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> flagAnnullato;
	public static volatile SingularAttribute<StoricoCodiceVersamento, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Ente> ente;
	public static volatile SingularAttribute<StoricoCodiceVersamento, VoceEntrata> voceEntrata;
	public static volatile SingularAttribute<StoricoCodiceVersamento, StatoMultibeneficiario> statoMultibeneficiario;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> bicAppoggio;
	public static volatile SingularAttribute<StoricoCodiceVersamento, String> ibanAppoggio;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> flagPresenzaBollettinoPostale;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> ibanAppoggioPostale;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> ibanPostale;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> flagMbSecondario;
	public static volatile SingularAttribute<StoricoCodiceVersamento, Boolean> flagMbPrimario;
	public static volatile SingularAttribute<StoricoCodiceVersamento, ModalitaIntegrazione> modalitaIntegrazione;
}

