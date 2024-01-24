/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTAnagrafica
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTAnagrafica.class )
public class EpayTAnagrafica_ {
	public static volatile SingularAttribute<EpayTAnagrafica, Long> idAnagrafica; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> nome; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> cognome; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> ragioneSociale; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> indirizzo; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> email; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> telefono; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> cellulare; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> civico; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> cap; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> localita; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> provincia; 
    public static volatile SingularAttribute<EpayTAnagrafica, String> nazione; 
    public static volatile SingularAttribute<EpayTAnagrafica, Boolean> flagPersonaFisica; 
    public static volatile ListAttribute<EpayTAnagrafica, EpayTPagamento> listOfEpayTPagamento;
    public static volatile ListAttribute<EpayTAnagrafica, EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti;
}
