/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import it.csi.mdp.mdppagopaapi.dto.common.FileContentDTO;


/**
 *
 */
public abstract class FilesUtils {

    protected static final Logger logger = LoggerFactory.getLogger ( FilesUtils.class );

    /**
     * Metodo per ottenere il nome del file che viene inviato
     *
     * @param multipart il file inviato da cui ottenere il nome
     * @return una stringa contenente il mime type individuato oppure il default "application/octet-stream"
     */
    public static String getMimetype ( byte [] data ) {
        String mimeType = null;
        try {
            InputStream is = new BufferedInputStream ( new ByteArrayInputStream ( data ) );
            mimeType = URLConnection.guessContentTypeFromStream ( is );
            if ( mimeType == null ) {
                mimeType = "application/octet-stream";
            }
        } catch ( IOException e ) {
            logger.error ( "IOException: " + e );
        }
        return mimeType;
    }

    /**
     * Metodo per ottenere il nome del file che viene inviato
     *
     * @param multipart il file inviato da cui ottenere il nome
     * @return una stringa contenente il nome del file del sistema con anche l'estensione
     */
    public static String getFileName ( MultipartFormDataInput multipart ) {

        Map<String, List<InputPart>> uploadForm = multipart.getFormDataMap ();

        List<InputPart> inputPartsFilename = uploadForm.get ( "filename" );
        if ( inputPartsFilename != null && !inputPartsFilename.isEmpty () ) {

            String filenameFromParts;
            try {
                filenameFromParts = inputPartsFilename.get ( 0 ).getBodyAsString ();
            } catch ( IOException e ) {
                throw new RuntimeException ( e );
            }
            if ( !StringUtils.isBlank ( filenameFromParts ) ) {
                return filenameFromParts;
            }
        }

        List<InputPart> inputParts = uploadForm.get ( "file" );

        for ( InputPart inputPart: inputParts ) {
            MultivaluedMap<String, String> header = inputPart.getHeaders ();
            String [] contentDisposition = header.getFirst ( "Content-Disposition" ).split ( ";" );
            for ( String filename: contentDisposition ) {
                if ( ( filename.trim ().startsWith ( "filename" ) ) ) {
                    String [] name = filename.split ( "=" );
                    return name [1].trim ().replaceAll ( "\"", "" );
                }
            }
            return "";
        }
        return "";
    }

    public static String getPart ( MultipartFormDataInput multipart, String partName ) {

        Map<String, List<InputPart>> uploadForm = multipart.getFormDataMap ();

        List<InputPart> foundPart = uploadForm.get ( partName );
        if ( foundPart != null && !foundPart.isEmpty () ) {

            String valueFromParts;
            try {
                valueFromParts = foundPart.get ( 0 ).getBodyAsString ();
            } catch ( IOException e ) {
                throw new RuntimeException ( e );
            }
            if ( !StringUtils.isBlank ( valueFromParts ) ) {
                return valueFromParts;
            }
        }

        return null;
    }

    public static String requirePart ( MultipartFormDataInput multipart, String partName ) {
        String partValue = getPart ( multipart, partName );
        if ( StringUtils.isBlank ( partValue ) ) {
            throw new RuntimeException ( "Part " + partName + " is required but was not found" );
        } else {
            return partValue;
        }
    }

    public static byte [] requireFileContent ( MultipartFormDataInput multipart ) throws IOException {

        InputStream inputStream = null;

        if ( multipart.getParts ().size () == 1 ) {
            InputPart filePart = multipart.getParts ().iterator ().next ();
            inputStream = filePart.getBody ( InputStream.class, null );
        } else {
            inputStream = multipart.getFormDataPart ( "file", InputStream.class, null );
        }

        if ( inputStream == null ) {
            throw new IllegalArgumentException ( "Can't find a valid 'file' part in the multipart request" );
        }

        byte [] contentData = IOUtils.toByteArray ( inputStream );

        return contentData;
    }

    public static Response toResponse ( FileContentDTO input ) {
        if ( input == null ) {
            throw new NotFoundException ( "File content not found" );
        }

        if ( StringUtils.isBlank ( input.getFileName () ) ) {
            throw new InvalidParameterException ();
        }

        ResponseBuilder response = Response.status ( HttpStatus.OK.value () )
            .entity ( input.getContent () );

        response.header ( "Content-Disposition", "attachment; filename=" + input.getFileNameSafe () );

        if ( StringUtils.isNotBlank ( input.getMimeType () ) ) {
            response.header ( "Content-Type", input.getMimeType () );
        } else {
            response.header ( "Content-Type", "application/octet-stream" );
        }

        return response.build ();
    }

}
