package eu.fiveminutes.cipele46.api;

public interface UserLoginListener {

	void onSuccess();
	
	void onFailure(Throwable t);
}
