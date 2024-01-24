/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.model.filtrable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.model.DebtPositionDetail;


@JsonInclude ( JsonInclude.Include.NON_NULL )
@JsonTypeName ( "DebtPosition" )
public class XDebtPositionDetail extends DebtPositionDetail {

}
