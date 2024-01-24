/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.KeyValue;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class EstraiListaTipoVersamentoNotSupportedDAO extends BaseDAO<List<KeyValue>> {
	 LogUtil log = new LogUtil(EstraiListaTipoVersamentoNotSupportedDAO.class);
	
	public  EstraiListaTipoVersamentoNotSupportedDAO() throws SerialException, SQLException {
		setResultSetExtractor(new ListaTipoVersamento());
	}

	@Override
	public String componiQuery() {
		return "select key,value  from config where key Like'%mdpetl.informativapsp.notsupported%' ";
	}

	
	public static void main(String[] args){
		String s = "pippo.pluto.paperino";
		System.out.println(s.substring(s.lastIndexOf(".")+1, s.length()));
	}
	
}

class ListaTipoVersamento implements ResultSetExtractor<List<KeyValue>> {
	
	public List<KeyValue> extractData(ResultSet rs) throws SQLException {
		List<KeyValue> lista = new ArrayList<KeyValue>();
		KeyValue kv = null;
		while(rs.next()){
			kv = new KeyValue();
			String key = rs.getString("key");
			kv.setKey(key.substring(key.lastIndexOf(".")+1, key.length()));	
			kv.setValue(rs.getString("value"));	
			lista.add(kv);
		}
		return lista;
	}
	
}

