/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.FlussoRendicontazione;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciFlussoRiversamentoDAO extends BaseDAO<FlussoRendicontazione> {

	public  InserisciFlussoRiversamentoDAO(FlussoRendicontazione flusso) throws SerialException, SQLException {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				flusso.getId(),
				flusso.getIdentificativoPSP(), 
				flusso.getIdentificativoFlusso(), 
				flusso.getVersioneOggetto(), 
				flusso.getIdentificativoUnivocoRegolamento(), 
				flusso.getIdentificativoIstitutoMittente(), 
				flusso.getIdentificativoIstitutoRicevente(), 
				flusso.getNumeroTotalePagamenti(), 
				flusso.getImportoTotalePagamenti(), 
				flusso.getDataOraFlusso(), 
				flusso.getDataRegolamento(), 
				flusso.getXml(),
				flusso.getDenominazioneMittente(),
				flusso.getDenominazioneRicevente(),
				flusso.getCodiceBicBancaDiRiversamento(),
				flusso.getStatoInvioFlussoBase (),
				flusso.getStatoInvioFlussoEsteso ()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO flusso_riversamento(id, identificativoPsp, identificativoFlusso, versioneOggetto, identificativoUnivocoRegolamento, identificativoIstitutoMittente, identificativoIstitutoRicevente, numeroTotalePagamenti, importoTotalePagamenti, dataOraFlusso, dataRegolamento, dataInserimento, dataModifica, xmlFlusso, denominazionemittente, denominazionericevente, codiceBicBancaDiRiversamento, stato_invio_flusso_base, stato_invio_flusso_esteso )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?, ?, ?, ?, ?)";
	}

}
