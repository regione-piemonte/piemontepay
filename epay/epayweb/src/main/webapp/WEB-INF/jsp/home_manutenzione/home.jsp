<!DOCTYPE html>
<html lang="it">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <head>
      <title>Piemontepay</title>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, user-scalable=yes" />
      <meta name="author" content="CSI-Piemonte" />
      <meta name="description" content="Sistema Piemonte il portale per cittadini ed imprese" />
      <meta name="keywords" content="piemonte, servizi, cittadini, imprese, sistema, amministrazione" />
      <link href="/ris/epayweb/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
      <link rel="stylesheet" href="/ris/epayweb/bootstrap/css/bootstrap.min.css" />
      <link rel="stylesheet" href="/ris/epayweb/css/bootstrap-yeti-accessibilita.css" />
      <link rel="stylesheet" href="/ris/epayweb/fonts/fontawesome-free-5.13.0-web/css/all.css" />
      <link rel="stylesheet" href="/ris/epayweb/css/skin-home.css" />
      <link rel="stylesheet" type="text/css" href="/ris/epayweb/css/google_fonts_imported_home_manutenzione.css" />

      <!--[if IE]>
      <script>
         var console = { log: function() {} };
      </script>
      <![endif]-->
      <script src="/ris/epayweb/js/jquery-1.12.3.min.js"></script>
      <script src="/ris/epayweb/js/jquery-ui.min.js"></script>
      <script src="/ris/epayweb/bootstrap/js/bootstrap.min.js"></script>
  
<style>

:root {
  --main-color: #eaeaea;
  --stroke-color: black;
  
}
/**/
h2 {
  margin: 20px auto 30px auto;
 
  font-size: 1.5rem;
  font-weight: 200;
  text-align: center;
}

.gears {
  position: relative;
  margin: 0 auto;
  width: auto; height: 0;
}
.gear {
  position: relative;
  z-index: 0;
  width: 120px; height: 120px;
  margin: 0 auto;
  border-radius: 50%;
  background: var(--stroke-color);
}
.gear:before{
  position: absolute; left: 5px; top: 5px; right: 5px; bottom: 5px;
  z-index: 2;
  content: "";
  border-radius: 50%;
  background: var(--main-color);
}
.gear:after {
  position: absolute; left: 25px; top: 25px;
  z-index: 3;
  content: "";
  width: 70px; height: 70px;
  border-radius: 50%;
  border: 5px solid var(--stroke-color);
  box-sizing: border-box;
  background: var(--main-color);
}
.gear.one {
  left: -130px;
}
.gear.two {
  top: -75px;
}
.gear.three {
  top: -235px;
  left: 130px;
}
.gear .bar {
  position: absolute; left: -15px; top: 50%;
  z-index: 0;
  width: 150px; height: 30px;
  margin-top: -15px;
  border-radius: 5px;
  background: var(--stroke-color);
}
.gear .bar:before {
  position: absolute; left: 5px; top: 5px; right: 5px; bottom: 5px;
  z-index: 1;
  content: "";
  border-radius: 2px;
  background: var(--main-color);
}
.gear .bar:nth-child(2) {
  transform: rotate(60deg);
  -webkit-transform: rotate(60deg);
}
.gear .bar:nth-child(3) {
  transform: rotate(120deg);
  -webkit-transform: rotate(120deg);
}
@-webkit-keyframes clockwise {
  0% { -webkit-transform: rotate(0deg);}
  100% { -webkit-transform: rotate(360deg);}
}
@-webkit-keyframes anticlockwise {
  0% { -webkit-transform: rotate(360deg);}
  100% { -webkit-transform: rotate(0deg);}
}
@-webkit-keyframes clockwiseError {
  0% { -webkit-transform: rotate(0deg);}
  20% { -webkit-transform: rotate(30deg);}
  40% { -webkit-transform: rotate(25deg);}
  60% { -webkit-transform: rotate(30deg);}
  100% { -webkit-transform: rotate(0deg);}
}
@-webkit-keyframes anticlockwiseErrorStop {
  0% { -webkit-transform: rotate(0deg);}
  20% { -webkit-transform: rotate(-30deg);}
  60% { -webkit-transform: rotate(-30deg);}
  100% { -webkit-transform: rotate(0deg);}
}
@-webkit-keyframes anticlockwiseError {
  0% { -webkit-transform: rotate(0deg);}
  20% { -webkit-transform: rotate(-30deg);}
  40% { -webkit-transform: rotate(-25deg);}
  60% { -webkit-transform: rotate(-30deg);}
  100% { -webkit-transform: rotate(0deg);}
}
.gear.one {
  -webkit-animation: anticlockwiseErrorStop 2s linear infinite;
}
.gear.two {
  -webkit-animation: anticlockwiseError 2s linear infinite;
}
.gear.three {
  -webkit-animation: clockwiseError 2s linear infinite;
}
.loading .gear.one, .loading .gear.three {
  -webkit-animation: clockwise 3s linear infinite;
}
.loading .gear.two {
  -webkit-animation: anticlockwise 3s linear infinite;
}
.loading{min-height: 300px;  margin:10% auto 20% auto;} 


