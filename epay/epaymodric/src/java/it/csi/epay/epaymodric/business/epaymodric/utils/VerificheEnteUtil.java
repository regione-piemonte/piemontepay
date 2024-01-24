/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;

/*
 * @Gueye
 *
 */
public class VerificheEnteUtil {

    enum EnteSchedulazioni {

        GIORNALIERA(1, 0), SETTIMANALE(2, 0), MENSILE(3, 1), BIMESTRALE(4, 2), TRIMESTRALE(5, 3), QUADRIMESTRALE(6,
            4), SEMESTRALE(7, 6), ANNUALE(8, 0), SINGOLOFLUSSO(99, 0);

        private final int key;

        private final int months;

        private EnteSchedulazioni(int key, int months) {
            this.key = key;
            this.months = months;
        }

        public int getKey() {
            return key;
        }

        public int getMonths() {
            return months;
        }

    }

    // Determina se lanciare il Motore Di Rconciliazione o non (in base al boolean
    // che ritorna)
    public static Boolean verificaEnteSchedulazione(CblTEnte ente, Calendar dateToStest) {
        List<CblTEnte> listaEnti = new ArrayList<CblTEnte>();

        listaEnti.add(ente);

        return verificaEnteSchedulazione(listaEnti, dateToStest);
    }

    private static boolean enteSchedulabile(CblTEnte ente, Calendar dateToStest) {

        if ( null == ente || null == ente.getPeriodicitaSchedulazione () ) {
            return false;
        }


        Integer periodicitaSchedulazione = ente.getPeriodicitaSchedulazione ();

        if (EnteSchedulazioni.GIORNALIERA.key == periodicitaSchedulazione) {
            return true;
        }

        if ( EnteSchedulazioni.SINGOLOFLUSSO.key == periodicitaSchedulazione ) {
            return true;
        }

        Integer giornoSchedulazione = ente.getGiornoSchedulazione ();

        if ( null == giornoSchedulazione ) {
            return false;
        }

        if (EnteSchedulazioni.SETTIMANALE.key == periodicitaSchedulazione) {
            //if (giornoSchedulazione == getGiornoDellaSettimana())
            //Se abbiamo 1 come giorno di schedulazione dobbiamo trovare a quale giorno corresponde facendo il confronto
            //con il giorno della settimana corrente(1...7)
            if ( getGiornoDellaSettimana ().equals ( giornoSchedulazione ) )
                return true;
        }

        if (EnteSchedulazioni.MENSILE.key == periodicitaSchedulazione) {
            if (giornoSchedulazione.equals(getGiornoDelMeseCorrente1_30(dateToStest)))
                return true;
        }

        if (EnteSchedulazioni.BIMESTRALE.key == periodicitaSchedulazione) {
            if (isGiornoValidoPerMensilita(giornoSchedulazione, EnteSchedulazioni.BIMESTRALE, dateToStest))
                return true;
        }

        if (EnteSchedulazioni.TRIMESTRALE.key == periodicitaSchedulazione) {
            if (isGiornoValidoPerMensilita(giornoSchedulazione, EnteSchedulazioni.TRIMESTRALE, dateToStest))
                return true;
        }

        if (EnteSchedulazioni.QUADRIMESTRALE.key == periodicitaSchedulazione) {
            if (isGiornoValidoPerMensilita(giornoSchedulazione, EnteSchedulazioni.QUADRIMESTRALE, dateToStest))
                return true;
        }

        if (EnteSchedulazioni.SEMESTRALE.key == periodicitaSchedulazione) {
            if (isGiornoValidoPerMensilita(giornoSchedulazione, EnteSchedulazioni.SEMESTRALE, dateToStest))
                return true;
        }

        if (EnteSchedulazioni.ANNUALE.key == periodicitaSchedulazione) {
            if (getGiornoDellAnno(giornoSchedulazione))
                return true;
        }

        return false;
    }

    private static boolean getGiornoDellAnno(int giornoSchedulazione) {
        Calendar cal = Calendar.getInstance();
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        return (dayOfYear == giornoSchedulazione);
    }

    private static Integer getMeseCorrente0_11(Calendar dateToStest) {
        return (dateToStest.get(Calendar.MONTH));
    }

