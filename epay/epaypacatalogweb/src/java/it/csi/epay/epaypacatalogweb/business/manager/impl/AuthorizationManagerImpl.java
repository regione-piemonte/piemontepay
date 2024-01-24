/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager.impl;

import java.security.InvalidParameterException;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.epay.epaypacatalogweb.business.manager.AuthorizationManager;
import it.csi.epay.epaypacatalogweb.business.manager.EnteManager;
import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.codiceversamento.CodiceVersamentoVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;
import it.csi.epay.epaypacatalogweb.model.riferimentocontabile.RiferimentoContabileVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.security.EntityAction;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;


@Service
public class AuthorizationManagerImpl implements AuthorizationManager {

    private Logger logger = LoggerFactory.getLogger ( AuthorizationManager.class );

    @Autowired
    private EnteManager enteManager;

    public AuthorizationManagerImpl () {

    }

    @Override
    public boolean authorize ( Class<?> entity, EntityAction action ) {
        return authorize ( entity, null, action );
    }

    @Override
    public boolean authorize ( Class<?> entity, Long id, EntityAction action ) {
        if ( entity == null ) {
            throw new InvalidParameterException ();
        }

        if ( entity == EnteVO.class ) {
            return this.authorizeEnte ( null, id, action );
        } else if ( entity == VoceEntrataPPayVO.class ) {
            return this.authorizeVoceEntrataPPayVO ( null, id, action );
        } else if ( entity == CodiceVersamentoVO.class ) {
            return this.authorizeCodiceVersamentoVO ( null, id, action );
        } else if ( entity == RiferimentoContabileVO.class ) {
            return this.authorizeRiferimentoContabileVO ( null, id, action );
        } else if ( entity == TassonomiaVO.class ) {
            return this.authorizeTassonomiaVO ( null, id, action );
        } else if ( entity == UtenteVO.class ) {
            return true;
        }

        // TODO Auto-generated method stub
        throw new NotImplementedException ();
    }

    @Override
    public boolean authorize ( Object vo, EntityAction action ) {
        if ( vo == null ) {
            throw new InvalidParameterException ();
        }

        if ( vo.getClass () == EnteVO.class ) {
            return this.authorizeEnte ( (EnteVO) vo, null, action );
        } else if ( vo.getClass () == VoceEntrataPPayVO.class ) {
            return this.authorizeVoceEntrataPPayVO ( (VoceEntrataPPayVO) vo, null, action );
        }

        // TODO Auto-generated method stub
        return false;
    }

    private boolean authorizeRiferimentoContabileVO ( RiferimentoContabileVO vo, Object object, EntityAction action ) {
        UserDetails user = SecurityUtils.getUser ();
        if ( user == null ) {
            return false;
        }

        return true;
    }

    private boolean authorizeCodiceVersamentoVO ( CodiceVersamentoVO vo, Object object, EntityAction action ) {
        UserDetails user = SecurityUtils.getUser ();
        if ( user == null ) {
            return false;
        }

        return true;
    }
    
    private boolean authorizeTassonomiaVO ( TassonomiaVO vo, Object object, EntityAction action ) {
        UserDetails user = SecurityUtils.getUser ();
        if ( user == null ) {
            return false;
        }

        return true;
    }


    private boolean authorizeVoceEntrataPPayVO ( VoceEntrataPPayVO vo, Object object, EntityAction action ) {
        UserDetails user = SecurityUtils.getUser ();
        if ( user == null ) {
            return false;
        }

        if ( action == EntityAction.LIST ) {
            // TODO
            return true;
        }

        return false;
    }

    private boolean authorizeEnte ( EnteVO vo, Long id, EntityAction action ) {
        UserDetails user = SecurityUtils.getUser ();
        if ( user == null ) {
            return false;
        }

        if ( vo == null && id != null ) {
            try {
                vo = enteManager.getEnteById ( id );
            } catch ( OperationFailedException e ) {
                logger.error ( "error evaluating permissions", e );
                return false;
            }
        }

        if ( action == EntityAction.LIST ) {
            // TODO
            return true;
        }

        if ( action == EntityAction.READ ) {
            return true;
        } else if ( action == EntityAction.WRITE ) {

            if ( vo.getAdmins () != null )
                for ( Long admin: vo.getAdmins () ) {
                    if ( user.getUtente ().getId ().equals ( admin ) ) {
                        return true;
                    }
                }

            return false;

        } else if ( action == EntityAction.DELETE ) {
            return false;
        }

        return true;
    }
}
