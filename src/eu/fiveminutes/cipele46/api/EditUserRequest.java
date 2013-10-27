package eu.fiveminutes.cipele46.api;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

public class EditUserRequest extends JsonObjectRequest {

	private Map<String, String> headers;

	public EditUserRequest(int method, String url, JSONObject data, Listener<JSONObject> responseListener, ErrorListener errorListener,
			Map<String, String> headers) {
		super(method, url, data, responseListener, errorListener);
		this.headers = headers;
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		// TODO Auto-generated method stub
		return headers;
	}

}
