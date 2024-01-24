/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.333+0200")
@StaticMetamodel(CblRStatoFlussoErrore.class)
public class CblRStatoFlussoErrore_ {
	public static volatile SingularAttribute<CblRStatoFlussoErrore, Long> id;
	public static volatile SingularAttribute<CblRStatoFlussoErrore, Timestamp> dataAggiornamentoStato;
	public static volatile SingularAttribute<CblRStatoFlussoErrore, String> descrizioneAggiuntivaErrore;
	public static volatile SingularAttribute<CblRStatoFlussoErrore, CblDErrore> cblDErrore;
	public static volatile SingularAttribute<CblRStatoFlussoErrore, CblTFlussoOrigine> cblTFlussoOrigine;
	public static volatile ListAttribute<CblRStatoFlussoErrore, CblTFlussoSintesi> cblTFlussoSintesis;
}
