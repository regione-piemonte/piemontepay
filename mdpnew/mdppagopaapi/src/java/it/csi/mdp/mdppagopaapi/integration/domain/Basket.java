/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the basket database table.
 * 
 */
@Entity
@NamedQuery(name="Basket.findAll", query="SELECT b FROM Basket b")
public class Basket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BASKET_BASKETID_GENERATOR", sequenceName="BASKET_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BASKET_BASKETID_GENERATOR")
	@Column(name="basket_id")
	private String basketId;

	@Column(name="creation_date")
	private Timestamp creationDate;

	@Column(name="last_change_date")
	private Timestamp lastChangeDate;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="basket")
	private List<Item> items;

	public Basket() {
	}

	public String getBasketId() {
		return this.basketId;
	}

	public void setBasketId(String basketId) {
		this.basketId = basketId;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Timestamp lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setBasket(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setBasket(null);

		return item;
	}

}
