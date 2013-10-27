package eu.fiveminutes.cipele46.model;

import com.google.gson.annotations.SerializedName;

import eu.fiveminutes.cipele46.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class User {

	@SerializedName("first_name")
	private String firstName;

	private String name;

	@SerializedName("last_name")
	private String lastName;
	private String email;
	private String phone;

	private long id;

	private String basicAuth;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}

	public String toString() {
		return firstName + ", " + lastName + ", " + email + ", " + phone;
	}

	public static void setUserAsActive(Context context, User user) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);

		Editor editor = prefs.edit();
		editor.putString(context.getString(R.string.preference_user_first_name), user.getFirstName());
		editor.putString(context.getString(R.string.preference_user_last_name), user.getLastName());
		editor.putString(context.getString(R.string.preference_user_name), user.getName());
		editor.putString(context.getString(R.string.preference_user_email), user.getEmail());
		editor.putString(context.getString(R.string.preference_user_phone), user.getPhone());
		editor.putString(context.getString(R.string.preference_user_basicauth), user.getBasicAuth());
		editor.commit();
	}

	public static void deactivateUser(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);

		Editor editor = prefs.edit();
		editor.putString(context.getString(R.string.preference_user_name), "");
		editor.putString(context.getString(R.string.preference_user_first_name), "");
		editor.putString(context.getString(R.string.preference_user_last_name), "");
		editor.putString(context.getString(R.string.preference_user_email), "");
		editor.putString(context.getString(R.string.preference_user_phone), "");
		editor.putString(context.getString(R.string.preference_user_basicauth), "");
		editor.commit();
	}

	public static User getActiveUser(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.shared_preferences_name), 0);

		if (prefs.getString(context.getString(R.string.preference_user_name), "").equals("")) {
			return null;
		}

		User user = new User();
		user.setName(prefs.getString(context.getString(R.string.preference_user_name), ""));
		user.setFirstName(prefs.getString(context.getString(R.string.preference_user_first_name), ""));
		user.setLastName(prefs.getString(context.getString(R.string.preference_user_last_name), ""));
		user.setEmail(prefs.getString(context.getString(R.string.preference_user_email), ""));
		user.setPhone(prefs.getString(context.getString(R.string.preference_user_phone), ""));
		user.setBasicAuth(prefs.getString(context.getString(R.string.preference_user_basicauth), ""));
		return user;
	}
}
