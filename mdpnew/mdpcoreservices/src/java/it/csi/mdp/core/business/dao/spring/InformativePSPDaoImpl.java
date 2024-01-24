package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.InformativePSPDao;
import it.csi.mdp.core.business.dto.InformativePSP;
import it.csi.mdp.core.business.dto.TipoVersamento;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.business.util.UtilDate;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class InformativePSPDaoImpl extends AbstractDAO implements ParameterizedRowMapper<InformativePSP>, InformativePSPDao
{
	protected NamedParameterJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@SuppressWarnings("cast")
	public List<InformativePSP> findAll() throws DaoException {
		return getInformativePSPByParam(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	public List<InformativePSP> findAllAttive() throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		return jdbcTemplate.query("SELECT * FROM informativa_psp where statoinserimento = 'NEW' ", params, this);
	}

	public InformativePSP findWhereKeyidEquals(Integer id) throws DaoException {
		List<InformativePSP> ris = getInformativePSPByParam(id,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
		if(ris.size()==0)
			return null;
		else
			return ris.get(0);
		
	}
	
	public InformativePSP mapRow(ResultSet rs, int index) throws SQLException {
		
		InformativePSP infoPSP = new InformativePSP();
		
		infoPSP.setIdinformativapsp(rs.getInt("idinformativapsp"));
		infoPSP.setCondizioniEconomicheMassime(rs.getString("condizionieconomichemassime"));
		infoPSP.setDataInizioValidita(rs.getTimestamp("datainiziovalidita"));
		infoPSP.setDatainserimento(rs.getTimestamp("datainserimento"));
		infoPSP.setDataPubblicazione(rs.getTimestamp("datapubblicazione"));
		infoPSP.setDescrizioneServizio(rs.getString("descrizioneservizio"));
		infoPSP.setDisponibilitaServizio(rs.getString("disponibilitaservizio"));
		infoPSP.setIdentificativoCanale(rs.getString("identificativocanale"));
		infoPSP.setIdentificativoFlusso(rs.getString("identificativoflusso"));
		infoPSP.setIdentificativoIntermediario(rs.getString("identificativointermediario"));
		infoPSP.setIdentificativoPSP(rs.getString("identificativopsp"));
		infoPSP.setModelloPagamento(rs.getInt("modellopagamento"));
		infoPSP.setOrdinamento(rs.getInt("ordinamento"));
		infoPSP.setOrigine(rs.getString("origine"));
		infoPSP.setPriorita(rs.getInt("priorita"));
		infoPSP.setRagioneSociale(rs.getString("ragionesociale"));
		infoPSP.setStatoinserimento(rs.getString("statoinserimento"));
		infoPSP.setStornoPagamento(rs.getInt("stornopagamento"));
		infoPSP.setTipoVersamento(rs.getString("tipoversamento"));
		infoPSP.setUrlInformazioniCanale(rs.getString("urlinformazionicanale"));
		infoPSP.setUrlInformazioniPSP(rs.getString("urlinformazionipsp"));
		
		return infoPSP;
	}

	public List<InformativePSP> getInformativePSPByParam(Integer idinformativapsp, String identificativoFlusso, String identificativoPSP, String ragioneSociale, Date dataPubblicazione,
			Date dataInizioValidita, String urlInformazioniPSP, Integer stornoPagamento, String identificativoIntermediario, String identificativoCanale, String tipoVersamento,
			Integer modelloPagamento, Integer priorita, String disponibilitaServizio, String descrizioneServizio, String condizioniEconomicheMassime, String urlInformazioniCanale,
			Date datainserimento, String statoinserimento, Integer ordinamento, String origine) throws DaoException {
		//List<InformativePSP> ris = jdbcTemplate.query("SELECT * FROM informativa_psp", this, null);
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");

		sql.append("   SELECT ");
		sql.append("   idinformativapsp ");
		sql.append("  ,identificativoFlusso ");
		sql.append("  ,identificativoPSP ");
		sql.append("  ,ragioneSociale ");
		sql.append("  ,dataPubblicazione ");
		sql.append("  ,dataInizioValidita ");
		sql.append("  ,urlInformazioniPSP ");
		sql.append("  ,stornoPagamento ");
		sql.append("  ,identificativoIntermediario ");
		sql.append("  ,identificativoCanale ");
		sql.append("  ,tipoVersamento ");
		sql.append("  ,modelloPagamento ");
		sql.append("  ,priorita ");
		sql.append("  ,disponibilitaServizio ");
		sql.append("  ,descrizioneServizio ");
		sql.append("  ,condizioniEconomicheMassime ");
		sql.append("  ,urlInformazioniCanale ");
		sql.append("  ,datainserimento ");
		sql.append("  ,statoinserimento ");
		sql.append("  ,ordinamento ");
		sql.append("  ,origine ");
		sql.append("   FROM informativa_psp a");
		sql.append("   WHERE 1=1 ");	
		
		if(idinformativapsp!=null){
			params.addValue("idinformativapsp", idinformativapsp, java.sql.Types.INTEGER);
			sql.append(" AND idinformativapsp=:idinformativapsp");
		}
		if(StringUtil.isNotBlank(identificativoFlusso)){ 
			params.addValue("identificativoFlusso", identificativoFlusso, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoFlusso=:identificativoFlusso");
		}
		
		if(StringUtil.isNotBlank(identificativoPSP)){ 
			params.addValue("identificativoPSP", identificativoPSP, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoPSP=:identificativoPSP");
		}
		
		if(StringUtil.isNotBlank(ragioneSociale)){ 
			//params.addValue("ragioneSociale", ragioneSociale, java.sql.Types.VARCHAR);
			sql.append(" AND UPPER(a.ragioneSociale) LIKE '%"+ragioneSociale.toUpperCase()+"%'");
		}
		
		if(dataPubblicazione!=null){ 
			Date dataMezzanotte = UtilDate.addDay(dataPubblicazione,1);
			//sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(dataPubblicazione)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
			sql.append(" AND a.datapubblicazione between to_timestamp('" +sdf.format(dataPubblicazione)+"','YYYYMMDD') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
		}
		
		if(dataInizioValidita!=null){ 
			Date dataMezzanotte = UtilDate.addDay(dataInizioValidita,1);
			//sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(dataPubblicazione)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
			sql.append(" AND a.datainiziovalidita between to_timestamp('" +sdf.format(dataInizioValidita)+"','YYYYMMDD') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
			//params.addValue("dataInizioValidita", dataInizioValidita, java.sql.Types.DATE);
		}
		
		if(StringUtil.isNotBlank(urlInformazioniPSP)){ 
			params.addValue("urlInformazioniPSP", urlInformazioniPSP, java.sql.Types.VARCHAR);
			sql.append(" AND a.urlInformazioniPSP=:urlInformazioniPSP");
		}
		
		if(stornoPagamento!=null){ 
			params.addValue("stornoPagamento", stornoPagamento, java.sql.Types.INTEGER);
			sql.append(" AND a.stornoPagamento=:stornoPagamento");
		}
		
		if(StringUtil.isNotBlank(identificativoIntermediario)){ 
			params.addValue("identificativoIntermediario", identificativoIntermediario, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoIntermediario=:identificativoIntermediario");
		}
		
		if(StringUtil.isNotBlank(identificativoCanale)){ 
			params.addValue("identificativoCanale", identificativoCanale, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoCanale=:identificativoCanale");
		}
		
		if(StringUtil.isNotBlank(tipoVersamento)){ 
			params.addValue("tipoVersamento", tipoVersamento, java.sql.Types.VARCHAR);
			sql.append(" AND a.tipoVersamento=:tipoVersamento");
		}
		
		if(modelloPagamento!=null){ 
			params.addValue("modelloPagamento", modelloPagamento, java.sql.Types.INTEGER);
			sql.append(" AND a.modelloPagamento=:modelloPagamento");
		}
		
		if(priorita!=null){ 
			params.addValue("priorita", priorita, java.sql.Types.INTEGER);
			sql.append(" AND a.priorita=:priorita");
		}
		
		if(StringUtil.isNotBlank(disponibilitaServizio)){ 
			params.addValue("disponibilitaServizio", disponibilitaServizio, java.sql.Types.VARCHAR);
			sql.append(" AND a.disponibilitaServizio=:disponibilitaServizio");
		}
		
		if(StringUtil.isNotBlank(descrizioneServizio)){ 
			params.addValue("descrizioneServizio", descrizioneServizio, java.sql.Types.VARCHAR);
			sql.append(" AND a.descrizioneServizio=:descrizioneServizio");
		}
		
		if(StringUtil.isNotBlank(condizioniEconomicheMassime)){ 
			params.addValue("condizioniEconomicheMassime", condizioniEconomicheMassime, java.sql.Types.VARCHAR);
			sql.append(" AND a.condizioniEconomicheMassime=:condizioniEconomicheMassime");
		}
		
		if(StringUtil.isNotBlank(urlInformazioniCanale)){ 
			params.addValue("urlInformazioniCanale", urlInformazioniCanale, java.sql.Types.VARCHAR);
			sql.append(" AND a.urlInformazioniCanale=:urlInformazioniCanale");
		}
		
		if(datainserimento!=null){
			Date dataMezzanotte = UtilDate.addDay(datainserimento,1);
			//sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(datainserimento)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
			sql.append(" AND a.datainserimento between to_timestamp('" +sdf.format(datainserimento)+"','YYYYMMDD') AND to_timestamp('" +sdf.format(dataMezzanotte)+"','YYYYMMDD HH24:MI')" );
			params.addValue("datainserimento", datainserimento, java.sql.Types.DATE);

		}
			
		if(StringUtil.isNotBlank(statoinserimento)){ 
			params.addValue("statoinserimento", statoinserimento, java.sql.Types.VARCHAR);
			sql.append(" AND a.statoinserimento=:statoinserimento");
		}
		
		if(ordinamento!=null){ 
			params.addValue("ordinamento", ordinamento, java.sql.Types.INTEGER);
			sql.append(" AND a.ordinamento=:ordinamento");
		}
		
		if(StringUtil.isNotBlank(origine)){ 
			params.addValue("origine", origine, java.sql.Types.VARCHAR);
			sql.append(" AND a.origine=:origine");
		}
		 sql.append(" ORDER BY a.datainserimento DESC ");

		LoggerUtil.info("Integer idinformativapsp --> " + idinformativapsp);
		LoggerUtil.info("String identificativoFlusso --> " + identificativoFlusso);
		LoggerUtil.info("String identificativoPSP --> " + identificativoPSP);
		LoggerUtil.info("String ragioneSociale --> " + ragioneSociale);
		LoggerUtil.info("Date dataPubblicazione --> " + dataPubblicazione);
		LoggerUtil.info("Date dataInizioValidita --> " + dataInizioValidita);
		LoggerUtil.info("String urlInformazioniPSP --> " + urlInformazioniPSP);
		LoggerUtil.info("Integer stornoPagamento --> " + stornoPagamento);
		LoggerUtil.info("String identificativoIntermediario --> " + identificativoIntermediario);
		LoggerUtil.info("String identificativoCanale --> " + identificativoCanale);
		LoggerUtil.info("String tipoVersamento --> " + tipoVersamento);
		LoggerUtil.info("Integer modelloPagamento --> " + modelloPagamento);
		LoggerUtil.info("Integer priorita --> " + priorita);
		LoggerUtil.info("String disponibilitaServizio --> " + disponibilitaServizio);
		LoggerUtil.info("String descrizioneServizio --> " + descrizioneServizio);
		LoggerUtil.info("String condizioniEconomicheMassime --> " + condizioniEconomicheMassime);
		LoggerUtil.info("String urlInformazioniCanale --> " + urlInformazioniCanale);
		LoggerUtil.info("Date datainserimento --> " + datainserimento);
		LoggerUtil.info("String statoinserimento --> " + statoinserimento);
		LoggerUtil.info("Integer ordinamento --> " + ordinamento);
		LoggerUtil.info("String origine --> " + origine);
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}
	
	/**
	 * A partire da applicazione e transazione recupera l'informativa del PSP che è stato scelto sul WISP
	 * @param applicationId
	 * @param transactionId
	 * @return l'informativa del PSP scelto sul Wisp
	 * @throws DaoException
	 */
	public InformativePSP recuperaInformativaPSPSceltaWisp(String applicationId, String transactionId) throws DaoException {
		LoggerUtil.begin();
		try {
		String query = "select i.* from wisp_params p, informativa_psp i where p.application_id = :appId and p.transaction_id = :transId and i.statoinserimento = :statoNew  and p.identificativopspscelto = i.identificativopsp and p.identificativointermediariopspscelto = i.identificativointermediario and p.identificativocanalescelto = i.identificativocanale and p.tipoversamentoscelto = i.tipoversamento order by p.insert_date desc";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("appId", applicationId);
		params.addValue("transId", transactionId);
		params.addValue("statoNew", "NEW");
		
		LoggerUtil.debug("QUERY" + query);
		LoggerUtil.debug("applicationId" + applicationId);
		LoggerUtil.debug("transactionId" + transactionId);
		

		List<InformativePSP> elencoTrovati = jdbcTemplate.query(query, params, this);
		
		if (elencoTrovati == null || elencoTrovati.size() == 0) throw new DaoException("Nessuna scelta trovata per " + applicationId + " TRANSAZIONE " + transactionId);
//		if (elencoTrovati.size() > 1) throw new DaoException("Troppe scelte trovate per " + applicationId + " TRANSAZIONE " + transactionId);
		return elencoTrovati.get(0);
		} finally {
			LoggerUtil.end();
		}
		
	}
/*
	public List<TipoVersamento> getListaTipoversamento() throws DaoException {
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		sql.append("   SELECT DISTINCT ");
		sql.append("   tipoVersamento,tipoVersamento ");
		sql.append("   FROM informativa_psp ");
		List<TipoVersamento> tv = new ArrayList<TipoVersamento>();	
		
		ResultSetExtractor tipoVersamentoRM = null;
		return (List<TipoVersamento>) jdbcTemplate.query(sql.toString(), params,tipoVersamentoRM);
			
	}
	*/
	
	public List<TipoVersamento> getListaTipoversamento()throws DaoException { 
		StringBuffer sql = new StringBuffer();
		sql.append("   SELECT DISTINCT ");
		sql.append("   tipoVersamento,tipoVersamento ");
		sql.append("   FROM informativa_psp ");
		
		SqlParameterSource mappa = null;
		ResultSetExtractor rse = null;
		
		
		@SuppressWarnings("unchecked")
		List<TipoVersamento> ris =  (List<TipoVersamento>) jdbcTemplate.query(sql.toString(),mappa,
		 new ResultSetExtractor(){ 
		    public List<TipoVersamento> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<TipoVersamento> list=new ArrayList<TipoVersamento>();  
		        while(rs.next()){  
		        	TipoVersamento e=new TipoVersamento();  
		        	e.setId(rs.getString(1));  
		        	e.setDescrizione(rs.getString(2)); 
		        	list.add(e);  
		        }  
		        return list;  
		        }  
		    });  
		return ris;
	
	}
	
	// ANTO da ricordarsi
	public List<String> getListaIdentificativopsp()throws DaoException { 
		StringBuffer sql = new StringBuffer();
		sql.append("   select distinct identificativopsp from informativa_psp ");
		
		SqlParameterSource mappa = null;
		ResultSetExtractor rse = null;
		
		
		@SuppressWarnings("unchecked")
		List<String> ris =  (List<String>) jdbcTemplate.query(sql.toString(),mappa,
		 new ResultSetExtractor(){ 
		    public List<String> extractData(ResultSet rs) throws SQLException,   DataAccessException {  
		      
		        List<String> list=new ArrayList<String>();  
		        while(rs.next()){  
		        	String e=rs.getString(1);  
		        	list.add(e);  
		        }  
		        return list;  
		        }  
		    });  
		return ris;
	
	}
	
	
}	
