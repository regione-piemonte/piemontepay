/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Questa classe usa la reflection per mappare un set di bean simili tra loro.
 * <p>
 * Questa classe espone un solo metodo pubblico statico, convert, che prende in input un oggetto
 * di tipo properties per mappare le informazioni tra bean.
 * </p>
 * <p>
 * L'oggetto di ripo properties e' nel formato:
 * com.package.SomeClass=com.otherpackage.OtherClass
 * 
 * oppure se ci sono due packages con un set di classi che hanno lo stesso nome
 * si puo' specificare cosi':
 * com.package.*=com.otherpackage.*
 * </p>
 * <p>
 * Quando la classe in input viene passata e un mapping viene trovato, le properties e
 * gli attributi public non-final nel bean vengono matchati con le properties e
 * gli attributi non-final sull'oggetto target. Ogni properties che non matcha viene ignorata.
 * Properties e attributes vengono considerati intercambiabili
 * per cui un public attribute su un bean puo' essere una property sull'altro.
 * </p>
 */
public class BeanMapping {

	@SuppressWarnings("unchecked")
	static final Class[] BASE_TYPES = {java.lang.Integer.class,
			java.lang.Long.class, java.lang.Byte.class,
			java.lang.Character.class, java.lang.Boolean.class,
			java.lang.Double.class, java.lang.Float.class,
			java.lang.String.class, java.lang.Number.class,
			java.lang.CharSequence.class};
	@SuppressWarnings("unchecked")
	private static final HashMap<Class, HashMap<String, Object>> inspections = new HashMap<Class, HashMap<String, Object>>();

	/** Creates a new instance of BeanMapping */
	private BeanMapping() {
		super();
	}

	/**
	 * Converte un bean nel suo tipo mappato.
	 * 
	 * @param mappings
	 *            rappresenta le mappings properties
	 * @param bean
	 *            rappresenta il bean da convertire nel suo tipo mappato
	 * @throws java.beans.IntrospectionException
	 * @throws java.lang.ClassNotFoundException
	 * @throws java.lang.InstantiationException
	 * @throws java.lang.IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 * @throws com.totsp.gwt.beans.server.MappingException
	 * @return l'oggetto mappato.
	 */
	public static Object convert(Properties mappings, Object bean)
			throws IntrospectionException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, MappingException {
		IdentityHashMap<Object, Object> instances = new IdentityHashMap<Object, Object>();

		return convertInternal(instances, mappings, bean);
	}

	@SuppressWarnings("unchecked")
	private static boolean isInterface(Class interfaceClass, Class check) {
		return check.equals(interfaceClass)
				|| arrayContains(check.getInterfaces(), interfaceClass);
	}

