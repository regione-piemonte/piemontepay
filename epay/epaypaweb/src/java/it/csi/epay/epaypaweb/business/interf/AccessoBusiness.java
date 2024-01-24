/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneEpayPaCatalogSrvDto;
import it.csi.epay.epaypaweb.dto.ProfiloDto;
import it.csi.epay.epaypaweb.dto.RuoloDto;
import it.csi.epay.epaypaweb.dto.StatoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.exception.BusinessException;

//@formatter:off
/** logica di business */
@Local
public interface AccessoBusiness {

    //	public UtenteDto getUtente(String codUtente) throws BusinessException;
    public RuoloDto getRuolo(Integer idRuolo) throws BusinessException;
    public ProfiloDto getProfilo(Integer idProfilo) throws BusinessException;

    public EnteDto getEnte ( Integer idEnte, Collection<EnteDto> entiCaricati ) throws BusinessException;

    public List<EnteDto> getEnteList ( String endpoint, String codUtente ) throws BusinessException;
    public List<RuoloDto> getRuoloList(Long idUtente, Integer idEnte) throws BusinessException;
    public List<ProfiloDto> getProfiloList(Long idUtente, Integer idEnte) throws BusinessException;
    public List<CduDto> getCduList(Integer idRuolo) throws BusinessException;
    public List<CodiceVersamentoDto> getCodiceVersamentoList(Integer idProfilo, Integer idEnte) throws BusinessException;
    public List<TipoFlussoDto> getTipoFlussoList() throws BusinessException;
    public List<StatoFlussoDto> getStatoFlussoList() throws BusinessException;
    public List<TipoFormatoFileDto> getTipoFormatoFileList() throws BusinessException;

    public ProfilazioneEpayPaCatalogSrvDto getProfilazione ( String endpoint, String codUtente, String codEnte ) throws BusinessException;
}
//@formatter:on
