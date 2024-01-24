/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.model.Utente_;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.repository.util.CriteriaBuilderUtil;


public class UtenteRepositoryImpl implements UtenteRepositoryCustom {

    @Autowired
    private UtenteRepository baseRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Override
    public List<Utente> ricerca ( RicercaUtenteInput input, Long idEnte ) {

        List<Specification<Utente>> filters = new ArrayList<> ();

        if ( !StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Utente_.codiceFiscale, input.getCodiceFiscale () ) );
        }
        if ( !StringUtils.isBlank ( input.getNome () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Utente_.nome, input.getNome () ) );
        }
        if ( !StringUtils.isBlank ( input.getCognome () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Utente_.cognome, input.getCognome () ) );
        }
        if ( !StringUtils.isBlank ( input.getEmail () ) ) {
            filters.add ( CriteriaBuilderUtil.likeCaseInsensitive ( Utente_.email, input.getEmail () ) );
        }
        if ( EntityUtils.isTrue ( input.getSoloUtentiInVita () ) ) {

            filters.add ( new Specification<Utente> () {

                Date now = new Date ();

                @Override
                public Predicate toPredicate ( Root<Utente> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {

                    return builder.and (
                        builder.isNotNull ( root.get ( Utente_.dataInizioValidita ) ),
                        builder.lessThanOrEqualTo ( root.get ( Utente_.dataInizioValidita ), now ),
                        builder.or (
                            builder.isNull ( root.get ( Utente_.dataFineValidita ) ),
                            builder.greaterThan ( root.get ( Utente_.dataFineValidita ), now ) ) );
                }
            } );
        }

        List<Utente> records;
        Sort sortClause = new Sort ( "nome", "cognome" );

        if ( filters.size () > 0 ) {
            records = baseRepository.findAll ( CriteriaBuilderUtil.groupAnd ( filters ), sortClause );
        } else {
            records = baseRepository.findAll ( sortClause );
        }

        records = filtraPerVisibilitaSuEnte ( input, records, idEnte );

        //EPAY-80
        if ( EntityUtils.isTrue ( input.getSoloUtentiInVita () ) ) {
            List<Utente> filteredRecords = new ArrayList<> ();
            for ( Utente infoUtente: records ) {
                if ( null != infoUtente.getAssociazioneUtenteEnte () ) {
                    for ( AssociazioneUtenteEnte aue: infoUtente.getAssociazioneUtenteEnte () ) {
                        if ( infoUtente.getId ().equals ( aue.getId ().getIdUtente () )
                                        && idEnte.equals ( new Long ( aue.getId ().getIdEnte () ) ) ) {

                            if ( !isAccountExpired ( aue ) ) {
                                filteredRecords.add ( infoUtente );
                            }
                        }
                    }
                }
            }
            records = filteredRecords;
        }


        records = filtraPerVisibilitaSuFunzioni ( input, records, idEnte );
        records = filtraPerVisibilitaSuTematicheECodiciVersamento ( input, records, idEnte );

        return records;
    }

    private List<AssociazioneUtenteEnte> getAdmins(Long idEnte) {

        Ente ente = enteRepository.findOneById(idEnte);

        AssociazioneUtenteEntePK assoc = new AssociazioneUtenteEntePK ();
        assoc.setIdEnte ( ente.getId ().intValue () );
        List<AssociazioneUtenteEnte> admin = associazioneUtenteEnteRepository.findByIdEnteAndFlagAdmin ( idEnte.intValue (), true );

        return admin;
    }


    private List<Utente> filtraPerVisibilitaSuTematicheECodiciVersamento ( RicercaUtenteInput input, List<Utente> records, Long idEnte ) {

        if ( records.size () < 1 ) {
            return records;
        }

        if ( !StringUtils.isBlank ( input.getCodiceTematica () ) ) {

            List<Utente> filtrati = new ArrayList<> ();

            List<AssociazioneUtenteTematica> associazioniTematica = associazioneUtenteTematicaRepository.findByCodiceTematica ( input.getCodiceTematica () );

            for ( Utente risultato: records ) {

                boolean ok = false;
                for ( AssociazioneUtenteTematica associazione: associazioniTematica ) {
                    if ( associazione.getId ().getIdUtente ().equals ( risultato.getId () ) ) {
                        ok = true;
                        break;
                    }
                }
                if ( ok ) {
                    filtrati.add ( risultato );
                }
            }

            List<AssociazioneUtenteEnte> admins = getAdmins(idEnte);
            
            for(AssociazioneUtenteEnte admin:admins) {
                Utente adminCurr = baseRepository.findOne ( admin.getId ().getIdUtente () );
                
                if (!filtrati.contains(adminCurr)) {
                    filtrati.add(adminCurr);
                }
            }
            
            records = filtrati;
        }

        if ( input.getIdCodiceVersamento () != null ) {
            List<AssociazioneUtenteCodiceVersamento> associazioniCV
            = associazioneUtenteCodiceVersamentoRepository.findByIdCodiceVersamento ( input.getIdCodiceVersamento ().intValue () );

            String codiceTematica = codiceVersamentoRepository.findOne ( input.getIdCodiceVersamento () ).getVoceEntrata ().getTematica ().getCodice ();
            List<AssociazioneUtenteTematica> associazioniTematica = associazioneUtenteTematicaRepository.findByCodiceTematica ( codiceTematica );
            List<AssociazioneUtenteTematica> associazioniTematicaTotali = new ArrayList<> ();
            for ( AssociazioneUtenteTematica a: associazioniTematica ) {
                if ( EntityUtils.isTrue ( a.getFlagVisibilitaTotale () ) ) {
                    associazioniTematicaTotali.add ( a );
                }
            }

            List<Utente> filtrati = new ArrayList<> ();

            for ( Utente risultato: records ) {
                boolean ok = false;

                for ( AssociazioneUtenteCodiceVersamento associazione: associazioniCV ) {
                    if ( associazione.getId ().getIdUtente ().equals ( risultato.getId () ) ) {
                        ok = true;
                        break;
                    }
                }

                if ( !ok ) {

                    for ( AssociazioneUtenteTematica associazione: associazioniTematicaTotali ) {
                        if ( associazione.getId ().getIdUtente ().equals ( risultato.getId () ) ) {
                            ok = true;
                            break;
                        }
                    }
                }

                if ( ok ) {
                    filtrati.add ( risultato );
                }
            }

            records = filtrati;

        }

        return records;
    }

    private List<Utente> filtraPerVisibilitaSuFunzioni ( RicercaUtenteInput input, List<Utente> records, Long idEnte ) {
        if ( records.size () < 1 ) {
            return records;
        }

        if ( StringUtils.isNotBlank ( input.getCodiceCdu () ) ) {
            List<AssociazioneUtenteCdu> utentiPermessi = associazioneUtenteCduRepository.findByCodiceCduAndIdEnte ( input.getCodiceCdu (), idEnte.intValue () );

            List<Utente> filtrati = new ArrayList<> ();

            for ( Utente risultato: records ) {
                boolean ok = false;
                for ( AssociazioneUtenteCdu associazione: utentiPermessi ) {
                    if ( associazione.getId ().getIdUtente ().equals ( risultato.getId () ) ) {
                        ok = true;
                        break;
                    }
                }
                if ( ok ) {
                    filtrati.add ( risultato );
                }
            }

            addAminOnLoginCdu(input.getCodiceCdu (), idEnte,records);

            records = filtrati;
        }

        if ( StringUtils.isNotBlank ( input.getCodiceCategoriaCdu () ) ) {
            List<AssociazioneUtenteCdu> utentiPermessi
            = associazioneUtenteCduRepository.findByCodiceCategoriaCduAndIdEnte ( input.getCodiceCategoriaCdu (), idEnte.intValue () );
            List<Utente> filtrati = new ArrayList<> ();

            for ( Utente risultato: records ) {
                boolean ok = false;
                for ( AssociazioneUtenteCdu associazione: utentiPermessi ) {
                    if ( associazione.getId ().getIdUtente ().equals ( risultato.getId () ) ) {
                        ok = true;
                        break;
                    }
                }
                if ( ok ) {
                    filtrati.add ( risultato );
                }
            }

            records = filtrati;
        }

        return records;
    }

    private void addAminOnLoginCdu(String casoDuso, Long idEnte, List<Utente> records) {
        if(casoDuso == null) {
            return;
        }

        //EPAY-80
        List<AssociazioneUtenteEnte> assUEAdmins = associazioneUtenteEnteRepository.findByIdEnteAndFlagAdmin ( idEnte.intValue (), true );
        if ( null != assUEAdmins ) {
            for ( AssociazioneUtenteEnte ass: assUEAdmins ) {

                Utente admin = baseRepository.findOne ( ass.getId ().getIdUtente () );

                boolean added = false;
                for ( Utente record: records ) {
                    if ( record.getCodiceFiscale ().equalsIgnoreCase ( admin.getCodiceFiscale () ) ) {
                        added = true;
                        break;
                    }
                }
                if ( !added ) {
                    records.add ( admin );
                }
            }
        }
        //EPAY-80
        //        Ente ente = enteRepository.findOneById(idEnte);
        //
        //        Utente admin = (ente.getUtenteAmministratore ());
        //
        //        if(admin != null) {
        //            for (Utente record : records) {
        //                if (record.getCodiceFiscale ().equalsIgnoreCase ( admin.getCodiceFiscale () )) {
        //                    return;
        //                }
        //            }
        //            records.add ( admin );
        //        }
    }

    private List<Utente> filtraPerVisibilitaSuEnte ( RicercaUtenteInput input, List<Utente> records, Long idEnte ) {

        List<Long> idUtentiPermessi = findIdUtentiAssociatiByIdEnte ( idEnte );
        List<Utente> filtratiPerEnte = new ArrayList<> ();
        for ( Utente risultato: records ) {
            if ( idUtentiPermessi.contains ( risultato.getId ().longValue () ) ) {
                filtratiPerEnte.add ( risultato );
            }
        }
        records = filtratiPerEnte;

        return records;
    }

    @Override
    public List<Long> findIdEntiAssociatiByIdUtente ( Long idUtente ) {
        List<Number> raw = baseRepository.getRawIdEntiAssociatiByIdUtente ( idUtente );
        List<Long> output = new ArrayList<> ();
        for ( Number r: raw ) {
            output.add ( Long.valueOf ( r.toString () ) );
        }
        return output;
    }

    @Override
    public List<Long> findIdUtentiAssociatiByIdEnte ( Long idEnte ) {
        List<Number> raw = baseRepository.getRawIdUtentiAssociatiByIdEnte ( idEnte );
        List<Long> output = new ArrayList<> ();
        for ( Number r: raw ) {
            output.add ( Long.valueOf ( r.toString () ) );
        }
        return output;
    }

    //EPAY-80
    private boolean isAccountExpired ( AssociazioneUtenteEnte infoUtente ) {

        if ( infoUtente.getDataInizioValidita () == null ) {
            return true;
        }

        Date now = new Date ();
        Date dataInizio = infoUtente.getDataInizioValidita ();

        if ( dataInizio.after ( now ) ) {
            return true;
        }

        if ( infoUtente.getDataFineValidita () == null ) {
            return false;
        }

        Date dataFine = infoUtente.getDataFineValidita ();

        if ( dataFine.after ( now ) ) {
            return false;
        } else {
            return true;
        }
    }
}
