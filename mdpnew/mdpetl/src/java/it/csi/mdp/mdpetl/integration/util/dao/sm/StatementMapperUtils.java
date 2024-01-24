/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.sm;

import it.csi.mdp.mdpetl.integration.util.dao.StatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public abstract class StatementMapperUtils implements StatementMapper {
	LogUtil log = new LogUtil(StatementMapperUtils.class);

	private int n = 1;
	
	
	public void setNull(PreparedStatement stmt) throws SQLException {
		stmt.setNull(n, java.sql.Types.NULL);
		log.debug("setNull", n+". null");
		n=n+1;
	}
	
	public void setBoolean(PreparedStatement stmt, Boolean o) throws SQLException {
		stmt.setBoolean(n, o);
		log.debug("setNull", n+". null");
		n=n+1;
	}

	public void setInt(PreparedStatement stmt, Integer value) throws SQLException {
		setInt(stmt, value, false);
	}

	public void setInt(PreparedStatement stmt, Integer value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.INTEGER);
		}
		log.debug("setInt", n+". "+value);
		n=n+1;
	}
	
	public void setLong(PreparedStatement stmt, Long value) throws SQLException {
	    setLong(stmt, value, false);
	}
	
	public void setLong(PreparedStatement stmt, Long value, boolean obbligatorio) throws SQLException {
	    if (value != null) {
	        stmt.setLong ( n, value );
	    } else {
	        if (obbligatorio)
	            throw new SQLException("Paramentro obbligatorio mancante");
	        else
	            stmt.setNull(n, java.sql.Types.BIGINT);
	    }
	    log.debug("setLong", n+". "+value);
	    n=n+1;
	}
	
	public void setInt0(PreparedStatement stmt, Integer value) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
		} else {
			stmt.setInt(n, 0);
		}
		log.debug("setInt0", n+". "+value);
		n=n+1;
	}
	
	public void setInt(PreparedStatement stmt, int num) throws SQLException {
		stmt.setInt(n, num);
		log.debug("setInt", n+". "+num);
		n=n+1;
	}
	
	public void setIntIfPresent(PreparedStatement stmt, Integer value) throws SQLException {
		if (value != null) {
			stmt.setInt(n, value.intValue());
			log.debug("setIntIfPresent", n+". "+value);
			n=n+1;
		}
		
	}
	
	

	public void setDouble(PreparedStatement stmt, Double value) throws SQLException {
		setDouble(stmt, value, false);
	}

	public void setDouble(PreparedStatement stmt, Double value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.DOUBLE);
		}
		log.debug("setDouble", n+". "+value);
		n=n+1;
	}
	
	
	public void setBigDecimal(PreparedStatement stmt, BigDecimal value) throws SQLException {
		setBigDecimal(stmt, value, false);
	}

	public void setBigDecimal(PreparedStatement stmt, BigDecimal value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setBigDecimal(n, value);
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.DOUBLE);
		}
		log.debug("setBigDecimal", n+". "+value);
		n=n+1;
	}
	
	
	
	
	public void setDouble0(PreparedStatement stmt, Double value) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
		} else {
			stmt.setDouble(n, 0);
		}
		log.debug("setDouble0", n+". "+value);
		n=n+1;
	}
	
	public void setDoubleIfPresent(PreparedStatement stmt, Double value) throws SQLException {
		if (value != null) {
			stmt.setDouble(n, value.doubleValue());
			log.debug("setDoubleIfPresent", n+". "+value);
			n=n+1;
		}
		
	}

	public void setStringUpperCase(PreparedStatement stmt, String value) throws SQLException {
		setStringUpperCase(stmt, value, false);
	}

	public void setStringUpperCase(PreparedStatement stmt, String value, boolean obbligatorio) throws SQLException {
		try{
		if (value != null) {
			stmt.setString(n, value.toUpperCase());
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.VARCHAR);
		}
		log.debug("setStringUpperCase", n+". "+value);
		n=n+1;
		}catch  (Exception e){
			log.debug("setStringUpperCase", "n vale: "+n);
			log.debug("setStringUpperCase ",e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setBinaryStream(PreparedStatement stmt, ByteArrayInputStream value,int length) throws SQLException {
		setBinaryStream(stmt, value,length, false);
	}
	
	private void setBinaryStream(PreparedStatement stmt,
			ByteArrayInputStream value, int length, boolean obbligatorio) throws SQLException {
		
		if (value != null) {
			stmt.setBinaryStream(n, value,length);
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.BLOB);
		}
		log.debug("setString", n+". "+value);
		n=n+1;
	}
	
	
	
	public void setString(PreparedStatement stmt, String value) throws SQLException {
		setString(stmt, value, false);
	}

	public void setString(PreparedStatement stmt, String value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setString(n, value);
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.VARCHAR);
		}
		log.debug("setString", n+". "+value);
		n=n+1;
	}
	
	public void setStringIfPresent(PreparedStatement stmt, String value) throws SQLException {
		if (value != null) {
			stmt.setString(n, value);
			log.debug("setStringIfPresent", n+". "+value);
			n=n+1;
		}
		
	}
	
	/*
	public void setClob(PreparedStatement stmt, Clob value) throws SQLException {
		setClob(stmt, value, false);
	}

	public void setClob(PreparedStatement stmt, Clob value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setClob(n, value);
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.CLOB);
		}
		log.debug("setClob", n+". "+value);
		n=n+1;
	}
	*/
	

	public void setDate(PreparedStatement stmt, java.util.Date value) throws SQLException {
		setDate(stmt, value, false);
	}

	public void setDate(PreparedStatement stmt, java.util.Date value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setDate(n, new java.sql.Date(value.getTime()));
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.DATE);
		}
		log.debug("setDate", n+". "+value);
		n=n+1;
	}
	
	public void setDateIfPresent(PreparedStatement stmt, java.util.Date value) throws SQLException {
		if (value != null) {
			stmt.setDate(n, new java.sql.Date(value.getTime()));
			log.debug("setDateIfPresent", n+". "+value);
			n=n+1;
		}
		
	}

	public void setTimestamp(PreparedStatement stmt, java.util.Date value) throws SQLException {
		setTimestamp(stmt, value, false);
	}

	public void setTimestamp(PreparedStatement stmt, java.util.Date value, boolean obbligatorio) throws SQLException {
		if (value != null) {
			stmt.setTimestamp(n, new java.sql.Timestamp(value.getTime()));
		} else {
			if (obbligatorio)
				throw new SQLException("Paramentro obbligatorio mancante");
			else
				stmt.setNull(n, java.sql.Types.TIMESTAMP);
		}
		log.debug("setTimestamp", n+". "+value);
		n=n+1;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}

