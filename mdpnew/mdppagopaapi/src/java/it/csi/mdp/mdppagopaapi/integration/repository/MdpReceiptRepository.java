/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;


/**
 * Spring data Jpa repository for "MdpReceiptRepository" <br>
 *
 * @author Marco.Mezzolla
 */

@Repository
public interface MdpReceiptRepository extends IRepository<MdpReceipt, Integer> {

    //Usato annotation con la query HQL perche' la versione di jpa(1.2.0) non supporta la TOP supportata dalla versione 1.7.
    @Query("SELECT e FROM MdpReceipt e WHERE e.receiptId =:receiptId ORDER BY e.id DESC LIMIT 1")
    MdpReceipt findTopByReceiptIdOrderByIdDesc ( String receiptId );

    //Usato annotation con la query HQL perche' la versione di jpa(1.2.0) non supporta la TOP supportata dalla versione 1.7.
    @Query("SELECT e FROM MdpReceipt e WHERE e.creditorReferenceid =:reqIuv ORDER BY e.id DESC LIMIT 1")
    MdpReceipt findTopBycreditorReferenceidOrderByIdDesc ( String reqIuv );

    //Usato annotation con la query HQL perche' la versione di jpa(1.2.0) non supporta la TOP supportata dalla versione 1.7.
    @Query("SELECT e FROM MdpReceipt e WHERE e.receiptId =:receiptId AND e.idPa =:idPa ORDER BY e.id DESC LIMIT 1")
	MdpReceipt findTopByReceiptIdAndIdPaOrderByIdDesc ( @Param ( "receiptId" ) String receiptId, @Param ( "idPa" ) String idPa );
}
