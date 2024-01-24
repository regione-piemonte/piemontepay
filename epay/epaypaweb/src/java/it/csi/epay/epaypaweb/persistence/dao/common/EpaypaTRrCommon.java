/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import static it.csi.epay.epaypaweb.enumeration.ColumnNameRevocheEnum.*;
import static it.csi.epay.epaypaweb.util.Util.getLaterDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.dto.FlussoLightFilterDto;
import it.csi.epay.epaypaweb.dto.RichiestaRevocheFilterDto;
import it.csi.epay.epaypaweb.enumeration.ColumnNameFlussoEnum;
import it.csi.epay.epaypaweb.enumeration.ColumnNameRevocheEnum;
import it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTFlussoLight;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;

public class EpaypaTRrCommon {
	@SuppressWarnings("unchecked")
	static public List<Predicate> buildPredicates(RichiestaRevocheFilterDto filter, CriteriaBuilder cbuilder, Root<EpaypaTRr> root) {
		List<Predicate> predicates = null;

		if (filter != null) {
			predicates = new ArrayList<Predicate>();
			
		    if (filter.getIdRr() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, ID_RR), filter.getIdRr()));
		    }
		    if (filter.getIdentificativoDominio() != null && !filter.getIdentificativoDominio().isEmpty ()) {
		        predicates.add(cbuilder.equal(getColumnPath(root, IDENTIFICATIVO_DOMINIO), filter.getIdentificativoDominio()));
		    }
		    if (filter.getApplicationId() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, APPLICATION_ID), filter.getApplicationId ()));
		    }
		    if (filter.getIdentificativoMessaggioRevoca() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, IDENTIFICATIVO_MESSAGGIO_REVOCA), filter.getIdentificativoMessaggioRevoca ()));
		    }
		    if (filter.getDataOraMessaggioRevocaDa () != null) {
		        predicates.add(cbuilder.greaterThanOrEqualTo(getColumnPath(root, DATA_ORA_MESSAGGIO_REVOCA), filter.getDataOraMessaggioRevocaDa ()));
		    }
            if (filter.getDataOraMessaggioRevocaAl () != null) {
                predicates.add(cbuilder.lessThan(getColumnPath(root, DATA_ORA_MESSAGGIO_REVOCA), filter.getDataOraMessaggioRevocaAl ()));
            }
		    if (filter.getCodiceIdentificativoUnivocoAttestante() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, CODICE_IDENTIFICATIVO_UNIVOCO_ATTESTANTE), filter.getCodiceIdentificativoUnivocoAttestante ()));
		    }
		    if (filter.getDenominazioneIstitutoAttestante() != null && !filter.getDenominazioneIstitutoAttestante().isEmpty ()) {
		        predicates.add(cbuilder.like(cbuilder.lower (getColumnPath(root, DENOMINAZIONE_ISTITUTO_ATTESTANTE)), "%"+filter.getDenominazioneIstitutoAttestante ().toLowerCase ()+"%"));
		    }
		    if (filter.getImportoTotaleTevocato() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, IMPORTO_TOTALE_REVOCATO), filter.getImportoTotaleTevocato ()));
		    }
		    if (filter.getIuv() != null && !filter.getIuv().isEmpty ()) {
		        predicates.add(cbuilder.like(cbuilder.lower (getColumnPath(root, IUV)), "%"+filter.getIuv ().toLowerCase ()+"%"));
		    }
		    if (filter.getCodiceContestoPagamento() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, CODICE_CONTESTO_PAGAMENTO), filter.getCodiceContestoPagamento ()));
		    }
		    if (filter.getTipoRevoca() != null) {
		        predicates.add(cbuilder.equal(getColumnPath(root, TIPO_REVOCA), filter.getTipoRevoca ()));
		    }

		}

		return predicates;
	}

	static public Predicate buildUserRestrictions(String codUtente, CriteriaBuilder cbuilder, Root<EpaypaTRr> root) {
	/*	if (codUtente != null) {
			List<Predicate> orPredicates = new ArrayList<Predicate>();
			orPredicates.add(cbuilder.equal(getColumnPath(root, UTENTE_ULTIMA_VARIAZIONE), codUtente));
			orPredicates.add(cbuilder.notEqual(getColumnPath(root, STATO_FLUSSO), StatoFlussoEnum.BOZZA.getId()));
			return cbuilder.or(orPredicates.toArray((new Predicate[orPredicates.size()])));
		}*/
		return null;
	}

	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTRr> root, ColumnNameRevocheEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_RR:
				columnPath = root.get("idRr");
				break;
			case IDENTIFICATIVO_DOMINIO:
				columnPath = root.get("identificativoDominio");
				break;
			case APPLICATION_ID:
				columnPath = root.get("applicationId");
				break;
			case IDENTIFICATIVO_MESSAGGIO_REVOCA:
				columnPath = root.get("identificativoMessaggioRevoca");
				break;
			case DATA_ORA_MESSAGGIO_REVOCA:
				columnPath = root.get("dataOraMessaggioRevoca");
				break;
			case CODICE_IDENTIFICATIVO_UNIVOCO_ATTESTANTE:
				columnPath = root.get("codiceIdentificativoUnivocoAttestante");
				break;
			case DENOMINAZIONE_ISTITUTO_ATTESTANTE:
				columnPath = root.get("denominazioneIstitutoAttestante");
				break;
			case IMPORTO_TOTALE_REVOCATO:
				columnPath = root.get("importoTotaleTevocato");
				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
			case CODICE_CONTESTO_PAGAMENTO:
				columnPath = root.get("codiceContestoPagamento");
				break;
			case TIPO_REVOCA:
				columnPath = root.get("tipoRevoca"); //.get("id");
				break;
			default:
				break;
			}
		}

		return columnPath;
	}

}