.alert-info{width:60%; margin: 0px auto 50px auto;  font-size: 16px; }
</style>
<!-- <link href="https://fonts.googleapis.com/css?family=Encode+Sans+Semi+Condensed:100,200,300,400" rel="stylesheet"> -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<meta charset="ISO-8859-1">

</head>
<body>


<header>
         <div class="container-fluid">
            <a class="" href="/epayweb">
             <img alt="PiemontePay" src="/ris/epayweb/images/logo1.svg" title="PiemontePay" class="logo">				
            </a>
         </div>
      </header>


<div class ="container loading">
<div class="alert alert-info">Attenzione l'applicazione risulta in manutenzione riprova tra qualche istante
<br>
   Ci scusiamo per il disagio
</div>
  <div class="gears">
    <div class="gear one">
      <div class="bar"></div>
      <div class="bar"></div>
      <div class="bar"></div>
    </div>
    <div class="gear two">
      <div class="bar"></div>
      <div class="bar"></div>
      <div class="bar"></div>
    </div>
    <div class="gear three">
      <div class="bar"></div>
      <div class="bar"></div>
      <div class="bar"></div>
    </div>
  </div>
 </div>
 </div> 	
</div>


      <footer role="contentinfo">
         <div class="pre-footer">
            <div class="container-fluid">
               <div class="row d-flex">
                  <div class="col-sm-6 col-md-6 col-lg-6">
                     <a href="https://www.regione.piemonte.it/web/"><img alt="csi piemonte" src="/ris/epayweb/images/logo-regione.png"></a>
                     <img alt="PiemontePay" src="/ris/epayweb/images/logo_ppay_bianco.png" title="PiemontePay" class="ppy">	
                  </div>
                  <div class="col-sm-6 col-md-6 col-lg-6 logo-dx">
                     <a href="https://www.regione.piemonte.it/web" target="_blank">
                     <img alt="Regione Piemonte" src="/ris/epayweb/images/fesr.jpg" title="Regione Piemonte">
                     </a>
                  </div>
               </div>
            </div>
         </div>
		 
		 
		 
         <div class="footer">
            <div class="container-fluid">
               <div class="row cont-footer">
                  <div class="col-sm-12 col-md-12 col-lg-12">
                     <h5 class="sm-t">PER MALFUNZIONAMENTI</h5>
                     <p> compilare il <a href="http://www.sistemapiemonte.it/cms/assistenza/index_ass_ppay_privati.php" target="_blank"> il modulo di segnalazione</a></p>
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
                           <p class="small">Questo servizio &egrave; parte di</p>
                           <img alt="Piemonte tu" src="/ris/epayweb/images/piemontetu-footer.svg">
                        </a>
                        </c:when>
                        <c:otherwise>
                          <a href="https://www.piemontetu.it/">
                           <p class="small">Questo servizio &egrave; parte di</p>
                           <img alt="Piemonte tu" src="/ris/epayweb/images/piemontetu-footer.svg">
                        </a>
                        </c:otherwise>
                         </c:choose>
                     </div>
                     <div class="col-sm-6 col-md-6 col-lg-6 logo-dx"><a href="http://www.csipiemonte.it"><img alt="csi piemonte" src="/ris/epayweb/images/logocsi.png"></a></div>
                  </div>
                  <div class="col-sm-12 col-md-12 col-lg-12">
                     <p class="border-w"></p>
                  </div>
               </div>
               <div class="row mt-3">
                  <div class="col-sm-12 col-md-12 col-lg-12 small"><a href="/cms/privati/accessibilita" title="Accessibilità">Accessibilità</a>  <a href="/cms/privati/privacy" title="Privacy">Privacy</a>  <a href="/cms/privati/cookies-policy" title="Cookie policy">Cookie policy</a></div>
               </div>
            </div>
         </div>
      </footer>

</div>
</body>
</html>
