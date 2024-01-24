/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import it.csi.epay.epaypacatalogweb.business.manager.ExportManager;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.GestioneUtentiFacadeMockImpl;
import it.csi.epay.epaypacatalogweb.vo.UserVO;





@Service
public class ExportManagerImpl extends AbstractExcelView implements ExportManager {

    @Override
    public void buildExcelDocument ( Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response ) {

        response.setHeader ( "Content-Disposition", "attachment; filename=\"XLS_User_Details.xls\"" );

        //        List<UserVO> userList = (List<UserVO>) model.get ( "userList" );
        List<UserVO> userList = GestioneUtentiFacadeMockImpl.getAll ();

        Sheet sheet = workbook.createSheet ( "Users Data" );
        //        CellStyle cellStyle = workbook.createCellStyle ();

        Row header = sheet.createRow ( 0 );
        header.createCell ( 0 ).setCellValue ( "SSN Number" );
        header.createCell ( 1 ).setCellValue ( "Name" );
        header.createCell ( 2 ).setCellValue ( "Email" );
        header.createCell ( 3 ).setCellValue ( "Start Date" );
        header.createCell ( 4 ).setCellValue ( "Expiration Date" );

        int rowNum = 1, index;

        for ( UserVO user: userList ) {

            index = userList.indexOf ( user );

            Row row = sheet.createRow ( rowNum++ );

            row.createCell ( 0 ).setCellValue ( user.getSsnNumber () );
            row.createCell ( 1 ).setCellValue ( user.getName () );
            row.createCell ( 2 ).setCellValue ( user.getEmail () );
            row.createCell ( 3 ).setCellValue ( formatDateTimestamp ( user.getStartDate () ) );
            row.createCell ( 4 ).setCellValue ( formatDateTimestamp ( user.getExpirationDate () ) );

            sheet.autoSizeColumn ( index );
        }

    }

    @Override
    public void downloadCSV ( HttpServletResponse response ) {

        response.setContentType ( "text/csv" );
        String reportName = "CSV_User_Details.csv";
        response.setHeader ( "Content-disposition", "attachment;filename=" + reportName );

        List<UserVO> userList = GestioneUtentiFacadeMockImpl.getAll ();

        List<String> rows = new ArrayList<String> ();
        rows.add ( "SSN Number,Name,Email,Start Date,Expiration Date" );
        rows.add ( "\n" );

        for ( UserVO user: userList ) {
            rows.add (
                user.getSsnNumber () + "," + user.getName () + "," + user.getEmail () + "," + formatDateTimestamp ( user.getStartDate () ) + ","
                                + formatDateTimestamp ( user.getExpirationDate () ) );
            rows.add ( "\n" );
        }


        Iterator<String> iter = rows.iterator ();
        while ( iter.hasNext () ) {
            String outputString = iter.next ();
            try {
                response.getOutputStream ().print ( outputString );
                response.getOutputStream ().flush ();
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace ();
            }
        }

    }

    private String formatDateTimestamp ( Date timestamp ) {

        DateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" );

        return sdf.format ( timestamp );

    }


}
