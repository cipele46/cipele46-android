package eu.fiveminutes.cipele46.api;

import java.util.List;

import eu.fiveminutes.cipele46.model.Category;

public interface CategoriesListener {

	void onCategoriesRetrieved(List<Category> categories, Throwable t);
}
