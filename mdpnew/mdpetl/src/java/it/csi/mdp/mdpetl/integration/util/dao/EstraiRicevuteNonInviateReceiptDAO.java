/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.cxf.common.util.CollectionUtils;

import it.csi.mdp.clientmod3.ChiaveValore;
import it.csi.mdp.generatedvo.pagamentimod3.CtMapEntry;
import it.csi.mdp.generatedvo.pagamentimod3.PaSendRTReq;
import it.csi.mdp.mdpetl.dto.CodaInvioReceipt;
import it.csi.mdp.mdpetl.dto.DatiReceiptNonInviata;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.Constants;
import it.csi.mdp.mdpetl.util.LogUtil;
import it.csi.mdp.mdpetl.util.StringUtils;
import it.csi.mdp.mdpetl.util.UtilDate;
import it.csi.mdp.utility.CostantiNodoSpc;


public class EstraiRicevuteNonInviateReceiptDAO extends BaseDAO<List<DatiReceiptNonInviata>> {

	private final LogUtil log = new LogUtil ( EstraiRicevuteNonInviateReceiptDAO.class );

	private Integer limite;

	public EstraiRicevuteNonInviateReceiptDAO ( String limiteRecord, Integer numGiorniKo, byte[] sKey ) throws SQLException {
		if ( null == numGiorniKo ) {
			throw new IllegalArgumentException ( "numGiorniKo null" );
		}
		if ( null == sKey ) {
			throw new IllegalArgumentException ( "Assenza di chiave di decodifica" );
		}

		String flagRetry = StringUtils.encodeStringValue ( "true", sKey );
		if ( StringUtils.isEmpty ( limiteRecord ) ) {

			setStatementMapper ( new it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper ( flagRetry, numGiorniKo ) );
		} else {
			this.limite = new Integer ( limiteRecord );
			setStatementMapper ( new GenericObjectArrayStatementMapper ( flagRetry, numGiorniKo, limite ) );
		}
		setResultSetExtractor ( new DatiReceiptNonInviataExtractor () );// clonare DatiRicevutaNonInviataExtractor sostiturlo con quello nuovo che tira su effettivamente i dati della receipt
	}

	@Override public String componiQuery () {
		String query = "select distinct rice.*, t.amount, coda.contatore_tentativi, "
						+ "coda.transaction_id as coda_transaction_id,  "
						+ "coda.application_id as coda_application_id, "
						+ "coda.data_tentativi, coda.data_inizio_tentativi, "
						+ "coda.num_giorni_tentativi_ko, coda.ultimo_esito_fruitore, "
						+ "coda.data_ultima_modifica, coda.data_inserimento  "
						+ "from receipt_coda_invio coda, "
						+ "mdp_receipt rice, "
						+ "transazione t,"
						+ "applicationcustomfields custom, "
						+ "r_application_enti rae, "
						+ "enti e "
						+ "where coda.iuv = rice.creditor_referenceid "
						+ "and rice.transaction_id = coda.transaction_id "
						+ "and rice.transaction_id = t.transaction_id "
						+ "and rae.application_id = coda.application_id "
						+ "and e.ente_id =rae.ente_id "
						+ "and e.partita_iva =rice.id_pa "
						+ "and custom.applicationid = coda.application_id "
						+ "and custom.fieldname = 'flag_retry_rt' "
						+ "and custom.fieldvalue = ? "
						+ "and (coda.data_inizio_tentativi is null  or coda.num_giorni_tentativi_ko<= ? )";
		query = query + "order by application_id, id ";
		if ( null != limite ) {
			query = query + " limit ?";
		}
		return query;
	}
	
	
}

class DatiReceiptNonInviataExtractor implements ResultSetExtractor<List<DatiReceiptNonInviata>> {
    
    public List<DatiReceiptNonInviata> extractData(ResultSet rs) throws SQLException {
        List<DatiReceiptNonInviata> elenco = new ArrayList<DatiReceiptNonInviata>();
        while(rs.next()){
            DatiReceiptNonInviata risultato = new DatiReceiptNonInviata();
            risultato.setId(rs.getInt("id"));
            risultato.setReceiptId  (rs.getString("receipt_id")) ;
            risultato.setCreditorReferenceId ( rs.getString("creditor_referenceid") );
            risultato.setApplicationId(rs.getString("application_id"));
            risultato.setTransactionId(rs.getString("transaction_id"));
            
            risultato.setDataOraInsert (rs.getTimestamp("data_ora_insert"));
            risultato.setDataOraUpdate (rs.getTimestamp("data_ora_update"));
            risultato.setDataOraRicezione (rs.getTimestamp("data_ora_ricezione"));
            risultato.setMsgInvioFallito ( rs.getString("msg_invio_fallito") );
            risultato.setXmlReceip (rs.getBytes("xml_receipt"));
            risultato.setDataOraInvioFallito ( rs.getTimestamp("data_ora_invio_fallito") );
            risultato.setPaymentAmount (  rs.getBigDecimal("payment_amount")  );
            risultato.setIdPsp (  rs.getString("id_psp")  );
            risultato.setPspCompanyName (   rs.getString("psp_comany_name")   );
            risultato.setPaymentDateTime ( rs.getTimestamp("payment_datetime")   );
            
            
            CodaInvioReceipt coda= new CodaInvioReceipt();
            
            coda.setContatoreTentativi(rs.getInt("contatore_tentativi"));
            coda.setApplicationId(rs.getString("application_id"));
            coda.setTransactionId(rs.getString("transaction_id"));
            coda.setDataTentativi(rs.getTimestamp("data_tentativi"));
            coda.setDataInizioTentativi(rs.getTimestamp("data_inizio_tentativi"));
            coda.setNumGiorniTentativiKo(rs.getInt("num_giorni_tentativi_ko"));
            coda.setUltimoEsitoFruitore(rs.getString("ultimo_esito_fruitore"));
            coda.setDataUltimaModifica(rs.getTimestamp("data_ultima_modifica"));
            coda.setDataInserimento(rs.getTimestamp("data_inserimento"));
            risultato.setCoda(coda);
            elenco.add(risultato);
        }
        return elenco;
    }
    
    
}
