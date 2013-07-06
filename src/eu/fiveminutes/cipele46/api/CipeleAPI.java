package eu.fiveminutes.cipele46.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CipeleAPI {

	private static CipeleAPI cipele;
	private RequestQueue reqQueue;
	
	public void init(Context c) {
		reqQueue = Volley.newRequestQueue(c);
	}
	
	public static CipeleAPI get() {
		if (cipele == null) {
			cipele = new CipeleAPI();
		}
		return cipele;
	}
	
	public void getAds(AdsListener adsListener) {
		
	}
	
	
	public void getCategories(CategoriesListener cl) {
		
	}
}
