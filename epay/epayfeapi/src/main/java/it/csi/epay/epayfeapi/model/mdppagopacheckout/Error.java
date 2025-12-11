package it.csi.epay.epayfeapi.model.mdppagopacheckout;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@JsonTypeName ( "Error" )
@javax.annotation.Generated ( value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2023-06-28T11:44:33.921086400+02:00[Europe/Berlin]" )
public class Error {

	private @Valid String status;

	private @Valid CodeEnum code;

	private @Valid String detail;

	/**
	 *
	 **/
	public Error status ( String status ) {
		this.status = status;
		return this;
	}

	@JsonProperty ( "status" )
	@NotNull
	public String getStatus () {
		return status;
	}

	@JsonProperty ( "status" )
	public void setStatus ( String status ) {
		this.status = status;
	}

	/**
	 *
	 **/
	public Error code ( CodeEnum code ) {
		this.code = code;
		return this;
	}

	@JsonProperty ( "code" )
	@NotNull
	public CodeEnum getCode () {
		return code;
	}

	@JsonProperty ( "code" )
	public void setCode ( CodeEnum code ) {
		this.code = code;
	}

	/**
	 *
	 **/
	public Error detail ( String detail ) {
		this.detail = detail;
		return this;
	}

	@JsonProperty ( "detail" )
	public String getDetail () {
		return detail;
	}

	@JsonProperty ( "detail" )
	public void setDetail ( String detail ) {
		this.detail = detail;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass () != o.getClass () ) {
			return false;
		}
		var error = (Error) o;
		return Objects.equals ( this.status, error.status ) &&
						Objects.equals ( this.code, error.code ) &&
						Objects.equals ( this.detail, error.detail );
	}

	@Override
	public int hashCode () {
		return Objects.hash ( status, code, detail );
	}

	@Override
	public String toString () {
		var sb = new StringBuilder ();
		sb.append ( "class Error {\n" );
		sb.append ( "    status: " ).append ( toIndentedString ( status ) ).append ( "\n" );
		sb.append ( "    code: " ).append ( toIndentedString ( code ) ).append ( "\n" );
		sb.append ( "    detail: " ).append ( toIndentedString ( detail ) ).append ( "\n" );
		sb.append ( "}" );
		return sb.toString ();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString ( Object o ) {
		if ( o == null ) {
			return "null";
		}
		return o.toString ().replace ( "\n", "\n    " );
	}

	public enum CodeEnum {

		VALIDATION_ERROR ( String.valueOf ( "VALIDATION_ERROR" ) ), INTERNAL_ERROR ( String.valueOf ( "INTERNAL_ERROR" ) ), BUSINESS_ERROR (
						String.valueOf ( "BUSINESS_ERROR" ) ), AUTHORIZATION_ERROR ( String.valueOf ( "AUTHORIZATION_ERROR" ) );

		private final String value;

		CodeEnum ( String v ) {
			value = v;
		}

		/**
		 * Convert a String into String, as specified in the
		 * <a href="https://download.oracle.com/otndocs/jcp/jaxrs-2_0-fr-eval-spec/index.html">See JAX RS 2.0 Specification, section 3.2, p. 12</a>
		 */
		public static CodeEnum fromString ( String s ) {
			for ( CodeEnum b : CodeEnum.values () ) {
				// using Objects.toString() to be safe if value type non-object type
				// because types like 'int' etc. will be auto-boxed
				if ( java.util.Objects.toString ( b.value ).equals ( s ) ) {
					return b;
				}
			}
			throw new IllegalArgumentException ( "Unexpected string value '" + s + "'" );
		}

		@JsonCreator
		public static CodeEnum fromValue ( String value ) {
			for ( CodeEnum b : CodeEnum.values () ) {
				if ( b.value.equals ( value ) ) {
					return b;
				}
			}
			throw new IllegalArgumentException ( "Unexpected value '" + value + "'" );
		}

		public String value () {
			return value;
		}

		@Override
		@JsonValue
		public String toString () {
			return String.valueOf ( value );
		}
	}

}

