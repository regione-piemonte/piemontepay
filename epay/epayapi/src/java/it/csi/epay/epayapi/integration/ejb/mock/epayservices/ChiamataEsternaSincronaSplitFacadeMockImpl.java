/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.integration.ejb.mock.epayservices;

import it.csi.epay.epayservices.interfaces.ejb.ChiamataEsternaSincronaSplitFacade;
import it.csi.epay.epayservices.model.*;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public class ChiamataEsternaSincronaSplitFacadeMockImpl implements ChiamataEsternaSincronaSplitFacade {

	private static final String ErroreGenerico = "100";

	private static final String ValidazioneCompleata = "000";

    private String validazioneComponenti ( List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> componenti, GetIuvCommonChiamanteEsternoInput input ) {
        for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: componenti ) {
            String componentType = " ";
            if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
				componentType = " secondarie ";
			}

            if ( componente.getProgressivo () == null ) {
                return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getImporto () == null ) {
                return ( String.format ( "Il campo importo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( StringUtils.isBlank ( componente.getCausale () ) ) {
                return ( String.format ( "Il campo causale delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getAnnoAccertamento () == null ) {
                return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );

            }
            if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) ) {
                return ( String.format ( "Il campo numeroAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return ( String.format ( "Il campo importo delle componenti%sdel pagamento non e' valido", componentType ) );
            }
            if ( componente.getProgressivo () < 0 ) {
                return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento non e' valido", componentType ) );
            }
            if ( componente.getAnnoAccertamento () < 0 ) {
                return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento non e' valido", componentType ) );
            }

        }
        return null;
    }
    private String validazioneComponentiDSR ( List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componenti, GetIuvCommonChiamanteEsternoInput input ) {
        for ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componente: componenti ) {
            String componentType = " ";
            if ( input instanceof GetIuvMultibeneficiarioChiamanteEsternoInput ) {
				componentType = " secondarie ";
			}
            
            if ( componente.getProgressivo () == null ) {
                return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getImporto () == null ) {
                return ( String.format ( "Il campo importo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( StringUtils.isBlank ( componente.getCausale () ) ) {
                return ( String.format ( "Il campo causale delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getAnnoAccertamento () == null ) {
                return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );
                
            }
            if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) ) {
                return ( String.format ( "Il campo numeroAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );
            }
            if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
                return ( String.format ( "Il campo importo delle componenti%sdel pagamento non e' valido", componentType ) );
            }
            if ( componente.getProgressivo () < 0 ) {
                return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento non e' valido", componentType ) );
            }
            if ( componente.getAnnoAccertamento () < 0 ) {
                return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento non e' valido", componentType ) );
            }
            
        }
        return null;
    }
    @Override
    public GetIuvChiamanteEsternoOutput getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input ) {
        GetIuvChiamanteEsternoOutput out = new GetIuvChiamanteEsternoOutput ();
        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo codiceFiscaleEnte e' obbligatorio" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getCausale () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo causale e' obbligatorio" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getTipoPagamento () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo tipoPagamento e' obbligatorio" );
            return out;
        }
        if ( input.getImporto () == null ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo e' obbligatorio" );
            return out;
        }
        if ( input.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo non e' valido" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getRagioneSociale () ) ) {
            if ( StringUtils.isBlank ( input.getNome () ) || StringUtils.isBlank ( input.getCognome () ) ) {
                out.setCodiceEsito ( ErroreGenerico );
                out.setDescrizioneEsito ( "E' obbligatorio indicare i campi nome e cognome, oppure ragioneSociale" );
                return out;
            }
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscalePartitaIVAPagatore () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo codiceFiscalePartitaIVAPagatore e' obbligatorio" );
            return out;
        }

        if ( input.getComponentiPagamento () != null ) {
            String validaComponenti = validazioneComponentiDSR ( input.getComponentiPagamento (), input );
            if ( validaComponenti != null ) {
                out.setCodiceEsito ( ErroreGenerico );
                out.setDescrizioneEsito ( validaComponenti );
                return out;
            }
            out.setCodiceEsito ( ValidazioneCompleata );
            out.setDescrizioneEsito ( "I campi sono stati validati tutti correttamente." );
        }
        out.setCodiceEsito ( ValidazioneCompleata );
        out.setDescrizioneEsito ( "I campi sono stati validati tutti correttamente." );
        return out;
    }

    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
        GetIuvMultibeneficiarioChiamanteEsternoOutput out = new GetIuvMultibeneficiarioChiamanteEsternoOutput ();
        if ( StringUtils.isBlank ( input.getCodiceFiscaleEnte () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo codiceFiscaleEnte e' obbligatorio" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getCausale () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo causale e' obbligatorio" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getTipoPagamento () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo tipoPagamento e' obbligatorio" );
            return out;
        }
        if ( input.getImportoPrincipale () == null ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo principale e' obbligatorio" );
            return out;
        }
        if ( input.getImportoPrincipale ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo principale non e' valido" );
            return out;
        }
        if ( input.getImportoSecondarioAltroEnte () == null ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo secondario e' obbligatorio" );
            return out;
        }
        if ( input.getImportoSecondarioAltroEnte ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo secondario non e' valido" );
            return out;
        }
        if ( input.getImportoTotale () == null ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo totale e' obbligatorio" );
            return out;
        }
        if ( input.getImportoTotale ().compareTo ( BigDecimal.ZERO ) < 0 ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo importo totale non e' valido" );
            return out;
        }
        if ( StringUtils.isBlank ( input.getRagioneSociale () ) ) {
            if ( StringUtils.isBlank ( input.getNome () ) || StringUtils.isBlank ( input.getCognome () ) ) {
                out.setCodiceEsito ( ErroreGenerico );
                out.setDescrizioneEsito ( "E' obbligatorio indicare i campi nome e cognome, oppure ragioneSociale" );
                return out;
            }
        }
        if ( StringUtils.isBlank ( input.getCodiceFiscalePartitaIVAPagatore () ) ) {
            out.setCodiceEsito ( ErroreGenerico );
            out.setDescrizioneEsito ( "Il campo codiceFiscalePartitaIVAPagatore e' obbligatorio" );
            return out;
        }

        if ( input.getComponentiPagamento () != null ) {
            String validaComponenti = validazioneComponenti ( input.getComponentiPagamento (), input );
            if ( validaComponenti != null ) {
                out.setCodiceEsito ( ErroreGenerico );
                out.setDescrizioneEsito ( validaComponenti );
                return out;
            }
            out.setCodiceEsito ( ValidazioneCompleata );
            out.setDescrizioneEsito ( "I campi sono stati validati tutti correttamente." );
        }
        if ( input.getComponentiPagamentoEntiSecondari () != null ) {
            String validaComponenti = validazioneComponenti ( input.getComponentiPagamentoEntiSecondari (), input );
            if ( validaComponenti != null ) {
                out.setCodiceEsito ( ErroreGenerico );
                out.setDescrizioneEsito ( validaComponenti );
                return out;
            }
            out.setCodiceEsito ( ValidazioneCompleata );
            out.setDescrizioneEsito ( "I campi sono stati validati tutti correttamente." );
        }
        return out;
    }

	private String validazioneComponenti ( List<ComponentePagamentoEnteSecondaroInput> componenti, GetIuvMultibeneficiarioChiamanteEsternoInput input ) {
		for ( ComponentePagamentoEnteSecondaroInput componente: componenti ) {
			String componentType = " ";
			if ( input != null ) {
				componentType = " secondarie ";
			}

			if ( componente.getProgressivo () == null ) {
				return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
			}
			if ( componente.getImporto () == null ) {
				return ( String.format ( "Il campo importo delle componenti%sdel pagamento e' obbligatorio", componentType ) );
			}
			if ( StringUtils.isBlank ( componente.getCausale () ) ) {
				return ( String.format ( "Il campo causale delle componenti%sdel pagamento e' obbligatorio", componentType ) );
			}
			if ( componente.getAnnoAccertamento () == null ) {
				return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );

			}
			if ( StringUtils.isBlank ( componente.getNumeroAccertamento () ) ) {
				return ( String.format ( "Il campo numeroAccertamento delle componenti%sdel pagamento e' obbligatorio", componentType ) );
			}
			if ( componente.getImporto ().compareTo ( BigDecimal.ZERO ) < 0 ) {
				return ( String.format ( "Il campo importo delle componenti%sdel pagamento non e' valido", componentType ) );
			}
			if ( componente.getProgressivo () < 0 ) {
				return ( String.format ( "Il campo progressivo delle componenti%sdel pagamento non e' valido", componentType ) );
			}
			if ( componente.getAnnoAccertamento () < 0 ) {
				return ( String.format ( "Il campo annoAccertamento delle componenti%sdel pagamento non e' valido", componentType ) );
			}

		}
		return null;
	}

	@Override
    public PagamentoIuvChiamanteEsternoOutput getPagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput arg0 ) {
        PagamentoIuvChiamanteEsternoOutput output = new PagamentoIuvChiamanteEsternoOutput ();
        if ( StringUtils.isBlank ( arg0.getCodiceFiscale () ) ) {
            output.setCodiceEsito ( ErroreGenerico );
            output.setDescrizioneEsito ( "Il campo codiceFiscale e' obbligatorio" );
            return output;
        }
        if ( StringUtils.isBlank ( arg0.getCodiceChiamante () ) ) {
            output.setCodiceEsito ( ErroreGenerico );
            output.setDescrizioneEsito ( "Il campo codiceChiamante e' obbligatorio" );
            return output;
        }
        if ( StringUtils.isBlank ( arg0.getIdentificativoPagamento () ) ) {
            output.setCodiceEsito ( ErroreGenerico );
            output.setDescrizioneEsito ( "Il campo identificativoPagamento e' obbligatorio" );
            return output;
        }

        if ( StringUtils.isBlank ( arg0.getIuv () ) ) {
            output.setCodiceEsito ( ErroreGenerico );
            output.setDescrizioneEsito ( "Il campo IUV e' obbligatorio" );
            return output;
        }
        output.setCodiceEsito ( ValidazioneCompleata );
        output.setDescrizioneEsito ( "I campi sono stati validati tutti correttamente." );

        return output;
    }

    @Override
    public Boolean verificaAutorizzazione ( String arg0, Long arg1 ) {
        return false;
    }

    @Override
    public GetRTChiamanteEsternoOutput getRTChiamanteEsterno(GetRTChiamanteEsternoInput input) {
        return null;
    }

    @Override
	public GetIuvChiamanteEsternoOutputContainer getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input ) {
		GetIuvChiamanteEsternoOutputContainer ret = new GetIuvChiamanteEsternoOutputContainer ();
		List<GetIuvChiamanteEsternoOutput> retValue = new ArrayList<> ();
        for ( GetIuvChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            retValue.add ( getIUVChiamanteEsterno ( inputSingleIUV ) );
        }
		ret.setElementiPosizioneDebitoria ( retValue );
		return ret;
    }

    @Override
	public GetIuvMultibeneficiarioChiamanteEsternoOutputContainer
		getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input ) {
		GetIuvMultibeneficiarioChiamanteEsternoOutputContainer ret = new GetIuvMultibeneficiarioChiamanteEsternoOutputContainer ();
		List<GetIuvMultibeneficiarioChiamanteEsternoOutput> retValue = new ArrayList<> ();
		for ( GetIuvMultibeneficiarioChiamanteEsternoInput inputSingleIUV: input.getElencoPosizioniDaInserire () ) {
            retValue.add ( getIUVMultibeneficiarioChiamanteEsterno ( inputSingleIUV ) );
        }
		ret.setElementiPosizioneDebitoria ( retValue );
		return ret;
    }

    @Override
    public AggiornaPosizioneDebitoriaChiamanteEsternoOutput
    aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput arg0 ) {
        return new AggiornaPosizioneDebitoriaChiamanteEsternoOutput ();
    }
    @Override
    public AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput aggiornaPosizioneDebitoriaChiamanteEsternoMultibeneficiario (
        AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput paramAggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput ) {
        return new AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoOutput ();
    }
    @Override
    public GetIuvMultibeneficiarioChiamanteEsternoOutput
        getIUVMultibeneficiarioChiamanteEsternov1 ( it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput arg0 ) {
        // TODO Auto-generated method stub
        return null;
    }

}
