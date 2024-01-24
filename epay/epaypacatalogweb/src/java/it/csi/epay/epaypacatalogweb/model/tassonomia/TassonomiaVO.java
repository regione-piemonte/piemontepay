/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.model.tassonomia;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.util.StringUtils;

import it.csi.epay.epaypacatalogweb.model.GenericVO;
import it.csi.epay.epaypacatalogweb.util.DateUtils;

/**
 *
 */

public class TassonomiaVO extends GenericVO {

	private static final long serialVersionUID = 515553006622503120L;

	private Long id;

	private String codTipoEnteCreditore;
	
	private String tipoEnteCreditore;

	private String codTipologiaServizio;

	private String tipoServizio;

	private String descrTipoServizio;

	private String datiSpecificiIncasso;

	private Date dataInizioValidita;

	private Date dataFineValidita;

	private String macroArea;
	
	private String nomeMacroArea;
	
	private String descrMacroArea;

	private String motivoGiuridicoRiscossione;
	
	private String desMotivoGiuridicoRiscossione;
	
	private String nVersioneTassonomia;


	public TassonomiaVO () {
		super ();
	}

	public TassonomiaVO ( Long id, String codTipologiaServizio, String tipoServizio ) {
		super ();
		setId(id);
		this.codTipologiaServizio = tipoServizio;
		this.tipoServizio = tipoServizio;

	}


	/**
	 * @return the codTipologiaServizio
	 */
	public String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}

	/**
	 * @param codTipologiaServizio the codTipologiaServizio to set
	 */
	public void setCodTipologiaServizio(String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
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
	 * @return the descrTipoServizio
	 */
	public String getDescrTipoServizio() {
		return descrTipoServizio;
	}

	/**
	 * @param descrTipoServizio the descrTipoServizio to set
	 */
	public void setDescrTipoServizio(String descrTipoServizio) {
		this.descrTipoServizio = descrTipoServizio;
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
	 * @return the motivoGiuridicoRiscossione
	 */
	public String getMotivoGiuridicoRiscossione() {
		return motivoGiuridicoRiscossione;
	}

	/**
	 * @param motivoGiuridicoRiscossione the motivoGiuridicoRiscossione to set
	 */
	public void setMotivoGiuridicoRiscossione(String motivoGiuridicoRiscossione) {
		this.motivoGiuridicoRiscossione = motivoGiuridicoRiscossione;
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
	

	/**
	 * @return the descrMacroArea
	 */
	public String getDescrMacroArea() {
		return descrMacroArea;
	}

	/**
	 * @param descrMacroArea the descrMacroArea to set
	 */
	public void setDescrMacroArea(String descrMacroArea) {
		this.descrMacroArea = descrMacroArea;
	}
	
	

	/**
	 * @return the desMotivoGiuridicoRiscossione
	 */
	public String getDesMotivoGiuridicoRiscossione() {
		return desMotivoGiuridicoRiscossione;
	}

	/**
	 * @param desMotivoGiuridicoRiscossione the desMotivoGiuridicoRiscossione to set
	 */
	public void setDesMotivoGiuridicoRiscossione(String desMotivoGiuridicoRiscossione) {
		this.desMotivoGiuridicoRiscossione = desMotivoGiuridicoRiscossione;
	}

	@Override
	public String toString () {
		return "TassonomiaVO [id=" + getId() + ", codTipologiaServizio=" + codTipologiaServizio + ", tipoServizio=" + tipoServizio +  "]";
	}
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

	/**
	 * @return the nVersioneTassonomia
	 */
	public String getnVersioneTassonomia() {
		return nVersioneTassonomia;
	}

	/**
	 * @param nVersioneTassonomia the nVersioneTassonomia to set
	 */
	public void setnVersioneTassonomia(String nVersioneTassonomia) {
		this.nVersioneTassonomia = nVersioneTassonomia;
	}
	

	/**
	 * @return the tipoEnteCreditore
	 */
	public String getTipoEnteCreditore() {
		return tipoEnteCreditore;
	}

	/**
	 * @param tipoEnteCreditore the tipoEnteCreditore to set
	 */
	public void setTipoEnteCreditore(String tipoEnteCreditore) {
		this.tipoEnteCreditore = tipoEnteCreditore;
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
