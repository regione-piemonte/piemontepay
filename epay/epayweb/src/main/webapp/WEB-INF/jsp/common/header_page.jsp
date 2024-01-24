<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>
<%@ include file="url.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function() {
			$("#cookieConsent").fadeIn(200);
		}, 1000);
	});

    function openOffcanvas1() {
        document.getElementById("myOffcanvas").style.width = "400px";
    }

    function openOffcanvas() {
        document.getElementById("myOffcanvas").style.width = "400px";            
    }
    
    function openNav3() {
        document.getElementById("myCanvasNav").style.width = "100%";
        document.getElementById("myCanvasNav").style.opacity = "0.8";
    }
    
    function closeOffcanvas() {
        document.getElementById("myOffcanvas").style.width = "0%";
        document.body.style.backgroundColor = "white";
        document.getElementById("myCanvasNav").style.width = "0%";
        document.getElementById("myCanvasNav").style.opacity = "0";
    }
</script>

<header>
	<div class="container-fluid">      
	   <c:if
            test="${(!empty menu) && (menu != 'homepage') && (menu != 'manutenzione') && (menu != 'homepage autenticato')}">
            <c:if test="${( (menu == 'libero') || (menu == 'autenticato') ) }">
				<button class="btn btn-link" onclick="openNav3();openOffcanvas()">
					<em class="fa fa-bars fa-2x"></em>
				</button>
			</c:if>
        </c:if>
		<a class="" href="${urlHomePage}"> <img alt="PiemontePay"
			src="/ris/epayweb/images/logo1.svg" title="PiemontePay"
			class="logo">
		</a>
		<c:if
			test="${(!empty menu) && (menu != 'homepage') && (menu != 'manutenzione') && (menu != 'homepage autenticato')}">
			<c:if test="${( (menu == 'libero') || (menu == 'autenticato') ) }">
				<div id="myCanvasNav" class="overlay3" onclick="closeOffcanvas()"></div>
				<div id="myOffcanvas" class="offcanvas">
					<h2>PiemontePay</h2>
					<div class="menu">
						<a href="/epayweb" class="icomenu-home">Home</a>
						<a href="${(empty menu || menu == 'libero') ? urlPagaSenzaIuv : urlPrivatePagaSenzaIuv}" class="icomenu-paga">Pagamento spontaneo</a>
						<a href="${(empty menu || menu == 'libero') ? urlPagaConIuv : urlPrivatePagaConIuv}" class="icomenu-paga-iuv">Paga con PIEMONTEPAY con IUV</a>
						<a href="${(empty menu || menu == 'libero') ? urlStampaAvviso : urlPrivateStampaAvviso}" class="icomenu-stampa">Stampa avviso pagamento</a>
						
						<a href="${(empty menu || menu == 'libero') ? urlVerifica : urlPrivateVerifica}" class="icomenu-verifica">Verifica pagamento</a>
					</div>
					<div class="footer-menu">
					<c:choose> 
    					<c:when test="${(menu == 'autenticato') || (menu == 'homepage autenticato')}">
						<a href="https://www.piemontetu.it/api/auth/login?redirect=true">
							<p class="small">PiemontePay e' parte di</p> <img
							alt="Piemonte tu" src="/ris/epayweb/images/logo-piemonte-tu.png"
							title="Piemonte tu"> <span class="scopri">SCOPRI</span>
						</a>
						</c:when>
						<c:otherwise>
						<a href="https://www.piemontetu.it/">
							<p class="small">PiemontePay e' parte di</p> <img
							alt="Piemonte tu" src="/ris/epayweb/images/logo-piemonte-tu.png"
							title="Piemonte tu"> <span class="scopri">SCOPRI</span>
						</a>
						
						</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</c:if>
		</c:if>
		<c:if test="${(menu == 'autenticato') || (menu == 'homepage autenticato')}">
			<div class="right">
				<div class="row">
					<nav class="navbar navbar-default">
						<div id="navbar" class="navbar-collapse">
							<ul class="nav navbar-nav">
								<li class="dropdown"><a href="#"
									class="dropdown-toggle utente" data-toggle="dropdown"
									role="button" aria-haspopup="true" aria-expanded="false"> <i
										class="fa fa-user" aria-hidden="true"></i>
								</a>
									<ul class="dropdown-menu">
										<li class="user"><span>${commonData.alias}</span></li>
										<li class="divider"></li>
										<li><a href="${urlPrivateLogout}"><em
												class="fas fa-sign-out-alt"></em> Esci</a></li>
									</ul></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</c:if>
	</div>
</header>
