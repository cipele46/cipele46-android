package eu.fiveminutes.cipele46.api;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class RegistrationRequest extends JsonObjectRequest {

	private Map<String, String> headers;
	
	public RegistrationRequest(int method, String url, JSONObject jsonRequest,
			Map<String, String> headers, Listener<JSONObject> listener, 
			ErrorListener errorListener) {
		super(method, url, jsonRequest, listener, errorListener);
		
		this.headers = headers;
	}

	
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

}
