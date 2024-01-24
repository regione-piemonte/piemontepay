/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.mapper;

import java.util.ArrayList;
import java.util.List;

import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataOutputDto;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;

public abstract class VociEntrataMapper extends ParentMapper {

	public static RicercaVoceEntrataInput map(RicercaVoceEntrataPPayFiltroVO input) {
		RicercaVoceEntrataInput o = new RicercaVoceEntrataInput();

		o.setCodice(input.getCodice());
		o.setCodiceMacrotipo(input.getCodiceMacrotipo());
		o.setCodiceTematica(input.getCodiceTematica());
		o.setDescrizione(input.getDescrizione());

		return o;
	}

    public static RicercaVoceEntrataDisponibileInput mapToRicercaVoceEntrataDisponibileInput ( RicercaVoceEntrataPPayFiltroVO input ) {
        RicercaVoceEntrataDisponibileInput o = new RicercaVoceEntrataDisponibileInput ();

        o.setCodice ( input.getCodice () );
        o.setCodiceMacrotipo ( input.getCodiceMacrotipo () );
        o.setCodiceTematica ( input.getCodiceTematica () );
        o.setDescrizione ( input.getDescrizione () );

        return o;
    }

	public static List<VoceEntrataPPayVO> mapListRicercaVoceEntrataOutputDto(List<RicercaVoceEntrataOutputDto> risultati) {
		if (risultati == null) {
			return null;
		}

		List<VoceEntrataPPayVO> output = new ArrayList<>();

		for (RicercaVoceEntrataOutputDto dto : risultati) {
			output.add(map(dto));
		}

		return output;
	}

    public static List<VoceEntrataPPayVO> mapListRicercaVoceEntrataDisponibileOutputDto ( List<RicercaVoceEntrataDisponibileOutputDto> risultati ) {
        if ( risultati == null ) {
            return null;
        }

        List<VoceEntrataPPayVO> output = new ArrayList<> ();

        for ( RicercaVoceEntrataDisponibileOutputDto dto: risultati ) {
            output.add ( map ( dto ) );
        }

        return output;
    }

	public static VoceEntrataPPayVO map(RicercaVoceEntrataOutputDto input) {
		if (input == null) {
			return null;
		}

		VoceEntrataPPayVO output = new VoceEntrataPPayVO();

		output.setId(input.getId());
		output.setCodice(input.getCodice());
		output.setCodiceMacrotipo(input.getCodiceMacrotipo());
		output.setCodiceTematica(input.getCodiceTematica());
		output.setDescrizione(input.getDescrizione());
		output.setDescrizioneMacrotipo(input.getDescrizioneMacrotipo());
		output.setDescrizioneTematica(input.getDescrizioneTematica());

		return output;
	}

    public static VoceEntrataPPayVO map ( RicercaVoceEntrataDisponibileOutputDto input ) {
        if ( input == null ) {
            return null;
        }

        VoceEntrataPPayVO output = new VoceEntrataPPayVO ();

        output.setId ( input.getId () );
        output.setCodice ( input.getCodice () );
        output.setCodiceMacrotipo ( input.getCodiceMacrotipo () );
        output.setCodiceTematica ( input.getCodiceTematica () );
        output.setDescrizione ( input.getDescrizione () );
        output.setDescrizioneMacrotipo ( input.getDescrizioneMacrotipo () );
        output.setDescrizioneTematica ( input.getDescrizioneTematica () );

        return output;
    }

}
