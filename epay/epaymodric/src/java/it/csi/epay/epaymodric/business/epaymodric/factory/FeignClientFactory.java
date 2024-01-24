/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

import it.csi.epay.epaymodric.business.epaymodric.factory.model.FeignClient;
import it.csi.epay.epaymodric.business.epaymodric.factory.model.FeignClientContext;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RestException;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *
 */

public abstract class FeignClientFactory {

    private static final String URI_PROPERTIES_PLACEHOLDER = "\\$\\{([^\\}]+)\\}";

    private static final String URI_PARAMS_PLACEHOLDER = "\\{([^\\}]+)\\}";

    private static final String QUERY_ENCODING = "UTF-8";

    private static final Logger logger = LogManager.getLogger ( FeignClientFactory.class );

    public static <T> T buildClient ( Class<T> clazz, FeignClientContext context ) {

    	logger.debug ( "building client for interface " + clazz );

        Enhancer enhancer = new Enhancer ();
        enhancer.setSuperclass ( clazz );

        enhancer.setCallback ( new MethodInterceptor () {

            @Override
            public Object intercept ( Object obj, Method method, Object [] args, MethodProxy proxy ) throws Throwable {
                try {
                    return FeignClientFactory.intercept ( clazz, obj, method, args, proxy, context );
                } catch (RestClientException e ) {
                    throw new RestException ( e.getMessage (), e );
                } catch ( RuntimeException e ) {
                    throw e;
                } catch ( Exception e ) {
                    throw new RestException ( "Exception during feign client operation proxy: " + e.getMessage (), e );
                } catch ( Throwable e ) {
                    throw e;
                }
            }
        } );

        @SuppressWarnings ( "unchecked" )
        T proxy = (T) enhancer.create ();
        return proxy;
    }

    private static String resolveProperties ( String raw, FeignClientContext context ) {
        if ( raw == null || !raw.contains ( "$" + "" ) ) {
            return raw;
        }

        final Pattern uriParamsPattern = Pattern.compile ( URI_PROPERTIES_PLACEHOLDER );
        Matcher uriParamsMatcher = uriParamsPattern.matcher ( raw );
        while ( uriParamsMatcher.find () ) {
            for ( int i = 0; i < uriParamsMatcher.groupCount (); i++ ) {
                String rawMatch = uriParamsMatcher.group ( i );
                String match = rawMatch.replace ( "$" + "{", "" ).replace ( "}", "" ).trim ();

                logger.trace ( "resolving URI placeholder " + match );

                String value = context.getProvider ().getConfigurazioneService ().getConfigFromDatabase( match );
                if ( value.isEmpty () ) {
                    throw new RuntimeException ( "Missing property " + match + " for placeholder: " + raw );
                }

                raw = raw.replace ( rawMatch, value );
            }
        }

        return raw;
    }

