package eu.fiveminutes.cipele46.api;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class NewAdRequest extends JsonObjectRequest{
	
	private Map<String, String> headers;

	public NewAdRequest(int method, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener,Map<String, String> headers) {
		super(method, url, jsonRequest, listener, errorListener);
		this.headers = headers;

	}
	
	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers;
	}

}
