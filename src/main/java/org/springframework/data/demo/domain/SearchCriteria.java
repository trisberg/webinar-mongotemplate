package org.springframework.data.demo.domain;

import java.util.Set;

public class SearchCriteria {

	private Set<String> categories;
	
	private String startYear;

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

}
