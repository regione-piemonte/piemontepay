/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciGiornaleEventiDAO extends BaseDAO<GiornaleEvento> {

	public  InserisciGiornaleEventiDAO(GiornaleEvento evento) throws SerialException, SQLException {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				evento.getDataoraevento(), 
				evento.getIdentificativodominio(), 
				evento.getIuv(), 
				evento.getCodiceContesto(), 
				evento.getIdPsp(), 
				evento.getTipoversamento(), 
				evento.getComponente(), 
				evento.getCategoriaevento(), 
				evento.getTipoevento(), 
				evento.getSottotipoevento(), 
				evento.getIdentificativofruitore(), 
				evento.getIdentificativoerogatore(), 
				evento.getIdentificativostazioneintermediariopa(), 
				evento.getIdIntPsp(), 
				evento.getCanalepagamento(), 
				evento.getParametrispecificiinterfaccia(), 
				evento.getEsito(), 
				evento.getApplicationId(), 
				evento.getTransactionId()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO gde(  insert_date,  last_update,  dataoraevento,  identificativodominio,  iuv,  codice_contesto,  id_psp,  tipoversamento,  componente,  categoriaevento,  tipoevento,  sottotipoevento,  identificativofruitore,  identificativoerogatore,  identificativostazioneintermediariopa,  id_int_psp,  canalepagamento,  parametrispecificiinterfaccia,  esito,  application_id,  transactionid)VALUES ( now(),  now(),  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?);";
	}

}
