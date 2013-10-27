package eu.fiveminutes.cipele46.utils;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.util.Base64;

public class Util {

	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null) && cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	public static byte[] convertBitmapToByteArray(Bitmap bm){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  
		byte[] b = baos.toByteArray();
		return b;
	}
	
	public static String encodeImageBase64(Bitmap bm){
		String encoded = Base64.encodeToString(convertBitmapToByteArray(bm), Base64.DEFAULT);
		return encoded;
	}

}
