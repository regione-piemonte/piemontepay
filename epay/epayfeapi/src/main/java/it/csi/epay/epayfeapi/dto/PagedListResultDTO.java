/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class PagedListResultDTO<T> implements Serializable {

	private static final long serialVersionUID = 1933067843057437477L;

	private List<T> list;

	private int currentPage; // one-based

	private int pageSize;

	private int totalPages;

	private long totalElements;

	public List<T> getList () {
		return list;
	}

	public void setList ( List<T> list ) {
		this.list = list;
	}

	public int getCurrentPage () {
		return currentPage;
	}

	public void setCurrentPage ( int currentPage ) {
		this.currentPage = currentPage;
	}

	public int getPageSize () {
		return pageSize;
	}

	public void setPageSize ( int pageSize ) {
		this.pageSize = pageSize;
	}

	public int getTotalPages () {
		return totalPages;
	}

	public void setTotalPages ( int totalPages ) {
		this.totalPages = totalPages;
	}

	public long getTotalElements () {
		return totalElements;
	}

	public void setTotalElements ( long totalElements ) {
		this.totalElements = totalElements;
	}

	@Override
	public String toString () {
		return "{ " +
						"list.size:" + list.size () +
						", currentPage:" + currentPage +
						", pageSize:" + pageSize +
						", totalPages:" + totalPages +
						", totalElements:" + totalElements +
						" }";
	}
}
