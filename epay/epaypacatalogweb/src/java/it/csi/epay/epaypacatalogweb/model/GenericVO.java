/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import javax.validation.constraints.Min;

import org.springframework.util.StringUtils;

/**
 * Value Object generico. Viene ereditato da tutti gli oggetti del Model di Epaypacatalogweb. Utilizzato anche per la validazione (sul solo campo id)
 * 
 * @author lorenzo.fantini
 * 
 */
public class GenericVO implements Serializable {
	private static final long serialVersionUID = -7876974444583948009L;

	@Min(value = 0, message = "parametro obbligatorio")
	private Long id;

	private String codice;

	private String descrizione;

	public GenericVO() {
		this.id = null;
		this.codice = null;
		this.descrizione = null;
	}

	public GenericVO(Long id, String codice, String descrizione) {
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public GenericVO(String codice, String descrizione) {
		this.id = null;
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public GenericVO(String descrizione) {
		this.id = null;
		this.descrizione = descrizione;
		this.codice = descrizione.toUpperCase()
				.replaceAll("[^A-Z0-9_]", "_")
				.replaceAll("_{2,}", "_");
	}
	
	@Override
	public String toString() {
		return "GenericVO [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	// DA QUI IN POI SOLO GETTER E SETTER

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
