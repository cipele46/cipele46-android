package eu.fiveminutes.cipele46.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import eu.fiveminutes.cipele46.model.Ad;
import eu.fiveminutes.cipele46.model.Category;

public class CipeleAPI {

	private static CipeleAPI cipele;
	private RequestQueue reqQueue;
	
	private String TAG = this.getClass().getSimpleName();
	
	public void init(Context c) {
		reqQueue = Volley.newRequestQueue(c);
	}
	
	public static CipeleAPI get() {
		if (cipele == null) {
			cipele = new CipeleAPI();
		}
		return cipele;
	}
	
	public void getAds(final AdsListener adsListener) {
		
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				adsListener.onFailure(error);
			}
		};
		
		Listener<JSONArray> arrayListener = new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				
				List<Ad> adList = new ArrayList<Ad>();
				
				try {
					JSONObject obj;
					for (int i = 0; i < response.length(); i++) {
						obj = response.getJSONObject(i);
						
						Ad newAd = new Ad();
						newAd.setId(obj.getLong("id"));
						newAd.setTitle(obj.getString("title"));
						newAd.setDescription(obj.getString("description"));
						newAd.setEmail(obj.getString("email"));
						newAd.setPhone(obj.getString("phone"));
						newAd.setImageURLString(obj.getString("imageURL"));
						newAd.setCityID(obj.getLong("cityID"));
						newAd.setCategoryID(obj.getLong("categoryID"));
						newAd.setDistrictID(obj.getLong("districtID"));
						
						adList.add(newAd);
					}
				} catch (JSONException e) {
					adsListener.onFailure(e);
				}
				
				adsListener.onSuccess(adList);
			}
		};
		
		reqQueue.add(
				new JsonArrayRequest("http://dev.fiveminutes.eu/cipele/api/ads", arrayListener, errorListener));
	}
	
	public void getCategories(final CategoriesListener cl) {
		
		String url = "http://dev.fiveminutes.eu/cipele/api/categories";
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				Log.i(TAG, response.toString());
				cl.onSuccess(parseJSONArray(response), null);
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i(TAG, error.getMessage());
				cl.onFailure(error);
				
			}
		});
		
		reqQueue.add(jsonArrayRequest);
		
	}
	
	private List<Category> parseJSONArray(JSONArray jsonArray) {

		//TODO
		return null;

	}
}
