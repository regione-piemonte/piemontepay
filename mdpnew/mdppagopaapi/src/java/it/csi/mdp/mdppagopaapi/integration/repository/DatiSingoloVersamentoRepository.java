/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.DatiSingoloVersamento;


/**
 * Spring data Jpa repository for "DatiSingoloVersamentoRepository" <br>
 *
 * @author Silvia.Balsamini
 * @author Marco.Mezzolla
 */

@Repository
public interface DatiSingoloVersamentoRepository extends IRepository<DatiSingoloVersamento, Integer> {

    List<DatiSingoloVersamento> findByMultiId ( int elementoMultiversamento );

}
