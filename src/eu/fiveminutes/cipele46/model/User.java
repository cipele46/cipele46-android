package eu.fiveminutes.cipele46.model;

import eu.fiveminutes.cipele46.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class User {

	private String name;
	private String email;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return name + ", " + email + ", " + phone;
	}
	
	public static void setUserAsActive(Context context, User user) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);
		
		Editor editor = prefs.edit();
		editor.putString(context.getString(R.string.preference_user_name), user.getName());
		editor.putString(context.getString(R.string.preference_user_email), user.getEmail());
		editor.putString(context.getString(R.string.preference_user_phone), user.getPhone());
		editor.commit();	
	}
	
	public static void deactivateUser(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);
		
		Editor editor = prefs.edit();
		editor.putString(context.getString(R.string.preference_user_name), "");
		editor.putString(context.getString(R.string.preference_user_email), "");
		editor.putString(context.getString(R.string.preference_user_phone), "");
		editor.commit();	
	}
	
	public static User getActiveUser(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);
		
		if(prefs.getString(context.getString(R.string.preference_user_name), "").equals("")) {
			return null;
		}
		
		User user = new User();
		user.setName(prefs.getString(context.getString(R.string.preference_user_name), ""));
		user.setEmail(prefs.getString(context.getString(R.string.preference_user_email), ""));
		user.setPhone(prefs.getString(context.getString(R.string.preference_user_phone), ""));
		return user;
	}
}
