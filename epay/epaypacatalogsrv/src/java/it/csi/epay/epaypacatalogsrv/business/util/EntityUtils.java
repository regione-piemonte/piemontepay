/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileStoricoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.model.AbstractChangeTrackedParentEntity;
import it.csi.epay.epaypacatalogsrv.model.Fruitore;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;

public interface EntityUtils {

    public static String formatMessage ( String messageRaw, Object... parameters ) {
        return String.format ( messageRaw, parameters );
    }

    public static boolean isTrue ( Boolean raw ) {
        return raw != null && raw.booleanValue ();
    }

    public static boolean isFalse ( Boolean raw ) {
        return raw == null || !raw.booleanValue ();
    }

    public static boolean equals ( Object o1, Object o2 ) {
        if ( o1 == null && o2 == null ) {
            return true;
        }
        if ( o1 == null && o2 != null || o1 != null && o2 == null ) {
            return false;
        }

        return o1.equals ( o2 );
    }

    @SuppressWarnings ( "unchecked" )
    public static <T> T shallowCopy ( T input ) {

        T output;

        try {
            output = (T) input.getClass ().newInstance ();
        } catch ( InstantiationException | IllegalAccessException e1 ) {
            throw new RuntimeException ( e1 );
        }

        try {
            PropertyUtils.copyProperties ( output, input );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new RuntimeException ( e );
        }

        return output;
    }

    public static void copyProperties ( Object output, Object input ) {

        try {
            PropertyUtils.copyProperties ( output, input );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new RuntimeException ( e );
        }
    }

	public static void populateEditFields(AbstractChangeTrackedParentEntity entity) {
		if (entity == null) {
			return;
		}

		String codiceFiscaleUtenteVariazione;
		PrincipalVO principal = SecurityUtils.getPrincipal();
		if (principal != null) {
            codiceFiscaleUtenteVariazione = principal.getUtente ().getCodiceFiscale ();
		} else {
			codiceFiscaleUtenteVariazione = Constants.CODICE_UTENTE_SISTEMA;
		}

        Timestamp now = Timestamp.valueOf ( LocalDateTime.now () );

        if ( entity.getDataInserimento () == null ) {
            entity.setDataInserimento ( now );
            entity.setUtenteInserimento ( codiceFiscaleUtenteVariazione );
        }

        entity.setDataModifica ( now );
        entity.setUtenteModifica ( codiceFiscaleUtenteVariazione );
	}

    public static boolean isValidCodiceFiscale ( String codFiscale ) {
        boolean ret = false;
        if ( codFiscale != null ) {
            if ( codFiscale.toUpperCase ().matches ( "[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]" ) ) {
                ret = true;
            }
        }

        return ret;
    }

    public static boolean isValidEmail ( String raw ) {
        if ( raw == null || raw.isEmpty () ) {
            return true;
        }

        return ( raw.matches ( "[a-zA-Z0-9_\\.\\+\\-]+@[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)*"  ) );
    }

    public static boolean isEmpty ( RicercaCodiceVersamentoInput input ) {
        return ( StringUtils.isBlank ( input.getCodiceVoceEntrata () ) &&
            StringUtils.isBlank ( input.getDescrizione () ) &&
            StringUtils.isBlank ( input.getIban () ) &&
            StringUtils.isBlank ( input.getCodiceTematica () ) &&
            StringUtils.isBlank ( input.getCodiceMacrotipo () ) );
    }

    public static boolean isEmpty ( RicercaVoceEntrataInput input ) {
        return ( StringUtils.isBlank ( input.getCodice () ) &&
            StringUtils.isBlank ( input.getDescrizione () ) &&
            StringUtils.isBlank ( input.getCodiceTematica () ) &&
            StringUtils.isBlank ( input.getCodiceMacrotipo () ) );
    }

