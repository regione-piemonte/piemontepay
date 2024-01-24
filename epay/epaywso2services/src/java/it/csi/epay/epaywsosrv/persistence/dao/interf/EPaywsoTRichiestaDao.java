/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.dto.CriterioOrdinamentoDto;
import it.csi.epay.epaywsosrv.dto.PaginazioneDto;
import it.csi.epay.epaywsosrv.dto.RichiestaLightFilterDto;
import it.csi.epay.epaywsosrv.enumeration.ColumnNameRichiestaEnum;
import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiesta;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTRichiestaLight;

//@formatter:off
public interface EPaywsoTRichiestaDao extends EPaywsoDaoBase<Long, EPaywsoTRichiesta> {

	public String findMessageUUIDById(Long id) throws PersistenceException;
	public Long findIdByMessageUUID(String messageUUID) throws PersistenceException;

	public EPaywsoTRichiesta findOneByMessageUUID(String messageUUID) throws PersistenceException;
	public EPaywsoTRichiestaLight findOneByMessageUUIDLight(String messageUUID) throws PersistenceException;

	public Long countAllRichiestaByFilter(RichiestaLightFilterDto filter) throws PersistenceException;
	public List<EPaywsoTRichiestaLight> findAllRichiestaLightByFilter(RichiestaLightFilterDto filter, List<CriterioOrdinamentoDto<ColumnNameRichiestaEnum>> criList, PaginazioneDto pag) throws PersistenceException;

	public List<EPaywsoTRichiesta> findAllRichiesteInErrore(Timestamp fromTime, Timestamp toTime) throws PersistenceException;
	public List<EPaywsoTRichiesta> findAllRichiesteScartate(Timestamp fromTimeIns, Timestamp toTimeIns, Timestamp fromTimeLastExe) throws PersistenceException;
	public List<EPaywsoTRichiesta> findAllRichiesteNonInviate(Timestamp beforeTimeRDE, Timestamp beforeTimeREE) throws PersistenceException;
	
	public List<EPaywsoTRichiesta> findAllRichiesteByMessaggioEnteTipoRichiesta(List<String> idMessaggioList,  Integer idStatoRichiesta) throws PersistenceException;
	
	public List<EPaywsoTRichiesta> findAllRichiesteByMessaggioEnte(List<String> idMessaggioList) throws PersistenceException;
    public Boolean existRichieste ( RichiestaLightFilterDto filter ) throws PersistenceException;

    

}
//@formatter:on
