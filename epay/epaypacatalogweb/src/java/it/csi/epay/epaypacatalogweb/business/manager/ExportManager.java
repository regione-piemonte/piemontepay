/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public interface ExportManager {

    void buildExcelDocument ( Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response );

    void downloadCSV ( HttpServletResponse response );

}
