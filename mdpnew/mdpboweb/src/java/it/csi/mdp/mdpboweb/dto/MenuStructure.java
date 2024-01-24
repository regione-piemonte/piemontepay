/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;
import java.util.*;
import flexjson.JSON;

public class MenuStructure implements java.io.Serializable {

	private String menuName;
	private List<MenuStructure> submenus = new ArrayList<MenuStructure>();

	private boolean visible;
	private boolean enabled;

	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@JSON(include = false)
	public List<MenuStructure> getSubmenus() {
		return submenus;
	}

	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public MenuStructure() {
	}

	public MenuStructure(String menuName) {
		this.menuName = menuName;
	}

	public MenuStructure(String menuName, boolean visible, boolean enabled,
			boolean active) {
		this.menuName = menuName;
		this.visible = visible;
		this.enabled = enabled;
	}

	@JSON(include = false)
	public List<String> getAllSubMenu() {
		List<String> allSubMenu = new ArrayList<String>();

		return addSubMenu(allSubMenu, getSubmenus());
	}

	@JSON(include = false)
	public MenuStructure getSubMenu(MenuStructure mn, String menuName) {
		for (MenuStructure menuStructure : mn.getSubmenus()) {
			if (menuStructure.getMenuName().equalsIgnoreCase(menuName)) {
				return menuStructure;
			}
			getSubMenu(menuStructure, menuName);
		}

		return mn;
	}

	@JSON(include = false)
	public List<MenuStructure> getSubMenuFlattenList(MenuStructure mn,
			String menuName) {
		List<MenuStructure> res = new ArrayList<MenuStructure>();
		res.addAll(mn.getSubmenus());
		for (MenuStructure menuStructure : mn.getSubmenus()) {
			res.addAll(menuStructure.getSubMenuFlattenList(menuStructure,
					menuStructure.getMenuName()));
		}
		return res;
	}

	private List<String> addSubMenu(List<String> allSubMenu,
			List<MenuStructure> submenus) {
		for (MenuStructure menuStructure : submenus) {
			allSubMenu.add(menuStructure.getMenuName());
			addSubMenu(allSubMenu, menuStructure.getSubmenus());
		}
		return allSubMenu;
	}
}
