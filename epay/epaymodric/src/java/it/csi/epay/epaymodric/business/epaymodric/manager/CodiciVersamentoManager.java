/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaCodiciVersamento;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsCodiciVersamento;

public interface CodiciVersamentoManager {
    
    /**
     * cerca tutti i codici versamento presenti sul DB e specificati in input
     * 
     * @param listaCodiciVersamento
     * @return 
     */
    public DTOOutputWsCodiciVersamento ricercaCodiciVersamentoPerCodice(DTOInputWsRicercaCodiciVersamento input);

	public List<DTOOutputWsCodiciVersamento> ricercaCodiciVersamento (
			DTOInputWsRicercaCodiciVersamento ricercaCodiciVersamento );
}