    public static boolean isEmpty ( RicercaRiferimentoContabileInput input ) {
        return ( StringUtils.isBlank ( input.getCodiceMacrotipo () ) &&
            StringUtils.isBlank ( input.getCodiceTematica () ) &&
            StringUtils.isBlank ( input.getDescrizioneCodiceVersamento () ) &&
            StringUtils.isBlank ( input.getCodiceVoceEntrata () ) &&
            input.getIdCodiceVersamento () == null );
    }
    
//    public static boolean isEmpty ( RicercaTassonomiaInput input ) {
//        return ( input != null && StringUtils.isBlank ( input.get () ) &&
//            StringUtils.isBlank ( input.getCodiceTematica () ) &&
//            StringUtils.isBlank ( input.getDescrizioneCodiceVersamento () ) &&
//            StringUtils.isBlank ( input.getCodiceVoceEntrata () ) &&
//            input.getIdCodiceVersamento () == null );
//    }

    public static LocalDate toLocalDate ( Date date ) {
        if ( date == null ) {
            return null;
        }

        if ( date instanceof java.sql.Date ) {
            return ( (java.sql.Date) date ).toLocalDate();
        } else {
            return date.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }

    }

    public static boolean isValid(RiferimentoContabile entity) {
        if (entity.getDataInizioValidita () == null) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate inizio = toLocalDate ( entity.getDataInizioValidita () );

        if ( inizio.isAfter ( now ) ) {
            return false;
        }

        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );
        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now ) ) {
            return true;
        }

        return false;
    }

    public static boolean isValid ( Fruitore entity ) {
        if ( entity.getDataInizioValidita () == null ) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate inizio = toLocalDate ( entity.getDataInizioValidita () );

        if ( inizio.isAfter ( now ) ) {
            return false;
        }

        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );
        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now ) ) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty ( RicercaUtenteInput input ) {
        return ( StringUtils.isBlank ( input.getCodiceFiscale () ) &&
            StringUtils.isBlank ( input.getNome () ) &&
            StringUtils.isBlank ( input.getCognome () ) &&
            StringUtils.isBlank ( input.getEmail () ) &&
            StringUtils.isBlank ( input.getCodiceCategoriaCdu () ) &&
            StringUtils.isBlank ( input.getCodiceCdu () ) &&
            StringUtils.isBlank ( input.getCodiceTematica () ) &&
            input.getIdCodiceVersamento () == null &&
            !isTrue ( input.getSoloUtentiInVita () ) );
    }

    public static boolean isValid ( RicercaRiferimentoContabileOutputDto entity ) {
        if ( entity.getDataInizioValidita () == null ) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate inizio = toLocalDate ( entity.getDataInizioValidita () );

        if ( inizio.isAfter ( now ) ) {
            return false;
        }

        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );
        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now ) ) {
            return true;
        }

        return false;
    }

    public static boolean isRiferimentoInVita ( RicercaRiferimentoContabileOutputDto entity ) {
        if ( entity.getDataInizioValidita () == null ) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );

        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now ) || fine.isEqual ( now ) ) {
            return true;
        }

        return false;
    }

    public static boolean isRiferimentoInVita ( RiferimentoContabile entity ) {
        if ( entity.getDataInizioValidita () == null ) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );

        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now ) || fine.isEqual ( now )  ) {
            return true;
        }

        return false;
    }

    public static boolean isRiferimentoInVita ( RicercaRiferimentoContabileStoricoOutputDto entity ) {
        if ( entity.getDataInizioValidita () == null ) {
            return false;
        }

        LocalDate now = LocalDate.now ();
        LocalDate fine = toLocalDate ( entity.getDataFineValidita () );

        if ( fine == null ) {
            return true;
        }

        if ( fine.isAfter ( now )|| fine.isEqual ( now )  ) {
            return true;
        }

        return false;
    }

    public static Date toDate ( LocalDate input ) {
        return java.util.Date.from ( input.atStartOfDay ( ZoneId.systemDefault () ).toInstant () );
    }
}
