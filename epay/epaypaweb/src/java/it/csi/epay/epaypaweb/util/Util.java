/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util;

import it.csi.epay.epaypaweb.dto.*;
import it.csi.epay.epaypaweb.enumeration.NomeFiltroReportEnum;
import it.csi.epay.epaypaweb.enumeration.TipoCampoFiltroEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDStatoReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoFileReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaDTipoReport;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTReport;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Util {
	// costanti
	public static final String APPLICATION_CODE = "epaypaweb";

	public static final String JOB_CONFIGFILENAME = "job-config.xml";

	public static final String UTENTE_INSERIMENTO = "SYSTEM";

	public static final String COD_ESITO_OK = "000";

	public static final String DET_ESITO_OK = "OK";

	// formati temporali
	public static final String DATE_Y4M2D2 = "yyyyMMdd";

	public static final String DATE_FORMAT = "dd/MM/yyyy";

	public static final String TIME_FORMAT = "HH:mm:ss";

	public static final String DATETIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

	// formati numerici
	public static final String BIGDECIMAL_FORMAT = "#.##";

	// formato email
//	public static final String EMAIL_FORMAT = ".+@.+";
	
	public static final String EMAIL_FORMAT = "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*";

	public static Timestamp date2timestap ( Date date ) {
		Timestamp timestamp = null;

		if (date != null) {
			timestamp = new Timestamp(date.getTime());
		}

		return timestamp;
	}

	public static String date2str ( Date date, String format ) {
		String str = null;

		if (date != null) {
			str = (new SimpleDateFormat(format)).format(date);
		}

		return str;
	}

	public static String date2strdate ( Date date ) {
		return date2str(date, DATE_FORMAT);
	}

	public static String date2strtime ( Date date ) {
		return date2str(date, TIME_FORMAT);
	}

	public static String date2strdatetime ( Date date ) {
		return date2str(date, DATETIME_FORMAT);
	}

	public static String calendar2str ( GregorianCalendar calendar, String format ) {
		String str = null;

		if (calendar != null) {
			str = (new SimpleDateFormat(format)).format(calendar.getTime());
		}

		return str;
	}

	public static String calendar2strdate ( GregorianCalendar calendar ) {
		return calendar2str(calendar, DATE_FORMAT);
	}

	public static String calendar2strtime ( GregorianCalendar calendar ) {
		return calendar2str(calendar, TIME_FORMAT);
	}

	public static String calendar2strdatetime ( GregorianCalendar calendar ) {
		return calendar2str(calendar, DATETIME_FORMAT);
	}

    public static XMLGregorianCalendar dateToXMLGregorianCalendar ( Date date ) throws DatatypeConfigurationException {
        if ( null == date ) {
            return null;
        } else {
            GregorianCalendar c = new GregorianCalendar ();
            c.setTime ( date );
			return DatatypeFactory.newInstance ().newXMLGregorianCalendar ( c );
        }
    }

	public static String int2str ( Integer n ) {
		String str = null;

		if (n != null) {
			str = Integer.toString(n);
		}

		return str;
	}

	public static String long2str ( Long n ) {
		String str = null;

		if (n != null) {
			str = Long.toString(n);
		}

		return str;
	}

	public static BigDecimal str2importo ( String s ) {
		BigDecimal bd = null;

		if (blank2null(s) != null) {
			String r = s.replace(',', '.');
			String n = r;
			if (r.endsWith(".0") || r.endsWith(".00")) {
                n = r.substring(0, r.indexOf("."));
            }

			bd = new BigDecimal(n);
		}

		return bd;
	}

	public static String bd2str ( BigDecimal bd, String format ) {
		String str = null;

		if (bd != null) {
			str = (new DecimalFormat(format)).format(bd);
		}

		return str;
	}

	public static String bd2str ( BigDecimal bd ) {
		return bd2str(bd, BIGDECIMAL_FORMAT);
	}

	public static Integer bd2int ( BigDecimal bd ) {
		Integer n = null;

		if (bd != null) {
			n = bd.intValue();
		}

		return n;
	}

	public static Long bd2long ( BigDecimal bd ) {
		Long n = null;

		if (bd != null) {
			n = bd.longValue();
		}

		return n;
	}

	public static String quote ( String s ) {
		StringBuilder sb = new StringBuilder();
		if (blank2null(s) != null) {
			sb.append("\"");
			sb.append(s);
			sb.append("\"");
		} else {
			sb.append("null");
		}
		return sb.toString();
	}

	public static Date getLaterDate ( Date date ) {
		Date laterDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			laterDate = calendar.getTime();
		}
		return laterDate;
	}

	public static String formatException ( Exception e ) {
		return formatException("", e);
	}

	public static String formatException ( String msg, Exception e ) {
		return msg + e.getMessage() != null ? e.getClass().getName() + ": " + e.getMessage() : e.getClass().getName();
	}

	public static String buildIdMessaggio ( Date now, String codVersamento, Long idFlusso ) {
		return buildIdMessaggio(now, codVersamento, idFlusso, 1);
	}

	//@formatter:off
	public static String buildIdMessaggio ( Date now, String codVersamento, Long idFlusso, int numProgressivoInvio ) {
		return Util.date2str(now, Util.DATE_Y4M2D2)
				.concat("-")
				.concat(codVersamento)
				.concat("-")
				.concat(Long.toString(idFlusso))
				.concat("-")
				.concat(String.format("%03d", numProgressivoInvio));
	}
	//@formatter:on

	public static String buildIdMessaggio ( Date now, String codVesamento, Long idFlusso, String idMessaggio ) {
		return buildIdMessaggio(now, codVesamento, idFlusso, getProgressivoInvio(idMessaggio) + 1);
	}

	/* DEV/CSI_PAG-2411 - BEGIN */
	private static long previousTimestamp = 0;
	
	public static synchronized long generateUniqueTimestamp () {
		long currentTimestamp = System.currentTimeMillis ();
		while ( currentTimestamp <= previousTimestamp ) {
			currentTimestamp++;
		}
		previousTimestamp = currentTimestamp;
		return currentTimestamp;
	}

	//@formatter:off
	public static String buildIdPosioneDebitoriaEsterna (String codFiscaleEnte, Long idUtente) {
		return codFiscaleEnte
				.concat("_")
				.concat(String.format ("%04d", idUtente)
				.concat("_")
				.concat(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date (generateUniqueTimestamp()))));
	}
	//@formatter:on
	/* DEV/CSI_PAG-2411 - END */

	private static int getIndexDelProgressivo ( String idMessaggio ) {
		return idMessaggio.lastIndexOf('-') + 1;
	}

	public static int getProgressivoInvio ( String idMessaggio ) {
		int indexDelProgressivo = getIndexDelProgressivo(idMessaggio);
		String progressivoInvio = idMessaggio.substring(indexDelProgressivo);
		return Integer.parseInt(progressivoInvio);
	}

	public static String buildIdMessaggioIncrementato ( String idMessaggio ) {
		int indexDelProgressivo = getIndexDelProgressivo(idMessaggio);
		int numProgressivoInvio = getProgressivoInvio(idMessaggio);
		String idMessaggioNew = idMessaggio.substring(0, indexDelProgressivo).concat(String.format("%03d", numProgressivoInvio + 1));
		return idMessaggioNew;
	}

	public static String blank2null ( String s ) {
		String r = null;

		if (s != null) {
			r = s.trim().isEmpty() ? null : s;
		}

		return r;
	}

    public static <T extends ReportBaseDto> T toDto ( EpaypaTReport report, Class<T> type ) {
        if ( null == report )
            return null;
        
        T reportDto;
		try {
			
			reportDto = type.newInstance();
			reportDto.setId( report.getId() );
			reportDto.setIdEnte( report.getIdEnte() );
			reportDto.setCodiceFiscaleEnte( report.getCodiceFiscaleEnte () );
			reportDto.setIdUtente( report.getIdUtente() );
	        reportDto.setCodiceFiscaleUtente( report.getCodiceFiscaleUtente() );
	        reportDto.setIdFile( null != report.getEpaypaTFileReport () ? report.getEpaypaTFileReport ().getId () : null );
	        reportDto.setNomeFile( null != report.getEpaypaTFileReport () ? report.getEpaypaTFileReport ().getNomeFile () : null );
	        reportDto.setNominativoExport( report.getNominativoExport () + "-" + report.getId () );
	        reportDto.setStatoReport( toDto ( report.getEpaypaDStatoReport () ) );
	        reportDto.setTipoFileReport( toDto ( report.getEpaypaDTipoFileReport () ) );
	        reportDto.setDataInserimento(  report.getDataInserimento () );
	        reportDto.setDataModifica(report.getDataModifica());
	        reportDto.setTipoReportDto( toDto ( report.getEpaypaDTipoReport () ) );
	        
	        if ( null != report.getEpaypaTDatiFiltroReports() && !CollectionUtils.isEmpty ( report.getEpaypaTDatiFiltroReports () ) ) {
	            
	        	List<DatiFiltroReportDto> datiFiltroReportDtoList = new ArrayList<>();
	        	
	        	report.getEpaypaTDatiFiltroReports ().forEach ( r -> datiFiltroReportDtoList.add ( DatiFiltroReportDto.builder()
	        																						.withId(r.getId())
	        																						.withNomeFiltro(NomeFiltroReportEnum.fromId(r.getNomeFiltro()))
	        																						.withTipoCampoFiltro(TipoCampoFiltroEnum.fromId(r.getEpaypaDTipoCampoFiltro().getCodice()))
	        																						.withValore(r.getValore())
	        																						.build()));
	        	reportDto.loadDatiFiltroReportDto(datiFiltroReportDtoList);
	        }
	        
			return reportDto;
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
			
			throw new IllegalArgumentException( "ERRORE DURANTE LA CREAZIONE DEL ReporBaseDto: ", e );
		}
    }

    public static TipoReportDto toDto ( EpaypaDTipoReport epaypaDTipoReport ) {
    	if ( epaypaDTipoReport == null ) {
    		return null;
    	} else {
    		return TipoReportDto.builder ()
    				.withCodice( epaypaDTipoReport.getCodice() )
    				.withDescrizione( epaypaDTipoReport.getDescrizione() )
    				.withId( epaypaDTipoReport.getId() )
    				.build();
    	}
    }
    
    public static StatoReportDto toDto ( EpaypaDStatoReport stato ) {
        if ( null == stato )
            return null;
        return StatoReportDto.builder ()
            .withCodice ( stato.getCodice () )
            .withDescrizione ( stato.getDescrizione () )
            .withId ( stato.getId () )
            .build ();
    }

    public static TipoFileReportDto toDto ( EpaypaDTipoFileReport tipoFile ) {
        if ( null == tipoFile )
            return null;
        return TipoFileReportDto.builder ()
            .withCodice ( tipoFile.getCodice () )
            .withDescrizione ( tipoFile.getDescrizione () )
            .withId ( tipoFile.getId () )
            .build ();
    }

	public static Date xmlGregorianCalendarToDate ( XMLGregorianCalendar calendar ) {
        if ( calendar == null ) {
            return null;
        }
        return calendar.toGregorianCalendar ().getTime ();
    }

    public static Timestamp xmlGregorianCalendarToTimestamp ( XMLGregorianCalendar calendar ) {
        if ( calendar == null ) {
            return null;
        }
        return new Timestamp ( calendar.toGregorianCalendar ().getTime ().getTime () );
    }
    
    public static boolean checkCodiceFiscaleValido(String codiceFiscale)
    {
    	if (StringUtils.isBlank(codiceFiscale))
    	{
    		return false;
    	}
    	if (!codiceFiscale.matches("[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$"))
    	{
    		return false;
    	}
    	
//    	 verifica del carattere di controllo.
    	 
    	 String set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	 String set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	 String setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	 String setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
         int s = 0;
         for( int i = 1; i <= 13; i += 2 )
             s += setpari.indexOf( set2.charAt( set1.indexOf( codiceFiscale.charAt(i) )));
         for( int i = 0; i <= 14; i += 2 )
             s += setdisp.indexOf( set2.charAt( set1.indexOf( codiceFiscale.charAt(i) )));
         if( s%26 != codiceFiscale.charAt(15)-"A".charAt(0) )
             return false;
         return true;
    	
    }

	public static boolean checkCodicePivaValida(String pIva) {
		if (StringUtils.isBlank(pIva))
    	{
    		return false;
    	}
		
		if (!pIva.matches("[0-9]{11}$"))
    	{
    		return false;
    	}
		
		
		int s = 0;
		for( int i = 0; i <= 9; i += 2 )
			s += pIva.charAt(i) - "0".charAt(0);
		for(int i = 1; i <= 9; i += 2 ){
			int c = 2*( pIva.charAt(i) - "0".charAt(0) );
			if( c > 9 )  c = c - 9;
			s += c;
		}
		if( ( 10 - s%10 )%10 != pIva.charAt(10) - "0".charAt(0) )
			return false;
		return true ;
	}

}
