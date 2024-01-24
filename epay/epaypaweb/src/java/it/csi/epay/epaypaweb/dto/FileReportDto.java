/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

/** dto facade <-> business <-> persistence */
public class FileReportDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	protected byte [] contenutoFile;
	
	private Date dataInserimento;
	
	private Date dataModifica;
	
	private String nomeFile;
	
	private ReportBaseDto report;

    public FileReportDto () {
        super ();
    }

    @Generated ( "SparkTools" )
    private FileReportDto ( Builder builder ) {
        this.id = builder.id;
        this.contenutoFile = builder.contenutoFile;
        this.dataInserimento = builder.dataInserimento;
        this.dataModifica = builder.dataModifica;
        this.nomeFile = builder.nomeFile;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getContenutoFile() {
		return contenutoFile;
	}

	public void setContenutoFile(byte[] contenutoFile) {
		this.contenutoFile = contenutoFile;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Date dataModifica) {
		this.dataModifica = dataModifica;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	

    public ReportBaseDto getReport() {
		return report;
	}

	public void setReport(ReportBaseDto report) {
		this.report = report;
	}

	/**
     * Creates builder to build {@link FileReportDto}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link FileReportDto}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long id;

        private byte [] contenutoFile;

        private Date dataInserimento;

        private Date dataModifica;

        private String nomeFile;

        private Builder () {
        }

        public Builder withId ( Long id ) {
            this.id = id;
            return this;
        }

        public Builder withContenutoFile ( byte [] contenutoFile ) {
            this.contenutoFile = contenutoFile;
            return this;
        }

        public Builder withDataInserimento ( Date dataInserimento ) {
            this.dataInserimento = dataInserimento;
            return this;
        }

        public Builder withDataModifica ( Date dataModifica ) {
            this.dataModifica = dataModifica;
            return this;
        }

        public Builder withNomeFile ( String nomeFile ) {
            this.nomeFile = nomeFile;
            return this;
        }

        public FileReportDto build () {
            return new FileReportDto ( this );
        }
        
        
    }
	
}
