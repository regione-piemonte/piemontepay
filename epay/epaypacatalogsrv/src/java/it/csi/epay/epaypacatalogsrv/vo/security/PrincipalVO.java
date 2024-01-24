/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.vo.security;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.function.Supplier;

import it.csi.epay.epaypacatalogsrv.vo.ParentVO;


public class PrincipalVO extends ParentVO {

    private static final long serialVersionUID = 1L;

    private PrincipalUtenteVO utente;
    private Supplier<PrincipalUtenteVO> utenteLoader;

    private PrincipalProfiloVO profilo;
    private Supplier<PrincipalProfiloVO> profiloLoader;

    private PrincipalEnteVO ente;
    private Supplier<PrincipalEnteVO> enteLoader;

    private PrincipalRuoloVO ruolo;

    private PrincipalFruitoreVO fruitore;
    private Supplier<PrincipalFruitoreVO> fruitoreLoader;

    private List<PrincipalTematicaVO> tematiche;
    private Supplier<List<PrincipalTematicaVO>> tematicheLoader;

    private String ipOrigine;

    public PrincipalVO() {
		super();
	}

	public boolean hasUseCase ( String uc ) {
        if ( uc == null || uc.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( ruolo != null && ruolo.hasUseCase ( uc ) ) {
            return true;
        }

        PrincipalUtenteVO u = getUtente();
        if ( u != null && u.hasUseCase ( uc ) ) {
            return true;
        }

        return false;
    }

    public boolean hasScope ( String scope ) {
        if ( scope == null || scope.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        PrincipalFruitoreVO f = getFruitore();
        if ( f != null && f.hasScope ( scope ) ) {
            return true;
        }

        return false;
    }

    public PrincipalTematicaVO cercaAssociazioneTematica ( String codice ) {
        if ( codice == null || codice.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( getTematiche() != null ) {
            for ( PrincipalTematicaVO t: getTematiche() ) {
                if ( t.getCodice ().equals ( codice ) ) {
                    return t;
                }
            }
        }

        return null;
    }

    
    /**
     * @param tematicheLoader the tematicheLoader to set
     */
    public void setTematicheLoader ( Supplier<List<PrincipalTematicaVO>> tematicheLoader ) {
        this.tematicheLoader = tematicheLoader;
    }

    public void setUtenteLoader(Supplier<PrincipalUtenteVO> utenteLoader) {
		this.utenteLoader = utenteLoader;
	}

	public void setProfiloLoader(Supplier<PrincipalProfiloVO> profiloLoader) {
		this.profiloLoader = profiloLoader;
	}

	public void setFruitoreLoader(Supplier<PrincipalFruitoreVO> fruitoreLoader) {
		this.fruitoreLoader = fruitoreLoader;
	}

	public void setEnteLoader(Supplier<PrincipalEnteVO> enteLoader) {
		this.enteLoader = enteLoader;
	}

	public String getIpOrigine () {
        return ipOrigine;
    }

    public void setIpOrigine ( String ipOrigine ) {
        this.ipOrigine = ipOrigine;
    }

    public PrincipalProfiloVO getProfilo () {
    	if ( profilo == null ) {
            if ( profiloLoader == null ) {
                return profilo;
            }
            synchronized ( profiloLoader ) { // NOSONAR
                if ( profilo == null ) {
                	profilo = this.profiloLoader.get ();
                }
            }
        }
        return profilo;
    }

    public void setProfilo ( PrincipalProfiloVO profilo ) {
        this.profilo = profilo;
    }

    public List<PrincipalTematicaVO> getTematiche () {
        if ( tematiche == null ) {
            if ( tematicheLoader == null ) {
                return tematiche;
            }
            synchronized ( tematicheLoader ) { // NOSONAR
                if ( tematiche == null ) {
                    tematiche = this.tematicheLoader.get ();
                }
            }
        }
        return tematiche;
    }

    public void setTematiche ( List<PrincipalTematicaVO> tematiche ) {
        this.tematiche = tematiche;
    }

    public PrincipalFruitoreVO getFruitore () {
        if ( fruitore == null ) {
            if ( fruitoreLoader == null ) {
                return fruitore;
            }
            synchronized ( fruitoreLoader ) { // NOSONAR
                if ( fruitore == null ) {
                	fruitore = this.fruitoreLoader.get ();
                }
            }
        }
        return fruitore;
    }

    public void setFruitore ( PrincipalFruitoreVO fruitore ) {
        this.fruitore = fruitore;
    }

    public PrincipalUtenteVO getUtente () {
    	if ( utente == null ) {
            if ( utenteLoader == null ) {
                return utente;
            }
            synchronized ( utenteLoader ) { // NOSONAR
                if ( utente == null ) {
                	utente = this.utenteLoader.get ();
                }
            }
        }
        return utente;
    }

    public void setUtente ( PrincipalUtenteVO utente ) {
        this.utente = utente;
    }

    public PrincipalEnteVO getEnte () {
    	if ( ente == null ) {
            if ( enteLoader == null ) {
                return ente;
            }
            synchronized ( enteLoader ) { // NOSONAR
                if ( ente == null ) {
                	ente = this.enteLoader.get ();
                }
            }
        }
        return ente;
    }

    public void setEnte ( PrincipalEnteVO ente ) {
        this.ente = ente;
    }

    public PrincipalRuoloVO getRuolo () {
        return ruolo;
    }

    public void setRuolo ( PrincipalRuoloVO ruolo ) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString () {
        return "PrincipalVO [utente=" + utente + ", profilo=" + profilo + ", ente=" + ente + ", ruolo=" + ruolo + ", fruitore=" + fruitore + ", tematiche="
            + tematiche + ", ipOrigine=" + ipOrigine + "]";
    }

}
