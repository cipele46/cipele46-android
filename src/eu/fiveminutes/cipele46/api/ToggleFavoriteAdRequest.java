package eu.fiveminutes.cipele46.api;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

public class ToggleFavoriteAdRequest extends JsonRequest<JSONObject> {

	private Map<String, String> headers;

	public ToggleFavoriteAdRequest(int method, String url, String requestBody, Listener<JSONObject> listener, ErrorListener errorListener,
			Map<String, String> headers) {
		super(method, url, requestBody, listener, errorListener);
		this.headers = headers;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new JSONObject().toString();
			return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return headers;
	}

}
