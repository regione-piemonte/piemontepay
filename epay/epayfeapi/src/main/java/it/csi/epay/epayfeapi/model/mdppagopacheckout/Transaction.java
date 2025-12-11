package it.csi.epay.epayfeapi.model.mdppagopacheckout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@JsonTypeName ( "Transaction" )
@javax.annotation.Generated ( value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
				date = "2023-06-28T10:23:33.078750500+02:00[Europe/Berlin]" )
public class Transaction {

	private @Valid String idTransaction;

	private @Valid String paymentUrl;

	/**
	 * Cart id, will be setted with TRANSACTION_ID
	 **/
	public Transaction idTransaction ( String idTransaction ) {
		this.idTransaction = idTransaction;
		return this;
	}

	@JsonProperty ( "idTransaction" )
	@NotNull
	public String getIdTransaction () {
		return idTransaction;
	}

	@JsonProperty ( "idTransaction" )
	public void setIdTransaction ( String idTransaction ) {
		this.idTransaction = idTransaction;
	}

	/**
	 * pagoPA url for payment
	 **/
	public Transaction paymentUrl ( String paymentUrl ) {
		this.paymentUrl = paymentUrl;
		return this;
	}

	@JsonProperty ( "paymentUrl" )
	@NotNull
	public String getPaymentUrl () {
		return paymentUrl;
	}

	@JsonProperty ( "paymentUrl" )
	public void setPaymentUrl ( String paymentUrl ) {
		this.paymentUrl = paymentUrl;
	}

	@Override
	public boolean equals ( Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass () != o.getClass () ) {
			return false;
		}
		var transaction = (Transaction) o;
		return Objects.equals ( this.idTransaction, transaction.idTransaction ) &&
						Objects.equals ( this.paymentUrl, transaction.paymentUrl );
	}

	@Override
	public int hashCode () {
		return Objects.hash ( idTransaction, paymentUrl );
	}

	@Override
	public String toString () {
		var sb = new StringBuilder ();
		sb.append ( "class Transaction {\n" );
		sb.append ( "    idTransaction: " ).append ( toIndentedString ( idTransaction ) ).append ( "\n" );
		sb.append ( "    paymentUrl: " ).append ( toIndentedString ( paymentUrl ) ).append ( "\n" );
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
