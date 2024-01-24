/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.util.coop;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.CodiceVersamentoType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.RiferimentoContabileType;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop.CoopPecException;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplication;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFields;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationCustomFieldsTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetail;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationDetailTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTApplicationTemp;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnte;
import it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model.MdpTEnteTemp;


public class TempUtility {

    public static final String AZIONE_MODIFICA = "M";

    public static final String AZIONE_INSERIMENTO = "I";

    public static final String AZIONE_CANCELLAZIONE = "C";

    public static MdpTEnte buildEnte ( MdpTEnte enteCurr, MdpTEnteTemp enteTemp ) {

        enteCurr.setAttivo ( enteTemp.getAttivo () );
        enteCurr.setCodice_segregazione ( enteTemp.getCodice_segregazione () );
        enteCurr.setDescrizione ( enteTemp.getDescrizione () );

        enteCurr.setFlag_invio_flusso_base ( enteTemp.getFlag_invio_flusso_base () );
        enteCurr.setFlag_invio_flusso_esteso ( enteTemp.getFlag_invio_flusso_esteso () );
        enteCurr.setPartita_iva ( enteTemp.getPartita_iva () );
        
        enteCurr.setProgressivo_application_id ( enteTemp.getProgressivo_application_id () );
        
        return enteCurr;
    }

    public static MdpTEnteTemp buildEnteTemp ( MdpTEnte ente, int progressivo ) {
        MdpTEnteTemp enteTemp = new MdpTEnteTemp ();

        enteTemp.setAttivo ( ente.getAttivo () );
        enteTemp.setCodice_segregazione ( ente.getCodice_segregazione () );
        enteTemp.setDescrizione ( ente.getDescrizione () );
        enteTemp.setEnte_id ( ente.getEnte_id () );
        enteTemp.setFlag_invio_flusso_base ( ente.getFlag_invio_flusso_base () );
        enteTemp.setFlag_invio_flusso_esteso ( ente.getFlag_invio_flusso_esteso () );
        enteTemp.setPartita_iva ( ente.getPartita_iva () );
        enteTemp.setProgressivo_application_id ( progressivo);

        return enteTemp;
    }

