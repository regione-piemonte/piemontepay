/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import it.csi.epay.epaypacatalogsrv.vo.ParentVO;


public class PrincipalUtenteVO extends ParentVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceFiscale;

    private String nome;

    private String cognome;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    //EPAY-80
    private Date dataInizioValiditaCurrent;

    //EPAY-80
    private Date dataFineValiditaCurrent;

    private List<PrincipalCduVO> cdu;
    private Supplier<List<PrincipalCduVO>> cduLoader;

    public PrincipalUtenteVO() {
		super();
	}

	//EPAY-80
    public boolean isValid () {

        if ( dataInizioValidita == null && dataInizioValidita == null ) {
            return false;
        }

        LocalDate dataInizioValiditaLD;
        LocalDate dataFineValiditaLD;
        LocalDate dataInizioValiditaCurrentLD;
        LocalDate dataFineValiditaCurrentLD;

        if ( dataInizioValidita instanceof java.sql.Date ) {
            dataInizioValiditaLD = ( (java.sql.Date) dataInizioValidita ).toLocalDate ();
        } else {
            dataInizioValiditaLD = dataInizioValidita.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }
        if ( dataInizioValiditaCurrent instanceof java.sql.Date ) {
            dataInizioValiditaCurrentLD = ( (java.sql.Date) dataInizioValiditaCurrent ).toLocalDate ();
        } else {
            dataInizioValiditaCurrentLD = dataInizioValiditaCurrent.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }

        if ( dataFineValidita == null ) {
            dataFineValiditaLD = null;
        } else if ( dataFineValidita instanceof java.sql.Date ) {
            dataFineValiditaLD = ( (java.sql.Date) dataFineValidita ).toLocalDate ();
        } else {
            dataFineValiditaLD = dataFineValidita.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }
        if ( dataFineValiditaCurrent == null ) {
            dataFineValiditaCurrentLD = null;
        } else if ( dataFineValiditaCurrent instanceof java.sql.Date ) {
            dataFineValiditaCurrentLD = ( (java.sql.Date) dataFineValiditaCurrent ).toLocalDate ();
        } else {
            dataFineValiditaCurrentLD = dataFineValiditaCurrent.toInstant ().atZone ( ZoneId.systemDefault () ).toLocalDate ();
        }

        LocalDate now = LocalDate.now ();

        if ( ( null != dataInizioValiditaLD && dataInizioValiditaLD.isAfter ( now ) )
                        || ( null != dataInizioValiditaCurrentLD && dataInizioValiditaCurrentLD.isAfter ( now ) ) ) {
            return false;
        }

        if ( dataFineValiditaLD == null && dataFineValiditaCurrentLD == null ) {
            return true;
        }

        if ( ( null != dataFineValiditaLD && dataFineValiditaLD.isAfter ( now ) )
                        || ( null != dataFineValiditaCurrentLD && dataFineValiditaCurrentLD.isAfter ( now ) ) ) {
            return true;
        }

        return false;
    }

    public boolean hasUseCase ( String uc ) {
        if ( uc == null || uc.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        List<PrincipalCduVO> cdus = getCdu ();
        if ( cdus != null ) {
            for ( PrincipalCduVO candidate: cdus ) {
                if ( uc.equalsIgnoreCase ( candidate.getCodice () ) ) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setCduLoader(Supplier<List<PrincipalCduVO>> cduLoader) {
		this.cduLoader = cduLoader;
	}

	public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public List<PrincipalCduVO> getCdu () {
    	if ( cdu == null ) {
            if ( cduLoader == null ) {
                return cdu;
            }
            synchronized ( cduLoader ) { // NOSONAR
                if ( cdu == null ) {
                	cdu = this.cduLoader.get ();
                }
            }
        }
        return cdu;
    }

    public void setCdu ( List<PrincipalCduVO> cdu ) {
        this.cdu = cdu;
    }

    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    @Override
    public String toString () {
        return "PrincipalUtenteVO [id=" + id
                        + ", codiceFiscale=" + codiceFiscale
                        + ", nome=" + nome
                        + ", cognome=" + cognome
                        + ", dataInizioValidita=" + dataInizioValidita
                        + ", dataFineValidita=" + dataFineValidita
                        + ", dataInizioValiditaCurrent=" + dataInizioValiditaCurrent
                        + ", dataFineValiditaCurrent=" + dataFineValiditaCurrent
                        + ", cdu=" + cdu + "]";
    }

    /**
     * @return the dataInizioValiditaCurrent
     */
    public Date getDataInizioValiditaCurrent () {
        return dataInizioValiditaCurrent;
    }

    /**
     * @param dataInizioValiditaCurrent the dataInizioValiditaCurrent to set
     */
    public void setDataInizioValiditaCurrent ( Date dataInizioValiditaCurrent ) {
        this.dataInizioValiditaCurrent = dataInizioValiditaCurrent;
    }

    /**
     * @return the dataFineValiditaCurrent
     */
    public Date getDataFineValiditaCurrent () {
        return dataFineValiditaCurrent;
    }

    /**
     * @param dataFineValiditaCurrent the dataFineValiditaCurrent to set
     */
    public void setDataFineValiditaCurrent ( Date dataFineValiditaCurrent ) {
        this.dataFineValiditaCurrent = dataFineValiditaCurrent;
    }

}
