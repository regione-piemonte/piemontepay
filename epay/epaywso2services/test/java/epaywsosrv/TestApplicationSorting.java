/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package epaywsosrv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import it.csi.epay.epaywsosrv.dto.AppDto;
import it.csi.epay.epaywsosrv.dto.ApplicationEsito;
import it.csi.epay.epaywsosrv.dto.ConfigAppDto;
import it.csi.epay.epaywsosrv.dto.EsitoInvioDto;
import it.csi.epay.epaywsosrv.dto.EsitoRichiestaDto;

public class TestApplicationSorting {

	private List<ApplicationEsito> abbinaConfigurazioniEdEsiti(List<ConfigAppDto> configurazioni, List<EsitoInvioDto> esitoInvioList) {
		boolean add = false;
		List<ApplicationEsito> applications = new ArrayList<ApplicationEsito>();
		for (ConfigAppDto config : configurazioni) {
			ApplicationEsito application = new ApplicationEsito();
			application.setConfig(config);
			add = true;
			for (EsitoInvioDto esitoInvio : esitoInvioList) {
				if (config.getAppDto().getId() == esitoInvio.getIdApplicativo()) {
					application.setEsito(esitoInvio);
					add = (esitoInvio.getEsitoInvioRichiestaDto().getCod() != null
							&& Integer.parseInt(esitoInvio.getEsitoInvioRichiestaDto().getCod()) >= 200)
							|| esitoInvio.getEsitoInvioRichiestaDto().getCod() == null;
					break;
				}
			}
			if (add) {
				applications.add(application);
			}
		}
		Collections.sort(applications);
		
		return applications;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Test
	public void sortApplicationsAndEsito() throws ParseException {
		
		List<EsitoInvioDto> esitoInvioList = new ArrayList<EsitoInvioDto>();
		esitoInvioList.add(getEsitoInvioDto(
			sdf.parse("01/06/2018 12:00:00"), 
			1, "Descrizione app 1",  
			"000", "Ok", null));
		esitoInvioList.add(getEsitoInvioDto(
			sdf.parse("01/06/2018 13:00:00"), 
			2, "Descrizione app 1",  
			"200", "Ok", null));
		esitoInvioList.add(getEsitoInvioDto(
			sdf.parse("01/06/2018 14:00:00"), 
			3, "Descrizione app 1",  
			"200", "Ok", null));
		
		List<ConfigAppDto> configApplicazioni = getConfigurazioni(5);
		List<ApplicationEsito> applications = abbinaConfigurazioniEdEsiti(configApplicazioni, esitoInvioList);
		
		logApplicationEsito(applications);
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("Aggiunto esito ok per applicazione 4");
		System.out.println("------------------------------------------------------------------");
		
		esitoInvioList.add(getEsitoInvioDto(
			sdf.parse("01/06/2018 14:00:00"), 
			4, "Descrizione app 1",  
			"000", "Ok", null));

		applications = abbinaConfigurazioniEdEsiti(configApplicazioni, esitoInvioList);
		
		logApplicationEsito(applications);

		System.out.println("------------------------------------------------------------------");
		System.out.println("Aggiunto esito ko per applicazione 5");
		System.out.println("------------------------------------------------------------------");
		
		esitoInvioList.add(getEsitoInvioDto(
			sdf.parse("01/06/2018 16:00:00"), 
			5, "Descrizione app 1",  
			"200", "Ok", null));

		applications = abbinaConfigurazioniEdEsiti(configApplicazioni, esitoInvioList);
		
		logApplicationEsito(applications);

		System.out.println("------------------------------------------------------------------");
		System.out.println("Aggiunto esito ok per applicazione 2");
		System.out.println("------------------------------------------------------------------");
		
		esitoInvioList.get(1).setEsitoInvioRichiestaDto(new EsitoRichiestaDto("000", "", ""));
		
		applications = abbinaConfigurazioniEdEsiti(configApplicazioni, esitoInvioList);
		
		logApplicationEsito(applications);
	}

	private void logApplicationEsito(List<ApplicationEsito> applications) {
		for (ApplicationEsito app : applications) {
			System.out.print("App:" + app.getConfig().getAppDto().getId()); 
			if (app.getEsito() != null) {
				System.out.print(
						" esito del:" + sdf.format(app.getEsito().getDataEsitoInvio()) + 
						" cod:" + app.getEsito().getEsitoInvioRichiestaDto().getCod());
			}
			System.out.println("");
		}
	}
	
	private EsitoInvioDto getEsitoInvioDto(Date dataEsitoInvio, Integer idApplicativo, String desApplicativo, String codEsito, String desEsito, String detEsito) {
		
		EsitoInvioDto esitoInvio = new EsitoInvioDto();
		esitoInvio.setDataEsitoInvio(dataEsitoInvio);
		esitoInvio.setIdApplicativo(idApplicativo);
		esitoInvio.setDesApplicativo(desApplicativo);
		EsitoRichiestaDto esitoInvioRichiestaDto = new EsitoRichiestaDto(codEsito, desEsito, detEsito);
		esitoInvio.setEsitoInvioRichiestaDto(esitoInvioRichiestaDto );
		
		return esitoInvio;
	}

	private List<ConfigAppDto> getConfigurazioni(int apps) {
		
		List<ConfigAppDto> configurazioni = new ArrayList<ConfigAppDto>();
		
		for (int i=1; i <= apps; i++) {
			ConfigAppDto configAppDto = new ConfigAppDto();
			AppDto appDto = new AppDto(i);
			appDto.setDes("Descrizione app " + i);
			configAppDto.setAppDto(appDto);
			configurazioni.add(configAppDto);
		}
		
		return configurazioni;
	}
}
