package eu.fiveminutes.cipele46.api;

import java.util.ArrayList;
import java.util.Collections;
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
import eu.fiveminutes.cipele46.model.AdStatus;
import eu.fiveminutes.cipele46.model.AdType;
import eu.fiveminutes.cipele46.model.Category;

public class CipeleAPI {

	public enum UserAdSection {
		ACTIVE_ADS,
		FAVORITE_ADS,
		CLOSED_ADS
	}

	
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
	
	
	/**
	 * 
	 * @param type Ad type. Required.
	 * @param categoryID If null, all categories implied.
	 * @param districtID If null, all districts implied.
	 * @param adsListener
	 */
	public void getAds(final AdType type, final Long categoryID, 
			final Long districtID, final AdsListener adsListener) {
		
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				adsListener.onFailure(error);
			}
		};
		
		Listener<JSONArray> arrayListener = new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				
				try {
					List<Ad> adList = parseAdList(response);
					adsListener.onSuccess(adList);
				} catch (JSONException e) {
					adsListener.onFailure(e);
				}
			}
		};
		
		reqQueue.add(
				new JsonArrayRequest("http://dev.fiveminutes.eu/cipele/api/ads", arrayListener, errorListener));
	}
	
	/**
	 * 
	 * @param user ad section, required.
	 * @param adsListener
	 */
	public void getUserAds(UserAdSection userAdSection, final AdsListener adsListener) {
		
		ErrorListener errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				adsListener.onFailure(error);
			}
		};
		
		Listener<JSONArray> arrayListener = new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				
				try {
					List<Ad> adList = parseAdList(response);
					adsListener.onSuccess(adList);
				} catch (JSONException e) {
					adsListener.onFailure(e);
				}
			}
		};
		
		reqQueue.add(
				new JsonArrayRequest("http://dev.fiveminutes.eu/cipele/api/ads", arrayListener, errorListener));
	}
	
	private static List<Ad> parseAdList(JSONArray array) throws JSONException {
		List<Ad> adList = new ArrayList<Ad>();
		
		JSONObject obj;
		for (int i = 0; i < array.length(); i++) {
			obj = array.getJSONObject(i);
			adList.add(parseAd(obj));
		}
		
		return adList;
	}
	
	private static Ad parseAd(JSONObject obj) throws JSONException {
		
		Ad newAd = new Ad();
		newAd.setId(obj.getLong("id"));
		newAd.setTitle(obj.getString("title"));
		newAd.setDescription(obj.getString("description"));
		newAd.setEmail(obj.getString("email"));
		newAd.setPhone(obj.getString("phone"));
		newAd.setImageURLString(obj.getString("imageUrl"));
		newAd.setCityID(obj.getLong("cityID"));
		newAd.setCategoryID(obj.getLong("categoryID"));
		newAd.setDistrictID(obj.getLong("districtID"));
		
		int statusNumber = obj.getInt("status");
		AdStatus status = AdStatus.ACTIVE;
		switch (statusNumber) {
			case 1:
				status = AdStatus.PENDING;
				break;
			case 2:
				status = AdStatus.ACTIVE;
				break;
			case 3:
				status = AdStatus.CLOSED;
				break;
			default:
				throw new IllegalArgumentException("Invalid ad status for ad " + newAd.getTitle());
		}
		newAd.setStatus(status);
		
		int typeNumber = obj.getInt("type");
		AdType type = AdType.SUPPLY;
		switch (typeNumber) {
			case 1:
				type = AdType.SUPPLY;
				break;
			case 2:
				type = AdType.DEMAND;
				break;
			default:
				throw new IllegalArgumentException("Invalid ad type for ad " + newAd.getTitle());
		}
		newAd.setType(type);
		
		return newAd;
	}
	
	public void getCategories(final CategoriesListener cl) {
		
		String url = "http://dev.fiveminutes.eu/cipele/api/categories";
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				Log.i(TAG, response.toString());
				
				List<Category> listofCategory = Collections.<Category>emptyList();
				
				for (int i  =0; i < response.length(); i++) {
					
					if (listofCategory.isEmpty()) {
						listofCategory = new ArrayList<Category>();
					}
					
					JSONObject jsonObject;
					try {
						
						jsonObject = response.getJSONObject(i);
						Category category = new Category();
						category.setId(jsonObject.getString("id"));
						category.setName(jsonObject.getString("name"));
						listofCategory.add(category);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				cl.onSuccess(listofCategory);
				
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

}
