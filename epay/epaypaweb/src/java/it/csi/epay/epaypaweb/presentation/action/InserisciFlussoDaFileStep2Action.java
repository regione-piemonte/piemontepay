/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import it.csi.epay.epaypaweb.dto.ParametriInserimentoFlussoDto;
import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.exception.EmptyLineInExcelException;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.util.CSVUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class InserisciFlussoDaFileStep2Action extends EpaypawebBaseAction {

	private static final long serialVersionUID = 1L;

    protected File file;
    protected String fileFileName;
    protected String fileContentType;
    protected TipoFormatoFileEnum tipoFormato;

    @Override
    public void validate() {
        if (hasFieldErrors()) {
            addActionMessage(getText("message.associare.file", new String[] { getDescrizioneTipoFormatoFile() }));
        }
    }

    //@formatter:off
    protected void elaboraFlussoDaFile(ParametriInserimentoFlussoDto params ) {
        try {
            log.debug("path+file:" + file);
            log.debug("filename:" + fileFileName);
            log.debug("contentType:" + fileContentType);
            log.debug("formato:" + tipoFormato);

			List<List<Object>> lines = new ArrayList<> ();

            switch (tipoFormato) {
            case CSV:
                /*
                 * N.B.1 Rimozione della verifica del content type del file.
                 *       Effettuando l'upload di un file CSV o XLSX da un pc con Windows senza Microsoft Office installato,
                 *       il browser specifica come content type del file "application/octect-stream" ossia un file binario qualunque.
                 *       Questo perche Windows di suo non imposta un content type per queste tipologie di file.
                 *       Di fatto, al momento, l'utente deve gia indicare se il tipo di file e' un excel o un csv,
                 *       con questa informazione in input viene attivata una logica di parse specifica per il tipo file,
                 *       pertanto controllare il content type risulta limitante, in quanto non sappiamo se gli utenti avranno tutti Office.
                 */				
                //				if (   fileContentType.equals("text/csv")
                //					|| fileContentType.equals("text/plain")
                // 					|| fileContentType.equals("application/vnd.ms-excel"))
            {
                // legge il file CSV e ne riporta le righe in una lista e ogni riga rapprenseta una lista di colonne
                String line;
                List<Object> lineParsed;
                InputStreamReader ireaderEncoded = new InputStreamReader(new FileInputStream(file), Constants.ENCODING);
                BufferedReader reader = new BufferedReader(ireaderEncoded);
                while((line = reader.readLine()) != null) {
                    log.debug("CSV LINE: " + line);
                    lineParsed = CSVUtil.parseLine(line);
                    lines.add(lineParsed);
                }
                reader.close();

                // analizza i dati ricevuti e fa l'inserimento
				inserisciFlusso ( params, lines, "CSV" );

                // N.B.2 come N.B.1
                //				} else {
                //					esitoElaborazione = "warning";
                //					messaggioEsitoElaborazione = "Elaborazione del file " + fileFileName + " terminata con errori: tipo del file (" + fileContentType + ") non congruente";
            }
            break;

            case XLSX:
                // N.B.3 come N.B.1
                //				if (fileContentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            {
                Workbook wb = WorkbookFactory.create(file);

                switch (wb.getNumberOfSheets()) {
                case 0:
                    esitoElaborazione = "warning";
                    messaggioEsitoElaborazione = "Elaborazione del file " + fileFileName + " terminata con errori: non sono presenti fogli di calcolo";
                    break;

                case 1:
                    // un solo foglio di calcolo presente
                    Sheet sheet = wb.getSheetAt(0);
                    for (Row row : sheet) {
                        log.debug("XLSX LINE:" + row.getRowNum());
                        int dim = row.getLastCellNum();
						if ( dim < 0 ) {
							continue;
							//throw new EmptyLineInExcelException ();
						}
                        List<Object> line = Arrays.asList(new Object[dim]); // N.B. dimensiona line in modo fisso in quanto il ciclo for "salta" le celle non valorizzate
                        for (Cell cell : row) {
                            Object objval;
                            switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                objval = cell.getStringCellValue();
                                break;

                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell))
                                    objval = cell.getDateCellValue();
                                else
                                    objval = cell.getNumericCellValue();
                                break;

                            case Cell.CELL_TYPE_BLANK:
                                objval = null;
                                break;

                            default:
                                objval = "UNEXPECTED TYPE!";
                                break;
                            }

                            int columnIndex = cell.getColumnIndex();
                            log.debug("XLSX CELL[" + columnIndex + "]:" + objval);
                            line.set(columnIndex, objval);
                        }

                        lines.add(line);
                        log.debug("XLSX END-OF-LINE");
                    }

                    // analizza i dati ricevuti e fa l'inserimento
                    inserisciFlusso ( params, lines, "XLSX" );
                    break;

                default:
                    esitoElaborazione = "warning";
                    messaggioEsitoElaborazione = "Elaborazione del file " + fileFileName + " terminata con errori: e' consentito un solo foglio per file";
                    break;
                }

                // N.B.4 come N.B.1
                //				} else {
                //					esitoElaborazione = "warning";
                //					messaggioEsitoElaborazione = "Elaborazione del file " + fileFileName + " terminata con errori: tipo del file (" + fileContentType + ") non congruente";
            }
            break;

            default:
                break;
            }

        } catch (EmptyLineInExcelException e) {
            log.error("Errore imprevisto", e);
            addActionMessage(getText("empty.line.in.excel.error"));

        } catch (BusinessException e) {
            log.error("Errore imprevisto", e);
            addActionMessage(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));

        } catch (IOException e) {
            log.error("Errore imprevisto", e);
            addActionMessage(getText("file.io.error"));

        } catch (Throwable e) {
            log.error("Errore imprevisto", e);
            addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
        }
    }
    //@formatter:on

	protected abstract void inserisciFlusso ( ParametriInserimentoFlussoDto params, List<List<Object>> lines, String tipoFormato ) throws BusinessException;

    public File getFile() {
        return file;
    }

    @RequiredFieldValidator(key = "campo.obbligatorio.file")
    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public TipoFormatoFileEnum getTipoFormato() {
        return tipoFormato;
    }

    public void setTipoFormato(TipoFormatoFileEnum tipoFormato) {
        this.tipoFormato = tipoFormato;
    }

    public String getDescrizioneTipoFormatoFile() {
        String descrizione = "";
        for (TipoFormatoFileDto dto : getSessionContext().getFormatiFile()) {
            if (dto.getTipoFormatoFile() == tipoFormato) {
                descrizione = dto.getDescrizione();
                break;
            }
        }
        return descrizione;
    }

}
