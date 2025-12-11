package it.csi.epay.epayfeapi.dto;

import javax.ws.rs.core.Response;
import java.io.Serializable;


// usato per la risposta dal checkout
public class ModelloUnicoDTO implements Serializable {

	private static final long serialVersionUID = 3273127423474106644L;

	private Response response;

	private String paymentUrl;

	public ModelloUnicoDTO ( Response response ) {
		this.response = response;
	}

	public ModelloUnicoDTO ( String paymentUrl ) {
		this.paymentUrl = paymentUrl;
	}

	public boolean isKO () {
		return response != null && (paymentUrl == null || paymentUrl.isEmpty());
	}

	public Response getResponse () {
		return response;
	}

	public String getPaymentUrl () {
		return paymentUrl;
	}
}
