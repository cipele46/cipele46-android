package eu.fiveminutes.cipele46.api;

import java.util.Map;

import org.json.JSONArray;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonArrayRequest;

public class UserAdsRequest extends JsonArrayRequest {

	private Map<String, String> headers;

	public UserAdsRequest(String url, Map<String, String> headers, Listener<JSONArray> responseListener, ErrorListener errorListener) {
		super(url, responseListener, errorListener);
		this.headers = headers;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers;
	}

}
