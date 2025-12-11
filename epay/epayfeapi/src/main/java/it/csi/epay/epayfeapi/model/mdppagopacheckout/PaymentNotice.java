package it.csi.epay.epayfeapi.model.mdppagopacheckout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;


@JsonTypeName ( "PaymentNotice" )
@javax.annotation.Generated ( value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
				date = "2023-06-28T10:23:33.078750500+02:00[Europe/Berlin]" )
public class PaymentNotice {

	private @Valid String description;

	private @Valid String applicationId;

	private @Valid String noticeNumber;

	private @Valid BigDecimal amount;

	/**
	 * Breve descrizione del pagamento
	 **/
	public PaymentNotice description ( String description ) {
		this.description = description;
		return this;
	}

	@JsonProperty ( "description" )
	@NotNull
	@Size ( max = 140 )
	public String getDescription () {
		return description;
	}

	@JsonProperty ( "description" )
	public void setDescription ( String description ) {
		this.description = description;
	}

	/**
	 * identificativo applicazione
	 **/
	public PaymentNotice applicationId ( String applicationId ) {
		this.applicationId = applicationId;
		return this;
	}

	@JsonProperty ( "applicationId" )
	@NotNull
	public String getApplicationId () {
		return applicationId;
	}

	@JsonProperty ( "applicationId" )
	public void setApplicationId ( String applicationId ) {
		this.applicationId = applicationId;
	}

	/**
	 * Numero avviso, with aux-digit
	 **/
	public PaymentNotice noticeNumber ( String noticeNumber ) {
		this.noticeNumber = noticeNumber;
		return this;
	}

	@JsonProperty ( "noticeNumber" )
	@NotNull
	@Size ( min = 17, max = 18 )
	public String getNoticeNumber () {
		return noticeNumber;
	}

	@JsonProperty ( "noticeNumber" )
	public void setNoticeNumber ( String noticeNumber ) {
		this.noticeNumber = noticeNumber;
	}

	/**
	 * Payment amount ( in euro cents ) of the payment notice minimum: 1 maximum: 99999999999
	 **/
	public PaymentNotice amount ( BigDecimal amount ) {
		this.amount = amount;
		return this;
	}

	@JsonProperty ( "amount" )
	@NotNull
	@DecimalMin ( "1" )
	@DecimalMax ( "99999999999" )
	public BigDecimal getAmount () {
		return amount;
	}

	@JsonProperty ( "amount" )
	public void setAmount ( BigDecimal amount ) {
		this.amount = amount;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass () != o.getClass () ) {
			return false;
		}
		var paymentNotice = (PaymentNotice) o;
		return Objects.equals ( this.description, paymentNotice.description ) &&
						Objects.equals ( this.applicationId, paymentNotice.applicationId ) &&
						Objects.equals ( this.noticeNumber, paymentNotice.noticeNumber ) &&
						Objects.equals ( this.amount, paymentNotice.amount );
	}

	@Override
	public int hashCode () {
		return Objects.hash ( description, applicationId, noticeNumber, amount );
	}

	@Override
	public String toString () {
		var sb = new StringBuilder ();
		sb.append ( "class PaymentNotice {\n" );
		sb.append ( "    description: " ).append ( toIndentedString ( description ) ).append ( "\n" );
		sb.append ( "    applicationId: " ).append ( toIndentedString ( applicationId ) ).append ( "\n" );
		sb.append ( "    noticeNumber: " ).append ( toIndentedString ( noticeNumber ) ).append ( "\n" );
		sb.append ( "    amount: " ).append ( toIndentedString ( amount ) ).append ( "\n" );
		sb.append ( "}" );
		return sb.toString ();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString ( Object o ) {
		if ( o == null ) {
			return "null";
		}
		return o.toString ().replace ( "\n", "\n    " );
	}

}
