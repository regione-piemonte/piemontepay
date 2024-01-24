/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.util.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MockDatabase implements InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(MockDatabase.class);

	public static class MockDatabaseContent implements java.io.Serializable {

		private static final long serialVersionUID = 1L;
		
		private Map<String, List<Object>> tables;
		private Long version;

		public MockDatabaseContent() {
			// NOP
		}

		public Long getVersion() {
			return version;
		}

		public void setVersion(Long version) {
			this.version = version;
		}

		public Map<String, List<Object>> getTables() {
			return tables;
		}

		public void setTables(Map<String, List<Object>> tables) {
			this.tables = tables;
		}

	}

	private MockDatabaseContent defaultFactoryContent;
	private MockDatabaseContent content;
	private ObjectMapper mapper;
	private String fileLocation;
	private String classpathRelativeDataFileLocation;

	public MockDatabase() {
		this(System.getProperty("java.io.tmpdir") + "/epaypacatalogweb_mock_db.db");
	}

	public MockDatabase(String fileLocation) {
		mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		classpathRelativeDataFileLocation = "mock_db.json";
		this.fileLocation = fileLocation;

		logger.debug("initializing in-memory-database on file " + fileLocation
				+ " with factory default data on resource " + classpathRelativeDataFileLocation);
	}

	private MockDatabaseContent getContentFromFile(String file) throws IOException, ClassNotFoundException {
		logger.debug("retrieving content from file " + file);

		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		MockDatabaseContent content = (MockDatabaseContent) in.readObject();
		in.close();
		fileIn.close();

		if (content.getVersion() == null) {
			throw new RuntimeException("version can't be NULL");
		}

		logger.debug("retrieved content from file " + file);
		return content;
	}

	private MockDatabaseContent getContentFromClasspathResource(String location) throws IOException, ClassNotFoundException {
		logger.debug("retrieving content from classpath resource at location " + location);

		Resource resource = new ClassPathResource(location);
		byte[] encoded = IOUtils.toByteArray(resource.getInputStream());
        String parsedStr = new String(encoded, "UTF-8");
		MockDatabaseContent content = mapper.readValue(parsedStr, MockDatabaseContent.class);
        
		if (content.getVersion() == null) {
			throw new RuntimeException("version can't be NULL");
		}
		
		MockDatabaseContent builtContent = new MockDatabaseContent();
		builtContent.setVersion(content.getVersion());
		builtContent.setTables(new HashMap<>());
		
		for (String key : content.getTables().keySet()) {
			Class<?> representedClass = Class.forName(key);
			List<Object> builtTable = new ArrayList<>();
			builtContent.getTables().put(key, builtTable);
			for (Object v : content.getTables().get(key)) {
				String serialized = mapper.writeValueAsString(v);
				Object parsed = mapper.readValue(serialized, representedClass);
				builtTable.add(parsed);
			}
		}

		logger.debug("retrieved content from classpath resource at location " + location);
		return builtContent;
	}

	public void loadFromFile() throws IOException, ClassNotFoundException {

		defaultFactoryContent = getContentFromClasspathResource(classpathRelativeDataFileLocation);

		File f = new File(fileLocation);
		if (f.exists() && !f.isDirectory()) {

			content = getContentFromFile(f.getAbsolutePath());
			logger.debug("restored data from disk. now comparing versions");

			Long fileVersion = content.getVersion();
			Long defaultVersion = defaultFactoryContent.getVersion();
			logger.debug("version of data stored on disk is " + fileVersion);
			logger.debug("version of data configured as default is " + defaultVersion);

			if (defaultVersion > fileVersion) {
				logger.debug("default data version requires overriding of stored data");
				content = defaultFactoryContent;
			} else if (fileVersion > defaultVersion) {
				logger.error("stored version is over factory version. WTF ???");
				logger.debug("restoring factory content");
				content = defaultFactoryContent;
			} else {
				logger.debug("data on disk is newer than default. Keeping data from disk");
			}
		} else {
			logger.warn("no stored data found on disk. Restoring from factory default");
			content = defaultFactoryContent;
		}
	}

	public void persist() {
		logger.debug("persisting data on file " + fileLocation);

		try {
			FileOutputStream fileOut = new FileOutputStream(fileLocation);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(content);
			out.close();
			fileOut.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public MockDatabaseContent getContent() {
		return content;
	}

	@Override
	public void destroy() throws Exception {
		try {
			this.persist();
		} catch (Exception e) {
			logger.error("could not save mock data on context shutdown", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			this.loadFromFile();
		} catch (Exception e) {
			logger.error("could not load mock data on context startup", e);
			content = defaultFactoryContent;
		}
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> list(Class<?> T) {
		List<T> table;
		String key = T.getName();
		if (!content.tables.containsKey(key)) {
			table = new ArrayList<>();
			content.tables.put(key, (List<Object>) table);
		} else {
			table = (List<T>) content.tables.get(key);
		}

		return table;
	}

	public <T> List<T> findAll(Class<?> T) {
		logger.debug("executing: SELECT * FROM " + T.getSimpleName());
		List<T> table = list(T);
		logger.debug("execution: found " + table.size() + " records");
		return table;
	}

	public <T> T findOne(Class<?> T, String propName, Object propValue) {
		List<T> all = find(T, propName, propValue);
		if (all.size() == 1) {
			return all.get(0);
		} else if (all.size() < 1) {
			return null;
		} else {
			throw new RuntimeException("findOne returned " + all.size() + " results");
		}
	}
	
	public <T> List<T> find(Class<?> T, Object... filterParams) {
		if (filterParams.length % 2 != 0) {
			throw new InvalidParameterException();
		}

		logger.debug("executing: SELECT * FROM " + T.getSimpleName() + " WITH FILTER PARAMS");
		
		List<T> table = list(T);
		List<T> results = table;
		
		if (filterParams != null && filterParams.length > 0) {
			for (int i = 0; i < filterParams.length; i += 2) {
				String propName = (String)filterParams[i];
				Object propValue = filterParams[i + 1];
				logger.debug("executing: FILTERING WHERE " + propName + " = " + propValue);
				results = filterByProperty(results, propName, propValue);
			}
		}
		
		logger.debug("execution: found " + results.size() + " records");
		return results;
	}

	public <T> int update(Object newObjectRaw) {
		return update(newObjectRaw, "id");
	}

	public <T> int update(Object newObjectRaw, String propName) {
		
		Class<?> T = newObjectRaw.getClass();
		Field field;
		Object propValue = null;
		@SuppressWarnings("unchecked")
		T newObject = (T)newObjectRaw;
		
		try {
			field = newObject.getClass().getDeclaredField(propName);
			field.setAccessible(true);
			propValue = field.get(newObject);
		} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
			return 0;
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("executing: UPDATE " + T.getSimpleName() + " WHERE " + propName + " = " + propValue + " SET " + represent(newObject));
		}
		
		List<T> table = list(T);
		if (table.size() < 1) {
            return 0;
        }
		int count = 0;
		for (int i = 0; i < table.size(); i ++) {
			T o = table.get(i);
			try {
				field = o.getClass().getDeclaredField(propName);
				field.setAccessible(true);
				Object value = field.get(o);
				if (value == null && propValue == null) {
					table.set(i, newObject);
					count ++;
				}
				if (value != null && value.equals(propValue)) {
					table.set(i, newObject);
					count ++;
				}
			} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		return count;
	}

	public <T> int delete(Object obj) {
		return delete(obj, "id");
	}

	public <T> int delete(Object obj, String propName) {
		Field field;
		Object propValue = null;
		
		try {
			field = obj.getClass().getDeclaredField(propName);
			field.setAccessible(true);
			propValue = field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
			return 0;
		}
		
		return delete(obj.getClass(), propName, propValue);
	}
	
	public <T> int delete(Class<?> T, String propName, Object propValue) {

		logger.debug("executing: DELETE FROM " + T.getSimpleName() + " WHERE " + propName + " = " + propValue);
		
		List<T> table = list(T);
		if (table.size() < 1) {
            return 0;
        }
		int count = 0;
		for (int i = table.size() - 1; i >= 0; i --) {
			T o = table.get(i);
			try {
				Field field = o.getClass().getDeclaredField(propName);
				field.setAccessible(true);
				Object value = field.get(o);
				if (value == null && propValue == null) {
					table.remove(i);
					count ++;
				}
				if (value != null && value.equals(propValue)) {
					table.remove(i);
					count ++;
				}
			} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		return count;
	}

	private String represent(Object o) {
		if (o == null) {
			return "null";
		}
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			return o.toString();
		}
	}
	
	public static <T> List<T> filterByProperty(List<T> list, String propName, Object propValue) {
		if (propName == null || propName.isEmpty()) {
			return list;
		}

		List<T> output = new ArrayList<>();

		for (T o : list) {
			Field field;
			try {
				field = o.getClass().getDeclaredField(propName);
				field.setAccessible(true);
				Object value = field.get(o);
				if (value == null && propValue == null) {
					output.add(o);
				} else if (value != null && propValue == null) {
					continue;
				} else if (value == null && propValue != null) {
					continue;
				} else if (propValue instanceof String && value instanceof String) {
					if (((String)value).toUpperCase().contains(((String)propValue).toUpperCase())) {
						output.add(o);
					}
				} else {
					if (value.equals(propValue)) {
						output.add(o);
					}
				}				
			} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
				e.printStackTrace();
			}    
		}
		
		return output;
	}
}
