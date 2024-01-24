/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

import java.util.List;

public class BusinessMatchException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private Integer id1, id2;
	private String cod1, cod2;
	private List<Integer> idList1, idList2;
	private List<String> codList1, codList2;
	private String objName;
	private String keyName;

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public String getCod1() {
		return cod1;
	}

	public void setCod1(String cod1) {
		this.cod1 = cod1;
	}

	public String getCod2() {
		return cod2;
	}

	public void setCod2(String cod2) {
		this.cod2 = cod2;
	}

	public List<Integer> getIdList1() {
		return idList1;
	}

	public void setIdList1(List<Integer> idList1) {
		this.idList1 = idList1;
	}

	public List<Integer> getIdList2() {
		return idList2;
	}

	public void setIdList2(List<Integer> idList2) {
		this.idList2 = idList2;
	}

	public List<String> getCodList1() {
		return codList1;
	}

	public void setCodList1(List<String> codList1) {
		this.codList1 = codList1;
	}

	public List<String> getCodList2() {
		return codList2;
	}

	public void setCodList2(List<String> codList2) {
		this.codList2 = codList2;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

}
