package eu.fiveminutes.cipele46.api;

import eu.fiveminutes.cipele46.model.User;

public interface UserLoginListener {

	void onSuccess(User user);
	
	void onFailure(Throwable t);
}
