/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Catalogo;

public interface SchedulazioneManager {
    
    public List<Catalogo> leggiSchedulazione( String idEnte );
    
    public List<String> verificaAttivazioneSchedulazione( String idEnte );
    
    
 
}
