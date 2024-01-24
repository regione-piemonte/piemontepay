/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;



@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/")
public class MainController extends _Controller{

	
	private final static String VIEWNAMEHOME = "accesso_libero/home/home";
	
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView home() {
		final String methodName = "home";
		log.debugStart(methodName);

		ModelAndView modelAndView = new ModelAndView(VIEWNAMEHOME);

		log.debugEnd(methodName);
		return modelAndView;
	}
	
	/*
 	@Autowired
	private MailFacade mailFacade;
 	
 	@RequestMapping(value = { "/testMail" }, produces = MediaType.TEXT_PLAIN_VALUE)
	public void testMail(HttpServletResponse res) {
		String methodName = "testMail";
		System.out.println("Call to MainController.testMail");

		try {
			Mail mail = new Mail();
			mail.setSubject("mail di prova");
			mail.setText("mail per testare la classe che compone il messaggio");
			
			mailFacade.inviaMail(mail);
			System.out.println("fine invio   ");
		} catch (Exception e) {
			System.out.println("errore   " + e);
			e.printStackTrace();
		}

		try (PrintWriter out = res.getWriter()) {
			out.println("Invia la mail");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
