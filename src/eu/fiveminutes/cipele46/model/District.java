package eu.fiveminutes.cipele46.model;

import java.util.List;

public class District {

	private Long id;
	private String name;
	private List<City> cities;
	
	public District() {
		
	}
	
	public District(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
}
