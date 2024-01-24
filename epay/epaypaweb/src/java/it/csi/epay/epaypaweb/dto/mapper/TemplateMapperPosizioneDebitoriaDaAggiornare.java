/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;

public class TemplateMapperPosizioneDebitoriaDaAggiornare implements TemplateMapper<PosizioneDebitoriaDaAggiornareDto> {

	//@formatter:off
	@Override
	public Object getValue(FlussoDto head, PosizioneDebitoriaDaAggiornareDto item, CampoFlussoEnum campoFlusso) {
		if (item != null && campoFlusso != null) {
			switch (campoFlusso) {
				case AGPD_TIPO_AGG_POSIZIONE_DEBITORIA: return (item.getTipoAggiornamento() != null) ? item.getTipoAggiornamento().getDescrizione() : null;
				case AGPD_ID_POSIZIONE_DEBITORIA_EST: return item.getIdPosizioneDebitoriaEsterna();
				case AGPD_MOTIVAZIONE: return item.getMotivazione();
				case AGPD_CODICE_AVVISO: return item.getCodAvviso();
				case AGPD_CODICE_ESITO: return item.getCodEsito();
				case AGPD_DETTAGLIO_ESITO: return item.getDetEsito();
				case AGPD_DATA_INIZIO_VALIDITA: return item.getDataInizioValidita();
				case AGPD_DATA_FINE_VALIDITA: return item.getDataFineValidita();
				case AGPD_DATA_SCADENZA: return item.getDataScadenza();
				case AGPD_DATA_IMPORTO_TOTALE: return item.getImportoTotale();
				case AGPD_DESCRIZIONE_CAUSALE_VERSAMENTO: return item.getDesCausaleVersamento();
				case CI_IMPORTO_1 :
					if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 0 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
					return null;
				case CI_CAUSALE_1 :
					if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 0 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
					return null;
				case CI_DATI_SPECIFICI_RISCOSSIONE_1 :
					if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 0 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getDatiSpecificiRiscossione ();
						}
					}
					return null;
				case CI_ANNO_ACCERTAMENTO_1 :
					if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 0 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getAnnoAccertamento ();
						}
					}
					return null;
				case CI_NUMERO_ACCERTAMENTO_1 :
					if ( item.getComponenteImportoDtoList () != null && !item.getComponenteImportoDtoList ().isEmpty () ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 0 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getNumeroAccertamento ();
						}
					}
					return null;
				case CI_IMPORTO_2 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 1 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
					return null;
				case CI_CAUSALE_2: 
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList().size() > 1 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 1 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
					return null;
				case CI_DATI_SPECIFICI_RISCOSSIONE_2 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 1 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getDatiSpecificiRiscossione ();
						}
					}
					return null;
				case CI_ANNO_ACCERTAMENTO_2 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 1 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getAnnoAccertamento ();
						}
					}
					return null;
				case CI_NUMERO_ACCERTAMENTO_2 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 1 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 1 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getNumeroAccertamento ();
						}
					}
					return null;
				case CI_IMPORTO_3 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 2 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
					return null;
				case CI_CAUSALE_3 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 2 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
					return null;
				case CI_DATI_SPECIFICI_RISCOSSIONE_3 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 2 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getDatiSpecificiRiscossione ();
						}
					}
					return null;
				case CI_ANNO_ACCERTAMENTO_3 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 2 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getAnnoAccertamento ();
						}
					}
					return null;
				case CI_NUMERO_ACCERTAMENTO_3 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 2 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 2 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getNumeroAccertamento ();
						}
					}
					return null;
				case CI_IMPORTO_4 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 3 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
					return null;
				case CI_CAUSALE_4 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 3 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
					return null;
				case CI_DATI_SPECIFICI_RISCOSSIONE_4 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 3 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getDatiSpecificiRiscossione ();
						}
					}
					return null;
				case CI_ANNO_ACCERTAMENTO_4 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 3 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getAnnoAccertamento ();
						}
					}
					return null;
				case CI_NUMERO_ACCERTAMENTO_4 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 3 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 3 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getNumeroAccertamento ();
						}
					}
					return null;
				case CI_IMPORTO_5 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 4 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getImporto ();
						}
					}
					return null;
				case CI_CAUSALE_5 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 4 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getCausale ();
						}
					}
					return null;
				case CI_DATI_SPECIFICI_RISCOSSIONE_5 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 4 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getDatiSpecificiRiscossione ();
						}
					}
					return null;
				case CI_ANNO_ACCERTAMENTO_5 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 4 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getAnnoAccertamento ();
						}
					}
					return null;
				case CI_NUMERO_ACCERTAMENTO_5 :
					if ( item.getComponenteImportoDtoList () != null && item.getComponenteImportoDtoList ().size () > 4 ) {
						ComponenteImportoDto c = item.getComponenteImportoDtoList ().get ( 4 );
						if ( c.getFlagComponenteSecondario () == null || !c.getFlagComponenteSecondario () ) {
							return c.getNumeroAccertamento ();
						}
					}
					return null;

				case CI_NOME_1 :
					return ( item.getRiferimentoPagamentoDtoList () != null && !item.getRiferimentoPagamentoDtoList ().isEmpty () )
									? item.getRiferimentoPagamentoDtoList ().get ( 0 ).getNome () : null;
				case CI_VALORE_1 :
					return ( item.getRiferimentoPagamentoDtoList () != null && !item.getRiferimentoPagamentoDtoList ().isEmpty () )
									? item.getRiferimentoPagamentoDtoList ().get ( 0 ).getValore () : null;
                case CI_NOME_2: return (item.getRiferimentoPagamentoDtoList () != null && item.getRiferimentoPagamentoDtoList().size() > 1) ? item.getRiferimentoPagamentoDtoList().get(1).getNome () : null;
                case CI_VALORE_2: return (item.getRiferimentoPagamentoDtoList() != null && item.getRiferimentoPagamentoDtoList().size() > 1) ? item.getRiferimentoPagamentoDtoList().get(1).getValore () : null;
                case CI_NOME_3: return (item.getRiferimentoPagamentoDtoList () != null && item.getRiferimentoPagamentoDtoList().size() > 2) ? item.getRiferimentoPagamentoDtoList().get(2).getNome () : null;
                case CI_VALORE_3: return (item.getRiferimentoPagamentoDtoList() != null && item.getRiferimentoPagamentoDtoList().size() > 2) ? item.getRiferimentoPagamentoDtoList().get(2).getValore () : null;
                case CI_NOME_4: return (item.getRiferimentoPagamentoDtoList () != null && item.getRiferimentoPagamentoDtoList().size() > 3) ? item.getRiferimentoPagamentoDtoList().get(3).getNome () : null;
                case CI_VALORE_4: return (item.getRiferimentoPagamentoDtoList() != null && item.getRiferimentoPagamentoDtoList().size() > 3) ? item.getRiferimentoPagamentoDtoList().get(3).getValore () : null;
                case CI_NOME_5: return (item.getRiferimentoPagamentoDtoList () != null && item.getRiferimentoPagamentoDtoList().size() > 4) ? item.getRiferimentoPagamentoDtoList().get(4).getNome () : null;
                case CI_VALORE_5: return (item.getRiferimentoPagamentoDtoList() != null && item.getRiferimentoPagamentoDtoList().size() > 4) ? item.getRiferimentoPagamentoDtoList().get(4).getValore () : null;
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
               default: return null;
			}
		} else
			return null;
	}
	//@formatter:on
}