	private static boolean arrayContains(Object[] array, Object find) {
		for (Object match : array) {
			if ((match == find) || match.equals(find)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	private static void convertCollection(
			IdentityHashMap<Object, Object> instances, Properties mappings,
			Collection source, Collection destination)
			throws IntrospectionException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, MappingException {
		if (source == null) {
			return;
		}

		for (Iterator it = source.iterator(); it.hasNext();) {
			Object o = it.next();

			if (!arrayContains(BASE_TYPES, o.getClass())) {
				o = convertInternal(instances, mappings, o);
			}

			destination.add(o);
		}
	}

	@SuppressWarnings("unchecked")
	private static Object convertInternal(
			IdentityHashMap<Object, Object> instances, Properties mappings,
			Object bean) throws IntrospectionException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, MappingException {
		// if null
		if (bean == null) {
			return null;
		}

		// if we have already seen this instance.
		if (instances.containsKey(bean)) {
			return instances.get(bean);
		}

		// if this is an array, backstep the array and return it.
		if (bean.getClass().isArray()) {
			Object[] beans = (Object[]) bean;
			Class arrayClass = resolveArray(mappings, bean);
			Object[] destination = (Object[]) Array.newInstance(arrayClass,
					beans.length);

			for (int i = 0; i < beans.length; i++) {
				destination[i] = convertInternal(instances, mappings, beans[i]);
			}

			return destination;
		}

		// if this is a primitve or a common type, just return it.
		if (bean.getClass().isPrimitive()
				|| arrayContains(BASE_TYPES, bean.getClass())) {
			return bean;
		}

		if (isInterface(Map.class, bean.getClass())) {
			Map map = (Map) resolveMapType(bean.getClass(), bean.getClass())
					.newInstance();
			convertMap(instances, mappings, (Map) bean, map);
			return map;
		} else if (isInterface(List.class, bean.getClass())
				|| isInterface(ArrayList.class, bean.getClass())) {
			List list = (List) resolveListType(bean.getClass(), bean.getClass())
					.newInstance();
			convertCollection(instances, mappings, (List) bean, list);
			return list;
		} else if (isInterface(Collection.class, bean.getClass())) {
			Collection collection = (Collection) resolveCollecitonType(
					bean.getClass(), bean.getClass()).newInstance();
			convertCollection(instances, mappings, (Collection) bean,
					collection);
			return collection;
		}
		// if we have gotten here,
		// this is a class that requires resolution mapping.
		Class destinationClass = resolveClass(mappings, bean.getClass());

		if (destinationClass == null) {
			throw new MappingException("Unable to resolve class"
					+ bean.getClass().getName());
		}

		Object dest = destinationClass.newInstance();

		// store the instance so it is there when we recurse into the
		// properties.
		instances.put(bean, dest);

		HashMap<String, Object> sourceProperties = inspectObject(bean);
		HashMap<String, Object> destinationProperties = inspectObject(dest);

		for (Iterator<String> it = sourceProperties.keySet().iterator(); it
				.hasNext();) {
			String propertyName = it.next();

			if (!destinationProperties.containsKey(propertyName)) {
				continue;
			}

			// System.out.println(destinationClass.getName() + " " +
			// propertyName);

			Object sourceAccessor = sourceProperties.get(propertyName);
			Object destinationAccessor = destinationProperties
					.get(propertyName);
			Class valueClass = null;
			Object valueObject = null;

			if (sourceAccessor instanceof Field) {
				Field f = (Field) sourceAccessor;
				valueClass = f.getType();
				valueObject = f.get(bean);
			} else {
				PropertyDescriptor pd = (PropertyDescriptor) sourceAccessor;
				valueClass = pd.getPropertyType();
				valueObject = pd.getReadMethod().invoke(bean);
			}

			Class valueDestinationClass = null;

			if (destinationAccessor instanceof Field) {
				Field f = (Field) destinationAccessor;
				valueDestinationClass = f.getType();

				if (valueClass.isArray() && valueObject != null
						&& valueDestinationClass.isArray()) {
					Object[] beans = (Object[]) valueObject;
					Class arrayClass = resolveArray(mappings, valueObject);
					Object[] destination = (Object[]) Array.newInstance(
							arrayClass, beans.length);

					for (int i = 0; i < beans.length; i++) {
						destination[i] = convertInternal(instances, mappings,
								beans[i]);
					}

					f.set(dest, new Object[]{destination});
				} else if (valueObject != null && valueClass.isArray()
						&& isInterface(ArrayList.class, valueDestinationClass)) {
					Object[] beans = (Object[]) valueObject;
					List list = (ArrayList) valueDestinationClass.newInstance();
					for (int i = 0; i < beans.length; i++) {
						list.add(convertInternal(instances, mappings, beans[i]));
					}
					f.set(dest, list);
				} else if (isInterface(ArrayList.class, valueClass)
						&& valueDestinationClass.isArray()) {
					List list = (ArrayList) valueObject;
					//Class arrayClass = resolveArray(mappings, valueDestinationClass.getComponentType());
					Object[] destination = (Object[]) Array.newInstance(
							valueDestinationClass.getComponentType(),
							list.size());

					for (int i = 0; i < list.size(); i++) {
						destination[i] = convertInternal(instances, mappings,
								list.get(i));
					}

					f.set(dest, new Object[]{destination});
				} else if (isInterface(ArrayList.class, valueClass)
						&& isInterface(HashSet.class, valueDestinationClass)) {
					Set set = (HashSet) valueDestinationClass.newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							set);
					f.set(dest, set);
				} else if (isInterface(Map.class, valueClass)
						&& isInterface(Map.class, valueDestinationClass)) {
					Map map = (Map) resolveMapType(valueClass,
							valueDestinationClass).newInstance();
					convertMap(instances, mappings, (Map) valueObject, map);
					f.set(dest, map);
				} else if (isInterface(List.class, valueClass)
						&& isInterface(List.class, valueDestinationClass)) {
					List list = (List) resolveListType(valueClass,
							valueDestinationClass).newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							list);
					f.set(dest, list);
				} else if (isInterface(ArrayList.class, valueClass)
						&& isInterface(ArrayList.class, valueDestinationClass)) {
					List list = (List) resolveListType(valueClass,
							valueDestinationClass).newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							list);
					f.set(dest, list);
				} else if (isInterface(Collection.class, valueClass)
						&& isInterface(Collection.class, valueDestinationClass)) {
					Collection collection = (Collection) resolveCollecitonType(
							valueClass, valueDestinationClass).newInstance();
					convertCollection(instances, mappings,
							(Collection) valueObject, collection);
					f.set(dest, collection);
				} else if (valueClass == valueDestinationClass) {
					f.set(dest, valueObject);
				} else if ((valueDestinationClass == resolveClass(mappings,
						valueClass))
						|| (valueDestinationClass.isArray() && valueClass
								.isArray())) {
					f.set(dest,
							convertInternal(instances, mappings, valueObject));
				} else {
					continue;
				}
			} else {
				PropertyDescriptor pd = (PropertyDescriptor) destinationAccessor;
				valueDestinationClass = pd.getPropertyType();

				if (valueClass.isArray() && valueObject != null
						&& valueDestinationClass.isArray()) {
					Object[] beans = (Object[]) valueObject;
					//Class arrayClass = resolveArray(mappings, valueDestinationClass.getComponentType());
					Object[] destination = (Object[]) Array.newInstance(
							valueDestinationClass.getComponentType(),
							beans.length);

					for (int i = 0; i < beans.length; i++) {
						destination[i] = convertInternal(instances, mappings,
								beans[i]);
					}

					pd.getWriteMethod().invoke(dest, new Object[]{destination});
				} else if (valueObject != null && valueClass.isArray()
						&& isInterface(ArrayList.class, valueDestinationClass)) {
					Object[] beans = (Object[]) valueObject;
					List list = (ArrayList) valueDestinationClass.newInstance();
					for (int i = 0; i < beans.length; i++) {
						list.add(convertInternal(instances, mappings, beans[i]));
					}

					pd.getWriteMethod().invoke(dest, list);
				} else if (isInterface(ArrayList.class, valueClass)
						&& valueDestinationClass.isArray()) {
					List list = (ArrayList) valueObject;
					if (list.size() > 0) {
						Object[] destination = (Object[]) Array.newInstance(
								valueDestinationClass.getComponentType(),
								list.size());

						for (int i = 0; i < list.size(); i++) {
							destination[i] = convertInternal(instances,
									mappings, list.get(i));
						}

						pd.getWriteMethod().invoke(dest,
								new Object[]{destination});
					}
				} else if (isInterface(ArrayList.class, valueClass)
						&& isInterface(HashSet.class, valueDestinationClass)) {
					Set set = (HashSet) valueDestinationClass.newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							set);
					pd.getWriteMethod().invoke(dest, set);
				} else if (isInterface(Map.class, valueClass)
						&& isInterface(Map.class, valueDestinationClass)) {
					Map map = (Map) resolveMapType(valueClass,
							valueDestinationClass).newInstance();
					convertMap(instances, mappings, (Map) valueObject, map);
					pd.getWriteMethod().invoke(dest, map);
				} else if (isInterface(List.class, valueClass)
						&& isInterface(List.class, valueDestinationClass)) {
					List list = (List) resolveListType(valueClass,
							valueDestinationClass).newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							list);
					pd.getWriteMethod().invoke(dest, list);
				} else if (isInterface(ArrayList.class, valueClass)
						&& isInterface(ArrayList.class, valueDestinationClass)) {
					List list = (List) resolveListType(valueClass,
							valueDestinationClass).newInstance();
					convertCollection(instances, mappings, (List) valueObject,
							list);
					pd.getWriteMethod().invoke(dest, list);
				} else if (isInterface(Collection.class, valueClass)
						&& isInterface(Collection.class, valueDestinationClass)) {
					Collection collection = (Collection) resolveCollecitonType(
							valueClass, valueDestinationClass).newInstance();
					convertCollection(instances, mappings,
							(Collection) valueObject, collection);
					pd.getWriteMethod().invoke(dest, collection);
				} else if (valueClass == valueDestinationClass) {
					pd.getWriteMethod().invoke(dest, valueObject);
				} else if ((valueDestinationClass == resolveClass(mappings,
						valueClass))
						|| (valueDestinationClass.isArray() && valueClass
								.isArray())) {
					pd.getWriteMethod().invoke(dest,
							convertInternal(instances, mappings, valueObject));
				} else {
					continue;
				}
			}
		}

		return dest;
	}

	@SuppressWarnings("unchecked")
	private static void convertMap(IdentityHashMap<Object, Object> instances,
			Properties mappings, Map source, Map destination)
			throws IntrospectionException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, MappingException {
		if (source == null) {
			return;
		}

		for (Iterator<Entry<Object, Object>> it = source.entrySet().iterator(); it
				.hasNext();) {
			Entry<Object, Object> entry = it.next();
			Object key = entry.getKey();

			if (!arrayContains(BASE_TYPES, key.getClass())) {
				key = convertInternal(instances, mappings, key);
			}

			Object value = entry.getValue();

			if (!arrayContains(BASE_TYPES, value.getClass())) {
				value = convertInternal(instances, mappings, value);
			}

			destination.put(key, value);
		}
	}

	private static HashMap<String, Object> inspectObject(Object o)
			throws IntrospectionException {
		if (inspections.containsKey(o.getClass())) {
			return inspections.get(o.getClass());
		}

		PropertyDescriptor[] pds = Introspector.getBeanInfo(o.getClass())
				.getPropertyDescriptors();
		HashMap<String, Object> values = new HashMap<String, Object>();

		for (PropertyDescriptor pd : pds) {
			if (pd.getName().equals("class")
					|| (pd.getReadMethod() == null)
					|| (pd.getWriteMethod() == null)
					|| ((pd.getReadMethod().getModifiers() & Modifier.PUBLIC) == 0)
					|| ((pd.getWriteMethod().getModifiers() & Modifier.PUBLIC) == 0)) {
				continue;
			}

			values.put(pd.getName(), pd);
		}

		for (Field field : o.getClass().getFields()) {
			if (((field.getModifiers() & Modifier.PUBLIC) != 0)
					&& ((field.getModifiers() & Modifier.FINAL) == 0)
					&& ((field.getModifiers() & Modifier.STATIC) == 0)
					&& (values.get(field.getName()) == null)) {
				values.put(field.getName(), field);
			}
		}

		inspections.put(o.getClass(), values);

		return values;
	}

	@SuppressWarnings("unchecked")
	private static Class resolveArray(Properties mappings, Object bean)
			throws ClassNotFoundException {
		int arrayDepth = 0;
		Class clazz = bean.getClass().getComponentType();

		if (bean.getClass().getComponentType() == null) {
			clazz = bean.getClass();
		}

		while (clazz.isArray()) {
			clazz = clazz.getComponentType();
			arrayDepth++;
		}

		if (!clazz.isPrimitive() && !arrayContains(BASE_TYPES, clazz)) {
			clazz = resolveClass(mappings, clazz);

			if (clazz == null) {
				return null;
			}
		}

		Object array = null;

		for (int i = 0; i < arrayDepth; i++) {
			array = Array.newInstance(clazz, 0);
			clazz = array.getClass();
		}

		return clazz;
	}

	@SuppressWarnings("unchecked")
	private static Class resolveClass(Properties mappings, Class clazz)
			throws ClassNotFoundException {
		// System.out.println("Resolving class: " + clazz.getName());
		assert ((mappings != null) && (mappings.size() > 0));
		assert (clazz != null);

		if (mappings.containsKey(clazz.getName())) {
			return Class.forName(mappings.getProperty(clazz.getName()));
		} else if (mappings.containsValue(clazz.getName())) {
			for (Iterator<Entry<Object, Object>> it = mappings.entrySet()
					.iterator(); it.hasNext();) {
				Entry entry = it.next();

				if (entry.getValue().equals(clazz.getName())) {
					return Class.forName(entry.getValue().toString());
				}
			}
		} else if (mappings.containsKey(trimPackage(clazz.getName()) + ".*")) {
			String newClass = trimPackage(mappings
					.getProperty(trimPackage(clazz.getName()) + ".*"))
					+ "."
					+ clazz.getSimpleName();
			// System.out.println("Mapped to: " + newClass);

			return Class.forName(newClass);
		} else if (mappings.containsValue(trimPackage(clazz.getName()) + ".*")) {
			for (Iterator<Entry<Object, Object>> it = mappings.entrySet()
					.iterator(); it.hasNext();) {
				Entry entry = it.next();

				if (entry.getValue()
						.equals(trimPackage(clazz.getName()) + ".*")) {
					String newClass = trimPackage(entry.getKey().toString())
							+ "." + clazz.getSimpleName();
					// System.out.println( "Mapped to: "+newClass);
					return Class.forName(newClass);
				}
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private static Class resolveCollecitonType(Class source, Class destination) {
		if (source.equals(Collection.class)
				&& destination.equals(Collection.class)) {
			return ArrayList.class;
		} else if (destination.equals(Collection.class)) {
			return source;
		} else {
			return destination;
		}
	}

	@SuppressWarnings("unchecked")
	private static Class resolveListType(Class source, Class destination) {
		if (source.equals(List.class) && destination.equals(List.class)) {
			return ArrayList.class;
		} else if (destination.equals(List.class)) {
			return source;
		} else {
			return destination;
		}
	}

	@SuppressWarnings("unchecked")
	private static Class resolveMapType(Class source, Class destination) {
		if (source.equals(Map.class) && destination.equals(Map.class)) {
			return HashMap.class;
		} else if (destination.equals(Map.class)) {
			return source;
		} else {
			return destination;
		}
	}

	@SuppressWarnings({"unused", "unchecked"})
	private static Class resolveSetType(Class source, Class destination) {
		if (source.equals(Set.class) && destination.equals(Set.class)) {
			return HashSet.class;
		} else if (destination.equals(Set.class)) {
			return source;
		} else {
			return destination;
		}
	}

	private static String trimPackage(String packageString) {
		return packageString.substring(0, packageString.lastIndexOf("."));
	}

}
