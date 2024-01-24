/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.PaginazioneDto;
import it.csi.epay.epaypaweb.dto.RichiestaDiRevocaDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamento;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;


public interface EpaypaTRrDao extends EpaypaDaoBase<Long, EpaypaTRr>{
    
    Long countAllByFilter(RichiestaRevocheFilterDto filter, String codUtente) throws PersistenceException;
    public List<EpaypaTRr> findAllLightByFilter(RichiestaRevocheFilterDto filter, PaginazioneDto pag, String codUtente) throws PersistenceException;
    void insertRR ( RichiestaDiRevocaDto richiestaRevoca, EpaypaTTipoRevoca tipoRevocaEntity ) throws PersistenceException;
}
