<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<!-- Matomo -->
<script type="text/javascript">
  var _paq = window._paq = window._paq || [];
  /* tracker methods like "setCustomDimension" should be called before "trackPageView" */
  _paq.push(['trackPageView']);
  _paq.push(['enableLinkTracking']);
  (function() {
    var u="https://ingestion.webanalytics.italia.it/";
    _paq.push(['setTrackerUrl', u+'matomo.php']);
    _paq.push(['setSiteId', 'jLp6wEdqWQ']);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.type='text/javascript'; g.async=true; g.src=u+'matomo.js'; s.parentNode.insertBefore(g,s);
  })();
</script>
<!-- End Matomo Code -->


<footer role="contentinfo">
	<div class="pre-footer">
		<div class="container-fluid">
			<div class="row d-flex">
				<div class="col-sm-6 col-md-6 col-lg-6">
					<a href="https://www.regione.piemonte.it/web/"><img
						alt="csi piemonte" src="/ris/epayweb/images/logo-regione.png"></a>
					<img alt="PiemontePay"
						src="/ris/epayweb/images/logo_ppay_bianco.png" title="PiemontePay"
						class="ppy">
				</div>
				<div class="col-sm-6 col-md-6 col-lg-6 logo-dx">
					<a href="https://www.regione.piemonte.it/web" target="_blank">
						<img alt="Regione Piemonte" src="/ris/epayweb/images/fesr.jpg"
						title="Regione Piemonte">
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="container-fluid">
			<div class="row cont-footer">
				<div class="col-sm-12 col-md-6 col-lg-6">
					<h5>PER INFORMAZIONI</h5>
					<p>
						Contattare il <b>800333444</b>
					</p>
					<p>Il servizio e' gratuito, funziona dal lunedì al venerdì dalle
						ore 8:00 alle 18:00, da telefono fisso e mobile</p>
				</div>
				<div class="col-sm-12 col-md-6 col-lg-6">
					<h5 class="sm-t">PER MALFUNZIONAMENTI</h5>
					<p>
						compilare il <a
							href="http://www.sistemapiemonte.it/cms/assistenza/index_ass_ppay_privati.php"
							target="_blank"> il modulo di segnalazione</a>
					</p>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<p class="border-w"></p>
				</div>
				<div class="d-flex">
					<div class="col-sm-6 col-md-6 col-lg-6">
					
					<c:choose> 
    					<c:when test="${(menu == 'autenticato') || (menu == 'homepage autenticato')}">
                            <a href="https://www.piemontetu.it/api/auth/login?redirect=true">
							<p class="small">Questo servizio &egrave; parte di</p> <img
							alt="Piemonte tu" src="/ris/epayweb/images/piemontetu-footer.svg">
						</a>
                        </c:when> 
    					<c:otherwise>
                          <a href="https://www.piemontetu.it/">
							<p class="small">Questo servizio &egrave; parte di</p> <img
							alt="Piemonte tu" src="/ris/epayweb/images/piemontetu-footer.svg">
						</a>
                      </c:otherwise> 
					</c:choose> 
						
					</div>
					<div class="col-sm-6 col-md-6 col-lg-6 logo-dx">
						<a href="http://www.csipiemonte.it"><img alt="csi piemonte"
							src="/ris/epayweb/images/logocsi.png"></a>
					</div>
				</div>
				<div class="col-sm-12 col-md-12 col-lg-12">
					<p class="border-w"></p>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-sm-12 col-md-12 col-lg-12 small">
                    
					<a href="http://www.sistemapiemonte.it/cms/privati/accessibilita" target="_blank" title="Accessibilita'">Accessibilita'</a>
					<a href="/ris/privacy/cittadini/informativaPrivacyUtente.html" target="_blank" title="Privacy">Privacy</a> 
					<a href="http://www.sistemapiemonte.it/cms/privati/cookies-policy" target="_blank" title="Cookie policy">Cookie policy</a>
				</div>
			</div>
		</div>
	</div>
</footer>
