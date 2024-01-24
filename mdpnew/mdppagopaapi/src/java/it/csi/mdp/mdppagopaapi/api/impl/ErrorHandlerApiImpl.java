/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.api.impl;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.MethodNotAllowedException;
import org.jboss.resteasy.spi.NotAcceptableException;
import org.jboss.resteasy.spi.NotFoundException;
import org.jboss.resteasy.spi.ReaderException;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.jboss.resteasy.spi.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import it.csi.mdp.mdppagopaapi.api.interceptors.AuthenticationInterceptor;
import it.csi.mdp.mdppagopaapi.api.interceptors.RequestLoggingInterceptor;
import it.csi.mdp.mdppagopaapi.api.util.SpringApplicationContextHelper;
import it.csi.mdp.mdppagopaapi.api.util.SpringSupportedResource;
import it.csi.mdp.mdppagopaapi.business.MessageService;
import it.csi.mdp.mdppagopaapi.business.impl.ConfigurazioneServiceImpl;
import it.csi.mdp.mdppagopaapi.dto.common.ErrorMessageDTO;
import it.csi.mdp.mdppagopaapi.dto.common.MessaggioDTO;
import it.csi.mdp.mdppagopaapi.dto.exception.ConflictException;
import it.csi.mdp.mdppagopaapi.dto.exception.ManagedException;
import it.csi.mdp.mdppagopaapi.dto.security.ClientInfo;
import it.csi.mdp.mdppagopaapi.util.Messages;
import it.csi.mdp.mdppagopaapi.util.SecurityUtils;


@SpringSupportedResource
@Provider
@Produces ( "application/json" )
public class ErrorHandlerApiImpl implements ExceptionMapper<Exception> {

    //    private static final String PLACEHOLDER_NOMINATIVO = "${NOMINATIVO}";

    public static final String REQUEST_ATTRIBUTE_RESULT_EXCEPTION = "reqAttrResultException";

    private static final Logger logger = LoggerFactory.getLogger ( ErrorHandlerApiImpl.class );

    private static final ObjectMapper mapper = new ObjectMapper ();

    private static volatile MessageService messageService = null;

    private static MessageService getMessageService () {
        if ( messageService == null ) {
            synchronized ( ErrorHandlerApiImpl.class ) {
                if ( messageService == null ) {
//                    messageService = (MessageService) SpringApplicationContextHelper.getBean ( MessageServiceImpl.class );
                }
            }
        }
        return messageService;
    }

    @Produces ( "application/json" )
    @Override
    public Response toResponse ( Exception e ) {
        HttpServletRequest request = SecurityUtils.getCurrentRequest ();
        if ( request != null ) {
            request.setAttribute ( REQUEST_ATTRIBUTE_RESULT_EXCEPTION, e );
        }

        Response response = convertExceptionToResponse ( e );
        RequestLoggingInterceptor.postProcessResponseForError ( response );
        return response;
    }

    private Response convertExceptionToResponse ( Exception e ) {
        logger.error ( "handling exception: " + e, e );

        if ( e instanceof ManagedException ) {
            return handleManagedException ( (ManagedException) e );

        } else if ( e instanceof MethodConstraintViolationException ) {
            return handleMethodConstraintViolationException ( (MethodConstraintViolationException) e );

        } else if ( e instanceof BadRequestException ) {
            return handleBadRequestException ( (BadRequestException) e );

        } else if ( e instanceof UnauthorizedException ) {
            return handleUnauthorizedException ( (UnauthorizedException) e );

        } else if ( e instanceof NotFoundException ) {
            return handleNotFoundException ( (NotFoundException) e );

        } else if ( e instanceof ConflictException ) {
            return handleConflictException ( (ConflictException) e );

        } else if ( e instanceof InternalServerErrorException ) {
            return handleInternalServerErrorException ( (InternalServerErrorException) e );

        } else if ( e instanceof MethodNotAllowedException ) {
            return handleMethodNotAllowedException ( (MethodNotAllowedException) e );

        } else if ( e instanceof NotAcceptableException ) {
            return handleNotAcceptableException ( (NotAcceptableException) e );

        } else if ( e instanceof ReaderException ) {
            return handleReaderException ( (ReaderException) e );

        } else if ( e instanceof WriterException ) {
            return handleWriterException ( (WriterException) e );

        } else {
            return handleOtherException ( e );
        }
    }

