/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.date2strdatetime;
import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.epay.epaypaweb.dto.common.AuditEnabledParameter;

public class RichiestaRevocheFilterDto 
    implements Serializable, AuditEnabledParameter {
        private static final long serialVersionUID = 1L;
        private Long idRr;
        
        private String identificativoDominio;
        
        private String applicationId;
        
        private String identificativoMessaggioRevoca;
        
        private Date dataOraMessaggioRevocaDa;
        private Date dataOraMessaggioRevocaAl;



        private String codiceIdentificativoUnivocoAttestante;
        
        private String denominazioneIstitutoAttestante;
        
        private BigDecimal importoTotaleTevocato;
        private String iuv;
        
        private String codiceContestoPagamento;
        
        private Integer tipoRevoca;
        
        private String nomeCognome;
        private String codiceFiscale;
        
        
        
        public String getCodiceFiscale () {
            return codiceFiscale;
        }




        
        public void setCodiceFiscale ( String codiceFiscale ) {
            this.codiceFiscale = codiceFiscale;
        }




        public String getNomeCognome () {
            return nomeCognome;
        }



        
        public void setNomeCognome ( String nomeCognome ) {
            this.nomeCognome = nomeCognome;
        }



        public Long getIdRr () {
            return idRr;
        }


        
        public void setIdRr ( Long idRr ) {
            this.idRr = idRr;
        }


        
        public String getIdentificativoDominio () {
            return identificativoDominio;
        }


        
        public void setIdentificativoDominio ( String identificativoDominio ) {
            this.identificativoDominio = identificativoDominio;
        }


        
        public String getApplicationId () {
            return applicationId;
        }


        
        public void setApplicationId ( String applicationId ) {
            this.applicationId = applicationId;
        }


        
        public String getIdentificativoMessaggioRevoca () {
            return identificativoMessaggioRevoca;
        }


        
        public void setIdentificativoMessaggioRevoca ( String identificativoMessaggioRevoca ) {
            this.identificativoMessaggioRevoca = identificativoMessaggioRevoca;
        }


        
        public Date getDataOraMessaggioRevocaDa () {
            return dataOraMessaggioRevocaDa;
        }


        
        public void setDataOraMessaggioRevocaDa ( Date date ) {
            this.dataOraMessaggioRevocaDa = date;
        }

        
        
        public Date getDataOraMessaggioRevocaAl () {
            return dataOraMessaggioRevocaAl;
        }



        
        public void setDataOraMessaggioRevocaAl ( Date date ) {
            this.dataOraMessaggioRevocaAl = date;
        }
        
        public String getCodiceIdentificativoUnivocoAttestante () {
            return codiceIdentificativoUnivocoAttestante;
        }


        
        public void setCodiceIdentificativoUnivocoAttestante ( String codiceIdentificativoUnivocoAttestante ) {
            this.codiceIdentificativoUnivocoAttestante = codiceIdentificativoUnivocoAttestante;
        }


        
        public String getDenominazioneIstitutoAttestante () {
            return denominazioneIstitutoAttestante;
        }


        
        public void setDenominazioneIstitutoAttestante ( String denominazioneIstitutoAttestante ) {
            this.denominazioneIstitutoAttestante = denominazioneIstitutoAttestante;
        }


        
        public BigDecimal getImportoTotaleTevocato () {
            return importoTotaleTevocato;
        }


        
        public void setImportoTotaleTevocato ( BigDecimal importoTotaleTevocato ) {
            this.importoTotaleTevocato = importoTotaleTevocato;
        }


        
        public String getIuv () {
            return iuv;
        }


        
        public void setIuv ( String iuv ) {
            this.iuv = iuv;
        }


        
        public String getCodiceContestoPagamento () {
            return codiceContestoPagamento;
        }


        
        public void setCodiceContestoPagamento ( String codiceContestoPagamento ) {
            this.codiceContestoPagamento = codiceContestoPagamento;
        }


        
        public Integer getTipoRevoca () {
            return tipoRevoca;
        }


        
        public void setTipoRevoca ( Integer tipoRevoca ) {
            this.tipoRevoca = tipoRevoca;
        }


        
        public static long getSerialversionuid () {
            return serialVersionUID;
        }


        @Override
        public String toString() {
            final String COMMA = ", ";
            StringBuilder s = new StringBuilder();
            s.append("{ ");
            s.append("idRr:").append(idRr).append(COMMA);
            s.append("identificativoDominio:").append(identificativoDominio).append(COMMA);
            s.append("applicationId:").append(quote(applicationId)).append(COMMA);
            s.append("identificativoMessaggioRevoca:").append(identificativoMessaggioRevoca).append(COMMA);
            s.append("dataOraMessaggioRevoca:").append(date2strdatetime(dataOraMessaggioRevocaDa!=null?dataOraMessaggioRevocaDa:dataOraMessaggioRevocaAl)).append(COMMA);
            s.append("codiceIdentificativoUnivocoAttestante:").append(codiceIdentificativoUnivocoAttestante).append(COMMA);
            s.append("denominazioneIstitutoAttestante:").append(denominazioneIstitutoAttestante).append(COMMA);
            s.append("importoTotaleTevocato:").append((importoTotaleTevocato)).append(COMMA);
            s.append("iuv:").append(quote(iuv)).append(COMMA);
            s.append("codiceContestoPagamento:").append(quote(codiceContestoPagamento));
            s.append("tipoRevoca:").append((tipoRevoca)).append(COMMA);
            s.append(" }");
            return s.toString();
        }

		@Override
		public String getAuditMessage() {
			StringBuilder sb = new StringBuilder();

			if(getIdentificativoDominio()!=null) {
	        	sb.append("identificativoDominio:'").append(getIdentificativoDominio()).append("'");
	        }
			
			if(getApplicationId()!=null) {
				sb.append("applicationId:'").append(getApplicationId()).append("'");
			}
			
			if(getIdentificativoMessaggioRevoca()!=null) {
				sb.append("identificativoMessaggioRevoca:'").append(getIdentificativoMessaggioRevoca()).append("'");
			}
			
			if(getDataOraMessaggioRevocaDa()!=null) {
				sb.append("dataOraMessaggioRevocaDa:'").append(getDataOraMessaggioRevocaDa()).append("'");
			}
			
			if(getDataOraMessaggioRevocaAl()!=null) {
				sb.append("dataOraMessaggioRevocaAl:'").append(getDataOraMessaggioRevocaAl()).append("'");
			}
			
			if(getDataOraMessaggioRevocaAl()!=null) {
				sb.append("dataOraMessaggioRevocaAl:'").append(getDataOraMessaggioRevocaAl()).append("'");
			}
			
			if(getCodiceIdentificativoUnivocoAttestante()!=null) {
				sb.append("codiceIdentificativoUnivocoAttestante:'").append(getCodiceIdentificativoUnivocoAttestante()).append("'");
			}
			
			if(getDenominazioneIstitutoAttestante()!=null) {
				sb.append("denominazioneIstitutoAttestante:'").append(getDenominazioneIstitutoAttestante()).append("'");
			}
			
			if(getImportoTotaleTevocato()!=null) {
				sb.append("importoTotaleTevocato:'").append(getImportoTotaleTevocato()).append("'");
			}
			
			if(getIuv()!=null) {
				sb.append("iuv:'").append(getIuv()).append("'");
			}
			
			if(getCodiceContestoPagamento()!=null) {
				sb.append("codiceContestoPagamento():'").append(getCodiceContestoPagamento()).append("'");
			}
			
			if(getTipoRevoca()!=null) {
				sb.append("tipoRevoca:'").append(getTipoRevoca()).append("'");
			}
			
			if(getNomeCognome()!=null) {
				sb.append("nomeCognome:'").append(getNomeCognome()).append("'");
			}
			
			if(getCodiceFiscale()!=null) {
				sb.append("codiceFiscale:'").append(getCodiceFiscale()).append("'");
			}
	        return sb.toString();	
	    }
			
}
