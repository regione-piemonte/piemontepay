/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import org.apache.commons.collections.CollectionUtils;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PagamentiExportDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;


public class TemplateMapperReportEnti implements TemplateMapper<PagamentiExportDto> {

    //@formatter:off
    @Override
    public Object getValue ( FlussoDto head, PagamentiExportDto item, CampoFlussoEnum campoFlusso ) {
        if ( item != null && campoFlusso != null ) {

            switch ( campoFlusso ) {
            //sostituire questi case aggiungendo i nuovi campi
            case PAGTI_IUV :
                return (item.getIUV () != null)?"'" + item.getIUV () : "";
            case PAGTI_CODICE_VERSAMENTO :
                return item.getCodiceVersamento ();
            case PAGTI_CODICE_AVVISO :
                return (item.getCodAvviso () != null)? "'" + item.getCodAvviso () : "";
            case PAGTI_POSIZ_DEBIT_EXT :
                return item.getIdPosizioneDebitoriaEsterna ();
            case PAGTI_IMPORTO_TOTALE :
                return item.getImportoTotale ();
			case PAGTI_DATA_SCADENZA : // in realta' riportiamo la data fine validita'!
				return item.getDataFineValidita ();
            case PAGTI_CAUSALE_VERS :
                return item.getDesCausaleVersamento ();
            case PAGTI_IMP_COMP_1 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 0
                ? item.getComponenteImportoDtoList ().get ( 0 ).getImporto () : null;
            case PAGTI_CAUSALE_COMP_1 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 0
                ? item.getComponenteImportoDtoList ().get ( 0 ).getCausale () : null;
            case PAGTI_DSR_COMP_1 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 0
                ? item.getComponenteImportoDtoList ().get ( 0 ).getDatiSpecificiRiscossione () : null;
            case PAGTI_ANNO_ACC_COMP_1 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 0
                ? item.getComponenteImportoDtoList ().get ( 0 ).getAnnoAccertamento () : null;
            case PAGTI_NUM_ACC_COMP_1 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 0
                ? item.getComponenteImportoDtoList ().get ( 0 ).getNumeroAccertamento () : null;
            case PAGTI_IMP_COMP_2 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1
                ? item.getComponenteImportoDtoList ().get ( 1 ).getImporto () : null;
            case PAGTI_CAUSALE_COMP_2 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1
                ? item.getComponenteImportoDtoList ().get ( 1 ).getCausale () : null;
            case PAGTI_DSR_COMP_2 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1
                ? item.getComponenteImportoDtoList ().get ( 1 ).getDatiSpecificiRiscossione () : null;
            case PAGTI_ANNO_ACC_COMP_2 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1
                ? item.getComponenteImportoDtoList ().get ( 1 ).getAnnoAccertamento () : null;
            case PAGTI_NUM_ACC_COMP_2 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1
                ? item.getComponenteImportoDtoList ().get ( 1 ).getNumeroAccertamento () : null;
            case PAGTI_IMP_COMP_3 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2
                ? item.getComponenteImportoDtoList ().get ( 2 ).getImporto () : null;
            case PAGTI_CAUSALE_COMP_3 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2
                ? item.getComponenteImportoDtoList ().get ( 2 ).getCausale () : null;
            case PAGTI_DSR_COMP_3 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2
                ? item.getComponenteImportoDtoList ().get ( 2 ).getDatiSpecificiRiscossione () : null;
            case PAGTI_ANNO_ACC_COMP_3 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2
                ? item.getComponenteImportoDtoList ().get ( 2 ).getAnnoAccertamento () : null;
            case PAGTI_NUM_ACC_COMP_3 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2
                ? item.getComponenteImportoDtoList ().get ( 2 ).getNumeroAccertamento () : null;
            case PAGTI_IMP_COMP_4 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3
                ? item.getComponenteImportoDtoList ().get ( 3 ).getImporto () : null;
            case PAGTI_CAUSALE_COMP_4 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3
                ? item.getComponenteImportoDtoList ().get ( 3 ).getCausale () : null;
            case PAGTI_DSR_COMP_4 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3
                ? item.getComponenteImportoDtoList ().get ( 3 ).getDatiSpecificiRiscossione () : null;
            case PAGTI_ANNO_ACC_COMP_4 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3
                ? item.getComponenteImportoDtoList ().get ( 3 ).getAnnoAccertamento () : null;
            case PAGTI_NUM_ACC_COMP_4 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3
                ? item.getComponenteImportoDtoList ().get ( 3 ).getNumeroAccertamento () : null;
            case PAGTI_IMP_COMP_5 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4
                ? item.getComponenteImportoDtoList ().get ( 4 ).getImporto () : null;
            case PAGTI_CAUSALE_COMP_5 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4
                ? item.getComponenteImportoDtoList ().get ( 4 ).getCausale () : null;
            case PAGTI_DSR_COMP_5 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4
                ? item.getComponenteImportoDtoList ().get ( 4 ).getDatiSpecificiRiscossione () : null;
            case PAGTI_ANNO_ACC_COMP_5 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4
                ? item.getComponenteImportoDtoList ().get ( 4 ).getAnnoAccertamento () : null;
            case PAGTI_NUM_ACC_COMP_5 :
                return item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4
                ? item.getComponenteImportoDtoList ().get ( 4 ).getNumeroAccertamento () : null;

            case PAGTI_NOME_VALORE:
                if ( !CollectionUtils.isEmpty ( item.getRiferimentoPagamentoDtoList () ) ) {
                    StringBuilder nomeValore = new StringBuilder ();
                    for(RiferimentoPagamentoDto riferimentoDto : item.getRiferimentoPagamentoDtoList ()) {
                        if(riferimentoDto != null) {
                            if(nomeValore.length()>0) {
                                nomeValore.append (" --- ");
                            }
                            nomeValore.append ( riferimentoDto.getNome () + ":" + riferimentoDto.getValore () );
                        }
                    }
                    return nomeValore.toString ();
                } else {
                    return null;
                }
            case PAGTI_TIPO_SOGG_DEB :
                return item.getSoggettoDebitore () != null && item.getSoggettoDebitore ().getTipologiaSoggettoEnum () != null
                ? item.getSoggettoDebitore ().getTipologiaSoggettoEnum ().getDes () : null;
            case PAGTI_CF_SOGG_DEB :
                return item.getSoggettoDebitore () != null ? item.getSoggettoDebitore ().getIdUnivocoFiscale () : null;
            case PAGTI_SOGGETTO_DEBITORE :
                if(item.getSoggettoDebitore ()!=null) {
                    if ( item.getSoggettoDebitore ().getRagioneSociale () != null ) {
                        return item.getSoggettoDebitore ().getRagioneSociale ();
                    } else {
                        return item.getSoggettoDebitore ().getCognome () + " " + item.getSoggettoDebitore ().getNome ();
                    }
                } else {
                    return null;
                }
            case PAGTI_EMAIL_SOGG_DEB :
                return item.getSoggettoDebitore () != null ? item.getSoggettoDebitore ().getEmail () : null;
            case PAGTI_NOTE_PAGATORE :
                return item.getNotePerPagatore ();
            case PAGTI_DATA_PAGAMENTO :
                return item.getDataPagamento ();
            case PAGTI_IMPORTO_PAGATO :
                return item.getImportoPagato ();
            case PAGTI_STATO_PAGAMENTO :
                return item.getStatoPagamento ();
                //RDI-23
            case PAGTI_CAUSALE :
                return item.getCausale ();
            case PAGTI_COSTI_NOTIFICA :
            	return item.getCostiDiNotifica();
			case MULTI_IMP_PRIN :
				return item.getImportoPrincipale ();
			case MULTI_IMP_SECONDARIO :
				return item.getImportoSecondarioAltroEnte ();
			case MULTI_CI_IMPORTO_1_SECONDARIO :
				if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
					for ( ComponenteImportoDto c: item.getComponenteImportoDtoList () ) {
						if ( c.getFlagComponenteSecondario () != null && c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
				}
				return null;
			case MULTI_CAUSALE_1_SECONDARIO :
				if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
					for ( ComponenteImportoDto c: item.getComponenteImportoDtoList () ) {
						if ( c.getFlagComponenteSecondario () != null && c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
				}
				return null;
            }
        }
        return null;
    }

    //@formatter:on
}