    private static Object intercept ( Class<?> clazz, Object obj, Method method, Object [] args, MethodProxy proxy, FeignClientContext context )
                    throws Throwable {
        if ( method.getDeclaringClass () == Object.class ) {
            return proxy.invokeSuper ( obj, args );
        }

        logger.trace ( "executing feign client method " + method.getName () );

        FeignClient annotationOnClass = clazz.getAnnotation ( FeignClient.class );
        Path pathAnnotationOnClass = clazz.getAnnotation ( Path.class );
        Path annotationOnMethod = method.getAnnotation ( Path.class );

        String rawUrl = "";
        if ( pathAnnotationOnClass != null && annotationOnClass != null ) {
            if ( StringUtils.hasText ( pathAnnotationOnClass.value () ) && StringUtils.hasText ( annotationOnClass.value () ) ) {
                throw new RuntimeException ( "Duplicate root path on class " + clazz.getName () );
            }
        }

        if ( pathAnnotationOnClass != null ) {
            rawUrl += resolveProperties ( pathAnnotationOnClass.value (), context );
            if ( annotationOnMethod != null ) {
                rawUrl += "/";
            }
        } else if ( annotationOnClass != null && StringUtils.hasText(  annotationOnClass.value () ) ) {
            rawUrl += resolveProperties ( annotationOnClass.value (), context );
            if ( annotationOnMethod != null ) {
                rawUrl += "/";
            }
        }
        if ( annotationOnMethod != null ) {
            rawUrl += resolveProperties ( annotationOnMethod.value (), context );
        }

        Map<String, String> pathParams = new HashMap<> ();
        Map<String, String> queryParams = new HashMap<> ();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<> ();
        Object payload = null;

        int parameterIndex = 0;
        for ( Annotation [] annotationsOnArgument: method.getParameterAnnotations () ) {
            if ( annotationsOnArgument.length == 0 ) {
                // parametro senza annotations
                if ( payload != null ) {
                    throw new RuntimeException ( "Multiple payload methods for " + method.getName () );
                }

                payload = args [parameterIndex];

            } else {
                // parametro con annotations
                for ( Annotation annotationOnArgument: annotationsOnArgument ) {
                    if ( annotationOnArgument instanceof PathParam ) {
                        PathParam typedAnnotation = (PathParam) annotationOnArgument;
                        Object value = args [parameterIndex];
                        if ( value == null || ( value instanceof String && ( (String) value ).trim ().isEmpty () ) ) {
                            throw new RuntimeException ( "Empty PathParam for given placeholder: " + typedAnnotation.value () );
                        }
                        logger.trace (  "resolved URI placeholder " + typedAnnotation.value () + " to " + value.toString () );
                        pathParams.put ( typedAnnotation.value (), value.toString () );

                    } else if ( annotationOnArgument instanceof QueryParam ) {
                        QueryParam typedAnnotation = (QueryParam) annotationOnArgument;
                        Object value = args [parameterIndex];
                        if ( value == null || ( value instanceof String && ( (String) value ).trim ().isEmpty () ) ) {
                            continue;
                        }

                        queryParams.put ( typedAnnotation.value (), value.toString () );

                    } else if ( annotationOnArgument instanceof HeaderParam ) {
                        HeaderParam typedAnnotation = (HeaderParam) annotationOnArgument;
                        Object value = args [parameterIndex];
                        if ( value == null || ( value instanceof String && ( (String) value ).trim ().isEmpty () ) ) {
                            continue;
                        }

                        headers.add ( typedAnnotation.value (), resolveProperties ( value.toString (), context ) );
                    }
                }
            }
            parameterIndex++;
        }

        final Pattern uriParamsPattern = Pattern.compile ( URI_PARAMS_PLACEHOLDER );
        Matcher uriParamsMatcher = uriParamsPattern.matcher ( rawUrl );
        while ( uriParamsMatcher.find () ) {
            for ( int i = 0; i < uriParamsMatcher.groupCount (); i++ ) {
                String rawMatch = uriParamsMatcher.group ( i );
                String match = rawMatch.replace ( "{", "" ).replace ( "}", "" ).trim ();

                logger.trace (  "resolving URI placeholder " + match );

                if ( !pathParams.containsKey ( match ) ) {
                    throw new RuntimeException ( "No PathParam for given placeholder: " + match );
                } else {
                    rawUrl = rawUrl.replace ( rawMatch, pathParams.get ( match ) );
                }
            }
        }

        URI uri = new URI ( rawUrl ).normalize ();

        if ( !queryParams.isEmpty () ) {
            for ( String key: queryParams.keySet () ) {

                // Modify the query: append your new parameter
                StringBuilder sb = new StringBuilder ( uri.getQuery () == null ? "" : uri.getQuery () );
                if ( sb.length () > 0 ) {
                    sb.append ( '&' );
                }
                sb.append ( URLEncoder.encode ( key, QUERY_ENCODING ) );
                sb.append ( '=' );
                sb.append ( URLEncoder.encode ( queryParams.get ( key ), QUERY_ENCODING ) );

                // Build the new url with the modified query:
                uri = new URI ( uri.getScheme (), uri.getAuthority (), uri.getPath (), sb.toString (), uri.getFragment () );
            }
        }

        logger.trace ( "parsed uri " + uri.toString () );

        HttpEntity<Object> requestEntity = new HttpEntity<> ( payload, headers );
        HttpMethod httpMethod;

        if ( method.getAnnotation ( GET.class ) != null ) {
            httpMethod = HttpMethod.GET;

        } else if ( method.getAnnotation ( POST.class ) != null ) {
            httpMethod = HttpMethod.POST;

        } else if ( method.getAnnotation ( PUT.class ) != null ) {
            httpMethod = HttpMethod.PUT;

        } else if ( method.getAnnotation ( DELETE.class ) != null ) {
            httpMethod = HttpMethod.DELETE;

        } else if ( method.getAnnotation ( OPTIONS.class ) != null ) {
            httpMethod = HttpMethod.OPTIONS;

        } else {
            throw new RuntimeException ( "Unrecognized HTTP method on " + method.getName () );
        }

        boolean returnsVoid = method.getReturnType ().equals ( Void.TYPE );

        Class<?> returnType = returnsVoid ? Object.class : method.getReturnType ();

        ResponseEntity<?> result = context.getRestTemplate ().exchange ( uri, httpMethod, requestEntity, returnType );

        if ( !returnsVoid ) {
            return result.getBody ();
        } else {
            return null;
        }
    }
}
