package it.csi.epay.epayfeapi.model.mdppagopacheckout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonTypeName ( "Cart" )
@javax.annotation.Generated ( value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
				date = "2023-06-28T10:23:33.078750500+02:00[Europe/Berlin]" )
public class Cart {

	private @Valid List<PaymentNotice> paymentNotices = new ArrayList<> ();

	private @Valid String emailNotice;

	/**
	 *
	 **/
	public Cart paymentNotices ( List<PaymentNotice> paymentNotices ) {
		this.paymentNotices = paymentNotices;
		return this;
	}

	@JsonProperty ( "paymentNotices" )
	@NotNull
	public List<PaymentNotice> getPaymentNotices () {
		return paymentNotices;
	}

	@JsonProperty ( "paymentNotices" )
	public void setPaymentNotices ( List<PaymentNotice> paymentNotices ) {
		this.paymentNotices = paymentNotices;
	}

	public Cart addPaymentNoticesItem ( PaymentNotice paymentNoticesItem ) {
		if ( this.paymentNotices == null ) {
			this.paymentNotices = new ArrayList<> ();
		}

		this.paymentNotices.add ( paymentNoticesItem );
		return this;
	}

	public Cart removePaymentNoticesItem ( PaymentNotice paymentNoticesItem ) {
		if ( paymentNoticesItem != null && this.paymentNotices != null ) {
			this.paymentNotices.remove ( paymentNoticesItem );
		}

		return this;
	}

	/**
	 * citizen email; provide it for notification system
	 **/
	public Cart emailNotice ( String emailNotice ) {
		this.emailNotice = emailNotice;
		return this;
	}

	@JsonProperty ( "emailNotice" )
	@Size ( max = 256 )
	public String getEmailNotice () {
		return emailNotice;
	}

	@JsonProperty ( "emailNotice" )
	public void setEmailNotice ( String emailNotice ) {
		this.emailNotice = emailNotice;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass () != o.getClass () ) {
			return false;
		}
		var cart = (Cart) o;
		return Objects.equals ( this.paymentNotices, cart.paymentNotices ) &&
						Objects.equals ( this.emailNotice, cart.emailNotice );
	}

	@Override
	public int hashCode () {
		return Objects.hash ( paymentNotices, emailNotice );
	}

	@Override
	public String toString () {
		var sb = new StringBuilder ();
		sb.append ( "class Cart {\n" );
		sb.append ( "    paymentNotices: " ).append ( toIndentedString ( paymentNotices ) ).append ( "\n" );
		sb.append ( "    emailNotice: " ).append ( toIndentedString ( emailNotice ) ).append ( "\n" );
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
