package eu.fiveminutes.cipele46.app;

import eu.fiveminutes.cipele46.api.CipeleAPI;
import android.app.Application;

public class CipeleApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		CipeleAPI.get().init(this);
	}
}
