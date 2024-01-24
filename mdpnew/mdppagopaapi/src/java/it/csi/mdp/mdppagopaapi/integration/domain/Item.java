/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ITEM_ITEMPKID_GENERATOR", sequenceName="ITEM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ITEM_ITEMPKID_GENERATOR")
	@Column(name="item_pk_id")
	private long itemPkId;

	@Column(name="creation_date")
	private Timestamp creationDate;

	private String description;

	@Column(name="item_id")
	private BigDecimal itemId;

	@Column(name="price_item")
	private BigDecimal priceItem;

	private BigDecimal quantity;

	@Column(name="total_price")
	private BigDecimal totalPrice;

	//bi-directional many-to-one association to Basket
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="basket_id")
	private Basket basket;

	//bi-directional many-to-one association to ItemExtraAttribute
	@OneToMany(mappedBy="item")
	private List<ItemExtraAttribute> itemExtraAttributes;

	public Item() {
	}

	public long getItemPkId() {
		return this.itemPkId;
	}

	public void setItemPkId(long itemPkId) {
		this.itemPkId = itemPkId;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getItemId() {
		return this.itemId;
	}

	public void setItemId(BigDecimal itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getPriceItem() {
		return this.priceItem;
	}

	public void setPriceItem(BigDecimal priceItem) {
		this.priceItem = priceItem;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Basket getBasket() {
		return this.basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public List<ItemExtraAttribute> getItemExtraAttributes() {
		return this.itemExtraAttributes;
	}

	public void setItemExtraAttributes(List<ItemExtraAttribute> itemExtraAttributes) {
		this.itemExtraAttributes = itemExtraAttributes;
	}

	public ItemExtraAttribute addItemExtraAttribute(ItemExtraAttribute itemExtraAttribute) {
		getItemExtraAttributes().add(itemExtraAttribute);
		itemExtraAttribute.setItem(this);

		return itemExtraAttribute;
	}

	public ItemExtraAttribute removeItemExtraAttribute(ItemExtraAttribute itemExtraAttribute) {
		getItemExtraAttributes().remove(itemExtraAttribute);
		itemExtraAttribute.setItem(null);

		return itemExtraAttribute;
	}

}
