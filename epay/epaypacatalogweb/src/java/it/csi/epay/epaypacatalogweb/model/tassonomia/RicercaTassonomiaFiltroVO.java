/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.model.tassonomia;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.util.StringUtils;

import it.csi.epay.epaypacatalogweb.util.DateUtils;

/**
 *
 */

public class RicercaTassonomiaFiltroVO implements Serializable {
	
	
	private static final long serialVersionUID = 8017150489933340848L;

	////	@JsonProperty("codTipoEnteCreditore")
	private String codTipoEnteCreditore;

////	@JsonProperty("tipoServizio")
	private String tipoServizio;


//	@JsonProperty("datiSpecificiIncasso")
	private String datiSpecificiIncasso;

//	@JsonProperty("dataInizioValidita")
	private Date dataInizioValidita;

//	@JsonProperty("dataFineValidita")
	private Date dataFineValidita;



	private String macroArea;
	
	private String nomeMacroArea;



	public RicercaTassonomiaFiltroVO () {
		super ();
	}

	


	/**
	 * @return the tipoServizio
	 */
	public String getTipoServizio() {
		return tipoServizio;
	}

	/**
	 * @param tipoServizio the tipoServizio to set
	 */
	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}



	/**
	 * @return the datiSpecificiIncasso
	 */
	public String getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}

	/**
	 * @param datiSpecificiIncasso the datiSpecificiIncasso to set
	 */
	public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}



	/**
	 * @return the dataInizioValidita
	 */
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	/**
	 * @param dataInizioValidita the dataInizioValidita to set
	 */
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	/**
	 * @return the dataFineValidita
	 */
	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}


	public String getDataInizioValiditaFormat() {
		return DateUtils.getStringDate(dataInizioValidita);
	}


	public String getDataFineValiditaFormat() {
		return DateUtils.getStringDate(dataFineValidita);
	}
	
	
	/**
	 * @return the codTipoEnteCreditore
	 */
	public String getCodTipoEnteCreditore() {
		return codTipoEnteCreditore;
	}

	/**
	 * @param codTipoEnteCreditore the codTipoEnteCreditore to set
	 */
	public void setCodTipoEnteCreditore(String codTipoEnteCreditore) {
		this.codTipoEnteCreditore = codTipoEnteCreditore;
	}

	/**
	 * @return the macroArea
	 */
	public String getMacroArea() {
		return macroArea;
	}

	/**
	 * @param macroArea the macroArea to set
	 */
	public void setMacroArea(String macroArea) {
		this.macroArea = macroArea;
	}

	
	/**
	 * @return the nomeMacroArea
	 */
	public String getNomeMacroArea() {
		return nomeMacroArea;
	}

	/**
	 * @param nomeMacroArea the nomeMacroArea to set
	 */
	public void setNomeMacroArea(String nomeMacroArea) {
		this.nomeMacroArea = nomeMacroArea;
	}

	

	public String toQueryString() throws UnsupportedEncodingException {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer sb = new StringBuffer();
		String fieldName;
		Object value;
		Method getMethod;
		int i=0;
		for (Field field : fields) {
			fieldName = field.getName();
			try {
				getMethod = this.getClass().getMethod("get"+StringUtils.capitalize(fieldName));

				value = getMethod.invoke(this);
				if (value != null) {
					if (i>0)
					{
						sb.append("&");
					}
					else
					{
						i++;
					}

					sb.append(fieldName);
					sb.append("=");
					sb.append(value.toString());
				}
			} catch (NoSuchMethodException ex) {

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(sb.toString(), "UTF-8");
	}

}
