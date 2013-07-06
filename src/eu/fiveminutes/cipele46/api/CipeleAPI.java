package eu.fiveminutes.cipele46.api;

public class CipeleAPI {

	private static CipeleAPI cipele;
	
	public static CipeleAPI get() {
		if (cipele == null) {
			cipele = new CipeleAPI();
		}
		return cipele;
	}
	
	public void getCategories(CategoriesListener cl) {
		
	}
}
