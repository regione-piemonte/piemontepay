/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import it.csi.epay.epayservices.utilities.LogUtil;
import it.csi.epay.epayservices.utilities.MapId;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class _Manager {

	protected LogUtil log = new LogUtil(this.getClass());
	
	private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
	
	protected <T> T map(Object source, Class<T> clazz)
	{
		if (source == null) return null;
		return mapper.map(source, clazz);
	}
	
	protected <T> T map(Object source, Class<T> clazz, MapId mapId)
	{
		if (source == null) return null;
		return mapper.map(source, clazz, mapId.getNameMapId());
	}

	protected void map(Object source, Object dest)
	{
		if (source == null) return;
		mapper.map(source, dest);
	}
	
	protected void map(Object source, Object dest, MapId mapId)
	{
		if (source == null) return;
		mapper.map(source, dest, mapId.getNameMapId());
	}

	protected <A, DA> List<A> mapList(List<DA> listDa, Class<A> classA)
	{
		if (listDa == null) return null;
		List<A> listA = new ArrayList<A>();
		for (DA tuplaDa : listDa) {
			listA.add(map(tuplaDa, classA));
		}
		return listA;
	}
	
	protected <A, DA> List<A> mapList(List<DA> listDa, Class<A> classA, MapId mapId)
	{
		if (listDa == null) return null;
		List<A> listA = new ArrayList<A>();
		for (DA tuplaDa : listDa) {
			listA.add(map(tuplaDa, classA, mapId));
		}
		return listA;
	}

	protected java.sql.Timestamp getTimestamp() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		return new java.sql.Timestamp(now.getTime());
	}
	
	
	
private final XStream xstream = new XStream();
	
	protected String toXml(Object source)
	{
		try {
			return xstream.toXML(source);
		} catch (XStreamException e) {
			return null;
		}
	}
	
	protected Object fromXML(String source)
	{
		try {
			return xstream.fromXML(source);
		} catch (XStreamException e) {
			return null;
		}
	}
}