    private Response handleManagedException ( ManagedException e ) {
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( e.getStatus () )
            .withCodice ( e.getCodice () )
            .withMessaggio ( e.getMessage () )
            .build () );
    }

    private Response handleMethodConstraintViolationException ( MethodConstraintViolationException e ) {
        String messageStr = "dati non validi.";

        Set<MethodConstraintViolation<?>> violations = e.getConstraintViolations ();
        if ( !violations.isEmpty () ) {

            StringBuilder message = new StringBuilder ( "" );

            for ( ConstraintViolation<?> constraintViolation: violations ) {
                if ( !StringUtils.isEmpty ( constraintViolation.getMessage () ) ) {
                    message.append (
                        String.format ( "%s", constraintViolation.getMessage () ) );
                } else {
                    message.append (
                        String.format ( "%s value '%s'", constraintViolation.getPropertyPath (), constraintViolation.getInvalidValue (),
                            constraintViolation.getMessage () ) );
                }

                message.append ( "; " );
            }
            messageStr = message.toString ();
        }
        MessaggioDTO messageDTO = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_CONSTRAINT_VIOLATION_EXCEPTION_ARGS, messageStr );

        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.BAD_REQUEST.value () )
            .withCodice ( messageDTO.getCodice () )
            .withMessaggio ( costruisciMessaggio ( messageDTO.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleBadRequestException ( BadRequestException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_BAD_REQUEST );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.BAD_REQUEST.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleUnauthorizedException ( UnauthorizedException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_UNAUTHORIZED );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.UNAUTHORIZED.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleConflictException ( ConflictException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_CONFLICT );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.CONFLICT.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleInternalServerErrorException ( InternalServerErrorException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_INTERNAL_SERVER_ERROR );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleMethodNotAllowedException ( MethodNotAllowedException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_METHOD_NOT_ALLOWED );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.METHOD_NOT_ALLOWED.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleNotAcceptableException ( NotAcceptableException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_NOT_ACCEPTABLE );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.NOT_ACCEPTABLE.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleNotFoundException ( NotFoundException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_NOT_FOUND );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.NOT_FOUND.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleReaderException ( ReaderException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_READER_ERROR );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.BAD_REQUEST.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleWriterException ( WriterException e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_WRITING_ERROR );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response handleOtherException ( Exception e ) {
        MessaggioDTO message = getMessageService ().getMessaggio ( Messages.HTTP_ERROR_UNEXPECTED );
        return buildResponse ( ErrorMessageDTO.builder ()
            .withErrore ( e )
            .withStatus ( HttpStatus.INTERNAL_SERVER_ERROR.value () )
            .withCodice ( message.getCodice () )
            .withMessaggio ( costruisciMessaggio ( message.getMessaggio (), e.getMessage () ) )
            .build () );
    }

    private Response buildResponse ( ErrorMessageDTO errorMessage ) {

        String messaggio = resolvePlaceholders ( errorMessage.getMessaggio () );

//        ClientInfo clientCorrente = ClientService.getInstance ().getClientCorrente ();

        String stacktrace = errorMessage.getErrore () != null && ConfigurazioneServiceImpl.isLocal ()
                        ? ExceptionUtils.getStackTrace ( errorMessage.getErrore () ) : null;

        return Response
            .status ( errorMessage.getStatus () )
            .entity ( ErrorMessageDTO.builder ()
                .withErrore ( null )
                .withDettagli ( stacktrace )
                .withStatus ( errorMessage.getStatus () )
                .withCodice ( errorMessage.getCodice () )
                .withMessaggio ( messaggio )
                .build () )
            .type ( "application/json" )
            //.header ( AuthenticationInterceptor.HEADER_CLIENT_ATTIVO, clientCorrente != null ? clientCorrente.getCodice () : null )
            .header ( AuthenticationInterceptor.HEADER_CLIENT_ATTIVO ,null)
            .build ();

    }

    private static String resolvePlaceholders ( String raw ) {

        if ( raw == null || !raw.contains ( "$MESSAGES" ) ) {
            return raw;
        }

        String finalMessage = raw;

        final String regex = "\\$MESSAGES\\.([^\\(\\s]+)(\\([^\\)]*\\))*";
        final Pattern pattern = Pattern.compile ( regex, Pattern.MULTILINE );
        final Matcher matcher = pattern.matcher ( raw );

        while ( matcher.find () ) {
            String placeholder, args, resolved;
            resolved = null;
            Object [] argsArray = new Object [] {};

            logger.debug ( "resolving message placeholder " + matcher.group ( 0 ) );

            if ( matcher.groupCount () >= 2 ) {
                // some args somewhere
                placeholder = matcher.group ( 1 );
                args = matcher.group ( 2 );
            } else {
                // no args never
                placeholder = matcher.group ( 1 );
                args = null;
            }

            Messages message = null;
            try {
                message = Messages.valueOf ( placeholder );
            } catch ( RuntimeException e ) {
                logger.warn ( "Error getting message" + placeholder, e );
                resolved = placeholder;
            }

            if ( message != null ) {

                if ( args != null ) {
                    args = args.trim ();
                    args = args.substring ( 1, args.length () - 1 ).trim ();
                    if ( !args.isEmpty () ) {
                        args = "[" + args + "]";

                        try {
                            argsArray = mapper.readValue ( args, Object [].class );
                        } catch ( IOException e ) {
                            throw new RuntimeException ( "Error parsing message placeholder" + matcher.group ( 0 ), e );
                        }
                    }
                }

                resolved = getMessageService ().getMessaggio ( message, argsArray ).getMessaggio ();

            } else {
                resolved = placeholder;
            }

            finalMessage = finalMessage.replace ( matcher.group ( 0 ), resolved );

        }

        return finalMessage;
    }

    private String costruisciMessaggio ( String... input ) {
        for ( String s: input ) {
            if ( !StringUtils.isEmpty ( s ) ) {
                return s;
            }
        }
        return null;

    }

}
