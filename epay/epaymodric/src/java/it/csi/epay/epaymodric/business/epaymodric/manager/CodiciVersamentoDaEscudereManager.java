/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

public interface CodiciVersamentoDaEscudereManager {
    
    public List<String> leggiCodiciVersamentoDaEscudere(String idEnte);
}