    private static Integer getGiornoDelMeseCorrente1_30(Calendar dateToStest) {
        int dayOfMonth = dateToStest.get(Calendar.DAY_OF_MONTH);
        int month = dateToStest.get(Calendar.MONTH);

        // Commercialmente i mesi sono da 30gg, idem febbraio
        if (month == Calendar.FEBRUARY) {
            if (dayOfMonth == 29)
                dayOfMonth = 30;
            else if ((dayOfMonth == 28) && (!isBisestile()))
                dayOfMonth = 30;
        }

        if (dayOfMonth == 31) {
            dayOfMonth = 30;
        }

        return dayOfMonth;
    }

    private static boolean isBisestile() {
        Calendar calendar1 = Calendar.getInstance();
        int currYear = calendar1.get(Calendar.YEAR);
        Calendar calendar2 = new GregorianCalendar(currYear, 11, 31);
        int dayDecember31 = calendar2.get(Calendar.DAY_OF_YEAR);

        return (dayDecember31 > 365);
    }


    private static boolean isGiornoValidoPerMensilita(int giornoDiSchedulazione, EnteSchedulazioni periodicita,
        Calendar dateToStest) {

        // Caso dei 31 gg -> caso : bimestre 31 agosto
        if (giornoDiSchedulazione > 30) {
            giornoDiSchedulazione = 30;
        }

        if (giornoDiSchedulazione != getGiornoDelMeseCorrente1_30(dateToStest))
            return false;

        if (getMeseCorrente0_11(dateToStest) == Calendar.JANUARY)
            return true;

        if (periodicita == EnteSchedulazioni.BIMESTRALE) {
            if (getMeseCorrente0_11(dateToStest) == Calendar.MARCH)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.MAY)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.JULY)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.SEPTEMBER)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.NOVEMBER)
                return true;
        } else if (periodicita == EnteSchedulazioni.TRIMESTRALE) {
            if (getMeseCorrente0_11(dateToStest) == Calendar.APRIL)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.JULY)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.OCTOBER)
                return true;
        } else if (periodicita == EnteSchedulazioni.QUADRIMESTRALE) {
            if (getMeseCorrente0_11(dateToStest) == Calendar.MAY)
                return true;
            if (getMeseCorrente0_11(dateToStest) == Calendar.SEPTEMBER)
                return true;
        } else if (periodicita == EnteSchedulazioni.SEMESTRALE) {
            if (getMeseCorrente0_11(dateToStest) == Calendar.JULY)
                return true;
        }

        return false;
        // Vecchia gestione che teneva conto dell'offset del giorno nel periodo
        // Caso dei 31 gg -> caso : bimestre 31 agosto
        // if ( giornoDiSchedulazioneInBlock > ( periodicita.months * 30 ) ) {
        // giornoDiSchedulazioneInBlock = ( periodicita.months * 30 );
        // }
        // int monthTodayInBlock = getMeseCorrente1_12 ( dateToStest ) % period;
        // int dayTodayInBlock = getGiornoDelMeseCorrente1_30 ( dateToStest ) + ( 30 * (
        // monthTodayInBlock - 1 ) );
        // return ( giornoDiSchedulazioneInBlock == dayTodayInBlock );
    }

    public static Boolean verificaEnteSchedulazione(List<CblTEnte> listaEnti, Calendar dateToTest) {

        for (CblTEnte ente : listaEnti) {
            if (enteSchedulabile(ente, dateToTest) == true)
                return true;
            ;

        }
        return false;
    }

    private static Integer getGiornoDellaSettimana() {
        Calendar c = Calendar.getInstance(Locale.ITALY);
        c.setTime(new Date());
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int day = c.get(Calendar.DAY_OF_WEEK);
        switch (day) {
        case Calendar.MONDAY:
            day = 1;
            break;
        case Calendar.TUESDAY:
            day = 2;
            break;
        case Calendar.WEDNESDAY:
            day = 3;
            break;
        case Calendar.THURSDAY:
            day = 4;
            break;
        case Calendar.FRIDAY:
            day = 5;
            break;
        case Calendar.SATURDAY:
            day = 6;
            break;
        case Calendar.SUNDAY:
            day = 7;
            break;
        default:
            break;
        }

        return day;
    }

    // Nuru: Metodo che ti calcola il giorno della settimana passandogli il giorno di schedulazione come param
    public static Integer trovaGiornoDellaSettimanaPerGiornoDiSchedulazione(Integer giornoDiSchedulazione) {
        Calendar c = Calendar.getInstance(Locale.ITALY);
        c.setTime(new Date());
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, giornoDiSchedulazione);
        int day = c.get(Calendar.DAY_OF_WEEK);

        return day;

    }

}