    public static void checkDatiObbligatori ( CodiceVersamentoType codiceVersamento, String azione, String idOperazione ) throws CoopPecException {

        if ( !StringUtils.isNotBlank ( azione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }
        if ( !StringUtils.isNotBlank ( idOperazione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( codiceVersamento.getCFEnte () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( codiceVersamento.getCodice () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice versamento non presente" ) );
        }
    }

    public static void checkDatiObbligatori ( RiferimentoContabileType riferimentoContabile, String azione, String idOperazione ) throws CoopPecException {

        if ( !StringUtils.isNotBlank ( azione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( idOperazione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getCodiceFiscaleEnte () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getCodiceVersamento () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice versamento non presente" ) );
        }

        //chiedere a Piero e lollo.
        //        if ( null != riferimentoContabile.getNumeroCapitolo () ) {
        //            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
        //                String.format ( "Capitolo non presente" ) );
        //        }

        //        if ( null != riferimentoContabile.getNumeroArticolo () ) {
        //            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
        //                String.format ( "Articolo non presente" ) );
        //        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getDatoSpecificoRiscossione () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Dati specifici riscossione non presenti" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getLivelloPDC () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Livello pdc non presente" ) );
        }
    }

    public static <E extends Enum<E>> boolean contains ( Class<E> enumClass, String value ) {
        try {
            return EnumSet.allOf ( enumClass )
                .contains ( Enum.valueOf ( enumClass, value ) );
        } catch ( Exception e ) {
            return false;
        }
    }

    //    public static List<MdpTCodiceVersamentoTemp> creaCodiciVersamento ( MdpTEnteTemp enteTemp, List<String> codiciRiferimento ) {
    //        List<MdpTCodiceVersamentoTemp> codiceVersamentoList = new ArrayList<MdpTCodiceVersamentoTemp> ();
    //        for ( String codiceRiferimento: codiciRiferimento ) {
    //            MdpTCodiceVersamentoTemp codiceVersamentoTemp = new MdpTCodiceVersamentoTemp ();
    //
    //            codiceVersamentoTemp.setIdOperazione ( enteTemp.getIdOperazione () );
    //            //codiceVersamentoTemp.setIdEnte ( enteTemp.getIdEnte () );
    //            codiceVersamentoTemp.setAzione ( AZIONE_MODIFICA );
    //
    //            codiceVersamentoTemp.setDtInizioValidita ( enteTemp.getDtInizioValidita () );
    //            codiceVersamentoTemp.setDtFineValidita ( enteTemp.getDtFineValidita () );
    //
    //            // - Tracciatura - codiceVersamentoTemp.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //            // - Tracciatura - codiceVersamentoTemp.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //            // - Tracciatura - codiceVersamentoTemp.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //            // - Tracciatura - codiceVersamentoTemp.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //            codiceVersamentoTemp.setCodVersamento ( codiceRiferimento );
    //            codiceVersamentoTemp.setCodFiscaleEnte ( enteTemp.getCodFiscaleEnte () );
    //            codiceVersamentoTemp.setDescrizione ( enteTemp.getDenominazione () );
    //
    //            codiceVersamentoTemp.setCodiceModalitaIntegrazione ( null );
    //
    //            codiceVersamentoList.add ( codiceVersamentoTemp );
    //        }
    //        return codiceVersamentoList;
    //    }

    //    public static MdpTCodiceVersamentoTemp createCodiceVersamento ( MdpTEnte enteEntity, CodiceVersamentoType codiceVersamento, String azione,
    //        String idOperazione )
    //                    throws CoopPecException {
    //        MdpTCodiceVersamentoTemp entity = new MdpTCodiceVersamentoTemp ();
    //
    //        checkDatiObbligatori ( codiceVersamento, azione, idOperazione );
    //
    //        if ( null == enteEntity ) {
    //            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
    //                String.format ( "Ente non trovato per il codice fiscale %s", codiceVersamento.getCFEnte () ) );
    //        }
    //
    //        entity.setCodFiscaleEnte ( codiceVersamento.getCFEnte () );
    //        entity.setCodVersamento ( codiceVersamento.getCodice () );
    //        entity.setDescrizione ( codiceVersamento.getDescrizione () );
    //
    //        entity.setDtInizioValidita ( new Timestamp ( new Date ().getTime () ) );
    //        entity.setDtFineValidita ( null );
    //
    //        // - Tracciatura - entity.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
    //        // - Tracciatura - entity.setDataModifica ( entity.getDataInserimento () );
    //        entity.setCodFiscaleEnte ( enteEntity.getCodFiscaleEnte () );
    //        entity.setIdOperazione ( idOperazione );
    //
    //        // - Tracciatura - entity.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //        // - Tracciatura - entity.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //
    //        if ( codiceVersamento.getModalitaDiIntegrazione () != null ) {
    //            entity.setCodiceModalitaIntegrazione ( codiceVersamento.getModalitaDiIntegrazione ().value () );
    //        }
    //
    //        entity.setAzione ( azione );
    //
    //        return entity;
    //    }

    //    public static List<MdpTCodiceVersamento> elaboraCodiciVersamento ( List<MdpTCodiceVersamento> codiceVersamentoList,
    //        List<MdpTCodiceVersamentoTemp> codiceVersamentoTempList ) {
    //        List<MdpTCodiceVersamento> codiceVersamentoListcatalogsToUpdate = new ArrayList<MdpTCodiceVersamento> ();
    //        if ( null != codiceVersamentoList && !codiceVersamentoList.isEmpty () ) {
    //            //se ho codice versamento per l'ente in questione procedo con la logica di decisione della dataFineValidita'.
    //            //confronto e aggiornamento
    //            if ( null != codiceVersamentoTempList && !codiceVersamentoTempList.isEmpty () ) {
    //                //Se mi vengono passati codici versamnto vado a confrontarli con quelli presenti lato DB in seguito valizzo o meno la dataFineVal.
    //                for ( MdpTCodiceVersamento codiceVersamento: codiceVersamentoList ) {
    //                    Boolean trovato = false;
    //                    for ( MdpTCodiceVersamentoTemp codiceVersamentoTemp: codiceVersamentoTempList ) {
    //                        if ( codiceVersamentoTemp.compareTo ( codiceVersamento ) == 0 ) {
    //                            //Se trovo una corrispondenza nel dubbio elimino la data fine validita'
    //                            trovato = true;
    //                            //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
    //                            //La data fine validita' non c'e' come campo per cui questo test non deve essere eseguito
    //                            //if ( null != codiceVersamento.getDataFineValidita () ) {
    //                            //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
    //                            //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //                            //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //                            //codiceVersamento.setDataFineValidita ( null );
    //                            //    codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
    //                            //}
    //                            break;
    //                        }
    //                    }
    //                    if ( !trovato ) {
    //                        //se non trovato significa che devo chiudere il recordo presente sul DB valorizzando la data fine validita'
    //                        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
    //                        //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //                        //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //                        //codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //                        codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
    //                    }
    //                }
    //            } else {
    //                //datafineval current a tutte
    //                for ( MdpTCodiceVersamento codiceVersamento: codiceVersamentoList ) {
    //                    //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
    //                    //codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //                    //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
    //                    //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
    //                    codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
    //                }
    //            }
    //        } else {
    //            //Se lato DB l'ente in questione non ha codiciVersamento
    //            if ( ( null == codiceVersamentoTempList || codiceVersamentoTempList.isEmpty () ) == false ) {
    //                throw new IllegalArgumentException ( "Codici Versamento non censiti." );
    //            }
    //            //nel caso abbia ricorrenze passate in input lancio un errore in quanto non posso inserire su DB.
    //
    //        }
    //        return codiceVersamentoListcatalogsToUpdate;
    //    }

    //    public static MdpTCodiceVersamento mapCblTCodiceVersamentoSenzaIdEnte ( MdpTCodiceVersamento codiceVersamento,
    //        MdpTCodiceVersamentoTemp codiceVersamentoTemp ) {
    //
    //        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
    //        //codiceVersamento.setCodiceFiscaleEnte ( codiceVersamentoTemp.getCodiceFiscaleEnte () );
    //        codiceVersamento.setDtFineValidita ( codiceVersamentoTemp.getDtFineValidita () );
    //        codiceVersamento.setDtInizioValidita ( codiceVersamentoTemp.getDtInizioValidita () );
    //        //codiceVersamento.setDataInserimento ( codiceVersamentoTemp.getDataInserimento () );
    //        //codiceVersamento.setDataModifica ( codiceVersamentoTemp.getDataModifica () );
    //        //codiceVersamento.setUtenteInserimento ( codiceVersamentoTemp.getUtenteInserimento () );
    //        //codiceVersamento.setUtenteModifica ( codiceVersamentoTemp.getUtenteModifica () );
    //        //codiceVersamento.setFlagAnnullato ( codiceVersamentoTemp.getFlagAnnullato () );
    //
    //        codiceVersamento.setDescrizione ( codiceVersamentoTemp.getDescrizione () );
    //        codiceVersamento.setCodVersamento ( codiceVersamentoTemp.getCodVersamento () );
    //
    //        return codiceVersamento;
    //    }

    public static MdpTApplication buildApplication ( MdpTApplicationTemp from ) {
        MdpTApplication to = new MdpTApplication ();

        BeanUtils.copyProperties ( from, to );

        return to;
    }

    public static List<MdpTApplicationCustomFields> buildApplicationCustom ( List<MdpTApplicationCustomFieldsTemp> from ) {
        List<MdpTApplicationCustomFields> to = new ArrayList<MdpTApplicationCustomFields> ();

        for ( MdpTApplicationCustomFieldsTemp item: from ) {
            MdpTApplicationCustomFields curr = new MdpTApplicationCustomFields ();
            BeanUtils.copyProperties ( item, curr );
            curr.setFielddescription("nodospc v2");

            to.add ( curr );
        }

        return to;
    }

    public static List<MdpTApplicationDetail> buildApplicationDetail ( List<MdpTApplicationDetailTemp> from ) {
        List<MdpTApplicationDetail> to = new ArrayList<MdpTApplicationDetail> ();

        for ( MdpTApplicationDetailTemp item: from ) {
            MdpTApplicationDetail curr = new MdpTApplicationDetail ();
            BeanUtils.copyProperties ( item, curr );

            to.add ( curr );
        }

        return to;
    }

    public static List<MdpTApplicationCustomFieldsTemp> buildApplicationCustomTemp ( List<MdpTApplicationCustomFields> from,String idOperazione ) {
        List<MdpTApplicationCustomFieldsTemp> to = new ArrayList<MdpTApplicationCustomFieldsTemp> ();

        for ( MdpTApplicationCustomFields item: from ) {
            MdpTApplicationCustomFieldsTemp curr = new MdpTApplicationCustomFieldsTemp ();
            BeanUtils.copyProperties ( item, curr );
            curr.setFielddescription("nodospc v2");
            curr.setIdOperazione ( idOperazione );
            to.add ( curr );
        }

        return to;
    }

    public static MdpTApplicationTemp buildApplicationTemp ( MdpTApplication from,String idOperazione ) {
        MdpTApplicationTemp to = new MdpTApplicationTemp ();

        BeanUtils.copyProperties ( from, to );

        to.setIdOperazione ( idOperazione );
        
        return to;
    }

    public static List<MdpTApplicationDetailTemp> buildApplicationDetailTemp ( List<MdpTApplicationDetail> from,String idOperazione ) {
        List<MdpTApplicationDetailTemp> to = new ArrayList<MdpTApplicationDetailTemp> ();

        for ( MdpTApplicationDetail item: from ) {
            MdpTApplicationDetailTemp curr = new MdpTApplicationDetailTemp ();
            BeanUtils.copyProperties ( item, curr );
            
            curr.setIdOperazione ( idOperazione );
            
            to.add ( curr );
        }

        return to;
    }

}
