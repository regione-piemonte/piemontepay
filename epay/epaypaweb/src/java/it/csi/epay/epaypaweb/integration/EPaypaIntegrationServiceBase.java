/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaypaweb.dto.ComponenteImportoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.dto.RiferimentoPagamentoDto;
import it.csi.epay.epaypaweb.dto.SoggettoAnagraficoDto;
import it.csi.epay.epaypaweb.dto.TipoAggiornamentoPosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.facade.dto.common.PersonaFisicaType;
import it.csi.epay.epaypaweb.facade.dto.common.PersonaGiuridicaType;
import it.csi.epay.epaypaweb.facade.dto.common.SoggettoType;
import it.csi.epay.epaypaweb.facade.dto.common.TipoAggiornamentoType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.ComponenteImportoType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.ElencoPosizioniDaAggiornareType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.ElencoPosizioniDaAggiornareType.PosizioniDaAggiornare;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.InserisciListaDiCaricoRequest;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.ListaDiCarico;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.ListaDiCarico.PosizioniDaInserire;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaAggiornareType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaInserireType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaInserireType.ComponentiImporto;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaInserireType.ComponentiImportoSecondario;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.RiferimentoType;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.TestataAggiornaPosizioniDebitorie;
import it.csi.epay.epaypaweb.integration.enti2epaywso.dto.TestataListaCarico;

public class EPaypaIntegrationServiceBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".integration");

    protected boolean isEmpty ( String raw ) {
        return raw == null || raw.trim ().equals ( "" );
    }

	protected XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws Exception {
		XMLGregorianCalendar xmlGregCalendar = null;
		if (date != null) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			xmlGregCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		}
		return xmlGregCalendar;
	}

    public static Date fromXMLGregorianCalendar ( XMLGregorianCalendar calendar ) {
        if ( calendar == null ) {
            return null;
        }

        return calendar.toGregorianCalendar ().getTime ();
    }

	protected TestataListaCarico toTestaListaCaricoType(FlussoDto dto) {
		TestataListaCarico type = null;
		if (dto != null) {
			type = new TestataListaCarico();
			type.setIdMessaggio(dto.getIdMessaggio());
			type.setCFEnteCreditore(dto.getCodFiscaleEnte());
			type.setCodiceVersamento(dto.getCodVersamento());
			type.setNumeroPosizioniDebitorie(dto.getNumeroElementi());
			type.setImportoTotaleListaDiCarico(dto.getImportoTotale());
			type.setMultiBeneficiario ( dto.getFlagPrimario () );
		}
		return type;
	}

	protected TestataAggiornaPosizioniDebitorie toTestaAggiornaPosizioniDebitorieType(FlussoDto dto) {
		TestataAggiornaPosizioniDebitorie type = null;
		if (dto != null) {
			type = new TestataAggiornaPosizioniDebitorie();
			type.setIdMessaggio(dto.getIdMessaggio());
			type.setCFEnteCreditore(dto.getCodFiscaleEnte());
			type.setCodiceVersamento(dto.getCodVersamento());
			type.setNumeroPosizioniDebitorie(dto.getNumeroElementi());
			type.setMultiBeneficiario ( dto.getFlagPrimario () );
		}
		return type;
	}

	protected InserisciListaDiCaricoRequest toListacaricoRequest(FlussoCompletoDto<PosizioneDebitoriaDto> dto) throws Exception {
		InserisciListaDiCaricoRequest request = null;
		if (dto != null) {
			request = new InserisciListaDiCaricoRequest();
			request.setTestata(toTestaListaCaricoType(dto.getFlusso()));
			request.setListaDiCarico(toListaDiCaricoType(dto.getItemList()));
		}
		return request;
	}

	protected ComponenteImportoType toComponenteImportoType(ComponenteImportoDto dto) {
		ComponenteImportoType type = null;
		if (dto != null) {
			type = new ComponenteImportoType();
			type.setImporto(dto.getImporto());
			type.setCausaleDescrittiva(dto.getCausale());
            type.setAnnoAccertamento ( dto.getAnnoAccertamento () );
            type.setNumeroAccertamento ( dto.getNumeroAccertamento () != null ? dto.getNumeroAccertamento ().toString () : null );
		}
		return type;
	}

    protected RiferimentoType toRiferimentoType ( RiferimentoPagamentoDto dto ) {
        RiferimentoType type = null;
        if ( dto != null ) {
            type = new RiferimentoType ();
            type.setNome ( dto.getNome () );
            type.setValore ( dto.getValore () );
        }
        return type;
    }

    protected PosizioneDaInserireType.ComponentiImporto toComponentiImportoDaInserireType ( List<ComponenteImportoDto> dtoList ) {
        PosizioneDaInserireType.ComponentiImporto typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            typeWrapper = new PosizioneDaInserireType.ComponentiImporto ();
            List<ComponenteImportoType> typeList = typeWrapper.getComponenteImporto ();
            for ( ComponenteImportoDto dto: dtoList ) {
                if ( !Boolean.TRUE.equals ( dto.getFlagComponenteSecondario () ) ) {
                    typeList.add ( toComponenteImportoType ( dto ) );
                }
            }
        }
        return typeWrapper;
    }

    protected PosizioneDaInserireType.ComponentiImportoSecondario toComponentiImportoSecondarioType ( List<ComponenteImportoDto> dtoList ) {
        PosizioneDaInserireType.ComponentiImportoSecondario typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            for ( ComponenteImportoDto dto: dtoList ) {
                if ( Boolean.TRUE.equals ( dto.getFlagComponenteSecondario () ) ) {
                    typeWrapper = new PosizioneDaInserireType.ComponentiImportoSecondario ();
                    typeWrapper.setComponenteImporto ( toComponenteImportoType ( dto ) );
                    break;
                }
            }
        }
        return typeWrapper;
    }

    protected PosizioneDaAggiornareType.ComponentiImporto toComponentiImportoDaAggiornareType ( List<ComponenteImportoDto> dtoList ) {
        PosizioneDaAggiornareType.ComponentiImporto typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            typeWrapper = new PosizioneDaAggiornareType.ComponentiImporto ();
            List<ComponenteImportoType> typeList = typeWrapper.getComponenteImporto ();
            for ( ComponenteImportoDto dto: dtoList ) {
                if ( !Boolean.TRUE.equals ( dto.getFlagComponenteSecondario () ) ) {
                    typeList.add ( toComponenteImportoType ( dto ) );
                }
            }
        }
        return typeWrapper;
    }

    protected PosizioneDaAggiornareType.ComponentiImportoSecondario toComponentiImportoSecondarioDaAggiornareType ( List<ComponenteImportoDto> dtoList ) {
        PosizioneDaAggiornareType.ComponentiImportoSecondario typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            for ( ComponenteImportoDto dto: dtoList ) {
                if ( Boolean.TRUE.equals ( dto.getFlagComponenteSecondario () ) ) {
                    typeWrapper = new PosizioneDaAggiornareType.ComponentiImportoSecondario ();
                    typeWrapper.setComponenteImporto ( toComponenteImportoType ( dto ) );
                }
            }
        }
        return typeWrapper;
    }

    protected PosizioneDaInserireType.RiferimentiPagamento toRiferimentiPagamentoDaInserireType ( List<RiferimentoPagamentoDto> dtoList ) {
        PosizioneDaInserireType.RiferimentiPagamento typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            typeWrapper = new PosizioneDaInserireType.RiferimentiPagamento ();
            List<RiferimentoType> typeList = typeWrapper.getRiferimento ();
            for ( RiferimentoPagamentoDto dto: dtoList ) {
                typeList.add ( toRiferimentoType ( dto ) );
            }
        }
        return typeWrapper;
    }

    protected PosizioneDaAggiornareType.RiferimentiPagamento toRiferimentiPagamentoDaAggiornareType ( List<RiferimentoPagamentoDto> dtoList ) {
        PosizioneDaAggiornareType.RiferimentiPagamento typeWrapper = null;
        if ( dtoList != null && !dtoList.isEmpty () ) {
            typeWrapper = new PosizioneDaAggiornareType.RiferimentiPagamento ();
            List<RiferimentoType> typeList = typeWrapper.getRiferimento ();
            for ( RiferimentoPagamentoDto dto: dtoList ) {
                typeList.add ( toRiferimentoType ( dto ) );
            }
        }
        return typeWrapper;
    }

	protected SoggettoType toSoggettoType(SoggettoAnagraficoDto dto) {
		SoggettoType type = null;
		if (dto != null) {
			type = new SoggettoType();
			type.setIdentificativoUnivocoFiscale(dto.getIdUnivocoFiscale());
			switch (dto.getTipologiaSoggettoEnum()) {
			case PERSONA_FISICA:
				PersonaFisicaType personaFisicaType = new PersonaFisicaType();
				personaFisicaType.setCognome(dto.getCognome());
				personaFisicaType.setNome(dto.getNome());
				type.setPersonaFisica(personaFisicaType);
				break;
			case PERSONA_GIURIDICA:
				PersonaGiuridicaType personaGiuridicaType = new PersonaGiuridicaType();
				personaGiuridicaType.setRagioneSociale(dto.getRagioneSociale());
				type.setPersonaGiuridica(personaGiuridicaType);
				break;
			}
            if ( StringUtils.isNotBlank ( dto.getIndirizzo () ) ) {
                type.setIndirizzo ( dto.getIndirizzo () );
            }
            if ( StringUtils.isNotBlank ( dto.getCivico () ) ) {
                type.setCivico ( dto.getCivico () );
            }
            if ( StringUtils.isNotBlank ( dto.getCap () ) ) {
                type.setCAP ( dto.getCap () );
            }
            if ( StringUtils.isNotBlank ( dto.getLocalita () ) ) {
                type.setLocalita ( dto.getLocalita () );
            }
            if ( StringUtils.isNotBlank ( dto.getProvincia () ) ) {
                type.setProvincia ( dto.getProvincia () );
            }
            if ( StringUtils.isNotBlank ( dto.getNazione () ) ) {
                type.setNazione ( dto.getNazione () );
            }
            if ( StringUtils.isNotBlank ( dto.getEmail () ) ) {
                type.setEMail ( dto.getEmail () );
            }
		}
		return type;
	}

    protected PosizioneDaInserireType toPosizioneDaInserireType ( PosizioneDebitoriaDto dto ) throws Exception {
        PosizioneDaInserireType type = null;
        if ( dto != null ) {
            type = new PosizioneDaInserireType ();
            type.setIdPosizioneDebitoria ( dto.getIdPosizioneDebitoriaEsterna () );
            type.setAnnoRiferimento ( dto.getAnnoRiferimento () );
            type.setImportoTotale ( dto.getImportoTotale () );
            if ( null != dto.getImportoPrincipale () ) {
                type.setImportoPrincipale ( dto.getImportoPrincipale () );
            }
            if ( null != dto.getImportoSecondarioAltroEnte () ) {
                type.setImportoSecondarioAltroEnte ( dto.getImportoSecondarioAltroEnte () );
            }
            type.setDataScadenza ( toXMLGregorianCalendar ( dto.getDataScadenza () ) );
            type.setDataInizioValidita ( toXMLGregorianCalendar ( dto.getDataInizioValidita () ) );
            type.setDataFineValidita ( toXMLGregorianCalendar ( dto.getDataFineValidita () ) );
            type.setDescrizioneCausaleVersamento ( dto.getDesCausaleVersamento () );
            type.setDescrizioneRata ( dto.getDesRata () );
            type.setNotePerIlPagatore ( dto.getNotePerPagatore () );
            
            ComponentiImporto cmpPrinc = toComponentiImportoDaInserireType ( dto.getComponenteImportoDtoList () );
            if ( null != cmpPrinc && !cmpPrinc.getComponenteImporto ().isEmpty () ) {
                type.setComponentiImporto ( cmpPrinc );
            }
            ComponentiImportoSecondario cmpSec = toComponentiImportoSecondarioType ( dto.getComponenteImportoDtoList () );
            if ( null != cmpSec ) {
                type.setComponentiImportoSecondario ( cmpSec );
            }
            type.setRiferimentiPagamento ( toRiferimentiPagamentoDaInserireType ( dto.getRiferimentoPagamentoDtoList () ) );
            type.setSoggettoPagatore ( toSoggettoType ( dto.getSoggettoDebitore () ) );
        }
        return type;
    }

	protected ListaDiCarico toListaDiCaricoType(List<PosizioneDebitoriaDto> dtoList) throws Exception {
		ListaDiCarico typeWrapper1 = null;
		if (dtoList != null) {
			typeWrapper1 = new ListaDiCarico();
			PosizioniDaInserire typeWrapper2 = new PosizioniDaInserire();
			List<PosizioneDaInserireType> typeList = typeWrapper2.getPosizioneDaInserire();
			for (PosizioneDebitoriaDto dto : dtoList) {
				typeList.add(toPosizioneDaInserireType(dto));
			}
			typeWrapper1.setPosizioniDaInserire(typeWrapper2);
		}
		return typeWrapper1;
	}

	protected TipoAggiornamentoType toTipoAggiornamentoType(TipoAggiornamentoPosizioneDebitoriaDto dto) {
		TipoAggiornamentoType type = null;
		if (dto != null && dto.getTipoAggiornamentoEnum() != null) {
			switch (dto.getTipoAggiornamentoEnum()) {
			case ANNULLAMENTO:
				type = TipoAggiornamentoType.ANNULLAMENTO;
				break;
			case MODIFICA:
				type = TipoAggiornamentoType.MODIFICA;
				break;
			default:
				break;
			}
		}
		return type;
	}

	protected PosizioneDaAggiornareType toPosizioneDaAggiornareType(PosizioneDebitoriaDaAggiornareDto dto) throws Exception {
		PosizioneDaAggiornareType type = null;
		if (dto != null) {
			type = new PosizioneDaAggiornareType();
			type.setTipoAggiornamento(toTipoAggiornamentoType(dto.getTipoAggiornamento()));
			type.setIdPosizioneDebitoria(dto.getIdPosizioneDebitoriaEsterna());
			type.setDataScadenza(toXMLGregorianCalendar(dto.getDataScadenza()));
			type.setDataInizioValidita(toXMLGregorianCalendar(dto.getDataInizioValidita()));
			type.setDataFineValidita(toXMLGregorianCalendar(dto.getDataFineValidita()));
			type.setImportoTotale(dto.getImportoTotale());
            type.setRiferimentiPagamento ( toRiferimentiPagamentoDaAggiornareType ( dto.getRiferimentoPagamentoDtoList () ) );
            type.setDescrizioneCausaleVersamento ( dto.getDesCausaleVersamento () );
            if ( StringUtils.isNotBlank ( dto.getMotivazione () ) ) {
                type.setMotivazione ( dto.getMotivazione () );
            }
			type.setSoggettoPagatore(toSoggettoType(dto.getSoggettoAnagraficoDto()));
			// RDI-45 Inizio
            it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaAggiornareType.ComponentiImporto comPrim
                = toComponentiImportoDaAggiornareType ( dto.getComponenteImportoDtoList () );
            if ( null != comPrim && CollectionUtils.isNotEmpty ( comPrim.getComponenteImporto () ) ) {
                type.setComponentiImporto ( comPrim );
            }
            it.csi.epay.epaypaweb.integration.enti2epaywso.dto.PosizioneDaAggiornareType.ComponentiImportoSecondario compSec = toComponentiImportoSecondarioDaAggiornareType ( dto.getComponenteImportoDtoList () );
            if ( null != compSec && null != compSec.getComponenteImporto () ) {
                type.setComponentiImportoSecondario ( compSec );
            }
            type.setImportoPrincipale ( dto.getImportoPrincipale () );
            type.setImportoSecondarioAltroEnte ( dto.getImportoSecondarioAltroEnte () );
            // RDI-45 Fine
		}
		return type;
	}

	protected ElencoPosizioniDaAggiornareType toElencoPosizioniDaAggiornareType(List<PosizioneDebitoriaDaAggiornareDto> dtoList) throws Exception {
		ElencoPosizioniDaAggiornareType typeWrapper1 = null;
		if (dtoList != null) {
			typeWrapper1 = new ElencoPosizioniDaAggiornareType();
			PosizioniDaAggiornare typeWrapper2 = new PosizioniDaAggiornare();
			List<PosizioneDaAggiornareType> typeList = typeWrapper2.getPosizioneDaAggiornare();
			for (PosizioneDebitoriaDaAggiornareDto dto : dtoList) {
				typeList.add(toPosizioneDaAggiornareType(dto));
			}
			typeWrapper1.setPosizioniDaAggiornare(typeWrapper2);
		}
		return typeWrapper1;
	}
}
