package com.sf.jjccountrycity.domain;

import java.util.Collection;
import java.util.LinkedHashSet;

import lombok.Data;

@Data
public class Country {
	private String name;
	private Collection<City> cities;
	
	public Country() {
		
	}

	public Country(String name) {
		this.name = name;
		cities = new LinkedHashSet<>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name.toUpperCase() == null) ? 0 : name.toUpperCase().hashCode());
		return result;
	}

}
